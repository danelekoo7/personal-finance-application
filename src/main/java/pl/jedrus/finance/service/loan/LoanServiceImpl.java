package pl.jedrus.finance.service.loan;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.LoanRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Loan> findAllByUser_UsernameForStep4(String username) {
        List<Loan> allLoans = findAllByUser_Username(username);
        List<Loan> collect = allLoans.stream()
                .filter(loan -> !loan.isVisibleOnlyInStep1())
                .sorted((o1, o2) -> {
                    if (o1.getInterest() >= 20) {
                        return (int) (o2.getInterest()-o1.getInterest());
                    }
                    return 1;
                })
                .sorted((o1, o2) -> {
                    if (o1.getInterest() < 20 && o2.getInterest()<20) {
                        return  o1.getValue().compareTo(o2.getValue()) ;
                    }
                    return 1;
                })
                .collect(Collectors.toList());
        return collect;
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
    public void updateLoan(Loan loan) {
        Loan loanInDb = findById(loan.getId());
        loanInDb.setValue(loan.getValue());
        loanInDb.setDescription(loan.getDescription());
        loanInDb.setInstallment(loan.getInstallment());
        loanInDb.setInterest(loan.getInterest());
        loanRepository.save(loanInDb);
    }

    @Override
    public void deleteLoanById(Long id) {
        loanRepository.deleteById(id);
    }

    @Override
    public void deactivateLoanInStep4(Long id) {
        Loan loan = findById(id);
        loan.setVisibleOnlyInStep1(true);
        loanRepository.save(loan);
    }
}
