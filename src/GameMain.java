import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.lwjgl.LWJGLException;


public class GameMain extends JFrame {
	private static final long serialVersionUID = 1L;
	public static JFrame GameFrame = new JFrame("Game");
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static Actions actions = new Actions();
	static int width = GameMain.screenSize.width;
	static int height = GameMain.screenSize.height;
	static Mouse mouse = new Mouse();
	static Menu menu = new Menu();
	static SPMenu SPmenu = new SPMenu();
	static MPMenu MPmenu = new MPMenu();
	static OMenu Omenu = new OMenu();
	static JComboBox MapSelect;
	static JTextArea NameBox;
	static boolean InMenu,InSPMenu,InMPMenu,InOMenu;
	static Collection Maps;
	static File MapDir;
    static File[] MapFiles;
    static int NumOfMaps;
    static String Name;
	public static void main(String[] args) throws IOException {
			GameFrame.setTitle("Möbius");
			GameFrame.setBounds(0,0,screenSize.width, screenSize.height);
			GameFrame.setSize(screenSize.width, screenSize.height);
			GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GameFrame.add(MPmenu);
			GameFrame.add(SPmenu);
			GameFrame.add(Omenu);
			GameFrame.add(menu);
			menu.addMouseListener(mouse);
			GameFrame.setVisible(true);
			InMenu = true;
	}
	public static void Error(String message)
	{
	JFrame Error = new JFrame("Error");
	JLabel Errormessage = new JLabel(message);
	Error.setSize(200,600);
	Error.setVisible(true);
	Error.add(Errormessage);
	System.out.println(Errormessage);
	}
	public static void CreateMapList()
	{
		MapDir = new File("./maps");
		MapFiles = MapDir.listFiles();
		if(MapFiles != null) {
		for(int i =0; i<MapFiles.length;i++)
		{
			if(MapFiles[i].getName().endsWith(".map"))
					{
				GameMain.MapSelect.addItem(MapFiles[i]);
				NumOfMaps++;
					}
		}
		GameMain.MapSelect.setMaximumRowCount(NumOfMaps);
	}
}
}
class Actions implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e) {
	}
}

