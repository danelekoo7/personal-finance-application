package pl.jedrus.finance.service.loan;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.LoanRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;
    private final UserService userService;

    public LoanServiceImpl(LoanRepository loanRepository, UserService userService) {
        this.loanRepository = loanRepository;
        this.userService = userService;
    }

    @Override
    public List<Loan> findAllByUser_Username(String username) {
        return loanRepository.findAllByUser_Username(username);
    }

    @Override
    public BigDecimal sumAllLoansByUser(String username) {
        List<Loan> loanList = findAllByUser_Username(username);
        BigDecimal loanSum = BigDecimal.ZERO;
        for (Loan loan : loanList) {
            loanSum = loanSum.add(loan.getValue());
        }
        return loanSum;
    }

    @Override
    public Loan findById(Long id) {
        return loanRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveLoan(Loan loan, String username) {
        loan.setUser(userService.findByUserName(username));
        loanRepository.save(loan);
    }

    @Override
    public void updateLoan(Loan loan, Long id) {
        Loan loanInDb = findById(id);
        loanInDb.setValue(loan.getValue());
        loanInDb.setDescription(loan.getDescription());
        loanRepository.save(loanInDb);
    }

    @Override
    public void deleteLoanById(Long id) {
        loanRepository.deleteById(id);
    }
}
