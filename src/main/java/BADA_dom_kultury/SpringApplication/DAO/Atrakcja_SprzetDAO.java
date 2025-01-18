package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Atrakcja_Sprzet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Atrakcja_SprzetDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Atrakcja_SprzetDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Atrakcja_Sprzet> list(){
        String sql = "SELECT * FROM Atrakcja_Sprzet";

        try {
            List<Atrakcja_Sprzet> listAtrakcja_Sprzet = jdbcTemplate.query(sql, (rs, rowNum) -> new Atrakcja_Sprzet(
                    rs.getInt("nr_atrakcji"),
                    rs.getInt("nr_sprzetu"),
                    rs.getInt("ilosc")));
            return listAtrakcja_Sprzet;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Atrakcja_Sprzet atrakcja_sprzet){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Atrakcja_Sprzet").usingColumns("nr_atrakcji", "nr_sprzetu", "ilosc");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atrakcja_sprzet);
        insertActor.execute(param);
    }

    public Atrakcja_Sprzet get(int id_atrakcji){
        String sql = "SELECT * FROM Atrakcja_Sprzet WHERE nr_atrakcji = ?";
        Atrakcja_Sprzet atrakcja_sprzet = jdbcTemplate.queryForObject(sql, new Object[]{id_atrakcji}, BeanPropertyRowMapper.newInstance(Atrakcja_Sprzet.class));
        return atrakcja_sprzet;
    }

    public void update(Atrakcja_Sprzet atrakcja_sprzet){
        String sql = "UPDATE Atrakcja_Sprzet SET nr_sprzetu=:nr_sprzetu, ilosc=:ilosc WHERE nr_atrakcji=:nr_atrakcji";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atrakcja_sprzet);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_atrakcji){
        String sql = "DELETE FROM Atrakcja_Sprzet WHERE nr_atrakcji = ?";
        jdbcTemplate.update(sql, nr_atrakcji);
    }

}
