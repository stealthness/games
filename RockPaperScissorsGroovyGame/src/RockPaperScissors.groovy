def enum HANDS  {
	ROCK("Rock",0),PAPER("Paper",1),SCISSORS("Scisors",2)
	
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
	
	

}
