package pl.jedrus.finance.service.income;

import pl.jedrus.finance.domain.Income;

import java.math.BigDecimal;
import java.util.List;


public interface IncomeService {
    List<Income> findAllByUser_Username(String username);

    BigDecimal sumAllIncomesByUser(String username);

    Income findById(Long id);


    void saveIncome(Income income, String username);

    void updateIncome(Income income, Long id);

    void deleteIncomeById(Long id);
}
