// I commented out all thread-sleeps, it seemed to work well

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {
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

    @When("Go to goodreads home page")
    public void go_to_goodreads_home_page() throws InterruptedException {
        driver.navigate().to("https://www.goodreads.com/");
        //Thread.sleep(3000);
    }

    @When("Place {string} in searchbox")
    public void place_in_searchbox(String string) throws InterruptedException {

        try {
            driver.findElement(By.name("query")).sendKeys(string + "\r\n");
        } catch (Exception e) {
            driver.findElement(By.name("q")).sendKeys(string + "\r\n");
        }

        //Thread.sleep(3000);
        try {
            if (driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/button")).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[3]/div/div/div[1]/button")).click();
            }
        } catch (Exception e) {

        }
        // driver.findElement(By.name("qid")).click();
    }

    @When("{string} exactly exists in table")
    public void keyword_exactly_exists_in_table(String string) throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/div[2]/table/tbody")) ;

        int c = 0;
        for (int i = 1; i <= 5; ++i) {
            try {
                String text = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/div[2]/table/tbody/tr["
                        + i + "]/td[2]/a/span")).getText();
                if (text.equalsIgnoreCase(string))
                    ++c;
            } catch (Exception e) {
                break;
            }
        }

        assertTrue("Keyword does not appear enough times.", c >= 1);
    }

    @When("{string} exists in table")
    public void keyword_exists_in_table(String string) throws InterruptedException {
        //driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/div[2]/table/tbody")) ;
        String[] words = string.toUpperCase().split(" ");

        int c = 0;
        for (int i = 1; i <= 5; ++i) {
            String text = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div[1]/div[2]/div[2]/table/tbody/tr["
                    + i + "]/td[2]/a/span")).getText().toUpperCase();
            for (String word : words) {
                System.out.println(word);
                if (text.contains(word)) {
                    ++c;
                    break;
                }
            }
        }

        assertTrue("Keyword does not appear enough times.", c >= 4);
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
        assertTrue("Homepage/Searchbox not visible after valid login", driver.findElement(By.name("q")).isDisplayed());
    }

    @Then("End")
    public void end() {
        driver.close();
        driver.quit();
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
        assertTrue("Invalid login not detected ", message.contains("Sorry, that email or password isn't right"));
        assertTrue("Invalid login not detected ", driver.getCurrentUrl().equalsIgnoreCase("https://www.goodreads.com/user/sign_in"));
    }

    @When("Log out")
    public void logout() throws InterruptedException {
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div/header/div[1]/div/div[3]/ul/li[5]/div/a")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/div[2]/div/header/div[1]/div/div[3]/ul/li[5]/div/div/div/ul/li[13]/a")).click();
        Thread.sleep(3000);

        assertTrue("Logout unsuccessful", driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/p")).getText().contains("signed out"));
    }

    @When("Upload invalid {string} as Profile Picture")
    public void uploadInvalidProfilePicture(String string) throws InterruptedException {
        driver.navigate().to("https://www.goodreads.com/user/edit");
        Thread.sleep(1500);

        try {
            if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/a[2]")).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/a[2]")).click();
                driver.switchTo().alert().accept();
            }
        }catch (Exception e)
        {
            ;
        }

        WebElement chooseFile = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/input[1]"));
        chooseFile.sendKeys("C:\\Users\\USER\\Desktop\\3-1\\307\\Assignment\\AI-Testing-307\\" + string);
        Thread.sleep(1500);

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/input[2]")).click();
        Thread.sleep(1500);

        String errorMessage = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/h2")).getText();

        System.out.println(errorMessage);
        assertTrue("Error message not shown for invalid file", errorMessage.contains("error prohibited this profile from being saved"));

        Thread.sleep(1000);
    }

    @When("Upload {string} as Profile Picture")
    public void uploadProfilePicture(String string) throws InterruptedException {
        driver.navigate().to("https://www.goodreads.com/user/edit");
        Thread.sleep(1500);

        try {
            if (driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/a[2]")).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/a[2]")).click();
                driver.switchTo().alert().accept();
            }
        }catch (Exception e)
        {
            ;
        }

        WebElement chooseFile = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/input[1]"));
        chooseFile.sendKeys("C:\\Users\\USER\\Desktop\\3-1\\307\\Assignment\\AI-Testing-307\\" + string);
        Thread.sleep(1500);

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[1]/div[1]/div[3]/div[1]/form/div[2]/p[1]/input[2]")).click();
        Thread.sleep(1500);

        assertEquals("Not updated", "https://www.goodreads.com/user/update", driver.getCurrentUrl());
    }

}
