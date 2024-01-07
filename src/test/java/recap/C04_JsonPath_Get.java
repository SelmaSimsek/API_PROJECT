package recap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C04_JsonPath_Get {
      /*
    Given
        https://petstore.swagger.io/v2/pet/9898
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı
    And
        "name" şu metni içermeli: "Pamuk",
    And
        "status" değeri "available" olmalı
    And
        "category" altındaki "name" değeri "Köpek" olmalı
    And
        "tags" altındaki "name" değeri "Sibirya Kurdu" olmalı

       */

    @Test
    public void jsonPath_Get() {
        String url = "https://petstore.swagger.io/v2/pet/9898";

        Response response = given().when().get(url);
        JsonPath json = response.jsonPath();

        // JsonPath, json bir data içerisinde bir değişkenin değerini alıp,
        // bir konteynıra koymamızı sağlar.

        int id = json.getInt("id"); //9898
        String name = json.getString("name"); //Pamuk

        assertEquals(200, response.statusCode());
        assertEquals("application/json" , response.contentType());
        assertEquals("Pamuk",json.getString("name"));
        assertEquals("available",json.getString("status"));
        assertEquals("Köpek",json.getString("category.name"));
        assertEquals("Sibirya Kurdu",json.getString("tags[0].name"));



    }
}
