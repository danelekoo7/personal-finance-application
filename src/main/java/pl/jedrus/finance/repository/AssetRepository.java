package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Asset;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
