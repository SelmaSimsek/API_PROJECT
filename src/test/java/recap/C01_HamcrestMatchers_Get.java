package recap;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C01_HamcrestMatchers_Get {
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
    public void hamcrestMatchers_Get() {
        // 1.set the URL
        String url = "https://petstore.swagger.io/v2/pet/9898";

        // 2.set the expected data

        // 3.send the request and get the response
        given().when().get(url)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", containsString("Pamuk"))
                .body("status", equalTo("available"))
                .body("category.name", equalTo("Köpek"))
                .body("tags[0].name", equalTo("Sibirya Kurdu"))
                .body("tags.find{it.id==0}.name", equalTo("Sibirya Kurdu"))
                .header("Connection", equalTo("keep-alive"))
                .header("Server", containsString("Jetty"))
                .log().all();



    }
}
