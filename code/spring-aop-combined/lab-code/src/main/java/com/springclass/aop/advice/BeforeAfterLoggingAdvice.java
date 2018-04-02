package com.springclass.aop.advice;

import java.lang.reflect.Method;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import com.springclass.aop.utils.AOPUtils;

/**
 * <p/>
 * This component and its source code representation are copyright protected
 * and proprietary to The Trivera Group, Inc., Worldwide D/B/A Trivera Technologies
 * <p/>
 * This component and source code may be used for instructional and
 * evaluation purposes only. No part of this component or its source code
 * may be sold, transferred, or publicly posted, nor may it be used in a
 * commercial or production environment, without the express written consent
 * of the Trivera Group, Inc.
 * <p/>
 * Copyright (c) 2014 The Trivera Group, LLC.
 * http://www.triveratech.com   http://www.triveragroup.com
 * </p>
 *
 * @author The Trivera Group Tech Team.
 */
@Aspect
public class BeforeAfterLoggingAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final int FIVE = 5;
	private final int TWO = 2;
	private final String PASSWORD = "whoknows";
	private final Integer LOAN_ID = 9494;

    // TODO add before() method here:
	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		
		logger.info("The before method advice details\n");
		String output = AOPUtils.buildMethodCall(target, method, args);
		logger.info(output);	
		
		// Correct the wrong third argument at method call for password
		/*if (args.length == FIVE) {
			args[TWO] = PASSWORD;
		}
		*/
	}

    // TODO add afterReturning() method here:
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		
		logger.info("The after method advice details\n");
		String output = AOPUtils.buildMethodCall(target, method, args);
		logger.info(output);	
		
		// Correct the wrong load id return due to incorrect password 
		/*if (returnValue instanceof Integer) {
			returnValue = LOAN_ID;
		}
		*/
	}

    // TODO add afterThrowing() method here:
	public void afterThrowing(Method method, Object[] args, Object target, Throwable t) throws Throwable {
		
		logger.info("The thrown method advice details\n");
		String output = AOPUtils.buildMethodCall(target, method, args);
		logger.info(output);	
	}

} // The End...
