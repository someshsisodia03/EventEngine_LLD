package Rippling.rule;

import Rippling.model.*;
import java.util.*;
public interface TripExpenseRule{
    Optional<Violation> check(List<Expense>expenses);
}