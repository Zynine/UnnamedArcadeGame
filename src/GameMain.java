import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class GameMain {

	public static JFrame GameFrame = new JFrame("Game");
	public static JTextArea text = new JTextArea();
	public static void main(String[] args) throws IOException {
			new Map("Map.map");
			GameFrame.setSize(300,100);
			GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GameFrame.setVisible(true);
			GameFrame.setAlwaysOnTop(true);
	}
	public static void Error(String message)
	{
	JFrame Error = new JFrame("Error");
	JLabel Errormessage = new JLabel(message);
	Error.add(Errormessage);
	System.out.println(Errormessage);
	}
}
