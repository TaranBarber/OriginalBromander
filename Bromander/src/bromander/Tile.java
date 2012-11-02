package bromander;

import java.awt.Graphics;

public class Tile
{
  //Fields
  TileType tileType;
  Sprite sprite;
  TileObject tileObject;
  
  //Constructor
  public Tile(Sprite sprite, TileType tileType)
  {
    this.sprite = sprite;
    this.tileType = tileType;
  }
  
  public Tile(TileType tt) 
  {// TODO Auto-generated constructor stub
	this.tileType = tt;
	if (TileType.parse("Grass") == tt)
	{
		this.sprite = new Sprite("/bromander/images/Bromander Pictures/Tiles/grass 1.png");
	}
	if (TileType.parse("Water") == tt)
	{
		this.sprite = new Sprite("/bromander/images/Bromander Pictures/Tiles/water 1.png");
	}
	if (TileType.parse("Sand") == tt)
	{
		this.sprite = new Sprite("/bromander/images/Bromander Pictures/Tiles/sand 1.png");
	}
	if (TileType.parse("Rock") == tt)
	{
		this.sprite = new Sprite("/bromander/images/Bromander Pictures/Tiles/rock 1.png");
	}
	
  }
  
  public Tile(Sprite s) 
  {// TODO Auto-generated constructor stub
	  this.sprite = sprite;

	
  }

//Draws the tile at the specified position
  public void draw(Graphics graphics, int x, int y)
  {
    this.sprite.draw(graphics,x,y);
    if (tileObject != null)
    {
    this.tileObject.draw(graphics,x,y);
    }
  }
  
  public String getImagePath()
  {
    return this.sprite.getImagePath();
  }
  //Gets the TileType of the tile.
  public TileType getTileType()
  {
    return tileType;
  }
  
  public void setTileType(TileType t){
	  TileType.parse("Grass");
	  
  }

public Sprite getSprite() {
	// TODO Auto-generated method stub
	return this.sprite;
}

public void setTileObject(TileObject tileObject) {
	// TODO Auto-generated method stub
	this.tileObject = tileObject;
}

}
