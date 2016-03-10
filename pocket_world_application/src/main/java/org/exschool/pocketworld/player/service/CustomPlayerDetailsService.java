package org.exschool.pocketworld.player.service;

import org.exschool.pocketworld.player.model.Player;
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

/**
 * Created by skandy on 10.03.16.
 */

@Service("customPlayerDetailsService")
public class CustomPlayerDetailsService implements UserDetailsService {
    @Autowired
    private PlayerService playerService;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Player player = playerService.getPlayerByLogin(s);
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

