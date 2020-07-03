package pl.jedrus.finance.service.initData;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.*;
import pl.jedrus.finance.service.asset.AssetService;
import pl.jedrus.finance.service.buffer.BufferService;
import pl.jedrus.finance.service.expense.ExpenseService;
import pl.jedrus.finance.service.income.IncomeService;
import pl.jedrus.finance.service.loan.LoanService;
import pl.jedrus.finance.service.securityFund.SecurityFundService;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitDataServiceImpl implements InitDataService {


    private final LoanService loanService;
    private final AssetService assetService;
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final UserService userService;
    private final BufferService bufferService;
    private final SecurityFundService securityFundService;

    public InitDataServiceImpl(LoanService loanService, AssetService assetService, IncomeService incomeService, ExpenseService expenseService, UserService userService, BufferService bufferService, SecurityFundService securityFundService) {
        this.loanService = loanService;
        this.assetService = assetService;
        this.incomeService = incomeService;
        this.expenseService = expenseService;
        this.userService = userService;
        this.bufferService = bufferService;
        this.securityFundService = securityFundService;
    }

    @Override
    public void initData(User user) {
        User userInDB = userService.findByUserName(user.getUsername());

        Asset asset1 = new Asset();
        asset1.setValue(BigDecimal.ZERO);
        asset1.setDescription("aktywo 1");
        assetService.saveAsset(asset1, userInDB.getUsername());

        Asset asset2 = new Asset();
        asset2.setValue(BigDecimal.ZERO);
        asset2.setDescription("aktywo 2");
        assetService.saveAsset(asset2, userInDB.getUsername());

        Loan loan1 = new Loan();
        loan1.setValue(BigDecimal.ZERO);
        loan1.setDescription("pasywo 1");
        loan1.setVisibleOnlyInStep1(false);
        loan1.setInstallment(BigDecimal.valueOf(100));
        loan1.setInterest(11);
        loanService.saveLoan(loan1, userInDB.getUsername());

        Loan loan2 = new Loan();
        loan2.setValue(BigDecimal.ZERO);
        loan2.setDescription("pasywo 2");
        loan2.setVisibleOnlyInStep1(false);
        loan2.setInstallment(BigDecimal.valueOf(125));
        loan2.setInterest(22);
        loanService.saveLoan(loan2, userInDB.getUsername());

        List<String> incomes = new ArrayList<>();
        incomes.add("Wynagrodzenie 1");
        incomes.add("Premia 1");
        incomes.add("Wynagrodzenie 2");
        incomes.add("Premia 2");
        incomes.add("Odsetki od lokat");
        incomes.add("Alimenty");
        incomes.add("Zasiłek");
        incomes.add("Renta");
        incomes.add("Emerytura");
        incomes.add("Dochody z najmu");
        incomes.add("Dywidendy");
        incomes.add("Prawa autorskie");
        incomes.add("Inne 1");
        incomes.add("Inne 2");
        initIncomes(incomes, userInDB);

        List<String> expenseListGroup1 = new ArrayList<>();
        expenseListGroup1.add("Jedzenie i picie");
        expenseListGroup1.add("Koszty leczenia i leki");
        expenseListGroup1.add("Ubranie");
        expenseListGroup1.add("Czynsz za mieszkanie (plus woda i ogrzewanie)");
        expenseListGroup1.add("Opłaty za energię elektryczną");
        expenseListGroup1.add("Koszty dojazdu do pracy");
        expenseListGroup1.add("Higiena, kosmetyki, fryzjer");
        expenseListGroup1.add("Środki czystości, pralnia chemiczna");
        expenseListGroup1.add("Wydatki na szkołę i przedszkole dzieci");
        expenseListGroup1.add("Rachunek telefoniczny i opłata za internet");
        expenseListGroup1.add("Inne niezbędne wydatki");
        initExpensesGroup(expenseListGroup1, user, 1);


        List<String> expenseListGroup2 = new ArrayList<>();
        expenseListGroup2.add("Rata kredytu hipotecznego");
        expenseListGroup2.add("Bieżące raty kredytów i pożyczek konsumenckich, spłata kart kredytowych");
        expenseListGroup2.add("Składka na ubezpieczenie OC");
        expenseListGroup2.add("Składka na ubezpieczenie mieszkania");
        expenseListGroup2.add("Składka na ubezpieczenie na życie (jeśli potrzebne)");
        expenseListGroup2.add("Inne składki ubezpieczeniowe");
        expenseListGroup2.add("Podatek od nieruchomości i/lub opłata za użytkowanie wieczyste");
        expenseListGroup2.add("Inne raty, składki i podatki");
        initExpensesGroup(expenseListGroup2, user, 2);

        List<String> expenseListGroup3 = new ArrayList<>();
        expenseListGroup3.add("Kwota na drobne przyjemności");
        expenseListGroup3.add("Bufor 2000 zł");
        expenseListGroup3.add("Szybsza spłata długów");
        expenseListGroup3.add("Wpłata na fundusz bezpieczeństwa");
        expenseListGroup3.add("Zabawki dla dzieci");
        expenseListGroup3.add("Własny rozwój (np. języki obce, inne kursy) i dodatkowe zajęcia dla dzieci");
        expenseListGroup3.add("Rezerwa na przegląd techniczny samochodu, olej, wymianę opon");
        expenseListGroup3.add("Hobby, kino, teatr itp.");
        expenseListGroup3.add("Wpłata na konto emerytalne");
        expenseListGroup3.add("Rezerwa na święta (prezenty, organizacja)");
        expenseListGroup3.add("Rezerwa na wakacje i urlopy (wraz z ubezpieczeniem kosztów leczenia)");
        expenseListGroup3.add("Wpłata na przyszłe studia dzieci");
        expenseListGroup3.add("Inne ważne wydatki");
        initExpensesGroup(expenseListGroup3, user, 3);

        List<String> expenseListGroup4 = new ArrayList<>();
        expenseListGroup4.add("Rezerwa na dodatkową ratę kredytu hipotecznego");
        expenseListGroup4.add("Rozrywka i przyjemności");
        expenseListGroup4.add("Lokowanie nadwyżek");
        expenseListGroup4.add("Pomoc innym");
        expenseListGroup4.add("Dowolne zakupy");
        initExpensesGroup(expenseListGroup4, user, 4);

        Buffer buffer = new Buffer();
        buffer.setCurrentValue(BigDecimal.ZERO);
        buffer.setExpectedValue(BigDecimal.valueOf(2000));
        bufferService.saveBuffer(buffer, user.getUsername());

        SecurityFund securityFund = new SecurityFund();
        securityFund.setCurrentValue(BigDecimal.ZERO);
        securityFund.setExpectedValue(BigDecimal.ZERO);
        securityFundService.saveSecurityFund(securityFund, user.getUsername());
    }


    private void initIncomes(List<String> incomesList, User user) {
        for (String income : incomesList) {
            Income income1 = new Income();
            income1.setSource(income);
            income1.setValue(BigDecimal.ZERO);
            incomeService.saveIncome(income1, user.getUsername());
        }
    }


    private void initExpensesGroup(List<String> expenses, User user, int groupNumber) {
        for (String expense : expenses) {
            Expense expense1 = new Expense();
            expense1.setExpenseType(expense);
            expense1.setExpenseGroup(groupNumber);
            expense1.setPlannedValue(BigDecimal.ZERO);
            expense1.setRealValue(BigDecimal.ZERO);
            expenseService.saveExpense(expense1, user.getUsername());
        }
    }

}
