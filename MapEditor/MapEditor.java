import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class MapEditor {
	public static JButton Create;
	public static JTextArea MapX;
	public static JTextArea MapY;
	public static JFrame NewBox;
	private static Action action = new Action();
	public static JButton New = new JButton("New");
	public static JButton Load = new JButton("Load");
	public static JButton Save = new JButton("Save");
	static JFrame MapEdit = new JFrame("Map Editor");
	static JButton[][] Pixels = new JButton[10][10];
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
	public static int MapXi;
	public static int MapYi;
	Color black;
	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		JButton id = (JButton) e.getSource();
		if(e.getSource() == MapEditor.New)
		{
			MapEditor.New();
		}
		if(e.getSource() == MapEditor.Create)
		{
			JFrame Map = new JFrame("Map Editor");
			MapXi = Integer.parseInt(MapEditor.MapX.getText());
			MapYi = Integer.parseInt(MapEditor.MapY.getText());
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			if(screenSize.width <= Integer.parseInt(MapEditor.MapX.getText())* 10 || screenSize.height <= Integer.parseInt(MapEditor.MapY.getText())* 10)
			{
				Map.setBounds(0,0,screenSize.width, screenSize.height);
				Map.setSize(screenSize.width, screenSize.height);
			} else {
				Map.setSize(Integer.parseInt(MapEditor.MapX.getText())*10,Integer.parseInt(MapEditor.MapY.getText())*10);
				Map.setBounds(0,0,Integer.parseInt(MapEditor.MapX.getText())* 10, Integer.parseInt(MapEditor.MapY.getText())* 10);
			}
        	Map.setLayout(new GridLayout(Integer.parseInt(MapEditor.MapX.getText()),Integer.parseInt(MapEditor.MapY.getText())));
        	MapEditor.Pixels=new JButton[Integer.parseInt(MapEditor.MapX.getText())][Integer.parseInt(MapEditor.MapY.getText())];
			for(int i=0;i<Integer.parseInt(MapEditor.MapX.getText());i++) {
				for(int j=0;j<Integer.parseInt(MapEditor.MapY.getText());j++) {
					MapEditor.Pixels[i][j]= new JButton();
					MapEditor.Pixels[i][j].setLocation(Integer.parseInt(MapEditor.MapX.getText())*5,Integer.parseInt(MapEditor.MapY.getText())*5);
					MapEditor.Pixels[i][j].addActionListener(this);
					MapEditor.Pixels[i][j].setVisible(true);
					MapEditor.Pixels[i][j].setName(i + "-" + j);
					Map.setVisible(true);
					Map.add(MapEditor.Pixels[i][j]);
				}
			}
		}
		if(id.getName() != null)
		{
		String[] MapId;
		MapId = id.getName().split("-");
		int MapX = Integer.parseInt(MapId[0]);
		int MapY = Integer.parseInt(MapId[1]);
		MapEditor.Pixels[MapX][MapY].setBackground(black);
		System.out.println("Pixel "+MapX+","+MapY+" has been activated!");
		}
	}
}
