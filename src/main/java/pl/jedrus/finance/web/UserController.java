package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.service.UserService;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setUsername("a");
        user.setPassword("a");
        userService.saveUser(user);
        return "admin";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        return "You are logged as " + customUser;
    }
}