package API;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	
		
		
		   //	6. Verify if Sum of all Course prices matches with Purchase Amount
	
	
	
	@Test
	public void sumOfCourse() {
		
		int totalPrice=0;
	
		JsonPath js= new JsonPath(payLoad.CoursePrice());
	
	    int count=js.get("courses.size()");
	    for(int i=0;i<count;i++) {
	    	
	  String  courseName=  js.get("courses["+i+"].title");
	  int price  =         js.get("courses["+i+"].price");
	  int copies =         js.get("courses["+i+"].copies");
	  
	  
	     System.out.print(courseName+":");
	     int EachCoursesAmount= price * copies;
	     System.out.println(EachCoursesAmount);
	     totalPrice= totalPrice + EachCoursesAmount;
	    
	     }
	    
		System.out.println(totalPrice);
        int purchaseAmount=	js.get("dashboard.purchaseAmount");
        Assert.assertEquals(totalPrice, purchaseAmount);
	}
		
	
		
	
	

}
