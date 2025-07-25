package core.pageobjects;

import core.testutils.BrowserActionsUtility;
import core.testutils.LoggerUtility;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegistrationPage extends BrowserActionsUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private final By practiceFormTab = By.xpath("//a[contains(@href,'selenium_automation_practice.php')]");

    public static final By Name = By.id("name");
    public static final By Email = By.id("email");
    //    public static final By Gender = By.xpath("//div[input[@type='radio'] and label[contains(text(),\"\")]][1]");
    public static final By Gender = By.xpath("(//input[@type='radio'])[2]");
    public static final By MobileNo = By.id("mobile");
    public static final By DateofBirth = By.id("dob");
    public static final By Subjects = By.id("subjects");
    public static final By Hobbies = By.xpath("(//input[@type='checkbox'])[2]");
    public static final By Picture = By.xpath("//input[@id='picture' and @type='file']");
    public static final By CurrentAddress = By.tagName("textarea");
    public static final By State = By.id("state");
    public static final By City = By.id("city");
    public static final By Login = By.xpath("//input[@value='Login']");
    public static final By errors = By.xpath("//label[@class='error']");

    public RegistrationPage() {
        super();
    }

    public RegistrationPage registrationFormGrid(String name, String email, String mobileNo, String dateOfBirth, String subject, String picturePath, String currentAddress, String stateName, String cityName) {
        try {
            clickOn(practiceFormTab);
            enterText(Name, name);
            enterText(Email, email);
            clickOn(Gender);
            enterText(MobileNo, mobileNo);
            selectDate(DateofBirth, dateOfBirth);
            enterText(Subjects, subject);
            JSClick(Hobbies);
            enterText(Picture, picturePath);
            enterText(CurrentAddress, currentAddress);
            selectFromDropdown(State, stateName);
            selectFromDropdown(City, cityName);

            try {
                JSClick(Login);
            } catch (ElementClickInterceptedException e) {
                clickOn(Login);
            }
            List<WebElement> ele = getAttribute(errors);
            if (ele.isEmpty()) {
                System.out.println("Registration completed successfully");
            } else {
                System.out.println("Some fields are missing");
            }

        } catch (ElementClickInterceptedException e) {
                logger.error("Error in registration form: " + e.getMessage());
            throw e;
        }
        return this;
    }
}
