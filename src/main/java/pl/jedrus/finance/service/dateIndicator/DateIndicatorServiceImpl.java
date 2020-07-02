package pl.jedrus.finance.service.dateIndicator;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.DateIndicator;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.DateIndicatorRepository;
import pl.jedrus.finance.service.DateConverter;
import pl.jedrus.finance.service.expense.ExpenseService;
import pl.jedrus.finance.service.income.IncomeService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class DateIndicatorServiceImpl implements DateIndicatorService {

    private final DateIndicatorRepository dateIndicatorRepository;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;

    public DateIndicatorServiceImpl(DateIndicatorRepository dateIndicatorRepository, @Lazy IncomeService incomeService, @Lazy ExpenseService expenseService) {
        this.dateIndicatorRepository = dateIndicatorRepository;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
    }


    @Override
    public DateIndicator findByUser_Username(String username) {
        return dateIndicatorRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public List<String> findAllDates(String username) {
        List<String> result = new ArrayList<>();
        List<String> allDatesByIncome = DateConverter.datesListFromStringDateToStringYearMonth(incomeService.findAllDates(username));
        List<String> allDatesByExpense = DateConverter.datesListFromStringDateToStringYearMonth(expenseService.findAllDates(username));
        result.addAll(allDatesByIncome);
        result.addAll(allDatesByExpense);
        result.sort((o1, o2) -> o2.compareTo(o1));

        return result;
    }

    @Override
    public String findCurrentYearMonthByUser(String username) {
        String dateInString = findByUser_Username(username).getCurrentDateIndicator().toString();
        return DateConverter.dateFromStringDateToStringYearMonth(dateInString);
    }

    @Override
    public DateIndicator saveDateIndicator(String username) {
        DateIndicator dateIndicator = new DateIndicator();
        dateIndicator.setCurrentDateIndicator(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1));
        dateIndicatorRepository.save(dateIndicator);
        return dateIndicator;
    }


    @Override
    public void updateDateIndicator(String yearMonth, String username) {
        DateIndicator dateIndicatorInDB = findByUser_Username(username);
        LocalDate newDate = DateConverter.dateFromStringYearMonthToLocalDate(yearMonth);
        dateIndicatorInDB.setCurrentDateIndicator(newDate);
        dateIndicatorRepository.save(dateIndicatorInDB);
    }

    @Override
    public void addDateIndicator(String yearMonth, String username) {
        List<Income> incomes = incomeService.findAllByUser_Username(username);
        List<Expense> expenses = expenseService.findAllByUser_Username(username);

        DateIndicator dateIndicatorInDB = findByUser_Username(username);
        LocalDate newDate = DateConverter.dateFromStringYearMonthToLocalDate(yearMonth);
        dateIndicatorInDB.setCurrentDateIndicator(newDate);
        dateIndicatorRepository.save(dateIndicatorInDB);

        List<String> allDates = findAllDates(username);
        if (!allDates.contains(yearMonth)) {
            for (Income income : incomes) {
                Income newIncome = new Income();
                newIncome.setValue(income.getValue());
                newIncome.setSource(income.getSource());
                newIncome.setComment(income.getComment());
                newIncome.setCurrentDateIndicator(newDate);
                incomeService.saveIncome(newIncome, username);
            }

            for (Expense expense : expenses) {
                Expense newExpense = new Expense();
                newExpense.setCurrentDateIndicator(newDate);
                newExpense.setPlannedValue(expense.getPlannedValue());
                newExpense.setExpenseGroup(expense.getExpenseGroup());
                newExpense.setExpenseType(expense.getExpenseType());
                newExpense.setComment(expense.getComment());
                newExpense.setRealValue(BigDecimal.ZERO);
                expenseService.saveExpense(newExpense, username);
            }
        }
    }

    @Override
    public void deleteDateIndicatorById(String yearMonth, String username) {

        expenseService.deleteExpenseByDateAndUsername(yearMonth, username);

        incomeService.deleteIncomeByDateAndUsername(yearMonth, username);


        DateIndicator dateIndicatorInDB = findByUser_Username(username);

        if (DateConverter.dateFromStringYearMonthToLocalDate(yearMonth).equals(dateIndicatorInDB.getCurrentDateIndicator())) {
            TreeSet<String> allDates = (TreeSet<String>) findAllDates(username);
            LocalDate newDate = DateConverter.dateFromStringYearMonthToLocalDate(allDates.first());
            dateIndicatorInDB.setCurrentDateIndicator(newDate);
            dateIndicatorRepository.save(dateIndicatorInDB);
        }
    }
}
