package BADA_dom_kultury.SpringApplication;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private PasswordEncoder passwordEncoder;

    // Setter dla wstrzyknięcia PasswordEncoder
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String storedUsername = fields[1];
                String storedHashedPassword = fields[2];
                String role = fields[3];

                // Sprawdzamy, czy login pasuje
                if (storedUsername.equals(username)) {
                    // Tworzymy i zwracamy obiekt User z odpowiednią rolą
                    return User.builder()
                            .username(storedUsername)
                            .password(storedHashedPassword)
                            .roles(role)
                            .build();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
