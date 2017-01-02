import static org.junit.Assert.*;
import org.junit.*;

import core.Board;
import core.Coord;
import core.Mark;
import core.Tile;


public class TestBoard {
	
	Board boardEmpty,boardAllPlayerA;
	Tile tile00e,tile00x,tile12e,tile12x,tile22e,tile22o,tile22x;
	Coord coord00,coord12,coord22;

	@Before
	public void setUp() throws Exception {
		boardEmpty = new Board();
		coord00 = new Coord(0,0);
		coord12 = new Coord(1,2);
		coord22 = new Coord(2,2);
		
		tile00e = new Tile(coord00);
		tile00x = new Tile(Mark.PLAYER_A,'x',coord00);
		tile12e = new Tile(coord12);
		tile12x = new Tile(Mark.PLAYER_A,'x',coord12);
		tile22e = new Tile(coord22);
		tile22o = new Tile(Mark.PLAYER_B,'o',coord22);
		tile22x = new Tile(Mark.PLAYER_A,'x',coord22);
		
		boardAllPlayerA = initAllPlayer();
		
	}

	private Board initAllPlayer() {
		Board board = new Board();
		for (int x =0 ;x <= Coord.getMaxX();x++){
			for (int y = 0 ;y <= Coord.getMaxY();y++){
				board.setPlayerAt(Mark.PLAYER_A, new Coord(x,y));
			}
		}
		return board;
	}

	@Test
	public void testCreate(){
		assertEquals(9,boardEmpty.size());
	}
	
	@Test
	public void testFindTileAt(){
		Tile tile = boardEmpty.findTileAt(coord00);
  		assertEqualTiles(tile00e,tile);
		assertNotEqualTiles(tile00x,tile);
		tile = boardEmpty.findTileAt(coord12);
		assertEqualTiles(tile12e,tile);
		assertNotEqualTiles(tile12x,tile);
	}
	
	@Test 
	public void testSetPlayerAt(){
		setPlayerAtTest(boardEmpty,Mark.PLAYER_A,coord00,tile00e,tile00x);
		setPlayerAtTest(boardEmpty,Mark.PLAYER_A,coord12,tile12e,tile12x);
		setPlayerAtTest(boardEmpty,Mark.PLAYER_B,coord22,tile22e,tile22o);
		
		setPlayerAtTest(boardAllPlayerA,Mark.EMPTY,coord00,tile00x,tile00e);
		setPlayerAtTest(boardAllPlayerA,Mark.PLAYER_B,coord22,tile22x,tile22o);
		setPlayerAtTest(boardAllPlayerA,Mark.PLAYER_A,coord22,tile22o,tile22x);
	}
	
	private void setPlayerAtTest(Board board, Mark mark, Coord coord,Tile preTile, Tile expTile){
		Tile tile = board.findTileAt(coord);
		assertEqualTiles(preTile,tile);
		board.setPlayerAt(mark,coord);
		assertEqualTiles(expTile,tile);
	}
	
	private void assertEqualTiles(Tile tile, Tile expectedTile){
		assertEquals(expectedTile.getCoord().getX(),tile.getCoord().getX());
		assertEquals(expectedTile.getCoord().getY(),tile.getCoord().getY());
		assertEquals(expectedTile.getMark(),tile.getMark());
	}
	
	private void assertNotEqualTiles(Tile tile, Tile expectedTile){
		assertFalse(			
				expectedTile.getCoord().getX()==tile.getCoord().getX() &&
				expectedTile.getCoord().getY()==tile.getCoord().getY() &&
				expectedTile.getMark()==tile.getMark() );
	}

}
