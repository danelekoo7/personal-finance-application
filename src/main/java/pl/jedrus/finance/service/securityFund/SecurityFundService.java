package pl.jedrus.finance.service.securityFund;

import pl.jedrus.finance.domain.SecurityFund;


public interface SecurityFundService {
    SecurityFund findByUser_Username(String username);

    SecurityFund findById(Long id);

    void saveSecurityFund(SecurityFund securityFund, String username);

    void updateSecurityFund(SecurityFund securityFund);

    void deleteSecurityFundById(Long id);
}
