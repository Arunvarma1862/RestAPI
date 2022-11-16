package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Files.payLoad;

public class Basic3 {
    public static void main(String[] args) {
    	
    	//  1. Validate if Add place API is working as expected
    	//  2. Add place -> Update Place with New Address -> GET place to validate is present in response
    	
    	//  RestASSured works on three principles is
    	
    	//   Given  - All input details to API
    	//   When   - Submit to API   -  resource,HTTP-method
		//   Then   - Validate the response
    
    	
    	
    	RestAssured.baseURI="https://rahulshettyacademy.com";
    	
       String response  = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
    	.body(payLoad.AddPlace()).when().post("maps/api/place/add/json")
    	.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
    	.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
    
    
      System.out.println(response);
      JsonPath js= new JsonPath(response);                // for parsing json
      String placeid= js.getString("place_id");	
      System.out.println("place_id is "+placeid);
	}
}
