import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;



public class Map extends JFrame {
int linenumber = 0;
String Mapname = null;
String Author = null;
static int MapX = 0;
static int MapY = 0;
int MapSize = 0;
int[][] PublicMap = null;
String[] parts = null;
int rect;
	public Map(String Map) throws NumberFormatException, IOException
	{
	BufferedReader reader = new BufferedReader(new FileReader(Map));
	String line;
	while((line = reader.readLine()) != null)
	{
	if(linenumber == 0)
	{
		parts = line.split("-");
		Mapname = parts[0];
		Author = parts[1];
		MapX = Integer.parseInt(parts[2]);
		MapY = Integer.parseInt(parts[3]);
		MapSize = MapX * MapY;
		System.out.println(linenumber + ":Map name is " + Mapname);
		System.out.println(linenumber + ":Map author is " + Author);
		System.out.println(linenumber + ":Map X length is " + MapX);
		System.out.println(linenumber + ":Map Y length is " + MapY);
		System.out.println(linenumber + ":Map size is " + MapSize);
	} else {
		parts = line.split("-");
		int[][] MapPeices = new int[MapX][MapY];
		PublicMap = MapPeices.clone();
		MapPeices[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] = Integer.parseInt(parts[0]);
		System.out.println(linenumber + ":Data=" + MapPeices[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] + " X=" + Integer.parseInt(parts[1]) + "Y=" + Integer.parseInt(parts[1]));
		}
		linenumber++;	
	}
	}
	  public void paintComponent(Graphics g) {
		JFrame Game = new JFrame("Game");
		for(int i=0;i<MapX;i++) {
			for(int j=0;j<MapY;j++) {
		g.drawRect(i*5, j*5, 5, 5);
		g.fillRect(i*5, j*5, 4, 4);
			}
		}
		Game.setSize(MapX*5,MapY*5);
		Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.setVisible(true);
		Game.setAlwaysOnTop(true);
		}
}
