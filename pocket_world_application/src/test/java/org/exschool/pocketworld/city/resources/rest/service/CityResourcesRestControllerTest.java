package org.exschool.pocketworld.city.resources.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exschool.pocketworld.config.SpringWebConfig;
import org.exschool.pocketworld.config.TestSpringRootConfig;
import org.exschool.pocketworld.dto.PositionOfBulding;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestSpringRootConfig.class, SpringWebConfig.class})
public class CityResourcesRestControllerTest {
    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private final String ROOT = "/v1/city/resources";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void test_get_resource_building_types() throws Exception {
        mockMvc.perform(get(ROOT + "/types"))
                .andExpect(status().isOk())
                .andExpect(
                        content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").value(hasItems("gold", "timber", "clay", "corn")));
    }

    @Test
    public void test_build_resource_building() throws Exception {
        PositionOfBulding positionOfBulding = new PositionOfBulding(10, "gold");

        String jsonInString = positionOfBuildingToJson(positionOfBulding);

        mockMvc.perform(
                        post(ROOT + "/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString))
                .andExpect(status().isCreated());
    }

    private String positionOfBuildingToJson(PositionOfBulding positionOfBulding) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(positionOfBulding);
    }
}
