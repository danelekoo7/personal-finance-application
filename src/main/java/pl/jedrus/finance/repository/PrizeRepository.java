package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Prize;

import java.util.Optional;

public interface PrizeRepository extends JpaRepository<Prize, Long> {
    Optional<Prize> findByUser_Username(String username);

    Optional<Prize> findById(Long id);
}
