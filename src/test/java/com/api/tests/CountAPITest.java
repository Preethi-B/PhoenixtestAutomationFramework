package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static com.api.utils.AuthTokenProvider.*;
import static  com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {

	@Test
	
	public void verifyCountAPIResponse() {
		
	
		
		given().baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken("FD"))
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label",everyItem(not(blankOrNullString())))
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema.json"));
		
		
		// TODO Auto-generated method stub

	}

	@Test
	public void CountAPIResponse_Missing() {
		
	
		
		given().baseUri(getProperty("BASE_URI"))
		.and()
		.when()
		.get("dashboard/count")
		.then()
		.log().all()
		.log().headers()
		.log().body()
		.statusCode(401);
}
}