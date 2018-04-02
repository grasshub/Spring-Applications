package com.springclass.aop.example;

import org.springframework.stereotype.Component;

@Component("storeTarget")
public class StoreImpl implements Store {

    public double purchaseItem(final String item) {
        int length = item.length();
        double price = length * Math.random() * 5;
        return price;
    }

} // The End...
