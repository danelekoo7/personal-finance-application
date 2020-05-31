package pl.jedrus.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.repository.LoanRepository;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/step1")
public class Step1Controller {

    private final LoanRepository loanRepository;
    private final AssetRepository assetRepository;

    public Step1Controller(LoanRepository loanRepository, AssetRepository assetRepository) {
        this.loanRepository = loanRepository;
        this.assetRepository = assetRepository;
    }

    @GetMapping
    public String get(Model model) {
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


        model.addAttribute("loans", allLoans);
        model.addAttribute("assets", allAssets);
        model.addAttribute("loansSum", loansSum);
        model.addAttribute("assetsSum", assetsSum);
        return "step1";
    }


}
