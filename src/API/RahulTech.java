package API;

public class RahulTech extends rajTech {


	RahulTech(String name) {
		super();
		System.out.println(name);
		
	}
	@Override
	public void webdeveloper() {
		
		System.out.println("completed dudee");
	}
	public static void main(String[] args) {
		
		RahulTech r = new RahulTech("jsd");
		
		r.webDesign();
		r.webdeveloper();
		r.web();
		rajTech.webbb();
		
		
	}
	@Override
	public void web() {

System.out.println("hii");
		
	}

}
