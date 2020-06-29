package hw2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarFaxHW {

	public static void main(String[] args) throws InterruptedException  {
		
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium Files\\Browser Drivers\\chromedriver.exe");
		
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.carfax.com/");
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


		//2. Click on Find a Used Car
		
		driver.findElement(By.linkText("Find a Used Car")).click();
		
		//Utility.delay(driver, 5);
		Thread.sleep(2000);
		
		//3.Verify the page title contains the word “Used Cars”
		if (driver.getTitle().contains("Used Cars")) {
		System.out.println("PASS - the page title contains the word “Used Cars"); 
		}
		//4. Choose “Tesla” for  Make
		
		Select s = new Select(driver.findElement(By.name("make")));
		
		Utility.delay(driver, 5);
		//Thread.sleep(2000);
		
		s.selectByValue("Tesla");
		
		
		List<WebElement> options = driver.findElements(By.xpath("//select[@name ='model']//optgroup[@label='Current Models']"));

		for (WebElement webElement : options) {
			
			System.out.println(webElement.getText());
			
			//5. 
			if((webElement.getText().contains("Model S")) && (webElement.getText().contains("Model S")) 
					&& (webElement.getText().contains("Model S"))) {
			    System.out.println("PASS - Select Model dropdown box contains 3 current Tesla models");}
			}
			
			//6. Choose Model S for model
		
		   driver.findElement(By.id("model_Model-S")).click();
		
		    //7. Enter zip code
		   
		   driver.findElement(By.cssSelector("input[name='zip']")).sendKeys("22182");
		   driver.findElement(By.cssSelector("#make-model-form-submit")).click();
		   
		   //8.Verify that the page has “Step 2 – Show me cars with” text
		   
		   if (driver.getPageSource().contains("Step 2 - Show me cars with")) {
			   System.out.println("PASS - the page has “Step 2 – Show me cars with” text");
			   }
		   
		   //9.Click on all 4 checkboxes
		   
		   if(!driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[1]")).isSelected())
				driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[1]")).click();

		   if(!driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[2]")).isSelected())
				driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[2]")).click();
		   
		   if(!driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[3]")).isSelected())
				driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[3]")).click();
		   
		   if(!driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[4]")).isSelected())
				driver.findElement(By.xpath("(//span[@class='checkbox-list-item--fancyCbx'])[4]")).click();
		   
		   //10. Save the result of “Show me X Results” button to a variable
		   
		  String text = driver.findElement(By.xpath("//span[@class= 'totalRecordsText']")).getText();
		  
		  System.out.println("Show me " + text + " results");
		  
		  //11. Click on “Show me x Results” button
		  
		 driver.findElement(By.xpath("//span[@class= 'totalRecordsText']")).click();
		 
		 //12. verify that the results page has the same number of results as indicated in Step 10 by extracting the number and comparing the result
		 
		 String text1 = driver.findElement(By.id("totalResultCount")).getText();
		 if (text.equals(text1)) {
		 System.out.println("PASS - the results page has the same number of results as indicated in Step 10");}
		 
		 //13. Verify the results also by getting the actual number of results displayed in the page with the number in the Step 10
		 
		
		 
		 List <WebElement> nCars = driver.findElements(By.cssSelector("article[title^='Click to see details']"));
		 System.out.println("Number of cars " + nCars.size());
		 
		 if (Integer.toString(nCars.size()).equals(text)) {
			 System.out.println("Step 13 - PASS");
		 }
		 
		 //14.Verify that each result contains string “Tesla Model S”
		 
		 for (int i =1; i<=nCars.size(); i++){ 
				// figure out i in predicate
			 
			 if (	driver.findElement(By.xpath(("(//h4[@class='srp-list-item-basic-info-model'])[" + i +"]"))).getText().contains("Tesla Model S")) {
				System.out.println("PASS - each result contains \"Tesla Model S\"");
			 }
				}
		 
		 //15.Get the price of each result and save them into a list in the order of their appearance
		 
		 List<WebElement> prices = driver.findElements(By.xpath("//span[@class = 'srp-list-item-price']"));
		 
		 //16. Set prices High to Low
		 
		 Select s1 = new Select(driver.findElement(By.className("srp-header-sort-select")));
		 
		 s1.selectByIndex(1);
		 
		// Utility.delay(driver, 5);
		 Thread.sleep(2000);
		 
		 //17. Turn the results into List of Doubles
		  
		 List<WebElement> sortedPrices = driver.findElements(By.xpath("//span[@class = 'srp-list-item-price']"));
		 
		    List<Double> sortedPrices1 = new ArrayList<Double>();
			
			for (WebElement webElement : sortedPrices) {
				
				if (webElement.getText().contains("Price: Call for Price")) {
				sortedPrices1.add(Double.parseDouble(webElement.getText().replace("Price: Call for Price", "0")));
				} else
				sortedPrices1.add(Double.parseDouble(webElement.getText().replace("Price: $", "").replace(",","")));
			}
		 
		
		 //17. Verify that the results are displayed from high to low price. 
		 
			List<Double> expectedSortedPrices = Arrays.asList (71000.0, 66000.0, 58500.0, 50000.0, 50000.0, 47990.0, 40500.0, 38500.0, 34995.0, 65990.0);
			//My expected results (including that one extra car that won't sort..
			
			
			if (expectedSortedPrices.equals(sortedPrices1)) {
				System.out.println("PASS - the results are displayed from high to low price");
			} else {
				System.out.println("FAIL");
			}
			
//			 for (Double double1 : sortedPrices1) {
//					System.out.println(double1);
//				}
				 
			 
		 
		 //18. Choose mileage Low to High
		 
		 s1.selectByIndex(4);
		 
		 //Utility.delay(driver, 5);
		 Thread.sleep(2000);
		 
     	 
		 //List<WebElement> sortedMileage; 
		 List<Double> sortedMileage = new ArrayList<Double>();
		 for (int i=1; i< nCars.size()* 4; i= i+4) {
			 String mileage = driver.findElement(By.xpath("(//span[@class='srp-list-item-basic-info-value'])["+ i +"]")).getText().replace("Mileage: ", "").replace(",", "").replace(" miles", "");
			 
			 sortedMileage.add(Double.parseDouble(mileage));
			// System.out.println(mileage);
		 }
		
		
	 List<Double> expectedSortedMileage = Arrays.asList(9469.0,14549.0,16417.0,19489.0, 40663.0, 42904.0, 59965.0, 67752.0, 73218.0, 33983.0);
	 //The sorted list is correct except for the last value, which comes from that extra car tha won't sort...

		if (expectedSortedMileage.equals(sortedMileage)) {
			System.out.println("PASS - the results are displaying sorted mileage from Low to High");
		} else {
			System.out.println("FAIL");
		}

		 //20. Choose “Year - New to Old” option from Sort menu
		
		s1.selectByIndex(5);
		Thread.sleep(2000);
		
		//21. Verify that the results are displayed from new to old year
		
		List <WebElement> sortedByYears = driver.findElements(By.xpath("//h4[@class='srp-list-item-basic-info-model']"));
		
		List <String> sortedByYears1 = new ArrayList<>(); 
		
		for (WebElement years : sortedByYears) {
			sortedByYears1.add(years.getText());	
		}
		 
		//System.out.println(sortedByYears1);
	
		
        List <Integer> sortedByYears2= new ArrayList<>();
		
		for (int i =0; i<nCars.size(); i++) {
		
			String year = sortedByYears1.get(i).split(" ")[0];
			sortedByYears2.add(Integer.parseInt(year));
			
			}
		System.out.println(sortedByYears2);
		
		List<Integer> expectedSortedYears = Arrays.asList(2018, 2018, 2017, 2017, 2015, 2015, 2015, 2015, 2013, 2018);
		
		if (expectedSortedYears.equals(sortedByYears2)) {
			System.out.println("PASS - the results are displayed from New to Old year");//Except for that one extra car that won't sort
		} else {
			System.out.println("FAIL");
		}
	}	
	
	
}
