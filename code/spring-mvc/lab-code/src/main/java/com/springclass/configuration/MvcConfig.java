package com.springclass.configuration;

import com.springclass.interceptors.LoggingInterceptor;
import com.springclass.interceptors.TransactionsInterceptor;

import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.WebContentInterceptor;
import org.springframework.web.servlet.view.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * @Profile
 *
 */

// TODO: Lab: you need to configure Spring in order for MVC features to be eneabled in your applications

@Configuration
@ComponentScan(basePackages = {"com.springclass.presentation", "com.springclass.validation"})
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {


    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //-------------------------------------------------------------------//
    // TODO: Lab: create internal viewResolver() here:
    public InternalResourceViewResolver viewResolver() {
    	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    	resolver.setPrefix("/WEB-INF/views/");
    	resolver.setSuffix(".jsp");
    	resolver.setAlwaysInclude(true);
    	return resolver;
    }

    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        //-------------------------------------------------------------------//
        // TODO: Lab: add the viewResolver to the ViewResolverRegistry
    	registry.viewResolver(viewResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("403");
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1_000_000L);
        return multipartResolver;
    }

    // i18N support
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
        resource.setBasenames("classpath:locales/messages");
        resource.setDefaultEncoding("UTF-8");
        return resource;
    }

    // Interceptor configuration
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {

        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(webContentInterceptor());

        registry.addInterceptor(loggingInterceptor());
        registry.addInterceptor(transactionsInterceptor());
    }
    
    @Bean
    public LoggingInterceptor loggingInterceptor() {
    	LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
    	return loggingInterceptor;
    }
    
    @Bean
    public TransactionsInterceptor transactionsInterceptor() {
    	TransactionsInterceptor transactionsInterceptor = new TransactionsInterceptor();
    	return transactionsInterceptor;
    }

    @Bean
    public LocaleResolver localeResolver(){
    	CookieLocaleResolver resolver = new CookieLocaleResolver();
    	resolver.setDefaultLocale(new Locale("en"));
    	resolver.setCookieName("languageCookie");
    	resolver.setCookieMaxAge(4800);
        return resolver;
    }

    /**
     * ?mylocale=en: Change the locale for English message source.
     * ?mylocale=de: Change the locale for German message source.
     *
     * @return LocaleChangeInterceptor
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("language");
        return interceptor;
    }

//    @Bean
    public UserRoleAuthorizationInterceptor userRoleAuthorizationInterceptor() {
        UserRoleAuthorizationInterceptor interceptor = new UserRoleAuthorizationInterceptor();
        interceptor.setAuthorizedRoles("ADMIN", "DBA");
        return interceptor;
    }

    @Bean
    public WebContentInterceptor webContentInterceptor() {
        WebContentInterceptor interceptor = new WebContentInterceptor();
        interceptor.setCacheControl(CacheControl.maxAge(4800, TimeUnit.SECONDS));
        
        interceptor.setSupportedMethods("GET", "POST");
        interceptor.setRequireSession(false);
        return interceptor;
    }

} // The End...
