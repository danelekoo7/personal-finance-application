package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Retirement;

import java.util.Optional;

public interface RetirementRepository extends JpaRepository<Retirement, Long> {
    Optional<Retirement> findByUser_Username(String username);

    Optional<Retirement> findById(Long id);
}
