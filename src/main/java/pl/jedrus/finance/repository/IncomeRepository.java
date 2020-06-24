package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Income;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    @Query( value= "Select *  FROM income WHERE user_id =:userId AND MONTH(current_date_indicator) =:monthId AND YEAR(current_date_indicator) =:yearId", nativeQuery = true)
    List<Income> findAllByUser(@Param("userId") Long userId,@Param("monthId") int monthId,@Param("yearId") int yearId);

    Optional<Income> findById(Long id);
}
