package pl.jedrus.finance.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.repository.LoanRepository;

import java.math.BigDecimal;
import java.util.List;

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
        List<Loan> allLoans = loanRepository.findAll();
        List<Asset> allAssets = assetRepository.findAll();

        BigDecimal loansSum = BigDecimal.ZERO;
        for (Loan loan : allLoans) {
            loansSum = loansSum.add(loan.getValue());
        }

        BigDecimal assetsSum = BigDecimal.ZERO;
        for (Asset asset : allAssets) {
            assetsSum = assetsSum.add(asset.getValue());
        }


        BigDecimal total = BigDecimal.ZERO;
        total = total.add(assetsSum);
        total = total.subtract(loansSum);

        return total;
    }
}