package BADA_dom_kultury.SpringApplication.DAO;



import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import BADA_dom_kultury.SpringApplication.Tables.Uczestnicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class UczestnicyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UczestnicyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Uczestnicy> list(){
        String sql = "SELECT * FROM UCZESTNICY";


        try {
            List<Uczestnicy> listUczestnicy = jdbcTemplate.query(sql, (rs, rowNum) -> {
                String dataUrodzenia = rs.getString("data_urodzenia");
                String formattedDataUrodzenia = null;
                if (dataUrodzenia != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataUrodzenia = dataUrodzenia.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }


                return new Uczestnicy(
                        rs.getInt("nr_uczestnika"),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        formattedDataUrodzenia,
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getInt("nr_domu_kultury")
                );
            });
            return listUczestnicy;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Uczestnicy uczestnicy){
        String sql = "INSERT INTO UCZESTNICY (nr_uczestnika, imie, nazwisko, data_urodzenia, telefon, email, nr_domu_kultury) " +
                "VALUES (?, ?, ?, TO_DATE(?, 'yyyy-MM-dd'), ?, ?, ?)";
        try {
            // Pobieranie daty urodzenia i konwertowanie na format 'dd.MM.yyyy'
            String dataUrodzenia = uczestnicy.getData_urodzenia();

            // Wykonanie zapytania SQL z parametrami
            jdbcTemplate.update(sql,
                    uczestnicy.getNr_uczestnika(),
                    uczestnicy.getImie(),
                    uczestnicy.getNazwisko(),
                    dataUrodzenia,  // Parametr data_urodzenia (String w formacie 'dd.MM.yyyy')
                    uczestnicy.getTelefon(),
                    uczestnicy.getEmail(),
                    uczestnicy.getNr_domu_kultury());
        } catch (Exception e) {
            e.printStackTrace();
            // Obsługuje ewentualne błędy
        }
    }


    public Uczestnicy get(int nr_uczestnika){
        String sql = "SELECT * FROM UCZESTNICY WHERE NR_UCZESTNIKA = ?";

        // Użycie queryForObject z własnym RowMapperem
        Uczestnicy uczestnicy = jdbcTemplate.queryForObject(sql, new Object[]{nr_uczestnika}, (rs, rowNum) -> {
            Uczestnicy p = new Uczestnicy();

            // Mapowanie pozostałych pól
            p.setNr_uczestnika(rs.getInt("NR_UCZESTNIKA"));
            p.setImie(rs.getString("IMIE"));
            p.setNazwisko(rs.getString("NAZWISKO"));
            p.setTelefon(rs.getString("TELEFON"));
            p.setEmail(rs.getString("EMAIL"));
            p.setNr_domu_kultury(rs.getInt("NR_DOMU_KULTURY"));

            // Przetwarzanie pola DATA_URODZENIA na String w formacie dd.MM.yyyy
            java.sql.Date dataUrodzenia = rs.getDate("DATA_URODZENIA");
            if (dataUrodzenia != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                p.setData_urodzenia(sdf.format(dataUrodzenia)); // Przechowywanie jako String w odpowiednim formacie
            }

            return p;
        });

        return uczestnicy;
    }

    public void update(Uczestnicy uczestnik) {
        String sql = "UPDATE UCZESTNICY SET imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, telefon=:telefon, email=:email, nr_domu_kultury=:nr_domu_kultury WHERE nr_uczestnika=:nr_uczestnika";

        // Konwersja daty urodzenia (String na java.sql.Date)
        if (uczestnik.getData_urodzenia() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateUrodzenia = sdf.parse(uczestnik.getData_urodzenia());
                uczestnik.setData_urodzenia(new java.sql.Date(dateUrodzenia.getTime()).toString());
            } catch (ParseException e) {
                // Obsługa wyjątku, jeśli format daty jest nieprawidłowy
                e.printStackTrace();
            }
        }

        // Parametryzowanie zapytania
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(uczestnik);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_uczestnika){
        String sql = "DELETE FROM UCZESTNICY WHERE NR_UCZESTNIKA = ?";
        jdbcTemplate.update(sql, nr_uczestnika);
    }

    public List<Uczestnicy> getParticipantsForEvent(int eventId){
        String sql = "SELECT * FROM UCZESTNICY WHERE NR_UCZESTNIKA IN (SELECT NR_UCZESTNIKA FROM UCZESTNIK_WYDARZENIE WHERE NR_WYDARZENIA = ?)";

        try {
            List<Uczestnicy> listUczestnicy = jdbcTemplate.query(sql, new Object[]{eventId}, (rs, rowNum) -> {
                String dataUrodzenia = rs.getString("data_urodzenia");
                String formattedDataUrodzenia = null;
                if (dataUrodzenia != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataUrodzenia = dataUrodzenia.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }


                return new Uczestnicy(
                        rs.getInt("nr_uczestnika"),
                        rs.getString("imie"),
                        rs.getString("nazwisko"),
                        formattedDataUrodzenia,
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getInt("nr_domu_kultury")
                );
            });
            return listUczestnicy;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }




}
