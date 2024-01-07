package recap;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C06_JsonPath_Post {
    @Test
    public void jsonPath_Post() {
        String url="https://petstore.swagger.io/v2/pet";
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

        Response response = given().when().body(payLoad)
                .contentType(ContentType.JSON).post(url);
        JsonPath json = response.jsonPath();

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals(9898, json.getInt("id"));
        assertEquals("Pamuk", json.getString("name"));
        assertEquals("available", json.getString("status"));
        assertEquals("Köpek", json.getString("category.name"));
        assertEquals("Sibirya Kurdu", json.getString("tags[0].name"));
    }
}
