package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Expense;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByUser_Username(String username);

    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);

    Optional<Expense> findById(Long id);
}
