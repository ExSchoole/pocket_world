package org.exschool.pocketworld.player.model;

import javax.persistence.*;


@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long playerId;

    Long resourcesID;

    @Column(name = "login")
    private String login;

    public Long getPlayerId() {
        return playerId;
    }

    public String getLogin() {
        return login;
    }


    public void setPlayerId(Long id) {
        this.playerId = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getResourcesID() {
        return resourcesID;
    }

    public void setResourcesID(Long resourcesID) {
        this.resourcesID = resourcesID;
    }

    public Player(Player player) {
        this.login = player.getLogin();
    }

    public Player() {
        this.login = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (playerId != null ? !playerId.equals(player.playerId) : player.playerId != null) return false;
        if (resourcesID != null ? !resourcesID.equals(player.resourcesID) : player.resourcesID != null) return false;
        return !(login != null ? !login.equals(player.login) : player.login != null);

    }

    @Override
    public int hashCode() {
        int result = playerId != null ? playerId.hashCode() : 0;
        result = 31 * result + (resourcesID != null ? resourcesID.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }
}
