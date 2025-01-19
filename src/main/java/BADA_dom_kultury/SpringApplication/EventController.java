package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.WydarzenieDAO;
import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/events")
    public String showEventsPage(Model model) {
        //TODO: podmienić z przykładowych
        //WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        //List<Wydarzenie> wydarzenia = wydarzenieDAO.list();

        List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(1, "Koncert", 100, "01.02.2025", "01.02.2025", 2, 1),
                new Wydarzenie(2, "Warsztaty", 200, "03.02.2025", "03.02.2025", 3, 1),
                new Wydarzenie(3, "Wystawa", 50, "05.02.2025", "10.02.2025", 1, 1)
        );
        model.addAttribute("wydarzenia", wydarzenia);
        return "events"; // Strona wyświetlania wydarzeń dla użytkowników
    }

    @GetMapping("/manage-events")
    public String manageEventsPage(Model model, HttpServletRequest request) {
        //TODO: podmienić z przykładowych
        //WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        //List<Wydarzenie> wydarzenia = wydarzenieDAO.list();

        List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(1, "Koncert", 100, "01.02.2025", "01.02.2025", 2, 1),
                new Wydarzenie(2, "Warsztaty", 200, "03.02.2025", "03.02.2025", 3, 1),
                new Wydarzenie(3, "Wystawa", 50, "05.02.2025", "10.02.2025", 1, 1)
        );

        boolean isAdmin = request.isUserInRole("ADMIN");
        model.addAttribute("wydarzenia", wydarzenia);
        model.addAttribute("isAdmin", isAdmin);
        return "manage-events";
    }

    @PostMapping("/manage-events/delete")
    public String deleteEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:/manage-events?error=unauthorized";
        }
        // TODO: Usuń wydarzenie z bazy danych za pomocą DAO
        return "redirect:/manage-events?success";
    }

    @PostMapping("/manage-events/modify")
    public String modifyEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        // TODO: Obsługa modyfikacji wydarzenia (formularz)
        return "redirect:/manage-events";
    }
}
