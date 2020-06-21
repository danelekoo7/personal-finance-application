package pl.jedrus.finance.service.expense;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.repository.ExpenseRepository;
import pl.jedrus.finance.service.income.IncomeService;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final IncomeService incomeService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService, IncomeService incomeService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.incomeService = incomeService;
    }

    @Override
    public List<Expense> findAllByUser_Username(String username) {
        return expenseRepository.findAllByUser_Username(username);
    }

    @Override
    public List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup) {
        return expenseRepository.findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUser(String username) {
        List<Expense> expenseList = expenseRepository.findAllByUser_Username(username);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getPlannedValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUserAndGroup(String username, int expenseGroup) {
        List<Expense> expenseList = expenseRepository.findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getPlannedValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUser(String username) {
        List<Expense> expenseList = expenseRepository.findAllByUser_Username(username);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getRealValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUserAndGroup(String username, int expenseGroup) {
        List<Expense> expenseList = expenseRepository.findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getRealValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal incomeSubExpenseGroup(String username, int expenseGroup) {
        BigDecimal sumIncomes = incomeService.sumAllIncomesByUser(username);

        BigDecimal valueToSubtract = BigDecimal.ZERO;

        for (int i = 1; i <= expenseGroup ; i++) {
            valueToSubtract= valueToSubtract.add(sumAllPlannedExpensesByUserAndGroup(username,i));
        }
        return sumIncomes.subtract(valueToSubtract);
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveExpense(Expense expense, String username) {
        expense.setUser(userService.findByUserName(username));
        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(Expense expense, Long id) {
        Expense expenseInDB = findById(id);
        expenseInDB.setRealValue(expense.getRealValue());
        expenseInDB.setPlannedValue(expense.getPlannedValue());
        expenseInDB.setExpenseGroup(expense.getExpenseGroup());
        expenseInDB.setComment(expense.getComment());
        expenseInDB.setExpenseType(expense.getExpenseType());
        expenseRepository.save(expenseInDB);
    }

    @Override
    public void deleteExpenseById(Long id) {
        expenseRepository.deleteById(id);
    }
}
