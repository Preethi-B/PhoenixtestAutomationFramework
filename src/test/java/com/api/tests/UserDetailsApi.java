package com.api.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import java.io.IOException;

import org.testng.annotations.Test;

import com.api.constant.Role;
import com.api.pojo.Usercredentials;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.Specutil;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import static com.api.constant.Role.FD;
import io.restassured.module.jsv.JsonSchemaValidator;

@Test

public class UserDetailsApi {
	
	public void UserDetailsApiTest() throws IOException {
		
		
		
		
		given().spec(Specutil.requestSpectwithAuth(FD))
.when()
.get("userdetails")
.then()
.spec(Specutil.responseSpec_OK())
.and()
.body("message", equalTo("Success"))
.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));

}
}