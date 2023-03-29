package TestScriptsPackage;

import java.io.IOException;


import org.DriverFactory.WebDriverFactory;
import org.Utils.Utils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;

public class TestCases {
	WebDriver driver;
     Utils utils = new Utils(driver);
	@BeforeMethod
	public void InitialSteps() throws IOException {
		WebDriverFactory factory = new WebDriverFactory();   
		driver = factory.driverInitialization();
		String URL = utils.getPropertyValue("./src/test/java/org/Utils/config.properties","url");
		driver.get(URL);
	}

	@Test
	public void verifychatbotVisibleOnHomeScreen() {
		HomePage homePage = new HomePage(driver);
		homePage.verifyChatBotVisible(driver);
	}

	@Test
	public void verifyChatBotWelcomeMessage() {
		HomePage homePage = new HomePage(driver);
		homePage.verifyWelcomeMessageOnChatBot(driver);
	}

}
