package infrastructure.utilities;

import infrastructure.page_objects.MainPage;
import org.openqa.selenium.support.PageFactory;

public class ManagePages extends BaseVariables {
    public static void InitWebPages() {

        main_page = PageFactory.initElements(driver, MainPage.class);


    }
}
