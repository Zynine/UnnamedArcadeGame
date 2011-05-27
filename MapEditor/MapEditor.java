import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
public class MapEditor {
	static File SaveFile;
	public static JButton Create;
	public static JTextArea MapX;
	public static JTextArea MapY;
	public static JTextArea MapTitle;
	public static JTextArea MapAuth;
	public static JTextArea Red = new JTextArea("Red Values, 0-255");
	public static JTextArea Blue = new JTextArea("Blue Values, 0-255");
	public static JTextArea Green = new JTextArea("Green Values, 0-255");
	public static JComboBox DataType = new JComboBox();
	public static JFrame NewBox;
	private static Action action = new Action();
	public static JButton New = new JButton("New");
	public static JButton Load = new JButton("Load");
	public static JButton Save = new JButton("Save");
	static JFileChooser SaveFileChooser = new JFileChooser();
	static JFrame MapEdit = new JFrame("Map Editor");
	static JButton[][] Pixels = new JButton[10][10];
	static String[][] MapData;
	static String MapHeader;
	public static void main(String[] args) {
	GridLayout layout=new GridLayout(2,3);
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
		GridLayout layout=new GridLayout(5,0);
		NewBox = new JFrame("New Map");
		NewBox.setLayout(layout);
		NewBox.setSize(500,200);
		MapTitle = new JTextArea("Map Title");
		MapTitle.setSize(100,200);
		MapAuth = new JTextArea("Map Author");
		MapAuth.setSize(100,200);
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
		NewBox.add(MapTitle, BorderLayout.CENTER);	
		NewBox.add(MapAuth, BorderLayout.CENTER);
		NewBox.add(MapX, BorderLayout.CENTER);	
		NewBox.add(MapY, BorderLayout.CENTER);
		NewBox.add(Create, BorderLayout.CENTER);
		Create.addActionListener(action);
		Save.addActionListener(action);
}
}
class Action implements ActionListener {
	public static int MapXi;
	public static int MapYi;
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
			MapEditor.MapHeader = MapEditor.MapTitle.getText()+"-"+MapEditor.MapAuth.getText()+"-"+MapEditor.MapX.getText()+"-"+MapEditor.MapY.getText();
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
					MapEditor.MapData = new String[MapXi][MapYi];
					if(MapEditor.MapData[i][j] == null)
					{
						MapEditor.MapData[i][j] = "0+0+0+0";
					}
					MapEditor.Pixels[i][j].setToolTipText("Pixel "+i+","+j+": Red - 0: Green - 0: Blue - 0: Data Type - 0");
					Map.add(MapEditor.Pixels[i][j]);
				}
			}
					Map.setVisible(true);
					Map.setLocation(300,300);
					MapEditor.NewBox.add(MapEditor.Save, BorderLayout.CENTER);
					MapEditor.MapEdit.setSize(500, 100);
					MapEditor.MapEdit.add(MapEditor.Save, BorderLayout.WEST);
					MapEditor.MapEdit.add(MapEditor.Red, BorderLayout.WEST);
					MapEditor.MapEdit.add(MapEditor.Green, BorderLayout.WEST);
					MapEditor.MapEdit.add(MapEditor.Blue, BorderLayout.WEST);
					MapEditor.MapEdit.add(MapEditor.DataType, BorderLayout.WEST);
					MapEditor.DataType.addItem("Open");
					MapEditor.DataType.addItem("Solid");
					MapEditor.DataType.addItem("Spawn");
					MapEditor.DataType.setMaximumRowCount(2);
					MapEditor.MapEdit.setAlwaysOnTop(true);
					MapEditor.MapEdit.setVisible(true);
		}
		if(e.getSource() == MapEditor.Save)
		{
			MapEditor.SaveFileChooser.setSize(400, 400);
			MapEditor.SaveFileChooser.setVisible(true);
			SaveClass saveclass = new SaveClass();
			int retval = MapEditor.SaveFileChooser.showSaveDialog(saveclass);
	        if (retval == JFileChooser.APPROVE_OPTION) {
	        	MapEditor.SaveFile = MapEditor.SaveFileChooser.getSelectedFile();
	        	try {
					printMap(MapEditor.SaveFile);
				} catch (IOException e1) {
					System.out.println("Error writing map.");
					e1.printStackTrace();
				}
	        }
		}
		if(id.getName() != null)
		{	
		String[] MapId;
		MapId = id.getName().split("-");
		int MapX = Integer.parseInt(MapId[0].replaceAll( "[^\\d]", "" ));
		int MapY = Integer.parseInt(MapId[1].replaceAll( "[^\\d]", "" ));
		MapEditor.Pixels[MapX][MapY].setBackground(new Color(Integer.parseInt(MapEditor.Red.getText().replaceAll( "[^\\d]", "" )), Integer.parseInt(MapEditor.Green.getText().replaceAll( "[^\\d]", "" )), Integer.parseInt(MapEditor.Blue.getText().replaceAll( "[^\\d]", "" ))));
		MapEditor.MapData[MapX][MapY] = MapEditor.Red.getText().replaceAll( "[^\\d]", "" )+"+"+MapEditor.Green.getText().replaceAll( "[^\\d]", "" )+"+"+MapEditor.Blue.getText().replaceAll( "[^\\d]", "" )+"+"+TranslateDataType(MapEditor.DataType.getSelectedItem());
		String[] temp = MapEditor.MapData[MapX][MapY].split("+");
		MapEditor.Pixels[MapX][MapY].setToolTipText("Pixel "+MapX+","+MapY+": Red - "+ temp[0]+": Green - "+temp[1]+": Blue - "+temp[2]+": Data Type - "+temp[3]);
		System.out.println("Pixel "+MapX+","+MapY+" has been set with "+ MapEditor.Red.getText().replaceAll( "[^\\d]", "" )+" and "+MapEditor.Green.getText().replaceAll( "[^\\d]", "" )+" and "+MapEditor.Blue.getText().replaceAll( "[^\\d]", "" )+" as type "+TranslateDataType(MapEditor.DataType.getSelectedItem())+"!");
		}
		if((Integer.parseInt(MapEditor.Red.getText().replaceAll( "[^\\d]", "" )) > 255) || (Integer.parseInt(MapEditor.Green.getText().replaceAll( "[^\\d]", "" )) > 255) || (Integer.parseInt(MapEditor.Blue.getText().replaceAll( "[^\\d]", "" )) > 255)|| (Integer.parseInt(MapEditor.Red.getText().replaceAll( "[^\\d]", "" )) < 0) || (Integer.parseInt(MapEditor.Green.getText().replaceAll( "[^\\d]", "" )) < 0) || (Integer.parseInt(MapEditor.Blue.getText().replaceAll( "[^\\d]", "" )) < 0))
		{
			System.out.println("Color values are out of range");
			
		}
	}
	public int TranslateDataType(Object Data)
	{
		if(Data == "Open")
		{
		return 0;
		}
		if(Data == "Solid")
		{
		return 1;
		}
		if(Data == "Spawn")
		{
		return 2;
		}
		return 0;
	}
	public void printMap(File Save) throws IOException
	{
		
		 BufferedWriter out
		   = new BufferedWriter(new FileWriter(Save));
		 out.write(MapEditor.MapHeader);
		 out.newLine();
		 for(int i=0;i<MapXi;i++) {
			 for(int j=0;j<MapYi;j++) {
				 if(MapEditor.MapData[i][j] == null)
				 {
					 MapEditor.MapData[i][j] = "0+0+0+0";
				 }
				 out.write(MapEditor.MapData[i][j]+"-"+i+"-"+j);
				 out.newLine();
			 }
		 }
		 out.close();
	}
}
class SaveClass extends JFrame {
	private static Action action = new Action();
    static JTextField FileName = new JTextField(15);
    
    SaveClass() {
       FileName.setEditable(false);
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("File:"));
        content.add(FileName);
        JMenuBar menubar  = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open...");
        openItem.addActionListener(action);

        menubar.add(fileMenu);
        fileMenu.add(openItem);


        this.setJMenuBar(menubar);
        this.setContentPane(content);
        this.setTitle("Count Words");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack(); 
        this.setLocationRelativeTo(null); 
}
    public static void main(String[] args) {
        JFrame window = new SaveClass();
        window.setVisible(true);
    }
    }
