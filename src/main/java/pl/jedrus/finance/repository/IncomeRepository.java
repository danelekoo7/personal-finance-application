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

    @Query(value = "SELECT * FROM income JOIN user u on income.user_id = u.id WHERE u.username =:username AND MONTH(current_date_indicator) =:monthId AND YEAR(current_date_indicator) =:yearId", nativeQuery = true)
    List<Income> findAllByUser(@Param("username") String username, @Param("monthId") int monthId, @Param("yearId") int yearId);

    @Query(value = "SELECT DISTINCT current_date_indicator FROM income JOIN user u on income.user_id = u.id WHERE u.username =:username ORDER BY current_date_indicator DESC", nativeQuery = true)
    List<String> findAllDates(@Param("username") String username);

    Optional<Income> findById(Long id);
}
