package org.exschool.pocketworld.service;

import org.exschool.pocketworld.model.Player;

public interface PlayerService {
	void savePlayer(Player player);
	Player getPlayerByLogin(String login);
}
