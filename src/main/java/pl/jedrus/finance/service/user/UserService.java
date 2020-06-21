package pl.jedrus.finance.service.user;

import pl.jedrus.finance.domain.User;

public interface UserService {
    User findByUserName(String name);
    void saveUser(User user);
    void deleteUser(User user);


}