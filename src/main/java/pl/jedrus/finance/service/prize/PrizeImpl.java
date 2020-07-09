package pl.jedrus.finance.service.prize;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Prize;
import pl.jedrus.finance.repository.PrizeRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;

@Service
public class PrizeImpl implements PrizeService {


    private final PrizeRepository prizeRepository;
    private final UserService userService;

    public PrizeImpl(PrizeRepository prizeRepository, UserService userService) {
        this.prizeRepository = prizeRepository;
        this.userService = userService;
    }

    @Override
    public Prize findByUser_Username(String username) {
        return prizeRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public Prize findById(Long id) {
        return prizeRepository.findById(id).orElseThrow();
    }

    @Override
    public void savePrize(Prize prize, String username) {
        prize.setUser(userService.findByUserName(username));
        prize.setExpectedValue(BigDecimal.ZERO);
        prize.setCurrentValue(BigDecimal.ZERO);
        prizeRepository.save(prize);
    }

    @Override
    public void updatePrize(Prize prize) {
        Prize prizeInDB = findById(prize.getId());
        prizeInDB.setExpectedValue(prize.getExpectedValue());
        prizeInDB.setCurrentValue(prize.getCurrentValue());
        prizeInDB.setDescription(prize.getDescription());
        prizeRepository.save(prizeInDB);
    }

    @Override
    public void deletePrizeById(Long id) {
        prizeRepository.deleteById(id);
    }
}
