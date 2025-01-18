package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import BADA_dom_kultury.SpringApplication.Tables.Wynagrodzenia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class WynagrodzeniaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public WynagrodzeniaDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wynagrodzenia> list(){
        String sql = "SELECT * FROM Wynagrodzenia";

        try {
            List<Wynagrodzenia> listWynagrodzenia = jdbcTemplate.query(sql, (rs, rowNum) -> {

                String dataString = rs.getString("data");
                String formattedData = null;
                if (dataString != null) {
                    formattedData = dataString.substring(0, 10);
                }


                    return new Wynagrodzenia(
                    rs.getInt("nr_wynagrodzenia"),
                    formattedData,
                    rs.getBigDecimal("kwota_brutto"),
                    rs.getBigDecimal("kwota_netto"),
                    rs.getInt("nr_pracownika")
            );
        });
            return listWynagrodzenia;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Wynagrodzenia wynagrodzenia){
        String sql = "INSERT INTO Wynagrodzenia (nr_wynagrodzenia, data, kwota_brutto, kwota_netto, nr_pracownika)" +
                "VALUES (?, TO_DATE(?, 'yyyy-MM-dd'), ?, ?, ?)";
        try {
            String data = wynagrodzenia.getData();
            jdbcTemplate.update(sql,
                    wynagrodzenia.getNr_wynagrodzenia(),
                    data,
                    wynagrodzenia.getKwota_brutto(),
                    wynagrodzenia.getKwota_netto(),
                    wynagrodzenia.getNr_pracownika());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Wynagrodzenia get(int nr_wynagrodzenia){
        String sql = "SELECT * FROM Wynagrodzenia WHERE nr_wynagrodzenia = ?";

        Wynagrodzenia wynagrodzenia = jdbcTemplate.queryForObject(sql, new Object[]{nr_wynagrodzenia}, (rs, rowNum) -> {
            Wynagrodzenia w = new Wynagrodzenia();

            // Mapowanie pozostałych pól
            w.setNr_wynagrodzenia(rs.getInt("nr_wynagrodzenia"));
            w.setKwota_brutto(rs.getBigDecimal("kwota_brutto"));
            w.setKwota_netto(rs.getBigDecimal("kwota_netto"));
            w.setNr_pracownika(rs.getInt("nr_pracownika"));

            // Przetwarzanie pola DATA_START na String w formacie dd.MM.yyyy
            java.sql.Date dataStart = rs.getDate("data");
            if (dataStart != null) {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                w.setData(sdf.format(dataStart)); // Przechowywanie jako String w odpowiednim formacie
            }

            return w;
        });

        return wynagrodzenia;
    }

    public void update(Wynagrodzenia wynagrodzenia){
        String sql = "UPDATE Wynagrodzenia SET data=:data, kwota_brutto=:kwota_brutto, kwota_netto=:kwota_netto, nr_pracownika=:nr_pracownika WHERE nr_wynagrodzenia=:nr_wynagrodzenia";

        if(wynagrodzenia.getData() != null){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date date = sdf.parse(wynagrodzenia.getData());
                wynagrodzenia.setData(new java.sql.Date(date.getTime()).toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wynagrodzenia);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_wynagrodzenia){
        String sql = "DELETE FROM Wynagrodzenia WHERE nr_wynagrodzenia = ?";
        jdbcTemplate.update(sql, nr_wynagrodzenia);
    }




}
