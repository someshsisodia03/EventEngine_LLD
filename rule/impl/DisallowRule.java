package Rippling.rule.impl;
import Rippling.model.*;
import Rippling.rule.*;
import java.util.*;
public class DisallowRule implements ExpenseRule{
    @Override
    public Optional<Violation>check(Expense expense){
        return Optional.of(Violation.of("All expenses are disallowed"));
    }
}