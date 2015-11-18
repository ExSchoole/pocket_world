package org.exschool.pocketworld.player.builder;

import org.exschool.pocketworld.player.model.Player;

public final class PlayerBuilder {
    private String login;
    private Long playerId;
    private Long resourceId;

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

    public PlayerBuilder resourceId(Long resourceId) {
        this.resourceId = resourceId;
        return this;
    }

    public Player build() {
        Player player = new Player();
        player.setLogin(login);
        player.setPlayerId(playerId);
        player.setResourcesID(resourceId);
        return player;
    }
}
