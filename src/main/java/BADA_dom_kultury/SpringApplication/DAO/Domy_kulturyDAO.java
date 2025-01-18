package BADA_dom_kultury.SpringApplication.DAO;


import BADA_dom_kultury.SpringApplication.Tables.Domy_Kultury;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Domy_kulturyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Domy_kulturyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Domy_Kultury> list(){
        String sql = "SELECT * FROM Domy_Kultury";

        try {
            List<Domy_Kultury> listDomy_Kultury = jdbcTemplate.query(sql, (rs, rowNum) -> new Domy_Kultury(
                    rs.getInt("nr_domu_kultury"),
                    rs.getString("nazwa"),
                    rs.getString("wlasciciel"),
                    rs.getInt("nr_adresu")));
            return listDomy_Kultury;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Domy_Kultury dom_kultury){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Domy_Kultury").usingColumns("nr_domu_kultury", "nazwa", "wlasciciel", "nr_adresu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dom_kultury);
        insertActor.execute(param);
    }

    public Domy_Kultury get(int nr_domu_kultury){
        String sql = "SELECT * FROM Domy_Kultury WHERE nr_domu_kultury = ?";
        Domy_Kultury dom_kultury = jdbcTemplate.queryForObject(sql, new Object[]{nr_domu_kultury}, BeanPropertyRowMapper.newInstance(Domy_Kultury.class));
        return dom_kultury;
    }

    public void update(Domy_Kultury dom_kultury){
        String sql = "UPDATE Domy_kultury SET nazwa=:nazwa, wlasciciel=:wlasciciel, nr_adresu=:nr_adresu WHERE nr_domu_kultury=:nr_domu_kultury";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(dom_kultury);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nr_domu_kultury){
        String sql = "DELETE FROM Domy_Kultury WHERE nr_domu_kultury = ?";
        jdbcTemplate.update(sql, nr_domu_kultury);
    }

}
