package BADA_dom_kultury.SpringApplication.DAO;


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
        String sql = "SELECT * FROM Wydarzenia";

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
                "VALUES (?, ?, ?, TO_DATE(?, 'dd.MM.yyyy'), TO_DATE(?, 'dd.MM.yyyy'), ?, ?)";

        try {
            // Konwertowanie daty na String w formacie yyyy.MM.dd
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



    public Wydarzenie get(int nr_wydarzenia){
        String sql = "SELECT * FROM Wydarzenia WHERE nr_wydarzenia = ?";
        Wydarzenie wydarzenie = jdbcTemplate.queryForObject(sql, new Object[]{nr_wydarzenia}, BeanPropertyRowMapper.newInstance(Wydarzenie.class));
        return wydarzenie;
    }

    public void update(Wydarzenie wydarzenie){
        String sql = "UPDATE Wydarzenia SET nazwa=:nazwa, liczba_miejsc=:liczba_miejsc, data_start=:data_start, data_koniec=:data_koniec, nr_sali=:id_sali, nr_domu_kultury=:nr_domu_kultury WHERE nr_wydarzenia=:nr_wydarzenia";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_wydarzenia){
        String sql = "DELETE FROM Wydarzenia WHERE nr_wydarzenia = ?";
        jdbcTemplate.update(sql, nr_wydarzenia);
    }
}
