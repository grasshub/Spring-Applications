package com.springclass.utils;

//import static org.hamcrest.MatcherAssert.assertThat;
import com.springclass.aop.utils.AOPUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

public class AuditorTest {

    private Auditor auditor;

    @Before
    public void beforeEachTest(){
        auditor = new Auditor();
    }

    @After
    public void afterEachTest(){
        auditor = null;
    }

    @Test
    public void test__add() {
        String key = "foo";
        String value = "bar";
        auditor.add(key, value);
        String result = auditor.getAuditTrail(key);
        assertThat(result).isEqualTo("foo");
    }

    @Test
    public void test__append() {
        String key = "foo";
        String value = "bar";

        auditor.add(key, value);
        auditor.append(key, value + value);
        String result = auditor.getAuditTrail(key);
        assertThat(result).isEqualTo("foobarbar");
    }

    @Test
    public void test__getAuditTrail_with_key() {
        String key = "foo";
        String value = "bar";
        auditor.add(key, value);
        String result = auditor.getAuditTrail(key);
        assertThat(result).isEqualTo("foo");
    }

    @Test
    public void test__getAuditTrail_with_out_key() {
        String key_search = "NOT THERE";
        String key = "foo";
        String value = "bar";
        auditor.add(key, value);

        String result = auditor.getAuditTrail(key_search);
        assertThat(result).isEqualTo("audit trail for (NOT THERE) not found!!!");
    }

    @Test
    public void test__toString() {
        String key = "foo";
        String value = "bar";
        auditor.add(key, value);

        String result = auditor.toString();

        assertThat(result).startsWith("java.lang.String");
    }


} // The End...
