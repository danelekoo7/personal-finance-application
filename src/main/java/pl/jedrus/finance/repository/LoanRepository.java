package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jedrus.finance.domain.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query(value = "SELECT * FROM loan JOIN user u ON loan.id_user = u.id WHERE username= ?1", nativeQuery = true)
    List<Loan> findAllByUserName (String userName);

    @Query(value = "SELECT SUM(value) FROM loan JOIN user u ON loan.id_user = u.id WHERE username= ?1", nativeQuery = true)
    BigDecimal sumAllLoansByUserName(String userName);
}
