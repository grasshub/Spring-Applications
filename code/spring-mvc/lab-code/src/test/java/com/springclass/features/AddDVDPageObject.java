package com.springclass.features;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;

@DefaultUrl("http://localhost:8888/spring-mvc/inventory/addDVD.html")
public final class AddDVDPageObject extends PageObject {


    private final Wait<WebDriver> wait = null;
    /*
    wait = new WebDriverWait(driver, 20);
     */

//    @FindBy(name="addDVD")
    @FindBy(id="command")
    WebElement addDVDForm;


    @FindBy(name="id")
    WebElement idField;

    @FindBy(name="title")
    WebElement titleField;

    @FindBy(name="actors")
    WebElement actorsField;

    @FindBy(name="releaseYear")
    WebElement releaseYearField;

    public void addDvd(final String id,
                       final String title,
                       final String actors,
                       final String releaseYear) {
        idField.sendKeys(id);
        titleField.sendKeys(title);
        actorsField.sendKeys(actors);
        releaseYearField.sendKeys(releaseYear);

//        addDVDForm.submit();
    }

} // The End...
