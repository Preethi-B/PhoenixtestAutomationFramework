package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.AuthTokenProvider;
import static  com.api.utils.ConfigManager.*;

import static com.api.utils.AuthTokenProvider.*;
import static  com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPI {

	@Test
	
	public void masterAPItest() {
		
		given().baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken("FD"))
		.log().all()
		.contentType("")
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1000L))
		.body("message", equalTo("Success"))
		.body("data", notNullValue());
		
		
		
	}
		
	
}