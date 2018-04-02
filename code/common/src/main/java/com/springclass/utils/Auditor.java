package com.springclass.utils;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Auditor {

    final Map<String, StringBuilder> auditor = new HashMap<>();

    public void add(final String key, final String value){
        append(key, value);
    }

    public void append(final String key, final String value){
        if(auditor.containsKey(key)){
            auditor.get(key).append(value);
        } else{
            auditor.put(key, new StringBuilder(key));
        }
    }

    public String getAuditTrail(final String key){
        if(auditor.containsKey(key)){
            return auditor.get(key).toString();
        } else{
            return "audit trail for ("+key+") not found!!!";
        }
    }

    public String toString(){
        return ToStringBuilder.reflectionToString(auditor.toString());
    }

} // The End...
