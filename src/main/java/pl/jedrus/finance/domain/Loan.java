package pl.jedrus.finance.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal value;

    private boolean visibleOnlyInStep1;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal installment;

    private double interest;

    @ManyToOne
    private User user;

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

    public boolean isVisibleOnlyInStep1() {
        return visibleOnlyInStep1;
    }

    public void setVisibleOnlyInStep1(boolean visibleOnlyInStep1) {
        this.visibleOnlyInStep1 = visibleOnlyInStep1;
    }

    public BigDecimal getInstallment() {
        return installment;
    }

    public void setInstallment(BigDecimal installment) {
        this.installment = installment;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", visibleOnlyInStep1=" + visibleOnlyInStep1 +
                ", installment=" + installment +
                ", interest=" + interest +
                ", user=" + user +
                '}';
    }
}
