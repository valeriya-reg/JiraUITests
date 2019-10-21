package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    WebDriver driver = null;

    private By userNameInput = By.id("login-form-username");
    private By passwordInput = By.id("login-form-password");
    private By loginButton = By.id("login-form-submit");
    private By dashboard = By.id("dashboard");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigate() {
        driver.get(baseUrl + "/login.jsp");
    }

    public void enterUserName(String name) {
        driver.findElement(userNameInput).sendKeys(name);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void loginToJira(String name, String password) {
        enterUserName(name);
        enterPassword(password);
        clickLogin();
    }

    public boolean isDashboardDisplayed() {
        return driver.findElement(dashboard).isDisplayed();
    }
}
