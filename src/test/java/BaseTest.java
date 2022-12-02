
import commons.App;
import commons.Driver;
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
    protected final RunnerConfig config = new RunnerConfig();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private void testConnections() {
        try {
            HttpURLConnection connection;
            connection = (HttpURLConnection) new URL(App.baseUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                log.error("Connection status: " + connection.getURL()+ " " + connection.getResponseMessage());
                System.out.println(ANSI_CYAN + "Connection status: " + connection.getURL() + " " + connection.getResponseMessage() + "!" + ANSI_RESET);
                connection.disconnect();
                Driver.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Parameters({"BROWSER"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        testConnections();
        config.setUpConfig(browser);
        app = new App();
        softAssert = new SoftAssert();

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
