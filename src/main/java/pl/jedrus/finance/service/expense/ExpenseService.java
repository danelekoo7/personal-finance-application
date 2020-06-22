package pl.jedrus.finance.service.expense;

import pl.jedrus.finance.domain.Expense;

import java.math.BigDecimal;
import java.util.List;

public interface ExpenseService {
    List<Expense> findAllByUser_Username(String username);

    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);

    BigDecimal sumAllPlannedExpensesByUser(String username);

    BigDecimal sumAllPlannedExpensesByUserAndGroup(String username, int expenseGroup);

    BigDecimal sumAllRealExpensesByUser(String username);

    BigDecimal sumAllRealExpensesByUserAndGroup(String username, int expenseGroup);

    BigDecimal incomeSubExpenseGroup(String username, int expenseGroup);


    int nextExpenseIdByGroup(String username, int expenseGroup);

    Expense findById(Long id);

    void saveExpense(Expense expense, String username);

    void updateExpense(Expense expense);

    void deleteExpenseById(Long id);
}
