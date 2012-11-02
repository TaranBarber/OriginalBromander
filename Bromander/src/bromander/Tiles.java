package bromander;

import java.util.Hashtable;
import java.util.ArrayList;

public class Tiles
{
  private static ArrayList tiles = new ArrayList();
  private static Hashtable lookUp = new Hashtable();
  private static int count = 0;
  
  static Tile GRASS;
  static Tile WATER;
  static Tile SAND;

  
  public static void addTile(Tile tile)
  {
    tiles.add(tile);
  }
  
  public static Tile getTile(int index)
  {
    return (Tile)tiles.get(index);
  }
  
  public static Tile getTile(TileType tt){
	  for (int i = 0; i < Tiles.getCount(); i++){
		  if (Tiles.getTile(i).getTileType() == tt)
			  {return Tiles.getTile(i);}
	  }
	  return null;
		  
	  
  }
  
  public static int getCount()
  {
    return tiles.size();
  }
  
  public static void initialize()
  {
    //tiles.add(new Tile(spriteLoader.load("Content/Textures/Tiles/empty.png"),TileType.Blank));
//    tiles.add(new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/water 1.png"), TileType.Water));
//    tiles.add(new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/Rock 1.png"), TileType.Rock));
//    tiles.add(new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/Sand 1.png"), TileType.Sand));
//    tiles.add(new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/Grass 1.png"), TileType.Grass));
    tiles.add(GRASS = new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/grass 1.png"), TileType.Solid));
    tiles.add(WATER = new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/water 1.png"), TileType.Liquid));
    tiles.add(SAND  = new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/Sand 1.png"),  TileType.Solid));
    //tiles.add(ROCK  = new Tile(TileType.Rock));
    
  }
}
