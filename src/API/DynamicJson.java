package API;


import org.testng.annotations.Test;

import Files.Reusablemethods;
import Files.payLoad;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

public class DynamicJson {

	@Test
	public void addBook() {

		   // AddBook
	
		RestAssured.baseURI="http://216.10.245.166";

		String response = given().log().all().header("Content-Type","application/json")
				.body(payLoad.addBook("sthvnn", "5522025"))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js= Reusablemethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);


		    //DeleteBook

		RestAssured.baseURI="http://216.10.245.166";
	    String resp = given().log().all().header("Content-Type","application/json")
		.body(id)
		.when().delete("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
	  
	  System.out.println(resp);

	/*  JsonPath js2= Reusablemethods.rawToJson(resp);
      String id2=	js2.get("Msg");
	  System.out.println(id2);*/




	}

}