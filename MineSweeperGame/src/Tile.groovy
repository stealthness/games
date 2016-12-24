/**
 * code by stealthness 2012
 */

def  enum STATUS {
	
	selected("X"), guessed("?"),exploded("*"),empty("."), mined("M"), guessedMine("?")
	
	String charTile
	
	STATUS(String charTile){
		this.charTile = charTile
	}
	
	String toString(){
		return this.charTile
	}
	
}

/**
 * Store the information about a Tile for the game Minesweeper
 * @author stealthness
 */
class Tile {
	
	/**
	 * stores the status of the tile
	 */
	STATUS status
	/**
	 *  number of Mines adjacent to this tile (null if unknown)
	 */
	def minesNear
	/**
	 * if tile is hidden mines will return as empty until selected
	 */
	def isHidden
	
	
	def Tile(isMined){
		// not known when created
		minesNear = null
		isHidden = true
		status = (isMined)?STATUS.mined:STATUS.empty
	}
	
	def select(){
		switch(status){
			case STATUS.mined:
			case STATUS.exploded:
			case STATUS.guessedMine:
				status = STATUS.exploded
				return false
				
			case STATUS.empty:
				status = STATUS.selected
				return true
				
			case STATUS.selected:
			case STATUS.guessed:
				status = STATUS.selected
				return false			
		}

	}
	
	def guess(){
		// return true if new tile selected, false if previously selected
		switch(status){
			case STATUS.guessed:
			case STATUS.guessedMine:
			case STATUS.exploded:
				return false
				
			case STATUS.selected:
				status = STATUS.guessed
				return false
				
			case STATUS.mined:
				status = STATUS.guessedMine
				return true
				
			default:
				status = STATUS.guessed
				return true
		}
	}
	
	String toString(){
		switch(status){
			case STATUS.selected:
				return minesNear?:status.toString()	
							
			case STATUS.mined:
				return isHidden?STATUS.empty.toString():STATUS.mined.toString()	
							
			default:
				return status.toString()
		}
	}

}