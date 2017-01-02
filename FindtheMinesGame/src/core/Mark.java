package core;

public enum Mark {
	PLAYER_A ("player A"),PLAYER_B("player B"),EMPTY("empty");
	
    // Member to hold the name
    private String string;

    // constructor to set the string
    private Mark(String name){
    	string = name;
    }

    // the toString just returns the given name
    public String toString() {
    	return string;
    }

	public void setName(String name) {
		string = name;
		
	}
}
