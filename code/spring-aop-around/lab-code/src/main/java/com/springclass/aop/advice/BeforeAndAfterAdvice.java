package com.springclass.aop.advice;

import com.springclass.aop.utils.AOPUtils;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

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
public class BeforeAndAfterAdvice
        implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    // TODO LAB: add before() method
    public void before(final Method method,
                       final Object[] args,
                       final Object target) throws Throwable {

        StringBuilder sb = new StringBuilder();

        // TODO: change args[2] for password
        if(args.length == 5) {
            logger.warn("*** Password entered was {}, but is being changed to {}.", args[2], "");
            args[2] = "whoknows";
        }

        sb.append("before: ");
        AOPUtils.buildMethodCall(sb, target, method, args);

        logger.info(sb.toString());
    }

    @Override
    // TODO LAB: add afterReturning() method
    public void afterReturning(Object result,
                               final Method method,
                               final Object[] args,
                               final Object target) {
        // TODO: change result to be returned
        if(result instanceof Integer) {
            result = 9494L;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("afterReturning: ");
        AOPUtils.buildMethodCall(sb, target, method, args);
        sb.append(result.toString());

        logger.info(sb.toString());
    }

    // TODO LAB: add afterThrowing() method
    public void afterThrowing(final Method method,
                              final Object[] args,
                              final Object target,
                              final Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("Throwing: ");
        AOPUtils.buildMethodCall(sb, target, method, args);
        sb.append(e.toString());

        logger.info(sb.toString());
    }

} // The End...
