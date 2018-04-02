package com.springclass.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public final class LoanEventListener {

    private static final Logger logger = LoggerFactory
            .getLogger(LoanEventListener.class);

    List<LoanEvent> events = new LinkedList<>();

    @EventListener
    public void handleNewLoan(final LoanEvent event){
        logger.info("handle LoanEvent: {}", event);
        events.add(event);
    }

} // The End...
