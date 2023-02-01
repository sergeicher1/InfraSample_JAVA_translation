package infrastructure.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.checkerframework.checker.units.qual.C;
import org.checkerframework.framework.qual.DefaultQualifier;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CommonOperations extends BaseVariables {

    static String data_path = "src\\main\\java\\infrastructure\\Configuration\\data.xml";
    static String data_path_options = "src\\main\\java\\infrastructure\\Configuration\\chrome_options.xml";

    public static void InitWebBrowser() throws ParserConfigurationException, IOException, SAXException {
        if (ExternalFiles.XML.ReadData(data_path, "Browser").equalsIgnoreCase("chrome")) {
            driver = GetChrome();
        } else if (ExternalFiles.XML.ReadData(data_path, "Browser").equalsIgnoreCase("firefox")) {
            driver = GetFireFox();
        } else if (ExternalFiles.XML.ReadData(data_path, "Browser").equalsIgnoreCase("edge")) {
            driver = GetMSEdge();
        } else
            throw new RuntimeException("The problem with choosing the browser: ");

        actions = new Actions(driver);
        soft_assert = new SoftAssert();
    }


    public static WebDriver GetChrome() throws ParserConfigurationException, IOException, SAXException {
        // Reading Web options
        WebDriverManager.chromedriver().setup();
        String options_check = ExternalFiles.XML.ReadData(data_path, "ChromeOptions");
        ChromeOptions options = new ChromeOptions();
        System.out.println("Used Chrome driver");
        if (options_check.equalsIgnoreCase("enabled")) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(data_path_options);
            doc.getDocumentElement().normalize();
            List<String> args = new ArrayList<>();
            NodeList nodeList = doc.getElementsByTagName("*");
            for (int i = 1; i < nodeList.getLength(); i++) {
                // Get element
                Element element = (Element) nodeList.item(i);
                args.add(element.getNodeName());
            }
            for (String arg : args) {
                options.addArguments(ExternalFiles.XML.ReadData(data_path_options, arg));
            }
            return new ChromeDriver(options);

        } else if (options_check.equalsIgnoreCase("disabled")) {
            options.addArguments("--start-maximized"); // will open maximized window as default
            return new ChromeDriver(options);
        } else
            throw new RuntimeException("Wrong Input for options, check configuration in src\\main\\java\\infrastructure\\Configuration\\data.xml");

    }

    public static WebDriver GetFireFox() { // TODO: Check why firefox doesn't start, maybe bin path in system environments?
        System.setProperty("webdriver.gecko.driver", "src\\main\\java\\infrastructure\\Configuration\\geckodriver.exe");
//        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        System.out.println("Used firefox driver");
        return driver;
    }


    public static WebDriver GetMSEdge() {
        WebDriverManager.iedriver().setup();
        System.out.println("Used ms-edge driver");
        return new EdgeDriver();
    }

    @BeforeMethod
    public void StartSession() throws ParserConfigurationException, IOException, SAXException {
        System.out.println("\nTest case started!");
        InitWebBrowser();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ExternalFiles.XML.ReadData(data_path, "URL"));
        ManagePages.InitWebPages();

    }

    @AfterMethod
    public void CloseSession() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
        System.out.println("\nTest case completed!\n");
    }

    @Attachment(value = "Page screenshot", type = "image/png")  // TODO: where to use it?
    public byte[] SaveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}


