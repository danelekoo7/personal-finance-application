package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jedrus.finance.domain.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findAllByUser_Username(String username);

    @Query("SELECT SUM(l.value) FROM Loan l WHERE l.user.username= :username")
    BigDecimal sumAllLoansByUser(@Param("username") String username);


}
