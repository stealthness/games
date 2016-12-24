import static org.junit.Assert.*;

import org.junit.Before;
import groovy.mock.interceptor.*
import groovy.util.GroovyTestCase

class TestMinesweeper extends GroovyTestCase {
	
	final static ROW_LENGTH = 6
	final static COL_LENGTH = 4
	Minesweeper m, m1 , m2
	def countTiles
	Tile tEmpty, 
		tSelected,
		tGuess,
		tGuessMine,
		tMine,
		tExploded

	public void setUp() throws Exception {
		
		tEmpty = new Tile(false)
		tSelected = new Tile(false)
		tGuess = new Tile(false)
		tGuessMine= new Tile(true)
		tMine= new Tile(true)
		tExploded = new Tile(true)	
			
		m = new Minesweeper()
		m.emptypIntialise()
		
		m1 = new Minesweeper()
		m1.emptypIntialise()
		tMine.isHidden = false
		m1.grid[1][1] = tMine
		m1.hidden = false
		
		countTiles = 24
		
		def minesAt = [[1,2],[2,1],[1,5],[4,1],[4,6]]
		def ones = [[1,3],[1,5],[2,3],[2,5],[2,6],[3,4],[3,5],[3,6],[4,2][4,6]]
		
		/*
		 * 		2 M 1 . 1 M
		 * 		M 2 1 . 1 1
		 * 		2 2 . 1 1 1
		 *      M 1 . 1 M 1
		 * 
		 * 		5 mines
		 */
		m2  = new Minesweeper()
		m2.emptypIntialise()
		minesAt.each {pos->
			m2.grid[pos[0]-1][pos[1]-1] = tMine
		}

	}

	

	public void testEmptyCreate(){
		(1..COL_LENGTH).each{ row->
			(1..ROW_LENGTH).each {col->
				def pos = [row,col]
				assertEquals(STATUS.empty, m.getTile(pos).status)
			}		
		}
	}
	
	void testInvalidInputs(){
		
		def badpos = [[-1,-1],[6,4],[6,3],[5,4]]
		badpos.each {pos->
			assertFalse(m.addMove('x', pos))
		}
	}
	
	public void testSelect(){
		(1..COL_LENGTH).each{ row->
			(1..ROW_LENGTH).each {col->
				def pos = [row,col]
				assertTrue(m.addMove('x', pos))
				countTiles--
				assertEquals(STATUS.selected, m.getTile(pos).status)
				assertEquals(countTiles, m.numberOfTiles)
				
			}
		}
	}
	
	public void testGuess(){
		(1..COL_LENGTH).each{ row->
			(1..ROW_LENGTH).each {col->
				def pos = [row,col]
				assertTrue(m.addMove('?', pos))
				assertEquals(STATUS.guessed, m.getTile(pos).status)
				// count will always be 24 as guess tiles don't count
				assertEquals(countTiles, m.numberOfTiles)				
			}
		}
	}
	
	public void testChangeGuess(){
		def pos = [1,1]
		m.addMove('?', pos)
		assertEquals(STATUS.guessed, m.getTile(pos).status)
		assertEquals(countTiles, m.numberOfTiles)
		
		m.addMove('x', pos)	
		assertEquals(STATUS.selected, m.getTile(pos).status)
		assertEquals(--countTiles, m.numberOfTiles)
		
		// once selected tile cannot be unselected
		m.addMove('?', pos)
		assertEquals(STATUS.selected, m.getTile(pos).status)
		assertEquals(countTiles, m.numberOfTiles)		
	}
	
	public void testEmptyCreateM1(){
		(1..COL_LENGTH).each{ row->
			(1..ROW_LENGTH).each {col->
				def pos = [row,col]
				if(row == 2 && col ==2){
					assertEquals(STATUS.mined, m1.getTile(pos).status)
				}else{		
					assertEquals(STATUS.empty, m1.getTile(pos).status)
				}
			}
		}
	}
	
