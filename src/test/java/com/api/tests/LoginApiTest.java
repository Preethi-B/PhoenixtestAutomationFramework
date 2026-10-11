package com.api.tests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.pojo.Usercredentials;
import com.api.utils.ConfigManager;
import com.api.utils.Specutil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class LoginApiTest {
	
	@Test
	
	public void loginApiTest() throws IOException {
	

		
		Usercredentials usercredentials = new Usercredentials("iamfd","password");
		
		given().spec(Specutil.requestSpec(usercredentials))
		.when()
		.post("login")
		.then()
		.spec(Specutil.responseSpec_OK())
		.and()
		.body("message", equalTo("Success"))
		.and()
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
	}

}
