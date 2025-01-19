package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.AdresyDAO;
import BADA_dom_kultury.SpringApplication.DAO.PocztyDAO;
import BADA_dom_kultury.SpringApplication.DAO.PracownicyDAO;
import BADA_dom_kultury.SpringApplication.DAO.StanowiskaDAO;
import BADA_dom_kultury.SpringApplication.DTO.EmployeeDTO;
import BADA_dom_kultury.SpringApplication.Tables.Adresy;
import BADA_dom_kultury.SpringApplication.Tables.Poczty;
import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import BADA_dom_kultury.SpringApplication.Tables.Stanowiska;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/employees")
public class AdminController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final PracownicyDAO pracownicyDAO;
    @Autowired
    private StanowiskaDAO stanowiskaDAO;
    @Autowired
    private DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;

    public AdminController(PracownicyDAO pracownicyDAO, StanowiskaDAO stanowiskaDAO) {
        this.pracownicyDAO = pracownicyDAO;
        this.stanowiskaDAO = stanowiskaDAO;
    }

    @GetMapping
    public String showAddEmployeeForm(Model model) {
        model.addAttribute("employeeDTO", new EmployeeDTO());
        List<Pracownicy> employees = pracownicyDAO.list();
        System.out.println("Pracownicy: " + employees);
        model.addAttribute("employees", employees);


        // Pobieranie dostępnych stanowisk z bazy danych
        List<Stanowiska> stanowiska = stanowiskaDAO.list();
        System.out.println("Stanowiska: " + stanowiska);
        model.addAttribute("stanowiska", stanowiska);

        return "admin/manage-employees";
    }

    @GetMapping("/add")
    public String showAddEmployeeForm(Model model, EmployeeDTO employeeDTO) {
        model.addAttribute("employeeDTO", employeeDTO);
        List<Stanowiska> stanowiska = stanowiskaDAO.list();
        model.addAttribute("stanowiska", stanowiska);
        return "admin/add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        if ("Male".equals(employeeDTO.getGender())) {
            employeeDTO.setGender("M");
        } else if ("Female".equals(employeeDTO.getGender())) {
            employeeDTO.setGender("K");
        }
        System.out.println("Imię: " + employeeDTO.getFirstName());
        System.out.println("Nazwisko: " + employeeDTO.getLastName());
        System.out.println("Data urodzenia: " + employeeDTO.getDob());
        System.out.println("Nr telefonu: " + employeeDTO.getPhoneNumber());
        System.out.println("E-mail: " + employeeDTO.getEmail());
        System.out.println("Pesel: " + employeeDTO.getPesel());
        System.out.println("Płeć: " + employeeDTO.getGender());
        System.out.println("Nr domu kultury: " + employeeDTO.getHouseNumber());
        System.out.println("Miasto:" + employeeDTO.getAddressCity());
        System.out.println("Ulica:" + employeeDTO.getAddressStreet());
        System.out.println("Lokal:" + employeeDTO.getAddressLocalNumber());


        // Dodawanie pracownika do bazy danych
        employeeDTO.addEmployeeToDatabase(employeeDTO, jdbcTemplate);

        return "redirect:/admin/employees";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam int id) {
        pracownicyDAO.delete(id);
        return "redirect:/admin/employees";
    }

    @GetMapping("/edit")
    public String showEditEmployeeForm(@RequestParam int id, Model model) {
        EmployeeDTO employeeToEdit;
        employeeToEdit = EmployeeDTO.getEmployeeFromDatabase(id, jdbcTemplate);
        employeeToEdit.setId(id);
        model.addAttribute("employee", employeeToEdit);

        List<Stanowiska> stanowiska = stanowiskaDAO.list();
        model.addAttribute("stanowiska", stanowiska);

        return "admin/edit-employee";
    }

    @PostMapping("/edit")
    public String editEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
        employeeDTO.updateEmployeeInDatabase(employeeDTO, jdbcTemplate);
        return "redirect:/admin/employees";
    }

    @GetMapping("/details")
    public String showEmployeeDetails(@RequestParam int id, Model model) {
        EmployeeDTO employeeDetails;
        employeeDetails = EmployeeDTO.getEmployeeFromDatabase(id, jdbcTemplate);
        employeeDetails.setId(id);
        int nr_stanowiska = Integer.parseInt(employeeDetails.getPositionName());
        System.out.println("NR STANOWISKA Z EMPLOYYE NAME: " + nr_stanowiska);
        String positionName = stanowiskaDAO.get(nr_stanowiska).getNazwa();
        System.out.println("POSITION NAME: " + positionName);

        model.addAttribute("employee", employeeDetails);
        model.addAttribute("positionName", positionName);
        return "admin/employee-details";
    }

    @GetMapping("/positions/add")
    public String showAddPositionForm(Model model) {
        List<Stanowiska> stanowiska = stanowiskaDAO.list();
        model.addAttribute("stanowiska", stanowiska);
        model.addAttribute("stanowisko", new Stanowiska());
        return "admin/add-position";
    }

    @PostMapping("/positions/add")
    public String addPosition(@ModelAttribute Stanowiska stanowisko) {
        System.out.println("Nazwa stanowiska: " + stanowisko.getNazwa());
        System.out.println("Opis stanowiska: " + stanowisko.getOpis());

        stanowiskaDAO.save(stanowisko);
        return "redirect:/admin/employees/positions/add";
    }

    @PostMapping("/positions/delete")
    public String deletePosition(@RequestParam int id, Model model) {
        boolean isPositionAssigned = pracownicyDAO.isPositionAssigned(id);
        List<Stanowiska> stanowiska = stanowiskaDAO.list();
        if (isPositionAssigned) {
            model.addAttribute("stanowiska", stanowiska);
            model.addAttribute("error", "Nie można usunąć stanowiska, " +
                    "do którego jest przypisany pracownik. Najpierw pozmieniaj stanowiska odpowiednich pracowników.");
            return "admin/add-position";
        }

        stanowiskaDAO.delete(id);
        return "redirect:/admin/employees/positions/add";
    }
}
