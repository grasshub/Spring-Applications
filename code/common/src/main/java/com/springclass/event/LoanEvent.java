package com.springclass.event;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.context.ApplicationEvent;

import java.util.Date;

public final class LoanEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;
    private int loanId;
    private Date eventDate;

    public LoanEvent(Object source, int id) {
        super(source);
        this.loanId = id;
        eventDate = new Date();
    }

    public int getLoanId() {
        return loanId;
    }

//    public void setLoanId(int loanId) {
//        this.loanId = loanId;
//    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

} // The End...
