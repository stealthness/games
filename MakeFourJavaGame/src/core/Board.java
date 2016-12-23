package core;

public class Board {
	
	public static final int SUPER_BOARD_NO_COLUMNS = 10;
	public static final int SUPER_BOARD_SIZE_OF_COLUMNS = 10;
	public static final int LARGE_BOARD_NO_COLUMNS = 7;
	public static final int LARGE_BOARD_SIZE_OF_COLUMNS = 7;
	public static final int DEFAULT_BOARD_NO_COLUMNS = 4;
	public static final int DEFAULT_BOARD_SIZE_OF_COLUMNS = 4;
	
	
	Column[] columns;
	
	public Board(){
		this(DEFAULT_BOARD_NO_COLUMNS,DEFAULT_BOARD_SIZE_OF_COLUMNS);
	}
	
	public Board(int noOfColumns, int maxSizeOfColumns){
		columns = new Column[noOfColumns];
		for ( int i = 0;i<noOfColumns;i++){
			columns[i] = new Column(maxSizeOfColumns);
		}
	}

	public int getSize() {
		return columns.length*columns[0].getMaxSize();
	}

	public int getNoColumns() {
		return columns.length;
	}

	public int getColumnSize(int i) {
		return columns[i].getCurrentSize();
	}

	public int getColumnMaxSize() {
		return columns[0].getMaxSize();
	}

	public void addTo(Player player, int i) {
		if (getColumnSize(i)<columns[0].getMaxSize()){
			columns[i].addPlayer(player);
		}		
	}

	public boolean isColFull(int i) {	
		return (getColumnSize(i)==this.getColumnMaxSize());
	}

	public Column getColumn(int i) {
		return columns[i];
	}

}
