package commons.listeners;

import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.logging.log4j.core.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;

import static commons.Driver.currentDriver;
import static commons.Driver.getBrowserLogs;
import static commons.Driver.getResponseStatus;
import static commons.Driver.takeScreenshot;

@CommonsLog
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test case: " + result.getName() + " running");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getName() + " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test " + result.getName() + " failed");
       // Allure.addAttachment("Screenshot: ", new ByteArrayInputStream(takeScreenshot()));

        if (!getBrowserLogs().isEmpty()) {
            Allure.addAttachment("Browser logs: ",
                    "Response code: " + getResponseStatus() + "\n" +
                            "Logs: " + String.valueOf(getBrowserLogs()));
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.error("Test " + result.getName() + " skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

}
