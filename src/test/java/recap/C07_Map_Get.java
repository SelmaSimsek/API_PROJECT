package recap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C07_Map_Get {
    @Test
    public void map_Get() throws JsonProcessingException {
        // 1. Set the URL
        String url = "https://petstore.swagger.io/v2/pet/9898";

        // Set the expected data
        // 1.YOL (Map oluşturarak)
        Map<String, Object> category = new HashMap<>();
        category.put("id", 0);
        category.put("name", "Köpek");

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");

        Map<String, Object> tagData = new HashMap<>();
        tagData.put("id", 0);
        tagData.put("name", "Sibirya Kurdu");

        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(tagData);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("id", 9898);
        expectedData.put("category", category);
        expectedData.put("name", "Pamuk");
        expectedData.put("photoUrls", photoUrls);
        expectedData.put("tags", tags);
        expectedData.put("status", "available");

        // 2.YOL (ObjectMapper class'ındaki readValue metodu ile)
        String data = "{\"id\":9898,\"category\":{\"id\":0,\"name\":\"Köpek\"},\"name\":\"Pamuk\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"Sibirya Kurdu\"}],\"status\":\"available\"}";
        Map<String, Object> expectedData2 = new ObjectMapper().readValue(data, HashMap.class);

        // 3.YOL (Map metodu ile)
        Map<String, Object> expectedData3 = expectedDataMap(9898, 0, "Köpek", "Pamuk", "string", 0, "Sibirya Kurdu", "available");

        Response response = given().when().get(url);
        Response response2 = given().when().body(expectedData).post(url); // Serialization = Java datasını json datasına dönüştürme
        Map<String, Object> actualData = response.as(HashMap.class); // De-serialization = json datasını java datasına dönüştürme

        assertEquals(expectedData.get("id"), actualData.get("id"));
        assertEquals(expectedData.get("categoryId"), actualData.get("categoryId"));
        assertEquals(expectedData.get("categoryName"), actualData.get("categoryName"));
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("photoUrl"), actualData.get("photoUrl"));
        assertEquals(expectedData.get("tagId"), actualData.get("tagId"));
        assertEquals(expectedData.get("tagName"), actualData.get("tagName"));
        assertEquals(expectedData.get("status"), actualData.get("status"));


    }

    public Map<String, Object> expectedDataMap(int id, int categoryId, String categoryName, String name, String photoUrl, int tagId, String tagName, String status){
        Map<String, Object> category = new HashMap<>();
        category.put("id", categoryId);
        category.put("name", categoryName);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);

        Map<String, Object> tagData = new HashMap<>();
        tagData.put("id", tagId);
        tagData.put("name", tagName);

        List<Map<String, Object>> tags = new ArrayList<>();
        tags.add(tagData);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("id", id);
        expectedData.put("category", category);
        expectedData.put("name", name);
        expectedData.put("photoUrls", photoUrls);
        expectedData.put("tags", tags);
        expectedData.put("status", status);

        return expectedData;
    }

}


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