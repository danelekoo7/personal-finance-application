package pl.jedrus.finance.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.service.UserService;

@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;


    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("Krzychu");
        user.setPassword("w");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        log.info("customUser class {} " , customUser.getClass());
        return "You are logged as " + customUser;
    }
}