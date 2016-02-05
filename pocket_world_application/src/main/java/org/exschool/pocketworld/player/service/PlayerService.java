package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;

public interface PlayerService {
    void savePlayer(Player player);

    Player getPlayerByLogin(String login);

    Long createPlayer(String playerLogin, PlayerResources playerResources);

    boolean isPlayerExist(String login);

    Long getPlayerId(String login);
}
