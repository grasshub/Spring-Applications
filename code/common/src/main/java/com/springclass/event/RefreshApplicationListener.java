package com.springclass.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class RefreshApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory
            .getLogger(RefreshApplicationListener.class);

    public void onApplicationEvent(final ContextRefreshedEvent event){
        logger.info("handle ContextRefreshedEvent: {}", event);
    }

} // The End...
