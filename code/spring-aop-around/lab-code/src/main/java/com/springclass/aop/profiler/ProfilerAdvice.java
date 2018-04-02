package com.springclass.aop.profiler;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.springclass.aop.utils.AOPUtils;
import com.springclass.utils.Auditor;

//TODO LAB: Update to implement interface
public class ProfilerAdvice implements MethodInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	Auditor auditor;

	final String auditKey = this.getClass().getName();

	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		logger.info("Start ProfileAdvice");
		
		long startTime = System.nanoTime();
		
		Object result = invocation.proceed();
		Thread.sleep(15);
		
		long endTime = System.nanoTime();
		
		logger.info("{} method took {} nanosecond to complete", invocation.getMethod().getName(), (endTime - startTime));
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Profiler advised: ");
		auditor.append(auditKey, "Profiler advised: ");
		auditor.append(auditKey, AOPUtils.buildMethodCall(invocation));
		AOPUtils.buildMethodCall(stringBuilder, invocation);
		
		logger.info(stringBuilder.toString());
		logger.info("End ProfileAdvice");
		
		return result;
	}

}
