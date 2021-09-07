package tests;

import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutPatchDelete {

    @Test
    public void testPut() {

        baseURI = "https://reqres.in/api/";

        JSONObject request = new JSONObject();

        request.put("name", "Dmitry");
        request.put("job", "leader");

        given().
                contentType(ContentType.JSON).
                body(request.toJSONString()).put("user/2").
        then().
                statusCode(200).
        and().
                body("name", equalTo("Dmitry"));
    }

    @Test
    public void testPatch() {
        baseURI = "https://reqres.in/api/";

        JSONObject request = new JSONObject();
        request.put("name", "Dmitry");
        request.put("job", "toster");

        given().
                contentType(ContentType.JSON).body(request.toJSONString()).
                patch("users/2").
        then().statusCode(200).body("job", equalTo("toster"));
    }

    @Test
    public void testDelete(){
        baseURI = "https://reqres.in/api/";

        given().delete("users/2").then().statusCode(204).log().all();
    }
}
