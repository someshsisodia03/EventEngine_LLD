package Rippling.impl;
import Rippling.model.*;
import Rippling.rule.*;
import java.util.*;
public class simpleRuleEngine implements ruleEngine{
    List<Violation>violations=new ArrayList<>();;
    @Override
    public List<Violation> evaluate(List<Expense> expenses,
        Map<ExpenseType,List<ExpenseRule>>expenseRegistry,
        List<ExpenseRule> allExpenseRegistry,
        List<TripExpenseRule> tripRegistry
    ){        // Individual Expenses
        for(Expense e:expenses){

            List<ExpenseRule>expenseRules = expenseRegistry.getOrDefault(e.getExpense_type(),List.of());
            for(ExpenseRule er:expenseRules){
                Optional<Violation> v = er.check(e);
                if(v.isPresent()) violations.add(v.get());
            } 
            for(ExpenseRule er:allExpenseRegistry){
                Optional<Violation> v = er.check(e);                
                if(v.isPresent())  violations.add(v.get());    
            }

            
        }
        // Trip level rules 
            for(TripExpenseRule ter:tripRegistry){
                Optional<Violation> v=ter.check(expenses);
                if(v.isPresent()) violations.add(v.get()); 
            }
        return violations;
    }
}