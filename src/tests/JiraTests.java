package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class JiraTests extends BaseTest{

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
        System.out.println("Test 2");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.loginToJira("Valeria_Regrut", "Valeria_Regrut");

        Assert.assertTrue(loginPage.isDashboardDisplayed());
    }

    @Test
    public void createIssueTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 20);

        System.out.println("Test 1");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.loginToJira("Valeria_Regrut", "Valeria_Regrut");

        driver.findElement(createIssueButton).click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(projectInput));

//        driver.findElement(projectInput).sendKeys("QAAUTO-8", Keys.RETURN);
//        Thread.sleep(3000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(issueTypeInput));

//        driver.findElement(issueTypeInput).clear();
//        driver.findElement(issueTypeInput).sendKeys("Task", Keys.RETURN);
//        Thread.sleep(3000);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInput));

        driver.findElement(summaryInput).sendKeys("Auto Test");
        driver.findElement(dataModeSource).click();
        driver.findElement(descriptionInput).sendKeys("Auto Test");

        driver.findElement(createButton).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(issueSuccessfullyCreated));

        Assert.assertTrue(driver.findElement(issueSuccessfullyCreated).isDisplayed());
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
