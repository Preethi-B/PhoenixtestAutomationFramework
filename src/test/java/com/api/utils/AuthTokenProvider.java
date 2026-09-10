package com.api.utils;
import static io.restassured.RestAssured.*;

import com.api.pojo.Usercredentials;

import io.restassured.http.ContentType;
public class AuthTokenProvider {

	public static String getToken(String role) {

	    Usercredentials usercredentials = null;

	    if (role.equalsIgnoreCase("FD")) {
	        usercredentials = new Usercredentials("iamfd", "password");
	    }
	    
	    else if(role.equalsIgnoreCase("SUP")){
	    	
	    	 usercredentials = new Usercredentials("iamsup", "password");
	    }
	    else if(role.equalsIgnoreCase("ENG")) {
	    	 usercredentials = new Usercredentials("iameng", "password");
	    
	}
		
String token =	given()
	.baseUri(ConfigManager.getProperty("BASE_URI"))
	.contentType(ContentType.JSON)
	.body(usercredentials)
.when()
.post("login")
.then()
.log().ifValidationFails()
.extract()
.body()
.jsonPath()
.getString("data.token");
	
return token;
		// TODO Auto-generated method stub

	}

}
