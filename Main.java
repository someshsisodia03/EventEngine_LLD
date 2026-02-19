package Rippling;
import Rippling.impl.*;
import Rippling.model.*;
import Rippling.registry.*;
import Rippling.rule.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        List<Expense> expense = List.of(
            new Expense("1","1",100,ExpenseType.Restaurant),
            new Expense("2","1",500,ExpenseType.Airfare),
            new Expense("3","1",200,ExpenseType.Entertainment)
        );
        ruleEngine re=new simpleRuleEngine();
        List<Violation>result = re.evaluate(expense, new ruleRegistry().expenseRuleRegistry(), new ruleRegistry().getAllExpenseRules(), new ruleRegistry().getAllTripExpenseRules());
        for(Violation v:result){
            System.out.println(v.getMessage());
        }
    }
}   