import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.DoubleBuffer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class Map extends JFrame  {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
int linenumber = 0;
static String Mapname = null;
String Author = null;
static int MapX = 0;
static int MapY = 0;
static int MapSize = 0;
String[] coords = null;
static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
public static JFrame Game;
static MapWindow Mapwindow;
int rect;
static String map;
static Player player;
static String[][] MapColors;
	public Map(String Map) throws NumberFormatException, IOException, InterruptedException, LWJGLException
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
		MapColors = new String[MapX][MapY];
		MapSize = (MapX) * (MapY);
		System.out.println(linenumber + ":Map name is " + Mapname);
		System.out.println(linenumber + ":Map author is " + Author);
		System.out.println(linenumber + ":Map X length is " + MapX);
		System.out.println(linenumber + ":Map Y length is " + MapY);
		System.out.println(linenumber + ":Map size is " + MapSize);
	} else {
		String [] peices =line.split(":");
		coords = peices[1].split("-");
		String[] ColorValue = peices[0].split("=");
		MapColors[Integer.parseInt(coords[0])][Integer.parseInt(coords[1])] = ColorValue[0]+"="+ColorValue[1]+"="+ColorValue[2]+"="+ColorValue[3];
		//System.out.println(linenumber + ":Red=" + ColorValue[0] + ":Green=" + ColorValue[1] + ":Blue=" + ColorValue[2] + ":DataType="+ColorValue[3]+ " X=" + Integer.parseInt(coords[0]) + "Y=" + Integer.parseInt(coords[1]));
		}
		linenumber++;	
		}
	if(MapColors!= null)
	{
Mapwindow = new MapWindow();
}
	}
}
class MapWindow extends JPanel
{
	static int fps;
	static long lastFPS;
	static boolean vsync;
	static long lastFrame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int linen;
	static DoubleBuffer eq;
	public MapWindow() throws LWJGLException
	{
		Display.setDisplayMode(new DisplayMode(800, 600));
		Display.create();
		Map.player = new Player(GameMain.Name,100,100,100,0,0,null,null);
		initGL(); // init OpenGL
		getDelta(); // call once before loop to initialise lastFrame
		lastFPS = getTime(); // call before loop to initialise fps timer
		 
		while (!Display.isCloseRequested()) {
			int delta = getDelta();
			update(delta);
			renderGL();
			if(Player.DrawX != null)
			{
			UpdatePlayer();
			}
			Display.update();
			Display.sync(120); // cap fps to 60fps
		}
 
		Display.destroy();
	}

 
	public static void update(int delta) {
		if (Mouse.isButtonDown(0)) {
			System.out.println(Mouse.getX()+","+Mouse.getY());
		}
		while (Keyboard.next()) {
		    if (Keyboard.getEventKeyState()) {
		        if (Keyboard.getEventKey() == Keyboard.KEY_F) {
		        	setDisplayMode(800, 600, !Display.isFullscreen());
		        }
		        else if (Keyboard.getEventKey() == Keyboard.KEY_V) {
		        	vsync = !vsync;
		        	Display.setVSyncEnabled(vsync);
		        }
		    		if(Keyboard.getEventKey() == Keyboard.KEY_W)
		    		{
		    			Player.Move(0,1,0);
		    			//Player.PosX = Player.PosX+1;
		    			System.out.println("Moved from "+(Player.PosX-1)+","+Player.PosY+" to "+Player.PosX+","+Player.PosY);		    		}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_S)
		    		{
		    			Player.Move(0,-1,0);
		    			//Player.PosX = Player.PosX-1;
		    			System.out.println("Moved from "+(Player.PosX+1)+","+Player.PosY+" to "+Player.PosX+","+Player.PosY);
		    		}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_D)
		    		{
		    			Player.Move(-1,0,0);
		    			//Player.PosY = Player.PosY+1;
		    			System.out.println("Moved from "+Player.PosX+","+(Player.PosY-1)+" to "+Player.PosX+","+Player.PosY);
		    			}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_A)
		    		{
		    			Player.Move(1,0,0);
		    			//Player.PosY = Player.PosY-1;
		    			System.out.println("Moved from "+Player.PosX+","+(Player.PosY+1)+" to "+Player.PosX+","+Player.PosY);
		    		}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_J)
		    		{
		    			System.out.println("Player is at "+Player.DrawX[0]+","+Player.DrawY[0]);
		    		}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_K)
		    		{
		    			System.out.println("Spawn is at "+Player.SpawnCoords[0]+","+Player.SpawnCoords[1]);
		    		}
		    		if(Keyboard.getEventKey() == Keyboard.KEY_I)
		    		{
		    			System.out.println(Player.r+"   "+Display.getDisplayMode().getWidth()/10+","+Display.getDisplayMode().getHeight()/10);
		    		}
		    		System.out.println("Key that was hit was "+Keyboard.getEventKey());
		    }
		}

		updateFPS(); // update FPS Counter
	}
 
	/**
	 * Set the display mode to be used 
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public static void setDisplayMode(int width, int height, boolean fullscreen) {

		// return if requested DisplayMode is already set
                if ((Display.getDisplayMode().getWidth() == width) && 
			(Display.getDisplayMode().getHeight() == height) && 
			(Display.isFullscreen() == fullscreen)) {
			return;
		}
		
		try {
			DisplayMode targetDisplayMode = null;
			
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				
				for (int i=0;i<modes.length;i++) {
					DisplayMode current = modes[i];
					
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against the 
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
						    (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
			} else {
				targetDisplayMode = new DisplayMode(width,height);
			}
			
			if (targetDisplayMode == null) {
				System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
				return;
			}

			Display.setDisplayMode(targetDisplayMode);
			Display.setFullscreen(fullscreen);
			
		} catch (LWJGLException e) {
			System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
		}
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public static int getDelta() {
	    long time = getTime();
	    int delta = (int) (time - lastFrame);
	    lastFrame = time;
 
	    return delta;
	}
 
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public static long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
 
	/**
	 * Calculate the FPS and set it in the title bar
	 */
	public static void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
 
	public static void UpdatePlayer() {
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_QUADS);
		for(int i = 0;i<Player.DrawX.length; i++)
		{
				GL11.glColor3d(100 ,0 ,100);
				GL11.glVertex2d(Player.DrawX[i],Player.DrawY[i]);
		}
		GL11.glEnd();
		GL11.glPopMatrix();
	}
	public static void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 800, 600, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
 
	public static void renderGL() {
		// Clear The Screen And The Depth Buffer
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
 
		// R,G,B,A Set The Color To Blue One Time Only
		GL11.glColor3d(0, 0, 0);

		// draw quad
		for(int i =0;i<10;i++) {
			for(int j =0; j<10;j++) {
		// R,G,B,A Set The Color To Blue One Time Only
				String[] Data = Map.MapColors[i][j].split("=");
				//System.out.println("Loading chunck "+i+","+j+" with :"+Data[0]+","+Data[1]+","+Data[2]);
				GL11.glPushMatrix();
				int r = Integer.parseInt(Data[0]);
				int g = Integer.parseInt(Data[1]);
				int b = Integer.parseInt(Data[2]);
				GL11.glColor3d( (float)r/255.0, (float)g/255.0, (float)b/255.0);
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex3f((Display.getDisplayMode().getWidth()/10)*j, (Display.getDisplayMode().getHeight()/10)*i,0);
				GL11.glVertex3f((Display.getDisplayMode().getWidth()/10)*j, (Display.getDisplayMode().getHeight()/10)*i+100,0);
				GL11.glVertex3f((Display.getDisplayMode().getWidth()/10)*j+100, (Display.getDisplayMode().getHeight()/10)*i+100,0);
				GL11.glVertex3f((Display.getDisplayMode().getWidth()/10)*j+100, (Display.getDisplayMode().getHeight()/10)*i,0);
			GL11.glEnd();
		GL11.glPopMatrix();
		}
	}
	}

}
