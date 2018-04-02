package com.springclass.features;

import com.springclass.domain.MemberException;
import net.serenitybdd.jbehave.SerenityStories;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * http://thucydides.info/docs/serenity-staging/#_serenity_webdriver_support_in_junit
 * http://blog.xebia.in/2015/05/10/different-approaches-to-write-automation-commands-in-selenium-webdriver/
 */
@Narrative(text={"Narrative:" +
        "  In order to successfully use the secured DVD rental application" +
        "  As a potential customer" +
        "  I want to be able to unit test with WhenSecurityTests"})
@RunWith(SerenityRunner.class)
public final class DvdStories extends SerenityStories {

    @Managed(driver="firefox")
    private WebDriver driver;

    @ManagedPages
    public Pages pages;


    @Steps
    public MvcSteps steps;

    //-----------------------------------------------------------------------//


    @Test
    @Screenshots(beforeAndAfterEachStep=true)
    public void gotoListAllDvdPage() throws Exception{
        steps.open_home_page();

        steps.open_list_all_dvd_page();

//        steps.enter_bad_credentials_on_restricted_page("foo", "bar");
    }

    @Test
    @Screenshots(beforeAndAfterEachStep=true)
    public void add_dvd_as_valid_user__with_INVALID_actor() throws Exception {
        steps.open_home_page();
        steps.add_dvd();
        steps.add_valid_dvd("234", "The Expendables 2", "chuck norris", "2012");
        // should fail..
    }






//    @Test
//    @Screenshots(beforeAndAfterEachStep=true)
//    public void login_as_valid_user_OLD() {
//        steps.open_home_page();
//        steps.enter_credentials_on_restricted_page("user", "password");
//        steps.should_see_artifacts_where(the("GroupId", startsWith("net.thucydides")),
//                each("ArtifactId").isDifferent(),
//                the_count(is(greaterThanOrEqualTo(16))));

//    }

    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
    //-----------------------------------------------------------------------//
    @Given("the traveller enters username $username and password $password")
    public void traveller_enters_username_and_password(final String username,
                                                       final String password)
            throws Exception{

//        steps.enter_credentials_on_restricted_page(username, password);
//        this.login_as_valid_user();
    }

    @Then("the traveller will be validated")
    public void traveller_will_be_validated()
            throws MemberException{
//        steps.should_see_title(driver.getTitle(), "DVD Listing");
//        steps.should_see_content_on_page(driver.getPageSource(), "This is secured!");

    }



} //The end...
