package pl.jedrus.finance.service.loan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.jedrus.finance.FinanceApplication;
import pl.jedrus.finance.domain.Loan;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FinanceApplication.class)
@AutoConfigureMockMvc
public class LoanServiceImplTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private LoanService loanService;


    @Test
    public void temporaryDisplayingResult() {


        List<Loan> demo = loanService.findAllByUser_UsernameForStep4("DEMO");


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        demo.forEach(System.out::println);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

    }
}