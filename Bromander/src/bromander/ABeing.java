package bromander;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

public abstract class ABeing implements Serializable{
	public int x;
	public int y;
	public int dx;
	public int dy;
	String name;
	Sprite sprite;
	Boolean isAlive;
	
	
	public ABeing(String name, String path){
		this.x = 0;
		this.y = 0;
		this.dx = 0;
		this.dy = 0;
		this.name = name;
		this.sprite = new Sprite(path);
		this.isAlive = true;
		
	}
	
	public int getx(){
		return this.x;
	}
	
	public int gety(){
		return this.y;
	}
	
	public int getdx(){
		return this.dx;
	}
	
	public int getdy(){
		return this.dy;
	}
	
	protected void setx(int i){
		this.x = i;
	}
	
	protected void sety(int i){
		this.y = i;
	}
	
	protected void setdx(int i){
		this.dx = i;
	}
	
	protected void setdy(int i){
		this.dy = i;
	}	
	
	//draws the model onto the graphics
	public void draw(Graphics g){
		this.sprite.draw(g, new Point(x, y));
	
	}

	public Rectangle getBounds()
	{
		return null;
		
	}  
	
	public void move(){
		
		setx(x + dx); 
		sety(y - dy);
		
	}
	

	
}
