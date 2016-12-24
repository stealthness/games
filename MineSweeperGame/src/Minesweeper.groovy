/**
 * code by stealthness 2012
 *   class Minesweeper 
 **/


class Minesweeper {
	final static DEFAULT_ROW_LENGTH = 6
	final static DEFAULT_COL_LENGTH = 4
	final static TOLERANCE = 15
	def colLength
	def rowLength
	def moves
	def grid
	def numberOfTiles
	def mines = 0
	def hidden = true
	def gameOver = false
	Random rnd 
	
	def Minesweeper(){		
		this(DEFAULT_ROW_LENGTH,DEFAULT_COL_LENGTH)
	}
	
	
	def Minesweeper(rowLength,colLength){
		super()
		this.colLength = colLength
		this.rowLength = rowLength
		grid  = new Object[colLength][ rowLength ]
		numberOfTiles = colLength*rowLength
		moves = []
	}	
	
	/**
	 * creates a random mine field
	 */
	def intialise(){
		rnd = new Random()
		// acceptable grids will have 10-20% of tiles as mines
		while (mines > numberOfTiles*0.2  || mines<numberOfTiles *0.1 ){
			mines = 0			
			(0..(colLength-1)).each { col->
				(0..(rowLength-1)).each {row ->
				 	// each tiles will have a tolerance(14%) chance of being a mine
					if (rnd.nextInt(99)>TOLERANCE){
						grid[col][row]= new Tile(false)						
					}else{
						grid[col][row]= new Tile(true)
						mines++
					}	
				}
			}
		}
	}
	
	/**
	 * sets the value of Tile based on the number of mines near
	 * @return
	 */
	def setMinesNearValues(){
		(1..(rowLength)).each { col->
			(1..(colLength)).each {row ->
				def pos = [row,col]
				Tile t = this.getTile(pos)
				if (t.status == STATUS.mined){
					def adds = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]]
					adds.each{add->
						def tempPos = [add[0]+pos[0],add[1]+pos[1]]
						if ( isPosValid(tempPos)&& this.getTile(tempPos).status != STATUS.mined){
								this.getTile(tempPos).minesNear = ((this.getTile(tempPos).minesNear)?:0)+1										
						}							
					}	
				}else if (t.minesNear == null){
					t.minesNear = 0
				}
			}
		}
	}
	
	def isPosValid(pos){
		return (pos[0] in (1..colLength) && (pos[1]) in (1..rowLength))
	}
	
	/**
	 * creates an empty mine field
	 */
	def emptypIntialise(){
		mines = 0
		(0..(colLength-1)).each {row ->
			(0..(rowLength-1)).each { col->
				grid[row][col]= new Tile(false)
			}
		}
	}	

	/**
	 * returns the tile at a given pos
	 * @param pos
	 * @return tile at pos
	 */
	def getTile(pos){
		return grid[pos[0]-1][pos[1]-1]
	}
	
	
	String toString(){
		return printGrid()
	}
	
	/**
	 * creates a simple string representation of the game
	 * @return 
	 */
	def printGrid(){
		def rowCount = 0
		StringBuilder sb = new StringBuilder("   ")
		(1..(rowLength)).each{it-> sb.append( "$it${(it>9)?" ":"  "}") }
		sb.append("\n")
		grid.each { row->
			sb.append("${++rowCount}${(rowCount>9)?" ":"  "}")
			row.each {tile->				
				sb.append((hidden)?tile.toString() +"  ":(tile.status == STATUS.mined)?"M  ":tile.toString() +"  ")			
			}
			sb.append("\n")
		}		
		sb.append("Number mine : $mines  Number Tiles left to select : ${numberOfTiles-mines}")
		return sb.toString()
	}
	
	def checkWin(){
		def countMines = 0;
		grid.each {row->
			row.each {tile->
				if (tile.status == STATUS.guessedMine){
					countMines++
				}		
			}
		}
		gameOver = countMines == mines
		return gameOver
	}	
	
	/**
	 * 
	 * @param pos
	 * @return list of all valid positions
	 */
	def getNearTiles(pos){
		def adds = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]]
		adds.each { nearPos->
			def tempPos = [nearPos[0]+pos[0], nearPos[1]+pos[1]]
			if (isPosValid(tempPos)){
				moves.add(tempPos)
			}		
		}
	}
	
	
	def selectTile(pos){
			def tile = grid[pos[0]-1][pos[1]-1]
		switch(tile.status){
			
			case STATUS.mined:
			case STATUS.guessedMine:
				tile.status = STATUS.exploded
				gameOver = true
				return false
				break
			case STATUS.guessed:
			case STATUS.empty:
				tile.status = STATUS.selected
				(tile.minesNear==0)?moves.add(getNearTiles(pos)):		
				numberOfTiles--
				return true
			case STATUS.selected:
				return false
				break
		}
	}
	
	def guessTile(pos){
		def tile = grid[pos[0]-1][pos[1]-1]
	switch(tile.status){
		case STATUS.mined:
			tile.status = STATUS.guessedMine
			return true
			break
		case STATUS.guessedMine:
		case STATUS.selected:
			return false
			break
		case STATUS.empty:
			tile.status = STATUS.guessed
			return true
			break
		}
	}
	
	def clearTile(pos){
		def tile = grid[pos[0]-1][pos[1]-1]
	switch(tile.status){
		case STATUS.guessedMine:
			tile.status = STATUS.mined
			return true
			break			
		case STATUS.guessed:
			tile.status = STATUS.empty
			return true
			break
		default:
			return false
		}
	}
	
	def makeMoves(pos){
		def firstMoveReturn = false
		if (moves!=[] ){
			firstMoveReturn = selectTile(moves.remove(0))
		}
/**/	while (moves !=[]){
			selectTile(moves.pop())
		}
		return firstMoveReturn
	}
	
	def addMove(choice,pos){
		(!isPosValid(pos))		
		if ( !(pos[1] in (1..(rowLength))) || !(pos[0] in (1..(colLength))))	{
			println "faile inputs"
			return false
		}else if (gameOver){
			// game is over
			return false
		}else{
			switch(choice){
				case 'x':
					moves<<pos
					return makeMoves(pos)
				break
				case '?':
					return guessTile(pos)
				break
				case 'c':
					return clearTile(pos)
				break
				case 't':
					return checkWin()
				break
			}
		}
	}

}

