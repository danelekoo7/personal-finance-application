package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.ExpenseRegister;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRegisterRepository extends JpaRepository<ExpenseRegister, Long> {
    @Query(value = "SELECT * FROM expense_register JOIN user u on expense_register.user_id = u.id WHERE u.username =:username AND MONTH(current_date_indicator) =:monthId AND YEAR(current_date_indicator) =:yearId", nativeQuery = true)
    List<ExpenseRegister> findAllByUser_Username(@Param("username") String username, @Param("monthId") int monthId, @Param("yearId") int yearId);

    Optional<ExpenseRegister> findAllById(Long id);

}
