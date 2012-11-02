package bromander;

import java.awt.*;

import javax.swing.ImageIcon;

public class Bullet {

	int x,y,dx,dy,owner;
	// 0 = player, 1 = enemy
	// by default it's owned by player
	Image img;
	boolean visible;
	
	public Bullet(int startX, int startY, int bdx, int bdy)
	{
		x = startX;
		y = startY;
		dx = bdx;
		dy = bdy;
		owner = 0;
		
		ImageIcon newBullet = 
			createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/fireball left.png",
							"bullet");
		img = newBullet.getImage();
		visible = true;
	}
	public Bullet(int startX, int startY, int bdx, int bdy, int owner)
	{
		x = startX;
		y = startY;
		dx = bdx;
		dy = bdy;
		this.owner = owner;
		ImageIcon newBullet = 
			createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/fireball left.png",
							"bullet");
		ImageIcon newBullet1 = 
			createImageIcon("/bromander/images/Bromander Pictures/Bromander Pictures/EBullet.png",
							"bullet");
		
		img = (owner==0)? newBullet.getImage() : newBullet1.getImage();
		visible = true;
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
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y, 45, 45);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public boolean getVisible()
	{
		return visible;
	}
	public Image getImage()
	{
		return img;
	}
	
	public void move()
	{
		x = x + dx;
		y = y + dy;
		
		if ( x >1000 || y > 1000){
			setVisible(false);
		}
	}
	
	public void setVisible(boolean isVisible)//down
	{
		visible = isVisible;
	}

}
