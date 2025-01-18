package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Uczestnicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UczestnicyDAOTest {



    private UczestnicyDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setUsername("BCZERWIN");
        dataSource.setPassword("BCZERWIN");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new UczestnicyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void testList() {
        List<Uczestnicy> listUczestnicy = dao.list();
        System.out.println(listUczestnicy);
        assertTrue(!listUczestnicy.isEmpty());
    }

    @Test
    void testSave() {
        String dataUrodzenia = "1995.01.01";
        Uczestnicy uczestnik = new Uczestnicy(0, "michal", "michalewski", dataUrodzenia, "123456789", "michal@gmail.com", 3);
        dao.save(uczestnik);
    }

    @Test
    void testGet() {
        int id = 4;
        Uczestnicy uczestnik = dao.get(id);
        if (uczestnik != null) {
            System.out.println(uczestnik);
        }
        assertNotNull(uczestnik);
    }

    @Test
    void testUpdate() {
        String dataUrodzenia = "1997.01.01";
        Uczestnicy uczestnik = new Uczestnicy(3, "Jan", "Kowalski", dataUrodzenia, "123456789", "abcd@gmail.com", 3);
        dao.update(uczestnik);
    }

    @Test
    void delete() {
        int id = 4;
        dao.delete(id);
    }
}