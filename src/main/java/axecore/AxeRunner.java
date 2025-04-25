package axecore;

import com.deque.html.axecore.selenium.AxeBuilder;
import com.deque.html.axecore.selenium.AxeBuilderOptions;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class AxeRunner {

    AxeBuilder axeBuilder;
    WebDriver  driver;

    public AxeRunner(WebDriver driver){
        this.driver = driver;
        this.axeBuilder = new AxeBuilder();
    }

    public AxeRunner(WebDriver driver, AxeBuilderOptions axeBuilderOptions){
        this.driver = driver;
        this.axeBuilder = new AxeBuilder(axeBuilderOptions);
    }

    public AxeRunnerResults analyze(){
        return new AxeRunnerResults(axeBuilder.analyze(this.driver));
    }

    public AxeRunnerResults analyze(WebDriver driver){
        return new AxeRunnerResults(axeBuilder.analyze(driver));
    }
    public AxeRunnerResults analyze(WebDriver driver, WebElement webElement){
        return  new AxeRunnerResults(axeBuilder.analyze(driver, webElement));
    }

    public AxeRunnerResults analyze(WebElement webElement){
        return  new AxeRunnerResults(axeBuilder.analyze(driver, webElement));
    }

    public AxeRunner includeSelectors(List<String> selectors){
        this.axeBuilder.include(selectors);
        return this;
    }

    public AxeRunner includeSelector(String selector){
        this.axeBuilder.include(selector);
        return this;
    }

    public AxeRunner excludeSelectors(List<String> selectors){
        this.axeBuilder.exclude(selectors);
        return this;
    }

    public AxeRunner excludeSelector(String selector){
        this.axeBuilder.exclude(selector);
        return this;
    }

    public AxeRunner limitToRules(List<String> rules){
        this.axeBuilder.withRules(rules);
        return this;
    }

    public AxeRunner disableRules(List<String> rules){
        this.axeBuilder.disableRules(rules);
        return this;
    }

    public AxeRunner limitToTags(List<String> tags){
        this.axeBuilder.withTags(tags);
        return this;
    }


    public AxeRunner disableIframe(){
        this.axeBuilder.disableIframeTesting();
        return this;
    }

}
