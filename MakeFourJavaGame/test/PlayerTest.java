package test;

import core.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
	
	Player player1,player2,player3;
	final String AMY = "Amy";
	final String BOB = "Bob";
	final String ROB = "Rob";
	final char X = 'X';

	@BeforeEach
	protected void setUp() throws Exception {
;
		player1 = new Player(AMY);
		player2 = new Player(BOB,X);
		player3 = new Player(ROB,X,true);
	}

	@Test
	public void testPlayers(){
		assertPlayers(player1,new Player(AMY));
		assertPlayers(player2,new Player(BOB,X));
		assertPlayers(player2,new Player(BOB,X,false));
		assertPlayers(player3,new Player(ROB,X,true));
	}
	
	
	public static void assertPlayers(Player expPlayer, Player objPlayer){
		assertEquals(expPlayer.getName(), objPlayer.getName());
	}
	public static void assertPlayers(Player expPlayer, Player objPlayer,char expMark){
		assertPlayers(expPlayer, objPlayer);
		assertEquals(expPlayer.getMark(), objPlayer.getMark());
	}
	
	public static void assertPlayers(Player expPlayer, Player objPlayer,char expMark, boolean expAi){
		assertPlayers(expPlayer, objPlayer,expMark);
		assertEquals(expAi, expPlayer.isAI());
	}

}
