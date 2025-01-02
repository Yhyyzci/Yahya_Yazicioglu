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

public class TC03_04 {

    private InsiderPage pages;
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        pages = new InsiderPage();
        pages.setUp("https://useinsider.com/careers/quality-assurance/");
        driver = pages.getDriver();
    }


    @Test
    public void case3() throws InterruptedException {
        // TC003; Go to https://useinsider.com/careers/quality-assurance/, click “See all QA jobs”, filter
        //jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the
        //presence of the job list

        // Explicit wait oluşturuluyor
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. "See all QA jobs" butonuna tıklama
        WebElement seeAllQaJobsButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='btn btn-outline-secondary rounded text-medium mt-2 py-3 px-lg-5 w-100 w-md-50']")));
        seeAllQaJobsButton.click();


        // 2. Location filtresi: İstanbul, Türkiye'yi seçme
       WebElement locationFilter = driver.findElement(By.xpath("(//span[@role='presentation'])[1]"));
        locationFilter.click();
        WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'İstanbul, Türkiye')]")));
        locationOption.click();

        // 3. Department filtresi: "Quality Assurance" seçme
        WebElement departmentFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[@role='combobox'])[2]")));
        departmentFilter.click();
        WebElement departmentOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//font[contains(text(),'Kalite Güvencesi')]")));
        departmentOption.click();

        // 4. İş listesinin görüntülenmesini doğrulama
        WebElement jobList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("no-job-result")));

        // Eğer iş listesi görünüyorsa, test başarılıdır.
        Assert.assertFalse(jobList.isDisplayed(), "Job list is not displayed or no jobs found.");
    }


    @AfterClass
    public void tearDown() {
        pages.tearDown();
    }
}
