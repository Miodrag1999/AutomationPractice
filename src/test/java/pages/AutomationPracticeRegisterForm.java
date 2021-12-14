package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AutomationPracticeRegisterForm {
    @FindBy (className = "radio-inline")
    private List<WebElement> genderRadioButton;
    @FindBy (id= "customer_firstname")
    private WebElement customerFirstNameInput;
    @FindBy (id ="customer_lastname")
    private WebElement customerLastNameInput;
    @FindBy (id = "email")
    private WebElement emailInput;
    @FindBy (id= "passwd")
    private WebElement passwordInput;
    @FindBy (id = "days")
    private WebElement selectDay;
    @FindBy (id = "months")
    private WebElement selectMonth;
    @FindBy (id = "years")
    private WebElement selectYear;
    @FindBy (id = "firstname")
    private WebElement firstNameInput;
    @FindBy (id = "lastname")
    private WebElement lastNameInput;
    @FindBy (id = "newsletter")
    private WebElement newsletter;
    @FindBy (id = "optin")
    private WebElement offers;
    @FindBy (id = "company")
    private WebElement companyInput;
    @FindBy (id = "address1")
    private WebElement firstAddressInput;
    @FindBy (id = "address2")
    private WebElement secondAddressInput;
    @FindBy (id = "city")
    private WebElement cityInput;
    @FindBy (id = "id_state")
    private WebElement selectState;
    @FindBy (id = "postcode")
    private WebElement postalCodeInput;
    @FindBy (id = "id_country")
    private WebElement selectCountry;
    @FindBy (id = "other")
    private WebElement additionalInput;
    @FindBy (id = "phone")
    private WebElement homePhoneInput;
    @FindBy (id = "phone_mobile")
    private WebElement mobilePhoneInput;
    @FindBy (id = "alias")
    private WebElement aliasInput;
    @FindBy (id = "submitAccount")
    private WebElement submitAccountButton;
    @FindBy (xpath = "//*[contains(@class, 'alert alert-danger')][1]")
    public WebElement errorMessage;

    protected WebDriver driver;

    public AutomationPracticeRegisterForm(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void fillInRegisterMandatory(String firstName, String lastName, String password, String firstAddress, String city, String postalCode,  String mobilePhone){
        customerFirstNameInput.sendKeys(firstName);
        customerLastNameInput.sendKeys(lastName);
        passwordInput.sendKeys(password);
        firstAddressInput.sendKeys(firstAddress);
        cityInput.sendKeys(city);
        postalCodeInput.sendKeys(postalCode);
        mobilePhoneInput.sendKeys(mobilePhone);
    }

    public void fillInRegisterOptional( String company, String secondAddress, String aditionalInfo, String homePhone){
        companyInput.sendKeys(company);
        secondAddressInput.sendKeys(secondAddress);
        additionalInput.sendKeys(aditionalInfo);
        homePhoneInput.sendKeys(homePhone);
    }

    public void selectGender(String gender){
        if(gender.equalsIgnoreCase("Mr."))
            genderRadioButton.get(0).click();
        else if(gender.equalsIgnoreCase("Mrs."))
            genderRadioButton.get(1).click();
    }

    public void selectDayOfBirth(String day){
        Select dobDropDown = new Select(selectDay);
        dobDropDown.selectByValue(day);
    }

    public void selectMonthOfBirth(String month){
        Select dobDropDown = new Select(selectMonth);
        dobDropDown.selectByValue(month);
    }
    public void selectYearOfBirth(String year) {
        Select dobDropDown = new Select(selectYear);
        String years = year;
        if (Integer.parseInt(years) <= 2003){
            dobDropDown.selectByValue(year);
        }
    }

    public void selectState(String state) {
        Select dobDropDown = new Select(selectState);
        dobDropDown.selectByVisibleText(state);
    }

    public void setSelectCountry(String country) {
        Select dobDropDown = new Select(selectCountry);
        dobDropDown.selectByVisibleText(country);
    }

    public void checkNewsletter(){
        if (! newsletter.isSelected())
            newsletter.click();
    }

    public void checkOffers(){
        if (! offers.isSelected())
            offers.click();
    }
    public void submitRegister(){
        submitAccountButton.click();
    }

    public String getError() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        String error = errorMessage.getText();
        return error;
    }
}
