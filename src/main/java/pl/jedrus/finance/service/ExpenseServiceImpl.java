package pl.jedrus.finance.service;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Expense;
import pl.jedrus.finance.repository.ExpenseRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository repository;

    public ExpenseServiceImpl(ExpenseRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Expense> findAllByUser_Username(String username) {
        return repository.findAllByUser_Username(username);
    }

    @Override
    public List<Expense> findAllByUser_UsernameAndExpenseGroup(String username, int expenseGroup) {
        return repository.findAllByUser_UsernameAndExpenseGroup(username, expenseGroup);
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUser(String username) {
        BigDecimal plannedExpensesSum = repository.sumAllPlannedExpensesByUser(username);
        if (plannedExpensesSum == null) {
            return BigDecimal.ZERO;
        }
        return plannedExpensesSum;
    }

    @Override
    public BigDecimal sumAllPlannedExpensesByUserAndGroup(String username, int expenseGroup) {
        BigDecimal plannedExpensesByUsernameAndGroup = repository.sumAllPlannedExpensesByUserAndGroup(username, expenseGroup);
        if (plannedExpensesByUsernameAndGroup == null) {
            return BigDecimal.ZERO;
        }
        return plannedExpensesByUsernameAndGroup;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUser(String username) {
        BigDecimal sumAllRealExpensesByUser = repository.sumAllRealExpensesByUser(username);
        if (sumAllRealExpensesByUser == null) {
            return BigDecimal.ZERO;
        }
        return sumAllRealExpensesByUser;
    }

    @Override
    public BigDecimal sumAllRealExpensesByUserAndGroup(String username, int expenseGroup) {
        BigDecimal sumAllRealExpensesByUserAndGroup = repository.sumAllRealExpensesByUserAndGroup(username, expenseGroup);
        if (sumAllRealExpensesByUserAndGroup == null) {
            return BigDecimal.ZERO;
        }
        return sumAllRealExpensesByUserAndGroup;
    }

    @Override
    public Expense findAllById(Long id) {
        Optional<Expense> allById = repository.findAllById(id);
        return allById.orElseThrow();
    }

    @Override
    public void saveExpense(Expense expense) {
        repository.save(expense);
    }

    @Override
    public void updateExpense(Expense expense) {
        repository.save(expense);
    }

    @Override
    public void deleteExpenseById(Long id) {
        repository.deleteById(id);
    }
}
