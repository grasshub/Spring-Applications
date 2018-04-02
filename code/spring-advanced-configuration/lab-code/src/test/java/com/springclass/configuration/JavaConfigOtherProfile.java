package com.springclass.configuration;

import com.springclass.bo.KioskService;
import com.springclass.bo.KioskServiceImpl;
import com.springclass.cache.InventoryManagerCachableMock;
import com.springclass.dao.*;
import com.springclass.service.InventoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Profile("other")
@EnableCaching
public class JavaConfigOtherProfile {

    private final Logger logger = LoggerFactory.getLogger(JavaConfigOtherProfile.class);

    @Bean
    @DependsOn("cacheManager")
    @Lazy(value = false)
    public InventoryManager manager(){
        return new InventoryManagerCachableMock();
    }

    @Bean
    @DependsOn("ehCacheCacheManager")
    @Profile("other")
    public CacheManager cacheManager() {
        logger.info("Cache manager is ehCacheCacheManager");
        return new EhCacheCacheManager(ehCacheCacheManager().getObject());
    }

    @Bean
    @Profile("other")
    public EhCacheManagerFactoryBean ehCacheCacheManager() {
        EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
        cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
        cmfb.setShared(true);
        return cmfb;
    }

} // the End...
