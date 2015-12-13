package org.exschool.pocketworld.player;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
public class PlayerBootstrap {
    @Autowired
    private Dao dao;

    private volatile boolean bootstraped = false;

    public void fillDatabase() {
        if (bootstraped) return;
        Player player1 = PlayerBuilder.builder().login("login-1").playerResources(new PlayerResources(1,2,3,4)).build();
        Player player2 = PlayerBuilder.builder().login("login-2").playerResources(new PlayerResources(10,20,30,40)).build();
        Player player3 = PlayerBuilder.builder().login("login-3").playerResources(new PlayerResources(100,200,300,400)).build();

        dao.saveAll(Arrays.asList(player1, player2, player3));
        bootstraped = true;
    }
}
