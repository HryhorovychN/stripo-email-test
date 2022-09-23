package commons.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import static commons.logger.CustomLogger.logger;

public class RunnerConfig {

    public void setUpConfig(String browser) {
        boolean modeDebug = false;

        Configuration.pageLoadStrategy = "eager";
        Configuration.startMaximized = true;
        Configuration.downloadsFolder = "target/build/downloads";
        Configuration.reportsFolder = "target/screenshots";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 10000;
        Configuration.browser = browser;

        if (!modeDebug) {
//            Configuration.remote = "http://localhost:4444/wd/hub";
//            Configuration.browserCapabilities = new DesiredCapabilities();
//            Configuration.browserCapabilities.setCapability("enableVNC", false);
//            Configuration.browserCapabilities.setCapability("enableVideo", false);
            Configuration.driverManagerEnabled = false;
            getProvider(browser);
            Configuration.browser = FirefoxProvider.class.getName();
            logger.info("Remote browser is starting");
        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
    }

    private void getProvider(String browser) {
        switch (browser) {
            case "firefox":
                Configuration.browser = FirefoxProvider.class.getName();
            case "chrome":
                Configuration.browser = ChromeProvider.class.getName();
        }
    }
}
