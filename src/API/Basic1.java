package API;

import io.restassured.RestAssured;
import  static io.restassured.RestAssured.*;
import Files.payLoad;

public class Basic1 {
    public static void main(String[] args) {
    	
    	              
    	//   RestASSured works on three principles is
    	
    	
    	//  1. Given  - All input details 
    	//  2. When   - Submit  to  API                 -> resource , HTTP-method
		//  3. Then   - Validate the response
    
    //      1.validate if Add place API is working as expected
    	
    	
    	RestAssured.baseURI="https://rahulshettyacademy.com";
    	
         given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
    	.body(payLoad.AddPlace()).when().post("maps/api/place/add/json")
    	.then().log().all().assertThat().statusCode(200);
    	
    
    

	}
}
