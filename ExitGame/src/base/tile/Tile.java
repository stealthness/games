package base.tile;

abstract public class Tile {
	
	public static final char MARK = '#';
	boolean isHidden = true;
	
	abstract public boolean isSolid();
	
	public abstract char getMark();
	
	public void reveal(){
		isHidden = false;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

}
