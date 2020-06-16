package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.repository.LoanRepository;

import java.math.BigDecimal;


@ControllerAdvice(assignableTypes = {HomeController.class, Step1Controller.class, Step2Controller.class})
public class AnnotationAdvice {

    private final LoanRepository loanRepository;
    private final AssetRepository assetRepository;

    public AnnotationAdvice(LoanRepository loanRepository, AssetRepository assetRepository) {
        this.loanRepository = loanRepository;
        this.assetRepository = assetRepository;
    }

    @ModelAttribute("totalValue")
    public BigDecimal getTotalValue(@AuthenticationPrincipal UserDetails user) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal sumAllLoans = BigDecimal.ZERO;
        if (loanRepository.sumAllLoansByUser(user.getUsername()) != null) {
            sumAllLoans = sumAllLoans.add(loanRepository.sumAllLoansByUser(user.getUsername()));
        }

        BigDecimal sumAllAssets = BigDecimal.ZERO;

        if (assetRepository.sumAllAssetByUser(user.getUsername()) != null) {
            sumAllAssets = sumAllAssets.add(assetRepository.sumAllAssetByUser(user.getUsername()));
        }
        return total.add(sumAllAssets).subtract(sumAllLoans);
    }


    @ModelAttribute("loan")
    public Loan loan() {
        return new Loan();
    }

    @ModelAttribute("asset")
    public Asset asset() {
        return new Asset();
    }


    @ModelAttribute("income")
    public Income income() {
        return new Income();
    }

    @ModelAttribute("expense")
    public Expense expense() {
        return new Expense();
    }


    @ModelAttribute("user")
    public String user(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}