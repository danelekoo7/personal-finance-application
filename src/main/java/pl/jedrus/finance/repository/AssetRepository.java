package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jedrus.finance.domain.Asset;

import java.math.BigDecimal;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query("SELECT SUM(value) FROM Asset ")
    BigDecimal sumAllAssets();
}
