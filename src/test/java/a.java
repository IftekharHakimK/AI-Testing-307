// I commented out all thread-sleeps, it seemed to work well

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class a {
    WebDriver driver = null;

    @Given("Browser is opened and maximized")
    public void browser_is_opened_and_maximized() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\USER\\Desktop\\3-1\\307\\Assignment\\AI-Testing-307\\Additions\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @When("Go to goodreads login page")
    public void go_to_goodreads_login_page() throws InterruptedException {
        driver.navigate().to("https://www.goodreads.com/user/sign_in");
        //Thread.sleep(3000);
    }
    @When("Correct credentials placed")
    public void correct_credentials_placed() throws InterruptedException {
        driver.findElement(By.id("user_email")).sendKeys("testing_1@mail2world.com");
        driver.findElement(By.id("user_password")).sendKeys("12345678");
        driver.findElement(By.name("next")).click();
        //Thread.sleep(3000);
    }
    @When("Homepage visible")
    public void homapage_visible() throws InterruptedException {
        System.out.println(driver.findElement(By.name("q")).isDisplayed());
        assertTrue("Homepage/Searchbox not visible after valid login",driver.findElement(By.name("q")).isDisplayed());
    }
    @Then("End")
    public void end()
    {
        driver.close();
    }

    @When("Incorrect credentials placed")
    public void incorrect_credentials_placed() {
        driver.findElement(By.id("user_email")).sendKeys("testing_1@mail2world.com");
        driver.findElement(By.id("user_password")).sendKeys("123456789");
        driver.findElement(By.name("next")).click();
    }
    @When("Error message visible and browser still at login page")
    public void error_message_visible_and_browser_still_at_login_page() {
        String message = driver.findElement(By.id("emailForm")).getText();
        assertTrue("Invalid login not detected ",message.contains("Sorry, that email or password isn't right"));
        assertTrue("Invalid login not detected ",driver.getCurrentUrl().equalsIgnoreCase("https://www.goodreads.com/user/sign_in"));
    }
}
