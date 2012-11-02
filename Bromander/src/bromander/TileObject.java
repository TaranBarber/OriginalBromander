package bromander;

import java.awt.Graphics;

public class TileObject {

	//Fields
	TileType tileType;
	Sprite sprite;
	  
	public TileObject(Sprite sprite, TileType tileType) {
		this.sprite = sprite;
		this.tileType = tileType;
	}

	public String getImagePath() {
		// TODO Auto-generated method stub
		return this.sprite.getImagePath();
	}

	public void draw(Graphics graphics, int x, int y) {
		// TODO Auto-generated method stub
		this.sprite.draw(graphics,x,y);
	}
	  
	
	  
	  
	 
}
