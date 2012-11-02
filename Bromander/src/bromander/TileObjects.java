package bromander;

import java.util.ArrayList;

public class TileObjects {
	private static ArrayList<TileObject> tileObjects = new ArrayList<TileObject>();
	
	static TileObject ROCK;
	
	 public static void initialize()
	  {
	    tileObjects.add(ROCK = 
	    	new TileObject(new Sprite("/bromander/images/Bromander Pictures/Tiles/rock 1.png")
	    				   ,TileType.Solid)
	    				); 
	  }

	public static int getCount() {
		return tileObjects.size();
	}

	public static TileObject getTileObject(int i) {
		return tileObjects.get(i);
	}
}
