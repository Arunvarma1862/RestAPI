package API;


import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class OauthTest {

	public static void main(String[] args) throws Exception {

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
		
		String url= "https://rahulshettyacademy.com/getCourse.php?state=verifyfd&code=4%2F0AdQt8qhakgdf8vIMXppRppAwndoi_6P7V7I1_L0U7ex-jyXueNABTHx9NhkSG1lUdQ9hmg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialcode = url.split("code=")[1];
		String code = partialcode.split("&scope")[0];
		System.out.println(code);



		String accesstokenresponse=	given().urlEncodingEnabled(false)
				.queryParam("code",code)
				.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
				.queryParam("scope","https://www.googleapis.com/auth/userinfo.email")
				.queryParam("grant_type","authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js = new JsonPath(accesstokenresponse);
		String accessstoken=js.get("access_token");
		System.out.println(accessstoken);


		String response=given().queryParam("access_token",accessstoken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php")
				.asString();


		System.out.println(response);

	}

}
