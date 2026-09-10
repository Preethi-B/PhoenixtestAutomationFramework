package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.Usercredentials;
import com.api.utils.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	
	@Test
	
	public void loginApiTest() throws IOException {
	

		
		Usercredentials usercredentials = new Usercredentials("iamfd","password");
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.and()
		.contentType(ContentType.JSON)
		.and()
		.accept(ContentType.JSON)
		.body(usercredentials)
		.log().uri()
		.log().body()
		.log().headers()
		.log().method()
		.when()
		.post("login")
		.then()
		.log().all()
		.time(lessThan(1500L))
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
