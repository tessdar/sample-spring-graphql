package com.common.aspect;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.exception.ResolverException;
import com.common.security.JwtManager;
import com.common.util.MessageTrans;
import graphql.schema.DataFetchingEnvironment;
import graphql.servlet.context.DefaultGraphQLServletContext;

@Component
@Aspect
public class GraphQLResolverAspect {

	@Autowired
	private JwtManager jwtManager;

	@Autowired
	private MessageTrans messageTrans;

	@Before("execution(* com.example.resolver.*.*(..))")
	public void LanguageConfig(JoinPoint joinPoint) {

		Object[] joinArgs = joinPoint.getArgs();

		if (joinArgs.length < 1)
			return;

		DataFetchingEnvironment dataFetEnv = (DataFetchingEnvironment) joinArgs[joinArgs.length - 1];

		DefaultGraphQLServletContext dgServCtx = dataFetEnv.getContext();
		HttpServletRequest request = dgServCtx.getHttpServletRequest();

		if (request.getHeader("Language") == null) {
			messageTrans.setLang("ko");
		} else {
			messageTrans.setLang(request.getHeader("Language"));
		}
	}

	@Around("execution(* com.example.resolver.*.*(..))")
	public Object LoginCheck(ProceedingJoinPoint proceedJoinPoint) throws Throwable {

		Object[] joinArgs = proceedJoinPoint.getArgs();

		if (joinArgs.length < 1) {
			throw new ResolverException("Error with Proceed JoinPoint.", "500");
		}

		DataFetchingEnvironment dataFetEnv = (DataFetchingEnvironment) joinArgs[joinArgs.length - 1];

		DefaultGraphQLServletContext dgServCtx = dataFetEnv.getContext();
		HttpServletRequest request = dgServCtx.getHttpServletRequest();

		String authToken = request.getHeader("Authorization");

		if (authToken == null || !authToken.startsWith("Bearer ")) {
			throw new ResolverException("There is no Authorization header", "401");

		} else {
			String token = authToken.substring("Bearer".length()).trim();

			try {
				jwtManager.verifyToken(token);

			} catch (Exception e) {
				throw new ResolverException("Error with verifying token.", "401");
			}

			return proceedJoinPoint.proceed();
		}

	}

}
