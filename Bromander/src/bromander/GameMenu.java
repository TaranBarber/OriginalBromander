package bromander;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class GameMenu extends JPanel
{
		// Initialize our components
		JButton chrisBrown = new JButton("Save and Exit");
		
		
		
		public GameMenu() 
		{
			super(new GridLayout(1, 1));
			
			JTabbedPane tabbedPane = new JTabbedPane();
	        //ImageIcon icon = createImageIcon("images/middle.gif");
	        
	        JComponent panel1 = new JPanel();
	        panel1.add(chrisBrown, BorderLayout.CENTER);
	        tabbedPane.addTab("Menu", null, panel1,
	                "Does nothing");
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	        
	        
	        JComponent panel2 = makeTextPanel("Panel #2");
	        tabbedPane.addTab("Bag", null, panel2,
	                "Does twice as much nothing");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	        
	        JComponent panel3 = makeTextPanel("Panel #3");
	        tabbedPane.addTab("Stats", null, panel3,
	                "Still does nothing");
	        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	        
	        
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
	        
	        //Add the tabbed pane to this panel.
	        add(tabbedPane);
	        
	        //The following line enables to use scrolling tabs.
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		}
		
		protected JComponent makeTextPanel(String text) {
	        JPanel panel = new JPanel(false);
	        JLabel filler = new JLabel(text);
	        filler.setHorizontalAlignment(SwingConstants.CENTER);
	        panel.setLayout(new GridLayout(1, 1));
	        panel.add(filler);
	        return panel;
	    }
	
}
