import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTest {

	public static void main(String[] args) throws IOException {
		
		WebTest t = new WebTest();
		WebElement elm;
		List<WebElement> elms;
		
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://sacramento.craigslist.org");
		driver.manage().window().maximize();
		
		
		// Nearby Cities
		
		elm = driver.findElement(By.className("acitem"));
		elms = elm.findElements(By.tagName("a"));
		for(WebElement city : elms) {
			System.out.println(city.getText());
		}
		
		// Click On Materials Link
		
		driver.findElement(By.xpath("//a[@class='maa']")).click();
		
		elm = driver.findElement(By.xpath("//div[@class='search-view js-only']"));
		elm.click();
		elm.findElement(By.xpath("//button[@id='picview']")).click();
		
		// Navigate Page
		
		Scanner sc = new Scanner(System.in);
		int totalItems = Integer.parseInt(driver.findElement(By.xpath("//span[@class='totalcount']")).getText());
		
		System.out.println("Enter Page Number: ");
		int userPage = sc.nextInt() - 1;
		sc.close();
		
		while(userPage > 0 && totalItems > 0) {
			driver.findElement(By.xpath("//a[@class='button next']")).click();
			userPage -= 1;
			totalItems -= 120;
		}
		
		// Print Listings 
		
		elms = driver.findElements(By.xpath("//a[@class='result-image gallery empty']"));
		if(elms.isEmpty()) {
			System.out.println("No Listings Without a Picture.");
		}else {
			t.takeScreenshot(elms);
		}
		
		// Search For Face Masks
		
		elm = driver.findElement(By.xpath("//input[@id='query']"));
		elm.sendKeys("face masks");
		elm.submit();
		
		elms = driver.findElements(By.xpath("//a[@class='result-image gallery']"));
		if(elms.isEmpty()) {
			System.out.println("No Listings With a Picture.");
		}else {
			t.takeScreenshot(elms);
		}
		
		driver.close();

	}
	
	// Screenshot of Listings
	private void takeScreenshot(List<WebElement> listings) throws IOException {
		
		WebDriver driverTmp = new ChromeDriver();
		String scrTitle;
		
		for(WebElement listing : listings) {
			driverTmp.get(listing.getAttribute("href"));
			scrTitle = listing.findElement(By.xpath("./..")).getAttribute("data-pid");
			File scrFile = ((TakesScreenshot) driverTmp).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.home") + "\\Desktop\\" + scrTitle +".png"));	
			
		}

		driverTmp.close();
	}

}


















