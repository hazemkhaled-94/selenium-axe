package pages;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.selenium.AxeBuilder;
import lombok.NonNull;
import org.openqa.selenium.WebDriver;


public class Page extends BasePage{

    private AxeBuilder axeBuilder;

    public Page(@NonNull WebDriver driver) {
        super(driver);
        this.axeBuilder = new AxeBuilder();
    }

    public void initialitzeAxeBuilder(){

    }

    public void disableIframe(){
        this.axeBuilder.disableIframeTesting();
    }

    public Results analyseWebPage(){
        return this.axeBuilder.analyze(driver);
    }


}
