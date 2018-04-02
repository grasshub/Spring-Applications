package com.springclass.features;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@DefaultUrl("http://localhost:8888/spring-mvc/inventory/ListAll.html")
public final class ListAllPageObject extends PageObject {

    @FindBy(name="j_username")
    WebElement usernameField;

    @FindBy(name="j_password")
    WebElement passwordField;

    @FindBy(name="f")
    WebElement loginForm;


    public void listAll() {
        this.open();

        /*
        WebElement textbox = driver.findElement(By.name("q"));
        textbox.sendKeys("selenium commands");
        WebElement button = driver.findElement(By.name("btnG"));
        button.click();
         */
//        usernameField.sendKeys(username);
//        passwordField.sendKeys(password);
//        loginForm.submit();

//        waitFor(titleContains("Successful Login"));
    }

} // The End...
