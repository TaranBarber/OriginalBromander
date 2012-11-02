package bromander;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePanel extends JPanel implements Runnable{
	
	private ChatBox chat;
	private GameMenu menu;
	View view;
	
	long beforeTime, sleep, timeDiff;
	
	boolean done2 = false;
	Timer time;
	static int v = 172;
	Thread animator;
	boolean a = false;
	boolean h = false;
	boolean done = false;

	GamePanel(){
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		gridbag.rowWeights = new double[] {0.0, 0.5, 0.0};//, 0.0, 0.0, 0.0};
		gridbag.rowHeights = new int[] {215, 235, 120};//, 100, 100, 50};
		gridbag.columnWeights = new double[] {0.5, 0.0};//, 0.0, 0.0, 0.0};
		gridbag.columnWidths = new int[] {600, 160};//, 0, 0, 100};
		
		this.setLayout(gridbag);
		
		chat = new ChatBox();
		menu = new GameMenu();
		view = new View();
		
		
		//action listeners
        menu.chrisBrown.addActionListener(new ChrisBrownListener());
        view.addKeyListener(new AL());

		//Add content to the window.
        this.add(chat,
        		new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, 
        					GridBagConstraints.NORTH, 
        					GridBagConstraints.BOTH, 
        					new Insets(0, 0, 0, 0), 0, 0));
        
        this.add(menu,
        		new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, 
        					GridBagConstraints.NORTH, 
        					GridBagConstraints.BOTH, 
        					new Insets(0, 0, 0, 0), 0, 0));
        
        
		this.add(view,
        		new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, 
        					GridBagConstraints.NORTH, 
        					GridBagConstraints.BOTH, 
        					new Insets(0, 0, 0, 0), 0, 0));
	}
	
	
	// sets the action for the button "Chris Brown"
	public class ChrisBrownListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			try
			{
				saveGame();
				System.exit(0);	
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	//converts the specific class to a string and saves it to the specified file location.
	public void saveGame() throws IOException
	{
		System.out.println("Saving your game.");
		try
		{
			ObjectOutputStream os = 
				new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("Game.ser")));
			os.writeObject(view.toString());
			os.close();
		}
		catch (IOException ex) 
		{
            ex.printStackTrace();
        }
	}
	
	//loads the specific class from a string to a class from the specified file location.
	public void loadGame() throws IOException
	{
		java.io.BufferedReader reader;
	    String line;
	    String[] elements;
	    Map map = null;
	    try
	    {
	      reader = new java.io.BufferedReader(new java.io.FileReader("Game.ser"));
	      line = reader.readLine();
	      elements = line.split(":");
	      map = new Map(Integer.parseInt(elements[0]),Integer.parseInt(elements[1]));
	      for (int h = 0; h < map.height; h++)
	      {
	        for (int w = 0; w < map.width; w++)
	        {
	          line = reader.readLine();
	          //elements = line//.split(":");
	          map.setTile(w,h, Tiles.getTile(TileType.parse(line)));
	        }
	      }
	      reader.close();
	    }
	    catch(Exception e)
	    {
	      System.err.println(map.width);
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
	
	public class AL extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			view.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			view.keyPressed(e);
		}
	}
	
	public void update(){
		// call individual update fields
		
		view.update();
		view.requestFocus();
		beforeTime = System.currentTimeMillis();
		
		

	}
	
	@Override
	public void run() {
		
		
		while(true)
		{
			timeDiff = System.currentTimeMillis() - beforeTime;
			sleep = 10 - timeDiff;
			System.out.println(sleep + "");
			if (sleep < 0)
				sleep = 2;
			
			
			
			
			// Sleep
			try 
			{
				// pauses the thread for an amount of time that accounts 
				// for processing speed variance between computers.
				Thread.sleep(sleep);
			} 
			catch (InterruptedException e) {}
			
			
			// Update
			update();
			
			
			// Try painting
			try 
			{
				SwingUtilities.invokeAndWait(new Runnable() 
				{
				    @Override
					public void run() {
				       repaint();
				    }
				});
			} 
		    catch (InterruptedException e1) 
				{
				e1.printStackTrace();
				} 
			catch (InvocationTargetException e1) 
			    {
				e1.printStackTrace();
			    }
		}
			
	}
	

	public void keyReleased(KeyEvent e) {
		view.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		view.keyPressed(e);
	}


	public View getView() {
		// TODO Auto-generated method stub
		return this.view;
	}

}
