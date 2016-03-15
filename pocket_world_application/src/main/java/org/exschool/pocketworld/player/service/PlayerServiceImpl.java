package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.builder.PlayerBuilder;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.model.PlayerResources;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

@Service("playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService, UserDetailsService {

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

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Player player = getPlayerByLogin(s);
        if (player==null) {
            Formatter formatter = new Formatter();
            formatter.format(" User %s not found", s );
            throw new UsernameNotFoundException(formatter.toString());
        }
        return new User(player.getLogin(),
                player.getPassword(),
                getGrantedAuthorities());

    }
    private List<GrantedAuthority> getGrantedAuthorities(){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        return authorities;
    }
}
