package bromander;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditorMenu extends JTabbedPane{
	// Options Tab
	JButton chrisBrown = new JButton("Chris Brown");
	JButton load = new JButton("Load");
	JButton save = new JButton("Save");
	JLabel widthLabel = new JLabel("Width:");
	JTextField widthField = new JTextField();
	JLabel heightLabel = new JLabel("Height:");
	JTextField heightField = new JTextField();
	JButton updateSize = new JButton("Update Size");
	JPanel options;
	
	// Tiles Tab
	JPanel tiles;
	
	private MapObject selectionType;
	private Tile currentTile;
	private TileObject currentTileObject;
	
	
	
	
	
	public EditorMenu() 
	{
        
          JPanel tiles = new JPanel();
          createTileButtons(tiles);
          
          addTab("Tiles", tiles);
          
          JPanel options = new JPanel();
          widthField.setMaximumSize(new Dimension(100,20));
          heightField.setMaximumSize(new Dimension(100,20));
          options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
          //options.add(chrisBrown);
          options.add(load);
          options.add(save);
          options.add(widthLabel);
          options.add(widthField);
          options.add(heightLabel);
          options.add(heightField);
          options.add(updateSize);
          
          addTab("Options", options);
          
          setPreferredSize(new Dimension(250, 450));
	}
	
	protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(SwingConstants.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	private void createTileButtons(JPanel panel)
	  {
	    for (int i = 0; i < Tiles.getCount(); i++)
	    {
	      JButton btn = new JButton(createImageIcon(Tiles.getTile(i).getImagePath(), 
	    		  					"Tile" + i));
	      btn.setToolTipText(""+i);
	      btn.setPreferredSize(new Dimension(50,50));
	      btn.setActionCommand(""+i);
	      btn.addActionListener(new TilesListener(btn));
	      panel.add(btn);
	    }
	    
	    for (int i = 0; i < TileObjects.getCount(); i++)
	    {
	      JButton btn = new JButton(createImageIcon(TileObjects.getTileObject(i).getImagePath(), 
	    		  					"Tile" + i));
	      btn.setToolTipText("" +i);
	      btn.setPreferredSize(new Dimension(50,50));
	      btn.setActionCommand(""+i);
	      btn.addActionListener(new TileObjectsListener(btn));
	      panel.add(btn);
	    }
	  }
	
	private class TilesListener implements ActionListener{

		JButton btn;

		public TilesListener(JButton btn) {
			this.btn = btn;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = Integer.parseInt(btn.getToolTipText());
			selectionType = MapObject.Tile;
			currentTile = Tiles.getTile(i);

			
		}
		
	}
	
	private class TileObjectsListener implements ActionListener{

		JButton btn;

		public TileObjectsListener(JButton btn) {
			this.btn = btn;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = Integer.parseInt(btn.getToolTipText());
			selectionType = MapObject.TileObject;
			currentTileObject = TileObjects.getTileObject(i);
			
		}
		
	}
	
	
	// Returns the ImageIcon from the specific path
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
	
	public MapObject getSelectionType(){
		return this.selectionType;
	}

	public Tile getCurrentTile() {
		return currentTile;
	}
	
	public TileObject getCurrentTileObject(){
		return currentTileObject;
	}


}
