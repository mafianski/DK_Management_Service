package BADA_dom_kultury.SpringApplication.DAO;


import BADA_dom_kultury.SpringApplication.EventManagment;
import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class WydarzenieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WydarzenieDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wydarzenie> list() {
        String sql = "SELECT * FROM Wydarzenia ORDER BY data_start ASC";

        try {
            List<Wydarzenie> listWydarzenie = jdbcTemplate.query(sql, (rs, rowNum) -> {
                // Pobieranie daty jako String z bazy danych
                String dataStartString = rs.getString("data_start");
                String dataKoniecString = rs.getString("data_koniec");

                // Sformatowanie daty do 'dd.MM.yyyy'
                String formattedDataStart = null;
                String formattedDataKoniec = null;

                if (dataStartString != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataStart = dataStartString.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }

                if (dataKoniecString != null) {
                    formattedDataKoniec = dataKoniecString.substring(0, 10);  // Podobnie dla daty końca
                    // formattedDataKoniec = formattedDataKoniec.replace("-", ".");
                }

                return new Wydarzenie(
                        rs.getInt("nr_wydarzenia"),
                        rs.getString("nazwa"),
                        rs.getInt("liczba_miejsc"),
                        formattedDataStart,  // Przekazywanie już sformatowanej daty
                        formattedDataKoniec,
                        rs.getInt("nr_sali"),
                        rs.getInt("nr_domu_kultury")
                );
            });

            for (Wydarzenie wydarzenie : listWydarzenie) {
                wydarzenie.addImageUrl(wydarzenie.getNazwa());
            }
            return listWydarzenie;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }


    public void save(Wydarzenie wydarzenie) {
        // Przygotowanie zapytania SQL
        String sql = "INSERT INTO Wydarzenia (nr_wydarzenia, nazwa, liczba_miejsc, data_start, data_koniec, nr_sali, nr_domu_kultury) " +
                "VALUES (?, ?, ?, TO_DATE(?, 'yyyy-MM-dd'), TO_DATE(?, 'yyyy-MM-dd'), ?, ?)";

        try {
            // Konwertowanie daty na String w formacie yyyy-MM-dd
            String dataStart = wydarzenie.getData_start();
            String dataKoniec = wydarzenie.getData_koniec();

            // Wykonanie zapytania SQL z parametrami
            jdbcTemplate.update(sql,
                    wydarzenie.getNr_wydarzenia(),
                    wydarzenie.getNazwa(),
                    wydarzenie.getLiczba_miejsc(),
                    dataStart,  // Parametr data_start (String)
                    dataKoniec, // Parametr data_koniec (String)
                    wydarzenie.getNr_sali(),
                    wydarzenie.getNr_domu_kultury());
        } catch (Exception e) {
            e.printStackTrace();
            // Obsługuje ewentualne błędy
        }
    }



    public Wydarzenie get(int nr_wydarzenia) {
        String sql = "SELECT * FROM Wydarzenia WHERE NR_WYDARZENIA = ?";

        // Użycie queryForObject z własnym RowMapperem
        Wydarzenie wydarzenie = jdbcTemplate.queryForObject(sql, new Object[]{nr_wydarzenia}, (rs, rowNum) -> {
            Wydarzenie w = new Wydarzenie();

            // Mapowanie pozostałych pól
            w.setNr_wydarzenia(rs.getInt("NR_WYDARZENIA"));
            w.setNazwa(rs.getString("NAZWA"));
            w.setLiczba_miejsc(rs.getInt("LICZBA_MIEJSC"));
            w.setNr_sali(rs.getInt("NR_SALI"));
            w.setNr_domu_kultury(rs.getInt("NR_DOMU_KULTURY"));

            // Przetwarzanie pola DATA_START na String w formacie dd.MM.yyyy
            java.sql.Date dataStart = rs.getDate("DATA_START");
            if (dataStart != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                w.setData_start(sdf.format(dataStart)); // Przechowywanie jako String w odpowiednim formacie
            }

            // Przetwarzanie pola DATA_KONIEC na String w formacie dd.MM.yyyy
            java.sql.Date dataKoniec = rs.getDate("DATA_KONIEC");
            if (dataKoniec != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                w.setData_koniec(sdf.format(dataKoniec)); // Przechowywanie jako String w odpowiednim formacie
            }

            return w;
        });

        return wydarzenie;
    }



    public void update(Wydarzenie wydarzenie) {
        String sql = "UPDATE Wydarzenia SET nazwa=:nazwa, liczba_miejsc=:liczba_miejsc, data_start=:data_start, data_koniec=:data_koniec, nr_sali=:nr_sali, nr_domu_kultury=:nr_domu_kultury WHERE nr_wydarzenia=:nr_wydarzenia";

        // Konwersja daty startowej (String na java.sql.Date)
        if (wydarzenie.getData_start() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateStart = sdf.parse(wydarzenie.getData_start());
                wydarzenie.setData_start(new java.sql.Date(dateStart.getTime()).toString());
            } catch (ParseException e) {
                // Obsługa wyjątku, jeśli format daty jest nieprawidłowy
                e.printStackTrace();
            }
        }

        // Konwersja daty końcowej (String na java.sql.Date)
        if (wydarzenie.getData_koniec() != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date dateKoniec = sdf.parse(wydarzenie.getData_koniec());
                wydarzenie.setData_koniec(new java.sql.Date(dateKoniec.getTime()).toString());
            } catch (ParseException e) {
                // Obsługa wyjątku, jeśli format daty jest nieprawidłowy
                e.printStackTrace();
            }
        }

        // Parametryzowanie zapytania
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }


    public void delete(int nr_wydarzenia){
        String sql = "DELETE FROM Wydarzenia WHERE nr_wydarzenia = ?";
        jdbcTemplate.update(sql, nr_wydarzenia);
    }

    public List<Wydarzenie> getAttendedEvents(Integer userId) {
        String sql = "SELECT * FROM wydarzenia INNER JOIN uczestnik_wydarzenie ON wydarzenia.Nr_wydarzenia = uczestnik_wydarzenie.nr_wydarzenia WHERE uczestnik_wydarzenie.nr_uczestnika = ?";
        try {
            List<Wydarzenie> listWydarzenie = jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
                // Pobieranie daty jako String z bazy danych
                String dataStartString = rs.getString("data_start");
                String dataKoniecString = rs.getString("data_koniec");

                // Sformatowanie daty do 'dd.MM.yyyy'
                String formattedDataStart = null;
                String formattedDataKoniec = null;

                if (dataStartString != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataStart = dataStartString.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }

                if (dataKoniecString != null) {
                    formattedDataKoniec = dataKoniecString.substring(0, 10);  // Podobnie dla daty końca
                    // formattedDataKoniec = formattedDataKoniec.replace("-", ".");
                }

                return new Wydarzenie(
                        rs.getInt("nr_wydarzenia"),
                        rs.getString("nazwa"),
                        rs.getInt("liczba_miejsc"),
                        formattedDataStart,  // Przekazywanie już sformatowanej daty
                        formattedDataKoniec,
                        rs.getInt("nr_sali"),
                        rs.getInt("nr_domu_kultury")
                );
            });

            for (Wydarzenie wydarzenie : listWydarzenie) {
                wydarzenie.addImageUrl(wydarzenie.getNazwa());
            }
            return listWydarzenie;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }

    }

    public boolean isEventAssigned(int nr_sali) {
        String sql = "SELECT COUNT(*) FROM WYDARZENIA WHERE nr_sali = ?";
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{nr_sali}, Integer.class);
        return count != null && count > 0;
    }

    public List<EventManagment> getListForEvents(){
        String sql = "SELECT w.nr_wydarzenia, w.nazwa, w.liczba_miejsc, w.data_start, w.data_koniec, w.nr_sali, w.nr_domu_kultury, COUNT(uw.nr_uczestnika) AS liczba_zapisanych, s.nazwa AS nazwa_sali FROM WYDARZENIA w LEFT JOIN uczestnik_wydarzenie uw ON w.nr_wydarzenia = uw.nr_wydarzenia LEFT JOIN sale s ON w.nr_sali = s.nr_sali GROUP BY w.nr_wydarzenia, w.nazwa, w.liczba_miejsc, w.data_start, w.data_koniec, w.nr_sali, w.nr_domu_kultury, s.nazwa";
        try {
            List<EventManagment> listWydarzenie = jdbcTemplate.query(sql, (rs, rowNum) -> {
                // Pobieranie daty jako String z bazy danych
                String dataStartString = rs.getString("data_start");
                String dataKoniecString = rs.getString("data_koniec");

                // Sformatowanie daty do 'dd.MM.yyyy'
                String formattedDataStart = null;
                String formattedDataKoniec = null;

                if (dataStartString != null) {
                    // Wybieramy tylko część daty (dzień, miesiąc, rok)
                    formattedDataStart = dataStartString.substring(0, 10);  // Zaczynamy od pierwszych 10 znaków (yyyy-MM-dd)
                    // Możesz również dodatkowo zmienić format (np. na 'dd.MM.yyyy'), jeśli jest taka potrzeba
                    // formattedDataStart = formattedDataStart.replace("-", ".");
                }

                if (dataKoniecString != null) {
                    formattedDataKoniec = dataKoniecString.substring(0, 10);  // Podobnie dla daty końca
                    // formattedDataKoniec = formattedDataKoniec.replace("-", ".");
                }

                return new EventManagment(
                        rs.getInt("nr_wydarzenia"),
                        rs.getString("nazwa"),
                        rs.getInt("liczba_miejsc"),
                        formattedDataStart,  // Przekazywanie już sformatowanej daty
                        formattedDataKoniec,
                        rs.getString("nazwa_sali"),
                        3,
                        rs.getInt("liczba_zapisanych")
                );
            });

            return listWydarzenie;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }

    }
}
