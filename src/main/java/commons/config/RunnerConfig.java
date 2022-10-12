package commons.config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.LogEventListener;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.apachecommons.CommonsLog;

import static commons.Driver.getBrowserLogs;

@CommonsLog
public class RunnerConfig {

    public void setUpConfig(String browser) {
        boolean modeDebug = false;

        Configuration.pageLoadStrategy = "eager";
        Configuration.startMaximized = true;
        Configuration.downloadsFolder = "target/build/downloads";
        Configuration.reportsFolder = "target/screenshots";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.timeout = 13000;
        Configuration.browser = "chrome";

        if (!modeDebug) {
            Configuration.driverManagerEnabled = false;
            getProvider(browser);
            log.info("Remote browser is starting");
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
