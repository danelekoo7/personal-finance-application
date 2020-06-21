package pl.jedrus.finance.service.expenseRegister;

import pl.jedrus.finance.domain.ExpenseRegister;

import java.math.BigDecimal;
import java.util.List;


public interface ExpenseRegisterService  {
    List<ExpenseRegister> findAllByUser_Username(String username);

    ExpenseRegister findById(Long id);

    BigDecimal sumAllExpensesInRegister(String username);

    void saveExpenseRegister(ExpenseRegister expenseRegister, String username);

    void updateExpenseRegister(ExpenseRegister expenseRegister);

    void deleteExpenseRegisterById(Long id);




}
