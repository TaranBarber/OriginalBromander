package bromander;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatBox extends JPanel{
	
	// Static components that will later be accessed by ActionListeners
	public static JTextField textField;
	public static JTextArea chatLog;
	public static JButton button;
	static boolean coinToss = false;
	static int money = 50;

	// Append message to the chatLog and reset textField
	public static class SendActionListener implements ActionListener 
	{


		@Override
		public void actionPerformed(ActionEvent e) 
		{
			chatLog.setText(chatLog.getText() + textField.getText() 
							+ doWork(textField.getText()) + "\n" );
			textField.setText("");

		}

		public String lobby()
		{
			return
			"\nYou have " + money + " dollars. \n" +
			"What would you like to do? \n" +
			"Available Options: \n" +
			"coin toss, craps, black jack, save game, welfare, exit \n";
		}
		
		public String coinToss(String stoof)
		{
			int heads = 0;
			if (!stoof.equalsIgnoreCase("tails") && !stoof.equalsIgnoreCase("heads")) return
															"\nYou must pick heads or tails!";
			if (stoof.equalsIgnoreCase("heads")) heads = 1;
			if (stoof.equalsIgnoreCase("tails")) heads = 0;
			
			int answer = (int) (Math.random() * 2);
			if (answer == heads)
			{
				money += 10;
				coinToss = false;
				return "\nRight! You now have " + money + ".";				
			}
			else
			{
				money -= 10;
				coinToss = false;
				return "\nWrong! You now have " + money + ".";
			}
		}

		public String doWork(String stoof)
		{
			if (coinToss == true) return coinToss(stoof);
			if (stoof.equalsIgnoreCase("chris brown")) System.exit(0);
			if (stoof.equalsIgnoreCase("lobby")) return lobby();
			if (stoof.equalsIgnoreCase("coin toss"))
			{
				coinToss = true;
				return "\nCoin Toss: Heads or tails?";
			}
			return "";
		}
	}

	public ChatBox() {
		super(new GridLayout(1,1));
		
		// Initialize our components
		JButton button = new JButton("Send");
		textField = new JTextField("Type message here...");
		chatLog = new JTextArea();
		
		// Initialize a JPanel with a BorderLayout LayoutManager
		JPanel panel = new JPanel( new GridBagLayout() );
		JScrollPane scrollableChatLog = new JScrollPane(chatLog);

		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.0, 0.0};
		thisLayout.rowHeights = new int[] {135, 25};
		thisLayout.columnWeights = new double[] {0.0, 0.0};
		thisLayout.columnWidths = new int[] {225, 65};
		panel.setLayout(thisLayout);

		// Add textField and button to the JPanel which
		// will ocupy the southern border of the JFrame
		panel.add(textField,  new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 0));
		panel.add(button, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 0));
		panel.add(scrollableChatLog, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.5, 
				GridBagConstraints.CENTER, 
				GridBagConstraints.BOTH, 
				new Insets(0, 0, 0, 0), 0, 0));


		// Make chatLog non-editable and use word wrap
		chatLog.setEditable(false);
		chatLog.setLineWrap(true);
		chatLog.setWrapStyleWord(true);

		// Capture actions from button and textField with SendActionListener
		button.addActionListener ( new SendActionListener () );
		textField.addActionListener ( new SendActionListener () );

		add(panel);
		
		setFocusable(true);


	}
	
	//adds the string to the chatLog and goes to the next line
	public void update(String s){
		chatLog.setText(chatLog.getText() + textField.getText() + "\n" );
	}
	
}