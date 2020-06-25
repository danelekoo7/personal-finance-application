package pl.jedrus.finance.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DateIndicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate currentDateIndicator;

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

    @Override
    public String toString() {
        return "DateIndicator{" +
                "id=" + id +
                ", currentDateIndicator=" + currentDateIndicator +
                '}';
    }
}
