package com.springclass.interceptors;

import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor  {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Logging the request URI
		logger.info("preHandle:: preHandle Execution for URI: {}", request.getRequestURI());
		
		// Logging the request parameters
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			logger.info("--parameter name: {}", parameterNames.nextElement());
		}
		
		// Logging the request header names
		Enumeration<String> headerrNames = request.getHeaderNames();
		while (headerrNames.hasMoreElements()) {
			logger.info("--header name: {}", headerrNames.nextElement());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Logging the view name
		logger.info("postHandle:: postHandle Execution for View: {}", modelAndView.getViewName());
		
		// Logging the handler class name
		logger.info("--handler class name: {}", handler.getClass().getName());
		
		// Logging the response header names
		Collection<String> headerNames = response.getHeaderNames();
		for (String headerName : headerNames) {
			logger.info("--header name: {}", headerName);
		}	
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Logging the handler class name
		logger.info("afterCompletion:: handler class name: {}", handler.getClass().getName());
		
		if(ex != null) {
			logger.error("afterCompletion:: Exception Class: {}, {}", ex.getClass().getName(), ex.getMessage());
		}
		
	}

} // The End...
