package com.springclass.aop.utils;

import com.springclass.aop.example.Store;
import com.springclass.aop.example.StoreImpl;
import com.springclass.domain.DVDInfo;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.fail;

import static org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry;  // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions

import static org.mockito.Mockito.*;

public class AOPUtilsTest {


    private AOPUtils aopUtils;

    @Mock
    private JoinPoint joinPoint;

    @Mock
    private Signature signature;

    @Mock
    private JoinPoint.StaticPart staticPart;

    @Mock
    private MethodInvocation methodInvocation;

    @Before
    public void beforeEachTest(){
        MockitoAnnotations.initMocks(this);
        aopUtils = new AOPUtils();
    }

    @After
    public void afterEachTest(){
    }

    @Test
    public void test__displayJoinPointDetails() throws Exception{

        final String signatureLongString = "before()";
        final String staticPartLongString = "foo(bar)";
        final String expected = "original message joinPoint: foo(bar) signature: before()";

        when(joinPoint.getSignature()).thenReturn(signature);
        when(joinPoint.getStaticPart()).thenReturn(staticPart);
        when(staticPart.toLongString()).thenReturn(staticPartLongString);
        when(signature.toLongString()).thenReturn(signatureLongString);

        String result = AOPUtils.displayJoinPointDetails("original message", joinPoint);

        assertThat(result, is(expected));

        verify(joinPoint).getSignature();
        verify(joinPoint).getStaticPart();
        verify(staticPart).toLongString();
        verify(signature).toLongString();
    }


    @Test
    public void test__BuildMethodCall__zero_arguments() throws Exception{
        String result = AOPUtils.buildMethodCall(new Object(),
                                                 this.getClass().getDeclaredMethods()[0],
                                                 new Object[0]);
        assertThat(result).startsWith("Object.");
                //.endsWith("EachTest()");
    }

    @Test
    public void test__BuildMethodCall__MethodInvocation() throws Exception{
        final Object[] args = new Object[]{new DVDInfo(), "foo", "bar"};

        final String expected = "com.springclass.domain.DVDInfo@";
        final String expectedEndsWith = "],\"foo\",\"bar\")";

        when(methodInvocation.getMethod())
                .thenReturn(this.getClass().getDeclaredMethods()[0]);

        when(methodInvocation.getArguments()).thenReturn(args);

        final String result = AOPUtils.buildMethodCall(methodInvocation);
        assertThat(result)
//                .contains(expected)
                .endsWith(expectedEndsWith);

        verify(methodInvocation).getArguments();
    }


    @Test
    public void test__BuildMethodCall__multipleArguments() throws Exception{
        Object[] args = new Object[]{new DVDInfo(), "foo", "bar"};
        String result = AOPUtils.buildMethodCall(new Object(),
                                                 this.getClass().getDeclaredMethods()[0],
                                                 args);
        assertThat(result).contains("(com.springclass.domain.DVDInfo")
                .endsWith("\"foo\",\"bar\")")
        ;
    }

} // The End...
