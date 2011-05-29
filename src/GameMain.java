import java.io.IOException;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
	static boolean InMenu,InSPMenu,InMPMenu,InOMenu;
	public static void main(String[] args) throws IOException {
			GameFrame.setTitle("GeoBalance");
			GameFrame.setBounds(0,0,screenSize.width, screenSize.height);
			GameFrame.setSize(screenSize.width, screenSize.height);
			GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GameFrame.add(menu);
			menu.addMouseListener(mouse);
			menu.setVisible(true);
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
			GameMain.GameFrame.add(GameMain.SPmenu);
			GameMain.SPmenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to SP");//From MainMenu to SinglePlayer - Debug
			GameMain.InMenu=false;
			GameMain.InSPMenu=true;
		}
		if(Within(x,y,GameMain.width/2-100, GameMain.height/2+100, 200, 50) && GameMain.InMenu)//MultiPlayer
		{
			GameMain.GameFrame.remove(GameMain.menu);
			GameMain.GameFrame.add(GameMain.MPmenu);
			GameMain.MPmenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to MP");//From MainMenu to Multiplayer - Debug
			GameMain.InMenu=false;
			GameMain.InMPMenu=true;
		}
		if(Within(x,y,GameMain.width/2+200, GameMain.height/2+100, 200, 50) && GameMain.InMenu)//Options
		{
			GameMain.GameFrame.remove(GameMain.menu);
			GameMain.GameFrame.add(GameMain.Omenu);
			GameMain.Omenu.addMouseListener(GameMain.mouse);
			System.out.println("From MM to O");//From MainMenu to Options - Debug
			GameMain.InMenu=false;
			GameMain.InOMenu=true;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2+100, 200, 50) && GameMain.InMPMenu)//MultiPlayerReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.MPmenu);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From MP to MM");//From Multiplayer Menu to MainMenu - Debug
			GameMain.InMenu=true;
			GameMain.InMPMenu=false;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2+100, 200, 50) && GameMain.InSPMenu)//SingePlayerReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.SPmenu);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From SP to MM");//From Singleplayer Menu to MainMenu - Debug
			GameMain.InMenu=true;
			GameMain.InSPMenu=false;
		}
		if(Within(x,y,GameMain.width/2, GameMain.height/2+100, 200, 50) && GameMain.InOMenu)//OptionsReturn - Test
		{
			GameMain.GameFrame.remove(GameMain.Omenu);
			GameMain.GameFrame.add(GameMain.menu);
			GameMain.menu.addMouseListener(GameMain.mouse);
			System.out.println("From O to MM");//From Options Menu to MainMenu - Debug
			GameMain.InMenu=true;
			GameMain.InOMenu=false;
		}
		System.out.println("x: " +x+" y: "+y);
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
	}
	public void paintComponent(Graphics g) //Draw Main Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("GeoBalance", GameMain.width/2, GameMain.height/2-200);
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
	}
	public void paintComponent(Graphics g) // Draw SinglePlayer Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.clearRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("GeoBalance Test Singleplayer Menu", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2+40, GameMain.height/2+125);
	}
	
}
class MPMenu extends JPanel
{
	public void main(String args[])
	{
	}
	public void paintComponent(Graphics g) // Draw Multiplayer menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.clearRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("GeoBalance Test Multiplayer Menu", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2+40, GameMain.height/2+125);
	}
	
}
class OMenu extends JPanel
{
	public void main(String args[])
	{
	}
	public void paintComponent(Graphics g) //Draw Options Menu
	{
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		g2d.clearRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, GameMain.width, GameMain.height);
		g2d.setColor(Color.white);
		g2d.drawString("GeoBalance Test Option Menu", GameMain.width/2, GameMain.height/2-200);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(GameMain.width/2, GameMain.height/2+100, 200, 50);
		g2d.setColor(Color.black);
		g2d.drawString("Back To Main menu", GameMain.width/2+40, GameMain.height/2+125);
	}
	
}

 