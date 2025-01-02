package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RestAssuredSetup;

public class GET extends RestAssuredSetup {

    // Pet ID'sine göre GET isteği yaparak pet bilgilerini alıyoruz.
    // 3. GET /pet/{petId}: Pet ID'ye göre pet almak
    @Test
    public void testGetPetById() {
        // Geçerli pet ID'sini kullanıyoruz
        long petId = 10;

        // GET isteği gönderiyoruz
        Response response = RestAssured.get("/pet/" + petId);

        // Yanıtın 200 OK olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 200, "Pet bilgileri alınamadı.");

        // Pet bilgilerini kontrol ediyoruz
        String petName = response.jsonPath().getString("name");
        Assert.assertEquals(petName, "doggie", "Pet adı beklenenle eşleşmiyor.");
    }

    // Olumsuz Senaryo: Geçersiz pet ID'si ile GET isteği
    @Test
    public void testGetPetByInvalidId() {
        // Geçersiz pet ID'si
        long invalidPetId = 54756;

        // GET isteği gönderiyoruz
        Response response = RestAssured.get("/pet/" + invalidPetId);

        // Yanıtın 404 Not Found olduğunu doğruluyoruz
        Assert.assertEquals(response.getStatusCode(), 404, "Geçersiz pet ID ile isteğin başarılı olmasına izin verildi.");

        // Hata mesajını kontrol ediyoruz
        String message = response.jsonPath().getString("message");
        Assert.assertEquals(message, "Pet not found", "Hata mesajı beklenenle eşleşmiyor.");
    }
}
