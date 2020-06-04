package pl.jedrus.finance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.jedrus.finance.domain.Asset;

import java.math.BigDecimal;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    @Query(value = "SELECT * FROM asset JOIN user u ON asset.id_user = u.id WHERE username= ?1", nativeQuery = true)
    List<Asset> findAllByUserName (String userName);

    @Query(value = "SELECT SUM(value) FROM asset JOIN user u ON asset.id_user = u.id WHERE username= ?1", nativeQuery = true)
    BigDecimal sumAllAssetsByUserName(String userName);

}
