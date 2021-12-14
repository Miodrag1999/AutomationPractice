package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutomationPracticeRegister {
    @FindBy(id = "email_create")
    public WebElement enterEmailAddress;
    @FindBy(id = "SubmitCreate")
    private WebElement createAccountButton;
    @FindBy(id = "create_account_error")
    private WebElement errorMessage;

    protected WebDriver driver;

    public AutomationPracticeRegister(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void fillInRegister(String email){
        enterEmailAddress.sendKeys(email);
    }

    public void submitRegister(){
        createAccountButton.click();
    }

    public String getError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String error = errorMessage.getText();
        return error;
    }
}
