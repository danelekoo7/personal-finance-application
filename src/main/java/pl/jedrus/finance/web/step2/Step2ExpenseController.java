package pl.jedrus.finance.web.step2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.service.expense.ExpenseService;
import pl.jedrus.finance.service.income.IncomeService;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/step2/expense")
public class Step2ExpenseController {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public Step2ExpenseController(IncomeService incomeService, ExpenseService expenseService) {
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }


    @GetMapping("group/{expenseGroup}")
    public String getExpense(@PathVariable int expenseGroup, Model model, @AuthenticationPrincipal UserDetails user) {

        model.addAttribute("incomesSum", incomeService.sumAllIncomesByUser(user.getUsername()));
        model.addAttribute("expensesGroup", expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), expenseGroup));
        model.addAttribute("plannedExpenseGroup", expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), expenseGroup));
        model.addAttribute("realExpenseGroup", expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), expenseGroup));
        model.addAttribute("nextExpenseGroup", expenseService.nextExpenseIdByGroup(user.getUsername(),expenseGroup));
        model.addAttribute("incomeSubExpenseGroup", expenseService.incomeSubExpenseGroup(user.getUsername(), expenseGroup));
        model.addAttribute("incomeSubExpenseGroupBefore", expenseService.incomeSubExpenseGroup(user.getUsername(), expenseGroup-1));

        model.addAttribute("allPlannedExpense",expenseService.sumAllPlannedExpensesByUser(user.getUsername()));
        model.addAttribute("allRealExpense", expenseService.sumAllRealExpensesByUser(user.getUsername()));

        return "step2/expense-group" + expenseGroup;
    }


    @PostMapping("/add/{expenseGroup}")
    public String saveExpense(@PathVariable int expenseGroup, @Valid Expense expense, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2/expense-group" + expenseGroup;

        }
        expenseService.saveExpense(expense, userDetails.getUsername());
        return "redirect:/step2/expense/group/" + expenseGroup;
    }


    @GetMapping("/edit/{id}")
    public String editExpense(@PathVariable Long id, Model model) {
        Expense expense = expenseService.findById(id);
        model.addAttribute("expense", expense);
        return "step2/edit-expense";
    }

    @PostMapping("/edit/{id}")
    public String updateExpense(@Valid Expense expense, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step2/edit-expense";
        }
        expenseService.updateExpense(expense);
        return "redirect:/step2/expense/group/" + expense.getExpenseGroup();
    }


    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        Expense expense = expenseService.findById(id);
        expenseService.deleteExpenseById(id);
        return "redirect:/step2/expense/group/" + expense.getExpenseGroup();
    }


    @ModelAttribute("expense")
    public Expense expense() {
        Expense expense = new Expense();
        expense.setRealValue(BigDecimal.ZERO);
        expense.setPlannedValue(BigDecimal.ZERO);
        return expense;
    }
}