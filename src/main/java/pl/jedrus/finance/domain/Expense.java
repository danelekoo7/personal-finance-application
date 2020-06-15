package pl.jedrus.finance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String expenseType;

    private BigDecimal plannedValue;

    private BigDecimal realValue;

    private int expenseGroup;

    private String comment;

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
                ", user=" + user +
                '}';
    }
}


