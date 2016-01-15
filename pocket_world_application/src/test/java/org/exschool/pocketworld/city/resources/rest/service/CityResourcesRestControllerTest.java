package org.exschool.pocketworld.city.resources.rest.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exschool.pocketworld.city.service.CityService;
import org.exschool.pocketworld.config.SpringWebConfig;
import org.exschool.pocketworld.config.TestSpringRootConfig;
import org.exschool.pocketworld.controllers.city.resources.CityResourcesRestController;
import org.exschool.pocketworld.dto.PositionOfBuilding;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.resource.building.model.ResourceBuilding;
import org.exschool.pocketworld.resource.building.service.ResourceBuildingService;
import org.exschool.pocketworld.util.builder.UserCityBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.*;
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

    @Mock
    private CityService cityService;

    @Mock
    private PlayerService playerService;

    @Mock
    private ResourceBuildingService resourceBuildingService;

    @InjectMocks
    CityResourcesRestController cityResourcesRestController;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(cityResourcesRestController).build();
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
        configureMocks();
        PositionOfBuilding positionOfBuilding = new PositionOfBuilding(10, "gold");
        String jsonInString = positionOfBuildingToJson(positionOfBuilding);

        mockMvc.perform(
                        post(ROOT + "/buildings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString))
                .andExpect(status().isCreated());
        verify(resourceBuildingService,times(1)).save(Mockito.any(ResourceBuilding.class));
    }

    private String positionOfBuildingToJson(PositionOfBuilding positionOfBuilding) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(positionOfBuilding);
    }

    private void configureMocks(){
        String playerLogin = "login-1";
        Long playerId = 1L;

        when(playerService.getPlayerByLogin(playerLogin))
                .thenReturn(PlayerBuilder
                        .builder()
                        .playerId(1L)
                        .login(playerLogin)
                        .playerResources(new PlayerResources(1,1,1,1))
                        .build());
        when(cityService.getCityByPlayerId(playerId))
                .thenReturn(UserCityBuilder
                        .builder()
                        .name("Test")
                        .playerId(playerId)
                        .build());
    }
}
