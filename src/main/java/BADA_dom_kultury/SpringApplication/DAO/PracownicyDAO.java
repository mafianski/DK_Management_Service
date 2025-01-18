package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

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
            List<Pracownicy> listPracownicy = jdbcTemplate.query(sql, (rs, rowNum) -> new Pracownicy(
                    rs.getInt("nr_pracownika"),
                    rs.getString("imie"),
                    rs.getString("nazwisko"),
                    rs.getDate("data_urodzenia"),
                    rs.getString("telefon"),
                    rs.getString("email"),
                    rs.getString("pesel"),
                    rs.getString("plec"),
                    rs.getInt("nr_domu_kultury"),
                    rs.getInt("nr_adresu"),
                    rs.getInt("nr_stanowiska")));
            return listPracownicy;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Pracownicy pracownik){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("PRACOWNICY").usingColumns("nr_pracownika", "imie", "nazwisko", "data_urodzenia", "telefon", "email", "pesel", "plec", "nr_domu_kultury", "nr_adresu", "nr_stanowiska");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
        insertActor.execute(param);
    }

    public Pracownicy get(int nr_pracownika){
        String sql = "SELECT * FROM PRACOWNICY WHERE nr_pracownika = ?";
        Pracownicy pracownik = jdbcTemplate.queryForObject(sql, new Object[]{nr_pracownika}, BeanPropertyRowMapper.newInstance(Pracownicy.class));
        return pracownik;
    }

    public void update(Pracownicy pracownik){
        String sql = "UPDATE PRACOWNICY SET imie=:imie, nazwisko=:nazwisko, data_urodzenia=:data_urodzenia, telefon=:telefon, email=:email, pesel=:pesel, plec=:plec, nr_domu_kultury=:nr_domu_kultury, nr_adresu=:nr_adresu, nr_stanowiska=:nr_stanowiska WHERE nr_pracownika=:nr_pracownika";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nr_pracownika){
        String sql = "DELETE FROM PRACOWNICY WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, nr_pracownika);
    }
}
