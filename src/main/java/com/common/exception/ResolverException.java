package com.common.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorClassification;
import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

@SuppressWarnings("serial")
public class ResolverException extends RuntimeException implements GraphQLError {

	private Map<String, Object> extensions = new HashMap<>();
	
    public ResolverException(String message, String errorCode) {
        super(message);
        extensions.put("Error Code", errorCode);
    }
	
	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }
	
	@Override
	public ErrorClassification getErrorType() {
		return ErrorType.DataFetchingException;
	}

}
