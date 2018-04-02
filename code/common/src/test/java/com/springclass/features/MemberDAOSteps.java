package com.springclass.features;

import com.springclass.configuration.JavaConfig;
import com.springclass.dao.MemberDAO;
import com.springclass.domain.AirportLocation;
import com.springclass.domain.InvalidMemberPasswordException;
import com.springclass.domain.MemberException;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * This is a JBehave test, but the support comes from the serenity libraries.
 */
@ContextConfiguration(classes = JavaConfig.class)
public class MemberDAOSteps {

    private final Logger logger = LoggerFactory
            .getLogger(this.getClass());

    @Autowired
    private MemberDAO dao;

    String userId;

    @Given("the member enters username $username and password $password")
    public void member_enters_username_and_password(final String username,
                                                    final String password)
    throws MemberException{
        String result = dao.getMemberID(username, password);
        this.userId = result;
    }


    @Given("the member enters username $username and incorrect password $password")
    public void member_enters_username_and_incorrect_password(final String username,
                                                    final String password)
    throws MemberException{
        try {
            String result = dao.getMemberID(username, password);
//            this.userId = result;
        } catch (InvalidMemberPasswordException e){
            assertThat(e, is(notNullValue()));
            this.userId = "invalid";
        }
    }


    @Then("the member will be validated as userid $userId")
    public void member_will_be_validated(final String userId)
    throws MemberException{
        logger.info("-------------------------------------------------");
        logger.info("traveller_will_be_validated");
        assertThat(this.userId, is(userId));
    }

} // The End...
