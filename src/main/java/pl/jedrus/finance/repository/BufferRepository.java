package pl.jedrus.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jedrus.finance.domain.Buffer;

import java.util.Optional;

public interface BufferRepository extends JpaRepository<Buffer,Long> {
    Optional<Buffer> findByUser_Username(String username);

    Optional<Buffer> findById(Long id);
}
