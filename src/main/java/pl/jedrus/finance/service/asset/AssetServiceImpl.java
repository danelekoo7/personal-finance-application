package pl.jedrus.finance.service.asset;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final UserService userService;

    public AssetServiceImpl(AssetRepository repository, UserService userService) {
        this.assetRepository = repository;
        this.userService = userService;
    }

    @Override
    public List<Asset> findAllByUser_Username(String username) {
        return assetRepository.findAllByUser_Username(username);
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
        return assetRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveAsset(Asset asset, String username) {
        asset.setUser(userService.findByUserName(username));
        assetRepository.save(asset);
    }

    @Override
    public void updateAsset(Asset asset) {
        Asset assetInDb = findById(asset.getId());
        assetInDb.setValue(asset.getValue());
        assetInDb.setDescription(asset.getDescription());
        assetRepository.save(assetInDb);
    }

    @Override
    public void deleteAssetById(Long id) {
        assetRepository.deleteById(id);
    }
}
