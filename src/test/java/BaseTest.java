
import commons.App;
import commons.Driver;
import commons.config.RunnerConfig;
import commons.listeners.TestListener;
import commons.logger.CustomLogger;
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

    private void testConnection() {
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
        logger.info("Connection success");
    }

    @BeforeClass
    public void setUp() {
        String browser = System.getenv("BROWSER");
        testConnection();
        config.setUpConfig(browser);
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

}
