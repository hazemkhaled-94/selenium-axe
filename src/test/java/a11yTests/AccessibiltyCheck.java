package a11yTests;

import axecore.AxeRunner;
import axecore.AxeRunnerResults;
import base.BaseTest;
import com.deque.html.axecore.results.Rule;
import dataProviders.TestData;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Epic("Accessibility Check")
public class AccessibiltyCheck extends BaseTest {



    @Test(testName = "Axe-Core Scan", suiteName = "A11y Checks", dataProvider = "urls", dataProviderClass = TestData.class, priority = 0)
    @Story("A11y Scan")
    @Description("Run automated a11y checks against the given set of websites.")
    @Tags({
            @Tag("a11y"),
            @Tag("wcag2a"),
            @Tag("wcag2aa"),
            @Tag("wcag21a"),
            @Tag("wcag21aa"),
            @Tag("wcag22aa")
    })
    @Parameters({"Tested url"})
    public void runAccessibilityCheck(String url) {
        driver.get(url);
        Allure.link("Click here to open the tested url", url);
        List<String> tags = Arrays.asList("wcag22aa", "wcag21a", "wcag21aa", "wcag2a", "wcag2aa");

        AxeRunner axeRunner = new AxeRunner(driver);
        axeRunner.limitToTags(tags);

        log.info("Test is starting...");
        AxeRunnerResults results = axeRunner.analyze(driver);
        log.info("Test finished successfully. Publishing reports...");
        reportPassedChecks(results.getPasses());
        reportIncompleteChecks(results.getIncomplete());
        reportInapplicable(results.getInapplicable());
        reportViolations(results.getViolations());
        log.info("Reports published successfully");
    }

    @Step("Passed Checks")
    @Parameters({"Passed Rules"})
    public void reportPassedChecks(List<Rule> passes){
        reportRules(passes);
        Assert.assertFalse(passes.isEmpty(), "No Passed Checks found!");
    }

    @Step("Violated Checks")
    @Parameters({"Violated Rules"})
    public void reportViolations(List<Rule> violations){
        reportRules(violations);
        Assert.assertTrue(violations.isEmpty(), "Violations found!");
    }

    @Step("Incomplete Checks")
    @Parameters({"Incomplete Rules"})
    public void reportIncompleteChecks(List<Rule> incompletes){
        reportRules(incompletes);
    }

    @Step("Inapplicable Checks")
    @Parameters({"Inapplicable Rules"})
    public void reportInapplicable(List<Rule> inapplicables){
        reportRules(inapplicables);
    }


    private void reportRules(List<Rule> rules) {
        for(Rule rule : rules){
            String message = formatRuleDetails(rule);
            Allure.addAttachment(rule.getId(), message);
        }
    }

    private String formatRuleDetails(Rule rule) {
        return new ToStringBuilder(rule, ToStringStyle.MULTI_LINE_STYLE)
                .append("Description", rule.getDescription())
                .append("Tags", rule.getTags())
                .append("Impact", rule.getImpact())
                .append("Help", rule.getHelp())
                .append("Help URL", rule.getHelpUrl())
                .toString();
    }

}
