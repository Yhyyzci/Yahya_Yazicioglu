package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class InsiderPage {

    protected WebDriver driver;  // Bu değişkeni protected yapıyoruz ki test sınıfı erişebilsin.

    // WebDriver'ı başlatma ve URL'ye gitme işlemi
    public void setUp(String url) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // WebDriver'ı başlatıyoruz
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url); // Parametre olarak gelen URL'ye gidiyoruz
    }

    // @AfterClass, test bitiminde yalnızca bir kez çalışır ve tarayıcıyı kapatır
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // WebDriver'ı döndüren bir metod ekliyoruz
    public WebDriver getDriver() {
        return driver;
    }
}
