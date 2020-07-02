package pl.jedrus.finance.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jedrus.finance.domain.Buffer;
import pl.jedrus.finance.service.buffer.BufferService;

@Controller
@RequestMapping("/step3")
public class Step3BufferController {
    private final BufferService bufferService;

    public Step3BufferController(BufferService bufferService) {
        this.bufferService = bufferService;
    }

    @GetMapping()
    public String get(Model model, @AuthenticationPrincipal UserDetails userDetails) {

        Buffer buffer = bufferService.findByUser_Username(userDetails.getUsername());
        model.addAttribute("buffer",buffer);

        return "step3/step3";
    }


}
