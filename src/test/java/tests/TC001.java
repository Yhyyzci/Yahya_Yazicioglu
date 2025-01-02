package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InsiderPage;

public class TC001 {

    private InsiderPage pages;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        pages = new InsiderPage();
        pages.setUp("https://useinsider.com/");
        driver = pages.getDriver();
    }

    @Test
    public void case1() {
        // TC001 - URL doğrulama
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://useinsider.com/", "URL doğru değil!");
    }


    @AfterClass
    public void tearDown() {
        pages.tearDown();
    }
}
