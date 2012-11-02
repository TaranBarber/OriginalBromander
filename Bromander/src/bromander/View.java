package bromander;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class View extends JPanel implements ActionListener
{

	public static final int TILE_SIZE = 50;
	
	Player p;
	Enemy en, en2, en3;
	public ArrayList<Enemy> enemy;
	private Map map;

	transient Timer time;
	static int v = 172;
	transient Thread animator;
	boolean a = false;

	static Font font = new Font("SanSerif", Font.BOLD, 24);

	public View() {

		setBorder(BorderFactory.createLineBorder(Color.black));



		p = new Player();
		map = new Map();
		map.loadDefaultMap();
		
		enemy = new ArrayList();
		en = new Enemy(400,400,3);
		en2 = new Enemy (500,200,2);
		en3 = new Enemy(100,300,1);
		enemy.add(en);
		enemy.add(en2);
		enemy.add(en3);
		
		
		time = new Timer(2, this);
		time.start();
		
		setFocusable(true);


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

	//performs specified tasks every time an action is performed 
	//by the component it’s linked to. 
	@Override
	public void actionPerformed(ActionEvent e) {
		//update is called by the timer now instead of an action being performed.
	}


	//updates the model information every time an action listener calls it 
	//or every time the thread calls this while in the loop
	public void update(){
		checkCollisions();
		for (int w = 0; w < Player.bullets.size(); w++)
		{
			Bullet m = (Bullet) Player.bullets.get(w);
			if (m.getVisible())
				m.move();
			else
				Player.bullets.remove(w);
		}
		for (int w = 0; w < Enemy.bullets.size(); w++)
		{
			Bullet m = (Bullet) Enemy.bullets.get(w);
			if (m.getVisible())
				m.move();
			else
				Enemy.bullets.remove(w);
		}
		p.move();
		
		moveEnemies(map.width, map.height, p.x, p.y, p.getState(), 0);
		
	}

	private void moveEnemies(int width, int height, int x, int y, String state, int i) {
		if (enemy.size() > i)
		{
			enemy.get(i).move(map.width, map.height, p.x, p.y, p.getState());
			moveEnemies(map.width, map.height, p.x, p.y, p.getState(), i+1);
		}
	
		
	}

	//called by the thread or actionlistener after the game is updated 
	//to paint the components graphics. 
	//Calls the draw methods of the components models.
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
		Graphics2D g2d = (Graphics2D) g;
		//draw the map, beings, and other objects.
		map.draw(g2d, 0,0);
		p.draw(g2d);
		
		g2d.setFont(font);
		g2d.setColor(Color.BLUE);
		g2d.drawString("Ammo left: " + Player.ammo, 50, 20);
		ArrayList bullets = Player.getBullets();
		for (int w = 0; w < bullets.size(); w++)
		{
			Bullet m = (Bullet) bullets.get(w);
			if (m.getVisible())
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
				m.move();
		}
		for (Enemy e : enemy)
		{
			ArrayList bulletsE = Enemy.bullets;
			for (int i = 0; i<bulletsE.size(); i++)
			{
				Bullet m = (Bullet) bulletsE.get(i);
			if (m.getVisible())
			{
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), null);
				m.move();
			}
			else
				bulletsE.remove(m);
			}
		}
		for (Enemy e : enemy)
		{
			if (e.Alive())
				g2d.drawImage(e.getImage(), e.getX(), e.getY(), null);
		}
	}
	
	//a method called by update to check for object collision. Responds accordingly.
	private void checkCollisions()
	{
		ArrayList bullets = Player.getBullets();
		for (int w = 0; w < bullets.size(); w++)
		{
			Bullet m = (Bullet) bullets.get(w);
			Rectangle m1 = m.getBounds();
			for (int i = 0; i < enemy.size(); i++)
			{
				Enemy e = enemy.get(i);
				if (e.getBounds().intersects(m1) && e.Alive())
				{
					e.isAlive = false;
					m.visible = false;
					enemy.remove(e);
					Player.bullets.remove(m);
				}
			}
		}
		Rectangle d = p.getBounds();
		ArrayList bulletsE = Enemy.getBullets();
		for (int w = 0; w < bulletsE.size(); w++)
		{
			Bullet m = (Bullet) bulletsE.get(w);
			Rectangle m1 = m.getBounds();
			if (d.intersects(m1) && p.isAlive)
			{
				//use this code to let the player actually die.
//				p.isAlive = false;
//				System.out.print("Bro, you've been hit.");
//				time.stop();
//				m.visible = false;
				Enemy.bullets.remove(m);	
			}
		} 

	}

	
	//Converts the component or model into a string that contains
	//the most important information for serialization.
	@Override
	public String toString(){
		return p.toString();
	}
	
	

	public void keyReleased(KeyEvent e) {
		p.keyReleased(e);
	}

	public void keyPressed(KeyEvent e) {
		p.keyPressed(e);
	}

	public Player getPlayer() {
		return this.p;
	}

}
