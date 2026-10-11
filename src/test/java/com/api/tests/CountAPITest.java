package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.api.constant.Role;
import com.api.constant.Role;
import com.api.utils.Specutil;
import static com.api.constant.Role.FD;

import static com.api.utils.AuthTokenProvider.*;
import static  com.api.utils.ConfigManager.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {

	@Test
	
	public void verifyCountAPIResponse() {
		
	
		
		given().spec(Specutil.requestSpectwithAuth(FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(Specutil.responseSpec_OK())
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
		.spec(Specutil.responseSpec_Text(401));
}
}