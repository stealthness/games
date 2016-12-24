import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import base.GameMap;

public class GameMapTest2 {
	
	
	private static final int STANDARD_LENGTH = 10;
	private static final int STANDARD_WIDTH = 10;
	private static final int STANDARD_SIZE = 100;
	
	private static final int SMALL_LONG_LENGTH = 8;
	private static final int SMALL_LONG_WIDTH = 3;
	private static final int SMALL_LONG_SIZE = 24;
	
	GameMap map;
	GameMap smallLongMap;
	
	final String NEWLINE = System.lineSeparator();

	@BeforeEach
	public void setUp() throws Exception {
		map = new GameMap();
		smallLongMap = new GameMap(SMALL_LONG_LENGTH, SMALL_LONG_WIDTH);
	}

	
	@Test
	public void testCreate(){
		assertEquals(STANDARD_SIZE,map.getSize());	
		assertEquals(STANDARD_LENGTH, map.getLength());
		assertEquals(STANDARD_WIDTH, map.getWidth());
		
		assertEquals(SMALL_LONG_SIZE,smallLongMap.getSize());	
		assertEquals(SMALL_LONG_LENGTH, smallLongMap.getLength());
		assertEquals(SMALL_LONG_WIDTH, smallLongMap.getWidth());
	}
	
	@Test
	public void testCreatMap(){
		map = new GameMap(STANDARD_LENGTH,STANDARD_WIDTH);
		map.setBlockAT(1,1);
		map.setBlockAT(1,2);
		map.setBlockAT(1,STANDARD_WIDTH-2);
		map.setBlockAT(STANDARD_LENGTH-2,1);
		map.setBlockAT(STANDARD_LENGTH-2,STANDARD_WIDTH-2);
		map.setGoalAT(2,2);
		map.createBug(2);
		map.setBugAt(STANDARD_LENGTH-1, 0);
		assertEquals(map1All,map.showAll());
		assertEquals(map1,map.toString());
	}
	

	
	/// private methods
	

	
	///  test maps
	
	final String map1All = 
			  ".........."+ NEWLINE+
			  ".XX.....X."+ NEWLINE+
			  "..O......."+ NEWLINE+
			  ".........."+ NEWLINE+
			  ".........."+ NEWLINE+
			  ".........."+ NEWLINE+
			  ".........."+ NEWLINE+
			  ".........."+ NEWLINE+
			  ".X......X."+ NEWLINE+
			  ".........."+ NEWLINE;
	
	final String map1 = 
			  "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ ".#########"+ NEWLINE
			+ ".X########"+ NEWLINE
			+ "b..#######"+ NEWLINE;
	

}
