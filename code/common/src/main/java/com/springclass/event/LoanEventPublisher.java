package com.springclass.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public final class LoanEventPublisher {

    private static final Logger logger = LoggerFactory
            .getLogger(LoanEventPublisher.class);

    private final ApplicationEventPublisher publisher;

    @Autowired
    public LoanEventPublisher(final ApplicationEventPublisher publisher){
        this.publisher = publisher;
    }

    public void publishLoanEvent(int loanId) {
        LoanEvent event = new LoanEvent(this, loanId);
        logger.info("publish new LoanEvent: {}", event);

        this.publisher.publishEvent(event);
    }

} // The End...
