import spock.lang.Specification;

class SpockTestTile extends Specification {

	// fields
	
	@Shared def tEmpty = new Tile(false)
	@Shared def tSelected = new Tile(false)
	@Shared def tGuess = new Tile(false)
	@Shared def tGuessMine= new Tile(true)
	@Shared def tMine= new Tile(true)
	@Shared def tExploded = new Tile(true)
	@Shared def tiles= [tEmpty, tSelected, tGuess, tGuessMine, tMine, tExploded]
	@Shared def statuses = [STATUS.empty, STATUS.selected, STATUS.guessed, STATUS.guessedMine,STATUS.mined, STATUS.exploded]


	// fixture methods
	def setup(){	
	
		tiles.eachWithIndex {tile, i->
			tile.status = statuses[i]
		}
	}
	
	// feature methods
	
	def "assert initial setup correct"(){	
		expect:
		3!=5
		tiles!= 8
		tiles.eachWithIndex {tile, i->
			tile.status == statuses[i]
		}
	}
	
	def "testing the correst staus change when a tile is selected"(){
		when:
			def bool = tile.select()
		then:
			bool == returned
			tile.status == result
		where:	
			tile 		|returned 	| result
			tSelected	|false		| STATUS.selected
			tGuess		|false		| STATUS.selected
			tEmpty		|true		| STATUS.selected
			tGuessMine	|false		| STATUS.exploded
			tExploded	|false		| STATUS.exploded
			tMine		|false		| STATUS.exploded	
	}
	
	def "testing the correct status changed when tile is guessed"(){
		when:
			def bool = tile.guess()
		then:
			bool == returned
			tile.status == result
		where:
			tile 		|returned 	| result
			tSelected	|false		| STATUS.guessed
			tGuess		|false		| STATUS.guessed
			tEmpty		|true		| STATUS.guessed
			tGuessMine	|false		| STATUS.guessedMine
			tExploded	|false		| STATUS.exploded
			tMine		|true		| STATUS.guessedMine
	}
	
	// helper methods

}
