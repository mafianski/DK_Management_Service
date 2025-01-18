package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Typy_Sprzetu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Typy_SprzetuDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Typy_SprzetuDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Typy_Sprzetu> list(){
        String sql = "SELECT * FROM TYPY_SPRZETU";

        try {
            List<Typy_Sprzetu> listTypy_Sprzetu = jdbcTemplate.query(sql, (rs, rowNum) -> new Typy_Sprzetu(
                    rs.getInt("nr_typu_sprzetu"),
                    rs.getString("typ_sprzetu"),
                    rs.getString("opis")));
            return listTypy_Sprzetu;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Typy_Sprzetu typy_sprzetu) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("TYPY_SPRZETU").usingColumns("nr_typu_sprzetu", "typ_sprzetu", "opis");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(typy_sprzetu);
        insertActor.execute(param);
    }

    public Typy_Sprzetu get(int nr_typu_sprzetu) {
        String sql = "SELECT * FROM TYPY_SPRZETU WHERE nr_typu_sprzetu = ?";
        Typy_Sprzetu typy_sprzetu = jdbcTemplate.queryForObject(sql, new Object[]{nr_typu_sprzetu}, BeanPropertyRowMapper.newInstance(Typy_Sprzetu.class));
        return typy_sprzetu;

    }

    public void update(Typy_Sprzetu typy_sprzetu) {
        String sql = "UPDATE TYPY_SPRZETU SET typ_sprzetu=:typ_sprzetu, opis=:opis WHERE nr_typu_sprzetu=:nr_typu_sprzetu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(typy_sprzetu);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_typu_sprzetu) {
        String sql = "DELETE FROM TYPY_SPRZETU WHERE nr_typu_sprzetu = ?";
        jdbcTemplate.update(sql, nr_typu_sprzetu);
    }
}
