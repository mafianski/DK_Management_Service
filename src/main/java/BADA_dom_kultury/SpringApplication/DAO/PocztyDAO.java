package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Poczty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PocztyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PocztyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Poczty> list(){
        String sql = "SELECT * FROM POCZTY";

        try {
            List<Poczty> listPoczty = jdbcTemplate.query(sql, (rs, rowNum) -> new Poczty(
                    rs.getInt("nr_Poczty"),
                    rs.getString("poczta"),
                    rs.getString("kod_Pocztowy")));
            return listPoczty;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }


    }

    public void save(Poczty poczta){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("POCZTY").usingColumns("nr_poczty", "poczta", "kod_pocztowy");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczta);
        insertActor.execute(param);
    }

    public Poczty get(int nrPoczty){
        String sql = "SELECT * FROM POCZTY WHERE nr_poczty = ?";
        Poczty poczta = jdbcTemplate.queryForObject(sql, new Object[]{nrPoczty}, BeanPropertyRowMapper.newInstance(Poczty.class));
        return poczta;
    }

    public void update(Poczty poczta){
        String sql = "UPDATE POCZTY SET poczta=:poczta, kod_pocztowy=:kod_pocztowy WHERE nr_poczty=:nr_poczty";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(poczta);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nrPoczty){
        String sql = "DELETE FROM POCZTY WHERE nr_poczty = ?";
        jdbcTemplate.update(sql, nrPoczty);
    }

}
