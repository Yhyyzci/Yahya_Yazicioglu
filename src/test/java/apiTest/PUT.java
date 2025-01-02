package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RestAssuredSetup;

public class PUT extends RestAssuredSetup {

    // 2. PUT /pet: Pet güncelleme
    @Test
    public void testUpdatePet() {
        // PUT isteği için gerekli JSON veri
        String updatedPetJson = "{"
                + "\"id\": 10,"
                + "\"category\": {\"id\": 1, \"name\": \"Dog\"},"
                + "\"name\": \"doggie\","
                + "\"photoUrls\": [\"https://example.com/photo1.jpg\"],"
                + "\"tags\": [{\"id\": 1, \"name\": \"cute\"}],"
                + "\"status\": \"available\""
                + "}";

        // PUT isteği gönderiyoruz
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(updatedPetJson)
                .put("/pet");

        // Yanıtın 200 OK olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 200, "Pet güncellenemedi.");

        // Güncellenmiş pet bilgisini kontrol ediyoruz
        String petName = response.jsonPath().getString("name");
        String petStatus = response.jsonPath().getString("status");
        Assert.assertEquals(petName, "doggie", "Pet adı beklenenle eşleşmiyor.");
        Assert.assertEquals(petStatus, "available", "Pet durumu beklenenle eşleşmiyor.");
    }
}
