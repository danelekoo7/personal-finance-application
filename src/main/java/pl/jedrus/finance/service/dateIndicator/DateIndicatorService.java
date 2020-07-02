package pl.jedrus.finance.service.dateIndicator;

import pl.jedrus.finance.domain.DateIndicator;

import java.util.List;

public interface DateIndicatorService {
    DateIndicator findByUser_Username(String username);

    List<String> findAllDates(String username);

    String findCurrentYearMonthByUser(String username);

    DateIndicator saveDateIndicator(String username);

    void updateDateIndicator(String yearMonth, String username);


    void addDateIndicator(String yearMonth, String username);

    void deleteDateIndicatorById(String yearMonth, String username);
}
