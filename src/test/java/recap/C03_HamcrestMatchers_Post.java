package recap;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Math.log;
import static org.hamcrest.Matchers.*;

public class C03_HamcrestMatchers_Post {
    @Test
    public void hamcrestMatchers_Post() {
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


        // 3.send the request and get the response
        // 4.Do assertion
       /* {
            "id": 9898,
                "category": {
            "id": 0,
                    "name": "Köpek"
        },
            "name": "Pamuk",
                "photoUrls": [
            "string"
  ],
            "tags": [
            {
                "id": 0,
                    "name": "Sibirya Kurdu"
            }
  ],
            "status": "available"
        }

        */
        // 1.set the URL
        String url="https://petstore.swagger.io/v2/pet";

        // 2.set the expected data
        String payLoad = "{\n" +
                "  \"id\":9898,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"Köpek\"\n" +
                "  },\n" +
                "  \"name\": \"Pamuk\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"Sibirya Kurdu\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        // 3.send the request and get the response
        // 4.Do assertion
        given().when()
                .body(payLoad)
                .contentType(ContentType.JSON)
                .post(url)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id",equalTo(9898))
                .body("category.id", equalTo(0))
                .body("category.name",equalTo("Köpek"))
                .body("name", equalTo("Pamuk"))
                .body("photoUrls[0]", equalTo("string"))
                .body("tags[0].id", equalTo(0))
                .body("tags[0].name", equalTo("Sibirya Kurdu"))
                .body("status",equalTo("available"))
                .header("Connection", "keep-alive")
                .header("Server", containsString("Jetty"))
                .log().all();






    }


}
