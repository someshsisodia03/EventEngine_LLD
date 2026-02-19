package Rippling.rule;
import Rippling.model.*;
import java.util.*;
public interface ruleEngine{
    List<Violation> evaluate(
        List<Expense> expenses,
        Map<ExpenseType,List<ExpenseRule>>expenseRegistry,
        List<ExpenseRule> allExpenseRegistry,
        List<TripExpenseRule> tripRegistry
    );
}