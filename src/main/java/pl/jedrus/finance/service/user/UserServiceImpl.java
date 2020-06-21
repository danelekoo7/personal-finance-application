package pl.jedrus.finance.service.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.jedrus.finance.domain.Role;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.repository.RoleRepository;
import pl.jedrus.finance.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(1);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername != null) {
            userRepository.deleteById(byUsername.getId());
        }
    }
}