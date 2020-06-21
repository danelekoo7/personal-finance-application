package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.jedrus.finance.domain.*;
import pl.jedrus.finance.service.asset.AssetService;
import pl.jedrus.finance.service.loan.LoanService;

import java.math.BigDecimal;
import java.time.LocalDate;


@ControllerAdvice(assignableTypes = {HomeController.class, Step1Controller.class, Step2IncomeController.class, Step2ExpenseController.class})
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


    @ModelAttribute("loan")
    public Loan loan() {
        Loan loan = new Loan();
        loan.setValue(BigDecimal.ZERO);
        return loan;
    }

    @ModelAttribute("asset")
    public Asset asset() {
        Asset asset = new Asset();
        asset.setValue(BigDecimal.ZERO);
        return asset;
    }


    @ModelAttribute("income")
    public Income income() {
        Income income = new Income();
        income.setValue(BigDecimal.ZERO);
        return income;
    }


    @ModelAttribute("expenseRegister")
    public ExpenseRegister expenseRegister() {
        ExpenseRegister expenseRegister = new ExpenseRegister();
        expenseRegister.setCreated(LocalDate.now());
        expenseRegister.setValue(BigDecimal.ZERO);
        return expenseRegister;
    }


    @ModelAttribute("user")
    public String user(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails.getUsername();
    }
}