package pl.jedrus.finance.service;

import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Expense;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseService {
    List<Expense> findAllByUser_Username(String username);

    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);


    BigDecimal sumAllPlannedExpensesByUser(String username);

    BigDecimal sumAllPlannedExpensesByUserAndGroup(String username, int expenseGroup);

    BigDecimal sumAllRealExpensesByUser(String username);

    BigDecimal sumAllRealExpensesByUserAndGroup(String username, int expenseGroup);

    Expense findAllById(Long id);

    void saveExpense(Expense expense);

    void updateExpense(Expense expense);

    void deleteExpenseById(Long id);

}
