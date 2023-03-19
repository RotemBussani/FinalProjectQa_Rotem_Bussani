import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import java.io.File;
import java.io.IOException;

public class WebsiteMain {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("web-driver.chrome.driver", "C:\\Users\\rotem\\Desktop\\Rotem studies\\presentations\\selenium\\chromedriver_win32 (1)\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        String title = driver.getTitle();
        System.out.println("web site title is :-" + title);
        String url = driver.getCurrentUrl();
        System.out.println("the url  is =" + url);
        Thread.sleep(1000);
        WebElement input = driver.findElement(By.xpath("//*[@id='content']/div[3]/header/div/div[3]/div/form/button"));
        input.click();
        WebElement inputTextBox = driver.findElement(By.id("search"));
        Thread.sleep(1000);
        inputTextBox.sendKeys("סוגי חשבונות");
        input.submit();
        driver.findElement(By.linkText("סוגי חשבונות")).click();
        Thread.sleep(3000);


        driver.close();
    }
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath)
    {
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        try
        {
            FileHandler.copy(SrcFile, DestFile);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void scrolling(WebDriver driver, WebElement element)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

}


