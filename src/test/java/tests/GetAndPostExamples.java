package tests;

import netscape.javascript.JSObject;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class GetAndPostExamples {

    @Test
    public void testGet() {

        baseURI = "https://reqres.in/api";

        given().
                get("users?page=2").
        then().
                statusCode(200).body("data[2].first_name", equalTo("Tobias")).
        and().
                body("data[2].id", equalTo(9)).
        and().
                body("data.first_name", hasItems( "Lindsay", "Byron"));

    }

    @Test
    public void testPOST() {
        baseURI = "https://reqres.in/api";
        JSONObject request = new JSONObject();
        request.put("name", "morpheus");
        request.put("job", "leader");

        given().
                header("Content-Type", "application/json").body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).log().body();
    }

}
