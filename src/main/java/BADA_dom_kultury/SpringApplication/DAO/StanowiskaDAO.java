package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Stanowiska;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StanowiskaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StanowiskaDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Stanowiska> list() {
        String sql = "SELECT * FROM Stanowiska";

        try {
            List<Stanowiska> listStanowiska = jdbcTemplate.query(sql, (rs, rowNum) -> new Stanowiska(
                    rs.getInt("nr_stanowiska"),
                    rs.getString("nazwa"),
                    rs.getString("opis")));
            return listStanowiska;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Stanowiska stanowisko) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Stanowiska").usingColumns("nr_stanowiska", "nazwa", "opis");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stanowisko);
        insertActor.execute(param);
    }

    public Stanowiska get(int nr_stanowiska) {
        String sql = "SELECT * FROM Stanowiska WHERE nr_stanowiska = ?";
        Stanowiska stanowisko = jdbcTemplate.queryForObject(sql, new Object[]{nr_stanowiska}, BeanPropertyRowMapper.newInstance(Stanowiska.class));
        return stanowisko;
    }

    public void update(Stanowiska stanowisko) {
        String sql = "UPDATE Stanowiska SET nazwa=:nazwa, opis=:opis WHERE nr_stanowiska=:nr_stanowiska";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(stanowisko);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nr_stanowiska) {
        String sql = "DELETE FROM Stanowiska WHERE nr_stanowiska = ?";
        jdbcTemplate.update(sql, nr_stanowiska);
    }




}