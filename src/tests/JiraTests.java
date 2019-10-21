package tests;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.loginToJira("Valeria_Regrut", "Valeria_Regrut");

        click(createIssueButton, 3, 3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(projectInput));
        enterText(projectInput, "QAAUTO-8", 3, 3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(issueTypeInput));
        enterText(issueTypeInput, "Task", 3, 3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(summaryInput));
        enterText(summaryInput, "Auto Test", 3, 3);

        click(dataModeSource, 3, 3);

        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionInput));
        enterText(descriptionInput, "Auto Test", 3, 3);

        click(createButton, 3, 3);
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(issueSuccessfullyCreated));

        Assert.assertTrue(driver.findElement(issueSuccessfullyCreated).isDisplayed());
    }

    private void click(By element, int retry, int timeoutSeconds) {
        for (int i = retry; i > 0; i--) {
            try {
                System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
                driver.findElement(element).click();
                break;
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ex) {
                try {
                    System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
                    Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
                    driver.findElement(element).click();
                    break;
                } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException | InterruptedException ex2) {
                    continue;
                }
            }
        }
    }

    private void enterText(By element, String text, int retry, int timeoutSeconds) {
        for (int i = retry; i > 0; i--) {
            try {
                System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
                driver.findElement(element).sendKeys(text);
                break;
            } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException ex) {
                try {
                    System.out.println("Searching element" + element.toString() + ". Retry - " + (retry - i));
                    Thread.sleep(TimeUnit.SECONDS.toMillis(timeoutSeconds));
                    driver.findElement(element).sendKeys(text);
                    break;
                } catch (NoSuchElementException | StaleElementReferenceException | ElementNotInteractableException | InterruptedException ex2) {
                    continue;
                }
            }
        }
    }
}
