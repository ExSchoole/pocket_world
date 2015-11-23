package org.exschool.pocketworld.player.model;

import javax.persistence.*;


@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue
    @Column(name = "player_id")
    private Long playerId;

    private Long resourcesID;

    @Column(name = "login")
    private String login;

    public Player() {
        this.login = "";
    }

    public Player(Long resourcesID, String login) {
        this.resourcesID = resourcesID;
        this.login = login;
    }

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

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", resourcesID=" + resourcesID +
                ", login='" + login + '\'' +
                '}';
    }
}
