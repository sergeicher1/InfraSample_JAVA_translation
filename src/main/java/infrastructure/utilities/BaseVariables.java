package infrastructure.utilities;

import infrastructure.page_objects.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.asserts.SoftAssert;

public class BaseVariables {

    // Web

    protected static Actions actions;
    protected static WebDriver driver;
    protected static SoftAssert soft_assert;


    // Page Objects - Web
    protected static MainPage main_page;
}
