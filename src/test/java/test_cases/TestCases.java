package test_cases;

import infrastructure.utilities.CommonOperations;
import infrastructure.work_flows.WebFlows;
import io.qameta.allure.Description;
import org.testng.annotations.Test;


public class TestCases extends CommonOperations {


    @Test//(description = "Test Case 01")
    @Description("Test free")
    public void test01_FreeTest() {
        WebFlows.ClickOnLogin();
    }
}
