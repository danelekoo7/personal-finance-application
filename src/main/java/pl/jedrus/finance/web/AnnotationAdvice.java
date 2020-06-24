package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.service.asset.AssetService;
import pl.jedrus.finance.service.loan.LoanService;
import pl.jedrus.finance.web.step2.Step2ExpenseController;
import pl.jedrus.finance.web.step2.Step2ExpenseRegisterController;
import pl.jedrus.finance.web.step2.Step2IncomeController;

import java.math.BigDecimal;


@ControllerAdvice(assignableTypes = {HomeController.class, Step1Controller.class, Step2IncomeController.class, Step2ExpenseController.class,
Step2ExpenseRegisterController.class})
public class AnnotationAdvice {

    private final LoanService loanService;
    private final AssetService assetService;

    public AnnotationAdvice(LoanService loanService, AssetService assetService) {
        this.loanService = loanService;
        this.assetService = assetService;
    }


    @ModelAttribute("totalValue")
    public BigDecimal getTotalValue(@AuthenticationPrincipal UserDetails user) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal sumAllLoans = loanService.sumAllLoansByUser(user.getUsername());
        BigDecimal sumAllAssets = assetService.sumAllAssetByUser(user.getUsername());
        return total.add(sumAllAssets).subtract(sumAllLoans);
    }

    @ModelAttribute("user")
    public String user(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}