class Mouse implements MouseListener
{
	static int x,y;
	public void mouseClicked(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		if(Within(x,y,GameMain.width/2-400, GameMain.height/2+100,200,50) && GameMain.InMenu)//SinglePlayer
		{
			GameMain.GameFrame.remove(GameMain.menu);
			GameMain.MapSelect = new JComboBox();
			GameMain.NameBox = new JTextArea("Name");
			GameMain.NameBox.setSize(300, 25);
			GameMain.MapSelect.setSize(300, 25);
			GameMain.MapSelect.setLocation(GameMain.width/2-200, GameMain.height/2-100);
			GameMain.NameBox.setLocation(GameMain.width/2-200, GameMain.height/2);
			GameMain.MapSelect.setSelectedIndex(-1);
			GameMain.CreateMapList();
			if(GameMain.NumOfMaps != 0)
			{
				GameMain.MapSelect.setSelectedIndex(0);
			} else {
				GameMain.MapSelect.addItem("You have no maps");
				GameMain.MapSelect.setMaximumRowCount(0);
			}
			GameMain.GameFrame.add(GameMain.NameBox);
			GameMain.GameFrame.add(GameMain.MapSelect);
			GameMain.GameFrame.add(GameMain.SPmenu);
			GameMain.SPmenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to SP");//From MainMenu to SinglePlayer - Debug
			GameMain.SPmenu.setVisible(true);
			GameMain.menu.setVisible(false);
			GameMain.MapSelect.setVisible(true);
			GameMain.InMenu=false;
			GameMain.InSPMenu=true;
		}
		if(Within(x,y,GameMain.width/2-100, GameMain.height/2+100, 200, 50) && GameMain.InMenu)//MultiPlayer
		{
			GameMain.GameFrame.remove(GameMain.menu);
			GameMain.GameFrame.add(GameMain.MPmenu);
			GameMain.MPmenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to MP");//From MainMenu to Multiplayer - Debug
			GameMain.MPmenu.setVisible(true);
			GameMain.menu.setVisible(false);
			GameMain.InMenu=false;
			GameMain.InMPMenu=true;
		}
		if(Within(x,y,GameMain.width/2+200, GameMain.height/2+100, 200, 50) && GameMain.InMenu)//Options
		{
			GameMain.GameFrame.remove(GameMain.menu);
			GameMain.GameFrame.add(GameMain.Omenu);
			GameMain.Omenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to O");//From MainMenu to Options - Debug
			GameMain.Omenu.setVisible(true);
			GameMain.menu.setVisible(false);
			GameMain.InMenu=false;
			GameMain.InOMenu=true;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2-100, 200, 50) && GameMain.InMPMenu)//MultiPlayerReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.MPmenu);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From MP to MM");//From Multiplayer Menu to MainMenu - Debug
			GameMain.MPmenu.setVisible(false);
			GameMain.menu.setVisible(true);
			GameMain.InMenu=true;
			GameMain.InMPMenu=false;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2+200, 200, 50) && GameMain.InSPMenu)//SingePlayerReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.SPmenu);
			GameMain.MapSelect.setVisible(false);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From SP to MM");//From Singleplayer Menu to MainMenu - Debug
			GameMain.SPmenu.setVisible(false);
			GameMain.menu.setVisible(true);
			GameMain.InMenu=true;
			GameMain.InSPMenu=false;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2-100, 200, 50) && GameMain.InOMenu)//OptionsReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.Omenu);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From O to MM");//From Options Menu to MainMenu - Debug
			GameMain.Omenu.setVisible(false);
			GameMain.menu.setVisible(true);
			GameMain.InMenu=true;
			GameMain.InOMenu=false;
		}
		if(Within(x,y,GameMain.width/2+150, GameMain.height/2-100,100,25) && GameMain.InSPMenu)//Load Map
		{
			System.out.println("Loading Map "+GameMain.MapSelect.getSelectedItem());//Loading which map again?
			try {
				new Map(GameMain.MapSelect.getSelectedItem().toString());
				GameMain.GameFrame.setVisible(false);
			} catch (NumberFormatException e1) {
				System.out.println("Some sort of internal error. Must. Fix.");
				e1.printStackTrace();
			} catch (IOException e2) {
				System.out.println("File not found? Check if it really is there.");
				e2.printStackTrace();
			} catch (InterruptedException e3) {
				System.out.println("Map stopped loading, internal error. Try again.");
				e3.printStackTrace();
			} catch (LWJGLException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			GameMain.Name = GameMain.NameBox.getText();
			GameMain.Omenu.setVisible(false);
			GameMain.menu.setVisible(false);
			GameMain.InMenu=false;
			GameMain.InSPMenu=false;
			GameMain.GameFrame.setVisible(false);
		}
		System.out.println("x: " +x+" y: "+y);
		GameMain.GameFrame.validate();
}
	public boolean Within(int x, int y, int xx,int yy,int width,int height)
	{
		if((x < xx+width && !(x < xx)) && (y<yy+height && !(y<yy)&&(!(x<xx) && !(y<yy))))
		{
			return true;
		}
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
class Menu extends JPanel
{
	public void main(String args[])
	{
		repaint();
	}
	public void paintComponent(Graphics g) //Draw Main Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("Möbius", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2-400, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("SinglePlayer", GameMain.width/2-340, GameMain.height/2+125);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2-100, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("MultiPlayer", GameMain.width/2-40, GameMain.height/2+125);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2+200, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Options", GameMain.width/2+280, GameMain.height/2+125);
	}
	
}
class SPMenu extends JPanel
{
	public void main(String args[])
	{
		repaint();
	}
	public void paintComponent(Graphics g) // Draw SinglePlayer Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("Möbius Test Singleplayer Menu", GameMain.width/2, GameMain.height/2-225);
		g2d.setColor(Color.white);
		g2d.fillRect(GameMain.width/2-200, GameMain.height/2-100, 300, 25);
		g2d.fillRect(GameMain.width/2-200, GameMain.height/2,300,25);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2+150, GameMain.height/2-100, 100, 25);
		g2d.setColor(Color.black);
		g2d.drawString("Load Map", GameMain.width/2+150, GameMain.height/2-85);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2+200, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2, GameMain.height/2+225);
	}
	
}
class MPMenu extends JPanel
{
	public void main(String args[])
	{
		repaint();
	}
	public void paintComponent(Graphics g) // Draw Multiplayer menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("Möbius Test Multiplayer Menu", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2-100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2+60, GameMain.height/2-75);
	}
	
}
class OMenu extends JPanel
{
	public void main(String args[])
	{
		repaint();
	}
	public void paintComponent(Graphics g) //Draw Options Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("Möbius Test Option Menu", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2-100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2+60, GameMain.height/2-75);
	}
	
}

 