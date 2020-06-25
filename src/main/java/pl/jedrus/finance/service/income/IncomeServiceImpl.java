package pl.jedrus.finance.service.income;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.DateIndicator;
import pl.jedrus.finance.domain.Income;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.repository.IncomeRepository;
import pl.jedrus.finance.service.dateIndicator.DateIndicatorService;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    private final IncomeRepository repository;
    private final UserService userService;
    private final DateIndicatorService dateIndicatorService;

    public IncomeServiceImpl(IncomeRepository repository, UserService userService, DateIndicatorService dateIndicatorService) {
        this.repository = repository;
        this.userService = userService;
        this.dateIndicatorService = dateIndicatorService;
    }

    @Override
    public List<Income> findAllByUser_Username(String username) {
        User user = userService.findByUserName(username);
        DateIndicator dateIndicator = dateIndicatorService.findByUser_Username(username);

        int monthId = dateIndicator.getCurrentDateIndicator().getMonthValue();
        int yearId = dateIndicator.getCurrentDateIndicator().getYear();

        return repository.findAllByUser(user.getId(), monthId, yearId);
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
    public List<String> findAllDates(String username) {
        List<String> allDates = repository.findAllDates(userService.findByUserName(username).getId());
        List<String> resultDates = new ArrayList<>();
        for (String date : allDates) {
            String[] singleDate = date.split("-");
            String year = singleDate[0];
            String month = singleDate[1];
            resultDates.add(year+"-"+month);
        }
        return resultDates;
    }

    @Override
    public void saveIncome(Income income, String username) {
        income.setUser(userService.findByUserName(username));
        income.setCurrentDateIndicator(dateIndicatorService.findByUser_Username(username).getCurrentDateIndicator());
        repository.save(income);
    }

    @Override
    public void updateIncome(Income income) {
        Income incomeInDB = findById(income.getId());
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
