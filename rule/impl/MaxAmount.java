package Rippling.rule.impl;
import Rippling.model.*;
import Rippling.rule.*;
import java.util.*;
public class MaxAmount implements ExpenseRule{
    private final double maxAmount;
    public MaxAmount(double maxAmount){
        this.maxAmount = maxAmount;
    }
    @Override
    public Optional<Violation>check(Expense expense){
        if(expense.getAmount()>maxAmount){
            return Optional.of(Violation.of("Expense amount exceeds the maximum allowed limit of "+maxAmount));
        }
        return Optional.empty();        
    }
}