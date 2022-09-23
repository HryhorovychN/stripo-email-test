package commons.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import commons.logger.CustomLogger;
import io.qameta.allure.selenide.AllureSelenide;

public class RunnerConfig {

    public void setUpConfig(String browser) {
        boolean localhost = false;

        Configuration.pageLoadStrategy = "eager";
        Configuration.startMaximized = true;
        Configuration.downloadsFolder = "target/build/downloads";
        Configuration.reportsFolder = "target/screenshots";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 10000;
        Configuration.browser = browser;

        if (!localhost) {
//            Configuration.remote = "http://localhost:4444/wd/hub";
//            Configuration.browserCapabilities = new DesiredCapabilities();
//            Configuration.browserCapabilities.setCapability("enableVNC", false);
//            Configuration.browserCapabilities.setCapability("enableVideo", false);
            Configuration.driverManagerEnabled = false;
            if (browser.equals("firefox")) {
                Configuration.browser = FirefoxProvider.class.getName();
            }
            if (browser.equals("chrome")) {
                Configuration.browser = ChromeProvider.class.getName();
            }



        }

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        CustomLogger.logger.info("ok");
    }

}
