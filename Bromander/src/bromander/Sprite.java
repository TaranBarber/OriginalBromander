package bromander;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
public class Sprite
{
  //stores the image that the sprite represents
  private Image image;
  private String imagePath;
  //Constructs a new sprite from an image
  public Sprite(Image image, String imagePath)
  {
    this.image = image;
    this.imagePath = imagePath;
  }
  
  public Sprite(String imagePath)
  {
    this.image = createImageIcon(imagePath).getImage();
    this.imagePath = imagePath;
  }

  
  //Gets the Width of this sprite
  public int getWidth()
  {
    return image.getWidth(null);
  }
  
  //Gets the Height of this sprite
  public int getHeight()
  {
    return image.getHeight(null);
  }
  public String getImagePath()
  {
    return imagePath;
  }
  //Allows easy drawing of this sprite at any position
  public void draw(Graphics graphics, int x, int y)
  {
    graphics.drawImage(image,x,y,null);
  }
  
    //Allows easy drawing of this sprite at any position
  public void draw(Graphics graphics, Point position)
  {
    graphics.drawImage(image,position.x,position.y,null);
  }

  public void drawFlipped(Graphics graphics, Point position)
  {
    graphics.drawImage(image, image.getWidth(null)+position.x, 
    		           position.y,position.x,image.getHeight(null)+position.y,
    		           0,0,image.getWidth(null), image.getHeight(null),null);
  }
  public void drawFlipped(Graphics graphics, int x, int y)
  {
    graphics.drawImage(image, image.getWidth(null)+x, y,x,image.getHeight(null)+y, 
    				   0,0,image.getWidth(null), image.getHeight(null),null);
  }
  
  	//Returns the ImageIcon from the specific path
   ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void setImage(Image still) {
		image = still;
	}
	
	public Image getImage(){
		return this.image;
	}
}
