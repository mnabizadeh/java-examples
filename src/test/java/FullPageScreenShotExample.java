import com.applitools.eyes.*;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class FullPageScreenShotExample {

    private Eyes eyes = new Eyes();
    private WebDriver driver;
    public static String applitoolsKey = System.getenv("APPLITOOLS_API_KEY");

    @Before
    public void setUp() throws Exception {

        //eyes.setServerUrl("YourApplitoolsServer");
        eyes.setApiKey(applitoolsKey);
        eyes.setHideScrollbars(true);
        //eyes.setForceFullPageScreenshot(true);
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setMatchLevel(MatchLevel.LAYOUT2);
        eyes.setLogHandler(new StdoutLogHandler(true));
        eyes.setAppName("TEST");
        //eyes.setMatchTimeout(5000);

        BatchInfo batch = new BatchInfo("My Fullpage Test");
        eyes.setBatch(batch);

        driver = new ChromeDriver();

        //eyes.setBranchName("MyAwesomeBranchAgain");

        //eyes.setSaveNewTests(false);
    }

    @Test
    public void GithubHomePage() throws Exception {
        driver.get("https://www.github.com/");
        eyes.open(driver, "Github98767344", "Home Page", new RectangleSize(1200, 800));

        driver.findElement(By.tagName("button")).click();

        eyes.checkWindow("github");

        driver.findElement(By.tagName("button")).click();

        TestResults results = eyes.close(false);
        assertEquals(true, results.isPassed());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        eyes.close();
        eyes.abortIfNotClosed();
    }
}