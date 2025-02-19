package demo.locators;

import org.openqa.selenium.By;

public class Locators {

    /*
     * Side Menu related locators
     */

    // Side menu in the homepage
    public static final By SIDE_MENU_ELEMENT = By.xpath("//button[@id='button' and @aria-label='Guide']");

    // Element used to wait until side menu is loaded
    public static final By Wait_FOR_SIDE_MENU = By.xpath("//button[@aria-label='Guide' and @aria-pressed='true']");

    // About link in the side menu
    public static final By ABOUT_ELEMENT = By.xpath("//a[text()='About']");

    // Movie tab in the side menu
    public static final By MOVIE_ELEMENT = By.xpath("//a[@title='Films' or @title='Movies']");

    // Music tab in the side menu
    public static final By MUSIC_ELEMENT = By.xpath("//a[@title='Music']");

    // News tab in the side menu
    public static final By NEWS_ELEMENT = By.xpath("//a[@title='News']");

    /*
     * About Page related locators
     */

    // About Message in the About page
    public static final By ABOUT_MESSAGE_ELEMENT = By.xpath("//section[contains(@class,'ytabout')]");

    /*
     * Movie Page related locators
     */

    // Get All Movie sections (Top Selling, Comedy, etc )
    public static final By ALL_MOVIE_SECTIONS = By.xpath("//div[@id='contents' and contains(@class,'item')]");

    // Title of Section (Movie/ Music section)
    public static final By SECTION_TITLLE = By.xpath(".//div[@id='title-container']//span[@id='title']");

    // Next button - to use this xpath use it with parent element found using
    // ALL_MOVIE_SECTIONS
    public static final By NEXT_BUTTON = By.xpath(".//button[@aria-label='Next']");

    // Individual Movie in the section
    public static final By INDIVIDUAL_MOVIE = By.xpath(".//div[@id='items']//*[contains(@class,'yt-horizontal-list')]");

    // Movie categories ("Comedy","Animation", "Drama") -elements
    public static final By MOVIE_CATEGORY = By.xpath(".//span[contains(@class,'grid-movie-renderer-metadata')]");

    // Movie Age ratings ('A','U/A','U', etc) - elements
    public static final By MOVIE_AGE_RATING = By.xpath(".//p[not(contains(text(),'Buy'))]");

    /*
     * Music Page related locators
     */

    // Get All Music sections (India's Biggest Hits, Discover New Music, etc )
    public static final By ALL_MUSIC_SECTIONS = By.xpath("//div[@id='content' and contains(@class,'rich-section')]");

    // Show more button of Music page
    public static final By SHOW_MORE_BUTTON = By.xpath(".//button[@aria-label='Show more']");

    // Individual Music playList in the section
    public static final By INDIVIDUAL_MUSIC_PLAYLIST = By.xpath(".//div[@id='contents']//*[contains(@class,'shelf')]");

    // Individual Music playlist title - to use this Xpath need parent element
    // Individual playlist
    public static final By PLAYLIST_TITLE = By.xpath(".//span[@role='text' and  not(contains(@class,'metadata'))]");

    // No of songs of Individual Music playlist - to use this Xpath need parent
    // element Individual playlist
    public static final By PLAYLIST_NO_OF_SONGS = By.xpath(".//div[@class='badge-shape-wiz__text']");

    // Wait for Music page to load
    public static final By MUSIC_PAGE_WAIT = By.xpath("//div[@id='header']//*[@id='title' and text()='Music']");

    /*
     * News Page related locators
     */

    // Latest News posts - parent elements contains all latest news posts
    public static final By ALL_LATEST_NEWS_POSTS = By
            .xpath("//span[ text()='Latest news posts']//ancestor::div[@id='content' and contains(@class, 'section')]");

    // To get all the individual posts - child ele for ALL_LATEST_NEWS_POSTS parent
    // element
    public static final By INDIVIDUAL_NEWS_POST = By.xpath(".//div[@id='content' and contains(@class, 'rich-item')]");

    // To gets the news title - child ele for INDIVIDUAL_NEWS_POST element
    public static final By NEWS_TITLE_ELEMENT = By.xpath(".//div[@id='header']//a//span");

    // To gets the news body/content - child ele for INDIVIDUAL_NEWS_POST element
    public static final By NEWS_BODY_ELEMENT = By.xpath(".//div[@id='body']//*[@id='home-content-text']");

    // To get the like count - child ele for INDIVIDUAL_NEWS_POST element
    public static final By LIKE_COUNT_ELEMENT = By.xpath(".//span[@id='vote-count-middle']");

    /*
     * Search related locators
     */

    //youTube search box element
    public static final By SEARCH_BOX_ELEMENT = By.xpath("//input[@name='search_query']");

    //Search results' view count 
    public static final By VIEW_COUNT_ELEMENT = By.xpath("//div[@id='metadata-line']//span[contains(text(), 'views')]");

}
