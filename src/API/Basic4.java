package API;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import Files.Reusablemethods;
import Files.payLoad;

public class Basic4 {
	public static void main(String[] args) {

		//  1.validate if Add place API is working as expected
		//  2. Add place -> Update Place with New Address -> GET place to validate is present in response


		//  RestASSured works on three principles is
		//   Given  - All input details to API
		//   When   - Submit to API   -  resource,HTTP-method
		//   Then   - Validate the response


		//Add place

		RestAssured.baseURI="https://rahulshettyacademy.com";


		String response  = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payLoad.AddPlace()).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();


		System.out.println(response);
		JsonPath js= new JsonPath(response);                // for parsing json ,To access anything from-
		String placeid= js.getString("place_id");	       // -json document parse into jsonPath using jsonPath class
		System.out.println("place_id is "+placeid);


		//update place

		String newAddress="summer walk,Africa";

		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


		// GET place

		String getResponse=  given().log().all().queryParam("key","qaclick123").param("place_id", placeid)
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();

		JsonPath js1=  Reusablemethods.rawToJson(getResponse);
		String Actualaddress=  js1.getString("address");
		System.out.println(Actualaddress);

		Assert.assertEquals(Actualaddress, newAddress);

		//cucumber junit  ,TestNg




	}


}
