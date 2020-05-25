package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
