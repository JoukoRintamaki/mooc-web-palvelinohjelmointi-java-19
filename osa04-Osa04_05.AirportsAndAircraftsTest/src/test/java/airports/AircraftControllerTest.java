package airports;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AircraftControllerTest {

    @Autowired
    private AircraftRepository aircraftRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void agetModelAttributesExists() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("aircrafts"))
                .andExpect(model().attributeExists("airports"));
        ;
    }

    @Test
    public void bpostAircrafs() throws Exception {
        mockMvc.perform(
                post("/aircrafts")
                        .param("name", "HA-LOL")
        )
                .andExpect(status().is3xxRedirection());
        List<Aircraft> actual = aircraftRepository.findAll();
        List<Aircraft> exepted = new ArrayList<>();
        exepted.add(new Aircraft("HA-LOL"));
        assertEquals(exepted, actual);
    }

    @Test
    public void cpostAircrafs2() throws Exception {
        aircraftRepository.deleteAll();
        mockMvc.perform(
                post("/aircrafts")
                        .param("name", "XP-55")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void dmodelHasAttributeMessages() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("aircrafts"))
                .andExpect(model().attributeExists("airports"));
    }

    @Test
    public void fpostSuccess() throws Exception {
        mockMvc.perform(get("/aircrafts"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("aircrafts"));
        MvcResult mvcResult = mockMvc.perform(get("/aircrafts")).andReturn();
        List<Aircraft> actual = (List) mvcResult.getModelAndView().getModel().get("aircrafts");
        List<Aircraft> exepted = new ArrayList<>();
        exepted.add(new Aircraft("XP-55"));
        System.out.println(actual);
        System.out.println(exepted);
        System.out.println("perkele");
        assertEquals(exepted, actual);
    }

}