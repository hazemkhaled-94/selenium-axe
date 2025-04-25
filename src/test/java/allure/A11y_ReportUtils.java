package allure;

import com.deque.html.axecore.results.Rule;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;
import java.util.UUID;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class A11y_ReportUtils {


    public static void reportViolations(List<Rule> rules){
        log.info("Publishing violations...");
        String uuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName("Violations").setStatus(Status.PASSED);
        Allure.getLifecycle().startStep(uuid, stepResult);
        if(rules != null && !rules.isEmpty()){
            reportResults(uuid, rules);
            Allure.getLifecycle().updateStep(step -> step.setStatus(Status.FAILED));
        }
        Allure.getLifecycle().stopStep();
    }

    public static void reportPasses(List<Rule> rules){
        log.info("Publishing Passes...");
        String uuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName("Passes").setStatus(Status.FAILED);
        Allure.getLifecycle().startStep(uuid, stepResult);
        if(rules == null || rules.isEmpty()){
            reportResults(uuid, rules);
            Allure.getLifecycle().updateStep(step -> step.setStatus(Status.PASSED));
        }
        Allure.getLifecycle().stopStep();
    }

    public static void reportInapplicable(List<Rule> rules){
        log.info("Publishing Inapplicable rules...");
        String uuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName("Inapplicable").setStatus(Status.PASSED);
        Allure.getLifecycle().startStep(uuid, stepResult);
        if(rules != null && !rules.isEmpty()){
            reportResults(uuid, rules);
            Allure.getLifecycle().updateStep(step -> step.setStatus(Status.SKIPPED));
        }
        Allure.getLifecycle().stopStep();
    }

    public static void reportInComplete(List<Rule> rules){
        log.info("Publishing Incomplete rules...");
        String uuid = UUID.randomUUID().toString();
        StepResult stepResult = new StepResult().setName("Incomplete").setStatus(Status.PASSED);
        Allure.getLifecycle().startStep(uuid, stepResult);
        if(rules != null && !rules.isEmpty()){
            reportResults(uuid, rules);
            Allure.getLifecycle().updateStep(step -> step.setStatus(Status.BROKEN));
        }
        Allure.getLifecycle().stopStep();
    }

    private static void reportResults(String uuid, List<Rule> rules) {
        Allure.getLifecycle().updateStep(uuid, stepResult -> {
            if (rules != null && !rules.isEmpty()) {
                rules.forEach(A11y_ReportUtils::reportRule);
            } else {
                log.warn(String.format("No rules found for %s", stepResult.getName()));
            }
        });
    }

    private static void reportRule(Rule rule) {
        String message = formatRuleDetails(rule);
        Allure.addAttachment(rule.getId(), message);
    }

    private static String formatRuleDetails(Rule rule) {
        return new ToStringBuilder(rule, ToStringStyle.MULTI_LINE_STYLE)
                .append("Description", rule.getDescription())
                .append("Tags", rule.getTags())
                .append("Impact", rule.getImpact())
                .append("Help", rule.getHelp())
                .append("Help URL", rule.getHelpUrl())
                .toString();
    }

}
