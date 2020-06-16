package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jedrus.finance.domain.Expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUser_Username(String username);

    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);


    @Query("SELECT SUM(e.plannedValue) FROM Expense e WHERE e.user.username= :username")
    BigDecimal sumAllPlannedExpensesByUser(@Param("username") String username);

    @Query("SELECT SUM(e.plannedValue) FROM Expense e WHERE e.user.username= :username and e.expenseGroup= :expenseGroup")
    BigDecimal sumAllPlannedExpensesByUserAndGroup(@Param("username") String username, @Param("expenseGroup") int expenseGroup);

    @Query("SELECT SUM(e.realValue) FROM Expense e WHERE e.user.username= :username")
    BigDecimal sumAllRealExpensesByUser(@Param("username") String username);

    @Query("SELECT SUM(e.realValue) FROM Expense e WHERE e.user.username= :username and e.expenseGroup= :expenseGroup")
    BigDecimal sumAllRealExpensesByUserAndGroup(@Param("username") String username, @Param("expenseGroup") int expenseGroup);

    Optional<Expense> findAllById(Long id);

}
