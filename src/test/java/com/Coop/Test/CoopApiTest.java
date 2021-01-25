package com.Coop.Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Coop.Base.baseClass;
import com.Coop.Utils.getPropertyData;
import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
public class CoopApiTest extends baseClass {
	
		
	public CoopApiTest() throws IOException {
		getPropertyData propdata= new getPropertyData();
	}
	
	@Test(priority = 1)
	public static String getToken() {
		Response resp= RestAssured.given()
				
				.formParam("client_id", getPropertyData.getClientId())
				.formParam("client_secret", getPropertyData.getClientSecret())
				.formParam("grant_type", getPropertyData.getGrantTypeString())
				.baseUri(getPropertyData.getBaseUrl())
				.post("/token");
		
		    String token=resp.jsonPath().getString("access_token");
		    return token;
	}
	
	@Test(priority = 2)
	public void postChikenFeedApi() {
		
		given()
		    .auth()
		    .oauth2(getToken())
		
		  .when()
		    .baseUri(getPropertyData.getBaseUrl())
		    .post("/api/1646/chickens-feed")
		  
		  .then()
		      .statusCode(200)
		      .statusLine("HTTP/1.1 200 OK")
		      .log().all();
		         
		
	}
	
	@Test(priority = 3)
	public void getAppInfo() {
		
		        given()
		            .auth()
                    .oauth2(getToken())
		         .when()
		            .baseUri(getPropertyData.getBaseUrl())    
		            .get("/api/me")
		         .then()
		            .statusCode(200)
			        .statusLine("HTTP/1.1 200 OK")
			        .log().all();
	}
	
	@Test(priority = 4)
	public void postEggsCollectApi() {
	
		         given()
                  .auth()
                  .oauth2(getToken())
                     .when()
                       .baseUri(getPropertyData.getBaseUrl())    
                       .post("/api/1646/eggs-collect")
                     .then()
                       .statusCode(200)
	                   .statusLine("HTTP/1.1 200 OK")
	                   .log().all();

	}
	
	@Test(priority = 5)
	public void postEggsCountApi() {
				given()
        .auth()
        .oauth2(getToken())
           .when()
             .baseUri(getPropertyData.getBaseUrl())    
             .post("/api/1646/eggs-count")
           .then()
             .statusCode(200)
             .statusLine("HTTP/1.1 200 OK")
             .log().all();
		
	}
	
	@Test(priority = 6)
	public void postBarnUnlockApi() {
				given()
        .auth()
        .oauth2(getToken())
           .when()
             .baseUri(getPropertyData.getBaseUrl())    
             .post("/api/1646/barn-unlock")
           .then()
             .statusCode(200)
             .statusLine("HTTP/1.1 200 OK")
             .log().all();
	}
	
	@Test(priority =7)
	public void postSeatDownApi() {
		
		given()
        .auth()
        .oauth2(getToken())
           .when()
             .baseUri(getPropertyData.getBaseUrl())    
             .post("/api/1646/toiletseat-down")
           .then()
             .statusCode(200)
             .statusLine("HTTP/1.1 200 OK")
             .log().all()
		     .assertThat()
		     .body("action", equalToObject("toiletseat-down"));	     
	}	
}
