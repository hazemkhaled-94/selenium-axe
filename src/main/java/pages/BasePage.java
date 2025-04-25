package pages;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;

@RequiredArgsConstructor
public abstract class BasePage {

    @NonNull
    protected WebDriver driver;


}
