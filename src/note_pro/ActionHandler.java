package note_pro;

import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.event.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Stack;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ActionHandler implements ActionListener
{
	
	String fontName;
	int fontSize; 
	
	public NotepadMain notepadMain;
	private Object PrintReceiptPage;
	protected Object mainFrame;
	public ActionHandler(NotepadMain notepadMain) {
		//this.notepadMain = notepadMain;
		
		fontName = "Consolas";
		fontSize = 20;
				
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == NotepadMain.openFile)
		{
			openfileoperation();
		}
		else if(event.getSource() == NotepadMain.newFile)
		{
			newfileoperation();
		}
		else if(event.getSource() == NotepadMain.saveFile)
		{
			savefileoperation();
		}
		
		else if(event.getSource() == NotepadMain.about)
		{
			aboutMenuOperation();
		}
		else if(event.getSource() == NotepadMain.close)
		{
			closeOperation();
		}
		else if(event.getSource() == NotepadMain.TimeDate)
		{
			timeOperation();
		}
		else if(event.getSource() == NotepadMain.fontTimesNewRoman)
		{			
			notepadMain.textArea.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
			fontName = "Times New Roman";
		}
		else if(event.getSource() == NotepadMain.fontCourierNew)
		{
			
			notepadMain.textArea.setFont(new Font("Courier New", Font.PLAIN, fontSize));
			fontName = "Courier New";
		}
		else if(event.getSource() == NotepadMain.fontConsolas)
		{
			
			notepadMain.textArea.setFont(new Font("Consolas", Font.PLAIN, fontSize));
			fontName = "Consolas";
		}
		else if(event.getSource() == NotepadMain.fontHarrigton)
		{
			
			notepadMain.textArea.setFont(new Font("Harrigton", Font.PLAIN, fontSize));
			fontName = "Harrigton";
		}
		else if(event.getSource() == NotepadMain.fontCorbel)
		{
			
			notepadMain.textArea.setFont(new Font("Corbel", Font.PLAIN, fontSize));
			fontName = "Corbel";
		}
		else if(event.getSource() == NotepadMain.fontCourier)
		{
			
			notepadMain.textArea.setFont(new Font("Courier", Font.PLAIN, fontSize));
			fontName = "Courier";
		}
		else if(event.getSource() == NotepadMain.fontGeorgia)
		{
			
			notepadMain.textArea.setFont(new Font("Georgia", Font.PLAIN, fontSize));
			fontName = "Georgia";
		}
		else if(event.getSource() == NotepadMain.fontLucidacalligraphy)
		{
			
			notepadMain.textArea.setFont(new Font("Lucidacalligraphy", Font.PLAIN, fontSize));
			fontName = "Lucidacalligraphy";
		}
		else if(event.getSource() == NotepadMain.fontComicSansMS)
		{
			
			notepadMain.textArea.setFont(new Font("ComicSansMS", Font.PLAIN, fontSize));
			fontName = "ComicSansMS";
		}
		
		
		else if(event.getSource() == NotepadMain.wordwrap)
		{		
			notepadMain.textArea.setWrapStyleWord(true);
			notepadMain.textArea.setLineWrap(true);
		}
		else if(event.getSource() == NotepadMain.printfile)
		{		
			//savePDFeoperation();
			new GeneratePDF(notepadMain);
		}
		
		for(int i=0; i<3; i++)
		{
			if(event.getSource() == NotepadMain.fontColor[i])
			{		
				notepadMain.textArea.setForeground(getColorName(notepadMain.fontColor[i].getText()));
				break;
			}
		}
		
		for(int i=0; i<notepadMain.fSize; i++)
		{
			if(event.getSource() == NotepadMain.fontSize[i])
			{		
				notepadMain.textArea.setFont(new Font(fontName, Font.PLAIN, (i+12)));
				break;
			}
		}
		
		

	}

	


	public Color getColorName(String colorName)
	{
		if(colorName.equals("Red"))
			return Color.RED;
		else if(colorName.equals("Green"))
			return Color.GREEN;
		else if(colorName.equals("Blue"))
			return Color.BLUE;
		else return Color.BLACK;
	}
	private void timeOperation() {
		
		String timeStamp = new SimpleDateFormat(" yyyy-MM-dd   HH:mm:ss").format(Calendar.getInstance().getTime());
		notepadMain.textArea.append(timeStamp);
		
			}
	private void savefileoperation() {
		JFileChooser save = new JFileChooser();

        int option = save.showSaveDialog(notepadMain);

        if (option == JFileChooser.APPROVE_OPTION) {

            try {

                BufferedWriter out = new BufferedWriter(new FileWriter(save

                        .getSelectedFile().getPath()));

                out.write(notepadMain.textArea.getText());

                out.close();

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

        }
		
	}
	
	
	private void savePDFeoperation() {
		JFileChooser save = new JFileChooser();

        int option = save.showSaveDialog(notepadMain);

        if (option == JFileChooser.APPROVE_OPTION) {

            try {

                BufferedWriter out = new BufferedWriter(new FileWriter(save

                        .getSelectedFile().getPath()));

                out.write(notepadMain.textArea.getText());

                out.close();

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

        }
		
	}

	private void closeOperation() {
		// TODO Auto-generated method stub
		System.exit(1);
	}

	private void aboutMenuOperation() {
		// TODO Auto-generated method stub
		JOptionPane jp = new JOptionPane();

        jp.showMessageDialog(null,

                "Created by Ashik");
	}

	private void newfileoperation() {
		// TODO Auto-generated method stub
		JOptionPane jp = new JOptionPane();

        Object[] options = { "Save", "Don't Save", "Cancel" };

        int button = jp.showOptionDialog(null,

                "Do you want to save " + "the file ", "Notepad",

                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,

                null, options, options[0]);

        if (button == 0) {

            JFileChooser newFile = new JFileChooser();

            int option = newFile.showSaveDialog(notepadMain);

            if (option == JFileChooser.APPROVE_OPTION) {

                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(

                            newFile.getSelectedFile().getPath()));

                    out.write(notepadMain.textArea.getText());

                    out.close();

                } catch (Exception ex) {

                    System.out.println(ex.getMessage());

                }

            }

        } else if (button == 1) {

        	notepadMain.textArea.setText(null);

        } else if (button == 2) {



        }
	}

	private void openfileoperation() {
		// TODO Auto-generated method stub
    	JFileChooser open = new JFileChooser();

        int option = open.showOpenDialog(notepadMain);

        if (option == JFileChooser.APPROVE_OPTION) {

        	notepadMain.textArea.setText("");  

            try {

                Scanner scan = new Scanner(new FileReader(open

                        .getSelectedFile().getPath()));

                while (scan.hasNext())

                	notepadMain.textArea.append(scan.nextLine() + "\n");
                
                
                scan.close();

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

        }
	}

}
