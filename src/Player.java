import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class Player extends JPanel {
int Health;
String Name;
int MoveSpeed;
int Damage;
int PosX,PosY;
int[] DrawX,DrawY;
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
	}
	public void Fire()
	{
		
	}
	public void paintComponent(Graphics g) // Draw Player
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.fillPolygon(DrawX, DrawY, DrawX.length);
	}
}
