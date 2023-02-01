package infrastructure.page_objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPage {

    @FindBy(xpath = "//*[@id=\"hs_menu_wrapper_site_header-module-1_\"]/ul/li[2]/a")
    private WebElement login_findBy;


    public WebElement GetLogin() {
        return login_findBy;
    }



}
