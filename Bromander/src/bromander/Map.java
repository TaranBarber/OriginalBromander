package bromander;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;

public class Map {
	//2D Array of tiles that represents the map.
	  Tile[][] tiles;
	  int width;
	  int height;
	  
	  
	  Tile[][] defaultTiles = new Tile[12][9];
	  
	  
	//Create a new map with specific dimensions
	  public Map()
	  {
		
	    tiles = new Tile[12][9];
	    
	    this.width = 12;
	    this.height = 9;
	    
	    loadDefaultMap();
	  }
	  
	  
	  public Map(int i, int j) {
		
		tiles = new Tile[i][j];
		
		this.width = i;
		this.height = j;
	}


	void loadDefaultMap(){
		  
		  this.tiles = defaultTiles;
		  this.setTilesInRange(0,0,12,9, Tiles.GRASS);
	  }
	  
		protected ImageIcon createImageIcon(String path,
				String description) {
			java.net.URL imgURL = getClass().getResource(path);
			if (imgURL != null) {
				return new ImageIcon(imgURL, description);
			} else {
				System.err.println("Couldn't find file: " + path);
				return null;
			}
		}

	  // set the tile at the given coordinates to the tile type
	  public void setTile(int x, int y, Tile tile)
	  {
		  try
		  {
			  if
			  ((x <this.width)&&(y<this.height))
			  tiles[x][y] = tile;
		  }
		  catch (Exception ex)
		  {
			  System.out.println("Increase the map size to change this tile.");
		  }
	  }
	  
		public void setTileObject(int x, int y, TileObject tileObject) {
			try
			  {
				  if
				  ((x <this.width)&&(y<this.height))
				  tiles[x][y].setTileObject(tileObject);
			  }
			  catch (Exception ex)
			  {
				  System.out.println("Increase the map size to change this tile.");
			  }
		}
	  
	  //Sets the the tiles in the range of the coordinates to the tile type.
	  public void setTilesInRange(int x, int y, int width, int height, Tile tile){
		 for(int i = 0; i < width; i++)
		 {
			 for (int j = 0; j < height; j++)
			 {
				 this.setTile( i, j, tile);
			 }
		 }
	  }
	  
	  //Gets the tile at the specified (x,y) position
	  public Tile getTile(int x, int y)
	  {
	    return tiles[x][y];
	  }
	  
	  //Gets the tile at the specified tile coord (x,y)
	  public Tile getTile(Point tileCoord)
	  {
	    return tiles[tileCoord.x][tileCoord.y];
	  }
	  
	  //Gets the tile at the specified pixel position
	  public Tile getTileAtPosition(Point point)
	  {
	    //Look into making sure this will return the correct tile.
	    return tiles[point.y/View.TILE_SIZE][point.y/View.TILE_SIZE];
	  }
	  
	  //Gets the tile at the specified pixel position
	  public Tile getTileAtPosition(int x, int y)
	  {
	    //Look into making sure this will return the correct tile.
	    return tiles[x/View.TILE_SIZE][y/View.TILE_SIZE];
	  }
	  
	  //Draws all of the tiles at their specific positions
	  public void draw(Graphics graphics, Point position)
	  {
	    this.draw(graphics, position.x, position.y);
	  }
	  //Draws all of the tiles at their specific positions
	  public void draw(Graphics graphics, int offsetX, int offsetY)
	  {
	    //Loop through each column
	    for(int x = 0; x < width; x++)
	    {
	      //Loop through each tile in the current column
	      for(int y = 0; y < height; y++)
	      {
	        //Draw the tile we are currently on at the correct position
	        tiles[x][y].draw(graphics,offsetX + x*View.TILE_SIZE, offsetY + y*View.TILE_SIZE);
	      }
	    }
	  }
	  
	  //Saving/Loading Methods
	  public void save(File fileName)
	  {
	    java.io.PrintWriter writer;
	    
	    try
	    {
	      writer = new java.io.PrintWriter(new java.io.FileWriter(fileName));
	      writer.println(width+","+height);
	      
	      for (int y = 0; y < height; y++)
	      {
	        for (int x = 0; x < width; x++)
	        {
	          writer.println(getTile(x,y).getTileType());
	        }
	      }
	      writer.close();
	    }
	    catch (Exception e)
	    {
	    	System.out.println("Error in saving");
	    }
	  }
	  
	  public Map load(File fileName)
	  {
	    java.io.BufferedReader reader;
	    String line;
	    String[] elements;
	    Map map = null;
	    try
	    {
	      reader = new java.io.BufferedReader(new java.io.FileReader(fileName));
	      line = reader.readLine();
	      elements = line.split(",");
	      map = new Map(Integer.parseInt(elements[0]),Integer.parseInt(elements[1]));
	      for (int h = 0; h < map.height; h++)
	      {
	        for (int w = 0; w < map.width; w++)
	        {
	          line = reader.readLine();
	          map.setTile(w,h, Tiles.getTile(TileType.parse(line)));
	        }
	      }
	      reader.close();
	    }
	    catch(Exception e)
	    {
	      System.out.println("Error in loading");
	    }
	    return map;
	  }
	  
	  public void setWidth(int w){
		  Tile[][] temp = new Tile[w][this.width];
		  
		  
		  for (int j = 0; j < this.width; j++) 
		  {
		      for (int i = 0; i < this.height; i++)
		      {
		    	  temp[j][i] = tiles[j][i];
		      }
		    		  
		  }
		    
		  tiles = temp;
		  this.width = w;


	  }
	  
	  public void setHeight(int h){
		  Tile[][] temp = new Tile[this.height][h];
		  
		  
		  
		  for (int j = 0; j < this.width; j++) 
		  {
		      for (int i = 0; i < this.height; i++)
		      {
		    	  temp[j][i] = tiles[j][i];
		      }
		    		  
		  }
		    
		  tiles = temp;
		  this.height =  h;


	  }
	  
	  public void setSize(int w, int h){
		  Tile[][] temp = new Tile[w][h];
		  
		  for (int j = 0; j < w; j++) 
		  {
		      for (int i = 0; i < h; i++)
		      {
		    	 if ((j < this.tiles.length)&&(i < this.tiles[0].length))
		    	 {
		    		 temp[j][i] = tiles[j][i];  
		    	 }
		    	 else
		    	 {
		    		 temp[j][i] = Tiles.GRASS;  
		    	 }
		      }
		    		  
		    }
		  
		  System.out.println(temp[w-1][h-1]);
		    
		  tiles = temp;
		  this.width = tiles.length;
		  this.height = tiles[0].length;

	  }



	  
	  
}
