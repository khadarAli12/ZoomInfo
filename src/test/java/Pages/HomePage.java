package Pages;

import org.Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	Utils utils = new Utils(driver);
	//////////Web Elements//////////////////////
	@FindBy(how=How.CSS, using = "div[id='insent-popup-message-detail']")
	private WebElement chatBot;

	@FindBy(how=How.XPATH, using="//a[normalize-space(text())='Accept']")
	private WebElement btnAcceptCookies;
	
	@FindBy(how=How.CSS, using = "div[id='insent-launcher-icon']")
	private WebElement iconChatBot;
	
	@FindBy(how=How.XPATH, using="//div[contains(text(),'further!')]")
	private WebElement txtChatBotWelcomeMessage;
	
	//////////////Reusables////////////////////

	/**
	 * verify Chat Bot Visible
	 * @param driver
	 * @author Kadhar Ali
	 */
	public synchronized void verifyChatBotVisible(WebDriver driver) {
		utils.waitTillTheElementIsVisible(driver, By.xpath("//a[normalize-space(text())='Accept']"));
		utils.click(driver, btnAcceptCookies);
		utils.switchToFrame(driver, "insent-iframe");
		utils.waitTillTheElementIsVisible(driver, By.cssSelector("div[id='insent-popup-message-detail']"));
		utils.verifyTheSpecifiedWebElementIsVisible(driver, chatBot);
	    utils.switchToDefaultContent(driver);
	}
	
	/**
	 * verify Welcome Message On Chat Bot
	 * @author kadhar ali
	 * @param driver
	 */
	public synchronized void verifyWelcomeMessageOnChatBot(WebDriver driver){
		utils.switchToFrame(driver, "insent-iframe");
		utils.click(driver, iconChatBot);
		utils.waitTillTheElementIsVisible(driver,By.xpath("//div[contains(text(),'further!')]"));
        String txtWelcomeMessage = utils.getTextFromWebElement(driver, txtChatBotWelcomeMessage);
        utils.verifyTextsMatching(driver,txtWelcomeMessage,"Please enter your email address to assist you further!","Welcome message is mismatching");
	}


}
