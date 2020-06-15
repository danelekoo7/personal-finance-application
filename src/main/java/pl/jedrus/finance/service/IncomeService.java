package pl.jedrus.finance.service;

import pl.jedrus.finance.domain.Income;

import java.math.BigDecimal;
import java.util.List;


public interface IncomeService {
    List<Income> findAllByUser_Username(String username);

    BigDecimal sumAllIncomesByUser(String username);
}
