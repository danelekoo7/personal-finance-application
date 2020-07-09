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
import pl.jedrus.finance.domain.Prize;
import pl.jedrus.finance.service.prize.PrizeService;

import javax.validation.Valid;

@Controller
@RequestMapping("/step6")
public class Step6PrizeController {
    private final PrizeService prizeService;

    public Step6PrizeController(PrizeService prizeService) {
        this.prizeService = prizeService;
    }

    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Prize prize = prizeService.findByUser_Username(userDetails.getUsername());
        model.addAttribute("prize",prize);
        return "step6/step6";
    }

    @GetMapping("/edit/{id}")
    public String editPrize(@PathVariable Long id, Model model) {
        Prize prize = prizeService.findById(id);
        model.addAttribute("prize", prize);
        return "step6/edit-prize";
    }

    @PostMapping("/edit/{id}")
    public String updatePrize(@Valid Prize prize, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step6/edit-prize";
        }
        prizeService.updatePrize(prize);
        return "redirect:/step6";
    }
}
