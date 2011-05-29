import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;



public class Map extends JFrame {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int linenumber = 0;
static String Mapname = null;
String Author = null;
static int MapX = 0;
static int MapY = 0;
int MapSize = 0;
static String[][] MapPeices;
String[] coords = null;
static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public static JFrame Game;
static MapWindow Mapwindow;
int rect;
static String map;
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
		MapSize = MapX * MapY;
		System.out.println(linenumber + ":Map name is " + Mapname);
		System.out.println(linenumber + ":Map author is " + Author);
		System.out.println(linenumber + ":Map X length is " + MapX);
		System.out.println(linenumber + ":Map Y length is " + MapY);
		System.out.println(linenumber + ":Map size is " + MapSize);
	} else {
		String [] peices =line.split(":");
		coords = peices[1].split("-");
		MapPeices = new String[MapX][MapY];
		String ColorValues = peices[0];
		String[] ColorValue = peices[0].split("=");
		System.out.println(linenumber + ":Red=" + ColorValue[0] + ":Green=" + ColorValue[1] + ":Blue=" + ColorValue[2] + ":DataType="+ColorValue[3]+ " X=" + Integer.parseInt(coords[0]) + "Y=" + Integer.parseInt(coords[1]));
		}
		linenumber++;	
		if(linenumber == MapSize-1)
		{
	Mapwindow = new MapWindow();
	Game = new JFrame();
	Game.setTitle(Mapname+" by "+Author);
	Game.setBounds(0,0,screenSize.width, screenSize.height);
	Game.setSize(screenSize.width, screenSize.height);
	Game.add(Mapwindow);
	Game.setVisible(true);
	}
		}
	}
}
class MapWindow extends JPanel
{
	String[][] temp = new String[Map.MapX][Map.MapY];
	public MapWindow()
	{
	}
	public void main(String[] args)
{
		repaint();
}
	public String[][] GrabMap() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(Map.map));
		String line;
		reader.readLine();
		while((line = reader.readLine()) != null)
		{
			String[] parts = line.split(":");
			for(int i =0;i<Map.MapX;i++) {
				for(int j =0; i<Map.MapY;j++) {
					temp[i][j] = parts[0];
				}
		}
			return temp;
		}
		return null;
	}
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		for(int i =0;i<Map.MapX;i++) {
			for(int j =0; i<Map.MapY;j++) {
		String[][] MapData = null;
		try {
			MapData = GrabMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] Data = MapData[i][j].split("=");
		g2d.setColor(new Color(Integer.parseInt(Data[0]), Integer.parseInt(Data[1]), Integer.parseInt(Data[2])));
		g2d.fillRect(i*5, j*5, 5, 5);
		System.out.println("Loading chunck "+i+","+j);
			}
		}
	}
}
