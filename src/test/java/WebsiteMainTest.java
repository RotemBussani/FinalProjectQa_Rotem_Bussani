import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


class WebsiteMainTest {

    private WebDriver driver= new ChromeDriver();

    @BeforeEach
    public void initializeSelenium() {
        //Initialize Selenium
        //Config the webdriver.chrome.driver which is a permanent key with the path value
        System.setProperty("web-driver.chrome.driver", "C:\\Users\\rotem\\Desktop\\Rotem studies\\presentations\\selenium\\chromedriver_win32 (1)\\chromedriver.exe");    //The web driver is an interface. The ChromeDriver inherits the WebDriver.
        driver.manage().window().maximize();//Make the browser open on the whole screen
    }

    @Test
    public void testUrl() throws InterruptedException {
        //This method tests if the opened URL is the expected URL
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        assertEquals("https://www.bankotsar.co.il/wps/portal/", driver.getCurrentUrl());
        driver.getPageSource();
    }

    @Test
    public void HomePageNavigation () throws InterruptedException {
        //This method tests if by pressing the logo it navigates you to the home page (+taking a snapshot)
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        Thread.sleep(1000);
        driver.findElement(By.linkText("כרטיסי אשראי")).click();
        Thread.sleep(3000);
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\1.png");
        driver.findElement(By.cssSelector("#content > div.wpthemeFrame > header > div > div.main-header-content > div.header-menu-holder > strong > a")).click();
        Thread.sleep(3000);
        assertEquals("https://www.bankotsar.co.il/wps/portal/FibiMenu/Marketing/Private", driver.getCurrentUrl());
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\2.png");
    }

    @Test
    public void scrollPage() throws InterruptedException {
        //This method tests if the Scrolling bar works and take snapshots
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element1 = driver.findElement(By.id("blog-posts"));
        js.executeScript("arguments[0].scrollIntoView();",element1);
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\3.png");
        Thread.sleep(5000);
        WebElement element2 = driver.findElement(By.id("fibi_rates_stocks"));
        js.executeScript("arguments[0].scrollIntoView();",element2);
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\4.png");
        Thread.sleep(5000);
        WebElement element3 = driver.findElement(By.className("main-footer"));
        js.executeScript("arguments[0].scrollIntoView();",element3);
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\5.png");
        Thread.sleep(5000);

        int elementLocation1 = element1.getLocation().getY();
        int elementLocation2 = element2.getLocation().getY();
        int elementLocation3 = element3.getLocation().getY();

        assertTrue(elementLocation1 < elementLocation2 && elementLocation2 < elementLocation3,
                "The page did not scroll correctly");
    }



    @Test
    public void testActionsOnPage() throws InterruptedException {
        //This method tests buttons: back,forward,refresh and opening a new window.
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("הלוואות ומשכנתאות")).click();


        assertEquals("https://www.bankotsar.co.il/wps/portal/FibiMenu/Marketing/Private/Loansandmortgages", driver.getCurrentUrl());
        Thread.sleep(3000);
        driver.navigate().back();

        assertEquals("https://www.bankotsar.co.il/wps/portal/", driver.getCurrentUrl());
        Thread.sleep(3000);
        driver.navigate().forward();

        assertEquals("https://www.bankotsar.co.il/wps/portal/FibiMenu/Marketing/Private/Loansandmortgages", driver.getCurrentUrl());
        Thread.sleep(3000);
        driver.navigate().refresh();

