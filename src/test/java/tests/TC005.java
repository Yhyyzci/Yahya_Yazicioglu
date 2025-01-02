package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InsiderPage;

import java.time.Duration;

public class TC005 {

    private InsiderPage pages;
    private WebDriver driver;


    @BeforeClass
    public void setUp() {
        pages = new InsiderPage();
        pages.setUp("https://useinsider.com/careers/quality-assurance/");
        driver = pages.getDriver();
    }

    // TC03_04'ün başarılı bir şekilde çalışmasını bekliyoruz
    @Test(dependsOnMethods = {"tests.TC03_04.case3"}) // TC03_04'ün case3 metodunun başarılı olmasını bekler
    public void case4() {
        // TC004: Check that all jobs’ Position contains “Quality Assurance”, Department contains
        // “Quality Assurance”, and Location contains “Istanbul, Turkey”


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. "View Role" butonuna tıklama (ilk buton)
        WebElement viewRoleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'][normalize-space()='View Role'])[1]")));
        viewRoleButton.click();

        // 2. "Başvurunuzu gönderin" butonunun görünmesini bekleme
        WebElement applyButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//font[contains(text(),'Başvurunuzu gönderin')]")));

        // 3. Başvuru butonuna tıklama
        applyButton.click();

        // 4. Başvuru başarılı olduğunu kontrol etme (Başvuru butonunun görünür olması, işlem başarılı demektir)
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("postings-btn template-btn-submit shamrock")));

        // Testin başarılı olduğunu doğrulama
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Başvuru butonu görünür değil, başvuru yapılmadı.");
    }


    @AfterClass
    public void tearDown() {
        pages.tearDown();
    }
}
