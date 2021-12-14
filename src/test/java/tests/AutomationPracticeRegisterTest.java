package tests;

import Helpers.RandomEmail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.AutomationPracticeRegister;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class AutomationPracticeRegisterTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }

    @Test
    public void checkValidRegister() {
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);

        String email = RandomEmail.generateRandomEmail();
        register.fillInRegister(email);
        register.submitRegister();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));
        assertThat(driver.getCurrentUrl(), containsString("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        driver.quit();
    }

    @Test
    public void checkEmptyRegister() {
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);

        register.submitRegister();
        assertThat(register.getError(), containsString("Invalid email address."));

        driver.quit();
    }

    @Test
    public void checkInvalidRegister() {
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);

        register.fillInRegister("abc");
        register.submitRegister();
        assertThat(register.getError(), containsString("Invalid email address."));

        driver.quit();
    }

    @Test
    public void checkUsedRegister() {
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);

        register.fillInRegister("test@yahoo.com");
        register.submitRegister();
        assertThat(register.getError(), containsString("An account using this email address has already been registered. Please enter a valid password or request a new one."));

        driver.quit();
    }
}
