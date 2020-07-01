package pl.jedrus.finance.service.buffer;

import pl.jedrus.finance.domain.Buffer;


public interface BufferService {
    Buffer findByUser_Username(String username);

    Buffer findById(Long id);

    void saveBuffer(Buffer buffer, String username);

    void updateBuffer(Buffer buffer);

    void deleteBufferById(Long id);
}
