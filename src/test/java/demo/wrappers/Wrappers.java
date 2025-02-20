package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import demo.locators.Locators;

import java.security.KeyStore.LoadStoreParameter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Wrappers {
    public static final String HOME_PAGE = "https://www.youtube.com/";
    public static final int MAX_WAIT_TIME = 30;
    public final WebDriver driver;
    public Actions action;
    public WebDriverWait wait;


    // Constructor
    public Wrappers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT_TIME));
        this.action = new Actions(driver);
    }

    // To print Start and End testcase logs
    public void logStatus(String description, String testCaseID) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s| %s | %s", timeStamp, description, testCaseID));
    }

    // To print Error and exceptions
    public void logErrors(String errorDescription, String errorMessage, By locator) {
        String timeStamp = String.valueOf(java.time.LocalDateTime.now());
        System.out.println(String.format("%s| %s | %s", timeStamp, errorDescription, locator));
        System.out.println(String.format("Error: %s", errorMessage));
    }

    // To open Url
    public void NavigateTo(String url) {
        driver.get(url);
    }

    // To get current url
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // To scroll to element
    public void scrollToView(WebElement element, By locator) {
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        } catch (Exception e) {
            logErrors("Failed to scroll to element", e.getMessage(), locator);
            e.printStackTrace();
        }

    }

    // To scroll and click on the element By locator
    public void clickOnElement(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            scrollToView(element, locator);
            element.click();

        } catch (Exception e) {
            logErrors("Failed to click on the element By locator", e.getMessage(), locator);
            e.printStackTrace();

        }
    }

    // To scroll and click on the element by WebElement
    public void clickOnElement(WebElement ele) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ele));
            scrollToView(element, null);
            element.click();

        } catch (Exception e) {
            logErrors("Failed to click on the element by element", e.getMessage(), null);
            e.printStackTrace();
        }
    }

    // To open side menu and wait for it to load
    public void openSideMenu(By locator) {
        try {
            // Click on side menu
            clickOnElement(locator);

            // Wait for side menu to open
            wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.Wait_FOR_SIDE_MENU));

        } catch (Exception e) {
            logErrors("Failed to open side menu", e.getMessage(), Locators.Wait_FOR_SIDE_MENU);
            e.printStackTrace();
        }

    }

    // To open open About page and wait for it to load
    public void openAboutPage(By locator) {
        try {
            // Click on About link
            clickOnElement(locator);

            // Wait for About page to load
            wait.until(driver -> getCurrentUrl().toLowerCase().contains("about"));

        } catch (Exception e) {
            logErrors("Failed to click on 'About' link", e.getMessage(), locator);
            e.printStackTrace();
        }

    }

    // To open open Movie page and wait for it to load
    public void openMovieTab(By locator) {
        try {
            // Click on Movie link
            clickOnElement(locator);

            // Wait for Movie page to load
            wait.until(driver -> getCurrentUrl().toLowerCase().contains("storefront"));

        } catch (Exception e) {
            logErrors("Failed to click on 'Movies' or 'Films' tab", e.getMessage(), locator);
            e.printStackTrace();
        }

    }

    // To open open Music page and wait for it to load
    public void openMusicTab(By locator) {
        try {
            // Click on Music link
            clickOnElement(locator);

            // Wait for Music page to load
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators.ALL_MUSIC_SECTIONS));

            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.ALL_MUSIC_SECTIONS));

        } catch (Exception e) {
            logErrors("Failed to click on 'Music' tab", e.getMessage(), locator);
            e.printStackTrace();
        }

    }

    // To open open News page and wait for it to load
    public void openNewsTab(By locator) {
        try {
            // Click on Music link
            clickOnElement(locator);

            // Wait for Music page to load
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.ALL_LATEST_NEWS_POSTS));

        } catch (Exception e) {
            logErrors("Failed to click on 'News' tab", e.getMessage(), locator);
            e.printStackTrace();
        }

    }

    // To print About message
    public void printAboutMessage() {

        WebElement aboutMessageElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(Locators.ABOUT_MESSAGE_ELEMENT));
        scrollToView(aboutMessageElement, Locators.ABOUT_MESSAGE_ELEMENT);

        try {
            System.out.println(String.format("The About message of YouTube is: \n%s", aboutMessageElement.getText()));

        } catch (Exception e) {
            logErrors("Faild to print About message", e.getMessage(), Locators.ABOUT_MESSAGE_ELEMENT);
            e.printStackTrace();
        }

    }

    // To fetch all Movie sections displayed
    public List<WebElement> getAllMovieSections() {
        try {
            List<WebElement> elements = wait
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.ALL_MOVIE_SECTIONS));
            return elements;

        } catch (Exception e) {
            logErrors("Failed to get all the movie sections", e.getMessage(), Locators.ALL_MOVIE_SECTIONS);
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    // To fetch all Music sections displayed
    public List<WebElement> getAllMusicSections() {
        try {
            List<WebElement> elements = wait
                    .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.ALL_MUSIC_SECTIONS));
            return elements;

        } catch (Exception e) {
            logErrors("Failed to get all the Music sections", e.getMessage(), Locators.ALL_MUSIC_SECTIONS);
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    // Returns a movie/music section's element which has given title
    public WebElement selectSection(String sectionTitleToGet, List<WebElement> allSections) {

        for (WebElement sectionElement : allSections) {
            try {
                WebElement sectionTitleElement = wait.until(ExpectedConditions
                        .visibilityOf(sectionElement.findElement(Locators.SECTION_TITLLE)));

                String sectionTitle = sectionTitleElement.getText();

                if (sectionTitle.contains(sectionTitleToGet)) {
                    return sectionElement;
                }

            } catch (Exception e) {
                logErrors("Failed to get section's title element", e.getMessage(),
                        Locators.SECTION_TITLLE);
                e.printStackTrace();
            }
        }
        // If no movie/music section found with given title return null
        System.out.println("No section found for title " + sectionTitleToGet);
        return null;

    }

    // Clicks on next button until there is no new content
    public void clickOnNextBtn(String movieSection) {
        // movieSectionElement is parent element which contains all the elements of that
        // movie section
        List<WebElement> allMovieSections = getAllMovieSections();
        WebElement movieSectionElement = selectSection(movieSection, allMovieSections);

        try {
            if (movieSectionElement != null) {
                WebElement nextButtonElement = wait.until(
                        ExpectedConditions.elementToBeClickable(movieSectionElement.findElement(Locators.NEXT_BUTTON)));

                // Clicks until next btn goes invisible
                while (nextButtonElement.isDisplayed()) {
                    clickOnElement(nextButtonElement);
                }

            }
        } catch (Exception e) {
            logErrors("Failed to click on next button", e.getMessage(), Locators.NEXT_BUTTON);
            e.printStackTrace();
        }

    }

    // Checks if last movie of the given movie section has age rating 'A'
    public boolean isLastMovieRatedAs(String ageRating, String movieSection) {
        // movieSectionEle is parent element which contains all the elements of that
        // movie section

        List<WebElement> allMovieSections = getAllMovieSections();
        WebElement movieSectionEle = selectSection(movieSection, allMovieSections);
        try {
            if (movieSectionEle != null) {
                // Get all Movie age rating elements
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators.MOVIE_AGE_RATING));
                List<WebElement> ageRatingElements = movieSectionEle.findElements(Locators.MOVIE_AGE_RATING);

                // Get the age rating for last movie
                WebElement ageRatingOfLastMovie = ageRatingElements.get(ageRatingElements.size() - 1);

                // System.out.println("ageRating : " + ageRatingOfLastMovie.getText());
                // System.out.println("Age rating is A? " + ageRatingOfLastMovie.getText().equals(ageRating));

                // return true if age rating of last movie is 'A'
                return ageRatingOfLastMovie.getText().trim().equals(ageRating);

            } else {
                System.out.println("No movie section found for title " + movieSection);
                return false;
            }

        } catch (Exception e) {
            logErrors("Failed when checking is the last movie from '" + movieSection + "' section rated as '"
                    + ageRating + "'", e.getMessage(), Locators.MOVIE_AGE_RATING);
            e.printStackTrace();
            return false;
        }

    }

    // Checks if the movie category exists 
    public boolean isLastMovieCategoryExists(String movieSectionToGet) {

        List<WebElement> allMovieSections = getAllMovieSections();
        WebElement movieSectionElement = selectSection(movieSectionToGet, allMovieSections);
        try {
            if (movieSectionElement != null) {
                // Gets all movie category elements
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators.MOVIE_CATEGORY));
                List<WebElement> movieCategoryElements = movieSectionElement.findElements(Locators.MOVIE_CATEGORY);

                // Get the movie category element of the last movie
                WebElement movieCategoryOfLastMovie = movieCategoryElements.get(movieCategoryElements.size() - 1);

                // System.out.println("movieCategoryOfLastMovie displayed? " + movieCategoryOfLastMovie.isDisplayed());

                // return true if movie category of last movie is displayed
                return movieCategoryOfLastMovie.isDisplayed();

            } else {
                System.out.println("No movie section found for title " + movieSectionToGet);
                return false;
            }
        } catch (Exception e) {
            logErrors("Error while checking is the last movie from '" + movieSectionToGet
                    + "' has movieCategory displayed", e.getMessage(), Locators.MOVIE_CATEGORY);
            e.printStackTrace();
            return false;
        }

    }

    // Print title of last playList from the 1st section of Music page
    public void printTitleOfLastPlayList() {
        List<WebElement> allMusicSection = getAllMusicSections();
        WebElement firstSection = allMusicSection.get(0);

        try {
            // Click on 'Show More' button of first section
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators.SHOW_MORE_BUTTON));
            WebElement showMoreBtn = firstSection.findElement(Locators.SHOW_MORE_BUTTON);
            showMoreBtn.click();

            // Get elements of all the playlist present in 1st section
            List<WebElement> playlistElements = wait.until(ExpectedConditions
                    .visibilityOfAllElements(firstSection.findElements(Locators.INDIVIDUAL_MUSIC_PLAYLIST)));

            // Element of last playlist of 1st section
            WebElement lastPlaylistEle = playlistElements.get(playlistElements.size() - 1);

            // Title of last playlist in first section
            wait.until(ExpectedConditions.visibilityOf(lastPlaylistEle.findElement(Locators.PLAYLIST_TITLE)));
            WebElement lastPlaylistTitleEle = lastPlaylistEle.findElement(Locators.PLAYLIST_TITLE);

            String lastPlaylistTitle = lastPlaylistTitleEle.getText();
            System.out.println("The title of the last playlist in the 1st section of Music page is: " + lastPlaylistTitle);

        } catch (Exception e) {
            logErrors("Failed while tring to print the last playlist's title in first section of Music page",
                    e.getMessage(), Locators.PLAYLIST_TITLE);
            e.printStackTrace();
        }

    }

    // Checks if no of songs listed <=50 in last playlist of 1st section on Music page
    public boolean checkNoOfSongs() {
        List<WebElement> allMusicSection = getAllMusicSections();
        WebElement firstSection = allMusicSection.get(0);

        try {
            // Get elements of all the playlist present in 1st section
            List<WebElement> playlistElements = wait.until(ExpectedConditions
                    .visibilityOfAllElements(firstSection.findElements(Locators.INDIVIDUAL_MUSIC_PLAYLIST)));

            // Element of last playlist of 1st section
            WebElement lastPlaylistEle = playlistElements.get(playlistElements.size() - 1);

            // Element for number of songs in playlist
            WebElement noOfSongsElement = wait
                    .until(ExpectedConditions.visibilityOf(lastPlaylistEle.findElement(Locators.PLAYLIST_NO_OF_SONGS)));

            int noOfSongs = Integer.parseInt(noOfSongsElement.getText().replaceAll("[^[0-9]]", ""));

            return (noOfSongs <= 50);

        } catch (Exception e) {
            logErrors("Failed while verifying number of songs in last playlist is <=50", e.getMessage(),
                    Locators.INDIVIDUAL_MUSIC_PLAYLIST);
            e.printStackTrace();
            return false;
        }

    }


    //Gets all Individual Posts under 'Latest news posts' section
    public List<WebElement> getAllNewsPosts(){
        try {
            WebElement allLatestPosts = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.ALL_LATEST_NEWS_POSTS));

            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Locators.INDIVIDUAL_NEWS_POST));
            List<WebElement> individualLatestPost = allLatestPosts.findElements(Locators.INDIVIDUAL_NEWS_POST);

            return individualLatestPost;
            
        } catch (Exception e) {
            logErrors("Failed to get all News post from 'Latest news posts section'", e.getMessage(),Locators.INDIVIDUAL_NEWS_POST);
            e.printStackTrace();
            return null;
        }

    }




    //Prints News Title, Body and sum of likes of 1st 3 "Latest News Posts" 
    public void printTitleBodySumOfLikes(){

        List<WebElement> allNewsPostsEle = getAllNewsPosts();
        int totalLikesCount =0;

        for(int i=0; i<3; i++){
            WebElement newsPostElement = allNewsPostsEle.get(i);
            try {
                WebElement newsTitleElement = wait.until(ExpectedConditions.visibilityOf(newsPostElement.findElement(Locators.NEWS_TITLE_ELEMENT)));
                WebElement newsBodyElement = wait.until(ExpectedConditions.visibilityOf(newsPostElement.findElement(Locators.NEWS_BODY_ELEMENT)));
                WebElement newsLikeCount = wait.until(ExpectedConditions.visibilityOf(newsPostElement.findElement(Locators.LIKE_COUNT_ELEMENT)));

                int likeCount = Integer.parseInt(newsLikeCount.getText().replaceAll("[^0-9]", ""));
                totalLikesCount += likeCount;

                System.out.println(String.format((i+1)+". The Title of News post is: %s \nThe Body of the News post is: %s",newsTitleElement.getText(),newsBodyElement.getText()));

            } catch (Exception e) {
                logErrors("Failed to print News Title and Body", e.getMessage(),Locators.NEWS_BODY_ELEMENT);
                e.printStackTrace();
            }
        }

        System.out.println("The sum of likes of 1st 3 'Latest News Posts' is: "+totalLikesCount);
    }


    //Search in youTube
    public void youTubeSearch(String toBeSearched){
        try {
            WebElement searchBoxElement = wait.until(ExpectedConditions.elementToBeClickable(Locators.SEARCH_BOX_ELEMENT));
            searchBoxElement.clear();
            searchBoxElement.sendKeys(toBeSearched,Keys.ENTER);
            
        } catch (Exception e) {
            logErrors("Failed while trying to search for '"+toBeSearched+"'", e.getMessage(),Locators.SEARCH_BOX_ELEMENT);
            e.printStackTrace();
        }

    }

    //Converts viewCount text into long
    public long convertViewsToNumber(String viewCountText){
        double billion = 1000000000;
        double million = 1000000;
        double thousand = 1000;
        double number;

        if(viewCountText.endsWith("K")){
            number= Double.parseDouble(viewCountText.replaceAll("[^0-9.]", "")) * thousand;
        }else if(viewCountText.endsWith("M")){
            number= Double.parseDouble(viewCountText.replaceAll("[^0-9.]", "")) * million;
        }else if(viewCountText.endsWith("B")){
            number= Double.parseDouble(viewCountText.replaceAll("[^0-9.]", "")) * billion;
        }else{
            number = Double.parseDouble(viewCountText);
        }

        return (long) number;
    }
    

    //Keeps scrolling and adding view counts until it reach 10Cr
    public void scrollUntilViewCount(long tenCr){
        long viewCount = 0;
        long totalViewCount = 0;

        try {

            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.VIEW_COUNT_ELEMENT));
            //Scroll to end so the element will load
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            List<WebElement> viewCountElements = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locators.VIEW_COUNT_ELEMENT)));

            for(WebElement element: viewCountElements){
                scrollToView(element, null);

                String viewCountText = element.getText().replace("views", "").trim();
                viewCount = convertViewsToNumber(viewCountText);
                totalViewCount += viewCount;
                // System.out.println("viewCountText "+ viewCountText);
                // System.out.println("Current viewCount: "+viewCount+" totalViewCount: "+totalViewCount);

                if(totalViewCount>=tenCr){
                    System.out.println("Condition statisfied the view count reached: "+totalViewCount);
                    // System.out.println("totalViewCount>=scrollTillViewCount: "+(totalViewCount>=tenCr));
                    break;
                }

            }
   
        } catch (Exception e) {
            logErrors("Failed to scroll till sum of view count reaches 10cr", e.getMessage(), Locators.VIEW_COUNT_ELEMENT);
            e.printStackTrace();
        }

    }




}