package pl.jedrus.finance.service;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Income findAllById(Long id) {
        Optional<Income> incomeById = repository.findAllById(id);
        return incomeById.orElseThrow();
    }

    @Override
    public void saveIncome(Income income) {
        repository.save(income);
    }

    @Override
    public void updateIncome(Income income) {
        repository.save(income);
    }

    @Override
    public void deleteIncomeById(Long id) {
        repository.deleteById(id);
    }

}
