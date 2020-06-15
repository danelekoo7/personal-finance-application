package pl.jedrus.finance.service;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository repository;

    public IncomeServiceImpl(IncomeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Income> findAllByUser_Username(String username) {
        return repository.findAllByUser_Username(username);
    }

    @Override
    public BigDecimal sumAllIncomesByUser(String username) {
        BigDecimal incomesSum = repository.sumAllIncomesByUser(username);
        if (incomesSum == null) {
            return BigDecimal.ZERO;
        }
        return incomesSum;
    }
}
