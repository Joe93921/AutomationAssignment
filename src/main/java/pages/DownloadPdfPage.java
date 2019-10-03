package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DownloadPdfPage {
    WebDriver driver;
    @FindBy(xpath = "//a[@class='button-exclusive btn btn-default']")
    WebElement backtoorder;
    @FindBy(xpath = "//a[@class='color-myaccount']")
    List<WebElement> orderid;
    @FindBy(xpath = "//span[@class='footable-toggle']")
    List<WebElement> downloadlink;
    @FindBy(xpath = "//div[@class='footable-row-detail-inner']//a[@title='Invoice']")
    WebElement downloadinvoicepdf;

    public DownloadPdfPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBacktoOrder(){
        backtoorder.click();
    }

    public void clickOrder(){
        orderid.get(0).click();
        System.out.println("Orderclicked");
    }

    public void clickPlusOption(){
        downloadlink.get(0).click();
        System.out.println("Click on Download");
    }

    public String getdownloadLink()
    {
        String invoiceDownloadLink = downloadinvoicepdf.getAttribute("href");
        return invoiceDownloadLink;
    }

    public void clickdownloadpdf(){
        downloadinvoicepdf.click();
    }

}
