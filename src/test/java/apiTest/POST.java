package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RestAssuredSetup;

public class POST extends RestAssuredSetup {



    // 1. POST /pet: Yeni pet eklemek
    @Test
    public void testCreatePet() {
        // POST isteği için gerekli JSON veri
        String petJson = "{ \"code\": 0, \"type\": \"string\", \"message\": \"Pet created successfully\" }";

        // POST isteği gönderiyoruz
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(petJson)
                .post("/pet");

        // Yanıtın 200 OK olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 200, "Yeni pet eklenemedi.");

        // Geri dönen mesajı kontrol ediyoruz
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Pet created successfully", "Mesaj beklenenle eşleşmiyor.");
    }
    // Olumsuz Senaryo: Eksik parametre ile POST isteği
    @Test
    public void testCreatePetWithMissingData() {
        // Eksik pet verisi
        String petJson = "{ \"code\": 0, \"type\": \"string\" }";  // message eksik

        // POST isteği gönderiyoruz
        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(petJson)
                .post("/pet");

        // Yanıtın 400 Bad Request olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 400, "Eksik veri ile pet eklenmesine izin verildi.");
    }
}
