package commons.listeners;

import commons.config.RetryConfig;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer analyzer = annotation.getRetryAnalyzer();
        if (analyzer != null) {
            annotation.setRetryAnalyzer(RetryConfig.class);
        }
    }
}
