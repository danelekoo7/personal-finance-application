package pl.jedrus.finance.domain;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String expenseType;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal plannedValue;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal realValue;

    @Range(min = 1, max = 4)
    private int expenseGroup;

    private String comment;

    private LocalDate monthIndicator;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public BigDecimal getPlannedValue() {
        return plannedValue;
    }

    public void setPlannedValue(BigDecimal plannedValue) {
        this.plannedValue = plannedValue;
    }

    public BigDecimal getRealValue() {
        return realValue;
    }

    public void setRealValue(BigDecimal realValue) {
        this.realValue = realValue;
    }

    public int getExpenseGroup() {
        return expenseGroup;
    }

    public void setExpenseGroup(int expenseGroup) {
        this.expenseGroup = expenseGroup;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getMonthIndicator() {
        return monthIndicator;
    }

    public void setMonthIndicator(LocalDate monthIndicator) {
        this.monthIndicator = monthIndicator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", expenseType='" + expenseType + '\'' +
                ", plannedValue=" + plannedValue +
                ", realValue=" + realValue +
                ", expenseGroup=" + expenseGroup +
                ", comment='" + comment + '\'' +
                ", monthIndicator=" + monthIndicator +
                ", user=" + user +
                '}';
    }
}


