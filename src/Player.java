import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Polygon;

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
static boolean Spawn= true;
static java.awt.Polygon User;
	public Player(String n, int h, int d, int m, int x, int y, int[] PointX, int[] PointY)
	{
		this.Name = n;
		this.Health = h;
		this.Damage = d;
		this.MoveSpeed = m;
		this.PosX = x;
		this.PosY = y;
		Player.DrawX = PointX;
		Player.DrawY = PointY;
		if(x ==0 ||y ==0)
		{
			Spawn = true;
		}
	}
	public static void Move(int x, int y)
	{
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]+y;
		}
		System.out.println("Player is being moved "+y+".");
	}
	public void Fire()
	{
		
	}
	public static int[] DetectSpawn()
	{
		int[] None= {0,0};
		int[] SpawnCoords = new int[2];
		for(int i =0;i<Map.MapX;i++)
		{
			for(int j =0;j<Map.MapY;j++)
			{
		String[] Data = MapWindow.MapColors[i][j].split("=");
		if(Integer.parseInt(Data[3]) == 2)
		{
			SpawnCoords[0] = (Map.screenSize.width/Map.MapX*j);
			SpawnCoords[1] = (Map.screenSize.height/Map.MapY*i);
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
	}
}
