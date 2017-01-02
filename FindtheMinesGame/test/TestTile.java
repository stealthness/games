import core.Coord;
import core.Mark;
import core.Tile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestTile {

    private static final String COORD_PATH = "test//testFileCoords.txt";
    private static final String  TILE_PATH = "test//testFileTiles.txt";
    final char e = Tile.emptyMarkChar;
	final char x = Tile.playerAchar ;
	final char o = Tile.playerBchar;
	
	Tile tile00x,tile11x,tile12x, tile20x;
	Tile tile00e,tile11o,tile12e, tile20o;
	Tile tilen1n1x, tile22x, tile33x;
	
	Coord coord00,coord11,coord22,coord33,coordn12;
	Coord coord01,coord02,coord10,coord12,coord20,coord21;

	static Map<String, Coord> coordMap= new HashMap<>();
    static Map<String, Tile> tileMap= new HashMap<>();


	@BeforeAll
	public static void setUp() {
        readCoords();
        readTiles();
    }


    @BeforeEach
    public void SetUp(){

        coordMap.forEach((key,coord) -> System.out.println(key+" is "+coord.toString()));

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

        tile00e = new Tile(Mark.EMPTY,e,coord00);
        tile00x = new Tile(Mark.PLAYER_A,x,coord00);


        tile11x = new Tile(Mark.PLAYER_A,x,new Coord(1,1));
        tile11o = new Tile(Mark.PLAYER_B,o,new Coord(1,1));
        tile12x = new Tile(Mark.PLAYER_A,x,new Coord(1,2));
        tile12e = new Tile(Mark.EMPTY,e,new Coord(1,2));

        tile20x = new Tile(Mark.PLAYER_A,x,new Coord(2,0));
        tile20o = new Tile(Mark.PLAYER_B,o,new Coord(2,0));
        tile22x = new Tile(Mark.PLAYER_A,x,new Coord(2,2));

        // fail tiles
        tilen1n1x = new Tile(Mark.PLAYER_A,x,new Coord(-1,-1));
        tile33x = new Tile(Mark.PLAYER_A,x,new Coord(3,3));
    }
	
	

	private void assertTile(Tile tile, Mark expectedMark, char expectedChar, int[] expectedCoord){
		if (tile.getCoord()!=null){
			assertEquals(tile.getCoord().getX(),expectedCoord[0]);
			assertEquals(tile.getCoord().getY(),expectedCoord[1]);
		}else{
			assertNull(tile.getCoord());
		}
		assertEquals(tile.getMark(),expectedMark);
		assertEquals(tile.getSimpleCharDisplay(), expectedChar);
	}
	
	private void assertTile(Tile tile, Mark expectedMark, char expectedChar,Coord expectedCoord){
		assertTile(tile, expectedMark, expectedChar,new int[]{expectedCoord.getX(), expectedCoord.getY()});
	}
	

	@Test
	public void testValidCoord(){
        tileMap.forEach((k,t) -> System.out.println(k + " "+ t.toString()));


    	assertTile(tileMap.get("tile00x"),Mark.PLAYER_A,x,coordMap.get("coord00"));
		assertTile(tileMap.get("tile11x"),Mark.PLAYER_A,x,coordMap.get("coord11"));
		assertTile(tileMap.get("tile22x"),Mark.PLAYER_A,x,new int[]{2,2});
		
		// to do...
		//assertTile(tilen1n1x,Mark.EMPTY,e,null);
		//assertTile(tile33x,Mark.EMPTY,e,null);
	}

	@Test
	public void testCreate() {
		assertTile(tile00x,Mark.PLAYER_A,x,coord00);
        assertTile(tileMap.get("tile00x"),Mark.PLAYER_A,x,coordMap.get("coord00"));
//		assertTile(tile11x,Mark.PLAYER_A,x,new int[]{1,1});
//		assertTile(tile12x,Mark.PLAYER_A,x,new int[]{1,2});
//		assertTile(tile20x,Mark.PLAYER_A,x,new int[]{2,0});
//
//		assertTile(tile00e,Mark.EMPTY,e,new int[]{0,0});
//		assertTile(tile11o,Mark.PLAYER_B,o,new int[]{1,1});
//		assertTile(tile12e,Mark.EMPTY,e,new int[]{1,2});
//		assertTile(tile20o,Mark.PLAYER_B,o,new int[]{2,0});
		
	}

	@Test
	public void testChangeTile(){
	    Tile tile = tileMap.get("tile00e");
		tile.setMark(Mark.PLAYER_A);
		assertTileEquals(tileMap.get("tile00x"),tile);
//		tile11o.setMark(Mark.PLAYER_A);
//		assertTile(tile11x,Mark.PLAYER_A,x,coord11);
//
//		tile12e.setCoord(new Coord(0,0));
//		assertTile(tile12e,Mark.EMPTY,e,coord00);
//
//		tile20o.setCoord(new Coord(0,0));
//		assertTile(tile20o,Mark.PLAYER_B,o,coord00);
//		tile20o.setCoord(new Coord(0,0));
//
//		assertTile(tile20o,Mark.PLAYER_B,o,coord00);
	}
    @Disabled
	@Test
	public void testEquality(){
		
//		assertTileEquals(tile00e,tile00e);
//		assertTileEquals(tile20o,tile20o);
//
//		assertTileNotEquals(tile11o,tile11x);
//		assertTileNotEquals(tile00x,tile11x);
//
//		Tile newTile00e = new Tile(Mark.EMPTY,e,coord00);
//		Tile newTile20o = new Tile(Mark.PLAYER_B,o,coord20);
//		Tile newTile11x = new Tile(Mark.PLAYER_A,x,coord11);
//		Tile newTile11o = new Tile(Mark.PLAYER_B,o,coord11);
//		Tile newTile00x = new Tile(Mark.PLAYER_A,x,coord00);
//
//		assertTileEquals(tile00e,newTile00e);
//		assertTileEquals(tile20o,newTile20o);
//
//		assertTileEquals(tile11o,newTile11o);
//		assertTileEquals(tile00x,newTile00x);
//
//		assertTileNotEquals(tile11o,newTile11x);
//		assertTileNotEquals(tile00x,newTile11x);
//
//		tile00e.setMark(Mark.PLAYER_A);
//		assertTile(tile00x,Mark.PLAYER_A,x,new int[]{0,0});
//		tile11o.setMark(Mark.PLAYER_A);
//		assertTile(tile11x,Mark.PLAYER_A,x,new int[]{1,1});
			
	}

    // private methods


    private void assertTileEquals(Tile tile, Tile expectedTile){
		assertTrue(tile.equals(expectedTile));
	}
	
	private void assertTileNotEquals(Tile tile, Tile expectedTile){
		assertFalse(tile.equals(expectedTile));
	}


    private static void readTiles() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(TILE_PATH)))) {
            String line = br.readLine();
            while (line != null){
                String[] strArray = line.split(",");
                String key = strArray[0];
                Coord coord = coordMap.get(strArray[1]);
                if (strArray.length == 2){
                    tileMap.put(key, new Tile(coord));
                }else{
                    char letter =  strArray[3].charAt(0);
                    Mark mark = (strArray[2].equals("Mark.PLAYER_A"))?Mark.PLAYER_A:Mark.PLAYER_B;

                    tileMap.put(key ,new Tile(mark, letter, coord) );
                }
                line = br.readLine();
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void readCoords() {
        try (BufferedReader br = new BufferedReader(new FileReader(new File(COORD_PATH)))) {
            String line = br.readLine();
            while (line != null){

                String[] strArray = line.split(",");
                coordMap.put(strArray[0],new Coord(Integer.parseInt(strArray[1]),Integer.parseInt(strArray[2])));
                line = br.readLine();
            }
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
