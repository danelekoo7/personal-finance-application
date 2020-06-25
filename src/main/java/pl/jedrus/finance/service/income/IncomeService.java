package pl.jedrus.finance.service.income;

import pl.jedrus.finance.domain.Income;

import java.math.BigDecimal;
import java.util.List;


public interface IncomeService {
    List<Income> findAllByUser_Username(String username);

    BigDecimal sumAllIncomesByUser(String username);

    Income findById(Long id);

    List<String> findAllDates(String username);


    void saveIncome(Income income, String username);

    void updateIncome(Income income);

    void deleteIncomeById(Long id);
}
