package pl.jedrus.finance.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
public class DateIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private LocalDate currentDateIndicator;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "DateIndicator{" +
                "id=" + id +
                ", currentDateIndicator=" + currentDateIndicator +
                ", user=" + user +
                '}';
    }
}
