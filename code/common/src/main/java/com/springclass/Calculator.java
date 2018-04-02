package com.springclass;

/**
 * Created by mickknutson on 1/27/16.
 */
public class Calculator {

    public static int add(final int a, final int b){
        return a + b;
    }

    public static int subtract(final int a, final int b){
        return a - b;
    }

    public static int multiply(final int a, final int b){
        return a * b;
    }

    public static int divide(final int a, final int b){
        return a / b;
    }

    public static void main(final String[] args){
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        System.out.println("Output: " + add(a, b));
    }

} // The End...
