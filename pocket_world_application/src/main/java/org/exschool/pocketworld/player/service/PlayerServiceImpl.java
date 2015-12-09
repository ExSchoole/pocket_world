package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Override
    public void savePlayer(Player player) {
        dao.save(player);
    }

    @Override
    public Player getPlayerByLogin(String login) {
        DetachedCriteria query = DetachedCriteria.forClass(Player.class);
        query.add(Restrictions.eq("cityId", login));
        return dao.getBy(query);
    }
  

}
