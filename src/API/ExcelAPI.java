package API;

import org.testng.annotations.Test;

import Files.Reusablemethods;
import Files.TestExcel;
import Files.payLoad;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ExcelAPI {
    @Test
	public void Excel() throws IOException, Exception {
		
   	TestExcel ts= new TestExcel();
    //	TestExcel2 ts= new TestExcel2();
    	ArrayList<String> data=ts.getData("RestAddBook","Sheet2");
    	data.get(1);
    	HashMap<String , Object> map= new HashMap<>();
    	
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));
		
		/*HashMap<String , Object> map2= new HashMap<>();
		map2.put("lat", "18.25");
		map2.put("lag", "19.25 ");
		map.put("location", map2);*/
	
		
		RestAssured.baseURI="http://216.10.245.166";

		String response =given().log().all().header("Content-Type","application/json")
				.body(map)
				.when().post("Library/Addbook.php")
				.then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js= Reusablemethods.rawToJson(response);
		String id= js.get("ID");
		System.out.println(id);
		

		
	}
}
