package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Expense;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
//
//    List<Expense> findAllByUser_Username(String username);
//
//    List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup);

    @Query(value = "SELECT * FROM expense JOIN user u on expense.user_id = u.id WHERE u.username =:username AND MONTH(current_date_indicator) =:monthId AND YEAR(current_date_indicator) =:yearId", nativeQuery = true)
    List<Expense> findAllByUser(@Param("username") String username, @Param("monthId") int monthId, @Param("yearId") int yearId);

//    @Query(value = "SELECT * FROM expense JOIN user u on expense.user_id = u.id WHERE u.username =:username AND MONTH(current_date_indicator) =:monthId AND YEAR(current_date_indicator) =:yearId AND expense_group =:groupId ", nativeQuery = true)
//    List<Expense> findAllByUser_UsernameAndExpenseGroup(@Param("username") String username, @Param("monthId") int monthId, @Param("yearId") int yearId, @Param("groupId") int groupId);

  @Query(value = "SELECT * FROM expense JOIN user u on expense.user_id = u.id WHERE u.username =:username AND current_date_indicator LIKE :yearMonth+'%' AND expense_group =:groupId ", nativeQuery = true)
    List<Expense> findAllByUser_UsernameAndExpenseGroup(@Param("username") String username, @Param("yearMonth") String yearMonth, @Param("groupId") int groupId);



    @Query(value = "SELECT DISTINCT current_date_indicator FROM expense JOIN user u on expense.user_id = u.id WHERE u.username =:username ORDER BY current_date_indicator DESC", nativeQuery = true)
    List<String> findAllDates(@Param("username") String username);



    Optional<Expense> findById(Long id);
}
