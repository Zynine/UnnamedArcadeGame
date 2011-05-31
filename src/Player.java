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
int[] DrawX,DrawY;
int[] CurrentX, CurrentY;
boolean Spawn= false;
java.awt.Polygon User;
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
		if(x ==0 ||y ==0)
		{
			Spawn = true;
		}
		Map.Game.repaint();
	}
	public void Move(int x, int y)
	{
		for(int i = 0;i<DrawX.length; i++)
		{
			DrawX[i]= DrawX[i]+x;
		}
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]+y;
		}
		Map.Game.repaint();
	}
	public void Fire()
	{
		
	}
	public int[] DetectSpawn()
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
			Spawn = true;
			return SpawnCoords;
			}
		}
		}
		return None;
		
	}
	public void paintComponent(Graphics g) // Draw Player
	{
		Graphics2D g2d = (Graphics2D)g;
		g2d.setColor(Color.BLUE);
		if(Spawn = true)
		{
			int[] Spawncoords = DetectSpawn();
			int SpawnX = Spawncoords[0];
			int SpawnY = Spawncoords[1];
			for(int i = 0;i<DrawX.length; i++)
			{
				DrawX[i]= DrawX[i]+(Map.screenSize.width/Map.MapX)+SpawnX;
			}
			for(int j = 0;j<DrawY.length; j++)
			{
				DrawY[j]= DrawY[j]+(Map.screenSize.width/Map.MapY)+SpawnY;
			}
			g2d.fillPolygon(DrawX ,DrawY, DrawX.length);
		}
		User = new Polygon(DrawX, DrawY, DrawX.length);
		g2d.setClip(User);
		g2d.fillPolygon(User);
		Map.Game.repaint();
	}
}
