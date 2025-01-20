package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.SaleDAO;
import BADA_dom_kultury.SpringApplication.DAO.UczestnicyDAO;
import BADA_dom_kultury.SpringApplication.DAO.Uczestnik_WydarzenieDAO;
import BADA_dom_kultury.SpringApplication.DAO.WydarzenieDAO;
import BADA_dom_kultury.SpringApplication.Tables.Sale;
import BADA_dom_kultury.SpringApplication.Tables.Uczestnicy;
import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EventController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HttpSession session;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;


    @GetMapping("/events")
    public String showEventsPage(Model model) {


        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        //List<Wydarzenie> wydarzenia = wydarzenieDAO.list();
        List<Wydarzenie> wydarzenia = wydarzenieDAO.leftPlacesList();

        /*List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(1, "Koncert", 100, "01.02.2025", "01.02.2025", 2, 1),
                new Wydarzenie(2, "Warsztaty", 200, "03.02.2025", "03.02.2025", 3, 1),
                new Wydarzenie(3, "Wystawa", 50, "05.02.2025", "10.02.2025", 1, 1)
        );*/

        model.addAttribute("wydarzenia", wydarzenia);
        return "events"; // Strona wyświetlania wydarzeń dla użytkowników
    }

    @PostMapping("/events/register")
    public String registerUserForEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        String username = request.getRemoteUser(); // Pozyskanie aktualnie zalogowanego użytkownika

        if (username == null) {
            return "redirect:/events?error=notLoggedIn"; // Jeśli użytkownik nie jest zalogowany
        }

        Integer nrUczestnika = Integer.parseInt((String) session.getAttribute("uczestnikId"));

        // Zakładając, że mamy metodę w DAO, która zapisuje użytkownika na wydarzenie.
        UczestnicyDAO uczestnikDAO = new UczestnicyDAO(jdbcTemplate);
        Uczestnicy uczestnik = uczestnikDAO.get(nrUczestnika); // Pobranie użytkownika po loginie

        if (uczestnik != null) {
            Uczestnik_WydarzenieDAO uczestnik_wydarzenieDAO = new Uczestnik_WydarzenieDAO(jdbcTemplate);
            boolean isRegistered = uczestnik_wydarzenieDAO.registerUserForEvent(nrUczestnika, eventId);

            if (isRegistered) {
                return "redirect:/events?success=registered"; // Powodzenie rejestracji
            } else {
                return "redirect:/events?error=alreadyRegistered"; // Użytkownik już jest zapisany

            }
        }
        return "redirect:/events?error=userNotFound"; // W przypadku, gdy użytkownik nie istnieje
    }

    @RequestMapping("/events")
    public String events(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("error", error);  // Dodanie parametru do modelu
        return "events";
    }



    @GetMapping("/manage-events")
    public String manageEventsPage(Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN") && !request.isUserInRole("WORKER")) {
            return "redirect:/manage-events?error=unauthorized";
        }
        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        List<EventManagment> wydarzenia = wydarzenieDAO.getListForEvents();

        /*List<Wydarzenie> wydarzenia = List.of(
                new Wydarzenie(1, "Koncert", 100, "01.02.2025", "01.02.2025", 2, 1),
                new Wydarzenie(2, "Warsztaty", 200, "03.02.2025", "03.02.2025", 3, 1),
                new Wydarzenie(3, "Wystawa", 50, "05.02.2025", "10.02.2025", 1, 1)
        );*/



        boolean isAdmin = request.isUserInRole("ADMIN");
        model.addAttribute("wydarzenia", wydarzenia);
        model.addAttribute("isAdmin", isAdmin);
        System.out.println("isAdmin: " + isAdmin);
        System.out.println(wydarzenia);
        return "manage-events";
    }

    @PostMapping("/manage-events/delete")
    public String deleteEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {

        if (!request.isUserInRole("ADMIN") && !request.isUserInRole("WORKER")) {
            return "redirect:/manage-events?error=unauthorized";
        }

        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        wydarzenieDAO.delete(eventId);
        return "redirect:/manage-events?success";
    }

    /*@PostMapping("/manage-events/modify")
    public String modifyEvent(@RequestParam("eventId") int eventId, HttpServletRequest request) {
        // TODO: Obsługa modyfikacji wydarzenia (formularz)
        return "redirect:/manage-events";
    }*/

    @GetMapping("/manage-events/edit")
    public String editEventPage(@RequestParam("eventId") int eventId, Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:/manage-events?error=unauthorized";
        }

        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        Wydarzenie wydarzenie = wydarzenieDAO.get(eventId);
        SaleDAO saleDAO = new SaleDAO(jdbcTemplate);
        List<Sale> sale = saleDAO.list();
        model.addAttribute("sale", sale);
        model.addAttribute("wydarzenie", wydarzenie);

        return "edit-event";
    }

    @PostMapping("/manage-events/edit")
    public String editEvent(@RequestParam("eventId") int eventId,
                            @RequestParam("name") String name,
                            @RequestParam("capacity") int capacity,
                            @RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate,
                            @RequestParam("roomNumber") int roomNumber,
                            Model model) {
        // Tworzymy obiekt wydarzenia
        Wydarzenie event = new Wydarzenie();
        event.setNr_wydarzenia(eventId);
        event.setNazwa(name);
        event.setLiczba_miejsc(capacity);
        event.setData_start(startDate);  // Przechowywanie dat jako String
        event.setData_koniec(endDate);   // Przechowywanie dat jako String
        event.setNr_sali(roomNumber);    // Ustawiamy numer sali
        event.setNr_domu_kultury(3);     // Ustawiamy numer domu kultury

        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        wydarzenieDAO.update(event);

        // Zapisz zmiany w bazie danych (np. przy użyciu serwisów lub DAO)
        // eventService.update(event);

        // Dodaj komunikat o sukcesie
        model.addAttribute("successMessage", "Wydarzenie zostało zaktualizowane.");
        return "redirect:/manage-events"; // Przekierowanie na stronę listy wydarzeń
    }

    @GetMapping("/manage-events/participants")
    public String showParticipantsPage(@RequestParam("eventId") int eventId, Model model, HttpServletRequest request) {
        // Sprawdzamy, czy użytkownik ma rolę ADMIN
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:/manage-events?error=unauthorized";
        }

        // Pobranie uczestników dla konkretnego wydarzenia
        UczestnicyDAO uczestnikDAO = new UczestnicyDAO(jdbcTemplate);
        List<Uczestnicy> uczestnicy = uczestnikDAO.getParticipantsForEvent(eventId);

        model.addAttribute("uczestnicy", uczestnicy);
        model.addAttribute("eventId", eventId);

        return "participants"; // Strona wyświetlająca listę uczestników
    }

    @PostMapping("/manage-events/participants/delete")
    public String deleteParticipantFromEvent(@RequestParam("uczestnikId") int uczestnikId,
                                             @RequestParam("eventId") int eventId,
                                             HttpServletRequest request) {

        if (!request.isUserInRole("ADMIN") && !request.isUserInRole("WORKER")) {
            return "redirect:/manage-events/participants?error=unauthorized";
        }

        Uczestnik_WydarzenieDAO uczestnikWydarzenieDAO = new Uczestnik_WydarzenieDAO(jdbcTemplate);
        uczestnikWydarzenieDAO.delete(uczestnikId, eventId);


        return "redirect:/manage-events/participants?eventId=" + eventId;

    }

    @GetMapping("/manage-events/new")
    public String newEventPage(Model model, HttpServletRequest request) {
        // Sprawdzamy, czy użytkownik ma rolę ADMIN
        if (!request.isUserInRole("ADMIN")) {
            return "redirect:/manage-events?error=unauthorized";
        }

        SaleDAO saleDAO = new SaleDAO(jdbcTemplate);
        List<Sale> sale = saleDAO.list();

        model.addAttribute("sale", sale);
        return "new-event";
    }

    @PostMapping("/manage-events/create")
    public String createEvent(@RequestParam("name") String name,
                              @RequestParam("capacity") int capacity,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("roomNumber") int roomNumber,
                              HttpServletRequest request) {

        if (!request.isUserInRole("ADMIN")) {
            return "redirect:/manage-events?error=unauthorized";
        }

        // Tworzymy nowe wydarzenie
        Wydarzenie newEvent = new Wydarzenie();
        newEvent.setNazwa(name);
        newEvent.setLiczba_miejsc(capacity);
        newEvent.setData_start(startDate);
        newEvent.setData_koniec(endDate);
        newEvent.setNr_sali(roomNumber);
        newEvent.setNr_domu_kultury(3); // Dom kultury (przykład)

        WydarzenieDAO wydarzenieDAO = new WydarzenieDAO(jdbcTemplate);
        wydarzenieDAO.save(newEvent);

        return "redirect:/manage-events?success";
    }









}
