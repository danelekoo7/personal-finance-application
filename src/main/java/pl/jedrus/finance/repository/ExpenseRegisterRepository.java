package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jedrus.finance.domain.ExpenseRegister;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ExpenseRegisterRepository extends JpaRepository<ExpenseRegister, Long> {

    List<ExpenseRegister> findAllByUser_Username(String username);

    Optional<ExpenseRegister> findAllById(Long id);

    @Query("SELECT SUM(e.value) FROM ExpenseRegister e WHERE e.user.username= :username")
    BigDecimal sumAllExpensesInRegister(@Param("username") String username);

}
