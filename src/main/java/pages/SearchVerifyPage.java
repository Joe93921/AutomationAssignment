package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;

//import static org.hamcrest.core.IsEqual.equalTo;
//import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
public class SearchVerifyPage {
    WebDriver driver;
    @FindBy(className = "login")
    WebElement signintab;
    @FindBy(id="email")
    WebElement emailidd;
    @FindBy(id="passwd")
    WebElement passwordlogin;
    @FindBy(id="SubmitLogin")
    WebElement submit;
    @FindBy(id="search_query_top")
    WebElement searchbox;
    @FindBy(xpath = "//span[contains(@class,'heading-counter')]  [contains(text(),'7 results have been found')]")
    WebElement searchcount;
    @FindBy(xpath = "//div/div/h5/a[@class='product-name']")
    List<WebElement> listofproducts;


    public SearchVerifyPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitTillPageload()  {
        WebDriverWait wait= new WebDriverWait(this.driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("authentication")));
    }
    public void clickSigninTab() {
        signintab.click();
    }

    public void enteremailid(String email){
        emailidd.sendKeys(email);
    }

    public void enterpassword(String password){
        passwordlogin.sendKeys(password);
    }

    public void clickSubmit(){
        submit.click();
    }

    public void enterSearchData(String productsearch){
        searchbox.sendKeys(productsearch);
        searchbox.sendKeys(Keys.ENTER);
    }
    public String searchResultsCount(){
        return searchcount.getText();
    }

    public List<String> getListOfElements() {
        List<String> prodname = new ArrayList<>();
        for(WebElement product:listofproducts) {
            if (product.getText().contains("Dress")) {
                prodname.add(product.getText());
            }
        }
        return prodname;
    }


}

