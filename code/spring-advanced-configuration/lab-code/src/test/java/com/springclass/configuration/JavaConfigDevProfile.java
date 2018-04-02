package com.springclass.configuration;

import com.springclass.cache.InventoryManagerCachableMock;
import com.springclass.service.InventoryManager;
import com.springclass.stubs.service.InventoryManagerMock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class JavaConfigDevProfile {

    private final Logger logger = LoggerFactory.getLogger(JavaConfigDevProfile.class);

    @Bean
    @DependsOn("cacheManager")
    public InventoryManager manager(){
        return new InventoryManagerMock();
    }

    @Bean
    @Profile("dev")
    public CacheManager cacheManager() {
        logger.info("Cache manager is concurrentMapCacheManager");
        return new ConcurrentMapCacheManager("dvdCache");
    }

} // the End...
