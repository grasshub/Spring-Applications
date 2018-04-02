package com.springclass.features;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

@DefaultUrl("http://localhost:8888/spring-mvc/index.html")
public final class IndexPageObject extends PageObject {


    private final Wait<WebDriver> wait;

    @FindBy(name="viewAll")
    private WebElement viewAllLink;

    @FindBy(name="addDVD")
    private WebElement addDVDLink;


    public IndexPageObject(final WebDriver driver) {
        super();
        setDriver(driver);
        wait = new WebDriverWait(driver, 20);
    }


    public void open_list_all_dvd_page() {
        viewAllLink.click();
    }

} // The End...
