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
<<<<<<< HEAD
static int[] SpawnCoords = new int[2];;
static java.awt.Polygon User;
static String KeepTrack;
=======
static boolean Spawn= true;
static java.awt.Polygon User;
>>>>>>> ed6b8ca33841fec2b41305f2f8ac1d51fb88e19e
	public Player(String n, int h, int d, int m, int x, int y, int[] PointX, int[] PointY)
	{
		this.Name = n;
		this.Health = h;
		this.Damage = d;
		this.MoveSpeed = m;
		this.PosX = x;
		this.PosY = y;
<<<<<<< HEAD
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
=======
		Player.DrawX = PointX;
		Player.DrawY = PointY;
		if(x ==0 ||y ==0)
		{
			Spawn = true;
		}
	}
	public static void Move(int x, int y)
	{
>>>>>>> ed6b8ca33841fec2b41305f2f8ac1d51fb88e19e
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]-(y*(Map.screenSize.height/10));
		}
<<<<<<< HEAD
		
		Map.Mapwindow.repaint();
=======
		System.out.println("Player is being moved "+y+".");
>>>>>>> ed6b8ca33841fec2b41305f2f8ac1d51fb88e19e
	}
	public void Fire()
	{
		
	}
<<<<<<< HEAD
	public void DetectSpawn()
=======
	public static int[] DetectSpawn()
>>>>>>> ed6b8ca33841fec2b41305f2f8ac1d51fb88e19e
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
<<<<<<< HEAD
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
=======
			return SpawnCoords;
			}
		}
		}
		return None;
	}
	public static void Spawn()
	{
		int[] Spawncoords = Player.DetectSpawn();
		int SpawnX = Spawncoords[0];
		int SpawnY = Spawncoords[1];
		for(int i = 0;i<Player.DrawX.length; i++)
		{
			Player.DrawX[i]= Player.DrawX[i]+(Map.screenSize.width/Map.MapX)+SpawnX;
		}
		for(int j = 0;j<Player.DrawY.length; j++)
		{
			Player.DrawY[j]= Player.DrawY[j]+(Map.screenSize.width/Map.MapY)+SpawnY;
		}
		Player.User = new Polygon(Player.DrawX, Player.DrawY, Player.DrawX.length);
		System.out.println("The User is written at:"+DrawX+","+DrawY+" with "+DrawX.length+" points.");
>>>>>>> ed6b8ca33841fec2b41305f2f8ac1d51fb88e19e
	}
}
