package pl.jedrus.finance.service.income;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.repository.IncomeRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository repository;
    private final UserService userService;

    public IncomeServiceImpl(IncomeRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public List<Income> findAllByUser_Username(String username) {
        return repository.findAllByUser_Username(username);
    }

    @Override
    public BigDecimal sumAllIncomesByUser(String username) {
        List<Income> incomeList = findAllByUser_Username(username);
        BigDecimal incomesSum = BigDecimal.ZERO;
        for (Income income : incomeList) {
            incomesSum = incomesSum.add(income.getValue());
        }
        return incomesSum;
    }

    @Override
    public Income findById(Long id) {
        Optional<Income> incomeById = repository.findById(id);
        return incomeById.orElseThrow();
    }

    @Override
    public void saveIncome(Income income, String username) {
        income.setUser(userService.findByUserName(username));
        repository.save(income);
    }

    @Override
    public void updateIncome(Income income, Long id) {
        Income incomeInDB = findById(id);
        incomeInDB.setValue(income.getValue());
        incomeInDB.setComment(income.getComment());
        incomeInDB.setSource(income.getSource());
        repository.save(incomeInDB);
    }

    @Override
    public void deleteIncomeById(Long id) {
        repository.deleteById(id);
    }

}
