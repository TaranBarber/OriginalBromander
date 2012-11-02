package bromander;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class EditorPanel extends JPanel implements MouseListener, ActionListener{
	MapView mapView;
	EditorMenu menu;
	JScrollPane scrollableMapView;
	
	File saveTarget;
	File loadTarget;
	
	private boolean mouseIsDown;
	private boolean mouseClicked;
	private Point mousePoint;
	private Point clickedPoint;
	private Point changeInKeyPoint;
	
	
	EditorPanel(){
		mapView = new MapView();
		menu = new EditorMenu();
		scrollableMapView = new JScrollPane(mapView);
		
//		scrollableMapView.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//		scrollableMapView.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//mapView.setMinimumSize(new Dimension(600,450));
		scrollableMapView.setPreferredSize(new Dimension(600,450));
		//menu.setSize(300,450);
		
		//add(scrollableMapView, BorderLayout.CENTER);
		add(scrollableMapView, BorderLayout.CENTER);
		add(menu, BorderLayout.EAST);
		
		
		addMouseListener(this);
		mapView.addMouseListener(this);
		menu.save.addActionListener(this);
		menu.load.addActionListener(this);
		menu.updateSize.addActionListener(new SizeUpdateListener());
		resetSizeFields();
		setBackground(new Color(100, 100, 100));
		
		
		setMinimumSize(new Dimension(875,475));

	}
		
	@Override
	public void mousePressed(MouseEvent e) {
		
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			mouseIsDown = true;
			this.mousePoint = e.getPoint();
			actionPerformed();
			
		}
    }

	@Override
	public void mouseClicked(MouseEvent e) {
      	
//        	mapView.repaint();
//        	validate();
//        	System.out.println(mapView.getTile(e.getX()/50,e.getY()/50).getImagePath());
//        	System.out.println("x:" + e.getX()/50);
//        	System.out.println("y:" + e.getY()/50);
//        	System.out.println(menu.getCurrentIndex());
//        	System.out.println(Tiles.getTile(menu.getCurrentIndex()).sprite.getImagePath());
		if (e.getButton() == MouseEvent.BUTTON1){
		mouseClicked = true;
		mouseIsDown = false;
		clickedPoint = e.getPoint();
		this.mousePoint = e.getPoint();
		actionPerformed();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			mouseIsDown = false;
			mouseClicked = false;
			this.mousePoint = e.getPoint();
			actionPerformed();
		}
		
	}
	
	public void mouseDragged(MouseEvent e){

		if (e.getButton() == MouseEvent.BUTTON1){
		this.changeInKeyPoint.x = this.mousePoint.x - e.getPoint().x;
	    this.changeInKeyPoint.y = this.mousePoint.y - e.getPoint().y;
	    this.mousePoint = e.getPoint();
	    actionPerformed();
		}
	}
	
	public void mouseMoved(MouseEvent e){
		this.mousePoint = e.getPoint();
		actionPerformed();
	}

	public void actionPerformed() {
//		System.out.println("hi");
//		System.out.println(mapView.getTile(mousePoint.x/50 , mousePoint.y/50).getImagePath());
//    	System.out.println("x:" + mousePoint.x/50);
//    	System.out.println("y:" + mousePoint.y/50);
//    	System.out.println(menu.getCurrentIndex());
//    	System.out.println(Tiles.getTile(menu.getCurrentIndex()).sprite.getImagePath());
    	
		if (mouseIsDown)
		{
			if (this.menu.getSelectionType() == MapObject.Tile)
				{
				mapView.setTile(mousePoint.x/View.TILE_SIZE,
				mousePoint.y/View.TILE_SIZE,
				getCurrentTile());
				}
			else if (this.menu.getSelectionType() == MapObject.TileObject)
			{
				mapView.setTileObject(mousePoint.x/View.TILE_SIZE,
						mousePoint.y/View.TILE_SIZE,
						getCurrentTileObject());
			}
		}
		
		
	}
	
	public Tile getCurrentTile() {
		
		return menu.getCurrentTile();
	}
	
	public TileObject getCurrentTileObject(){
		return menu.getCurrentTileObject();
	}

	private void resetSizeFields()
	{
		menu.widthField.setText(this.mapView.map.width+ "");
		menu.heightField.setText(this.mapView.map.height+"");
	}

	
	private class SizeUpdateListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			mapView.map.setSize(Integer.decode(menu.widthField.getText()), 
								Integer.decode(menu.heightField.getText()));
			resetSizeFields();
			mapView.update();
			mapView.repaint();
			scrollableMapView.revalidate();
			//scrollableMapView.invalidate();
		}
		
	}

	//performs specified tasks every time an action 
	//is performed by the component it’s linked to. 
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JFileChooser fc = new JFileChooser(); 
		
		if
		(e.getSource() == menu.save){
			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				saveTarget = fc.getSelectedFile();
				mapView.save(saveTarget);
				
			}
		}
		
		if
		(e.getSource() == menu.load)
		{
			if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
			{
				loadTarget = fc.getSelectedFile();
				mapView.load(loadTarget);
				mapView.validate();
				mapView.repaint();
				scrollableMapView.revalidate();
			}
		}
	}
	
	

}
