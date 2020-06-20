package pl.jedrus.finance.service;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.ExpenseRegister;
import pl.jedrus.finance.repository.ExpenseRegisterRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExpenseRegisterServiceImpl implements ExpenseRegisterService {

    private final ExpenseRegisterRepository repository;

    public ExpenseRegisterServiceImpl(ExpenseRegisterRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ExpenseRegister> findAllByUser_Username(String username) {
        return repository.findAllByUser_Username(username);
    }

    @Override
    public ExpenseRegister findAllById(Long id) {
        return repository.findAllById(id).orElseThrow();
    }

    @Override
    public BigDecimal sumAllExpensesInRegister(String username) {

        BigDecimal sumAllExpensesInRegister = repository.sumAllExpensesInRegister(username);
        if (sumAllExpensesInRegister == null) {
            return BigDecimal.ZERO;
        }
        return sumAllExpensesInRegister;
    }

    @Override
    public void saveExpenseRegister(ExpenseRegister expenseRegister) {
        repository.save(expenseRegister);
    }

    @Override
    public void updateExpenseRegister(ExpenseRegister expenseRegister) {
        repository.save(expenseRegister);
    }

    @Override
    public void deleteExpenseRegisterById(Long id) {
        repository.deleteById(id);
    }
}
