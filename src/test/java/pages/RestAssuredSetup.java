package pages;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;

public class RestAssuredSetup {

    // Base URL
    public static final String BASE_URL = "https://petstore.swagger.io/v2";

    // Setup method: Her testten önce yapılacak genel ayarlar
    @BeforeClass
    public void setup() {
        // RestAssured base URI ayarlanıyor
        RestAssured.baseURI = BASE_URL;
    }
}
