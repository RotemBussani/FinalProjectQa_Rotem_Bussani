import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppiumTest {
    static AndroidDriver driver;
    static String appPackage = "com.snatik.matches";
    static String appActivity = "com.snatik.matches.MainActivity";


    public boolean isElementPresent(WebElement element) {
        // this function checks if an element is still displayed on the page.
        try {
            element.isDisplayed();
            return true;
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }


    public static boolean isElementExist(WebDriver driver, String xpath) {
        // this function checks if an element exists on the page.
        try {
            driver.findElement(By.xpath(xpath));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public int CardsCounter() {
        // this function counts how many memory card are there in a given page.
            int count=0,i=1,j=1;
            boolean flag=true,ezer=true;
            while (flag)
            {
                ezer=isElementExist(driver,"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout["+i+"]/android.widget.FrameLayout["+j+"]/android.widget.RelativeLayout");
                if (ezer)
                {
                    count++;
                    j++;
                    ezer=isElementExist(driver,"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout["+i+"]/android.widget.FrameLayout["+j+"]/android.widget.RelativeLayout");
                    if (!ezer)
                    {
                        j = 1;
                        i++;
                        ezer = isElementExist(driver,"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[" + i + "]/android.widget.FrameLayout[" + j + "]/android.widget.RelativeLayout");
                        if (!ezer)
                            flag=false;
                    }
                }
            }
            return count;
    }


    public void StartGame() {
        // this function is starting a game on beginner difficulty level
        WebElement startButton = driver.findElement(By.id(appPackage+":id/start_game_button"));
        startButton.click();
        WebElement gameBoard = driver.findElement(By.id(appPackage+":id/theme_animals"));
        gameBoard.click();
        WebElement difficultyOption = driver.findElement(By.id(appPackage+":id/select_difficulty_1"));
        difficultyOption.click();
    }


    @BeforeEach
    public void setUp() throws MalformedURLException {
        // setup configuration
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();//Set the desired capabilities like in the appium desktop
        desiredCapabilities.setCapability("deviceName", "emulator-5554");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appPackage", appPackage);
        desiredCapabilities.setCapability("appActivity", appActivity);

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");//The url that the appium sits on

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);//Create new instance of AndroidDriver with the url and desired capabilities
    }

    @Test
    public void testGameStarts() throws InterruptedException {
        // this method tests if a game has actually started.
        WebElement startButton = driver.findElement(By.id(appPackage+":id/start_game_button"));
        startButton.click();
        WebElement gameBoard = driver.findElement(By.id(appPackage+":id/theme_animals"));
        gameBoard.click();
        WebElement difficultyOption = driver.findElement(By.id(appPackage+":id/select_difficulty_1"));
        difficultyOption.click();
        WebElement pointer = driver.findElement(By.id(appPackage+":id/time_bar_text"));
        assertTrue(pointer.isDisplayed(), "Game board Not displayed");
    }

    @Test
    public void testNumberOfCardsIsDifferent() throws InterruptedException {
        // this method tests if a game has actually different number of cards on different difficulties.
        int beginnerCardCounter,hardestCardCounter;
        // Start the game
        StartGame();
        CardsCounter();
        beginnerCardCounter=CardsCounter();
        System.out.println(beginnerCardCounter);
        Thread.sleep(500);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        WebElement difficultyOption = driver.findElement(By.id(appPackage+":id/select_difficulty_5"));
        difficultyOption.click();
        Thread.sleep(500);
        CardsCounter();
        hardestCardCounter=CardsCounter();
        System.out.println(hardestCardCounter);
        assertTrue(hardestCardCounter>beginnerCardCounter);
    }

    @Test
    public void testGameTimerWorksCorrectly() throws InterruptedException {
        // this method tests if the timer of the game works correctly.
        // Start the game
        StartGame();
        WebElement timer = driver.findElement(By.id(appPackage+":id/time_bar_text"));
        //timer starts with 00:59
        //wait for 10 sec
        Thread.sleep(10000);
        String timerValue = timer.getText();
        assertEquals(timerValue, " 00:49");
    }

    @Test
    public void testCardMatching() throws InterruptedException {
        // this method tests if there are actually 4 cards left after a single match was found.
        boolean Flag= false;
        boolean FlagForWhile=true;
        boolean cardStillExist=true;
        int i=1,count=0;
        // Start the game
        StartGame();
        WebElement card1 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout"));
        WebElement card2 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[2]/android.widget.RelativeLayout"));
        WebElement card3 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.FrameLayout[3]/android.widget.RelativeLayout"));
        WebElement card4 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout[1]/android.widget.RelativeLayout"));
        WebElement card5 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout[2]/android.widget.RelativeLayout"));
        WebElement card6 = driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout[3]/android.widget.RelativeLayout"));

        while (FlagForWhile){
            switch (i)
            {
                case 1:
                    card1.click();
                    card2.click();
                    Thread.sleep(2000);
                    cardStillExist=isElementPresent(card1);
                    if (cardStillExist)
                        System.out.println("1 and 2 are Not a match");
                    else
                    {
                        System.out.println("1 and 2 are a match");
                        Flag= true;
                        break;
                    }
                    break;

                case 2:
                    card1.click();
                    card3.click();
                    Thread.sleep(2000);
                    cardStillExist=isElementPresent(card1);
                    if (cardStillExist)
                        System.out.println("1 and 3 are Not a match");
                    else
                    {
                        System.out.println("1 and 3 are a match");
                        Flag= true;
                        break;
                    }
                    break;

                case 3:
                    card1.click();
                    card4.click();
                    Thread.sleep(2000);
                    cardStillExist=isElementPresent(card1);
                    if (cardStillExist)
                        System.out.println("1 and 4 are Not a match");
                    else
                    {
                        System.out.println("1 and 4 are a match");
                        Flag= true;
                        break;
                    }
                    break;

                case 4:
                    card1.click();
                    card5.click();
                    Thread.sleep(2000);
                    cardStillExist=isElementPresent(card1);
                    if (cardStillExist)
                        System.out.println("1 and 5 are Not a match");
                    else
                    {
                        System.out.println("1 and 5 are a match");
                        Flag= true;
                        break;
                    }
                    break;

                case 5:
                    card1.click();
                    card6.click();
                    Thread.sleep(2000);
                    cardStillExist=isElementPresent(card1);
                    if (cardStillExist)
                        System.out.println("1 and 6 are Not a match");
                    else
                    {
                        System.out.println("1 and 6 are a match");
                        Flag= true;
                        break;
                    }
                    break;
            }
            i++;
            if (Flag)
                FlagForWhile=false;
        }

        Thread.sleep(1500);
        if (isElementPresent(card2))
            count++;
        if (isElementPresent(card3))
            count++;
        if (isElementPresent(card4))
            count++;
        if (isElementPresent(card5))
            count++;
        if (isElementPresent(card6))
            count++;

        assertEquals(count,4);
        assertFalse(cardStillExist);
    }

    @Test
    public void testSoundOff(){
        // this method tests if by pressing the button "sound off" the text is actually displaying.
        WebElement settingsButton = driver.findElement(By.id(appPackage+":id/settings_game_button"));
        settingsButton.click();
        WebElement soundButton = driver.findElement(By.id(appPackage+":id/sound_image"));
        soundButton.click();
        WebElement expectedSound = driver.findElement(By.id(appPackage+":id/sound_off_text"));
        assertEquals(expectedSound.getText(), "Sound OFF");
    }


    @AfterEach
    public void tearDown() {
        // Quit the driver and close the memory game app
        driver.quit();
    }
}
