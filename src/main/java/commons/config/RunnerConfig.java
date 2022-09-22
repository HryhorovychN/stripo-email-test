package commons.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import commons.logger.CustomLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public class RunnerConfig {


    public void setUpConfig(String browser, String browserVersion) {
        //remote == false
        boolean locale = false;

        Configuration.pageLoadStrategy = "eager";
        Configuration.startMaximized = true;
        Configuration.downloadsFolder = "target/build/downloads";
        Configuration.reportsFolder = "target/screenshots";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 10000;
        Configuration.browser = browser;

        if (!locale) {
            if (browserVersion != null) {
                Configuration.browserVersion = browserVersion;
            }
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            Configuration.browserCapabilities = desiredCapabilities;
            Configuration.browserCapabilities.setCapability("enableVNC", false);
            Configuration.browserCapabilities.setCapability("enableVideo", false);
            desiredCapabilities.setBrowserName(browser);
            desiredCapabilities.setAcceptInsecureCerts(true);
            try {
                new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);
                return;
            } catch (final MalformedURLException e) {
                throw new RuntimeException("Unable to create driver", e);
            }

        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        CustomLogger.logger.info("ok");
    }
}
