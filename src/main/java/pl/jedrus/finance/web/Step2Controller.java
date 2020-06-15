package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;
import pl.jedrus.finance.service.IncomeService;
import pl.jedrus.finance.service.UserService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/step2")
public class Step2Controller {

    private final IncomeService incomeService;
    private final UserService userService;

    public Step2Controller(IncomeRepository incomeRepository, IncomeService incomeService, UserService userService) {
        this.incomeService = incomeService;
        this.userService = userService;
    }

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());

        int nextIncomeId = allIncomes.size() + 1;

        model.addAttribute("incomes", allIncomes);
        model.addAttribute("incomesSum", incomesSum);
        model.addAttribute("nextIncomeId", nextIncomeId);
        return "step2";
    }

    //    Incomes
    @PostMapping("/add-income")
    public String saveIncome(@Valid Income income, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2";
        }

        Income newIncome = new Income();
        newIncome.setId(income.getId());
        newIncome.setSource(income.getSource());
        newIncome.setValue(income.getValue());
        newIncome.setComment(income.getComment());
        newIncome.setUser(userService.findByUserName(userDetails.getUsername()));
        incomeService.saveIncome(newIncome);
        return "redirect:";
    }


    @GetMapping("/edit-income/{id}")
    public String editIncome(@PathVariable Long id, Model model) {

        Income income = incomeService.findAllById(id);

        model.addAttribute("income", income);
        return "edit-income";
    }

    @PostMapping("/edit-income/{id}")
    public String updateIncome(@Valid Income income, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "edit-income";
        }

        Income incomeInDB = incomeService.findAllById(id);
        incomeInDB.setSource(income.getSource());
        incomeInDB.setValue(income.getValue());
        incomeInDB.setComment(income.getComment());
        incomeService.updateIncome(incomeInDB);
        return "redirect:/step2";
    }


    @GetMapping("/delete-income/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncomeById(id);
        return "redirect:/step2";
    }
}