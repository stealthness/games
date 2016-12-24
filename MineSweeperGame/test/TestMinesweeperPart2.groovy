import static org.junit.Assert.*;
import TestMinesweeper

import org.junit.Before;


class TestMinesweeperPart2 extends GroovyTestCase {
	final static ROW_LENGTH = 6
	final static COL_LENGTH = 4
	Minesweeper  m2
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
			
		countTiles = 24
		//
		def minesAt = [[1,2],[2,1],[1,5],[4,1],[4,6]]
		
		
		/*
		 * 		2 M 1 1 M 1
		 * 		M 2 1 1 1 1
		 * 		2 2 . . 1 1
		 *      M 1 . . 1 M
		 * 
		 * 		5 mines
		 */
		m2  = new Minesweeper()
		
		m2.emptypIntialise()
		minesAt.each {pos->
			m2.grid[pos[0]-1][pos[1]-1] = tMine
		}
		// set mines value
		m2.mines = 5

	}
	
	
	void testEdges(){
		
		println m2.printGrid()
		
		m2.setMinesNearValues()
		println "Test Edges numbering"
		
		def ones = [[1,3],[1,4],[1,6],[2,3],[2,4],[2,5],[2,6],[3,5],[3,6],[4,2],[4,5]]
		def twos = [[1,1],[2,2],[3,1],[3,2]]
		def zeros = [[3,3],[3,4],[4,3],[4,4]]
		def mines = [[1,2],[1,5],[2,1],[4,1],[4,6]]
		
		ones.each{pos->
			assertEquals(1,m2.getTile(pos).minesNear)
			m2.addMove('x', pos)
		}
		println m2.printGrid()
		println "twos ;"
		twos.each{pos->
			
			assertEquals(2,m2.getTile(pos).minesNear)
			m2.addMove('x', pos)
		}
		println m2.printGrid()
		println "zeros ;"
		zeros.each{pos->
			
			assertEquals(0, m2.getTile(pos).minesNear)
			m2.addMove('x', pos)
		}
		println m2.printGrid()
		println "mines ;"
		mines.each{pos->
			
			assertNull( m2.getTile(pos).minesNear)
			m2.addMove('?', pos)
		}		
		println m2.printGrid()
		
		// test victory test
		m2.addMove('t', [1,1])
		assertEquals(5,m2.mines)
		assertTrue(m2.gameOver)
	}
	
	void testSelectEmptyTile(){
		m2.setMinesNearValues()
		// in this test selecting an points [3,3] [3,4] [4,3] [4,4] will 
		//select any of the points around it
		def results = ['2','1','1','2','X','1','X','X']
		def nearPos = [[2,2],[2,3],[2,4],[3,2],[3,4],[4,2],[4,3],[4,4]]
		m2.addMove('x', [3,3])
		nearPos.eachWithIndex {pos,i->
			assertEquals(results[i].toString(),m2.getTile(pos).toString())
		}
		println m2	
	}
	
	
	void testPop(){
		def list1 = [[3,3], [3,4], [4,3], [4,4]]
		def list2 = [[3,3], [3,4], [4,3]]
		def list3 = [4,4]
		m2.moves = list1
	
		assertEquals(list3,m2.moves.pop())
		assertEquals(list2, m2.moves)
		
		while (m2.moves != []){
			m2.addMove('x', m2.moves.pop())
		}
		println m2
	}
	
	void testIsPosValid(){
		def truePos = [[1,3],[1,4],[1,6],[2,3],[2,4],[2,5],[2,6],[3,5],[3,6],[4,2],[4,5],[1,1],[2,2],[3,1],[3,2]]
		truePos.each{pos->
			assertTrue(m2.isPosValid(pos))
		}
		def falsePos = [[0,3],[1,7],[-1,6],[-2,-3],[2,-4],[2,0],[5,6],[3,7]]
		falsePos.each{pos->
			assertFalse(m2.isPosValid(pos))
		}
		
	}
}
