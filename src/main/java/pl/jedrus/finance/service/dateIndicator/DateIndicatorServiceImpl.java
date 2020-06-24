package pl.jedrus.finance.service.dateIndicator;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.DateIndicator;
import pl.jedrus.finance.repository.DateIndicatorRepository;
import pl.jedrus.finance.service.user.UserService;

import java.time.LocalDate;

@Service
public class DateIndicatorServiceImpl implements DateIndicatorService {

    private final DateIndicatorRepository dateIndicatorRepository;
    private final UserService userService;

    public DateIndicatorServiceImpl(DateIndicatorRepository dateIndicatorRepository, UserService userService) {
        this.dateIndicatorRepository = dateIndicatorRepository;
        this.userService = userService;
    }

    @Override
    public DateIndicator findByUser_Username(String username) {
        return dateIndicatorRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public void saveDateIndicator(DateIndicator dateIndicator, String username) {
        dateIndicator.setUser(userService.findByUserName(username));
        if (dateIndicator.getCurrentDateIndicator() == null) {
            dateIndicator.setCurrentDateIndicator(LocalDate.now());
        }
        dateIndicatorRepository.save(dateIndicator);
    }

    @Override
    public void updateDateIndicator(DateIndicator dateIndicator) {
        DateIndicator dateIndicatorInDB = findByUser_Username(dateIndicator.getUser().getUsername());
        dateIndicatorInDB.setCurrentDateIndicator(dateIndicator.getCurrentDateIndicator());
        dateIndicatorRepository.save(dateIndicatorInDB);
    }

    @Override
    public void deleteDateIndicatorById(Long id) {
        dateIndicatorRepository.deleteById(id);
    }
}
