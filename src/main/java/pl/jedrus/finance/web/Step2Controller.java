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
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;
import pl.jedrus.finance.service.ExpenseService;
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
    private final ExpenseService expenseService;

    public Step2Controller(IncomeRepository incomeRepository, IncomeService incomeService, UserService userService, ExpenseService expenseService) {
        this.incomeService = incomeService;
        this.userService = userService;
        this.expenseService = expenseService;
    }

    @GetMapping
    public String get(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());

        List<Expense> expenseGroup1 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 1);
        List<Expense> expenseGroup2 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 2);
        List<Expense> expenseGroup3 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 3);
        List<Expense> expenseGroup4 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 4);

        BigDecimal sumAllPlannedExpensesByUser = expenseService.sumAllPlannedExpensesByUser(user.getUsername());
        BigDecimal sumAllRealExpensesByUser = expenseService.sumAllRealExpensesByUser(user.getUsername());

        BigDecimal sumAllPlannedExpensesByUserAndGroup1 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 1);
        BigDecimal sumAllRealExpensesByUserAndGroup1 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 1);
        BigDecimal sumAllPlannedExpensesByUserAndGroup2 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 2);
        BigDecimal sumAllRealExpensesByUserAndGroup2 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 2);
        BigDecimal sumAllPlannedExpensesByUserAndGroup3 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 3);
        BigDecimal sumAllRealExpensesByUserAndGroup3 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 3);
        BigDecimal sumAllPlannedExpensesByUserAndGroup4 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 4);
        BigDecimal sumAllRealExpensesByUserAndGroup4 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 4);


        model.addAttribute("incomes", allIncomes);
        model.addAttribute("incomesSum", incomesSum);
        model.addAttribute("nextIncomeId", allIncomes.size() + 1);

        model.addAttribute("expensesGroup1", expenseGroup1);
        model.addAttribute("plannedExpenseGroup1", sumAllPlannedExpensesByUserAndGroup1);
        model.addAttribute("realExpenseGroup1", sumAllRealExpensesByUserAndGroup1);
        model.addAttribute("nextExpenseGroup1", expenseGroup1.size() + 1);




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