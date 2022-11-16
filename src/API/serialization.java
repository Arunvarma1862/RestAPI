package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;
import POJOS.getDate;
import POJOS.getLocation;

public class serialization {
	public static void main(String[] args) {


		RestAssured.baseURI="https://rahulshettyacademy.com";

		getDate gd= new getDate();
		
		gd.setAccuracy(50);
		gd.setAddress("29, side layout, cohen 09");
		gd.setWebsite("http://google.com");
		gd.setLanguage("French-IN");
		gd.setName("Frontline house");
		gd.setPhonenumber("(+91) 983 893 3937");

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		gd.setTypes(myList);

		getLocation gl = new getLocation();
		gl.setLatitude(-38.383494);
		gl.setLongitude(33.427362);
		gd.setLocation(gl);


		RequestSpecification  req  = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON)
				. build();

		ResponseSpecification resp  =  new ResponseSpecBuilder().expectStatusCode(200)
				                          .expectContentType(ContentType.JSON).build();

		RequestSpecification res =  given().log().all().spec(req)                      
				                    .body(gd);
		Response	response=	res.when().post("/maps/api/place/add/json")
				                .then().log().all().spec(resp).extract().response();

		String responseString = response.asString();
		System.out.println("response"+responseString);





		                                         // De-serialization

		JsonPath js1=  new JsonPath(responseString);
		String placeid= js1.get("place_id");	
		System.out.println("place_id is "+placeid);

	/*	getDate getd =  given().param("key","qaclick123").queryParam("place_id",placeid).expect().defaultParser(Parser.JSON)
				.when().get("/maps/api/place/get/json")
			.as(getDate.class);

	    System.out.println(	getd.getAddress());*/


	}
}
