package infrastructure.extensions;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.WebElement;
import infrastructure.utilities.CommonOperations;


public class UIActions extends CommonOperations {


    public static void Click(WebElement elem) {
        Allure.step("Click on element");
        elem.click();
    }


    public static void UpdateText(WebElement elem, String text) {
        Allure.step("Update text");
        elem.sendKeys(text);
    }


    public static void MouseHover(WebElement elem1) {
        Allure.step("Mouse hover on element");
        actions.moveToElement(elem1).perform();
    }

    public static void MouseHoverTwoEls(WebElement elem1, WebElement elem2) {
        Allure.step("Mouse hover on element then move to another element");
        actions.moveToElement(elem1).moveToElement(elem2).perform();
    }

    public static void MouseHoverAndClick(WebElement elem1) {
        Allure.step("Mouse hover and click on element");
        actions.moveToElement(elem1).click().build().perform();
    }

    public static void MouseHoverTwoElsAndClick(WebElement elem1, WebElement elem2) {
        Allure.step("Mouse hover on element then move to another element and click");
        actions.moveToElement(elem1).moveToElement(elem2).click().build().perform();
    }


}

