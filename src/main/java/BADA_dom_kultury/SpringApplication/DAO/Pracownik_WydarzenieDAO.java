package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Atrakcja_Sprzet;
import BADA_dom_kultury.SpringApplication.Tables.Pracownik_Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Pracownik_WydarzenieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Pracownik_WydarzenieDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Pracownik_Wydarzenie> list(){
        String sql = "SELECT * FROM Practownik_Wydarzenie";

        try {
            List<Pracownik_Wydarzenie> listPracownik_Wydarzenie = jdbcTemplate.query(sql, (rs, rowNum) -> new Pracownik_Wydarzenie(
                    rs.getInt("nr_pracownika"),
                    rs.getInt("nr_wydarzenia")));
            return listPracownik_Wydarzenie;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Pracownik_Wydarzenie pracownik_wydarzenie){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Pracownik_Wydarzenie").usingColumns("nr_pracownika", "nr_wydarzenia");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik_wydarzenie);
        insertActor.execute(param);
    }

    public Pracownik_Wydarzenie get(int nr_pracownika){
        String sql = "SELECT * FROM Pracownik_Wydarzenie WHERE nr_pracownika = ?";
        Pracownik_Wydarzenie pracownik_wydarzenie = jdbcTemplate.queryForObject(sql, new Object[]{nr_pracownika}, BeanPropertyRowMapper.newInstance(Pracownik_Wydarzenie.class));
        return pracownik_wydarzenie;
    }

    public void update(Pracownik_Wydarzenie pracownik_wydarzenie){
        String sql = "UPDATE Pracownik_Wydarzenie SET nr_wydarzenia=:nr_wydarzenia WHERE nr_pracownika=:nr_pracownika";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(pracownik_wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_pracownika){
        String sql = "DELETE FROM Pracownik_Wydarzenie WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql, nr_pracownika);
    }


}
