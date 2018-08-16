package note_pro;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;

import javax.swing.JFileChooser;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePDF {

	public GeneratePDF(NotepadMain notepadMain) {
		// TODO Auto-generated constructor stub
		
		JFileChooser save = new JFileChooser();

        int option = save.showSaveDialog(notepadMain);

        String directory = "";
        if (option == JFileChooser.APPROVE_OPTION) {

            try{
                directory = save.getSelectedFile().getPath() + ".pdf";

            } catch (Exception ex) {

                System.out.println(ex.getMessage());

            }

        }
		
		Document doc = new Document(PageSize.A4);
		//Response response;
		String title = "ashik";
		//response.setHeader("Content-disposition", "attachment; filename=\"" + title + ".pdf\"");
		//response.setContentType("application/pdf");
		File file = new File("ashik.pdf");
		//OutputStream out = new OutputStream();
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(directory);
		
			PdfWriter.getInstance(doc, fileOut);
			
			doc.open();
			HTMLWorker hw = new HTMLWorker(doc);
			hw.parse(new StringReader("<html>"+ notepadMain.textArea.getText() +"</html>"));
			doc.close();
			
			System.out.println("Successfully");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
