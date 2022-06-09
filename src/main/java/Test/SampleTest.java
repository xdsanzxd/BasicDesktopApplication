package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;
import org.testng.Assert;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import utilities.GetExcelData;



public class SampleTest {
    private static DesktopOptions options;
    private static WiniumDriverService service=null;
    private static WiniumDriver driver=null;;

    @BeforeTest
    public static void setupEnvironment() throws MalformedURLException {
        String appPath = "C:/windows/system32/calc.exe";
        //String appPath = "C:/ExcelData/TestDataXLS.xlsx";
        DesktopOptions option = new DesktopOptions();
        option.setApplicationPath(appPath);
        option.setDebugConnectToRunningApp(false);
        option.setLaunchDelay(2);
        driver = new WiniumDriver(new URL("http://localhost:9999"),option);
        }

    @Test(dataProvider = "excel-data-sheet1" , dataProviderClass = GetExcelData.class)
    public void VerifyNumberDisplayed(String data1, String data2) throws InterruptedException, MalformedURLException {
        setupEnvironment();
        System.out.println("Data obteined: "+data1 + data2);
        Thread.sleep(3000);
        driver.findElement(By.id("NumberPad")).findElement(By.id("num8Button")).findElement(By.name("Eight")).click();
        String results = driver.findElement(By.id("CalculatorResults")).getAttribute("Name");
        Assert.assertTrue(results.contains("8"));
        System.out.println("the number "+results+", was displayed sucessfully");
        driver.findElement(By.id("Close")).findElement(By.name("Close Calculator")).click();
    }

    @AfterSuite
    public void tearDown(){
      // service.stop();
    }
}