package pl.jedrus.finance.service.dateIndicator;

import pl.jedrus.finance.domain.DateIndicator;

import java.util.Set;

public interface DateIndicatorService {
    DateIndicator findByUser_Username(String username);

    Set<String> findAllDates(String username);

    String findCurrentYearMonthByUser(String username);

    DateIndicator saveDateIndicator(String username);

    void updateDateIndicator(String yearMonth, String username);


    void addDateIndicator(String yearMonth, String username);

    void deleteDateIndicatorById(Long id);
}
