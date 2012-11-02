package bromander;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Player extends ABeing{
	int left;
	Image still;
	private String state;
	private Stat stats;
	
	static int ammo;
	
	void downPNG()
	{
		still = 
		createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/bro down 1.png", 
						"down").getImage();
	}
	void upPNG()
	{
		still = 
		createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/bro up 1.png",
						"up").getImage();
	}
	void rightPNG()
	{
		still = 
		createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/bro right 1.png", 
						"right").getImage();
	}
	void leftPNG()
	{
		still = 
		createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/bro left 1.png",
						"left").getImage();
	}
	
	static ArrayList bullets;
	
	public Player() {
		super("Bro", "/bromander/images/Bromander Pictures/Bromander Pictures/bro right 1.png");
		
		state = "right";
		setx(300);
		sety(225);
		left = 300;
		
		bullets = new ArrayList();
		ammo = 1000;
	
	}
	
	public Player(String state, int ammo) {
		super("Bro", "/bromander/images/Bromander Pictures/Bromander Pictures/bro right 1.png");
		
		this.state = state;
		setx(300);
		sety(225);
		left = 300;

		
		bullets = new ArrayList();
		Player.ammo = ammo;
		
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
	
	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(getx(), gety(), 50, 50);
	}  
	
	public String getState()
	{
		return state;
	}
	
	public static ArrayList getBullets()
	{
		return bullets;
	}
	
	public void fire()
	{
		if (ammo > 0)
		{
		int bdx = 0;
		int bdy = 0;
		if (state.equals("left"))
		{
			bdx = -1;
		}
		
		if (state.equals("right"))
		{
			bdx = 1;
		}
		
		if (state.equals("up"))
		{
			bdy = -1;
		}
		
		if (state.equals("down"))
		{
			bdy = 1;
		}
		
		if (state.equals("downleft"))
		{
			bdy = 1;
			bdx = -1;
		}
		
		if (state.equals("downright"))
		{
			bdy = 1;
			bdx = 1;
		}
		
		if (state.equals("upleft"))
		{
			bdy = -1;
			bdx = -1;
		}
		
		if (state.equals("upright"))
		{
			bdy = -1;
			bdx = 1;
		}
		
		ammo--;
		
		Bullet z = new Bullet((this.getx()), (this.gety()), bdx, bdy);
		bullets.add(z);
	}}

	public void fireX()
	{
		if (ammo > 3)
		{
		ammo= ammo - 4;
		//The v is from the board class, which corresponds to the character's
		//position when it is jumping, resulting in the bullet being formed 
		//at a higher position when the character is at the peak of its jump
		Bullet 	a = new Bullet((this.getx()), (this.gety()), -1, -1),
				b = new Bullet((this.getx()), (this.gety()), -1, 1),
				c = new Bullet((this.getx()), (this.gety()), 1, -1),
				d = new Bullet((this.getx()), (this.gety()), 1, 1);
		bullets.add(a);
		bullets.add(b);
		bullets.add(c);
		bullets.add(d);
		}
	}
	
	
	public void fireSides()
	{
		if (ammo > 3)
		{
		ammo= ammo - 4;
		//The v is from the board class, which corresponds to the character's
		//position when it is jumping, resulting in the bullet being formed 
		//at a higher position when the character is at the peak of its jump
		Bullet 	a = new Bullet((this.getx()), (this.gety()), 0, -1),
				b = new Bullet((this.getx()), (this.gety()), 0, 1),
				c = new Bullet((this.getx()), (this.gety()), 1, 0),
				d = new Bullet((this.getx()), (this.gety()), -1, 0);
		bullets.add(a);
		bullets.add(b);
		bullets.add(c);
		bullets.add(d);
		}
	}

	
	
	public int getLeft(){
		return left;
	}



	
	public Image getImage() {
		return still;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT){
			setdx(-1);
			leftPNG();	
			setSpriteImage(still);
			state = "left";
			
		}
		
		if (key == KeyEvent.VK_RIGHT){
			setdx(1);
			rightPNG();	
			setSpriteImage(still);
			state = "right";
			}
		
		if (key == KeyEvent.VK_DOWN){
			setdy(-1);
			downPNG();
			setSpriteImage(still);
			state = "down";
		}
		
		
		if (key == KeyEvent.VK_UP){
			setdy(1);
			upPNG();
			setSpriteImage(still);
			state = "up";
			}	
		
		if (getdx()<0)
		{
			if (getdy()<0)
				state = "downleft";
			if (getdy()>0)
			{
				state = "upleft";
			}
		}
		
		if (getdx()>0)
		{
			if (getdy()<0)
				state = "downright";
			if (getdy()>0)
				state = "upright";
		}
		
		if (key == KeyEvent.VK_V){
			fire();
		}
		
		if (key == KeyEvent.VK_X){
			fireX();
		}
		
		if (key == KeyEvent.VK_C){
			fireSides();
		}
		
		if (key == KeyEvent.VK_Z){
			fireSides();
			fireX();
		}
		
		}

	private void setSpriteImage(Image img) {
		sprite.setImage(still);
		
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT)
			setdx(0);

		if (key == KeyEvent.VK_RIGHT)
			setdx(0);
		
		if (key == KeyEvent.VK_UP)
			setdy(0);
		
		if (key == KeyEvent.VK_DOWN)
			setdy(0);
		
		}
	
	//converts the component or model into a string 
	//that contains the most important information for serialization.
	@Override
	public String toString(){
		System.out.println(state + ":" + Integer.toString(ammo));
		return state + ":" + Integer.toString(ammo);
		
	}
	public void setAmmo(int i) {
		Player.ammo = i;
		
	}
	public void setState(String s) {
		this.state = s;
	}


	}
