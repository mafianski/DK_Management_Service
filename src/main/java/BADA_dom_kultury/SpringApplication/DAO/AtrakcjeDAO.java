package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Atrakcje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AtrakcjeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AtrakcjeDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Atrakcje> list(){
        String sql = "SELECT * FROM Atrakcje";

        try {
            List<Atrakcje> listAtrakcje = jdbcTemplate.query(sql, (rs, rowNum) -> new Atrakcje(
                    rs.getInt("nr_atrakcji"),
                    rs.getString("nazwa"),
                    rs.getInt("min_wiek"),
                    rs.getInt("czas_trwania"),
                    rs.getString("opis")));
            return listAtrakcje;
        } catch (Exception e) {
            System.out.println("Error executing query: " + e.toString());
            throw e;
        }
    }

    public void save(Atrakcje atrakcja){
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("Atrakcje").usingColumns("nr_atrakcji", "nazwa", "min_wiek", "czas_trwania", "opis");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atrakcja);
        insertActor.execute(param);

    }

    public Atrakcje get(int nr_atrakcji){
        String sql = "SELECT * FROM Atrakcje WHERE nr_atrakcji = ?";
        Atrakcje atrakcja = jdbcTemplate.queryForObject(sql, new Object[]{nr_atrakcji}, BeanPropertyRowMapper.newInstance(Atrakcje.class));
        return atrakcja;
    }

    public void update(Atrakcje atrakcja){
        String sql = "UPDATE Atrakcje SET nazwa=:nazwa, min_wiek=:min_wiek, czas_trwania=:czas_trwania, opis=:opis WHERE nr_atrakcji=:nr_atrakcji";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(atrakcja);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);

        template.update(sql, param);
    }

    public void delete(int nr_atrakcji){
        String sql = "DELETE FROM Atrakcje WHERE nr_atrakcji = ?";
        jdbcTemplate.update(sql, nr_atrakcji);
    }




}
