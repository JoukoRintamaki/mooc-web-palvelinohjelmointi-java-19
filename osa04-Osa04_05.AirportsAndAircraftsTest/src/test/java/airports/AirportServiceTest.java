package airports;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    AirportService airportService;

    @Autowired
    AirportRepository airportRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
        List<Airport> expected = new ArrayList<>();
        expected.add(new Airport("testi", "testi"));
        airportService.create("testi", "testi");
        List<Airport> actual1 = airportRepository.findAll();
        assertEquals(expected, actual1);
    }

    @Test
    public void list() {
        airportRepository.deleteAll();
        List<Airport> expected2 = new ArrayList<>();
        expected2.add(new Airport("testi1", "testi1"));
        expected2.add(new Airport("testi2", "testi2"));
        expected2.add(new Airport("testi3", "testi3"));
        expected2.add(new Airport("testi4", "testi4"));
        airportService.create("testi1", "testi1");
        airportService.create("testi2", "testi2");
        airportService.create("testi3", "testi3");
        airportService.create("testi4", "testi4");
        List<Airport> actual2 = airportRepository.findAll();
        assertEquals(expected2, actual2);
    }

    @Test
    public void createNotDublicate() {
        airportRepository.deleteAll();
        List<Airport> expected = new ArrayList<>();
        expected.add(new Airport("testi", "testi"));
        airportService.create("testi", "testi");
        airportService.create("testi", "testi");
        List<Airport> actual1 = airportRepository.findAll();
        assertEquals(expected, actual1);
    }
}