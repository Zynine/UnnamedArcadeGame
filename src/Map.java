import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;



public class Map extends JFrame  {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int linenumber = 0;
static String Mapname = null;
String Author = null;
static int MapX = 0;
static int MapY = 0;
static int MapSize = 0;
String[] coords = null;
static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public static JFrame Game;
static MapWindow Mapwindow;
int rect;
static String map;
Controls controls = new Controls();
	public Map(String Map) throws NumberFormatException, IOException, InterruptedException
	{
		this.map = Map;
	BufferedReader reader = new BufferedReader(new FileReader(Map));
	String line;
	while((line = reader.readLine()) != null)
	{
	if(linenumber == 0)
	{
		String[] peices = line.split("-");
		Mapname = peices[0];
		Author = peices[1];
		MapX = Integer.parseInt(peices[2]);
		MapY = Integer.parseInt(peices[3]);
		MapWindow.MapColors = new String[MapX][MapY];
		MapSize = (MapX) * (MapY);
		System.out.println(linenumber + ":Map name is " + Mapname);
		System.out.println(linenumber + ":Map author is " + Author);
		System.out.println(linenumber + ":Map X length is " + MapX);
		System.out.println(linenumber + ":Map Y length is " + MapY);
		System.out.println(linenumber + ":Map size is " + MapSize);
	} else {
		String [] peices =line.split(":");
		coords = peices[1].split("-");
		String[] ColorValue = peices[0].split("=");
		MapWindow.MapColors[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])] = ColorValue[0]+"="+ColorValue[1]+"="+ColorValue[2]+"="+ColorValue[3];
		System.out.println(linenumber + ":Red=" + ColorValue[0] + ":Green=" + ColorValue[1] + ":Blue=" + ColorValue[2] + ":DataType="+ColorValue[3]+ " X=" + Integer.parseInt(coords[0]) + "Y=" + Integer.parseInt(coords[1]));
		}
		linenumber++;	
		}
	if(MapWindow.MapColors!= null)
	{
Game = new JFrame();
Game.setTitle(Mapname+" by "+Author);
Game.setBounds(0,0,screenSize.width, screenSize.height);
Game.setSize(screenSize.width, screenSize.height);
Game.add(new Player(GameMain.Name,0,0,0,0,0,GameMain.StandardX,GameMain.StandardY));
Mapwindow = new MapWindow();
Mapwindow.setBounds(0,0,screenSize.width, screenSize.height);
Mapwindow.setSize(screenSize.width, screenSize.height);
Mapwindow.addKeyListener(controls);
Game.add(Mapwindow);
Mapwindow.setVisible(true);
Game.setVisible(true);
}
	}
}
class MapWindow extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int linen;
	static String[][] MapColors;
	public MapWindow()
	{
	}
	public void main(String[] args)
{
		repaint();
}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		//Map
		g2d.setColor(Color.black);
		for(int i =0;i<Map.MapX;i++) {
			for(int j =0; j<Map.MapY;j++) {
				String[] Data = MapWindow.MapColors[i][j].split("=");
				g2d.setColor(new Color(Integer.parseInt(Data[0]),Integer.parseInt(Data[1]),Integer.parseInt(Data[2])));
				g2d.fillRect((Map.screenSize.width/(Map.MapX)*j),(Map.screenSize.height/(Map.MapY)*i), Map.screenSize.width/(Map.MapX), Map.screenSize.height/(Map.MapY));
				//System.out.println("Loading chunck "+i+","+j+" with :"+Data[0]+","+Data[1]+","+Data[2]);
		}
		}
		g2d.setColor(Color.BLUE);
		g2d.setClip(Player.User);
		g2d.fillPolygon(Player.User);
		repaint();
		}
}

class Controls implements KeyListener
{
	@Override
	public void keyPressed(KeyEvent e2) {
			if(e2.getKeyChar() == KeyEvent.VK_W)
			{
				Player.Move(0, 100);
				Map.Mapwindow.repaint();
			}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
