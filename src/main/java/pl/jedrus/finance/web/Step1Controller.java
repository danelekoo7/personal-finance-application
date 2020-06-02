package pl.jedrus.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Asset;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.repository.AssetRepository;
import pl.jedrus.finance.repository.LoanRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/step1")
public class Step1Controller {

    private final LoanRepository loanRepository;
    private final AssetRepository assetRepository;

    public Step1Controller(LoanRepository loanRepository, AssetRepository assetRepository) {
        this.loanRepository = loanRepository;
        this.assetRepository = assetRepository;
    }


    /**
     * @param model
     * @return Display pages
     */
    @GetMapping
    public String get(Model model) {
        List<Loan> allLoans = loanRepository.findAll();
        List<Asset> allAssets = assetRepository.findAll();

        BigDecimal loansSum = loanRepository.sumAllLoans();
        BigDecimal assetsSum = assetRepository.sumAllAssets();

        int nextLoanId = allLoans.size() + 1;
        int nextAssetId = allAssets.size() + 1;

        model.addAttribute("loans", allLoans);
        model.addAttribute("assets", allAssets);
        model.addAttribute("loansSum", loansSum);
        model.addAttribute("assetsSum", assetsSum);
        model.addAttribute("nextLoanId", nextLoanId);
        model.addAttribute("nextAssetId", nextAssetId);

        return "step1";
    }


    //    Assets
    @PostMapping("/add-asset")
    public String saveAsset(@Valid Asset asset, BindingResult result) {
        if (result.hasErrors()) {
            return "step1";
        }
        Asset newAsset = new Asset();
        newAsset.setId(asset.getId());
        newAsset.setDescription(asset.getDescription());
        newAsset.setValue(asset.getValue());

        assetRepository.save(newAsset);
        return "redirect:";
    }


    @GetMapping("/edit-asset/{id}")
    public String editAsset(@PathVariable Long id, Model model) throws Exception {

        Optional<Asset> assetRepositoryById = assetRepository.findById(id);
        Asset asset = assetRepositoryById.orElseThrow(Exception::new);

        model.addAttribute("asset", asset);
        return "edit-asset";
    }

    @PostMapping("/edit-asset/{id}")
    public String updateAsset(@Valid Asset asset, BindingResult result, @PathVariable Long id) throws Exception {
        if (result.hasErrors()) {
            return "edit-asset";
        }

        Optional<Asset> byId = assetRepository.findById(id);
        Asset assetInDB = byId.orElseThrow(Exception::new);

        assetInDB.setId(asset.getId());
        assetInDB.setDescription(asset.getDescription());
        assetInDB.setValue(asset.getValue());

        assetRepository.save(assetInDB);

        return "redirect:/step1";
    }


    @GetMapping("/delete-asset/{id}")
    public String deleteAsset(@PathVariable Long id) {
        assetRepository.deleteById(id);
        return "redirect:/step1";
    }


    //    Loans
    @PostMapping("/add-loan")
    public String saveLoan(@Valid Loan loan, BindingResult result) {
        if (result.hasErrors()) {
            return "step1";
        }
        loanRepository.save(loan);
        return "redirect:";
    }

    @GetMapping("/edit-loan/{id}")
    public String editLoan(@PathVariable Long id, Model model) throws Exception {

        Optional<Loan> byId = loanRepository.findById(id);
        Loan loan = byId.orElseThrow(Exception::new);

        model.addAttribute("loan", loan);
        return "edit-loan";
    }

    @PostMapping("/edit-loan/{id}")
    public String updateLoan(@Valid Loan loan, BindingResult result, @PathVariable Long id) throws Exception {
        if (result.hasErrors()) {
            return "edit-loan";
        }

        Optional<Loan> byId = loanRepository.findById(id);
        Loan loanInDb = byId.orElseThrow(Exception::new);

        loanInDb.setDescription(loan.getDescription());
        loanInDb.setValue(loan.getValue());
        loanInDb.setId(loan.getId());

        loanRepository.save(loanInDb);

        return "redirect:/step1";
    }


    @GetMapping("/delete-loan/{id}")
    public String deleteLoan(@PathVariable Long id) {
        loanRepository.deleteById(id);
        return "redirect:/step1";
    }


}
