package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;

import java.util.List;

public interface PlayerService {
    List<Player> getAll();

    void savePlayer(Player player);

    Player getPlayerByLogin(String login);

    Long createPlayer(String playerLogin, PlayerResources playerResources);

    boolean isPlayerExist(String login);

    Long getPlayerId(String login);

	boolean createPlayer(String playerName, String password);
}
