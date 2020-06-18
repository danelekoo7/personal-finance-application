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

//    @GetMapping
//    public String get(Model model, @AuthenticationPrincipal UserDetails user) {
//        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
//        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());
//
//        List<Expense> expenseGroup1 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 1);
//        List<Expense> expenseGroup2 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 2);
//        List<Expense> expenseGroup3 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 3);
//        List<Expense> expenseGroup4 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 4);
//
//        BigDecimal sumAllPlannedExpensesByUser = expenseService.sumAllPlannedExpensesByUser(user.getUsername());
//        BigDecimal sumAllRealExpensesByUser = expenseService.sumAllRealExpensesByUser(user.getUsername());
//
//        BigDecimal sumAllPlannedExpensesByUserAndGroup1 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 1);
//        BigDecimal sumAllRealExpensesByUserAndGroup1 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 1);
//        BigDecimal sumAllPlannedExpensesByUserAndGroup2 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 2);
//        BigDecimal sumAllRealExpensesByUserAndGroup2 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 2);
//        BigDecimal sumAllPlannedExpensesByUserAndGroup3 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 3);
//        BigDecimal sumAllRealExpensesByUserAndGroup3 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 3);
//        BigDecimal sumAllPlannedExpensesByUserAndGroup4 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 4);
//        BigDecimal sumAllRealExpensesByUserAndGroup4 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 4);
//
//
//        model.addAttribute("incomes", allIncomes);
//        model.addAttribute("incomesSum", incomesSum);
//        model.addAttribute("nextIncomeId", allIncomes.size() + 1);
//
//        model.addAttribute("expensesGroup1", expenseGroup1);
//        model.addAttribute("plannedExpenseGroup1", sumAllPlannedExpensesByUserAndGroup1);
//        model.addAttribute("realExpenseGroup1", sumAllRealExpensesByUserAndGroup1);
//        model.addAttribute("nextExpenseGroup1", expenseGroup1.size() + 1);
//        model.addAttribute("incomeSubExpenseGroup1", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1));
//
//
//        model.addAttribute("expensesGroup2", expenseGroup2);
//        model.addAttribute("plannedExpenseGroup2", sumAllPlannedExpensesByUserAndGroup2);
//        model.addAttribute("realExpenseGroup2", sumAllRealExpensesByUserAndGroup2);
//        model.addAttribute("nextExpenseGroup2", expenseGroup1.size() + expenseGroup2.size() + 1);
//
//        model.addAttribute("expensesGroup3", expenseGroup3);
//        model.addAttribute("plannedExpenseGroup3", sumAllPlannedExpensesByUserAndGroup3);
//        model.addAttribute("realExpenseGroup3", sumAllRealExpensesByUserAndGroup3);
//        model.addAttribute("nextExpenseGroup3", expenseGroup1.size() + expenseGroup2.size() + expenseGroup3.size() + 1);
//
//        model.addAttribute("expensesGroup4", expenseGroup4);
//        model.addAttribute("plannedExpenseGroup4", sumAllPlannedExpensesByUserAndGroup4);
//        model.addAttribute("realExpenseGroup4", sumAllRealExpensesByUserAndGroup4);
//        model.addAttribute("nextExpenseGroup4", expenseGroup1.size() + expenseGroup2.size() + expenseGroup3.size() + expenseGroup4.size() + 1);
//
//        model.addAttribute("allPlannedExpense", sumAllPlannedExpensesByUser);
//        model.addAttribute("allRealExpense", sumAllRealExpensesByUser);
//
//        return "step2";
//    }
//

    //    Incomes
    @GetMapping("/income")
    public String getIncome(Model model, @AuthenticationPrincipal UserDetails user) {
        List<Income> allIncomes = incomeService.findAllByUser_Username(user.getUsername());
        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());

        model.addAttribute("incomes", allIncomes);
        model.addAttribute("incomesSum", incomesSum);
        model.addAttribute("nextIncomeId", allIncomes.size() + 1);
        return "step2/income";
    }


    @PostMapping("/add-income")
    public String saveIncome(@Valid Income income, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2/income";
        }

        Income newIncome = new Income();
        newIncome.setId(income.getId());
        newIncome.setSource(income.getSource());
        newIncome.setValue(income.getValue());
        newIncome.setComment(income.getComment());
        newIncome.setUser(userService.findByUserName(userDetails.getUsername()));
        incomeService.saveIncome(newIncome);
        return "redirect:/step2/income";
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
        return "redirect:/step2/income";
    }


    @GetMapping("/delete-income/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncomeById(id);
        return "redirect:/step2/income";
    }

    //    Expenses

