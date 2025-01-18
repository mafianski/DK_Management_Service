package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Sprzety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SprzetyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SprzetyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Sprzety> list(){
        String sql = "SELECT * FROM Sprzety";

        try {
            List<Sprzety> listSprzety = jdbcTemplate.query(sql, (rs, rowNum) -> new Sprzety(
                    rs.getInt("nr_sprzetu"),
                    rs.getString("nazwa"),
                    rs.getString("marka"),
                    rs.getString("stan"),
                    rs.getString("opis"),
                    rs.getInt("nr_domu_kultury"),
                    rs.getInt("nr_typu_sprzetu")));
            return listSprzety;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Sprzety sprzet){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Sprzety").usingColumns("nr_sprzetu", "nazwa", "marka", "stan", "opis", "nr_domu_kultury", "nr_typu_sprzetu");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sprzet);
        insertActor.execute(param);
    }

    public Sprzety get(int nr_sprzetu){
        String sql = "SELECT * FROM Sprzety WHERE nr_sprzetu = ?";
        Sprzety sprzet = jdbcTemplate.queryForObject(sql, new Object[]{nr_sprzetu}, BeanPropertyRowMapper.newInstance(Sprzety.class));
        return sprzet;
    }

    public void update(Sprzety sprzet) {
        String sql = "UPDATE Sprzety SET nazwa=:nazwa, marka=:marka, stan=:stan, opis=:opis, nr_domu_kultury=:nr_domu_kultury, nr_typu_sprzetu=:nr_typu_sprzetu WHERE nr_sprzetu=:nr_sprzetu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(sprzet);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_sprzetu){
        String sql = "DELETE FROM Sprzety WHERE nr_sprzetu = ?";
        jdbcTemplate.update(sql, nr_sprzetu);
    }


}
