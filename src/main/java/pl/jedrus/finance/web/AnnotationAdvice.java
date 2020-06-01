package pl.jedrus.finance.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.repository.LoanRepository;

import java.math.BigDecimal;


@ControllerAdvice()
public class AnnotationAdvice {

    private final LoanRepository loanRepository;
    private final AssetRepository assetRepository;

    public AnnotationAdvice(LoanRepository loanRepository, AssetRepository assetRepository) {
        this.loanRepository = loanRepository;
        this.assetRepository = assetRepository;
    }

    @ModelAttribute("totalValue")
    public BigDecimal getTotalValue() {
        BigDecimal sumAllLoans = loanRepository.sumAllLoans();
        BigDecimal sumAllAssets = assetRepository.sumAllAssets();
        return sumAllAssets.subtract(sumAllLoans);
    }


    @ModelAttribute("loan")
    public Loan loan() {
        return new Loan();
    }

    @ModelAttribute("asset")
    public Asset asset() {
        return new Asset();
    }
}