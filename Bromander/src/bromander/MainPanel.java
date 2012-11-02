package bromander;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MainPanel extends JPanel implements ActionListener{

	final static String STARTMENU = "Card with the StartMenu";
    final static String GAMEPANEL = "Card with the GameGUI";
    final static String EDITORPANEL = "Card with the Editor";
	
	public StartPanel startPanel;
	public GamePanel gamePanel;
	public EditorPanel editorPanel;
	
	JPanel cards;
	JPanel comboBoxPane;
	CardLayout cl = new CardLayout();
	MainPanel(){
		cards = new JPanel(cl);
		setBackground(new Color(100, 100, 100));
		
		Content.initialize();
		
		startPanel  = new StartPanel();
		cards.add(startPanel, STARTMENU);
		cl.show(cards, STARTMENU);
		
		gamePanel = new GamePanel();
		cards.add(gamePanel, GAMEPANEL);
		
		
		editorPanel = new EditorPanel();
		cards.add(editorPanel, EDITORPANEL);
		
		this.add(cards);
	
		//listeners
		startPanel.gameButton.addActionListener(this);
		startPanel.editorButton.addActionListener(this);
		startPanel.loadGameButton.addActionListener(this);

		
	}
	
	//converts the specific class to a string and saves it to the specified file location.
	public void saveGame(GamePanel gamePanel) throws IOException
	{
		System.out.println("Saving your game.");
		try
		{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));
			os.writeObject(gamePanel);
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
	    int ammo = 0;
	    String state = null;
	    try
	    {
	      reader = new java.io.BufferedReader(new java.io.FileReader("Game.ser"));
	      line = reader.readLine();
	      elements = line.split(":");
	      state = elements[0];
	      ammo = Integer.decode(elements[1]);
	      
	      reader.close();
	      
		  gamePanel.getView().getPlayer().setAmmo(ammo);
		  gamePanel.getView().getPlayer().setState(state);
	    }
	    catch(Exception e)
	    {
	      System.out.println("Can't load this save");
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startPanel.gameButton)
		{
			cl.show(cards, MainPanel.GAMEPANEL);
			new Thread(gamePanel).start();
		}
		
		if (e.getSource() == startPanel.editorButton)
		{
			cl.show(cards, MainPanel.EDITORPANEL);
		}
		
		if (e.getSource() == startPanel.loadGameButton)
		{
			try
			{
			loadGame();
			cl.show(cards, MainPanel.GAMEPANEL);
			new Thread(gamePanel).start();
			}
			catch (Exception ex)
			{
				System.out.println("cant");
			}
		}
	}
	
//	static void createAndShowGUI() {
//		JFrame frame = new JFrame("Bromander Chronicles");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		
//		frame.setSize(450,300);
//		MainPanel mainPanel = new MainPanel();
//		
//		frame.add(mainPanel, BorderLayout.CENTER);
//		
//		
//		frame.pack();
//		frame.setVisible(true);	
//	}
//
//
//	public static void main(String[] args) {
//        
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//			public void run() {
//                createAndShowGUI();
//            }
//        });
//		
//		
//
//    }
	
	
}
