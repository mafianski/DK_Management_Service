package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Sala_Sprzet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Sala_SprzetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Sala_SprzetDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sala_Sprzet> list(){
        String sql = "SELECT * FROM Sala_Sprzet";

        try {
            List<Sala_Sprzet> listSala_Sprzet = jdbcTemplate.query(sql, (rs, rowNum) -> new Sala_Sprzet(
                    rs.getInt("nr_sali"),
                    rs.getInt("nr_sprzetu"),
                    rs.getInt("ilosc")));
            return listSala_Sprzet;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Sala_Sprzet Sala_Sprzet){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Sala_Sprzet").usingColumns("nr_sali", "nr_sprzetu", "ilosc");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(Sala_Sprzet);
        insertActor.execute(param);
    }

    public Sala_Sprzet get(int nr_sali){
        String sql = "SELECT * FROM Sala_Sprzet WHERE nr_sali = ?";
        Sala_Sprzet Sala_Sprzet = jdbcTemplate.queryForObject(sql, new Object[]{nr_sali}, BeanPropertyRowMapper.newInstance(Sala_Sprzet.class));
        return Sala_Sprzet;
    }

    public void update(Sala_Sprzet Sala_Sprzet){
        String sql = "UPDATE Sala_Sprzet SET nr_sprzetu=:nr_sprzetu, ilosc=:ilosc WHERE nr_sali=:nr_sali";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(Sala_Sprzet);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_sali){
        String sql = "DELETE FROM Sala_Sprzet WHERE nr_sali = ?";
        jdbcTemplate.update(sql, nr_sali);
    }

}
