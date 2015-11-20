package org.exschool.pocketworld.player;

import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = TestSpringConfig.class)
public class PlayerServiceTest {

    @Autowired
    PlayerService playerService;
    @Autowired
    PlayerBootstrap bootstrap;
    @Autowired
    Dao dao;

    @Before
    public void before() {
        bootstrap.fillDatabase();
    }

    @Test
    public void testCreate() {
        String login = "test-login";
        Player player = PlayerBuilder.builder().login(login).build();
        playerService.savePlayer(player);
        Player savedPlayer = playerService.getPlayerByLogin(login);
        assertNotNull(savedPlayer);
        assertNotNull(savedPlayer.getPlayerId());
    }

    @Test
    public void testUpdate() {
        String existingPlayerLogin = "login-2";
        Player existingPlayer = playerService.getPlayerByLogin(existingPlayerLogin);
        assertNotNull(existingPlayer);
        existingPlayer.setResourcesID(123L);
        playerService.savePlayer(existingPlayer);
        Player savedPlayer = playerService.getPlayerByLogin(existingPlayerLogin);
        assertTrue(savedPlayer.equals(existingPlayer));
    }

    @Test(expected = Exception.class)
    public void testSaveNull() {
        playerService.savePlayer(null);
    }


    @Test
    public void testGetByLogin() {
        Player existingPlayer = playerService.getPlayerByLogin("login-3");
        assertNotNull(existingPlayer);
    }
}

