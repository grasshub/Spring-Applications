package com.springclass.aop.utils;

import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class AOPUtils {

    private static final Logger logger = LoggerFactory.getLogger(AOPUtils.class);

    public static String displayJoinPointDetails(final String appendMessage,
                                                 final JoinPoint jp) {

        Signature sign = jp.getSignature();
        return new StringBuilder(appendMessage)
                .append(" joinPoint: ")
                .append(jp.getStaticPart().toLongString())
                .append(" signature: ")
                .append(sign.toLongString())
                .toString();
    }

    /**
     * Build a detailed method call, and append it to  StringBuilder.
     * @param target
     * @param method
     * @param args
     */
    public static String buildMethodCall(final Object target,
                                         final Method method,
                                         final Object[] args) {
        StringBuilder sb = new StringBuilder();
        buildMethodCall(sb, target, method, args);
        return sb.toString();
    }

    /**
     * Build a detailed method call, and append it to  StringBuilder.
     * @param sb StringBuilder
     * @param target
     * @param method
     * @param args
     */
    public static void buildMethodCall(final StringBuilder sb,
                                       final Object target,
                                       final Method method,
                                       final Object[] args) {

        sb.append(target.getClass().getSimpleName());
        sb.append(".");
        sb.append(method.getName());
        sb.append("(");

        buildArgumentsCall(sb, args);

        sb.append(")");
    }

    public static String buildMethodCall(final MethodInvocation methodInvocation) {
        StringBuilder sb = new StringBuilder();
        buildMethodCall(sb, methodInvocation);
        return sb.toString();
    }

    public static void buildMethodCall(final StringBuilder sb,
                                       final MethodInvocation methodInvocation) {

        sb.append(methodInvocation.getClass().getSimpleName());
        sb.append(".");
        sb.append(methodInvocation.getMethod().getName());
        sb.append("(");

        buildArgumentsCall(sb, methodInvocation.getArguments());

        sb.append(")");
    }


    private static StringBuilder buildArgumentsCall(final StringBuilder sb,
                                             final Object[] args){
        boolean firstArg = true;

        for (Object arg : args) {
            if (!firstArg) {
                sb.append(",");
            }
            ;
            firstArg = false;
            boolean isString = (arg.getClass() == String.class);
            if (isString) sb.append('"');
            sb.append(arg.toString());
            if (isString) sb.append('"');
        }
        return sb;
    }

} // The End...
