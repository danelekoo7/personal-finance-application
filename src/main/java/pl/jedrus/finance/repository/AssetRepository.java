package pl.jedrus.finance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Asset;

import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    List<Asset> findAllByUser_Username(String username);
}
