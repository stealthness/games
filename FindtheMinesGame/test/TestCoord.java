import static org.junit.Assert.*;
import org.junit.*;

import core.Coord;

public class TestCoord {
	
	// Coord is in the form (X,Y)
	//
	//    4
	//    3        .(4,3)
	//  Y 2
	//    1
	//    0  .(1,0)
	//     0 1 2 3 4 
	//        X
	//
	//       up
	//   left  right
	//      down
	//
	
	Coord coord00,coord11,coord22,coord33,coordn12;
	Coord coord01,coord02,coord10,coord12,coord20,coord21;

	@Before
	public void setUp() throws Exception {
		coord00 = new Coord(0,0);
		coord11 = new Coord(1,1);
		coord22 = new Coord(2,2);
		coord33 = new Coord(3,3);			
		coordn12 = new Coord(-1,2);	
		
		coord01 = new Coord(0,1);
		coord02 = new Coord(0,2);
		coord10 = new Coord(1,0);
		coord12 = new Coord(1,2);
		coord20 = new Coord(2,0);
		coord21 = new Coord(2,1);

	}

	
	@Test
	public void testCreate(){
		assertCoord(coord00,0,0);
		assertCoord(coord11,1,1);
		assertCoord(coord22,2,2);
		
		assertFailCoord(coord33);
		assertFailCoord(coordn12);
	}

	private void assertFailCoord(Coord coord) {
		assertEquals(coord.getX(),Coord.getMinX()-1);
		assertEquals(coord.getY(),Coord.getMinY()-1);
		
	}
	
	private void assertCoord(Coord coord, Coord expectedCoord){
		assertCoord(coord,expectedCoord.getX(),expectedCoord.getY());
	}
	
	private void assertCoord(Coord coord, int x, int y) {
		assertEquals(coord.getX(),x);
		assertEquals(coord.getY(),y);		
	}
	
	@Test
	public void testMoveCoordUpBy1(){
		assertCoord(coord00.moveUp(),coord01);
		assertCoord(coord10.moveUp(),coord11);
		assertCoord(coord20.moveUp(),coord21);		

		assertCoord(coord01.moveUp(),coord02);
		assertCoord(coord11.moveUp(),coord12);
		assertCoord(coord21.moveUp(),coord22);		

		assertCoord(coord02.moveUp(),coord02);
		assertCoord(coord12.moveUp(),coord12);
		assertCoord(coord22.moveUp(),coord22);
	}
	
	@Test
	public void testMoveCoordDownBy1(){		

		assertCoord(coord02.moveDown(),coord01);
		assertCoord(coord12.moveDown(),coord11);
		assertCoord(coord22.moveDown(),coord21);		

		assertCoord(coord01.moveDown(),coord00);
		assertCoord(coord11.moveDown(),coord10);
		assertCoord(coord21.moveDown(),coord20);
		
		assertCoord(coord00.moveDown(),coord00);
		assertCoord(coord10.moveDown(),coord10);
		assertCoord(coord20.moveDown(),coord20);
	}
	
	@Test
	public void testMoveCoordLeftBy1(){
		
		assertCoord(coord20.moveLeft(),coord10);		
		assertCoord(coord21.moveLeft(),coord11);
		assertCoord(coord22.moveLeft(),coord12);
		
		assertCoord(coord10.moveLeft(),coord00);
		assertCoord(coord11.moveLeft(),coord01);
		assertCoord(coord12.moveLeft(),coord02);
		
		assertCoord(coord00.moveLeft(),coord00);
		assertCoord(coord01.moveLeft(),coord01);		
		assertCoord(coord02.moveLeft(),coord02);	
		
	}
	
	@Test
	public void testMoveCoordRightBy1(){
		assertCoord(coord00.moveRight(),coord10);
		assertCoord(coord01.moveRight(),coord11);		
		assertCoord(coord02.moveRight(),coord12);	
		
		assertCoord(coord10.moveRight(),coord20);
		assertCoord(coord11.moveRight(),coord21);
		assertCoord(coord12.moveRight(),coord22);
		
		assertCoord(coord20.moveRight(),coord20);		
		assertCoord(coord21.moveRight(),coord21);
		assertCoord(coord22.moveRight(),coord22);
	}
	
	@Test
	public void testMoveTo(){
		// testing the moveto(coord) method
		assertCoord(coord00.moveTo(coord10),coord10);
		assertCoord(coord01.moveTo(coord10),coord10);		
		assertCoord(coord02.moveTo(coord10),coord10);	
		
		assertCoord(coord10.moveTo(coord22),coord22);
		assertCoord(coord11.moveTo(coord22),coord22);
		assertCoord(coord12.moveTo(coord22),coord22);
		
		assertCoord(coord20.moveTo(coord33),coord20);		
		assertCoord(coord21.moveTo(coordn12),coord21);
		assertCoord(coord22.moveTo(coord33),coord22);
		
	}
}
