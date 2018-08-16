package note_pro;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.Scanner;

//import javax.print.DocFlavor.URL;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.text.DefaultEditorKit;

//import notweprac.textEditor.dateTimeMenuItemListener;

public class NotepadMain extends JPanel {

	public static String[] s;

	public NotepadMain() 
	{
		setLookandFeel();

		setMenuInformation();

		setFrameInformation();
	}

	public static void main(String[] args) 
	{
		new NotepadMain();
	}


	protected void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		menuBar.setSize(getWidth(),22);
		menuBar.setLocation(0, 0);
		
		//textArea.setPreferredSize(new Dimension(getWidth(), getHeight()-20));
		areaScrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));

		areaScrollPane.setLocation(0,20);
	}

	public void setFrameInformation() {

		frame = new JFrame();
		frame.setSize(700, 400);
		
		frame.addWindowStateListener(new WindowStateListener() {

			@Override
			public void windowStateChanged(WindowEvent arg0) {
				// TODO Auto-generated method stub

				areaScrollPane.setPreferredSize(new Dimension(frame.getSize()));
			}
		});

		frame.setTitle("Notepad");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}

	private void setMenuInformation() {
		// TODO Auto-generated method stub

		actionHandler = new ActionHandler(this);

		textArea = new JTextArea();
		textArea.setFont(new Font("Consolas", Font.PLAIN, 20));
		textArea.setDragEnabled(true);


		areaScrollPane = new JScrollPane(textArea);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		areaScrollPane.setPreferredSize(new Dimension(getWidth(), getHeight()));

		add(areaScrollPane);//add ki 

		file = new JMenu();
		file.setText("File");
		file.setMnemonic('F');

		edit = new JMenu();
		edit.setText("Edit");

		format = new JMenu();
		format.setText("Format");

		view = new JMenu();
		view.setText("View");

		help = new JMenu();
		help.setText("Help");

		menuBar = new JMenuBar();
		menuBar.add(this.file);

		menuBar.add(this.edit);

		menuBar.add(this.help);

		newFile = new JMenuItem();
		newFile.setText("New");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		newFile.addActionListener(actionHandler);
		//newFile.setMnemonic('N');
		file.add(newFile);
		java.net.URL imageURL5 = this.getClass().getResource("/new.png");
		ImageIcon image5 = new ImageIcon(imageURL5);

		newFile.setIcon(image5);

		openFile = new JMenuItem();
		openFile.setText("Open");
		openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		openFile.addActionListener(actionHandler);
		//openFile.setMnemonic(KeyEvent.VK_O);
		file.add(openFile);
		java.net.URL imageURL4 = this.getClass().getResource("/open.png");
		ImageIcon image4 = new ImageIcon(imageURL4);

		openFile.setIcon(image4);

		saveFile = new JMenuItem();
		saveFile.setText("Save");
		saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		saveFile.addActionListener(actionHandler);
		saveFile.setMnemonic(KeyEvent.VK_S);
		file.addSeparator();
		file.add(this.saveFile);
		java.net.URL imageURL3 = this.getClass().getResource("/save.png");
		ImageIcon image3 = new ImageIcon(imageURL3);

		saveFile.setIcon(image3);


		close = new JMenuItem();
		close.setText("Close");
		close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4,
				ActionEvent.CTRL_MASK));
		// close.setMnemonic(KeyEvent.VK_F4);
		close.addActionListener(actionHandler);
		file.addSeparator();
		file.add(this.close);
		java.net.URL imageURL1 = this.getClass().getResource("/close.png");
		ImageIcon image1 = new ImageIcon(imageURL1);
		close.setIcon(image1);

		/*undo = new JMenuItem();
		undo.setText("Undo");
		undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				ActionEvent.CTRL_MASK));
		undo.setMnemonic(KeyEvent.VK_U);
		undo.addActionListener(actionHandler);
		edit.add(this.undo);
		java.net.URL imageURL2 = this.getClass().getResource("/undo.png");
		ImageIcon image2 = new ImageIcon(imageURL2);*/

		// System.out.println(imageURL); //imageURL is printing correctly in
		// console
		//undo.setIcon(image2);

		JMenuItem cut = new JMenuItem(new DefaultEditorKit.CutAction());
		// cut = new JMenuItem()
		cut.setText("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		cut.setMnemonic(KeyEvent.VK_T);
		edit.addSeparator();
		edit.add(cut);
		java.net.URL imageURL7 = this.getClass().getResource("/cut.png");
		ImageIcon image7 = new ImageIcon(imageURL7);

		// System.out.println(imageURL); //imageURL is printing correctly in
		// console
		cut.setIcon(image7);
		TimeDate = new JMenuItem();
		TimeDate.setText("TimeDate");
		TimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));
		TimeDate.setMnemonic(KeyEvent.VK_D);
		TimeDate.addActionListener(actionHandler);
		edit.add(TimeDate);

		printfile = new JMenuItem();
		printfile.setText("print");
		printfile.addActionListener(actionHandler);
		file.add(printfile);
		java.net.URL imageURL8 = this.getClass().getResource("/prev.PNG");
		ImageIcon image8 = new ImageIcon(imageURL8);

		// System.out.println(imageURL); //imageURL is printing correctly in
		// console
		printfile.setIcon(image8);

		JMenuItem copy = new JMenuItem(new DefaultEditorKit.CopyAction());
		// copy = new JMenuItem();
		copy.setText("copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				ActionEvent.CTRL_MASK));
		copy.setMnemonic(KeyEvent.VK_C);
		copy.addActionListener(actionHandler);
		edit.add(copy);
		java.net.URL imageURL10 = this.getClass().getResource("/copy.png");
		ImageIcon image10 = new ImageIcon(imageURL10);

		// System.out.println(imageURL); //imageURL is printing correctly in
		// console
		copy.setIcon(image8);

		JMenuItem paste = new JMenuItem(new DefaultEditorKit.PasteAction());
		// paste = new JMenuItem();
		paste.setText("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		paste.setMnemonic(KeyEvent.VK_P);
		paste.addActionListener(actionHandler);
		edit.add(paste);
		java.net.URL imageURL9 = this.getClass().getResource("/edit_paste.png");
		ImageIcon image9 = new ImageIcon(imageURL9);

		// System.out.println(imageURL); //imageURL is printing correctly in
		// console
		paste.setIcon(image9);

		about = new JMenuItem();
		about.setText("Authors");
		about.addActionListener(actionHandler);
		help.add(this.about);

		font = new JMenu("Font");

		fontTimesNewRoman = new JMenuItem("Times New Roman");
		// fontSansSerif.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		fontTimesNewRoman.addActionListener(actionHandler);

		fontCourierNew = new JMenuItem("Courier New");
		fontCourierNew.addActionListener(actionHandler);

		fontSansSerif = new JMenuItem("Sans Serif");

		fontSansSerif.addActionListener(actionHandler);
		fontConsolas = new JMenuItem("Consolas");
		fontConsolas.addActionListener(actionHandler);
		fontCorbel = new JMenuItem("Corbel");
		fontCorbel.addActionListener(actionHandler);
		fontCourier = new JMenuItem("Courier");
		fontCorbel.addActionListener(actionHandler);
		fontGeorgia = new JMenuItem("Georgia");
		fontGeorgia.addActionListener(actionHandler);
		fontLucidacalligraphy = new JMenuItem("Lucidacalligraphy");
		fontLucidacalligraphy.addActionListener(actionHandler);
		fontHarrigton = new JMenuItem("Harrigton");
		fontHarrigton.addActionListener(actionHandler);
	    fontComicSansMS = new JMenuItem("ComicSansMS");
		fontComicSansMS.addActionListener(actionHandler);
		

		font.add(fontTimesNewRoman);
		font.add(fontCourierNew);
		
		font.add(fontSansSerif);
		font.add(fontHarrigton);
		font.add(fontCorbel);
		font.add(fontCourier);
		font.add(fontGeorgia);
		font.add(fontLucidacalligraphy);
		font.add(fontComicSansMS);
		wordwrap = new JMenuItem("Word Wrap");
		wordwrap.addActionListener(actionHandler);

		fontColorMenu = new JMenu("Font Color");
		for(int i=0; i<3; i++)
		{
			fontColor[i] = new JMenuItem();
			fontColor[i].addActionListener(actionHandler);
			fontColorMenu.add(fontColor[i]);
		}
		
		fontColor[0].setText("Red");
		fontColor[1].setText("Green");
		fontColor[2].setText("Blue");
		//fontColor[3].setText("Gray");
		
		
		fontSizeMenu = new JMenu("Font Size");
		for(int i=0; i<fSize; i++)
		{
			fontSize[i] = new JMenuItem("" + (i+12));
			fontSize[i].addActionListener(actionHandler);
			fontSizeMenu.add(fontSize[i]);
		}
		
		
		format.add(wordwrap);
		format.addSeparator();
		format.add(font);
		format.add(fontColorMenu);
		format.add(fontSizeMenu);
		

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(view);
		menuBar.add(format);
		menuBar.add(help);

		add(menuBar);
	}

	private void setLookandFeel() {
		// TODO Auto-generated method stub
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}

	JFrame frame;

	public static JTextArea textArea;
	private JScrollPane areaScrollPane;
	
	public static int fSize = 20;
	
	private JMenuBar menuBar;

	public static JMenu file, edit, format, view, help, font, fontSizeMenu, fontColorMenu;

	public static JMenuItem openFile, saveFile, close, newFile, printfile,
			undo, delete, wordwrap, about, TimeDate,

			fontTimesNewRoman, fontSansSerif, fontCourierNew, fontConsolas,
			fontCorbel, fontCourier, fontGeorgia, fontLucidacalligraphy,fontComicSansMS,
			fontHarrigton, fontColor[] = new JMenuItem[3],
			fontSize[] = new JMenuItem[fSize];// = new JMenuItem();

	ActionHandler actionHandler;

}
