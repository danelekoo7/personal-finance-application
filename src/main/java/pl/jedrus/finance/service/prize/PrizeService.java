package pl.jedrus.finance.service.prize;

import pl.jedrus.finance.domain.Prize;


public interface PrizeService {
    Prize findByUser_Username(String username);

    Prize findById(Long id);

    void savePrize(Prize prize, String username);

    void updatePrize(Prize prize);

    void deletePrizeById(Long id);
}
