import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import base.GameMap;
import base.tile.EmptyTile;
import org.junit.jupiter.api.BeforeEach;

public class GameMapTest {
	
	
	GameMap map = new GameMap();;
	final int STANDARD_SIZE = 100;
	
	final String NEWLINE = System.lineSeparator();


	@Test
	public void testCreate(){
		assertEquals(STANDARD_SIZE,map.getSize());		
	}
	
	@Test
	public void testShowAll(){
		assertEquals(getEmptyGameMap(),map.showAll());
	}
	
	@Test
	public void testToString(){
		assertEquals(getEmptyGameHiddenMap(),map.toString());
	}
	
	@Test
	public void testRevelwithSmalView(){
		map.setBugAt(8,1);
		assertEquals(map1,map.toString());
	}
	
	
	@Test
	public void testRevelwithMediumView(){
		map.createBug(2);
		map.setBugAt(7,2);
		assertEquals(2, map.getBug().getSightLength());
		assertEquals(map2,map.toString());
	}
	
	@Test
	public void testRevelwithMediumView2(){
		map.createBug(2);
		map.setBugAt(9,0);
		assertEquals(2, map.getBug().getSightLength());
		assertEquals(map3,map.toString());
		
		map.setBugAt(5,4);
		assertEquals(2, map.getBug().getSightLength());
		assertEquals(map4,map.toString());
	}
	
	
	/// private methods
	
	private String getEmptyGameHiddenMap() {
		return fill('#');
	}	

	private String getEmptyGameMap() {
		return fill(EmptyTile.MARK);
	}
	
	private String fill(char mark){
		StringBuilder output = new StringBuilder();
		for (int i =0;i<10;i++){
			for (int j=0;j<10;j++){
				output.append(mark);
			}
			output.append(NEWLINE);
		}
		return output.toString();
	}
	
	///  test maps
	
	final String map1 = 
			  "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "...#######"+ NEWLINE
			+ ".b.#######"+ NEWLINE
			+ "...#######"+ NEWLINE;
	
	final String map2 = 
			  "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ ".....#####"+ NEWLINE
			+ ".....#####"+ NEWLINE
			+ "..b..#####"+ NEWLINE
			+ ".....#####"+ NEWLINE
			+ ".....#####"+ NEWLINE;
	
	final String map3 = 
			  "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "...#######"+ NEWLINE
			+ "...#######"+ NEWLINE
			+ "b..#######"+ NEWLINE;
	
	final String map4 = 
			  "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##########"+ NEWLINE
			+ "##.....###"+ NEWLINE
			+ "##.....###"+ NEWLINE
			+ "##..b..###"+ NEWLINE
			+ "##.....###"+ NEWLINE
			+ ".......###"+ NEWLINE
			+ "...#######"+ NEWLINE
			+ "...#######"+ NEWLINE;

}
