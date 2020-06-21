package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.domain.ExpenseRegister;
import pl.jedrus.finance.service.expense.ExpenseService;
import pl.jedrus.finance.service.expenseRegister.ExpenseRegisterService;
import pl.jedrus.finance.service.income.IncomeService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/step2/expense/register")
public class Step2ExpenseRegisterController {

    private final ExpenseRegisterService expenseRegisterService;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public Step2ExpenseRegisterController(ExpenseRegisterService expenseRegisterService, ExpenseService expenseService, IncomeService incomeService) {
        this.expenseRegisterService = expenseRegisterService;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }


    @GetMapping()
    public String getExpenseRegister(Model model, @AuthenticationPrincipal UserDetails user) {

        List<ExpenseRegister> expenseRegisterList = expenseRegisterService.findAllByUser_Username(user.getUsername());

        BigDecimal sumAllExpensesInRegister = expenseRegisterService.sumAllExpensesInRegister(user.getUsername());

        model.addAttribute("expenseRegisterList", expenseRegisterList);
        model.addAttribute("sumAllExpensesInRegister", sumAllExpensesInRegister);
        return "step2/expense-register";
    }


    @PostMapping()
    public String saveExpenseRegister(@Valid ExpenseRegister expenseRegister, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2/expense-register";
        }
        expenseRegisterService.saveExpenseRegister(expenseRegister, userDetails.getUsername());
        return "redirect:/step2/expense/register";
    }


    @GetMapping("/edit/{id}")
    public String editExpenseRegister(@PathVariable Long id, Model model) {
        ExpenseRegister expenseRegister = expenseRegisterService.findById(id);
        model.addAttribute("expenseRegister", expenseRegister);
        return "step2/edit-expense-register";
    }

    @PostMapping("/edit/{id}")
    public String updateExpenseRegister(@Valid ExpenseRegister expenseRegister, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "step2/edit-expense-register";
        }
        expenseRegisterService.updateExpenseRegister(expenseRegister);
        return "redirect:/step2/expense/register";
    }

    @GetMapping("/delete/{id}")
    public String deleteExpenseRegister(@PathVariable Long id) {
        expenseRegisterService.deleteExpenseRegisterById(id);
        return "redirect:/step2/expense/register";
    }


    @ModelAttribute("expenseRegister")
    public ExpenseRegister expenseRegister() {
        ExpenseRegister expenseRegister = new ExpenseRegister();
        expenseRegister.setCreated(LocalDate.now());
        expenseRegister.setValue(BigDecimal.ZERO);
        return expenseRegister;
    }


    @ModelAttribute("expenseGroup1")
    public List<Expense> expenseGroup1(@AuthenticationPrincipal UserDetails user) {
        return expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 1);
    }

    @ModelAttribute("expenseGroup2")
    public List<Expense> expenseGroup2(@AuthenticationPrincipal UserDetails user) {
        return expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 2);
    }

    @ModelAttribute("expenseGroup3")
    public List<Expense> expenseGroup3(@AuthenticationPrincipal UserDetails user) {
        return expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 3);
    }

    @ModelAttribute("expenseGroup4")
    public List<Expense> expenseGroup4(@AuthenticationPrincipal UserDetails user) {
        return expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 4);
    }

    @ModelAttribute("incomesSum")
    public BigDecimal incomesSum(@AuthenticationPrincipal UserDetails user) {
        return incomeService.sumAllIncomesByUser(user.getUsername());
    }

}