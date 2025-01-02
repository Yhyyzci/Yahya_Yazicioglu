package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GET {

    // Pet ID'sine göre GET isteği yaparak pet bilgilerini alıyoruz.
    @Test
    public void testGetPetById() {
        // 1. API endpoint'i ve ID'yi tanımlıyoruz.
        long petId = 1;  // Test için geçerli bir pet ID'si
        String endpoint = "https://petstore.swagger.io/v2/pet/" + petId;  // API URL'si oluşturuluyor

        // 2. RestAssured ile GET isteği gönderiyoruz.
        Response response = RestAssured.get(endpoint);  // GET isteği gönderilir

        // 3. Yanıt kodunu kontrol ediyoruz (200 OK olmalı).
        Assert.assertEquals(response.getStatusCode(), 200, "Pet bilgileri başarıyla alınamadı.");  // Yanıtın başarılı olduğunu doğruluyoruz.

        // 4. Yanıtın JSON formatında olduğunu kontrol ediyoruz.
        Assert.assertTrue(response.getContentType().contains("application/json"), "Yanıt JSON formatında değil.");  // Yanıtın JSON formatında olup olmadığını kontrol ediyoruz.

        // 5. Yanıtın içeriğinde doğru pet bilgilerini kontrol ediyoruz.
        String petName = response.jsonPath().getString("name");  // Pet adı alınır
        long petIdFromResponse = response.jsonPath().getLong("id");  // Pet ID'si alınır
        String petStatus = response.jsonPath().getString("status");  // Pet durumu alınır

        // 6. Pet bilgilerini doğruluyoruz.
        Assert.assertEquals(petIdFromResponse, petId, "Pet ID'si eşleşmiyor.");  // Pet ID'sinin doğru olduğunu kontrol ediyoruz
        Assert.assertNotNull(petName, "Pet adı bulunamadı.");  // Pet adının null olmadığını kontrol ediyoruz
        Assert.assertEquals(petStatus, "available", "Pet durumu beklenen değerle eşleşmiyor.");  // Pet durumunun "available" olduğunu kontrol ediyoruz
    }

    // Olumsuz senaryo: Geçersiz bir ID ile pet bilgisi alınmaya çalışılıyor
    @Test
    public void testGetPetByInvalidId() {
        // 1. Geçersiz bir pet ID'si tanımlıyoruz.
        long invalidPetId = 999999999999999999L;  // Geçersiz bir ID
        String endpoint = "https://petstore.swagger.io/v2/pet/" + invalidPetId;  // API URL'si oluşturuluyor

        // 2. RestAssured ile GET isteği gönderiyoruz.
        Response response = RestAssured.get(endpoint);  // GET isteği gönderilir

        // 3. Yanıt kodunun 404 Not Found olması gerektiğini kontrol ediyoruz.
        Assert.assertEquals(response.getStatusCode(), 404, "Geçersiz pet ID ile istek başarılı oldu.");  // Yanıt kodunun 404 olduğunu doğruluyoruz

        // 4. Hata mesajının doğru olduğunu kontrol ediyoruz.
        String message = response.jsonPath().getString("message");  // Hata mesajı alınır
        Assert.assertEquals(message, "Pet not found", "Hata mesajı beklenenle eşleşmiyor.");  // Hata mesajının doğru olup olmadığını kontrol ediyoruz
    }
}
