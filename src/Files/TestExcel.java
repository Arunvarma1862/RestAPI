package Files;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {
	
	public ArrayList<String> getData(String testCaseName,String SheetName) throws Exception, IOException {

		// fileinputStream argument
		
		
		File file = new File("C:\\eclipse project\\RestAssuresAPI\\ExcelAPI\\Test1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		int sheets=workbook.getNumberOfSheets();
		System.out.println(sheets);



		ArrayList<String > a= new 	ArrayList<String >();

		for (int i = 0; i <sheets; i++) 
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(SheetName)) 
			{
				XSSFSheet sheet=	workbook.getSheetAt(i);

				//  Identify test-cases column by scanning the entire 1st row

				Iterator<Row> rows =     sheet.iterator();  // sheet is collection of rows
				Row firstrow  =    rows.next();
				Iterator<Cell> ce= firstrow.cellIterator(); // row is collection of cells
				
				int k=0;
				int column=0;

				while(ce.hasNext()) 
				{
					Cell value=  	ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("testcases")) 
					{
						//desired column
						column=k;

					}
					
					k++;
					
				}
				System.out.println(column);

				// once column is identified then scan entire test-case column to identify purchase test-case

				while(rows.hasNext()) 
				{
					Row r=    rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName))
					{
						// after you grab purchase test-case row = pull all the data of that row and feed into test
						Iterator<Cell> c=	 r.cellIterator();
						while(c.hasNext()) 
						{
						Cell cv  =	c.next();
						if(cv.getCellType()==CellType.STRING)
						{
						    a.add(cv.getStringCellValue())	;
						}
						else 
						{
						a.add(	NumberToTextConverter.toText(cv.getNumericCellValue()));
						
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
