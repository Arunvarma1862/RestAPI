package API;

import io.restassured.RestAssured;
import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.payLoad;

public class Basic2 {
    public static void main(String[] args) {
    	
    	         //  1.validate if Add place API is working as expected
  
    	
     	RestAssured.baseURI="https://rahulshettyacademy.com";
    	
         given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
    	.body(payLoad.AddPlace()).when().post("maps/api/place/add/json")
    	.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
    	.header("server","Apache/2.4.41 (Ubuntu)");
    
    }
}
