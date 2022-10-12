package commons.config;

import lombok.extern.apachecommons.CommonsLog;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@CommonsLog
public class RetryConfig implements IRetryAnalyzer {
    private int retryCount = 1;

    @Override
    public boolean retry(ITestResult result) {
        int maxRetryCount = 1;
        if (retryCount <= maxRetryCount) {
            if (result.getStatus() == 2) {
                log.info("Retrying test: " + result.getName());
                retryCount++;
                return true;
            }
        }
        return false;
    }

}
