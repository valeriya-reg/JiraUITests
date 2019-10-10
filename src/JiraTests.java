import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JiraTests {

  WebDriver driver;

  @BeforeTest
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "chromedriver77");
    // Create a new instance of the Chrome driver
    this.driver = new ChromeDriver();
  }

  By userNameInput = By.id("login-form-username");
  By passwordInput = By.id("login-form-password");
  By loginButton = By.id("login-form-submit");
  By dashboard = By.id("dashboard");

  @Test
  public void loginToJiraTest() {
    this.driver.get("https://jira.hillel.it/login.jsp");

    driver.findElement(userNameInput).sendKeys("Valeria_Regrut");
    driver.findElement(passwordInput).sendKeys("Valeria_Regrut");
    driver.findElement(loginButton).click();

    Assert.assertTrue(driver.findElement(dashboard).isDisplayed());
  }

  @AfterTest
  public void tearDown() {
    // Close the driver
    this.driver.quit();
  }

//  void clickOnElementWithRetry(By locator) {
//    try {
//      WebElement element = this.driver.findElement(locator);
//      element.click();
//    } catch (NoSuchElementException | StaleElementReferenceException exception) {
//      WebElement element = this.driver.findElement(locator);
//      element.click();
//    }
//  }
}
