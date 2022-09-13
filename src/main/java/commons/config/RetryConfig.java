package commons.config;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static commons.logger.CustomLogger.logger;

public class RetryConfig implements IRetryAnalyzer {
    private int retryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetryCount = 1;
        if (retryCount <= maxRetryCount) {
            if (result.getStatus() == 2) {
                logger.info("Retrying test: " + result.getName());
                retryCount++;
                return true;
            }
        }
        return false;
    }

}
