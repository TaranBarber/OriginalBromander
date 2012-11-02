package bromander;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{
	Image bgImage;
	JButton gameButton = new JButton("New Game!");
	JButton editorButton = new JButton("Create Levels!");
	JButton loadGameButton = new JButton("Load Game!");
	
	StartPanel()
	{
		//bgImage = 
		//	createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/start.png", "start").getImage();
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(gridbag);
		gridbag.rowWeights = new double[] {0.0, 0.0, 0.0};
		gridbag.rowHeights = new int[] {60, 40, 200};
		gridbag.columnWeights = new double[] {0.0, 0.0,0.0};
		gridbag.columnWidths = new int[] {60,90,100};
		
		add(gameButton); // pad x, pad y
		add(editorButton);
		add(loadGameButton);
		
		
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
	
	


	//Called by the thread or actionlistener after the game is updated to paint 
	//the components graphics. Calls the draw methods of the components models.
	@Override
	public void paintComponent(Graphics g) {
		    
			if (bgImage!=null) {
		      g.drawImage(bgImage,0,0,this);
		    } else {
		      g.drawString("",40,40);
		    }
		  }
	

}
