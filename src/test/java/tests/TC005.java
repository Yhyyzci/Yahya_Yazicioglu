package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


    @Test //
    public void case4() throws InterruptedException {
        // TC005: Click the “View Role” button and check that this action redirects us to the Lever
        //Application form page


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 1. "See all QA jobs" butonuna tıklama
        WebElement seeAllQaJobsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50']")));
        seeAllQaJobsButton.click();

        Thread.sleep(5000);
        // Sayfayı 600px kaydırma
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 600);"); // 600px kadar sayfayı kaydır

      // Actions sınıfını başlatıyoruz
        Actions actions = new Actions(driver);

     // Butonu bulma ve görünür olduğundan emin olma
        WebElement viewRoleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='position-list-item col-12 col-lg-4 sales istanbul-turkey full-time']//a[@class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5'][normalize-space()='View Role']")));

     // Fareyi butonun üzerine getirme
        actions.moveToElement(viewRoleButton).perform();

        // Butona tıklama
        viewRoleButton.click();


        // 2. Yönlendirme sonrasında yeni sayfanın URL'sinin yüklenmesini bekleme
        WebDriverWait waitForNewPage = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForNewPage.until(ExpectedConditions.urlToBe("https://jobs.lever.co/useinsider/6b1cb4da-e2b8-4eff-83b7-2931c44b4e69"));

        // 3. Yeni sayfada öğeyi bulma ve tıklama
        WebElement applyButton = waitForNewPage.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='postings-btn-wrapper']//a[@class='postings-btn template-btn-submit shamrock'][normalize-space()='Apply for this job']")));
        applyButton.click();


        // 4. Başvuru butonuna tıklama
        applyButton.click();


       // 5. Başvuru butonunun görünür olması, işlem başarılı demektir)
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//font[contains(text(),'Submit your application')]")));

        // Testin başarılı olduğunu doğrulama
        Assert.assertTrue(confirmationMessage.isDisplayed(), "Submit your application değil, başvuru yapılmadı.");

    }


    @AfterClass
    public void tearDown() {
        pages.tearDown();
    }
}
