package commons;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Driver {

    public static WebDriver currentDriver() {
        return getWebDriver();
    }

    public static void open(String url) {
        Selenide.open(url);
    }

    public static void executeJs(String script) {
        JavascriptExecutor js = (JavascriptExecutor)currentDriver();
        try {
            js.executeScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForUrlContains(String urlChunk) {
        WebDriverWait wait = new WebDriverWait(currentDriver(), 10);
        wait.until(ExpectedConditions.urlContains(urlChunk));
    }

    public static void waitForUrlDoesNotContain(String urlChunk) {
        int maxTime = 20;
        while(  currentDriver().getCurrentUrl().contains(urlChunk) && maxTime > 0) {
            wait(1);
            maxTime--;
        }
    }

    public static void maximize() {
        currentDriver().manage().window().maximize();
    }

    public static void changeWindowSize(int width, int height) {
        currentDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static void clearCookies() {
            Selenide.clearBrowserCookies();
            Selenide.clearBrowserLocalStorage();
            executeJs("sessionStorage.clear();");
        }

    public static void close() {
        currentDriver().quit();
    }

    public static void wait(int seconds)
    {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] takeScreenshot() {
        return ((TakesScreenshot) currentDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static List<LogEntry> getBrowserLogs() {
        LogEntries log = currentDriver().manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }

    public static int getResponseStatus() {
        HttpURLConnection connection;
        int responseCode = 0;
        try {
            connection = (HttpURLConnection) new URL(currentDriver().getCurrentUrl()).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            connection.disconnect();
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseCode;
    }

    // COOKIES

    public static void addCookie(Cookie cookie) {
        currentDriver().manage().addCookie(cookie);
    }

    public static Cookie getCookie(String cookieName) {
        return currentDriver().manage().getCookieNamed(cookieName);
    }

    public static void deleteCookie(String cookieName) {
        currentDriver().manage().deleteCookieNamed(cookieName);
    }

}
