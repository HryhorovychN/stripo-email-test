package commons.config;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeProvider implements WebDriverProvider {

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.merge(capabilities);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        try {
            RemoteWebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
            webDriver.setFileDetector(new LocalFileDetector());
            return webDriver;
        } catch (final MalformedURLException e) {
            throw new RuntimeException("Unable to create driver", e);
        }
    }
}