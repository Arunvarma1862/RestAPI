package API;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.filter.session.SessionFilter;

public class JiraTest1 {
	public static void main(String[] args) {

		// login scenario

		RestAssured.baseURI="https://loaalhost:8080";
		SessionFilter session = new SessionFilter();

		String response=	given().log().all().header("Content-Type","application/json")
				.body("{ \"username\": \"arunbabu120894@gmail.com\", \"password\": \"Arunjira1862\" }'")
				.filter(session).when().post("/rest/auth/1/session")
				.then().log().all().extract().response().asString();


		// Add comment

		given().log().all().pathParam("key", "10101").header("Content-Type","application/json")
		.body("{\r\n"
				+ "    \"body\": \"This is first comment\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session)
		.when().post("/rest/api/2/issue/{Key}/comment")
		.then().log().all().assertThat().statusCode(201);


		// Add Attachment

		given().log().all().header("X-Atlassian-Token", "no-check").filter(session)
		.pathParam("key", "10101").header("Content-Type","multipart(form-data")
		.multiPart("file",new File("jira.txt")).when().post("/rest/api/2/issue/{Key}/attachments")
		.then().log().all().assertThat().statusCode(200);

		// GetIssue 


		String issueDetails=  given().filter(session).pathParam("key", "10101").queryParam("fields","comment")
				.log().all().when().get("/rest/api/2/issue/{Key}")
				.then().log().all().extract().response().asString();


		System.out.println(issueDetails);




	}
}
