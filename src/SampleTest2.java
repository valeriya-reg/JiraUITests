import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SampleTest2 {

  WebDriver driver;

  @BeforeTest
  public void setUp() {
    // Fix for - The path to the driver executable must be set by the webdriver.chrome.driver system property
    System.setProperty("webdriver.chrome.driver", "/Users/apiliuk/Downloads/polymorphismSample/chromedriver");
    // Create a new instance of the Firefox driver
    this.driver = new ChromeDriver();
  }

  By alreadyHaveAccountBtn = By.xpath("//div[contains(text(), 'У МЕНЯ УЖЕ ЕСТЬ АККАУНТ')]");
  By userNameInput = By.xpath("//input[@name='email']");
  By passwordInput = By.xpath("//input[@name='password']");
  By enterButton = By.xpath("//span[text() = 'ВОЙТИ']");
  By wrongPasswordLabel = By.xpath("//*[text() = 'Неправильный email или пароль']");
  String userName = "a.a.piluck@gmail.com";
  String password = "wrong password";

  @Test
  public void firstTest() throws InterruptedException {
    this.driver.get("https://lingualeo.com/ru");
    this.driver.findElement(alreadyHaveAccountBtn).click();
    this.driver.findElement(userNameInput).sendKeys(userName);
    this.driver.findElement(passwordInput).sendKeys(password);

    clickOnElementWithRetry(enterButton);

    Thread.sleep(2000); // ждем пока появится сообщение об ошибке
    assertTrue(this.driver.findElement(wrongPasswordLabel).isDisplayed());
  }

  @AfterTest
  public void tearDown() {
    // Close the driver
    this.driver.quit();
  }

  void clickOnElementWithRetry(By locator) {
    try {
      WebElement element = this.driver.findElement(locator);
      element.click();
    } catch (NoSuchElementException | StaleElementReferenceException exception) {
      // NoSuchElementException - элемент не был найден
      // потому что его нет на странице или опечатка в локаторе.
      WebElement element = this.driver.findElement(locator);
      element.click();
    }
  }
}
