package Rippling.registry;
import Rippling.model.*;
import Rippling.rule.*;
import Rippling.rule.impl.*;
import java.util.*;

public class ruleRegistry {
    public Map<ExpenseType,List<ExpenseRule>> expenseRuleRegistry(){
        Map<ExpenseType,List<ExpenseRule>>registry = new HashMap<>();
        registry.put(ExpenseType.Airfare,List.of(
            new DisallowRule()
        ));
        registry.put(ExpenseType.Entertainment,List.of(
            new DisallowRule()
        ));
        return registry;
    }
    public List<ExpenseRule> getAllExpenseRules(){
        return List.of(
            new MaxAmount(250)
        );
    }
    public List<TripExpenseRule> getAllTripExpenseRules(){
        return List.of(
            new TripMaxAmount(100)
        );
    }
}