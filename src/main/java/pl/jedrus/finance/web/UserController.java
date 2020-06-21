package pl.jedrus.finance.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.repository.UserRepository;
import pl.jedrus.finance.service.user.UserService;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {

        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("q");
        user.setPassword("q");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/temp")
    @ResponseBody
    public String temp() {
        return "temp";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        log.info("customUser class {} ", customUser.getClass());
        return "You are logged as " + customUser;
    }


}