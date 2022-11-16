package API;

import org.testng.annotations.Test;

import Files.Reusablemethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class StaticJson {

	@Test
	public void addBook() throws IOException {

		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response = given().log().all().header("Content-Type","application/json")
				.body(generateStringFromResource("D:\\RestAPI\\Static.json"))     // .body(new String(Files.readAllBytes(Paths.get("D:\\RestAPI\\Static.json"))
				.when().post("maps/api/place/add/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();


		JsonPath js1=  Reusablemethods.rawToJson(response);
		String placeid=  js1.get("place_id");
		System.out.println(placeid);



	}

	public String generateStringFromResource(String path) throws IOException {

		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
