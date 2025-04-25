package axecore;

import com.deque.html.axecore.results.Results;
import com.deque.html.axecore.results.Rule;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AxeRunnerResults {

    private final Results results;

    public AxeRunnerResults(Results results){
        this.results = results;
    }

    public List<Rule> getPasses(){
        return this.results.getPasses();
    }

    public List<Rule> getIncomplete(){
        return this.results.getIncomplete();
    }

    public List<Rule> getInapplicable(){
        return this.results.getInapplicable();
    }

    public List<Rule> getViolations(){
        return this.results.getViolations();
    }

}
