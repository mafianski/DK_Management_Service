package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie_Atrakcja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Wydarzenie_AtrakcjaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Wydarzenie_AtrakcjaDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wydarzenie_Atrakcja> list(){
        String sql = "SELECT * FROM Wydarzenie_Atrakcja";

        try {
            List<Wydarzenie_Atrakcja> listWydarzenie_Atrakcja = jdbcTemplate.query(sql, (rs, rowNum) -> new Wydarzenie_Atrakcja(
                    rs.getInt("nr_atrakcji"),
                    rs.getInt("nr_wydarzenia")));
            return listWydarzenie_Atrakcja;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Wydarzenie_Atrakcja wydarzenie_atrakcja){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Wydarzenie_Atrakcja").usingColumns("nr_atrakcji", "nr_wydarzenia");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie_atrakcja);
        insertActor.execute(param);
    }

    public Wydarzenie_Atrakcja get(int nr_atrakcji){
        String sql = "SELECT * FROM Wydarzenie_Atrakcja WHERE nr_atrakcji = ?";
        Wydarzenie_Atrakcja wydarzenie_atrakcja = jdbcTemplate.queryForObject(sql, new Object[]{nr_atrakcji}, BeanPropertyRowMapper.newInstance(Wydarzenie_Atrakcja.class));
        return wydarzenie_atrakcja;
    }

    public void update(Wydarzenie_Atrakcja wydarzenie_atrakcja){
        String sql = "UPDATE Wydarzenie_Atrakcja SET nr_wydarzenia=:nr_wydarzenia WHERE nr_atrakcji=:nr_atrakcji";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(wydarzenie_atrakcja);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_atrakcji){
        String sql = "DELETE FROM Wydarzenie_Atrakcja WHERE nr_atrakcji = ?";
        jdbcTemplate.update(sql, nr_atrakcji);
    }


}
