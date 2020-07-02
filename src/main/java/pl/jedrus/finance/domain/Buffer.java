package pl.jedrus.finance.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Buffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",
            unique=true)
    private User user;


    @NotNull
    @DecimalMin("0.0")
    private BigDecimal currentValue;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal expectedValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    public BigDecimal getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(BigDecimal estimatedValue) {
        this.expectedValue = estimatedValue;
    }

    @Override
    public String toString() {
        return "Buffer2000{" +
                "id=" + id +
                ", user=" + user +
                ", currentValue=" + currentValue +
                ", estimatedValue=" + expectedValue +
                '}';
    }
}
