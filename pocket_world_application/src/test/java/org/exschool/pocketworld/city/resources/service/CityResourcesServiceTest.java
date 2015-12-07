package org.exschool.pocketworld.city.resources.service;

import org.exschool.pocketworld.city.resources.dto.CityResourcesDto;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CityResourcesServiceTest {
    @Mock
    PlayerService playerService;
    @InjectMocks
    CityResourcesServiceImpl cityResourcesService = new CityResourcesServiceImpl();

    @Before
    public void before() {
        when(playerService.getPlayerByLogin(anyString())).thenReturn(new Player());
    }

    @Test
    public void testCityResourcesInfo() {
        CityResourcesDto cityResourcesDto = cityResourcesService.cityResourcesInfo();
        assertEquals(cityResourcesDto.getNickName(), "");
    }
}


