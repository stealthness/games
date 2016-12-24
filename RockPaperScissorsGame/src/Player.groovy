class Player {

	String name
	boolean isHuman
	def scores = Integer[3][3]
	
	Player(name,isHuman){
		this.name = name
		this.isHuman = isHuman
		this.setZero()
	}
	
	private void setZero(){
		scores.each{row->
			row.each {score->
				score = 0
			}
		}
	}

}
