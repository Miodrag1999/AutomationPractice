package tests;

import Helpers.RandomEmail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.AutomationPracticeRegister;
import pages.AutomationPracticeRegisterForm;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;


public class AutomationPracticeRegisterFormTest {
    WebDriver driver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Workspace\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }
    @Test
    public void checkValidRegister(){
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);
        String email = RandomEmail.generateRandomEmail();
        register.fillInRegister(email);
        register.submitRegister();

        AutomationPracticeRegisterForm form = new AutomationPracticeRegisterForm(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        form.fillInRegisterMandatory("Test","Tester","Testing123","Home 404","Home","12345","123456789");
        form.selectState("Alabama");
        form.setSelectCountry("United States");
        form.submitRegister();

        WebDriverWait waitAgain = new WebDriverWait(driver, Duration.ofSeconds(60));
        waitAgain.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=my-account"));
        assertThat(driver.getCurrentUrl(), containsString("http://automationpractice.com/index.php?controller=my-account"));

        driver.quit();
    }

    @Test
    public void checkValidRegisterFull(){
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);
        String email = RandomEmail.generateRandomEmail();
        register.fillInRegister(email);
        register.submitRegister();

        AutomationPracticeRegisterForm form = new AutomationPracticeRegisterForm(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        form.selectGender("Mr.");
        form.selectDayOfBirth("1");
        form.selectMonthOfBirth("1");
        form.selectYearOfBirth("2000");
        form.checkNewsletter();
        form.checkOffers();
        form.fillInRegisterMandatory("Test","Tester","Testing123","Home 404","Home","12345","123456789");
        form.selectState("Alabama");
        form.setSelectCountry("United States");
        form.fillInRegisterOptional("Endava","Home 123","info","987654321");
        form.submitRegister();

        WebDriverWait waitAgain = new WebDriverWait(driver, Duration.ofSeconds(60));
        waitAgain.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=my-account"));
        assertThat(driver.getCurrentUrl(), containsString("http://automationpractice.com/index.php?controller=my-account"));

        driver.quit();
    }

    @Test
    public void checkEmptyRegister(){
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);
        String email = RandomEmail.generateRandomEmail();
        register.fillInRegister(email);
        register.submitRegister();

        AutomationPracticeRegisterForm form = new AutomationPracticeRegisterForm(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        form.submitRegister();
        assertThat(form.getError(), containsString("There are 8 errors\n" +
                "You must register at least one phone number.\n" +
                "lastname is required.\n" +
                "firstname is required.\n" +
                "passwd is required.\n" +
                "address1 is required.\n" +
                "city is required.\n" +
                "The Zip/Postal code you've entered is invalid. It must follow this format: 00000\n" +
                "This country requires you to choose a State."));

        driver.quit();
    }

    @Test
    public void checkValidYear(){
        AutomationPracticeRegister register = new AutomationPracticeRegister(driver);
        String email = RandomEmail.generateRandomEmail();
        register.fillInRegister(email);
        register.submitRegister();

        AutomationPracticeRegisterForm form = new AutomationPracticeRegisterForm(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation"));

        form.selectGender("Mr.");
        form.selectDayOfBirth("1");
        form.selectMonthOfBirth("1");
        form.selectYearOfBirth("2004");
        form.checkNewsletter();
        form.checkOffers();
        form.fillInRegisterMandatory("Test","Tester","Testing123","Home 404","Home","12345","123456789");
        form.selectState("Alabama");
        form.setSelectCountry("United States");
        form.fillInRegisterOptional("Endava","Home 123","info","987654321");
        form.submitRegister();

        assertThat(form.getError(), containsString("There is 1 error\n" +
                "Invalid date of birth"));

        driver.quit();
    }
}
