import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	private Player player1 = new Player(1, "Amy", false, 'x');
	private Player player4 = new Player(1, "Amy", false, 'o');
	private Player player7 = new Player(1, "Amy", false, 'o');

	private Player player3 = new Player(1, "Amy", true, 'x');
	private Player player2 = new Player(2, "Bob", true, 'o');
	private Player player5 = new Player(2, "Bob", false, 'o');
	private Player player6 = new Player(2, "Bob", true, 'x');
	private Player player8 = new Player(2, "Bob", true, 'x');
	private Player playerNone = new Player(0, "none");;

	@Test
	public void testCreate(){
		assertNotEqualsPlayer(player1,player2);
		assertNotEqualsPlayer(player1,player3);
		assertNotEqualsPlayer(player1,player4);
		assertNotEqualsPlayer(player1,player5);
		assertNotEqualsPlayer(player1,player6);
		assertNotEqualsPlayer(player1,player8);
		assertNotEqualsPlayer(player1,playerNone);
		
		assertEqualsPlayer(player1,player7);
		assertEqualsPlayer(player2,player8);
		assertEqualsPlayer(playerNone,new Player(0, "none"));
		
	}
	
	static void assertNotEqualsPlayer(Player expPlayer,Player actualPlayer){
		boolean playersEqual = true;
		if (expPlayer.getName().equals("none")){
			//assertNull(actualPlayer.isAI());
			assertNull(actualPlayer.getMark());
		}else{
			if (expPlayer.getMark()!= actualPlayer.getMark()) playersEqual = false;
			if (expPlayer.isAI() != actualPlayer.isAI()) playersEqual = false;
		}
		if (!expPlayer.getName().equals(actualPlayer.getName())) playersEqual = false;
		if (expPlayer.getId() != actualPlayer.getId()) playersEqual = false;
		if (playersEqual) fail("??");
		
	}
	
	static void assertEqualsPlayer(Player expPlayer,Player actualPlayer){
		assertEquals(expPlayer.getId(),actualPlayer.getId());
		assertEquals(expPlayer.getName(),actualPlayer.getName());
		if (!expPlayer.getName().equals("none")){
			assertEquals(expPlayer.getMark(), actualPlayer.getMark());
			assertEquals(expPlayer.isAI(), actualPlayer.isAI());
		}
	}

}
