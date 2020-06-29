package pl.jedrus.finance.web.step2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.jedrus.finance.service.dateIndicator.DateIndicatorService;

@Controller
@RequestMapping("/step2/date")
public class DateIndicatorController {

    private final DateIndicatorService dateIndicatorService;

    public DateIndicatorController(DateIndicatorService dateIndicatorService) {
        this.dateIndicatorService = dateIndicatorService;
    }


    @GetMapping()
    public String getDate(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("dates", dateIndicatorService.findAllDates(user.getUsername()));

        return "step2/budget-manage";
    }

    @PostMapping("/add")
    public String addDate(@RequestParam(value = "yearMonth") String yearMonth, @AuthenticationPrincipal UserDetails userDetails) {
        dateIndicatorService.addDateIndicator(yearMonth, userDetails.getUsername());
        return "redirect:/step2/date";
    }


    @PostMapping("/update")
    public String updateDate(@RequestParam(value = "yearMonth") String yearMonth, @AuthenticationPrincipal UserDetails userDetails) {
        dateIndicatorService.updateDateIndicator(yearMonth, userDetails.getUsername());
        return "redirect:/step2/date";
    }

    @GetMapping("/activate/{yearMonth}")
    public String activate(@PathVariable String yearMonth, @AuthenticationPrincipal UserDetails userDetails) {
        dateIndicatorService.updateDateIndicator(yearMonth, userDetails.getUsername());
        return "redirect:/step2/date";
    }

    @GetMapping("/delete/{yearMonth}")
    public String delete(@PathVariable String yearMonth, @AuthenticationPrincipal UserDetails userDetails) {
        dateIndicatorService.deleteDateIndicatorById(yearMonth, userDetails.getUsername());
        return "redirect:/step2/date";
    }


}
