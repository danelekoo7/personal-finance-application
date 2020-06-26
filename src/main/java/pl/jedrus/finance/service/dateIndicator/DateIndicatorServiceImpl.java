package pl.jedrus.finance.service.dateIndicator;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.DateIndicator;
import pl.jedrus.finance.repository.DateIndicatorRepository;
import pl.jedrus.finance.service.income.IncomeService;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class DateIndicatorServiceImpl implements DateIndicatorService {

    private final DateIndicatorRepository dateIndicatorRepository;
    private  final IncomeService incomeService;

    public DateIndicatorServiceImpl(DateIndicatorRepository dateIndicatorRepository, @Lazy IncomeService incomeService) {
        this.dateIndicatorRepository = dateIndicatorRepository;
        this.incomeService = incomeService;
    }



    @Override
    public DateIndicator findByUser_Username(String username) {
        return dateIndicatorRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public Set<String> findAllDates(String username) {

        Set<String> result = new LinkedHashSet<>();

        List<String> allDatesByIncome = incomeService.findAllDates(username);

        result.addAll(allDatesByIncome);

        return result;
    }

    @Override
    public String findCurrentYearMonthByUser(String username) {
        String dateInString = findByUser_Username(username).getCurrentDateIndicator().toString();
        String[] split = dateInString.split("-");
        String year = split[0];
        String month = split[1];
        return year+"-"+month;
    }

    @Override
    public DateIndicator saveDateIndicator(String username) {
        DateIndicator dateIndicator = new DateIndicator();
        dateIndicator.setCurrentDateIndicator(LocalDate.of(LocalDate.now().getYear(),LocalDate.now().getMonthValue(),1));
        dateIndicatorRepository.save(dateIndicator);
        return dateIndicator;
    }


    @Override
    public void updateDateIndicator(String yearMonth, String username) {
        DateIndicator dateIndicatorInDB = findByUser_Username(username);
        dateIndicatorInDB.setCurrentDateIndicator(getDateIndicator(yearMonth));
        dateIndicatorRepository.save(dateIndicatorInDB);
    }

    @Override
    public void deleteDateIndicatorById(Long id) {
        dateIndicatorRepository.deleteById(id);
    }



    private LocalDate getDateIndicator(String yearMonth) {
        String[] yearMonthValue = yearMonth.split("-");
        int year = -1;
        int month = -1;
        try {
            year = Integer.parseInt(yearMonthValue[0]);
            month = Integer.parseInt(yearMonthValue[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return  LocalDate.of(year, month, 1);
    }
}
