package pl.jedrus.finance.service.buffer;

import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Buffer;
import pl.jedrus.finance.repository.BufferRepository;
import pl.jedrus.finance.service.user.UserService;

import java.math.BigDecimal;

@Service
public class BufferImpl implements BufferService {


    private final BufferRepository bufferRepository;
    private final UserService userService;

    public BufferImpl(BufferRepository bufferRepository, UserService userService) {
        this.bufferRepository = bufferRepository;
        this.userService = userService;
    }

    @Override
    public Buffer findByUser_Username(String username) {
        return bufferRepository.findByUser_Username(username).orElseThrow();
    }

    @Override
    public Buffer findById(Long id) {
        return bufferRepository.findById(id).orElseThrow();
    }

    @Override
    public void saveBuffer(Buffer buffer, String username) {
        buffer.setUser(userService.findByUserName(username));
        buffer.setEstimatedValue(BigDecimal.valueOf(2000));
        buffer.setCurrentValue(BigDecimal.ZERO);
        bufferRepository.save(buffer);
    }

    @Override
    public void updateBuffer(Buffer buffer) {
        Buffer bufferInDB = findById(buffer.getId());
        bufferInDB.setEstimatedValue(buffer.getEstimatedValue());
        bufferInDB.setCurrentValue(buffer.getCurrentValue());
        bufferRepository.save(bufferInDB);
    }

    @Override
    public void deleteBufferById(Long id) {
        bufferRepository.deleteById(id);
    }
}
