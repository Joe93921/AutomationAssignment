package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvalidDataPage {
        WebDriver driver;
        @FindBy(xpath = "//a[@class='sf-with-ul' and contains(@title,'Women')]")
        WebElement clickwomentab;

        public InvalidDataPage(WebDriver driver) {
            this.driver = driver;
            PageFactory.initElements(driver, this);
        }

        public String verify(){
            return clickwomentab.getText();
        }

}
