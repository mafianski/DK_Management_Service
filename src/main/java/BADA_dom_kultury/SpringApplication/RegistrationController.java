package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class RegistrationController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO()); // Tworzymy pusty obiekt UserDTO
        return "register"; // To będzie nazwa widoku, który wyświetli formularz rejestracji
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO) {
        // Hashowanie hasła
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Przykład wyświetlenia danych w logach
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
            bw.write(userDTO.getFirstName() + "," + userDTO.getLastName() + "," +
                    userDTO.getDob() + "," + userDTO.getPhoneNumber() + "," +
                    userDTO.getEmail() + "," + userDTO.getUsername() + "," + hashedPassword);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Jeśli chciałbyś kontynuować z zapisem do bazy danych, będzie to później w tym miejscu

        return "redirect:/login";  // Przekierowanie po rejestracji
    }
}

