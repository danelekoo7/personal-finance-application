package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jedrus.finance.domain.DateIndicator;

import java.util.Optional;

public interface DateIndicatorRepository extends JpaRepository<DateIndicator,Long> {
   @Query( value= "SELECT * FROM date_indicator JOIN user u on date_indicator.id = u.date_indicator_id WHERE username=:username", nativeQuery = true)
   Optional<DateIndicator> findByUser_Username(@Param("username") String username);
}

