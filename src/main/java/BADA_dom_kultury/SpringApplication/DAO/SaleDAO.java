package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SaleDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sale> list(){
        String sql = "SELECT * FROM Sale";

        try {
            List<Sale> listSale = jdbcTemplate.query(sql, (rs, rowNum) -> new Sale(
                    rs.getInt("nr_sali"),
                    rs.getString("nazwa"),
                    rs.getString("typ_sali"),
                    rs.getInt("liczba_miejsc"),
                    rs.getString("opis"),
                    rs.getInt("nr_domu_kultury")));
            return listSale;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Sale sala){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Sale").usingColumns("nr_sali", "nazwa", "typ_sali", "liczba_miejsc", "opis", "nr_domu_kultury");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sala);
        insertActor.execute(param);
    }

    public Sale get(int nr_sali){
        String sql = "SELECT * FROM Sale WHERE nr_sali = ?";
        Sale sala = jdbcTemplate.queryForObject(sql, new Object[]{nr_sali}, BeanPropertyRowMapper.newInstance(Sale.class));
        return sala;
    }

    public void update(Sale sala){
        String sql = "UPDATE Sale SET nazwa=:nazwa, typ_sali=:typ_sali, liczba_miejsc=:liczba_miejsc, opis=:opis, nr_domu_kultury=:nr_domu_kultury WHERE nr_sali=:nr_sali";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sala);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_sali){
        String sql = "DELETE FROM Sale WHERE nr_sali = ?";
        jdbcTemplate.update(sql, nr_sali);
    }
}
