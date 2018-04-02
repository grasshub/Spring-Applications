package com.springclass.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RefreshEventListener {

    private static final Logger logger = LoggerFactory
            .getLogger(RefreshEventListener.class);

    @EventListener
    public void handleRefreshEvent(final ContextRefreshedEvent event) {
        logger.info("handle handleRefreshEvent: {}", event);
    }


} // The End...
