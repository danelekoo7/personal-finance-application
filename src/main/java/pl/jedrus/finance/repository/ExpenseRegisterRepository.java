package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.ExpenseRegister;

import java.util.List;
import java.util.Optional;

public interface ExpenseRegisterRepository extends JpaRepository<ExpenseRegister, Long> {

    List<ExpenseRegister> findAllByUser_Username(String username);

    Optional<ExpenseRegister> findAllById(Long id);

}
