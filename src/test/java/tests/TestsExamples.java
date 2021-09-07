package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestsExamples {

    @Test
    public void test_1() {

        Response res =  RestAssured.get("https://reqres.in/api/users?page=2");

        System.out.println(res.getStatusCode());
        System.out.println(res.getTime());
        System.out.println(res.getBody().prettyPrint());
        System.out.println(res.getHeader("content-type"));

        int statusCode = res.getStatusCode();

        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void test_2() {

        baseURI = "https://reqres.in/api/";

        given().
                get("users/2").
        then().
                statusCode(200).body("data.id", equalTo(2));
    }

    @Test
    public void test_3() {
        baseURI = "https://reqres.in/api/";

        given().
                get("https://reqres.in/api/unknown").
        then().
                statusCode(200).body("data[5].name", equalTo("blue turquoise")).
        and().
                body("data[5].year", equalTo(2005));
    }

}
