package org.exschool.pocketworld.player;

import org.exschool.pocketworld.config.TestSpringConfig;
import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.player.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
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
    public void testCreatePlayerForPopulator() {
        String playerlogin = "testLogin";
        PlayerResources playerresources = new PlayerResources(1, 1, 1, 1);
        playerService.createPlayer(playerlogin, playerresources);
        Player createdPlayer = playerService.getPlayerByLogin(playerlogin);
        assertNotNull(createdPlayer);
        assertNotNull(createdPlayer.getId());
    }

    @Test
    public void testCreate() {
        String login = "test-login";
        Player player = PlayerBuilder.builder().login(login).build();
        playerService.savePlayer(player);
        Player savedPlayer = playerService.getPlayerByLogin(login);
        assertNotNull(savedPlayer);
        assertNotNull(savedPlayer.getId());
    }

    @Test
    public void testUpdate() {
        String existingPlayerLogin = "login-2";
        Player existingPlayer = playerService.getPlayerByLogin(existingPlayerLogin);
        assertNotNull(existingPlayer);
        existingPlayer.setPlayerResources(new PlayerResources(90, 80, 70, 60));
        playerService.savePlayer(existingPlayer);
        Player savedPlayer = playerService.getPlayerByLogin(existingPlayerLogin);
        assertTrue(savedPlayer.equals(existingPlayer));
    }

    @Test
    public void givenPlayerExist_getPlayerIdReturnPlayerId() {
        String existingPlayerLogin = "login-1";
        Player existingPlayer = playerService.getPlayerByLogin(existingPlayerLogin);
        Long id = playerService.getPlayerId(existingPlayerLogin);
        assertEquals(existingPlayer.getId(), id);
    }

    @Test
    public void givenPlayerDoesntExist_getPlayerIdReturnNull() {
        String nonExistingPlayerLogin = "login-10000";
        Long id = playerService.getPlayerId(nonExistingPlayerLogin);
        assertNull(id);
    }

    @Test
    public void givenPlayerDoesntExist_isPlayerExistReturnFalse() {
        String nonExistingPlayerLogin = "login-10000";
        boolean result = playerService.isPlayerExist(nonExistingPlayerLogin);
        assertFalse(result);
    }

    @Test
    public void givenPlayerExist_isPlayerExistReturnTrue() {
        String existingPlayerLogin = "login-1";
        boolean result = playerService.isPlayerExist(existingPlayerLogin);
        assertTrue(result);
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


