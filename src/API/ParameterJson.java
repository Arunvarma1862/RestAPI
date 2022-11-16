package API;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Reusablemethods;
import Files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class ParameterJson {

	@Test(dataProvider = "getData")
	public void addBook(String s, String b) {

		// AddBook

		RestAssured.baseURI="http://216.10.245.166";
		String response   =	given().log().all().header("Content-Type","application/json")
				.body(payLoad.addBook(s,b))
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js= Reusablemethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);


		//DeleteBook

		/*RestAssured.baseURI="http://216.10.245.166";
		String resp = given().log().all().header("Content-Type","application/json")
				.body("ID")
				.when().delete("/Library/DeleteBook.php")
				.then().log().all().assertThat().extract().response().asString();

		JsonPath js2= Reusablemethods.rawToJson(response);
		String id2=	js2.get("ID");
		System.out.println(id2);*/




	}
	@DataProvider
	public Object[] []getData() {

		Object[][] data= new Object[][]{{"ajsbcja","45625"},{"hsahbdj","458775"},{"svchbk","455765"}

		};
		return data;
	}
}