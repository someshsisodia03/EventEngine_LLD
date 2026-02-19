# 🧾 Event Engine — Low Level Design (LLD)

> A clean, extensible **Expense Rule Engine** built in Java, inspired by Rippling's expense management system. Designed as part of a Low-Level Design (LLD) exercise to demonstrate real-world OOP principles, interface-driven architecture, and the Strategy + Registry patterns.

---

## 📌 Problem Statement

Design a rule engine that evaluates a list of employee expenses and flags **violations** based on configurable business rules. Rules can apply at:
- **Individual expense level** — e.g., disallow certain categories, cap per-expense amount.
- **Trip level** — e.g., cap the total amount across all expenses in a trip.

---

## ✨ Features

- ✅ Per-expense rule evaluation (type-specific and global)
- ✅ Trip-level aggregated rule evaluation
- ✅ Pluggable rule implementation via interfaces
- ✅ Rule registry to manage rule-to-category mappings
- ✅ Clean violation reporting
- ✅ Easily extensible — add new rules without touching core engine logic

---

## 🏗️ Project Structure

```
Rippling/
│
├── Main.java                        # Entry point — sets up expenses and triggers the engine
├── ruleManagerRunner.java           # Orchestrates the engine run and prints violations
│
├── model/
│   ├── Expense.java                 # Core data model: expense_id, trip_id, amount, type
│   └── ExpenseType.java             # Enum: Restaurant, Entertainment, Airfare
│
├── rule/
│   ├── ExpenseRule.java             # Interface: check(Expense) → Optional<Violation>
│   ├── TripExpenseRule.java         # Interface: check(List<Expense>) → Optional<Violation>
│   ├── Violation.java               # Value object holding the violation message
│   ├── ruleEngine.java              # Interface defining the core engine contract
│   │
│   └── impl/
│       ├── DisallowRule.java        # Rule: disallows an expense category entirely
│       ├── MaxAmount.java           # Rule: flags expenses exceeding a per-expense limit
│       └── TripMaxAmount.java       # Rule: flags trip totals exceeding a limit
│
├── registry/
│   └── ruleRegistry.java            # Wires rules to categories and provides rule lists
│
└── impl/
    └── simpleRuleEngine.java        # Concrete engine: evaluates per-expense + trip-level rules
```

---

## ⚙️ Architecture & Design

### Class Diagram (High Level)

```
         ┌──────────────┐
         │   Expense    │◄──────────────────────────┐
         │  (model)     │                           │
         └──────────────┘                           │
                │                                   │
                ▼                                   │
     ┌────────────────────┐             ┌───────────────────────┐
     │    ExpenseRule     │             │    TripExpenseRule     │
     │   «interface»      │             │      «interface»       │
     └────────────────────┘             └───────────────────────┘
       ▲          ▲                              ▲
       │          │                              │
 DisallowRule  MaxAmount                  TripMaxAmount
```

```
ruleRegistry  ──────►  simpleRuleEngine  ──────►  ruleManagerRunner  ──────►  Main
```

---

### Design Patterns Used

| Pattern | Where Used |
|---|---|
| **Strategy** | `ruleEngine` interface → `simpleRuleEngine` is a pluggable strategy |
| **Registry** | `ruleRegistry` maps `ExpenseType` → applicable `ExpenseRule` list |
| **Value Object** | `Violation` is immutable, created via static factory `Violation.of()` |
| **Interface Segregation** | `ExpenseRule` and `TripExpenseRule` are kept separate for single-level and trip-level rules |

---

## 🔄 How It Works

1. **Expenses are created** — each expense has an ID, trip ID, amount, and type.
2. **`ruleManagerRunner`** invokes `simpleRuleEngine.evaluate()` with:
   - The expense list
   - A type-specific registry (`Map<ExpenseType, List<ExpenseRule>>`)
   - Global per-expense rules (`List<ExpenseRule>`)
   - Trip-level rules (`List<TripExpenseRule>`)
3. **`simpleRuleEngine`** loops through each expense:
   - Applies type-specific rules from the registry
   - Applies global expense rules to all expenses
4. Then applies trip-level rules across the **entire expense list**.
5. All `Violation` messages are collected and printed.

---

## 🧪 Sample Run

**Input Expenses:**

| Expense ID | Trip ID | Amount | Type |
|---|---|---|---|
| 1 | 1 | ₹100 | Restaurant |
| 2 | 1 | ₹500 | Airfare |
| 3 | 1 | ₹200 | Entertainment |

**Configured Rules (via `ruleRegistry`):**

| Rule | Scope | Config |
|---|---|---|
| `DisallowRule` | Airfare, Entertainment | Block entirely |
| `MaxAmount` | All expenses | Limit: ₹250 |
| `TripMaxAmount` | All trips | Limit: ₹100 |

**Expected Output:**

```
Airfare expenses are not allowed
Airfare exceeds maximum allowed amount of 250.0
Entertainment expenses are not allowed
Expense amount exceeds the maximum allowed limit of 100.0
```

---

## 🚀 How to Run

### Prerequisites
- Java 11+

### Compile

```bash
javac -d . *.java model/*.java rule/*.java rule/impl/*.java registry/*.java impl/*.java
```

### Run

```bash
java Rippling.Main
```

> **Note:** Run from the directory **containing** the `Rippling` folder (the parent of this project root), since the package is `Rippling`.

---

## 🔧 How to Extend

### Add a new Expense-level rule

1. Create a class in `rule/impl/` that implements `ExpenseRule`:
```java
public class MinAmount implements ExpenseRule {
    private final double minAmount;
    public MinAmount(double minAmount) { this.minAmount = minAmount; }

    @Override
    public Optional<Violation> check(Expense expense) {
        if (expense.getAmount() < minAmount) {
            return Optional.of(Violation.of(expense.getExpense_type() + " amount is below minimum of " + minAmount));
        }
        return Optional.empty();
    }
}
```

2. Register it in `ruleRegistry.java`:
```java
public List<ExpenseRule> getAllExpenseRules() {
    return List.of(new MaxAmount(250), new MinAmount(10));
}
```

### Add a new Trip-level rule

Implement `TripExpenseRule` and register it in `getAllTripExpenseRules()`.

### Plug in a different Rule Engine

Implement the `ruleEngine` interface and pass your custom engine to `ruleManagerRunner`:
```java
ruleManagerRunner runner = new ruleManagerRunner(new myCustomRuleEngine());
```

---

## 📚 Concepts Demonstrated

- Interface-driven programming
- Open/Closed Principle (add rules without modifying engine)
- Single Responsibility Principle (registry, engine, runner — each has one job)
- Factory method pattern (`Violation.of(...)`)
- Java `Optional` for safe violation handling
- Enum for type safety (`ExpenseType`)

---

## 👤 Author

**Somesh Sisodia**  
LLD Practice — Rippling-inspired Expense Rule Engine  
🔗 [GitHub](https://github.com/someshsisodia03)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
