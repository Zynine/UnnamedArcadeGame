import javax.swing.JPanel;


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
static int[] SpawnCoords = new int[2];;
static int [] StandardX = {1,1,(Map.screenSize.width/10)-1,(Map.screenSize.width/10)-1};
static int [] StandardY = {1,(Map.screenSize.height/10)-1,(Map.screenSize.height/10)-1,1};
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
			DrawX[i]= DrawX[i]-(x*(Map.screenSize.width/10));
		}
		for(int j = 0;j<DrawY.length; j++)
		{
			DrawY[j]= DrawY[j]-(y*(Map.screenSize.height/10));
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
		String[] Data = MapWindow.MapColors[i][j].split("=");
		if(Integer.parseInt(Data[3]) == 2)
		{
			SpawnCoords[0] = (Map.screenSize.width/10*j);
			SpawnCoords[1] = (Map.screenSize.height/10*i);
			PosX = j;
			PosY = i;
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
			Player.DrawY[j]= Player.DrawY[j]+SpawnY-(Map.screenSize.height/10);
		}
		System.out.println("Spawning at "+SpawnX+","+SpawnY);
	}
	public boolean CheckBlock()
	{
		
		return false;
	}
}
