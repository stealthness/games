package core;

import java.util.ArrayList;

public class Board {
	
	private static final int MAX_X = 2;
	private static final int MAX_Y = 2;
	ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public Board(){
		super();
		for (int x = 0 ; x<=MAX_X;x++){
			for (int y = 0 ; y<=MAX_Y;y++){
				tiles.add(new Tile(new Coord(x,y)));
			}
		}
	}
	
	public Tile findTileAt(Coord coord){
		return tiles.stream()
				.filter(tile ->tile.getCoord().equals(coord))
				.findFirst()
				.orElse(null);


/*		for (Tile tile :tiles){
			if(tile.getCoord().getX()==coord.getX() && tile.getCoord().getY()==coord.getY()){
				return tile;
			}
		}
		return null;*/
	}

	public int size() {
		return tiles.size();
	}

	public void setPlayerAt(Mark mark, Coord coord) {
		if (Coord.isValid(coord)){
			Tile tile = findTileAt(coord);
			if (tile!=null){
				tile.setMark(mark);
			}
		}
		
	}

}
