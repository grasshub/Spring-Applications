package com.springclass.features;

import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MvcSteps extends ScenarioSteps {

//    public SecuritySteps(Pages pages) {
//        super(pages);
//    }

//    @ManagedPages
//    public Pages pages;

    @Step
    public void open_home_page() {
        onIndexPage().open();
        assertThat(this.getDriver().getTitle(), is("DVD Management Index"));
    }

    @Step
    public void open_list_all_dvd_page() {
        onIndexPage().open_list_all_dvd_page();
    }

    @Step
    public void add_dvd() {
        onAddDVDPageObjectPage().open();
    }

    @Step
    public void add_valid_dvd(final String id,
                              final String title,
                              final String actors,
                              final String releaseYear) {
        onAddDVDPageObjectPage().addDvd(id,
                                        title,
                                        actors,
                                        releaseYear);
    }


    @Step
    public void should_see_content_on_page(final String content,
                                           final String expected) {
        assertThat(
                content.contains(expected),
                is(true));
    }

    @Step
    public void should_not_see_content_on_page(final String content,
                                               final String expected) {
        assertThat(
                content.contains(expected),
                is(false));
    }

    @Step
    public void should_see_title(final String actual,
                                 final String expected) {
        assertThat(actual, is(expected));
    }

//    @Step
//    public void should_see_artifacts_where(BeanMatcher... matchers) {
//        shouldMatch(onSearchResultsPage().getSearchResults(), matchers);
//    }

    private IndexPageObject onIndexPage() {
        return getPages().get(IndexPageObject.class);
    }

    private ListAllPageObject onListAllPage() {
        return getPages().get(ListAllPageObject.class);
    }

    private AddDVDPageObject onAddDVDPageObjectPage() {
        return getPages().get(AddDVDPageObject.class);
    }


    private boolean isElementPresent(By by) {
        try {
            getPages().getDriver().findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


} // The end...
