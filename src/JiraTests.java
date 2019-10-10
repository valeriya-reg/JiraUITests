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

  // Login
  By userNameInput = By.id("login-form-username");
  By passwordInput = By.id("login-form-password");
  By loginButton = By.id("login-form-submit");
  By dashboard = By.id("dashboard");

  // Create Issue
  By createIssueButton = By.id("create_link");
  By projectInput = By.id("project-field");
  By issueTypeInput = By.id("issuetype-field");
  By summaryInput = By.id("summary");
  By dataModeSource = By.xpath("//li[@data-mode='source']");
  By descriptionInput = By.xpath("//textarea[@name='description']");
  By createButton = By.id("create-issue-submit");
  By issueSuccessfullyCreated = By.xpath("//div[@class='aui-message closeable aui-message-success aui-will-close']");

  @Test
  public void loginToJiraTest() {
    this.driver.get("https://jira.hillel.it/login.jsp");

    driver.findElement(userNameInput).sendKeys("Valeria_Regrut");
    driver.findElement(passwordInput).sendKeys("Valeria_Regrut");
    driver.findElement(loginButton).click();

    Assert.assertTrue(driver.findElement(dashboard).isDisplayed());
  }

  @Test
  public void createIssueTest() throws InterruptedException {
      this.driver.get("https://jira.hillel.it/login.jsp");

      driver.findElement(userNameInput).sendKeys("Valeria_Regrut");
      driver.findElement(passwordInput).sendKeys("Valeria_Regrut");
      driver.findElement(loginButton).click();

      driver.findElement(createIssueButton).click();
      Thread.sleep(3000);

      driver.findElement(projectInput).sendKeys("QAAUTO-8", Keys.RETURN);
      Thread.sleep(2000);
      driver.findElement(issueTypeInput).sendKeys("Task", Keys.RETURN);
      Thread.sleep(2000);
      driver.findElement(summaryInput).sendKeys("Auto Test");
      driver.findElement(dataModeSource).click();
      driver.findElement(descriptionInput).sendKeys("Auto Test");

      driver.findElement(createButton).click();
      Thread.sleep(2000);

      Assert.assertTrue(driver.findElement(issueSuccessfullyCreated).isDisplayed());
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
