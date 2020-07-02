package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.service.asset.AssetService;
import pl.jedrus.finance.service.dateIndicator.DateIndicatorService;
import pl.jedrus.finance.service.loan.LoanService;
import pl.jedrus.finance.web.step2.DateIndicatorController;
import pl.jedrus.finance.web.step2.ExpenseController;
import pl.jedrus.finance.web.step2.ExpenseRegisterController;
import pl.jedrus.finance.web.step2.IncomeController;

import java.math.BigDecimal;


@ControllerAdvice(assignableTypes = {HomeController.class, Step1Controller.class, IncomeController.class, ExpenseController.class,
        ExpenseRegisterController.class, DateIndicatorController.class, Step3BufferController.class})
public class AnnotationAdvice {

    private final LoanService loanService;
    private final AssetService assetService;
    private final DateIndicatorService dateIndicatorService;

    public AnnotationAdvice(LoanService loanService, AssetService assetService, DateIndicatorService dateIndicatorService) {
        this.loanService = loanService;
        this.assetService = assetService;
        this.dateIndicatorService = dateIndicatorService;
    }


    @ModelAttribute("totalValue")
    public BigDecimal getTotalValue(@AuthenticationPrincipal UserDetails user) {
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal sumAllLoans = loanService.sumAllLoansByUser(user.getUsername());
        BigDecimal sumAllAssets = assetService.sumAllAssetByUser(user.getUsername());
        return total.add(sumAllAssets).subtract(sumAllLoans);
    }

    @ModelAttribute("date")
    public String date(@AuthenticationPrincipal UserDetails userDetails) {
        return dateIndicatorService.findCurrentYearMonthByUser(userDetails.getUsername());
    }

    @ModelAttribute("user")
    public String user(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}