package test;

import Utility.BrowserDetails;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Listeners(listener.ListenerTest.class)

public class DemoAppTest extends BrowserDetails {


    AccountCreationPage accobj;
    SearchVerifyPage searchobj;
    ProductPurchasePage productobj;
    DownloadPdfPage downloadobj;
    InvalidDataPage invalidloginobj;


    FileInputStream fis = new FileInputStream("C:\\Users\\JyotiP\\IdeaProjects\\mavendemo\\configdetails\\config.properties");
    Properties properties = new Properties();


    public DemoAppTest() throws FileNotFoundException {
    }

    @Test
    public void AccountCreate() throws IOException, InterruptedException {
        properties = new Properties();
        properties.load(fis);
        accobj = new AccountCreationPage(driver);
        accobj.clickSigninTab();
        Random ran = new Random();
        String userEmail =  properties.getProperty("emailid") + ran.nextInt(200) + "@gmail.com";
        accobj.enterEmail(userEmail);
        accobj.clickCreateAccount();
        accobj.waitTillPageload();
        accobj.selectTitle();
        accobj.enterFirstName("Jyoti");
        accobj.enterLastName("Pujari");
        accobj.enterPassword(properties.getProperty("password"));
        accobj.selectBirthdate("21");
        accobj.selectMonth();
        accobj.selectYear("1993");
        accobj.enterCompany("Tal");
        accobj.enterAddress("Baner");
        accobj.enterCity("Pune");
        accobj.selectState("Indiana");
        accobj.enterPostalCode("11124");
        accobj.selectCountry("United States");
        accobj.enterMobileno("7854136960");
        accobj.enterAliasAddress("Same as above");
        accobj.register();
        String fullname = "Jyoti" + " " + "Pujari";
        accobj.getcreatedAccountName();
        assertThat("incorrect firstname and lastname", accobj.getcreatedAccountName(), equalTo(fullname));
        //System.out.println("Customer account created successfully");
    }


    @Test
    public void SearchData() throws IOException, InterruptedException {
        properties = new Properties();
        properties.load(fis);
        searchobj = new SearchVerifyPage(driver);
        searchobj.clickSigninTab();
        searchobj.waitTillPageload();
        searchobj.enteremailid("useremail189@gmail.com");
        searchobj.enterpassword("First@2019");
        searchobj.clickSubmit();
        searchobj.enterSearchData("Dress");
        assertThat("No search found are matched ", searchobj.searchResultsCount(), equalTo("7 results have been found."));
        assertThat("No product found with name dress", searchobj.getListOfElements().toString(), containsString("Dress"));

    }

    @Test
    public void ProductCheckout() throws IOException, InterruptedException {
        properties = new Properties();
        properties.load(fis);
        searchobj = new SearchVerifyPage(driver);
        searchobj.clickSigninTab();
        searchobj.waitTillPageload();
        searchobj.enteremailid("useremail189@gmail.com");
        searchobj.enterpassword("First@2019");
        searchobj.clickSubmit();
        searchobj.enterSearchData("Dress");
        searchobj.getListOfElements();
        assertThat("No search found are matched ", searchobj.searchResultsCount(), equalTo("7 results have been found."));
        productobj=new ProductPurchasePage(driver);
        productobj.addToCart();
        productobj.firstCheckOut();
        productobj.setProceedcheckout();
        productobj.clickProceedcheckout();
        productobj.setAcceptcheckbox();
        productobj.shippingcheckout();
        productobj.proceedpayment();
        productobj.confirmpayment();
        productobj.assertpayment();
        assertThat("Payment failed", productobj.assertpayment(), equalTo("Your order on My Store is complete."));
        //System.out.println("Product checked out with assertion");
    }


    @Test
    public void Downloadpdf() throws IOException, InterruptedException {
        properties = new Properties();
        properties.load(fis);
        searchobj = new SearchVerifyPage(driver);
        searchobj.clickSigninTab();
        searchobj.waitTillPageload();
        searchobj.enteremailid("useremail189@gmail.com");
        searchobj.enterpassword("First@2019");
        searchobj.clickSubmit();
        searchobj.enterSearchData("Dress");
        searchobj.getListOfElements();
        assertThat("No search found are matched ", searchobj.searchResultsCount(), equalTo("7 results have been found."));
        productobj=new ProductPurchasePage(driver);
        productobj.addToCart();
        productobj.firstCheckOut();
        productobj.setProceedcheckout();
        productobj.clickProceedcheckout();
        productobj.setAcceptcheckbox();
        productobj.shippingcheckout();
        productobj.proceedpayment();
        productobj.confirmpayment();
        productobj.assertpayment();
        assertThat("Payment failed", productobj.assertpayment(), equalTo("Your order on My Store is complete."));
        //System.out.println("Product checked out with assertion");
        downloadobj = new DownloadPdfPage(driver);
        downloadobj.clickBacktoOrder();
        downloadobj.clickOrder();
        downloadobj.clickPlusOption();
        String downloadUrl = downloadobj.getdownloadLink();
        downloadobj.clickdownloadpdf();
        URL url = new URL(downloadUrl);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        int statusCode = http.getResponseCode();
        //String responseMessage = http.getResponseMessage();
        //System.out.println("statusCode" + statusCode);
        assertThat("File not downloaded successfully",statusCode,equalTo(200));
    }


    @Test
    public void InvalidDataCase() throws IOException {
        String test= "Woman";
        properties = new Properties();
        properties.load(fis);
        searchobj = new SearchVerifyPage(driver);
        searchobj.clickSigninTab();
        searchobj.waitTillPageload();
        searchobj.enteremailid("useremail189@gmail.com");
        searchobj.enterpassword("First@2019");
        searchobj.clickSubmit();
        invalidloginobj = new InvalidDataPage(driver);
        String verifywomentab = invalidloginobj.verify();
        Assert.assertEquals(verifywomentab,test);
    }

}