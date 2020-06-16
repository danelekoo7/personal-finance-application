package pl.jedrus.finance.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Expense;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ExpenseService {
    List<Expense> findAllByUser_Username(String username);

    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);


    BigDecimal sumAllPlannedExpensesByUser(@Param("username") String username);

    BigDecimal sumAllPlannedExpensesByUserAndGroup(@Param("username") String username, @Param("expenseGroup") int expenseGroup);

    BigDecimal sumAllRealExpensesByUser(@Param("username") String username);

    BigDecimal sumAllRealExpensesByUserAndGroup(@Param("username") String username, @Param("expenseGroup") int expenseGroup);

    Expense findAllById(Long id);

    void saveExpense(Expense expense);

    void updateExpense(Expense expense);

    void deleteExpenseById(Long id);

}
