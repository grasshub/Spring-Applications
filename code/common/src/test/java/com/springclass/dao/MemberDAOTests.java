package com.springclass.dao;

import com.springclass.configuration.JavaConfig;
import com.springclass.domain.InvalidMemberPasswordException;
import com.springclass.domain.UnknownMemberException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springclass.domain.MemberException;


@ContextConfiguration(classes = JavaConfig.class)
//@ContextConfiguration("classpath*:applicationContext-kiosk.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MemberDAOTests {

    private final Logger logger = LoggerFactory
            .getLogger(MemberDAOTests.class);

    @Autowired
    private MemberDAO dao;


    @Before
    public void beforeEachTest() {

    }

    @Test
    public void test_Get_MemberID() throws Exception {

        String username = "j.smith";
        String password = "hiking";

        String result = dao.getMemberID(username, password);

        assertThat(result, is("93941"));
    }

    @Test(expected = UnknownMemberException.class)
    public void test_GetMemberID_Invalid_UserName() throws Exception {

        String username = "foo";
        String password = "bar";

        String result = dao.getMemberID(username, password);

        fail();
    }

    @Test(expected = InvalidMemberPasswordException.class)
    public void testGetMemberID_InvalidPassword() throws Exception {

        String username = "j.smith";
        String password = "bar";

        String result = dao.getMemberID(username, password);

        fail();
    }

    @Test(expected = MemberException.class)
    public void test_Get_MemberID_Null_Username() throws Exception {

        String username = null;
        String password = "bar";

        String result = dao.getMemberID(username, password);

        fail();
    }

    @Test(expected = MemberException.class)
    public void test__Get_MemberID_Null_Password() throws Exception {

        String username = "foo";
        String password = null;

        String result = dao.getMemberID(username, password);

        fail();
    }

} // The End...
