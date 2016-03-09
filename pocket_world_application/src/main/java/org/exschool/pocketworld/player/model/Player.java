package org.exschool.pocketworld.player.model;

import javax.persistence.*;


@Entity
@Table(name = "PLAYER")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Embedded
    private PlayerResources playerResources;

    @Column(name = "login")
    private String login;
    
    private String password;
    
    public Player() {
        this.login = "";
    }

    public Player(PlayerResources playerResources, String login) {
        this.playerResources = playerResources;
        this.login = login;
    }
    
    public Player(PlayerResources playerResources, String login,String password) {
        this.playerResources = playerResources;
        this.login = login;
        this.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public PlayerResources getPlayerResources() {
        return playerResources;
    }

    public void setPlayerResources(PlayerResources playerResources) {
        this.playerResources = playerResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (login != null ? !login.equals(player.login) : player.login != null) return false;
        if (playerResources != null ? !playerResources.equals(player.playerResources) : player.playerResources != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerResources != null ? playerResources.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerResources=" + playerResources +
                ", login='" + login + '\'' +
                '}';
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
