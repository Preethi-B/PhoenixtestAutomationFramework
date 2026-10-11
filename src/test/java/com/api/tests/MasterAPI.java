package com.api.tests;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.AuthTokenProvider;
import com.api.utils.Specutil;

import static  com.api.utils.ConfigManager.*;

import com.api.constant.*;

import static com.api.utils.AuthTokenProvider.*;
import static  com.api.utils.ConfigManager.*;
import static com.api.constant.Role.FD;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPI {

	@Test
	
	public void masterAPItest() {
		
		given().spec(Specutil.requestSpectwithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(Specutil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue());
		
		
		
	}
		
	
}