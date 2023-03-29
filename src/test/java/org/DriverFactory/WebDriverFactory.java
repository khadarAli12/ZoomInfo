package org.DriverFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactory {
	public WebDriver driver;
	public synchronized WebDriver driverInitialization() throws IOException {
       FileInputStream sourceFile = new FileInputStream("./src/test/java/org/Utils/config.properties");
		Properties property = new Properties();
		property.load(sourceFile);
		Object object = property.get("browser");
		String browser = (String) object;
		String browserName = browser.toLowerCase();
		
		if(browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}else if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browserName.equals("fireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else {
			System.out.println("The entered browser dosent match, invoking chrome browser by default");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		return driver;
	}
}
