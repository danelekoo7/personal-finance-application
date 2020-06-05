package pl.jedrus.finance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.jedrus.finance.domain.Asset;


import java.math.BigDecimal;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAllByUser_Username(String username);

    @Query("SELECT SUM(a.value) FROM Asset a WHERE a.user.username= :username")
    BigDecimal sumAllAssetByUser(@Param("username") String username);

}
