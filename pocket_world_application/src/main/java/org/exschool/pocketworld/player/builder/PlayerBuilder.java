package org.exschool.pocketworld.player.builder;

import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;

public final class PlayerBuilder {
    private String login;
    private Long playerId;
    private String password;
    private PlayerResources playerResources;

    public static PlayerBuilder builder() {
        return new PlayerBuilder();
    }

    private PlayerBuilder() {}

    public PlayerBuilder login(String login) {
        this.login = login;
        return this;
    }

    public PlayerBuilder playerId(Long playerId) {
        this.playerId = playerId;
        return this;
    }

    public PlayerBuilder password(String password) {
        this.password = password;
        return this;
    }

    public PlayerBuilder playerResources(PlayerResources playerResources) {
        this.playerResources = playerResources;
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setLogin(login);
        player.setId(playerId);
        player.setPassword(password);
        player.setPlayerResources(playerResources);
        return player;
    }
}
