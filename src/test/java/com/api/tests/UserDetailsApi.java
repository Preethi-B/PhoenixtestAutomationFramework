package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.Usercredentials;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.module.jsv.JsonSchemaValidator;

@Test

public class UserDetailsApi {
	
	public void UserDetailsApiTest() throws IOException {
		
		
		
		Header authHeader = new Header("Authorization",AuthTokenProvider.getToken("FD"));

		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
.and()
.contentType(ContentType.JSON)
.header(authHeader)
.log().uri()
.log().body()
.log().headers()
.log().method()
.when()
.get("userdetails")
.then()
.statusCode(200)
.log().all()
.time(lessThan(1500L))
.and()
.body("message", equalTo("Success"))
.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

}
}