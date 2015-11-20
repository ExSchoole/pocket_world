package org.exschool.pocketworld.player;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
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
        Player player1 = PlayerBuilder.builder().login("login-1").resourceId(1L).build();
        Player player2 = PlayerBuilder.builder().login("login-2").resourceId(1L).build();
        Player player3 = PlayerBuilder.builder().login("login-3").resourceId(1L).build();

        dao.saveAll(Arrays.asList(player1, player2, player3));
        bootstraped = true;
    }
}
