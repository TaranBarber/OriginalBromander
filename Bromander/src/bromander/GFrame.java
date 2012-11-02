package bromander;

import javax.swing.*;


public class GFrame
{
	JFrame frame = new JFrame("Bromander Chronicles");
	public MainPanel mainPanel;

	
	public GFrame(){
		mainPanel = new MainPanel();
		
		frame.add(mainPanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(
				createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/icon.png", 
								"bro").getImage());
		frame.setSize(450,300);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		
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
	
	static void createAndShowGUI() {
		GFrame gf = new GFrame();
		//frame.gamePanel.view.run();
	}

	
	public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
			public void run() {
                createAndShowGUI();
            }
        });
    }


}
