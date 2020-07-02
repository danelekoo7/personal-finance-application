package pl.jedrus.finance.service.loan;

import pl.jedrus.finance.domain.Loan;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    List<Loan> findAllByUser_Username(String username);

    List<Loan> findAllByUser_UsernameForStep4(String username);

    BigDecimal sumAllLoansByUser(String username);

    Loan findById(Long id);

    void saveLoan(Loan loan, String username);

    void updateLoan(Loan asset);

    void deleteLoanById(Long id);
}
