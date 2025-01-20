package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Uczestnik_Wydarzenie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Uczestnik_WydarzenieDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Uczestnik_WydarzenieDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Uczestnik_Wydarzenie> list(){
        String sql = "SELECT * FROM Uczestnik_Wydarzenie";

        try {
            List<Uczestnik_Wydarzenie> listUczestnik_Wydarzenie = jdbcTemplate.query(sql, (rs, rowNum) -> new Uczestnik_Wydarzenie(
                    rs.getInt("nr_uczestnika"),
                    rs.getInt("nr_wydarzenia")));
            return listUczestnik_Wydarzenie;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Uczestnik_Wydarzenie uczestnik_wydarzenie){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Uczestnik_Wydarzenie").usingColumns("nr_uczestnika", "nr_wydarzenia");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(uczestnik_wydarzenie);
        insertActor.execute(param);
    }

    public Uczestnik_Wydarzenie get(int nr_uczestnika){
        String sql = "SELECT * FROM Uczestnik_Wydarzenie WHERE nr_uczestnika = ?";
        Uczestnik_Wydarzenie uczestnik_wydarzenie = jdbcTemplate.queryForObject(sql, new Object[]{nr_uczestnika}, BeanPropertyRowMapper.newInstance(Uczestnik_Wydarzenie.class));
        return uczestnik_wydarzenie;
    }

    public void update(Uczestnik_Wydarzenie uczestnik_wydarzenie){
        String sql = "UPDATE Uczestnik_Wydarzenie SET nr_wydarzenia=:nr_wydarzenia WHERE nr_uczestnika=:nr_uczestnika";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(uczestnik_wydarzenie);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_uczestnika){
        String sql = "DELETE FROM Uczestnik_Wydarzenie WHERE nr_uczestnika = ?";
        jdbcTemplate.update(sql, nr_uczestnika);
    }

    public boolean registerUserForEvent(int nr_uczestnika, int nr_wydarzenia) {
        String sql = "SELECT COUNT(*) FROM UCZESTNIK_WYDARZENIE WHERE nr_uczestnika = ? AND nr_wydarzenia = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, nr_uczestnika, nr_wydarzenia);
        if(count != null && count > 0) {
            return false;
        }
        Uczestnik_Wydarzenie uczestnik_wydarzenie = new Uczestnik_Wydarzenie();
        uczestnik_wydarzenie.setNr_uczestnika(nr_uczestnika);
        uczestnik_wydarzenie.setNr_wydarzenia(nr_wydarzenia);
        save(uczestnik_wydarzenie);
        return true;
    }

    public void delete(int nr_uczestnika, int nr_wydarzenia) {
        String sql = "DELETE FROM Uczestnik_Wydarzenie WHERE nr_uczestnika = ? AND nr_wydarzenia = ?";
        jdbcTemplate.update(sql, nr_uczestnika, nr_wydarzenia);
    }
}
