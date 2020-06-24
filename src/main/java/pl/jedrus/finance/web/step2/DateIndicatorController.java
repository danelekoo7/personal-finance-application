package pl.jedrus.finance.web.step2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jedrus.finance.service.dateIndicator.DateIndicatorService;

@Controller
@RequestMapping("/step2/date")
public class DateIndicatorController {

    private final DateIndicatorService dateIndicatorService;

    public DateIndicatorController(DateIndicatorService dateIndicatorService) {
        this.dateIndicatorService = dateIndicatorService;
    }


    @GetMapping()
    public String getDate() {

        return "step2/create-date-indicator";
    }


    @PostMapping("/add")
    public String addDate(@RequestParam(value = "yearMonth") String yearMonth, @AuthenticationPrincipal UserDetails userDetails) {
        dateIndicatorService.saveDateIndicator(yearMonth, userDetails.getUsername());
        return "redirect:/step2/income";
    }

}
