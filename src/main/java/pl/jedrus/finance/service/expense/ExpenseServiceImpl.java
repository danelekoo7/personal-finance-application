package pl.jedrus.finance.service.expense;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.DateIndicator;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.repository.ExpenseRepository;
import pl.jedrus.finance.service.DateConverter;
import pl.jedrus.finance.service.dateIndicator.DateIndicatorService;
import pl.jedrus.finance.service.income.IncomeService;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserService userService;
    private final IncomeService incomeService;
    private final DateIndicatorService dateIndicatorService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, UserService userService, IncomeService incomeService, DateIndicatorService dateIndicatorService) {
        this.expenseRepository = expenseRepository;
        this.userService = userService;
        this.incomeService = incomeService;
        this.dateIndicatorService = dateIndicatorService;
    }

    @Override
    public List<Expense> findAllByUser_Username(String username) {
        DateIndicator dateIndicator = dateIndicatorService.findByUser_Username(username);
        int monthId = dateIndicator.getCurrentDateIndicator().getMonthValue();
        int yearId = dateIndicator.getCurrentDateIndicator().getYear();

        return expenseRepository.findAllByUser(username, monthId, yearId);
    }

    @Override
    public List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup) {
        DateIndicator dateIndicator = dateIndicatorService.findByUser_Username(username);
        int monthId = dateIndicator.getCurrentDateIndicator().getMonthValue();
        int yearId = dateIndicator.getCurrentDateIndicator().getYear();

        return expenseRepository.findAllByUser_UsernameAndExpenseGroup(username, monthId, yearId, expenseGroup);
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUser(String username) {
        List<Expense> expenseList = findAllByUser_Username(username);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getPlannedValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUserAndGroup(String username, int expenseGroup) {
        List<Expense> expenseList = findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getPlannedValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUser(String username) {
        List<Expense> expenseList = findAllByUser_Username(username);
        BigDecimal expenseSum = BigDecimal.ZERO;
        for (Expense expense : expenseList) {
            expenseSum = expenseSum.add(expense.getRealValue());
        }
        return expenseSum;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUserAndGroup(String username, int expenseGroup) {
        List<Expense> expenseList = findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
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

        for (int i = 1; i <= expenseGroup; i++) {
            valueToSubtract = valueToSubtract.add(sumAllPlannedExpensesByUserAndGroup(username, i));
        }
        return sumIncomes.subtract(valueToSubtract);
    }

    @Override
    public List<String> findAllDates(String username) {
        return expenseRepository.findAllDates(username);
    }

    @Override
    public int nextExpenseIdByGroup(String username, int expenseGroup) {
        int result = 0;
        for (int i = 1; i <= expenseGroup; i++) {
            result += findAllByUser_UsernameAndExpenseGroup(username, i).size();
        }
        return result + 1;
    }

    @Override
    public Expense findById(Long id) {
        return expenseRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveExpense(Expense expense, String username) {
        expense.setUser(userService.findByUserName(username));
        expense.setCurrentDateIndicator(dateIndicatorService.findByUser_Username(username).getCurrentDateIndicator());
        expenseRepository.save(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        Expense expenseInDB = findById(expense.getId());
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

    @Override
    public void deleteExpenseByDateAndUsername(String yearMonth, String username) {
        LocalDate date = DateConverter.dateFromStringYearMonthToLocalDate(yearMonth);

        List<Expense> expenseList = expenseRepository.findAllByUser(username, date.getMonthValue(), date.getYear());

        for (Expense expense : expenseList) {
            deleteExpenseById(expense.getId());
        }
    }
}
