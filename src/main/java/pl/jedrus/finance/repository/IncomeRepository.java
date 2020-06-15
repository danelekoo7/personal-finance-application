package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jedrus.finance.domain.Income;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

    List<Income> findAllByUser_Username(String username);

    @Query("SELECT SUM(i.value) FROM Income i WHERE i.user.username= :username")
    BigDecimal sumAllIncomesByUser(@Param("username") String username);

}
