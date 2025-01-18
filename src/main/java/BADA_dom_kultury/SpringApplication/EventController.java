package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EventController {

    @GetMapping("/events")
    public String showEventsPage(Model model) {
        List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(1, "Koncert", 20, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(2, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(3, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(4, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(5, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(6, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1),
                new Wydarzenie(7, "Warsztaty", 50, "01.20.2025", "01.20.2025", 1, 1)
        );
        model.addAttribute("wydarzenia", wydarzenia);
        return "events";
    }


    @PostMapping("/event_register")
    public String registerForEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        // Obsługa zapisu użytkownika na wydarzenie (np. zapis do bazy danych)
        return "redirect:/events?success";
    }
}


