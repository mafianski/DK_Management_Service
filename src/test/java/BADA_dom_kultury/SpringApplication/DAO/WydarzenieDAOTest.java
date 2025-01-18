package BADA_dom_kultury.SpringApplication.DAO;

import BADA_dom_kultury.SpringApplication.Tables.Poczty;
import BADA_dom_kultury.SpringApplication.Tables.Wydarzenie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WydarzenieDAOTest {

    private WydarzenieDAO dao;

    @BeforeEach
    void setUp() throws Exception {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setUsername("BCZERWIN");
        dataSource.setPassword("BCZERWIN");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dao = new WydarzenieDAO(new JdbcTemplate(dataSource));
    }

    @Test
    void testList() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "BCZERWIN", "BCZERWIN");
        if (connection != null) {
            System.out.println("Connected successfully");
        } else {
            System.out.println("Connection failed");
        }

        List<Wydarzenie> listWydarzenie = dao.list();
        System.out.println(listWydarzenie);
        assertTrue(!listWydarzenie.isEmpty());

    }

    @Test
    void testSave() throws ParseException {
        String startDateString = "03.04.2025";
        String endDateString = "03.06.2025";
        Wydarzenie wydarzenie = new Wydarzenie(0, "Koncert", 100, startDateString, endDateString, 5, 3);
        dao.save(wydarzenie);
        wydarzenie = new Wydarzenie(1, "Warsztaty", 100, startDateString, endDateString, 5, 3);
        dao.save(wydarzenie);
        wydarzenie = new Wydarzenie(2, "ABCDE", 100, startDateString, endDateString, 5, 3);
        dao.save(wydarzenie);
    }

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}