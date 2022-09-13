package commons.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import commons.logger.CustomLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;

public class RunnerConfig {

    public void setUpConfig(String browser, String browserVersion) {
        boolean modeDebug = false;

        Configuration.pageLoadStrategy = "eager";
        Configuration.startMaximized = true;
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 10000;
        Configuration.browser = browser;
        if (browserVersion != null) {
            Configuration.browserVersion = browserVersion;
        }
        if (!modeDebug) {
            Configuration.remote = "http://localhost:4444/wd/hub";
            Configuration.browserCapabilities.setCapability("enableVNC", false);
            Configuration.browserCapabilities.setCapability("enableVideo", false);
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
        CustomLogger.logger.info("ok");
    }
}
