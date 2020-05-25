package pl.jedrus.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.domain.Loan;
import pl.jedrus.finance.domain.LoanType;
import pl.jedrus.finance.repository.LoanRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/loan")
public class LoanController {

    private final LoanRepository loanRepository;

    public LoanController(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @GetMapping("/all")
    public String allLoan() {
        return "loan/all";
    }


    @ModelAttribute("loans")
    public List<Loan> loanList() {
        return loanRepository.findAll();
    }

    @GetMapping("/add")
    public String addLoan(Model model) {
        model.addAttribute("loan", new Loan());
        return "loan/add";
    }


    @PostMapping("/add")
    public String saveLoan(@Valid Loan loan, BindingResult result) {
        if (result.hasErrors()) {
            return "loan/add";
        }
        loanRepository.save(loan);
        return "redirect:all";
    }


    @ModelAttribute("loanTypes")
    public LoanType[] loanTypeList() {
        return LoanType.values();
    }

}
