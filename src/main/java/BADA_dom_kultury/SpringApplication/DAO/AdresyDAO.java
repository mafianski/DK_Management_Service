package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Adresy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresyDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AdresyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Adresy> list(){
        String sql = "SELECT * FROM ADRESY";

        try {
            List<Adresy> listAdresy = jdbcTemplate.query(sql, (rs, rowNum) -> new Adresy(
                    rs.getInt("nr_adresu"),
                    rs.getString("miasto"),
                    rs.getString("ulica"),
                    rs.getString("nr_budynku"),
                    rs.getString("nr_lokalu"),
                    rs.getInt("nr_poczty")));
            return listAdresy;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Adresy adres){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("ADRESY").usingColumns("nr_adresu", "miasto", "ulica", "nr_budynku", "nr_lokalu", "nr_poczty");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adres);
        insertActor.execute(param);
    }

    public Adresy get(int nrAdresu){
        String sql = "SELECT * FROM ADRESY WHERE nr_adresu = ?";
        Adresy adres = jdbcTemplate.queryForObject(sql, new Object[]{nrAdresu}, BeanPropertyRowMapper.newInstance(Adresy.class));
        return adres;
    }

    public void update(Adresy adres){
        String sql = "UPDATE ADRESY SET miasto=:miasto, ulica=:ulica, nr_budynku=:nr_budynku, nr_lokalu=:nr_lokalu, nr_poczty=:nr_poczty WHERE nr_adresu=:nr_adresu";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(adres);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nrAdresu){
        String sql = "DELETE FROM ADRESY WHERE nr_adresu = ?";
        jdbcTemplate.update(sql, nrAdresu);
    }


}
