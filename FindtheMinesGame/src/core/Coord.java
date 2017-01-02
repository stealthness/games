package core;

public class Coord implements Comparable{
	int x;
	int y;
	
	static int minX = 0;
	static int maxX = 2;
	static int minY = 0;
	static int maxY = 2;
	
	
	private static boolean validCoord(Coord coord){
		return validCoord(coord.getX(), coord.getY());
	}
	
	private static boolean validCoord(int x, int y) {
		return (minX<=x && minY<=y && maxX>=x && maxY>=y);
		
	}	
	
	public Coord(int x, int y){
		if (validCoord(x,y)){
			this.x = x;
			this.y = y;
		}else{
			this.x = minX-1;
			this.y = minY-1;
		}
	}
	


	public Coord(Coord coord){
		this(coord.getX(),coord.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static int getMinX() {
		return minX;
	}

	public static void setMinX(int minX) {
		Coord.minX = minX;
	}

	public static int getMaxX() {
		return maxX;
	}

	public static void setMaxX(int maxX) {
		Coord.maxX = maxX;
	}

	public static int getMinY() {
		return minY;
	}

	public static void setMinY(int minY) {
		Coord.minY = minY;
	}

	public static int getMaxY() {
		return maxY;
	}

	public static void setMaxY(int maxY) {
		Coord.maxY = maxY;
	}

	public static boolean isValid(Coord coord) {
		return validCoord(coord);
	}

	public Coord moveUp() {
		if (validCoord(this.x,this.y+1)){
			this.y++;
		}
		return this;		
	}
	
	public Coord moveDown() {
		if (validCoord(this.x,this.y-1)){
			this.y--;
		}
		return this;		
	}

	public Coord moveLeft() {
		if (validCoord(this.x-1,this.y)){
			this.x--;
		}
		return this;
	}

	public Coord moveRight() {
		if (validCoord(this.x+1,this.y)){
			this.x++;
		}
		return this;
	}

	public Coord moveTo(Coord coord) {
		if(validCoord(coord)){
			x = coord.getX();
			y = coord.getY();
		}
		return this;
	}	
	
	@Override
	public String toString(){
		return "("+x+","+y+")";
	}

	@Override
	public int compareTo(Object o) {
		if (o.getClass() != Coord.class){
			return 0;
		}else if (this.getX() == ((Coord)o).getX() && this.getY() == ((Coord)o).getY()){
			return 1;
		}else{
			return 0;
		}
	}
}
