package pl.jedrus.finance.web.plan;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.service.loan.LoanService;

@Controller
@RequestMapping("/step4")
public class Step4Controller {

   private final LoanService loanService;

    public Step4Controller(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("loans",loanService.findAllByUser_Username(userDetails.getUsername()));
        return "step4/step4";
    }

}
