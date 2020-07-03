package pl.jedrus.finance.web.plan;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.service.loan.LoanService;

import javax.validation.Valid;

@Controller
@RequestMapping("/step4")
public class Step4Controller {

   private final LoanService loanService;

    public Step4Controller(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("loans",loanService.findAllByUser_UsernameForStep4(userDetails.getUsername()));
        return "step4/step4-display";
    }


    @GetMapping("/edit/{id}")
    public String editLoan(@PathVariable Long id, Model model) {
        model.addAttribute("loan", loanService.findById(id));
        return "step4/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLoan(@Valid Loan loan, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()) {
            return "step4/edit";
        }
        loanService.updateLoan(loan);
        return "redirect:/step4";
    }


    @GetMapping("/deactivate/{id}")
    public String deactivateLoan(@PathVariable Long id) {
        loanService.deactivateLoanInStep4(id);
        return "redirect:/step4";
    }


}
