package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
            WebElement element = driver.findElement(By.linkText("register new user")); 
            element.click();
    }
    @Given("user with username {string} with password {string} is successfully created")
    public void newUserCreated(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user")); 
        element.click();
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);      
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @Given("user with username {string} and password {string} is tried to be created")
    public void badCreationAttempt(String username, String password) {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user")); 
        element.click();
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);      
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @When("an invalid username {string} and password {string} and matching password confirmation are entered")
    public void userNameNotValid(String username, String password) {
        makeNewUser(username, password);
        
    }
    @Then("user is not created and error 'username should have at least 3 characters' is reported")
    public void userNameInvalidAndErrorMessageShown() {
        pageHasContent("username should have at least 3 characters");
    }
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }   
    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void correctUserAndPassForNewUser(String username, String password) {
        makeNewUser(username, password);
    } 
    @When("a valid username {string} and invalid password {string} and matching password confirmation are entered")
    public void passwordNotValid(String username, String password) {
        makeNewUser(username, password);
    }
    @When("a valid username {string} and valid password {string} and non-matching password confirmation are entered")
    public void confirmationDoesNotMatch(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);      
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password+"aaa");
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    @Then("user is not created and error 'password and password confirmation do not match' is reported")
    public void badConfirmation() {
        pageHasContent("password and password confirmation do not match");
    }
    @Then("user is not created and error 'password should have at least 8 characters' is reported")
    public void passWordTooShort() {
        pageHasContent("password should have at least 8 characters");
    }
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent("Ohtu Application main page");
    }    
    @Then("a new user is created")
    public void newUserIsLoggedIn() {
        pageHasContent("Welcome to Ohtu Application!");
    }    
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   
    
    @Then("system will respond {string}")
    public void systemWillRespond(String pageContent) throws Throwable {
        assertTrue(driver.getPageSource().contains(pageContent));
    }
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    private void makeNewUser(String username, String password) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);      
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
}
