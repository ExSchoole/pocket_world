package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public Long createPlayer(String playerLogin, PlayerResources playerResources) {
        Player player = PlayerBuilder.builder()
                .login(playerLogin)
                .playerResources(playerResources)
                .build();
        savePlayer(player);
        return player.getId();
    }

    @Override
    public List<Player> getAll() {
        return dao.all(Player.class);
    }

    @Override
    public void savePlayer(Player player) {
        dao.save(player);
    }

    @Override
    public Player getPlayerByLogin(String login) {
        DetachedCriteria query = DetachedCriteria.forClass(Player.class);
        query.add(Restrictions.eq("login", login));
        return dao.getBy(query);
    }

    @Override
    public boolean isPlayerExist(String login) {
        return getPlayerByLogin(login) != null;
    }

    @Override
    public Long getPlayerId(String login) {
        return getPlayerByLogin(login) != null ?
                getPlayerByLogin(login).getId() :
                null;
    }

	@Override
	public boolean createPlayer(String playerName, String password) {
		try {
			if (!isPlayerExist(playerName)) {
				PlayerResources playerResources =  new PlayerResources(100,100, 100, 100);
				Player player = new Player(playerResources,playerName,password);
				savePlayer(player);
			}else{
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
