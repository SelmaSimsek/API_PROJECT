package recap;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class C05_JsonPath_GetList {
     /*
    Given
        https://petstore.swagger.io/v2/pet/findByStatus?status=available
    When
        Kullanıcı URL'e bir GET request gönderir
    Then
        HTTP Status Code 200 olmalı
    And
        Content Type "application/json" olmalı
    And
        Listede id değeri 9898 olan bir eleman olmalı
    And
        Listede name değeri "Pamuk" olan bir eleman olmalı
    And
        Listede name değerleri "Pamuk", "doggie", "fish" olan elemanlar olmalı
    And
        Listede en az 200 tane eleman olmalı
    And
        Listede 1000'den az eleman olmalı
    And
        Listenin ilk elemanının category - id değeri 0 olmalı
    And
        Listenin ilk elemanının photoUrls değeri "string" olmalı
    And
        Listenin ilk elemanının tags - id değeri 0 olmalı
 */
    static int id;
    @Test
    public void jsonPath_GetList() {
        String url = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";

        Response response = given().when().get(url);
        JsonPath json = response.jsonPath();

        id = json.getInt("find{it.name=='Pamuk}.id");
        System.out.println("id = " + id);

        assertEquals(200, response.statusCode());
        assertEquals("application/json" , response.contentType());
        assertTrue(json.getList("findAll{it.id==9898}").size()>0);
        assertTrue(json.getList("findAll{it.name=='Pamuk'}").size()>0);
    }

    @Test
    public void petDelete() {
        System.out.println(id);
        String url = "https://petstore.swagger.io/v2/pet/" + id;

        given().when().delete().prettyPrint();

    }
}
