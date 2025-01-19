package BADA_dom_kultury.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Formularz logowania
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, Model model) {
        // Wczytanie danych z pliku CSV
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Format CSV: uczestnikId,login,hashedPassword,role
                String uczestnikId = fields[0];
                String storedUsername = fields[1];
                String storedHashedPassword = fields[2];
                String role = fields[3];

                // Sprawdzamy, czy login pasuje
                if (storedUsername.equals(username)) {
                    // Porównujemy hasło
                    if (passwordEncoder.encode(password).equals(storedHashedPassword)) {
                        // Użytkownik zalogowany pomyślnie
                        model.addAttribute("username", username);
                        model.addAttribute("role", role);
                        return "main_user";  // Przekierowanie do strony powitalnej
                    } else {
                        model.addAttribute("error", "Invalid password.");
                        return "login";  // Błąd hasła
                    }
                }
            }
            // Jeśli login nie pasuje do żadnego użytkownika w pliku
            model.addAttribute("error", "Invalid username.");
            return "login";  // Błąd loginu
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error reading user data.");
            return "login";  // Błąd wczytywania pliku
        }
    }
}

