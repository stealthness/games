import groovy.util.GroovyTestCase;

class TestTile extends GroovyTestCase {
	
	Tile t0,t1,tSelected,tMine, tEmpty, tGuess, tGuessMine, tExploded
	def statuses
	def tiles

	protected void setUp() {
			
		tEmpty = new Tile(false)		
		tSelected = new Tile(false)
		tGuess = new Tile(false)
		tGuessMine= new Tile(true)
		tMine= new Tile(true)
		tExploded = new Tile(true)
			
		tiles = [tEmpty, tSelected, tGuess, tGuessMine, tMine, tExploded]		
		statuses = [STATUS.empty, STATUS.selected, STATUS.guessed, STATUS.guessedMine,STATUS.mined, STATUS.exploded]	
		tiles.eachWithIndex {tile, i->
			tile.status = statuses[i]
		}
		

	}
	
	
	public void testSetupTileTesting(){
		// assert initial setup correct
		tiles.eachWithIndex {tile, i->
			assertEquals(tile.status , statuses[i])
		}
	}
		
	public void testCreateTile(){
		// Testing creating
		t0 = new Tile(false)
		assertEquals(t0.status, STATUS.empty)
		t1 = new Tile(true)
		assertEquals(t1.status, STATUS.mined)
	}

	public void testSelectSelectedResults(){	
		// check when tile has some other status
		assertFalse(tSelected.select())
		assertEquals(tSelected.status, STATUS.selected)
		assertFalse(tGuess.select())
		assertEquals(tGuess.status, STATUS.selected)
		assertTrue(tEmpty.select())
		assertEquals(tEmpty.status, STATUS.selected)

	}
	
	public void testSelectExplodedResults(){
		assertFalse(tGuessMine.select())
		assertEquals(STATUS.exploded, tGuessMine.status)
		assertFalse(tExploded.select())
		assertEquals(STATUS.exploded, tExploded.status)
		assertFalse(tMine.select())
		assertEquals(STATUS.exploded, tMine.status )		
	}
	
	/**
	 *
	 */
	public void testguessOnEmpty(){
		// check when tile has some other status
		assertFalse(tSelected.guess())
		assertEquals(STATUS.guessed, tSelected.status)
		assertFalse(tGuess.guess())
		assertEquals(STATUS.guessed, tGuess.status)
		assertTrue(tEmpty.guess())
		assertEquals(STATUS.guessed, tEmpty.status)

	}
	
	public void testGuessonMine(){
		assertFalse(tGuessMine.guess())
		assertEquals(STATUS.guessedMine, tGuessMine.status)
		assertFalse(tExploded.guess())
		assertEquals(STATUS.exploded, tExploded.status)
		assertTrue(tMine.guess())
		assertEquals(STATUS.guessedMine, tMine.status )
	}
	
	public void testToString1(){
		// [tEmpty, tSelected, tGuess, tGuessMine, tMine, tExploded]
		def results = [".","X","?","?",".","*"]	
		
		tiles.eachWithIndex {tile, i->
			assertEquals(results[i],tile.toString())
		}
		tMine.isHidden = false
		assertEquals('M', tMine.toString())		
	}
	
	public void testToString2(){
		// [tEmpty, tSelected, tGuess, tGuessMine, tMine, tExploded]
		def results = [".","1","?","?",".","*"]	
		
		tiles.eachWithIndex {tile, i->
			tile.minesNear = 1
			assertEquals(1,tile.minesNear)
			assertEquals(results[i],tile.toString())
		}
		tMine.isHidden = false
		assertEquals("M", tMine.toString())
		
	}
}
