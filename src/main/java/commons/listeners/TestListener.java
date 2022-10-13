package commons.listeners;

import io.qameta.allure.Allure;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static commons.Driver.browserLog;
import static commons.Driver.getResponseStatus;

@Log4j
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test case: " + result.getName() + " running");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getName() + " passed");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test " + result.getName() + " failed");
        // Allure.addAttachment("Screenshot: ", new ByteArrayInputStream(takeScreenshot()));

        if (getResponseStatus() != 200) {
            Allure.addAttachment("Browser logs: ",
                    "Response code: " + getResponseStatus() + "\n" +
                            "Logs: " + browserLog());
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
