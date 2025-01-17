package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Poczty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PocztyDAOTest {

    private PocztyDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setUsername("BCZERWIN");
        dataSource.setPassword("BCZERWIN");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new PocztyDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void testList() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "BCZERWIN", "BCZERWIN");
        if (connection != null) {
            System.out.println("Connected successfully");
        } else {
            System.out.println("Connection failed");
        }

        List<Poczty> listPoczty = dao.list();
        System.out.println(listPoczty);
        assertTrue(listPoczty.isEmpty());
    }

    @Test
    void testSave() {
        Poczty poczta = new Poczty(0, "Poczta", "00-000");
        dao.save(poczta);
    }

    @Test
    void testGet() {
        Poczty poczta = dao.get(4);
        System.out.println(poczta);
        assertNotNull(poczta);
    }

    @Test
    void testUpdate() {
        Poczty poczta = new Poczty(6, "Wadowice", "11-200");

        dao.update(poczta);
        Poczty pocztaUpdated = dao.get(6);

        System.out.println(pocztaUpdated);
        assertEquals(poczta, pocztaUpdated);
    }

    @Test
    void testDelete() {
        int nrPoczty = 4;
        dao.delete(nrPoczty);
    }
}