	public void testClearGuess(){
		def pos1 = [1,1]
		def pos2 = [2,2]
		
		//add a guess at 2,2 which is a mine
		assertTrue(m1.addMove('?', pos2))
		assertEquals(STATUS.guessedMine, m1.getTile(pos2).status)
		// clear a guess at 2,2
		assertTrue(m1.addMove('c', pos2))
		assertEquals(STATUS.mined, m1.getTile(pos2).status)
		
		
		// add a guess at 1,1 which is empty
		assertTrue(m1.addMove('?', pos1))
		assertEquals(STATUS.guessed, m1.getTile(pos1).status)
		// clear the guess at 1,1
		assertTrue(m1.addMove('c', pos1))
		assertEquals(STATUS.empty, m1.getTile(pos1).status)
		
		// add a select at 1,1 which is empty
		assertTrue(m1.addMove('x', pos1))
		assertEquals(STATUS.selected, m1.getTile(pos1).status)
		assertEquals(countTiles-1, m1.numberOfTiles)
		// attempt to clear the selection at 1,1
		assertFalse(m1.addMove('c', pos1))
		assertEquals(STATUS.selected, m1.getTile(pos1).status)
		assertEquals(countTiles-1, m1.numberOfTiles)
	
	}
	
	
	
	public void testChangeGuessM1(){
		
		// added guess at 2,2"
		def pos = [2,2]	
		assertTrue(m1.addMove('?', pos))
		
		//assertEquals(STATUS.guessedMine, m1.getTile(pos).status)
		assertEquals(countTiles, m1.numberOfTiles)
		
		// change guess to select at 2,2"
		m1.addMove('x', pos)
		assertEquals(STATUS.exploded, m1.getTile(pos).status)
		assertEquals(countTiles, m1.numberOfTiles)	
		
		// since game is over should not be able to select another tile
		pos = [1,1]
		m1.addMove('x', pos)
		assertEquals(STATUS.empty, m1.getTile(pos).status)
		assertEquals(countTiles, m1.numberOfTiles)

	}
	
	
	/// testing numbering of a tile based on the number mines near
	
	void testNumberGridPrint(){
		println "start testNumberGridPoint"
		// artificiall set 4,4 to 0, and 3,3 to 1 for test grid m1
		def pos3 = [3,3]
		def pos4 = [4,4]
		
		m1.getTile(pos3).minesNear = 1
		m1.getTile(pos4).minesNear = 0
		
		assertEquals(1,m1.getTile(pos3).minesNear)
		assertEquals(0,m1.getTile(pos4).minesNear)
		// currently tiles are empty
		assertEquals('.',m1.getTile(pos3).toString())
		assertEquals('.',m1.getTile(pos4).toString())

		// select the tiles
		m1.addMove('x', pos3)
		m1.addMove('x', pos4)

		println m1.printGrid()
		assertEquals('1',m1.getTile(pos3).toString())
		assertEquals('X',m1.getTile(pos4).toString())
		println "end testNumberGridPoint"
	}
	
	void testNumbers(){
		m1.setMinesNearValues()
		def ones = [[1,1],[1,2],[1,3],[2,1],[2,3],[3,1],[3,2],[3,3]]
		(1..COL_LENGTH).each{ row->
			(1..ROW_LENGTH).each {col->
				def pos = [row,col]
				if (pos in ones){
					assertEquals(1, m1.getTile(pos).minesNear)
				}else if(pos == [2,2]){
					assertEquals(STATUS.mined, m1.getTile(pos).status)
				}else{
					assertEquals(STATUS.empty, m1.getTile(pos).status)
				}
				
				// count will always be 24 as guess tiles don't count
				assertEquals(countTiles, m1.numberOfTiles)
			}
		}
		

	}
	
	void numberWithTwoMines(){
		
		// sett a mine at 4,4, this will make tile at 3,3 have a value of 2
		
		m1.getTile([4,4]).status = STATUS.mined
		m1.setMinesNearValues()
		assertEquals(2,m1.getTile([3,3]).minesNear)
	}
	

	
}