//    @GetMapping("expense-group/1")
//    public String getExpenseGroup1(Model model, @AuthenticationPrincipal UserDetails user) {
//        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());
//
//        List<Expense> expenseGroup1 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 1);
//
//        BigDecimal sumAllPlannedExpensesByUserAndGroup1 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 1);
//        BigDecimal sumAllRealExpensesByUserAndGroup1 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 1);
//
//        model.addAttribute("incomesSum", incomesSum);
//        model.addAttribute("expensesGroup1", expenseGroup1);
//        model.addAttribute("plannedExpenseGroup1", sumAllPlannedExpensesByUserAndGroup1);
//        model.addAttribute("realExpenseGroup1", sumAllRealExpensesByUserAndGroup1);
//        model.addAttribute("nextExpenseGroup1", expenseGroup1.size() + 1);
//        model.addAttribute("incomeSubExpenseGroup1", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1));
//        return "step2/expense-group1";
//    }
//
//
//    @GetMapping("expense-group/2")
//    public String getExpenseGroup2(Model model, @AuthenticationPrincipal UserDetails user) {
//        BigDecimal incomesSum = incomeService.sumAllIncomesByUser(user.getUsername());
//
//        List<Expense> expenseGroup1 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 1);
//        List<Expense> expenseGroup2 = expenseService.findAllByUser_UsernameAndExpenseGroup(user.getUsername(), 2);
//
//        BigDecimal sumAllPlannedExpensesByUserAndGroup1 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 1);
//        BigDecimal sumAllPlannedExpensesByUserAndGroup2 = expenseService.sumAllPlannedExpensesByUserAndGroup(user.getUsername(), 2);
//        BigDecimal sumAllRealExpensesByUserAndGroup2 = expenseService.sumAllRealExpensesByUserAndGroup(user.getUsername(), 2);
//
//        model.addAttribute("nextExpenseGroup1", expenseGroup1.size() + 1);
//        model.addAttribute("incomeSubExpenseGroup1", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1));
//        model.addAttribute("expensesGroup2", expenseGroup2);
//        model.addAttribute("plannedExpenseGroup2", sumAllPlannedExpensesByUserAndGroup2);
//        model.addAttribute("realExpenseGroup2", sumAllRealExpensesByUserAndGroup2);
//        model.addAttribute("nextExpenseGroup2", expenseGroup1.size() + expenseGroup2.size() + 1);
//        model.addAttribute("incomeSubExpenseGroup2", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1).subtract(sumAllPlannedExpensesByUserAndGroup2));
//        return "step2/expense-group2";
//    }


    @GetMapping("expense-group/{expenseGroup}")
    public String get(@PathVariable int expenseGroup, Model model, @AuthenticationPrincipal UserDetails user) {
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
        model.addAttribute("incomeSubExpenseGroup1", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1));


        model.addAttribute("expensesGroup2", expenseGroup2);
        model.addAttribute("plannedExpenseGroup2", sumAllPlannedExpensesByUserAndGroup2);
        model.addAttribute("realExpenseGroup2", sumAllRealExpensesByUserAndGroup2);
        model.addAttribute("nextExpenseGroup2", expenseGroup1.size() + expenseGroup2.size() + 1);
        model.addAttribute("incomeSubExpenseGroup2", incomesSum.subtract(sumAllPlannedExpensesByUserAndGroup1).subtract(sumAllPlannedExpensesByUserAndGroup2));

        model.addAttribute("expensesGroup3", expenseGroup3);
        model.addAttribute("plannedExpenseGroup3", sumAllPlannedExpensesByUserAndGroup3);
        model.addAttribute("realExpenseGroup3", sumAllRealExpensesByUserAndGroup3);
        model.addAttribute("nextExpenseGroup3", expenseGroup1.size() + expenseGroup2.size() + expenseGroup3.size() + 1);

        model.addAttribute("expensesGroup4", expenseGroup4);
        model.addAttribute("plannedExpenseGroup4", sumAllPlannedExpensesByUserAndGroup4);
        model.addAttribute("realExpenseGroup4", sumAllRealExpensesByUserAndGroup4);
        model.addAttribute("nextExpenseGroup4", expenseGroup1.size() + expenseGroup2.size() + expenseGroup3.size() + expenseGroup4.size() + 1);

        model.addAttribute("allPlannedExpense", sumAllPlannedExpensesByUser);
        model.addAttribute("allRealExpense", sumAllRealExpensesByUser);

        return "step2/expense-group" + expenseGroup;
    }


    @PostMapping("/add-expense/{expenseGroup}")
    public String saveExpense(@PathVariable int expenseGroup, @Valid Expense expense, BindingResult result, @AuthenticationPrincipal UserDetails userDetails) {
        if (result.hasErrors()) {
            return "step2/expense-group" + expenseGroup;

        }

        Expense newExpense = new Expense();
        newExpense.setId(expense.getId());
        newExpense.setExpenseGroup(expenseGroup);
        newExpense.setComment(expense.getComment());
        newExpense.setExpenseType(expense.getExpenseType());
        newExpense.setPlannedValue(expense.getPlannedValue());

        newExpense.setRealValue(expense.getRealValue());
        newExpense.setUser(userService.findByUserName(userDetails.getUsername()));
        expenseService.saveExpense(newExpense);

        return "redirect:/step2/expense-group/" + expenseGroup;
    }


    @GetMapping("/edit-expense/{id}")
    public String editExpense(@PathVariable Long id, Model model) {

        Expense expense = expenseService.findAllById(id);


        model.addAttribute("expense", expense);
        return "edit-expense";
    }

    @PostMapping("/edit-expense/{id}")
    public String updateExpense(@Valid Expense expense, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "edit-expense";
        }

        Expense expenseInDB = expenseService.findAllById(id);
        expenseInDB.setExpenseType(expense.getExpenseType());
        expenseInDB.setPlannedValue(expense.getPlannedValue());
        expenseInDB.setRealValue(expense.getRealValue());
        expenseInDB.setComment(expense.getComment());
        expenseInDB.setExpenseGroup(expense.getExpenseGroup());

        expenseService.updateExpense(expenseInDB);
        return "redirect:/step2/expense-group/" + expenseInDB.getExpenseGroup();
    }


    @GetMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Long id) {
        Expense expense = expenseService.findAllById(id);
        expenseService.deleteExpenseById(id);
        return "redirect:/step2/expense-group/" + expense.getExpenseGroup();
    }


}