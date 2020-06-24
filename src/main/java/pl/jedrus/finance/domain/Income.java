package pl.jedrus.finance.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String source;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal value;

    private String comment;

    private LocalDate currentDateIndicator;

    @ManyToOne
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
        return "Income{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", value=" + value +
                ", comment='" + comment + '\'' +
                ", currentDateIndicator=" + currentDateIndicator +
                ", user=" + user +
                '}';
    }
}
