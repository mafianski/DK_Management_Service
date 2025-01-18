package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Pracownicy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PracownicyDAOTest {

    private PracownicyDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setUsername("BCZERWIN");
        dataSource.setPassword("BCZERWIN");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new PracownicyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void testList() {
        List<Pracownicy> listPracownicy = dao.list();
        System.out.println(listPracownicy);
        assertTrue(!listPracownicy.isEmpty());
    }

    @Test
    void testSave() {
        String dataUrodzenia = "1995-01-01";
        Pracownicy pracownik = new Pracownicy(0, "Jan", "Kowalski", dataUrodzenia, "123456789", "123@gmail.com", "12345678901", "M", 3, 3, 3);
        dao.save(pracownik);
    }

    @Test
    void testGet() {
        int id = 4;
        Pracownicy pracownik = dao.get(id);
        if (pracownik != null) {
            System.out.println(pracownik);
        }
        assertNotNull(pracownik);
    }

    @Test
    void update() {
        String dataUrodzenia = "1997-01-01";
        Pracownicy pracownik = new Pracownicy(3, "Jan", "Kowalski", dataUrodzenia, "123456789", "123@gmail.com", "12345678901", "M", 3, 3, 3);
        dao.update(pracownik);
    }

    @Test
    void delete() {
        int id = 6;
        dao.delete(id);
    }
}