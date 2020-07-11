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
import pl.jedrus.finance.domain.SecurityFund;
import pl.jedrus.finance.service.securityFund.SecurityFundService;

import javax.validation.Valid;

@Controller
@RequestMapping("/step5")
public class Step5SecurityFundController {
    private final SecurityFundService securityFundService;

    public Step5SecurityFundController(SecurityFundService securityFundService) {
        this.securityFundService = securityFundService;
    }

    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        SecurityFund securityFund = securityFundService.findByUser_Username(userDetails.getUsername());
        model.addAttribute("securityFund",securityFund);
        return "step5/step5";
    }

    @GetMapping("/edit/{id}")
    public String editSecurityFund(@PathVariable Long id, Model model) {
        SecurityFund securityFund = securityFundService.findById(id);
        model.addAttribute("securityFund", securityFund);
        return "step5/edit-security-fund";
    }

    @PostMapping("/edit/{id}")
    public String updateSecurityFund(@Valid SecurityFund securityFund, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step5/edit-security-fund";
        }
        securityFundService.updateSecurityFund(securityFund);
        return "redirect:/step5";
    }
}
