package pl.jedrus.finance.service.securityFund;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.SecurityFund;
import pl.jedrus.finance.repository.SecurityFundRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;

@Service
public class SecurityFundImpl implements SecurityFundService {


    private final SecurityFundRepository securityFundRepository;
    private final UserService userService;

    public SecurityFundImpl(SecurityFundRepository securityFundRepository, UserService userService) {
        this.securityFundRepository = securityFundRepository;
        this.userService = userService;
    }

    @Override
    public SecurityFund findByUser_Username(String username) {
        return securityFundRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public SecurityFund findById(Long id) {
        return securityFundRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveSecurityFund(SecurityFund securityFund, String username) {
        securityFund.setUser(userService.findByUserName(username));
        securityFund.setExpectedValue(BigDecimal.ZERO);
        securityFund.setCurrentValue(BigDecimal.ZERO);
        securityFundRepository.save(securityFund);
    }

    @Override
    public void updateSecurityFund(SecurityFund securityFund) {
        SecurityFund securityFundInDB = findById(securityFund.getId());
        securityFundInDB.setExpectedValue(securityFund.getExpectedValue());
        securityFundInDB.setCurrentValue(securityFund.getCurrentValue());
        securityFundRepository.save(securityFundInDB);
    }

    @Override
    public void deleteSecurityFundById(Long id) {
        securityFundRepository.deleteById(id);
    }
}
