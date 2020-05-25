package pl.jedrus.finance.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @DecimalMin("0.0")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    private LoanType loanType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", loanType=" + loanType +
                '}';
    }
}
