package Rippling.rule.impl;
import Rippling.model.*;
import Rippling.rule.*;
import java.util.*;
public class TripMaxAmount implements TripExpenseRule{
    private final double amount;
    public TripMaxAmount(double amount) {
        this.amount=amount;
    }
    @Override
    public Optional<Violation>check(List<Expense>expenses){
        int total=0;
        for(Expense e:expenses){
            total+=e.getAmount();
        }
        if(total>amount){
            return Optional.of(Violation.of("Expense amount exceeds the maximum allowed limit of "));
        }
        return Optional.empty();
    }
}