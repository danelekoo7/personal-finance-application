package pl.jedrus.finance.service.dateIndicator;

import pl.jedrus.finance.domain.DateIndicator;

public interface DateIndicatorService {
    DateIndicator findByUser_Username(String username);

    void saveDateIndicator(String yearMonth, String username);

    void updateDateIndicator(String yearMonth, String username);

    void deleteDateIndicatorById(Long id);
}
