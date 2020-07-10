package pl.jedrus.finance.service.retirement;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Retirement;
import pl.jedrus.finance.repository.RetirementRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;

@Service
public class RetirementImpl implements RetirementService {


    private final RetirementRepository retirementRepository;
    private final UserService userService;

    public RetirementImpl(RetirementRepository retirementRepository, UserService userService) {
        this.retirementRepository = retirementRepository;
        this.userService = userService;
    }

    @Override
    public Retirement findByUser_Username(String username) {
        return retirementRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public Retirement findById(Long id) {
        return retirementRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveRetirement(Retirement retirement, String username) {
        retirement.setUser(userService.findByUserName(username));
        retirement.setExpectedValue(BigDecimal.ZERO);
        retirement.setCurrentValue(BigDecimal.ZERO);
        retirementRepository.save(retirement);
    }

    @Override
    public void updateRetirement(Retirement retirement) {
        Retirement retirementInDB = findById(retirement.getId());
        retirementInDB.setExpectedValue(retirement.getExpectedValue());
        retirementInDB.setCurrentValue(retirement.getCurrentValue());
        retirementInDB.setDescription(retirement.getDescription());
        retirementRepository.save(retirementInDB);
    }

    @Override
    public void deleteRetirementById(Long id) {
        retirementRepository.deleteById(id);
    }
}
