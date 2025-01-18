package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.WydarzenieDAO;
import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/events")
    public String showEventsPage(Model model) {



        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        List<Wydarzenie> wydarzenia = wydarzenieDAO.list();

        /* Nie usuwam tylko komentuje bo ty nie bedziesz mogl pobrac z bazy danych tylko musisz z wpisanych recznie
        List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(0, "Koncert", 20, "01.02.2025", "01.02.2025", 1, 1),
                new Wydarzenie(1, "Koncert", 20, "01.02.2025", "01.02.2025", 1, 1),
                new Wydarzenie(2, "Koncert", 20, "01.02.2025", "01.02.2025", 1, 1)

        );

         */
        model.addAttribute("wydarzenia", wydarzenia);
        return "events";
    }


    @PostMapping("/register")
    public String registerForEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        String username = request.getUserPrincipal().getName();
        // Obsługa zapisu użytkownika na wydarzenie (np. zapis do bazy danych)
        return "redirect:/events?success";
    }
}


