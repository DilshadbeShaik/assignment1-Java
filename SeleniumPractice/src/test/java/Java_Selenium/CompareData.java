package Java_Selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CompareData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// To store data in map1 from excel

		FileInputStream fs = new FileInputStream("C:\\Users\\Dilsha\\Documents\\Map.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		XSSFSheet sh1 = wb.getSheetAt(0); // Read sheet inside the workbook by its name
		Map<XSSFCell, XSSFCell> map1 = new HashMap<>();
		int noOfRows = sh1.getPhysicalNumberOfRows();
		for (int i = 1; i < noOfRows - 2; i++) {
			XSSFCell row0 = sh1.getRow(i).getCell(0);
			XSSFCell row1 = sh1.getRow(i).getCell(1);
			map1.put(row0, row1);
		}

		Set<XSSFCell> keys = map1.keySet();
		for (XSSFCell key : keys) {
			System.out.println(key + " : " + map1.get(key));
		}
		wb.close();
		fs.close();

		// To Store data in map2 from selenium webdriver
		
		  WebDriverManager.chromedriver().setup(); WebDriver driver=new ChromeDriver();
		  driver.get("https://money.rediff.com/losers/bse/daily/groupall"); 
		  WebElement mytable = driver.findElement(By.xpath("//div[contains(@id,'leftcontainer')]//tbody"));
		  List<WebElement> rows_table =mytable.findElements(By.xpath("(//div[contains(@id,'leftcontainer')])//tbody//tr")); 
		  int rows_count = rows_table.size(); 
		   Map<String,String > map2=new HashMap<>();
		   for(int row = 0; row < rows_count; row++) { 
		  List<WebElement> company = rows_table.get(row).findElements(By.xpath("(//div[contains(@id,'leftcontainer')])//tbody//tr//td[1]"));
		  List<WebElement> price = rows_table.get(row).findElements(By.xpath("(//div[contains(@id,'leftcontainer')])//tbody//tr//td[4]")); 
		  String celtext1 = company.get(row).getText(); 
		  String celtext2 =price.get(row).getText();  
		  map2.put(celtext1,celtext2);
		  } 
		  Set<String> keys1 =map2.keySet(); 
		  System.out.println(3); 
		  
		  for(String key:keys1) { 
			  System.out.println( key+" : "+ map2.get(key)); 
		  }
		  
		 //compare the two hash maps data
		if (map1.equals(map2)) {
			System.out.println("Map1 and Map2 are equal");
		} else {
			System.out.println("Map1 and Map2 are not equal");
		}

	}
}
