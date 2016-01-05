package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.player.model.Player;

public interface PlayerService {
	void savePlayer(Player player);
	Player getPlayerByLogin(String login);
	boolean isPlayerExist(String login);
	Long getPlayerId(String login);
}
