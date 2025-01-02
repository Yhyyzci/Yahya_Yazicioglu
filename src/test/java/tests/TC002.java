package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.InsiderPage;

import java.time.Duration;

public class TC002 {

    private InsiderPage pages;
    private WebDriver driver;


    @BeforeClass
    public void setUp() {
        pages = new InsiderPage();
        pages.setUp("https://useinsider.com/");
        driver = pages.getDriver();
    }


    @Test
    public void case2() throws InterruptedException {
        // TC002: Select the “Company” menu in the navigation bar, select “Careers” and check Career
        //page, its Locations, Teams, and Life at Insider blocks are open or not
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement Company = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='nav-link dropdown-toggle'])[5]")));
        Company.click();

        WebElement careers =driver.findElement(By.xpath("//a[normalize-space()='Careers']"));
        careers.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;

        boolean elementFound = false;
        int maxScrolls = 10;
        int i = 0;

        while (!elementFound && i < maxScrolls) {
            try {

                js.executeScript("window.scrollBy(0, 2000);");  // Sayfayı 2000px aşağı kaydır

                // See all teams kontrolü
                WebElement element1 = driver.findElement(By.xpath("(//a[normalize-space()='See all teams'])[1]"));
                if (element1.isDisplayed()) {
                    System.out.println("See all teams' görünür hale geldi.");
                    elementFound = true;  // Öğeyi bulduk, döngü sonlandırılabilir
                }

                // Our Locations kontrolü
                WebElement element2 = driver.findElement(By.xpath("(//h3[normalize-space()='Our Locations'])[1]"));
                if (element2.isDisplayed()) {
                    System.out.println("Our Locations' görünür hale geldi.");
                    elementFound = true;
                }

                // Life at Insider kontrolü
                WebElement element3 = driver.findElement(By.xpath("(//h2[normalize-space()='Life at Insider'])[1]"));
                if (element3.isDisplayed()) {
                    System.out.println("Life at Insider' görünür hale geldi.");
                    elementFound = true;
                }

            } catch (NoSuchElementException e) {

                System.out.println("Bir öğe bulunamadı, kaydırmaya devam ediyorum.");
            }


            Thread.sleep(1000);
            i++;
        }

    }


    @AfterClass
    public void tearDown() {
        pages.tearDown();
    }
}
