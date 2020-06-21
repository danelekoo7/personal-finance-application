package pl.jedrus.finance.service.asset;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.repository.AssetRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;

    public AssetServiceImpl(AssetRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Asset> findAllByUser_Username(String username) {
        return repository.findAllByUser_Username(username);
    }

    @Override
    public BigDecimal sumAllAssetByUser(String username) {
        List<Asset> assetList = findAllByUser_Username(username);
        BigDecimal assetSum = BigDecimal.ZERO;
        for (Asset asset : assetList) {
            assetSum = assetSum.add(asset.getValue());
        }
        return assetSum;
    }

    @Override
    public Asset findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public void saveAsset(Asset asset) {
        repository.save(asset);
    }

    @Override
    public void updateAsset(Asset asset) {
        repository.save(asset);
    }

    @Override
    public void deleteAssetById(Long id) {
        repository.deleteById(id);
    }
}
