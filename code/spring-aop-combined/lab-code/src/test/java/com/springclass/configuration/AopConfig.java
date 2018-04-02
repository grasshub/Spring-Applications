package com.springclass.configuration;

import org.mockito.Mockito;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.springclass.aop.advice.BeforeAfterLoggingAdvice;
import com.springclass.bo.KioskServiceImpl;

@Configuration
@Import({JavaConfig.class})
public class AopConfig {

    @Bean
    public BeforeAfterLoggingAdvice beforeAfterLoggingAdvice() {
        return new BeforeAfterLoggingAdvice();
    }

    // TODO LAB: add beforeAfterLoggingAdviceProxy()
    @Bean
    public BeanNameAutoProxyCreator beforeAfterLoggingAdviceProxy() {
    	BeanNameAutoProxyCreator bnapc = new BeanNameAutoProxyCreator();
    	bnapc.setBeanNames("kioskService");
    	bnapc.setInterceptorNames("beforeAfterLoggingAdvice");
    	return bnapc;
    }

    //--- Mocks -------------------------------------------------------------//
 
    @Bean
    public KioskServiceImpl mockKioskService(){
        return Mockito.mock(KioskServiceImpl.class);
    }

} // The End...
