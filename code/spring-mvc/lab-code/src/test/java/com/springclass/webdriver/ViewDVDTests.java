package com.springclass.webdriver;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(JUnit4.class)
public class ViewDVDTests {

    private static final Logger logger = LoggerFactory
            .getLogger(ViewDVDTests.class);

    private static final String INDEX = "http://localhost:8888/spring-mvc/index.html";

    private static final String VIEW_ALL = "http://localhost:8888/spring-mvc/inventory/viewAll.html";

//    URL local = new URL("http://localhost:9515");
//    WebDriver driver = new RemoteWebDriver(local, DesiredCapabilities.chrome());

    private WebDriver driver;

    @Before
    public void beforeEachTest() {
        driver = new FirefoxDriver();
//        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
    }

    @After
    public void afterEachTest() {
        driver.quit();
    }

    //--- Tests -------------------------------------------------------------//

    @Test
    public void loginAsUser() throws Exception {

        driver.navigate().to(INDEX);

        //-------------------------------------------------------------------//
        //-- HOME
        String pageTitle = driver.getTitle();
        ___captureScreenShot(driver);
        assertThat(pageTitle, is("DVD Management Index"));

        //-------------------------------------------------------------------//
        //-- View All
        driver.findElement(By.linkText("View all DVDs (html)")).click();

        //-------------------------------------------------------------------//
        //-- DVD Listing & logged in.
        pageTitle = driver.getTitle();
        ___captureScreenShot(driver);
        assertThat(pageTitle, is("DVD Listing"));
        __pageContains(driver, "Harry Potter and the Prisoner of Azkaban (Widescreen Edition) (Harry Potter 3)");

        // wait 5 seconds and close the browser
        Thread.sleep(5_000);

    } // end test




    //--- Utilities ---------------------------------------------------------//

    public static void __pageContains(final WebDriver driver, final String content){
        assertThat(
                driver.getPageSource().contains(content),
                is(true));
    }


    /**
     * Take an image snap-shot of the test page.
     * USE: captureScreenShot(driver);
     * @param driver WebDriver
     */
    public static void ___captureScreenShot(final WebDriver driver) {

        // Take screen-shot and store as a file format
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String pageTitle = driver.getTitle();

        try {
            // now copy the  screenshot to desired location using copyFile method
            FileUtils.copyFile(source, new File("./target/snap-shots/" +pageTitle+"-"+ System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

} // The End...
