import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Desktop;

public class GeneralHelper {
	public void createWordFile(String title , ArrayList<String> lines , String fileName ){
		XWPFDocument document   = new XWPFDocument();
		XWPFParagraph paragraph = document.createParagraph();

		XWPFRun docTitle = paragraph.createRun();
		docTitle.setText( title );
		docTitle.setFontSize(25);
		docTitle.setUnderline(UnderlinePatterns.SINGLE);
		docTitle.addBreak();
		docTitle.addBreak();

		XWPFRun content = paragraph.createRun();
		content.setFontSize(14);
		for( int i=0;i<lines.size(); i++) {
			content.setText( lines.get(i) );
			content.addBreak();
		}

		try{
			FileOutputStream output = new FileOutputStream(fileName);
			document.write(output);
			output.close();
		}catch ( Exception e ){
			e.printStackTrace();
		}
		
	}
	public void createWordFile( ArrayList<ArrayList> arrayList , String fileName ){
		XWPFDocument document   = new XWPFDocument();
		XWPFParagraph paragraph = document.createParagraph();


		for( int i=0;i<arrayList.size(); i++) {
			ArrayList<String> lines = arrayList.get(i);
			XWPFRun docTitle = paragraph.createRun();
			XWPFRun content  = paragraph.createRun();

			docTitle.setUnderline(UnderlinePatterns.SINGLE);
			docTitle.setFontSize(25);
			content.setFontSize(12);
			for( int j=0;j<lines.size(); j++) {

				if( j == 0){
					docTitle.setText( lines.get(j) );
					docTitle.addBreak();
				} else{
					content.setText( lines.get(j) );
					content.addBreak();
				}
			}
			content.addBreak();
		}

		try{
			FileOutputStream output = new FileOutputStream(fileName);
			document.write(output);
			output.close();
		}catch ( Exception e ){
			e.printStackTrace();
		}
	}

	public void openFile( String filePath ) throws IOException {
		File file = new File( filePath );
		if(!Desktop.isDesktopSupported()){
			System.out.println("Desktop is not supported");
			return;
		}
		Desktop desktop = Desktop.getDesktop();
		if(file.exists()) desktop.open(file);

	}
	
}
