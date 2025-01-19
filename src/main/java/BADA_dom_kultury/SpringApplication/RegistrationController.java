package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.UczestnicyDAO;
import BADA_dom_kultury.SpringApplication.DTO.UserDTO;
import BADA_dom_kultury.SpringApplication.Tables.Uczestnicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO()); // Tworzymy pusty obiekt UserDTO
        return "register"; // To będzie nazwa widoku, który wyświetli formularz rejestracji
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        // Hashowanie hasła
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        String rola = "USER";
        Uczestnicy uczestnik = new Uczestnicy();
        uczestnik.setImie(userDTO.getFirstName());
        uczestnik.setNazwisko(userDTO.getLastName());
        uczestnik.setData_urodzenia(userDTO.getDob());
        uczestnik.setTelefon(userDTO.getPhoneNumber());
        uczestnik.setEmail(userDTO.getEmail());
        uczestnik.setNr_domu_kultury(3);


        UczestnicyDAO uczetnikDAO = new UczestnicyDAO(jdbcTemplate);
        uczetnikDAO.save(uczestnik);

        int id = 0;
        List<Uczestnicy> uczestnicy = uczetnikDAO.list();
        for (Uczestnicy uczestnik1 : uczestnicy) {
            if (uczestnik1.equals(uczestnik)) {
                id = uczestnik1.getNr_uczestnika();
            }
        }

        // Przykład wyświetlenia danych w logach
        System.out.println("nr_uczestnika: " + id);
        System.out.println("Imię: " + userDTO.getFirstName());
        System.out.println("Nazwisko: " + userDTO.getLastName());
        System.out.println("Data urodzenia: " + userDTO.getDob());
        System.out.println("Nr telefonu: " + userDTO.getPhoneNumber());
        System.out.println("E-mail: " + userDTO.getEmail());
        System.out.println("Login: " + userDTO.getUsername());
        System.out.println("Hasło (z hash'em): " + hashedPassword);

        // Możesz zapisać dane do pliku CSV
        try (FileWriter writer = new FileWriter("users.csv", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(id + "," + userDTO.getUsername() + "," + hashedPassword + "," + rola);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/login";  // Przekierowanie po rejestracji
    }
}

