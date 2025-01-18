package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class PracownicyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PracownicyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pracownicy> list(){
        String sql = "SELECT * FROM PRACOWNICY";

        try {
            List<Pracownicy> listPracownicy = jdbcTemplate.query(sql, (rs, rowNum) -> {
                    String dataUrodzenia = rs.getString("data_urodzenia");
                    String formattedDataUrodzenia = null;
                if (dataUrodzenia != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataUrodzenia = dataUrodzenia.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }


                return new Pracownicy(
                    rs.getInt("nr_pracownika"),
                    rs.getString("imie"),
                    rs.getString("nazwisko"),
                    formattedDataUrodzenia,
                    rs.getString("telefon"),
                    rs.getString("email"),
                    rs.getString("pesel"),
                    rs.getString("plec"),
                    rs.getInt("nr_domu_kultury"),
                    rs.getInt("nr_adresu"),
                    rs.getInt("nr_stanowiska")
                );
            });
            return listPracownicy;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Pracownicy pracownik){
        // Przygotowanie zapytania SQL
        String sql = "INSERT INTO PRACOWNICY (nr_pracownika, imie, nazwisko, data_urodzenia, telefon, email, pesel, plec, nr_domu_kultury, nr_adresu, nr_stanowiska) " +
                "VALUES (?, ?, ?, TO_DATE(?, 'yyyy-MM-dd'), ?, ?, ?, ?, ?, ?, ?)";

        try {
            // Pobieranie daty urodzenia i konwertowanie na format 'dd.MM.yyyy'
            String dataUrodzenia = pracownik.getData_urodzenia();

            // Wykonanie zapytania SQL z parametrami
            jdbcTemplate.update(sql,
                    pracownik.getNr_pracownika(),
                    pracownik.getImie(),
                    pracownik.getNazwisko(),
                    dataUrodzenia,  // Parametr data_urodzenia (String w formacie 'dd.MM.yyyy')
                    pracownik.getTelefon(),
                    pracownik.getEmail(),
                    pracownik.getPesel(),
                    pracownik.getPlec(),
                    pracownik.getNr_domu_kultury(),
                    pracownik.getNr_adresu(),
                    pracownik.getNr_stanowiska());
        } catch (Exception e) {
            e.printStackTrace();
            // Obsługuje ewentualne błędy
        }
    }


    public Pracownicy get(int nr_pracownika) {
        String sql = "SELECT * FROM PRACOWNICY WHERE NR_PRACOWNIKA = ?";

        // Użycie queryForObject z własnym RowMapperem
        Pracownicy pracownik = jdbcTemplate.queryForObject(sql, new Object[]{nr_pracownika}, (rs, rowNum) -> {
            Pracownicy p = new Pracownicy();

            // Mapowanie pozostałych pól
            p.setNr_pracownika(rs.getInt("NR_PRACOWNIKA"));
            p.setImie(rs.getString("IMIE"));
            p.setNazwisko(rs.getString("NAZWISKO"));
            p.setTelefon(rs.getString("TELEFON"));
            p.setEmail(rs.getString("EMAIL"));
            p.setPesel(rs.getString("PESEL"));
            p.setPlec(rs.getString("PLEC"));
            p.setNr_domu_kultury(rs.getInt("NR_DOMU_KULTURY"));
            p.setNr_adresu(rs.getInt("NR_ADRESU"));
            p.setNr_stanowiska(rs.getInt("NR_STANOWISKA"));

            // Przetwarzanie pola DATA_URODZENIA na String w formacie dd.MM.yyyy
            java.sql.Date dataUrodzenia = rs.getDate("DATA_URODZENIA");
            if (dataUrodzenia != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                p.setData_urodzenia(sdf.format(dataUrodzenia)); // Przechowywanie jako String w odpowiednim formacie
            }

            return p;
        });

        return pracownik;
    }


    public void update(Pracownicy pracownik) {
        String sql = "UPDATE PRACOWNICY SET imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, telefon=:telefon, email=:email, pesel=:pesel, plec=:plec, nr_domu_kultury=:nr_domu_kultury, nr_adresu=:nr_adresu, nr_stanowiska=:nr_stanowiska WHERE nr_pracownika=:nr_pracownika";

        // Konwersja daty urodzenia (String na java.sql.Date)
        if (pracownik.getData_urodzenia() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateUrodzenia = sdf.parse(pracownik.getData_urodzenia());
                pracownik.setData_urodzenia(new java.sql.Date(dateUrodzenia.getTime()).toString());
            } catch (ParseException e) {
                // Obsługa wyjątku, jeśli format daty jest nieprawidłowy
                e.printStackTrace();
            }
        }

        // Parametryzowanie zapytania
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }


    public void delete(int nr_pracownika){
        String sql = "DELETE FROM PRACOWNICY WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, nr_pracownika);
    }
}
