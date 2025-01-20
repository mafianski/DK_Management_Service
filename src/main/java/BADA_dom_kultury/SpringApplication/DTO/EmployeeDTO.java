package BADA_dom_kultury.SpringApplication.DTO;

import BADA_dom_kultury.SpringApplication.DAO.AdresyDAO;
import BADA_dom_kultury.SpringApplication.DAO.PocztyDAO;
import BADA_dom_kultury.SpringApplication.DAO.PracownicyDAO;
import BADA_dom_kultury.SpringApplication.DAO.StanowiskaDAO;
import BADA_dom_kultury.SpringApplication.Tables.Adresy;
import BADA_dom_kultury.SpringApplication.Tables.Poczty;
import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import BADA_dom_kultury.SpringApplication.Tables.Stanowiska;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private String phoneNumber;
    private String email;
    private String pesel;
    private String gender;
    private int houseNumber;
    private String addressCity;
    private String addressStreet;
    private String addressBuildingNumber;
    private String addressLocalNumber;
    private String postName;
    private String postalCode;
    private String positionName;
    private String username;
    private String password;



    // Gettery i settery


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressLocalNumber() {
        return addressLocalNumber;
    }

    public void setAddressLocalNumber(String addressLocalNumber) {
        this.addressLocalNumber = addressLocalNumber;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddressBuildingNumber() {
        return addressBuildingNumber;
    }

    public void setAddressBuildingNumber(String addressBuildingNumber) {
        this.addressBuildingNumber = addressBuildingNumber;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", pesel='" + pesel + '\'' +
                ", gender='" + gender + '\'' +
                ", houseNumber=" + houseNumber +
                ", addressCity='" + addressCity + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", addressBuildingNumber='" + addressBuildingNumber + '\'' +
                ", addressLocalNumber='" + addressLocalNumber + '\'' +
                ", postName='" + postName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", positionName='" + positionName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void addEmployeeToDatabase(EmployeeDTO employeeDTO, JdbcTemplate jdbcTemplate) {

        PracownicyDAO pracownicyDAO = new PracownicyDAO(jdbcTemplate);
        PocztyDAO pocztyDAO = new PocztyDAO(jdbcTemplate);
        Poczty poczty = new Poczty();
        AdresyDAO adresyDAO = new AdresyDAO(jdbcTemplate);
        Adresy adresy = new Adresy();
        StanowiskaDAO stanowiskaDAO = new StanowiskaDAO(jdbcTemplate);
        Stanowiska stanowiska = new Stanowiska();

        List<Poczty> pocztyList = pocztyDAO.list();
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


        List<Adresy> adresyList = adresyDAO.list();
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

        List<Stanowiska> stanowiskaList = stanowiskaDAO.list();
        int idStanowiska = Integer.parseInt(employeeDTO.getPositionName());

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

        System.out.println("Pracownik: " + pracownik.toString());

        id = 0;
        List<Pracownicy> pracownicy = pracownicyDAO.list();
        for (Pracownicy pracownik1 : pracownicy) {
            if(pracownik1.getPesel() == null) {
                pracownik1.setPesel("");

            }
            System.out.println("Pracownik1: " + pracownik1.toString());
            if (pracownik1.equals(pracownik)) {
                System.out.println("JEST RÓWNY");
                id = pracownik1.getNr_pracownika();
            }
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = employeeDTO.getPassword();  // Możesz zmienić, aby umożliwić użytkownikowi ustawienie hasła
        String hashedPassword = encoder.encode(rawPassword);

        // Możesz zapisać dane do pliku CSV
        try (FileWriter writer = new FileWriter("users.csv", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(id + "," + employeeDTO.getUsername() + "," + hashedPassword + "," + "WORKER");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static EmployeeDTO getEmployeeFromDatabase(int id, JdbcTemplate jdbcTemplate) {
        String sql = "SELECT * FROM PRACOWNICY INNER JOIN ADRESY ON PRACOWNICY.NR_ADRESU = ADRESY.NR_ADRESU " +
                "INNER JOIN POCZTY ON ADRESY.NR_POCZTY = POCZTY.NR_POCZTY " +
                "INNER JOIN STANOWISKA ON PRACOWNICY.NR_STANOWISKA = STANOWISKA.NR_STANOWISKA " +
                "WHERE NR_PRACOWNIKA = ?";

        // Użycie queryForObject z własnym RowMapperem
        EmployeeDTO employeeDTO = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> {
            EmployeeDTO e = new EmployeeDTO();

            // Mapowanie pozostałych pól
            e.setFirstName(rs.getString("IMIE"));
            e.setLastName(rs.getString("NAZWISKO"));
            e.setDob(rs.getString("DATA_URODZENIA"));
            e.setPhoneNumber(rs.getString("TELEFON"));
            e.setEmail(rs.getString("EMAIL"));
            e.setPesel(rs.getString("PESEL"));
            e.setGender(rs.getString("PLEC"));
            e.setPositionName(rs.getString("NR_STANOWISKA"));
            e.setAddressCity(rs.getString("MIASTO"));
            e.setAddressStreet(rs.getString("ULICA"));
            e.setAddressBuildingNumber(rs.getString("NR_BUDYNKU"));
            e.setAddressLocalNumber(rs.getString("NR_LOKALU"));
            e.setPostName(rs.getString("POCZTA"));
            e.setPostalCode(rs.getString("KOD_POCZTOWY"));

            // Przetwarzanie pola DATA_URODZENIA na String w formacie dd.MM.yyyy
            java.sql.Date dataUrodzenia = rs.getDate("DATA_URODZENIA");
            if (dataUrodzenia != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                e.setDob(sdf.format(dataUrodzenia)); // Przechowywanie jako String w odpowiednim formacie
            }

            return e;
        });
        return employeeDTO;

    }

    public void updateEmployeeInDatabase(EmployeeDTO employeeDTO, JdbcTemplate jdbcTemplate) {
        System.out.println("Cały employer" + employeeDTO.toString());
        PracownicyDAO pracownicyDAO = new PracownicyDAO(jdbcTemplate);
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
            System.out.println("Kod pocztowy z employera: " + employeeDTO.getPostalCode());
            System.out.println("Poczta z employera: " + employeeDTO.getPostName());
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
        pracownik.setNr_pracownika(employeeDTO.getId());
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

        pracownicyDAO.update(pracownik);
    }

}

