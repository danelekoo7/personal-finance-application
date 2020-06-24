package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.service.income.IncomeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/step2/income")
public class Step2IncomeController {
    private final IncomeService incomeService;

    public Step2IncomeController(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping()
    public String getIncome(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());

        model.addAttribute("incomes", allIncomes);
        model.addAttribute("incomesSum", incomesSum);
        model.addAttribute("nextIncomeId", allIncomes.size() + 1);
        return "step2/income";
    }


    @PostMapping("/add")
    public String saveIncome(@Valid Income income, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2/income";
        }
        incomeService.saveIncome(income, userDetails.getUsername());
        return "redirect:/step2/income";
    }


    @GetMapping("/edit/{id}")
    public String editIncome(@PathVariable Long id, Model model) {
        Income income = incomeService.findById(id);
        model.addAttribute("income", income);
        return "step2/edit-income";
    }

    @PostMapping("/edit/{id}")
    public String updateIncome(@Valid Income income, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step2/edit-income";
        }
        incomeService.updateIncome(income);
        return "redirect:/step2/income";
    }

    @GetMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncomeById(id);
        return "redirect:/step2/income";
    }

    @ModelAttribute("income")
    public Income income() {
        Income income = new Income();
        income.setValue(BigDecimal.ZERO);
        return income;
    }
}