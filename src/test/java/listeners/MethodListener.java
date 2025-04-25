package listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

@Slf4j
public class MethodListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result){
        log.info(String.format("Finishing method: %s", method.getTestMethod().getMethodName()));
    }
    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result){
        log.info(String.format("Starting method: %s", method.getTestMethod().getMethodName()));
    }
}
