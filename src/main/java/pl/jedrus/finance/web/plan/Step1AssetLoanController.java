package pl.jedrus.finance.web.plan;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.service.asset.AssetService;
import pl.jedrus.finance.service.loan.LoanService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/step1")
public class Step1AssetLoanController {

    private final LoanService loanService;
    private final AssetService assetService;

    public Step1AssetLoanController(LoanService loanService, AssetService assetService) {
        this.loanService = loanService;
        this.assetService = assetService;
    }

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Loan> allLoans = loanService.findAllByUser_Username(user.getUsername());
        List<Asset> allAssets = assetService.findAllByUser_Username(user.getUsername());

        BigDecimal loansSum = loanService.sumAllLoansByUser(user.getUsername());

        BigDecimal assetsSum = assetService.sumAllAssetByUser(user.getUsername());

        model.addAttribute("loans", allLoans);
        model.addAttribute("assets", allAssets);
        model.addAttribute("loansSum", loansSum);
        model.addAttribute("assetsSum", assetsSum);

        return "step1/step1";
    }


    //    Assets
    @PostMapping("/add-asset")
    public String saveAsset(@Valid Asset asset, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step1/step1";
        }
        assetService.saveAsset(asset, userDetails.getUsername());
        return "redirect:";
    }


    @GetMapping("/edit-asset/{id}")
    public String editAsset(@PathVariable Long id, Model model) {
        model.addAttribute("asset", assetService.findById(id));
        return "step1/edit-asset";
    }

    @PostMapping("/edit-asset/{id}")
    public String updateAsset(@Valid Asset asset, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step1/edit-asset";
        }
        assetService.updateAsset(asset);
        return "redirect:/step1";
    }


    @GetMapping("/delete-asset/{id}")
    public String deleteAsset(@PathVariable Long id) {
        assetService.deleteAssetById(id);
        return "redirect:/step1";
    }


    //    Loans
    @PostMapping("/add-loan")
    public String saveLoan(@Valid Loan loan, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step1/step1";
        }
        loanService.saveLoan(loan, userDetails.getUsername());
        return "redirect:";
    }

    @GetMapping("/edit-loan/{id}")
    public String editLoan(@PathVariable Long id, Model model) {
        model.addAttribute("loan", loanService.findById(id));
        return "step1/edit-loan";
    }

    @PostMapping("/edit-loan/{id}")
    public String updateLoan(@Valid Loan loan, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step1/edit-loan";
        }
        loanService.updateLoan(loan);
        return "redirect:/step1";
    }


    @GetMapping("/delete-loan/{id}")
    public String deleteLoan(@PathVariable Long id) {
        loanService.deleteLoanById(id);
        return "redirect:/step1";
    }


    @ModelAttribute("loan")
    public Loan loan() {
        Loan loan = new Loan();
        loan.setValue(BigDecimal.ZERO);
        loan.setInterest(0);
        loan.setInstallment(BigDecimal.ZERO);
        return loan;
    }

    @ModelAttribute("asset")
    public Asset asset() {
        Asset asset = new Asset();
        asset.setValue(BigDecimal.ZERO);
        return asset;
    }
}