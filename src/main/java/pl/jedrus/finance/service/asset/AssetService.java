package pl.jedrus.finance.service.asset;

import pl.jedrus.finance.domain.Asset;

import java.math.BigDecimal;
import java.util.List;

public interface AssetService {
    List<Asset> findAllByUser_Username(String username);

    BigDecimal sumAllAssetByUser(String username);

    Asset findById(Long id);

    void saveAsset(Asset asset);

    void updateAsset(Asset asset);

    void deleteAssetById(Long id);
}
