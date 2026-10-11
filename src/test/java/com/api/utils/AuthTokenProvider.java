package com.api.utils;
import static io.restassured.RestAssured.*;

import com.api.constant.Role;
import com.api.pojo.Usercredentials;
import static com.api.constant.Role.*;
import io.restassured.http.ContentType;
public class AuthTokenProvider {
	
	
	private AuthTokenProvider(){
		
	}

	public static String getToken(Role role) {

	    Usercredentials usercredentials = null;

	    if (role == FD ){
	        usercredentials = new Usercredentials("iamfd", "password");
	    }
	    
	    else if(role==SUP){
	    	
	    	 usercredentials = new Usercredentials("iamsup", "password");
	    }
	    else if(role==ENG) {
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
