package pl.jedrus.finance.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ExpenseRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    @NotNull
    private Expense expense;

    private String comment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    private LocalDate currentDateIndicator;

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

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getCurrentDateIndicator() {
        return currentDateIndicator;
    }

    public void setCurrentDateIndicator(LocalDate currentDateIndicator) {
        this.currentDateIndicator = currentDateIndicator;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "ExpenseRegister{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", expense=" + expense +
                ", comment='" + comment + '\'' +
                ", created=" + created +
                ", currentDateIndicator=" + currentDateIndicator +
                ", user=" + user +
                '}';
    }
}
