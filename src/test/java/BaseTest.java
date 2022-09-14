
import com.codeborne.selenide.WebDriverProvider;
import commons.App;
import commons.Driver;
import commons.config.CustomProvider;
import commons.config.RunnerConfig;
import commons.listeners.TestListener;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@CommonsLog
public class BaseTest extends TestListener {

    protected App app;
    protected SoftAssert softAssert;
    protected Logger logger;
    protected final RunnerConfig config = new RunnerConfig();


    @Parameters({"browser", "browserVersion"})
    @BeforeClass
    public void setUp(@Optional("Chrome") String browser, @Optional String browserVersion) {
        try {
            HttpURLConnection connection;
            connection = (HttpURLConnection) new URL(App.STAGING_BASE_URL).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.error(connection.getResponseMessage() + connection.getErrorStream());
                connection.disconnect();
                Driver.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setUpConfig(browser, browserVersion, getBrowserProvider(browser));
        app = new App();
        softAssert = new SoftAssert();
        logger = LogManager.getLogger("");

        DOMConfigurator.configure("src/main/resources/log4j.xml");
    }


    @AfterMethod
    public void clearCookie() {
        Driver.clearCookies();
    }


    @AfterClass
    public void tearDown() {
        Driver.clearCookies();
        Driver.close();
    }

    private Class<? extends WebDriverProvider> getBrowserProvider(String browserName) {
        switch (browserName) {
            case "Chrome":
                return CustomProvider.class;

            default: return CustomProvider.class;
        }

    }

}
