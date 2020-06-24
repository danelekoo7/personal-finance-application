package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.DateIndicator;

import java.util.Optional;

public interface DateIndicatorRepository extends JpaRepository<DateIndicator,Long> {
   Optional<DateIndicator> findByUser_Username(String username);
}
