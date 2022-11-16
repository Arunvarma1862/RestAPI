package API;

import java.util.List;

import Files.payLoad;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
   public static void main(String[] args) {
	   
	 JsonPath js= new JsonPath(payLoad.CoursePrice());
	 
	 
	                        // 1. Print no of courses returned API
	 
	 
	int count= js.getInt("courses.size()");
	System.out.println(count);
	
	
	
	                         // 2.Print Purchase Amount
	
	
	int purchase=js.getInt("dashboard.purchaseAmount");
	System.out.println(purchase);
	
	
	                         //3.Print title of the first course
	
	
     String fcourse =	js.get("courses[1].title");
     System.out.println(fcourse);
	
	
	
	                          // 4. Print All course titles and their respective Prices
     
     
     
     List<String> allcourse= js.getList("courses.title");
    for (String str : allcourse) {
		System.out.println(str);
	}
    
    
    for(int i=0;i<count;i++) {
    	
       String courses=	js.get("courses["+i+"].title");
       System.out.print(courses+" course price is : ");
       System.out.println( js.get("courses["+i+"].price").toString());
    
 
    }
	
                          //	5. Print no of copies sold by RPA Course
    
    
    
  for(int i=0;i<count;i++) {

	 String course= js.get("courses["+i+"].title");
	 
	 if(course.equalsIgnoreCase("RPA")) {
		int co= js.get("courses["+i+"].copies");
		System.out.println("copies of RPA :"+co);
		break;
	 }
  }
      

                      
	
	
}
}
