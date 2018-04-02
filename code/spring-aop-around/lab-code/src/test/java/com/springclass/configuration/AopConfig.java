package com.springclass.configuration;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.springclass.aop.advice.BeforeAndAfterAdvice;
import com.springclass.aop.profiler.ProfilerAdvice;
import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.utils.Auditor;

@Configuration
@Import({ JavaConfig.class })
public class AopConfig {

	// --- Exercise: SPRING AOP – AROUND ADVICE --------------------------//
	@Bean
	public ProxyFactoryBean kioskService(KioskServiceImpl target, ProfilerAdvice advice) throws ClassNotFoundException {
		
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setProxyInterfaces(new Class[] {KioskService.class});
		proxyFactoryBean.addAdvice(advice);
		proxyFactoryBean.setTarget(target);
		
		return proxyFactoryBean;
	}
	
	@Bean
	public ProfilerAdvice profilerAdvice() {
		ProfilerAdvice advice = new ProfilerAdvice();
		advice.setAuditor(auditor());
		return advice;
	}
	// -- End Exercise: SPRING AOP - AROUND ADVICE -------------------------//

	@Bean
	public BeforeAndAfterAdvice beforeAndAfterAdvice() {
		return new BeforeAndAfterAdvice();
	}

	@Bean
	public BeanNameAutoProxyCreator beforeAndAfterAdviceProxy() {
		BeanNameAutoProxyCreator bnapc = new BeanNameAutoProxyCreator();
		bnapc.setBeanNames("kioskService");
		bnapc.setInterceptorNames("beforeAndAfterAdvice");
		return bnapc;
	}

	@Bean
	public Auditor auditor() {
		return new Auditor();
	}
}
