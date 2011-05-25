import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class Map {
BufferedReader reader;
int linenumber = 0;
String Mapname = null;
String Author = null;
int MapX = 0;
int MapY = 0;
int MapSize = 0;
int[][] PublicMap = null;
String[] parts = null;
	public Map(String Map) throws IOException
	{
	FileInputStream in = null;
	in = new FileInputStream(Map);
	BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	String line = null;
	while((line = reader.readLine())!= null)
	{
	if(linenumber == 0)
	{
		parts = line.split("~");
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
		parts = line.split("~");
		int[][] MapPeices = new int[MapX][MapY];
		PublicMap = MapPeices.clone();
		MapPeices[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] = Integer.parseInt(parts[0]);
		System.out.println(linenumber + ":Data=" + MapPeices[Integer.parseInt(parts[1])][Integer.parseInt(parts[2])] + " X=" + Integer.parseInt(parts[1]) + "Y=" + Integer.parseInt(parts[1]));
	}
	linenumber++;
	}
	}
}
