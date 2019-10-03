package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class AccountCreationPage {

    WebDriver driver;
    @FindBy(className = "login")
    WebElement signintab;
    @FindBy(id="email_create")
    WebElement email;
    @FindBy(id="SubmitCreate")
    WebElement createaccount;
    @FindBy(xpath="//*[@id='id_gender2']")
    WebElement usertitle;
    @FindBy(xpath = "//input[@id='customer_firstname']")
    WebElement firstname;
    @FindBy(xpath = "//input[@id='customer_lastname']")
    WebElement lastname;
    @FindBy(id = "passwd")
    WebElement password;
    @FindBy(id="days")
    WebElement birthdate;
    @FindBy(id="months")
    WebElement birthmonth;
    @FindBy(id="years")
    WebElement birthyear;
    @FindBy(id="company")
    WebElement company;
    @FindBy(id="address1")
    WebElement address;
    @FindBy(id="city")
    WebElement city;
    @FindBy(id="id_state")
    WebElement state;
    @FindBy(id="postcode")
    WebElement postcode;
    @FindBy(id="id_country")
    WebElement country;
    @FindBy(id="phone_mobile")
    WebElement mobileno;
    @FindBy(id="alias")
    WebElement aliasaddress;
    @FindBy(id="submitAccount")
    WebElement register;
    @FindBy(css = "#header > div.nav > div > div > nav > div:nth-child(1) > a > span")
    WebElement verifyuserdata;
    @FindBy(id="firstname")
    WebElement afirstname;
    @FindBy(id="lastname")
    WebElement slastname;


    public AccountCreationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void clickSigninTab() {
        signintab.click();
    }

    public void enterEmail(String susername){
        System.out.println("User signed in");
        try{
            WebDriverWait wait= new WebDriverWait(this.driver,5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email_create']")));
        }
        catch (Exception e){
            System.out.println("Timeout, page not found");
        }
        email.sendKeys(susername);
    }

    public void clickCreateAccount() {
        createaccount.click();
    }

    public void waitTillPageload() {
        WebDriverWait wait= new WebDriverWait(this.driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='alias']")));
    }

    public void selectTitle(){
        usertitle.click();
    }

    public void enterFirstName(String cfirstname) {
        firstname.sendKeys(cfirstname);
    }

    public void enterLastName(String clastname)  {
        lastname.sendKeys(clastname);
    }

    public void enterPassword(String cpassword){
        password.sendKeys(cpassword);
    }

    public void selectBirthdate(String cbirthdate){
        Select bddropdown=new Select(birthdate);
        bddropdown.selectByValue(cbirthdate);
    }

    public void selectMonth(){
        Select bmdropdown=new Select(birthmonth);
        bmdropdown.selectByIndex(1);
    }

    public void selectYear(String cbirthyear){
        Select bydropdown=new Select(birthyear);
        bydropdown.selectByValue(cbirthyear);
    }

    public void enterCompany(String ccompany){
        company.sendKeys(ccompany);
    }

    public void enterAddress(String caddress){
        address.sendKeys(caddress);
    }

    public void enterCity(String ccity){
        city.sendKeys(ccity);
    }

    public void selectState(String cstate){
        Select statedropdown=new Select(state);
        statedropdown.selectByVisibleText(cstate);
    }

    public void enterPostalCode(String cpostcode){
        postcode.sendKeys(cpostcode);
    }

    public void selectCountry(String ccountry){
        Select countrydropdown=new Select(country);
        countrydropdown.selectByVisibleText(ccountry);;
    }

    public void enterMobileno(String cmobile) {
        mobileno.sendKeys(cmobile);
    }
    public void enterAliasAddress(String caaddress){
        aliasaddress.clear();
        aliasaddress.sendKeys(caaddress);
    }

    public void register(){
        register.click();
    }

    public String  getcreatedAccountName(){
        return verifyuserdata.getText();
    }

}
