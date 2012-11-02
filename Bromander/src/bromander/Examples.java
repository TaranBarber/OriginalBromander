package bromander;
import java.awt.Rectangle;
import tester.*;

public class Examples 
{
	

	// Test functions of Bullet
	public void testBullet(Tester t)
	{
		Bullet bullet = new Bullet(50, 50, 1, 1);
		
		// Test coordinates and image of bullets
		t.checkExpect(bullet.getBounds(), new Rectangle(50, 50, 45, 45));
		bullet.move();
		t.checkExpect(bullet.getX(), 51);
		t.checkExpect(bullet.getY(), 51);
		t.checkExpect(bullet.getImage(), bullet.createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/fireball left.png", "bullet").getImage());
	}
	
	// Test functions of IBeing, ABeing, Player, and Enemy
	public void testBeings(Tester t)
	{
		Player bro = new Player();
		Enemy cb = new Enemy(50, 50);
		
		// Test coordinates and movement
		t.checkExpect(bro.getx(), 300);
		t.checkExpect(bro.gety(), 225);
		bro.setdx(100);
		bro.setdy(50);
		bro.move();
		t.checkExpect(bro.getx(), 400);
		t.checkExpect(bro.gety(), 175);
		t.checkExpect(cb.getx(), 50);
		t.checkExpect(cb.gety(), 50);
		cb.setdx(1234);
		cb.setdy(5678);
		cb.move();
		t.checkExpect(cb.getx(), 1284);
		t.checkExpect(cb.gety(), -5628);
	}
	
	// Test functions of Tile, Tiles, and TileType
	public void testTiles(Tester t)
	{
		Tiles.initialize();
		Tile tile1 = 
			new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/grass 1.png"), 
								TileType.Solid);
		Tile tile3 = 
			new Tile(new Sprite("/bromander/images/Bromander Pictures/Tiles/water 1.png"),
					            TileType.Liquid);
		
		// Test the image paths of tiles
		t.checkExpect(tile1.getImagePath().equals(Tiles.GRASS.getImagePath()), true);
		t.checkExpect(tile3.getImagePath().equals(Tiles.WATER.getImagePath()), true);
		t.checkExpect(tile1.getImagePath().equals(Tiles.SAND.getImagePath()), false);
		
		// Test the tile types of tiles
		t.checkExpect(tile1.getTileType() == TileType.Solid, true);
		t.checkExpect(tile3.getTileType() == TileType.Liquid, true);
		t.checkExpect(tile3.getTileType() == TileType.Gas, false);
	}
	
	// Test functions of Map
	public void testMap(Tester t)
	{
		Map map = new Map();
		Map map2 = new Map(5, 5);
		
		// Check default tiles in new maps
		t.checkExpect(map.getTile(1, 4), Tiles.GRASS);
		t.checkExpect(map2.getTile(5, 5), Tiles.GRASS);
		
		// Test resizing maps and the new tiles
		map2.setHeight(10);
		map2.setWidth(10);
		t.checkExpect(map2.getTile(6, 7), Tiles.GRASS);
		map2.setSize(20, 20);
		t.checkExpect(map2.getTile(11, 19), Tiles.GRASS);
	}
	
	public static void main(String args[])
	{
		Tiles.initialize();
		Tester.runFullReport(new Examples());
	}
}
