package API;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel2 {

	public ArrayList<String> getData(String testcaseName, String sheets) throws IOException {
		
		File file = new File("C:\\eclipse project\\RestAssuresAPI\\ExcelAPI\\Test1.xlsx");
		FileInputStream fis= new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheet=workbook.getNumberOfSheets();
		System.out.println(sheet);
		
		ArrayList<String> a= new ArrayList<>();
		
		for (int i = 0; i < sheet; i++)
		{
			
			if(workbook.getSheetName(i).equalsIgnoreCase(sheets)) 
			{
			XSSFSheet shee =	workbook.getSheetAt(i);
			
		      Iterator<Row> rows=	shee.iterator();
		      Row firstrow= rows.next();
		       Iterator<Cell> cells=  firstrow.cellIterator();
		       
		       int k=0;
    		   int column=0;
    		   
    		   
		         while(cells.hasNext()) 
		         {
		    	   Cell ce= cells.next();
		    	   if(ce.getStringCellValue().equalsIgnoreCase("testcases")){
		    		  
		    		   column=k;
		    		 	   
		    	   }
		    	   k++;
		    	   
		       }
		         System.out.println(column);
		         
		         while(rows.hasNext()) {
		        	 Row re=rows.next();
		        	 if(re.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
		        		 
		        		Iterator<Cell> c= re.cellIterator();
		        		while(c.hasNext()) {
		        		Cell value=	c.next();
		        		if(value.getCellType()==CellType.STRING) {
		        			a.add(value.getStringCellValue());
		        			
		        		}
		        		else 
		        		{
		        			a.add(NumberToTextConverter.toText(value.getNumericCellValue()));
		        		}
		        		}
		        		 
		        	 }
		         }
			}
			
		}
	
		workbook.close();
		return a;
	}
	
}
