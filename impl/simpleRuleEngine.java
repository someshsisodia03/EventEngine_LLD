package Rippling.impl;
import Rippling.model.*;
import Rippling.rule.*;
import java.util.*;
public class simpleRuleEngine implements ruleEngine{
    List<Violation>violations;
    @Override
    public List<Violation> evaluate(List<Expense> expenses,
        Map<ExpenseType,List<ExpenseRule>>expenseRegistry,
        List<ExpenseRule> allExpenseRegistry,
        List<TripExpenseRule> tripRegistry
    ){
        // Individual Expenses
        for(Expense e:expenses){
            Optional<Violation> v=Optional.empty();
            List<ExpenseRule>expenseRules = expenseRegistry.getOrDefault(e.getExpense_type(),List.of());
            for(ExpenseRule er:expenseRules){
                v = er.check(e);
            }
            if(!(v.isEmpty())) violations.add(v.get());


            for(ExpenseRule er:allExpenseRegistry){
                v = er.check(e);
            }
            if(!(v.isEmpty())) violations.add(v.get());    

            
        }
            Optional<Violation> v=Optional.empty();
            for(TripExpenseRule ter:tripRegistry){
                v=ter.check(expenses);
            }
            if(!(v.isEmpty())) violations.add(v.get());                
        return violations;
    }
}