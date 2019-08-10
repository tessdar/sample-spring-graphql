package com.common.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.common.exception.GraphQLErrorAdapter;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.core.GraphQLErrorHandler;

@Component
public class GraphQLCustomError implements GraphQLErrorHandler {

	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> errors) {
		List<GraphQLError> clientErrors = errors.stream()
				.filter(this::isClientError)
				.collect(Collectors.toList());

		List<GraphQLError> serverErrors = errors.stream()
				.filter(e -> !isClientError(e))
				.map(GraphQLErrorAdapter::new)
				.collect(Collectors.toList());

		List<GraphQLError> e = new ArrayList<>();
		e.addAll(clientErrors);
		e.addAll(serverErrors);
		return e;
	}
	
	protected boolean isClientError(GraphQLError error) {
		return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
	}

}
