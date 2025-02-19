package demo;

// import org.apache.poi.ss.formula.PlainCellCache.Loc;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.PrintStream;
import java.time.Duration;
import java.util.logging.Level;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import demo.locators.Locators;
import demo.utils.*;

public class TestCases extends ExcelDataProvider { // Lets us read the data
        ChromeDriver driver;
        Wrappers wrapper;
        WebDriverWait wait;

        // Test(Implementation)
        /*
         * testCase01: Go to YouTube.com and Assert you are on the correct URL. Click on
         * "About" at the bottom of the sidebar, and print the message on the screen.
         */

        @Test
        public void testCase01() {
                wrapper.logStatus("Start TestCase", "testCase01");

                // Go to YouTube.com
                wrapper.NavigateTo(Wrappers.HOME_PAGE);

                // Assert url
                Assert.assertEquals(wrapper.getCurrentUrl(), Wrappers.HOME_PAGE, "The url is not matching");

                // Open side menu
                wrapper.openSideMenu(Locators.SIDE_MENU_ELEMENT);

                // Click on About link in the side menu
                wrapper.openAboutPage(Locators.ABOUT_ELEMENT);

                // Print the about message from the about page
                wrapper.printAboutMessage();

                wrapper.logStatus("End TestCase", "testCase01");
        }

        /*
         * testCase02: Go to the "Films" or "Movies" tab and in the 'Top Selling'
         * section, scroll to the extreme right. Apply a Soft Assert on whether the
         * movie is marked 'A' for Mature or not. Apply a Soft assert on the movie
         * category to check if it exists ex: "Comedy", "Animation", "Drama".
         */

        @Test
        public void testCase02() {
                wrapper.logStatus("Start TestCase", "testCase02");

                // Go to YouTube.com
                wrapper.NavigateTo(Wrappers.HOME_PAGE);

                // Open side menu
                wrapper.openSideMenu(Locators.SIDE_MENU_ELEMENT);

                // Click on "Films" or "Movies" tab in the side menu
                wrapper.openMovieTab(Locators.MOVIE_ELEMENT);

                String movieSection = "Top selling";
                // Click on 'Next' arrow button until it dissapears
                wrapper.clickOnNextBtn(movieSection);

                SoftAssert softAssert = new SoftAssert();

                // Check whether the movie is rated as 'A' or not for last movie in the section
                softAssert.assertEquals(wrapper.isLastMovieRatedAs("A", movieSection), true,
                                "The Movie does not have age rating of 'A'");

                // Check whether the movie category exists Eg: "Comedy", "Animation" or "Drama"
                softAssert.assertTrue(wrapper.isLastMovieCategoryExists(movieSection),
                                "The Movie Category is not displayed");

                // softAssert.assertAll();

                wrapper.logStatus("End TestCase", "testCase02");
        }

        /*
         * testCase03: Go to the "Music" tab and in the 1st section, scroll to the
         * extreme right. Print the name of the playlist. Soft Assert on whether the
         * number of songs listed is less than or equal to 50.
         */

        @Test
        public void testCase03() {
                wrapper.logStatus("Start TestCase", "testCase03");

                // Go to YouTube.com
                wrapper.NavigateTo(Wrappers.HOME_PAGE);

                // Open side menu
                wrapper.openSideMenu(Locators.SIDE_MENU_ELEMENT);

                // Click on "Music" tab in the side menu
                wrapper.openMusicTab(Locators.MUSIC_ELEMENT);

                // In first section print the title of last playlist
                wrapper.printTitleOfLastPlayList();

                // Check Whether number of songs listed is <=50 in last playlist
                SoftAssert softAssert = new SoftAssert();
                softAssert.assertTrue(wrapper.checkNoOfSongs(), "The number of songs in the playlist is not <=50");

                // softAssert.assertAll();

                wrapper.logStatus("End TestCase", "testCase03");

        }

        /*
         * testCase04: Go to the "News" tab and print the title and body of the 1st 3
         * "Latest News Posts" along with the sum of the number of likes on all 3 of
         * them. No likes given means 0.
         */

        @Test
        public void testCase04() {
                wrapper.logStatus("Start TestCase", "testCase04");

                // Go to YouTube.com
                wrapper.NavigateTo(Wrappers.HOME_PAGE);

                // Open side menu
                wrapper.openSideMenu(Locators.SIDE_MENU_ELEMENT);

                // Click on "News" tab in the side menu
                wrapper.openNewsTab(Locators.NEWS_ELEMENT);

                // Print the title, body and sum of likes of 1st 3 "Latest News Posts"
                wrapper.printTitleBodySumOfLikes();

                wrapper.logStatus("End TestCase", "testCase04");
        }

        /*
         * testCase05: Search for each of the items given in the stubs:
         * src/test/resources/data.xlsx, and keep scrolling till the sum of each video’s
         * views reach 10 Cr.
         */

        @Test(dataProvider = "excelData", dataProviderClass = demo.utils.ExcelDataProvider.class)
        public void testCase05(String toBeSearched) {
                wrapper.logStatus("Start TestCase", "testCase05");

                // Go to YouTube.com
                wrapper.NavigateTo(Wrappers.HOME_PAGE);

                //Search for given text 'toBeSearched'
                wrapper.youTubeSearch(toBeSearched);

                long tenCr = 100000000;
                //Scroll until total view count reaches 10Cr
                wrapper.scrollUntilViewCount(tenCr);

                wrapper.logStatus("End TestCase", "testCase05");

        }

        // Set-up
        @BeforeTest
        public void startBrowser() {
                System.setProperty("java.util.logging.config.file", "logging.properties");

                // NOT NEEDED FOR SELENIUM MANAGER
                // WebDriverManager.chromedriver().timeout(30).setup();

                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logs = new LoggingPreferences();

                logs.enable(LogType.BROWSER, Level.ALL);
                logs.enable(LogType.DRIVER, Level.ALL);
                options.setCapability("goog:loggingPrefs", logs);
                options.addArguments("--remote-allow-origins=*");

                System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

                driver = new ChromeDriver(options);

                driver.manage().window().maximize();
                this.wrapper = new Wrappers(driver);
                this.wait = new WebDriverWait(driver, Duration.ofSeconds(Wrappers.MAX_WAIT_TIME));
        }

        // Tear down
        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}