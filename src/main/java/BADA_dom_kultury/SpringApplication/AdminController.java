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
        addEmployeeToDatabase(employeeDTO);




        return "redirect:/admin/employees";
    }

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam int id) {
        pracownicyDAO.delete(id);
        return "redirect:/admin/employees";
    }

    public void addEmployeeToDatabase(EmployeeDTO employeeDTO) {
        PocztyDAO pocztyDAO = new PocztyDAO(jdbcTemplate);
        Poczty poczty = new Poczty();
        AdresyDAO adresyDAO = new AdresyDAO(jdbcTemplate);
        Adresy adresy = new Adresy();
        StanowiskaDAO stanowiskaDAO = new StanowiskaDAO(jdbcTemplate);
        Stanowiska stanowiska = new Stanowiska();

        List<Poczty> pocztyList = pocztyDAO.list();
        System.out.println("Poczty: " + pocztyList);
        int idPoczty = 0;

        for (Poczty p : pocztyList) {
            if (p.getPoczta().equals(employeeDTO.getPostName()) && p.getKod_pocztowy().equals(employeeDTO.getPostalCode())) {
                idPoczty = p.getNr_poczty();
                break;
            }
        }

        if (idPoczty == 0) {
            poczty.setPoczta(employeeDTO.getPostName());
            poczty.setKod_pocztowy(employeeDTO.getPostalCode());
            pocztyDAO.save(poczty);
            pocztyList = pocztyDAO.list();
            for (Poczty p : pocztyList) {
                if (p.getPoczta().equals(employeeDTO.getPostName()) && p.getKod_pocztowy().equals(employeeDTO.getPostalCode())) {
                    idPoczty = p.getNr_poczty();
                    break;
                }
            }
        }
        System.out.println("idPoczty: " + idPoczty);

        List<Adresy> adresyList = adresyDAO.list();
        System.out.println("Adresy: " + adresyList);
        int idAdresu = 0;
        for (Adresy a : adresyList) {
            if (a.getMiasto().equals(employeeDTO.getAddressCity()) && a.getUlica().equals(employeeDTO.getAddressStreet())
                    && a.getNr_budynku().equals(employeeDTO.getAddressBuildingNumber())
                    && a.getNr_lokalu().equals(employeeDTO.getAddressLocalNumber())
                    && a.getNr_poczty() == idPoczty) {
                idAdresu = a.getNr_adresu();
                break;
            }
        }

        if (idAdresu == 0){
            adresy.setMiasto(employeeDTO.getAddressCity());
            adresy.setUlica(employeeDTO.getAddressStreet());
            adresy.setNr_budynku(employeeDTO.getAddressBuildingNumber());
            adresy.setNr_lokalu(employeeDTO.getAddressLocalNumber());
            adresy.setNr_poczty(idPoczty);
            adresyDAO.save(adresy);
            adresyList = adresyDAO.list();
            for (Adresy a : adresyList) {
                if (a.getMiasto().equals(employeeDTO.getAddressCity()) && a.getUlica().equals(employeeDTO.getAddressStreet())
                        && a.getNr_budynku().equals(employeeDTO.getAddressBuildingNumber())
                        && a.getNr_lokalu().equals(employeeDTO.getAddressLocalNumber())
                        && a.getNr_poczty() == idPoczty) {
                    idAdresu = a.getNr_adresu();
                    break;
                }
            }
        }
        System.out.println("idAdresu: " + idAdresu);

        List<Stanowiska> stanowiskaList = stanowiskaDAO.list();
        System.out.println("Stanowiska: " + stanowiskaList);
        int idStanowiska = Integer.parseInt(employeeDTO.getPositionName());
        System.out.println("idStanowiska: " + idStanowiska);

        Pracownicy pracownik = new Pracownicy();
        pracownik.setImie(employeeDTO.getFirstName());
        pracownik.setNazwisko(employeeDTO.getLastName());
        pracownik.setData_urodzenia(employeeDTO.getDob());
        pracownik.setTelefon(employeeDTO.getPhoneNumber());
        pracownik.setEmail(employeeDTO.getEmail());
        pracownik.setPesel(employeeDTO.getPesel());
        pracownik.setPlec(employeeDTO.getGender());
        //Bo taki się dodał klucz główny domu kultury w sqldeveloper więc będzie hardcodowany na 3
        pracownik.setNr_domu_kultury(3);
        pracownik.setNr_adresu(idAdresu);
        pracownik.setNr_stanowiska(idStanowiska);

        pracownicyDAO.save(pracownik);


    }

}
