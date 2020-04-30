package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new HtmlUnitDriver();
        driver.get("http://localhost:4567");
        
        // tulostetaan sivu konsoliin
        //System.out.println(driver.getPageSource());
        
        /*WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("gjsgjeisgj");
        element = driver.findElement(By.name("login"));
        element.submit();
        */
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("s1s11s1s1s1s1s1sd1s1s1s1s232");
        element = driver.findElement(By.name("password"));
        element.sendKeys("elliv");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("elliv");
        element=driver.findElement(By.name("signup"));
        element.submit();
        System.out.println(driver.getPageSource());
        element=driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        System.out.println(driver.getPageSource());
        element=driver.findElement(By.linkText("logout"));
        element.click();
        

        

        // tulostetaan sivu konsoliin
        System.out.println(driver.getPageSource());
        
        // ...

        driver.quit();
    }
    
}
