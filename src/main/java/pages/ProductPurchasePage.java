package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductPurchasePage {
    WebDriver driver;
    @FindBy(css = "a.button.ajax_add_to_cart_button.btn.btn-default")
    List<WebElement> addtocart;
    @FindBy(xpath = "//a[@class='btn btn-default button button-medium']")
    WebElement firstcheckout;
    @FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
    WebElement summarycheckout;
    @FindBy(xpath = "//button[@class='button btn btn-default button-medium']")
    WebElement addresscheckout;
    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement acceptcheckbox;
    @FindBy(xpath = "//button[@class='button btn btn-default standard-checkout button-medium']")
    WebElement proceedcheckoutbutton1;
    @FindBy(xpath = "//a[@class='bankwire' and contains(@title, 'Pay by bank wire')]")
    WebElement paybybankwire;
    @FindBy(xpath = "//button[@class='button btn btn-default button-medium']")
    WebElement confirm;
    @FindBy(xpath = "//strong[@class='dark' and contains(text(),'Your order on My Store is complete.')]")
    WebElement paymentveriy;


    public ProductPurchasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void addToCart() throws InterruptedException {
        Thread.sleep(200);
        try{
            addtocart.get(0).click();
            System.out.println("Element is clicked");
        }catch(NoSuchElementException nse){
            Collections.emptyList();
        }

    }

    public void firstCheckOut(){
        WebDriverWait wait= new WebDriverWait(this.driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn btn-default button button-medium']")));
        firstcheckout.click();
    }

    public void setProceedcheckout(){
        summarycheckout.click();
    }

    public void clickProceedcheckout(){
        WebDriverWait wait= new WebDriverWait(this.driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button btn btn-default button-medium']")));
        addresscheckout.click();
    }

    public void setAcceptcheckbox(){
        WebDriverWait wait= new WebDriverWait(this.driver,50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='button btn btn-default standard-checkout button-medium']")));
        acceptcheckbox.click();
    }

    public void shippingcheckout(){
        proceedcheckoutbutton1.click();
    }

    public void proceedpayment(){
        paybybankwire.click();
    }

    public void confirmpayment(){
        confirm.click();
    }

    public String assertpayment(){
        return paymentveriy.getText();
    }
}
