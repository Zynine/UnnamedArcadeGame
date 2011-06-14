import javax.swing.JPanel;
import java.lang.Math;
import org.lwjgl.opengl.Display;


public class Player{
int Health;
String Name;
int MoveSpeed;
int Damage;
static int PosX;
static int PosY;
static int[] DrawX;
static int[] DrawY;
int[] CurrentX, CurrentY;
static int[] SpawnCoords = new int[2];
static int [] StandardX = {0,0,35,35};
static int [] StandardY = {0,35,35,0};
static int r;
	public Player(String n, int h, int d, int m, int x, int y, int[] PointX, int[] PointY)
	{
		this.Name = n;
		this.Health = h;
		this.Damage = d;
		this.MoveSpeed = m;
		if (PointX == null || PointY == null)
		{
		Player.DrawX = StandardX;
		Player.DrawY = StandardY;
		} else {
		Player.DrawX = PointX;
		Player.DrawY = PointY;
		}
		if (x == 0 || y == 0)
		{
			DetectSpawn();
		} else {
		Player.PosX = x;
		Player.PosY = y;
		}
	}
	public static void Move(int x, int y, int time)
	{
		for(int i = 0;i<DrawX.length; i++)
		{
			DrawX[i]= DrawX[i]-(10*x);
		}
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]-(10*y);
		}
		if(DetectCollision())
		{
			for(int i = 0;i<DrawX.length; i++)
			{
				DrawX[i]= DrawX[i]+(10*x);
			}
			for(int j = 0;j<DrawY.length; j++)
			{
				DrawY[j]= DrawY[j]+(10*y);
			}
		}

	}
	public void Fire()
	{
		
	}
	public void DetectSpawn()
	{
		
		for(int i =0;i<10;i++)
		{
			for(int j =0;j<10;j++)
			{
		String[] Data = Map.MapColors[i][j].split("=");
		if(Integer.parseInt(Data[3]) == 2)
		{
			SpawnCoords[0] = (Display.getDisplayMode().getWidth()/10)*j;
			SpawnCoords[1] = Display.getDisplayMode().getHeight()-((Display.getDisplayMode().getHeight()/10)*(10-i));
			r = i;
			}
		}
		}
		for(int i = 0;i<Player.DrawX.length; i++)
		{
			Player.DrawX[i]= Player.DrawX[i]+SpawnCoords[0];
		}
		for(int j = 0;j<Player.DrawY.length; j++)
		{
			Player.DrawY[j]= Player.DrawY[j]+SpawnCoords[1];
		}
		System.out.println("Spawning at "+Player.DrawX[0]+","+Player.DrawY[0]);
	}
	public static int PlayerArea() {
		double S = (Player.DrawY[1]-Player.DrawY[0]);
		double temp1=(S*S)*Player.DrawX.length;
		double temp2=4*(Math.tan(Math.PI/Player.DrawX.length));
		int area = (int)temp1/(int)temp2;
		return area;
		
	}
	public static int[][] PlayerBox() {
		int[][] temp = new int[Display.getDisplayMode().getWidth()][Display.getDisplayMode().getHeight()];
		int side = Player.DrawY[1]-Player.DrawY[0];
		for(int i =0;i<Display.getDisplayMode().getWidth();i++) {
			for(int j =0;j<Display.getDisplayMode().getHeight();j++) {
				if((i > Player.DrawX[0] && i < Player.DrawX[0]+side) && (j > Player.DrawY[0] && j < Player.DrawY[0]+side))
				{
					temp[i][j] = 1;
				} else {
					temp[i][j] = 0;
				}
			}
		}
		return temp;
	}
	public static boolean DetectCollision() {
		int[][] temp = MapWindow.Mapdata();
		int[][] temp2 = PlayerBox();
		for(int i =0;i<Display.getDisplayMode().getWidth();i++) {
			for(int j =0;j<Display.getDisplayMode().getHeight();j++) {
			if((temp[i][j] == 1) && (temp2[i][j] == 1))
			{
				System.out.println("Collision detected at "+i+","+j);
				return true;
			}
			}
}
		return false;
	}
}
