package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;
import pl.jedrus.finance.service.IncomeService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/step2")
public class Step2Controller {

    private final IncomeService incomeService;

    public Step2Controller(IncomeRepository incomeRepository, IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());

        int nextIncomeId = allIncomes.size() + 1;

        model.addAttribute("incomes" , allIncomes);
        model.addAttribute("incomesSum",incomesSum);
        model.addAttribute("nextIncomeId", nextIncomeId);
        return "step2";
    }
}
