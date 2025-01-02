package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RestAssuredSetup; // Yapılandırma sınıfını dahil ediyoruz

public class DELETE extends RestAssuredSetup {

    // Olumsuz Senaryo: Geçersiz pet ID'si ile DELETE isteği
    @Test
    public void testDeletePetWithInvalidId() {
        // Geçersiz pet ID'si
        long invalidPetId = 13;

        // DELETE isteği gönderiyoruz
        Response response = RestAssured.delete("/pet/" + invalidPetId);

        // Yanıtın 404 Not Found olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 404, "Geçersiz pet ID ile silme işlemi başarılı oldu.");
    }
}
