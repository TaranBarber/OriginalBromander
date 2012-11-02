package bromander;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Enemy extends ABeing{

	int z;
	boolean isAlive = true;
	ImageIcon l = 
		createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/mob 1.png", "enemy");
	Image img = l.getImage();
	private String state; //direction
	private final int AI; 
	// Attack Style (1=direct, 2=100left100above,3=movesdifferentlybasedondirectionofplayer)
	public static ArrayList bullets;
	
	public Enemy(int startX, int startY)
	{
		super("Enemy", "/bromander/images/Bromander Pictures/Bromander Pictures/mob 1.png");
		state = "left";
	    x = startX;
		y = startY;
		z = 5;
		AI = (int)(Math.random() * 3) + 1; // gets 1-3 inclusively
		bullets = new ArrayList();
	}
	
	public Enemy(int startX, int startY, int aIStyle)
	{
		super("Enemy", "/bromander/images/Bromander Pictures/Bromander Pictures/mob 1.png");
		state = "left";
	    x = startX;
		y = startY;
		z = 5;
		AI = aIStyle;
		bullets = new ArrayList();
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
	
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getZ()
	{
		return z;
	}
	public void setZ(int i)
	{
		z = i;
	}
	public boolean Alive()
	{
		if (isAlive)
			if (z++%60==0)
			fire();
		return isAlive;
	}
	public Image getImage()
	{
		return img;
	}
	
	public void setState(int px, int py)
	{
		state = "";
		if (y-py<0)
		state = "down";
		if (y-py>0)
		state = "up";
		if (x-px>0)
		state = state.concat("left");
		if (x-px<0)
		state = state.concat("right");
	}
	public void move(int mapX , int mapY, int px, int py, String pState)
	{	
		setState(px, py);
		if (AI==3 && px>150 && py>150) //dr
		{
			if(pState.startsWith("down"))
			{
				if (y<py-150 || y>py+150)
					sety(++y);
				if (y>py-150 || y<py+150)
					sety(--y);
				if (x<px-150 || x>px+150)
					setx(++x);
				if (x>px-150 || x>px+150)
					setx(--x);
			}
			if (AI==3 && pState.startsWith("up"))
			{
				if (y<py-150 || y>py+150)
					sety(--y);
				if (y>py-150 || y<py+150)
					sety(++y);
				if (x<px-150 || x>px+150)
					setx(++x);
				if (x>px-150 || x>px+150)
					setx(--x);
			}
		}
		if (AI==2 && px>100 && py>100)
		{
			if (y<py-100)
				sety(++y);
			if (y>py-100)
				sety(--y);
			if (x<px-100)
				setx(++x);
			if (x>px-100)
				setx(--x);
		}
		else
		{
			if (y-py<0)
				sety(++y);
			if (y-py>0)
				sety(--y);
			if (x-px>0)
				setx(--x);
			if (x-px<0)
				setx(++x);
		}
	}
	
	public void fire()
	{	
		int bdx = 0;
		int bdy = 0;
		if (state.endsWith("left"))
		{
			bdx = -1;
			
		}
		
		if (state.endsWith("right"))
		{
			bdx = 1;
		}
		
		if (state.startsWith("up"))
		{
			bdy = -1;
		}
		
		if (state.startsWith("down"))
		{
			bdy = 1;
		}

		Bullet z = new Bullet((this.getx()), (this.gety()), bdx, bdy, 1);
		bullets.add(z);
	}
	

	@Override
	public Rectangle getBounds()
	{
		return new Rectangle(x,y, 73, 78);
	}
	
	
	public static ArrayList getBullets()
	{
		return bullets;
	}
	
}
