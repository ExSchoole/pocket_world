package org.exschool.pocketworld.player;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.exschool.pocketworld.resource.model.Resource;
import org.exschool.pocketworld.resource.model.ResourceType;
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
        Player player1 = PlayerBuilder.builder().login("login-1").playerResources(new PlayerResources(new Resource(ResourceType.GOLD, 1), new Resource(ResourceType.TIMBER, 1), new Resource(ResourceType.CLAY, 1), new Resource(ResourceType.CORN, 1))).build();
        Player player2 = PlayerBuilder.builder().login("login-2").playerResources(new PlayerResources(new Resource(ResourceType.GOLD, 1), new Resource(ResourceType.TIMBER, 1), new Resource(ResourceType.CLAY, 1), new Resource(ResourceType.CORN, 1))).build();
        Player player3 = PlayerBuilder.builder().login("login-3").playerResources(new PlayerResources(new Resource(ResourceType.GOLD, 1), new Resource(ResourceType.TIMBER, 1), new Resource(ResourceType.CLAY, 1), new Resource(ResourceType.CORN, 1))).build();

        dao.saveAll(Arrays.asList(player1, player2, player3));
        bootstraped = true;
    }
}
