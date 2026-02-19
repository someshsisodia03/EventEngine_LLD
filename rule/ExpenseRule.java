package Rippling.rule;
import Rippling.model.*;
import java.util.*;

public interface ExpenseRule{
    Optional<Violation>check(Expense expense);    
}