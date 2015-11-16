package org.exschool.pocketworld.player;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.exschool.pocketworld.dao.Dao;
import org.exschool.pocketworld.player.model.Player;
import org.exschool.pocketworld.player.service.PlayerService;
import org.exschool.pocketworld.player.service.PlayerServiceImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;




public class PlayerServiceTest  {
	

	
    private static PlayerServiceImpl playerService;

	List<Player> players;
	Player player;
	Player player1;
	Player player2;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
    
	@Before
	   public void before(){
	      this.playerService = new PlayerServiceImpl();
	      this.playerService.setDao(mock(Dao.class));
	      
	      
	      players= new ArrayList<>();
	      
	      Player player = new Player();
	      player.setLogin("test");
	      playerService.savePlayer(player);
	      players.add(player);
			
	      Player player1 = new Player();
	      player.setLogin("test");
	      playerService.savePlayer(player1);
	      players.add(player1);
			
	      Player player2 = new Player();
	      player.setLogin("test1");
	      playerService.savePlayer(player2);
	      players.add(player2);
			
	      
	   }
	@After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testSave()
	{
		Iterator<Player> itr= players.iterator();
		while(itr.hasNext())
		{
			try {
					playerService.savePlayer(itr.next());
		     	}
			catch (Exception e) {
		    	  fail("Exeptiton!");
		      	}
		}
	}
	
	
	@Test
	public void testGetByLogin()
	{
		Iterator<Player> itr= players.iterator();
		while(itr.hasNext())
		{
			try {
					when(playerService.getPlayerByLogin(itr.next().getLogin())).thenReturn( player);
					assertTrue(true);
		     	}
			catch (Exception e) {
		    	  	fail("Exeptiton!");
		      	}
		}
		
	}
	

}


