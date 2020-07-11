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
import pl.jedrus.finance.domain.Retirement;
import pl.jedrus.finance.service.retirement.RetirementService;

import javax.validation.Valid;

@Controller
@RequestMapping("/step7")
public class Step7RetirementController {

    private final RetirementService retirementService;

    public Step7RetirementController(RetirementService retirementService) {
        this.retirementService = retirementService;
    }


    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Retirement retirement = retirementService.findByUser_Username(userDetails.getUsername());
        model.addAttribute("retirement",retirement);
        return "step7/step7";
    }

    @GetMapping("/edit/{id}")
    public String editRetirement(@PathVariable Long id, Model model) {
        Retirement retirement = retirementService.findById(id);
        model.addAttribute("retirement", retirement);
        return "step7/edit-retirement";
    }

    @PostMapping("/edit/{id}")
    public String updateRetirement(@Valid Retirement retirement, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step7/edit-retirement";
        }
        retirementService.updateRetirement(retirement);
        return "redirect:/step7";
    }

    @PostMapping("/add-retirement")
    public String saveRetirement(@Valid Retirement retirement, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step7/step7";
        }
        retirementService.saveRetirement(retirement, userDetails.getUsername());
        return "redirect:/step7";
    }


    @GetMapping("/delete-retirement/{id}")
    public String deleteRetirement(@PathVariable Long id) {
        retirementService.deleteRetirementById(id);
        return "redirect:/step7";
    }

}