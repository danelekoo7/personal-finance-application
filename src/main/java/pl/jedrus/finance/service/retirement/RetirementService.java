package pl.jedrus.finance.service.retirement;

import pl.jedrus.finance.domain.Retirement;


public interface RetirementService {
    Retirement findByUser_Username(String username);

    Retirement findById(Long id);

    void saveRetirement(Retirement retirement, String username);

    void updateRetirement(Retirement retirement);

    void deleteRetirementById(Long id);
}
