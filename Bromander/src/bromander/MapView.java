package bromander;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Scrollable;

public class MapView extends JPanel implements Scrollable{
	Map map;
	
	MapView(){
		map = new Map();
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setPreferredSize(getPreferredScrollableViewportSize());
		
		map.loadDefaultMap();
		
		
	}
	
	//updates the model information every time an action listener 
	//calls it or ever time the thread calls this while in the loop.
	void update()
	{
		
		setPreferredSize(getPreferredScrollableViewportSize());
	}
	
	//called by the thread or actionlistener after the game is updated to 
	//paint the components graphics. Calls the draw methods of the components models.
	@Override
	protected void paintComponent(Graphics g)
	  {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;

		//draw the map, beings, and other objects.
		map.draw(g2d, 0,0);
	    drawGrid(g2d);
	  }

	private void drawGrid(Graphics g) {
		g.setColor(Color.black);
	    for (int x =0; x < map.height; x++)
	    {
	      g.fillRect(x*View.TILE_SIZE, 0, 2, (map.height)*View.TILE_SIZE);
	    }
	    for (int y = 0; y < map.width; y++)
	    {
	      g.fillRect(0, y*View.TILE_SIZE, (map.width)*View.TILE_SIZE ,2);
	    }
		
	}

	public void setTile(int i, int j, Tile tile) {

			map.setTile(i, j, tile);
			repaint();
	}
	
	public void setTileObject(int i, int j, TileObject tileObject) {

		map.setTileObject(i, j, tileObject);
		repaint();
	}


	public Tile getTile(int i, int j) {
		return map.getTile(i, j);
	}

	//converts the specific class to a string and saves it to the specified file location.
	public void save(File file) {
		map.save(file);
	}

	//loads the specific class from a string to a class from the specified file location.
	public void load(File loadTarget) {
		this.map = map.load(loadTarget);
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return new Dimension(map.width* View.TILE_SIZE, map.height*View.TILE_SIZE);
	}

	@Override
	public int getScrollableBlockIncrement(Rectangle arg0, int arg1, int arg2) {

		return 0;
	}

	@Override
	public boolean getScrollableTracksViewportHeight() {

		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {

		return false;
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle arg0, int arg1, int arg2) {

		return 0;
	}	

}
