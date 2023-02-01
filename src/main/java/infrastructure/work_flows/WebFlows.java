package infrastructure.work_flows;

import infrastructure.extensions.UIActions;
import infrastructure.utilities.CommonOperations;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class WebFlows extends CommonOperations {


    public static void ClickOnLogin() {
        Allure.step("Business flow");


//        UIActions.Click(main_page.GetLogin());
    }

}
