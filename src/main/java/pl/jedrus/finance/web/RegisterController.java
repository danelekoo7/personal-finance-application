package pl.jedrus.finance.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.jedrus.finance.domain.User;
import pl.jedrus.finance.service.UserService;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("registration")
    public String registration(Model model) {

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String createNewUser(@Valid User user, BindingResult result) {
        User userExists = userService.findByUserName(user.getUsername());
        if (userExists != null) {
            result
                    .rejectValue("username", "error.user");
        }
        if (result.hasErrors()) {
            return "register";
        } else {
            userService.saveUser(user);
        }
        return "redirect:/login";
    }

}