        assertEquals("https://www.bankotsar.co.il/wps/portal/FibiMenu/Marketing/Private/Loansandmortgages", driver.getCurrentUrl());
        Thread.sleep(3000);
    }

    @Test
    public void openANewTab() throws InterruptedException {
        //This method tests opening of a new tab and navigating to it.
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        String PicName= "";
        int i, k, numberOfSnapshots=2;
        Thread.sleep(1000);
        String originalHandle = driver.getWindowHandle();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.open('https://he.wikipedia.org/wiki/')");
        Thread.sleep(3000);

        js.executeScript("window.open('https://www.ecomschool.co.il/')");
        Thread.sleep(3000);


        for (String handle : driver.getWindowHandles())
        {
            if (!handle.equals(originalHandle))
            {
                driver.switchTo().window(handle);
                String pageTitle = js.executeScript("return document.title").toString();

                for (i=0;i<=numberOfSnapshots;i++) {
                    k = i + 6;
                    PicName = "C:\\Users\\rotem\\Pictures\\Photos of final project\\" + k + ".png";
                    WebsiteMain.takeSnapShot(driver, PicName);
                    switch (i) {
                        case 0:
                            break;

                        case 1:
                            // Assert that the actual title contains the expected text "Wikipedia"
                            assertTrue(pageTitle.contains("ויקיפדיה"));
                            break;

                        case 2:
                            // Assert that the actual title contains the expected text "ecomschool"
                            assertTrue(pageTitle.contains("מכללת לימודי הייטק | לימודים ב-Ecom School"));
                            break;
                    }
                    break;
                }
            }
        }
    }

    @Test
    public void searchWebsite() throws InterruptedException {
        //This method tests searching and text printing +taking a snapshot.
        driver.get("https://he.wikipedia.org/wiki/");
        Thread.sleep(1000);
        WebElement input = driver.findElement(By.name("search"));
        input.sendKeys("משפחה");
        driver.findElement(By.id("searchform")).submit();
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\1.png");
        Thread.sleep(3000);
        WebElement result = driver.findElement(By.cssSelector("#mw-content-text > div.mw-parser-output > p:nth-child(5)"));
        WebsiteMain.takeSnapShot(driver, "C:\\Users\\rotem\\Pictures\\Photos of final project\\1.png");
        System.out.println("Your result is: "+result.getText());
        assertTrue(driver.getCurrentUrl().contains("https://he.wikipedia.org/wiki/"));
    }


    @Test
    public void testAlerts() throws InterruptedException {
        //This method tests invalid details and the error Message (+use wait)
        driver.get("https://www.bankotsar.co.il/wps/portal/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("close-btn"))).click();

        WebElement userText = driver.findElement(By.className("login-trigger"));
        userText.click();
        driver.switchTo().frame("loginFrame");

        WebElement element_ID = driver.findElement(By.id("username"));
        element_ID.sendKeys("W5G79YE");
        Thread.sleep(1500);
        WebElement element_PWD = driver.findElement(By.id("password"));
        element_PWD.sendKeys("ThankU");
        Thread.sleep(1500);

        WebElement element_Enter = driver.findElement(By.id("continueBtn"));
        element_Enter.click();
        Thread.sleep(1500);
        WebElement errorMessageElement = driver.findElement(By.className("sr-only"));
        String errorMessage = errorMessageElement.getText();

        assertEquals("להקליד הפרטים הרלוונטים", errorMessage);
        Thread.sleep(1500);
    }

    @Test
    public void dropdown() throws InterruptedException {
        //This method tests opening a window, scrolling down and picking a specific option
        //from a dropdown element.
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("איתור סניף")).click();
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.className("form-holder-h"));
        js.executeScript("arguments[0].scrollIntoView();",element);
        Thread.sleep(3000);
        driver.findElement(By.id("bankSelection")).click();
        Thread.sleep(3000);
        Select selectList = new Select(driver.findElement(By.name("bankSelection")));
        List<WebElement> options = selectList.getOptions();
        assertEquals(5, options.size());
        Thread.sleep(1000);

        selectList.selectByIndex(1);
        assertEquals(1, selectList.getAllSelectedOptions().size());
        assertEquals(options.get(1), selectList.getFirstSelectedOption());
        Thread.sleep(3000);
        selectList.selectByValue("sa_bank_ubank");
        assertEquals(options.get(2).toString(), selectList.getFirstSelectedOption().toString());
        Thread.sleep(3000);
        selectList.selectByVisibleText("אוצר החייל – הבנק הבינלאומי");
        assertEquals(options.get(3).toString(), selectList.getFirstSelectedOption().toString());
        Thread.sleep(3000);
    }

    @Test
    public void testCheckBox() throws InterruptedException {
        //This method tests the checkbox element
        driver.get("https://www.bankotsar.co.il/wps/portal/");
        Thread.sleep(3000);
        driver.findElement(By.linkText("איתור סניף")).click();
        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.id("branchNumSelection"));
        js.executeScript("arguments[0].scrollIntoView();",element);
        Thread.sleep(3000);
        WebElement checkbox1 = driver.findElement(By.cssSelector("#cat_branches_bank_services_5"));
        Thread.sleep(3000);
        WebElement checkbox2 = driver.findElement(By.cssSelector("#cat_branches_bank_services_9"));
        assertFalse(checkbox1.isSelected());
        checkbox2.click();
        Thread.sleep(3000);
        assertTrue(checkbox2.isSelected());
        checkbox1.click();
        Thread.sleep(3000);

        assertTrue(checkbox1.isSelected());
    }


    @Test
    public void testBookAVacation() throws InterruptedException {
        //This method tests a booking of a vacation can be complete successfully
        driver.get("https://www.bankotsar.co.il/wps/portal");
        Thread.sleep(2000);
        driver.findElement(By.id("beyondColorButton")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("beyond-approve-button")).click();
        Thread.sleep(1000);

        String originalHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalHandle)) {
                driver.switchTo().window(handle);
            }
        }
        Thread.sleep(2000);
        driver.getCurrentUrl();
        driver.findElement(By.xpath("//*[@id='wrapper']/header/div[1]/nav/div/div/ul/li[6]/a")).click();
        Thread.sleep(2000);
        driver.findElement(By.linkText("סקי")).click();
        Thread.sleep(2000);
        driver.getCurrentUrl();
        driver.findElement(By.xpath("//*[@id='search_ski']/se-ski-form/form/div/div[1]/div/div[1]/se-ski-countries-dropdown/div/div[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='search_ski']/se-ski-form/form/div/div[1]/div/div[1]/se-ski-countries-dropdown/div/div[2]/ul/li[3]/span")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id='search_ski']/se-ski-form/form/div/div[1]/div/div[2]/se-ski-resorts-dropdown")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='search_ski']/se-ski-form/form/div/div[1]/div/div[2]/se-ski-resorts-dropdown/div/div[2]/ul/li[2]/span")).click();
        Thread.sleep(1500);
        driver.findElement(By.name("fdate")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("datepicker_widget"));
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='datepicker_widget']/div/div[2]/table[1]/tbody/tr[4]/td[2]/div/span")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//*[@id='search_ski']/se-ski-form/form/div/div[2]/div/form-button/button")).click();
        Thread.sleep(10000);
        String actualWebsite="https://bynd.co.il/ski/results?fdate=20/03/23&countryid=13&countrycode=AT&cname=%D7%90%D7%95%D7%A1%D7%98%D7%A8%D7%99%D7%94&site=29&frange=15&padt=2";
        assertEquals(actualWebsite,driver.getCurrentUrl());
    }

    @AfterEach
    public void quit()
    {
        driver.quit();
    }


}


