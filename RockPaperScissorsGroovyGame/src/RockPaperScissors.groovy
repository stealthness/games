def enum HANDS  {
	ROCK("Rock",0),PAPER("Paper",1),SCISSORS("Scissors",2)
	
	String handsString
	Integer index

    HANDS(String handsString, Integer index){
		this.handsString = handsString
		this.index = index
	}
	
	String toString(){
		return this.handsString
	}
}

class RockPaperScissors {

    public static final String PROP_FILENAME = 'RockPaperScissorsGame/Res/R.properties'
    Properties r
    Player player1
	Player player2
    boolean continuePlaying = true

	static void  main(String[] args){
        def rps = new RockPaperScissors()
        rps.startGame()
	}


    def startGame(){
        r = new Properties()
        (new File(PROP_FILENAME)).withInputStream {r.load(it)}
        createPlayers()
        while (continuePlaying){

            continuePlaying = false
        }
        println(r.goodbye)
    }


    def createPlayers(){
        player1 = new Player("Ann",false)
        player2 = new Player("Bob",false)
	}

	def getHand(){

    }

}
