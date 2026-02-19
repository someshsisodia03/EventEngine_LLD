package Rippling.model;
public class Expense{
    private final String expense_id;
    private final String trip_id;
    private final double amount;
    private final ExpenseType expense_type;
    public Expense(String expense_id, String trip_id, double amount, ExpenseType expense_type) {
        this.expense_id = expense_id;
        this.trip_id = trip_id;
        this.amount = amount;
        this.expense_type = expense_type;
    }
    public String getExpense_id() {
        return expense_id;
    }
    public String getTrip_id() {
        return trip_id;
    }
    public double getAmount() {
        return amount;
    }
    public ExpenseType getExpense_type() {
        return expense_type;
    }

}