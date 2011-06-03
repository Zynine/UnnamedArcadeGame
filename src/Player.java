import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;


public class Player extends JPanel {
int Health;
String Name;
int MoveSpeed;
int Damage;
int PosX,PosY;
static int[] DrawX;
static int[] DrawY;
int[] CurrentX, CurrentY;
static int[] SpawnCoords = new int[2];;
static java.awt.Polygon User;
static String KeepTrack;
	public Player(String n, int h, int d, int m, int x, int y, int[] PointX, int[] PointY)
	{
		this.Name = n;
		this.Health = h;
		this.Damage = d;
		this.MoveSpeed = m;
		this.PosX = x;
		this.PosY = y;
		this.DrawX = PointX;
		this.DrawY = PointY;
		DetectSpawn();
		this.repaint();
	}
	public static void Move(int x, int y, int time)
	{
		for(int i = 0;i<DrawX.length; i++)
		{
			DrawX[i]= DrawX[i]-(x*(Map.screenSize.width/10));
		}
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]-(y*(Map.screenSize.height/10));
		}
		
		Map.Mapwindow.repaint();
	}
	public void Fire()
	{
		
	}
	public void DetectSpawn()
	{
		for(int i =0;i<Map.MapX;i++)
		{
			for(int j =0;j<Map.MapY;j++)
			{
		String[] Data = MapWindow.MapColors[i][j].split("=");
		if(Integer.parseInt(Data[3]) == 2)
		{
			SpawnCoords[0] = (Map.screenSize.width/Map.MapX*j);
			SpawnCoords[1] = (Map.screenSize.height/Map.MapY*i);
			KeepTrack = i+","+j;
			this.repaint();
			}
		}
		}
		int SpawnX = SpawnCoords[0];
		int SpawnY = SpawnCoords[1];
		for(int i = 0;i<Player.DrawX.length; i++)
		{
			Player.DrawX[i]= Player.DrawX[i]+SpawnX;
		}
		for(int j = 0;j<Player.DrawY.length; j++)
		{
			Player.DrawY[j]= Player.DrawY[j]+SpawnY-(Map.screenSize.height/Map.MapY);
		}
		this.repaint();
	}
	public boolean CheckBlock()
	{
		
		return false;
	}
}
