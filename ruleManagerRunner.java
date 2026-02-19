package Rippling;
import Rippling.model.*;
import Rippling.registry.*;
import Rippling.rule.*;
import java.util.*;
public class ruleManagerRunner {
    private final ruleEngine ruleEngine;
    public ruleManagerRunner(ruleEngine ruleEngine){
        this.ruleEngine = ruleEngine;
    }
    public void run(List<Expense> expenses){

        List<Violation> violations = ruleEngine.evaluate(expenses, new ruleRegistry().expenseRuleRegistry(), new ruleRegistry().getAllExpenseRules(), new ruleRegistry().getAllTripExpenseRules());
        for (Violation v : violations) {
            System.out.println(v.getMessage());
        }
    }
}