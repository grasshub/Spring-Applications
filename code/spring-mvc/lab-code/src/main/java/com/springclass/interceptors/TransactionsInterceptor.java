package com.springclass.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TransactionsInterceptor implements HandlerInterceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(TransactionsInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// Logging the start of transaction
		logger.info("*** Starting Transaction ***");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// Logging a successful commit
		logger.info("Successfully committed the Transaction!");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// Logging the transaction was rolled back
		logger.info("Transaction was rolled back!");
	}

}
