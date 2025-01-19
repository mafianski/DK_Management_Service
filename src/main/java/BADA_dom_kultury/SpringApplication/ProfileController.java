package BADA_dom_kultury.SpringApplication;

import BADA_dom_kultury.SpringApplication.DAO.UczestnicyDAO;
import BADA_dom_kultury.SpringApplication.DTO.UserDTO;
import BADA_dom_kultury.SpringApplication.Tables.Uczestnicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class ProfileController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/update-profile")
    public String showUpdateForm(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            UczestnicyDAO uczestnikDAO = new UczestnicyDAO(jdbcTemplate);
            Uczestnicy uczestnik = uczestnikDAO.get(userId);

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(uczestnik.getImie());
            userDTO.setLastName(uczestnik.getNazwisko());
            userDTO.setDob(uczestnik.getData_urodzenia());
            userDTO.setPhoneNumber(uczestnik.getTelefon());
            userDTO.setEmail(uczestnik.getEmail());

            model.addAttribute("userDTO", userDTO);

            String username = getUsernameFromCSV(userId);
            model.addAttribute("username", username);

            return "user/update-profile";
        }

        return "redirect:/login";
    }

    private String getUsernameFromCSV(Integer userId) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String storedId = fields[0];
                String storedUsername = fields[1];

                if (storedId.equals(String.valueOf(userId))) {
                    return storedUsername;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute UserDTO userDTO, Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId != null) {
            UczestnicyDAO uczestnikDAO = new UczestnicyDAO(jdbcTemplate);
            Uczestnicy uczestnik = uczestnikDAO.get(userId);

            uczestnik.setImie(userDTO.getFirstName());
            uczestnik.setNazwisko(userDTO.getLastName());
            uczestnik.setData_urodzenia(userDTO.getDob());
            uczestnik.setTelefon(userDTO.getPhoneNumber());
            uczestnik.setEmail(userDTO.getEmail());

            uczestnikDAO.update(uczestnik);

            return "redirect:/main_user";
        }

        model.addAttribute("error", "User not found.");
        return "user/update-profile";
    }
}
