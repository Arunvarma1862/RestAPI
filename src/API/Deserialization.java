package API;


import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import POJO.api;
import POJO.getCourse;
import POJO.webAutomation;

public class Deserialization {

	public static void main(String[] args) throws Exception {

		String [] coursetitles= {"Selenium Webdriver Java","Cypress","Protractor"};

		/*WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com
		/auth/userinfo.email&auth_url=Authorization server url	https://accounts.google.com
		 * o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type
		 * =code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfd");	
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830",Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password",Keys.ENTER);
		String url=   driver.getCurrentUrl();*/
		String url= "https://rahulshettyacademy.com/getCourse.php?state=verifyfd&code=4%2F0AdQt8qhBTt4nyTRhyUUudTDQDvII8H0rSJC70Lt6vWrll5FM_28PR6bHzm0lqk-i_aOjcw&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partialcode=   url.split("code=")[1];
		String code=   partialcode.split("&scope")[0];
		System.out.println(code);



		String accesstokenresponse=	given().urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js= new JsonPath(accesstokenresponse);
		String accessstoken=	js.get("access_token");




		getCourse gc=given().queryParam("access_token", accessstoken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.as(getCourse.class);

		System.out.println(gc.getInstructor());
		System.out.println(	gc.getLinkedIn());
		System.out.println(	gc.getExpertise());
		System.out.println(	gc.getServices());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

              // get price of couresName
		
		List<api> apicourse =    gc.getCourses().getApi();
		
		
		for (int i = 0; i <apicourse.size(); i++)
		{
			if(apicourse.get(i).getCourseTitle().equalsIgnoreCase("SoupUI Webservices testing")) {

				System.out.println( apicourse.get(i).getPrice());
			}

		}

		 // get courseNames of webAutomation
		
		
		ArrayList<String> a = new ArrayList<String>();

		List<webAutomation>   w =   gc.getCourses().getWebAutomation();
	
		
		for (int j = 0; j <w.size(); j++)
        {
		System.out.println(	a.add(w.get(j).getCourseTitle()));

		}
		
	   List<String> expectedList=	Arrays.asList(coursetitles);
	   Assert.assertTrue(a.equals(expectedList));



	}

}
