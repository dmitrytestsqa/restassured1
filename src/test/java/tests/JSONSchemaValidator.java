package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

public class JSONSchemaValidator {

    @Test
    public void test_1() {

        baseURI = "https://reqres.in/api/";

        given().
                get("users?page=2").
        then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    }

}
