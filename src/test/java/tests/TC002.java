package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InsiderPage;

import java.time.Duration;

public class TC002 {

    InsiderPage pages = new InsiderPage();
    private WebDriver driver;

    @Test
    public void tc002() {
        //TC002
        pages.setUp();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html[1]/body[1]/nav[1]/div[2]/div[1]/ul[1]/li[6]/a[1]")));
        element1.click();

    }
}
