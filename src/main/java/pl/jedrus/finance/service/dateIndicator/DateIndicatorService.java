package pl.jedrus.finance.service.dateIndicator;

import pl.jedrus.finance.domain.DateIndicator;

public interface DateIndicatorService {
    DateIndicator findByUser_Username(String username);

    void saveDateIndicator(DateIndicator dateIndicator, String username);

    void updateDateIndicator(DateIndicator dateIndicator);

    void deleteDateIndicatorById(Long id);
}
