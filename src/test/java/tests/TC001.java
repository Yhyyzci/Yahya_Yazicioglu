package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InsiderPage;

public class TC001 {

    InsiderPage pages = new InsiderPage();
    private WebDriver driver;

    @Test
    public void t() {

        //TC001

        pages.setUp();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, "https://useinsider.com/", "URL doğru değil!");
        

    }
}