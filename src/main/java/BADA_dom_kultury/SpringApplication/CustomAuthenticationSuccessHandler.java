package BADA_dom_kultury.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private final HttpSession session;

    public CustomAuthenticationSuccessHandler(HttpSession session) {
        this.session = session;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // Pobieranie szczegółów użytkownika
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Pobierz dane z pliku CSV na podstawie loginu (username)
        String uczestnikId = null;
        String role = null;

        // Odczytaj dane z pliku CSV
        List<String[]> usersData = readUsersFromCsv("users.csv");

        for (String[] user : usersData) {
            String storedUsername = user[1]; // Załóżmy, że login jest w drugim polu CSV (index 1)
            if (storedUsername.equals(username)) {
                uczestnikId = user[0]; // Załóżmy, że uczestnikId jest w pierwszym polu (index 0)
                role = user[3]; // Załóżmy, że rola jest w czwartym polu (index 3)
                break;
            }
        }

        if (uczestnikId != null && role != null) {
            // Zapisz w sesji
            session.setAttribute("uczestnikId", uczestnikId);
            session.setAttribute("rola", role);

        }

        // Przekierowanie po pomyślnym logowaniu (na stronę główną użytkownika)
        response.sendRedirect("/main");
    }

    private List<String[]> readUsersFromCsv(String csvFile) throws IOException {
        List<String[]> usersData = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                usersData.add(fields);
            }
        }
        return usersData;
    }
}
