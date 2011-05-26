import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MapEditor {
	public static JButton Create;
	public static JTextArea MapX;
	public static int MapXi;
	public static int MapYi;
	public static JTextArea MapY;
	public static JFrame NewBox;
	private static Action action = new Action();
	public static JButton[][] Pixels;
	public static JButton New = new JButton("New");
	public static JButton Load = new JButton("Load");
	public static JButton Save = new JButton("Save");
	static JFrame MapEdit = new JFrame("Map Editor");
	public static void main(String[] args) {
	GridLayout layout=new GridLayout(2,1);
	MapEdit.setSize(200,200);
	MapEdit.setLayout(layout);
	New.setSize(200,200);
	Load.setSize(200,200);
	MapEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	MapEdit.setVisible(true);
	New.setVisible(true);
	Load.setVisible(true);
    New.addActionListener(action);
	MapEdit.add(New, BorderLayout.CENTER);	
	MapEdit.add(Load, BorderLayout.CENTER);
	}
	public static void New()
	{
		GridLayout layout=new GridLayout(3,1);
		NewBox = new JFrame("New Map");
		NewBox.setLayout(layout);
		NewBox.setSize(500,200);
		MapX = new JTextArea("Map X value");
		MapX.setSize(100,200);
		MapY = new JTextArea("Map Y value");
		MapY.setSize(100,200);
		Create = new JButton("Create");
		Create.setSize(200,200);
		NewBox.setVisible(true);
		MapX.setVisible(true);
		MapY.setVisible(true);
		Create.setVisible(true);
		NewBox.add(MapX, BorderLayout.CENTER);	
		NewBox.add(MapY, BorderLayout.CENTER);
		NewBox.add(Create, BorderLayout.CENTER);
		Create.addActionListener(action);
}
}
class Action implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == MapEditor.New)
		{
			MapEditor.New();
		}
		if(e.getSource() == MapEditor.Create)
		{
			MapEditor.MapXi = Integer.parseInt(MapEditor.MapX.getText());
			MapEditor.MapYi = Integer.parseInt(MapEditor.MapY.getText());
			MapEditor.MapEdit.setSize(Integer.parseInt(MapEditor.MapX.getText())*10,Integer.parseInt(MapEditor.MapY.getText())*10);
			MapEditor.New.setVisible(false);
			MapEditor.Load.setVisible(false);
			for(int i=0;i<Integer.parseInt(MapEditor.MapX.getText());i++) {
				for(int j=0;j<Integer.parseInt(MapEditor.MapY.getText());j++) {
					MapEditor.Pixels[i][j]= new JButton(" ");
					MapEditor.Pixels[i][j].setSize(10,10);
					MapEditor.Pixels[i][j].addActionListener(this);
					MapEditor.Pixels[i][j].setVisible(true);
					MapEditor.MapEdit.setVisible(true);
					MapEditor.MapEdit.add(MapEditor.Pixels[i][j]);
				}
			}
			MapEditor.NewBox.setVisible(false);
		}
	}
}
