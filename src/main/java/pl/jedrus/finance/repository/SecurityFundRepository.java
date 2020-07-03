package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.SecurityFund;

import java.util.Optional;

public interface SecurityFundRepository extends JpaRepository<SecurityFund,Long> {
    Optional<SecurityFund> findByUser_Username(String username);

    Optional<SecurityFund> findById(Long id);
}
