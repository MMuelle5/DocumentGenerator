package ch.moledor.exec;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * Erstellen von neuer Dokumente
 * @author MARIUS
 *
 */
public class DocumentGenerator {

	private String[] wordList;
	private int wlSize;
	
	public DocumentGenerator(List<String> wordList) {
		super();
		this.wordList = new String[wordList.size()];
		this.wordList = wordList.toArray(this.wordList);
		this.wlSize = wordList.size();
	}


	/**
	 * Generierung der Daten fuer ein bestimmtes .doc-File
	 *  - zufaellige Anzahl von Absaetzen
	 *  - zufaellige Anzahl von Woertern pro Absatz
	 *  
	 *  Speichern des Dokumentes
	 * @param filePath
	 * @throws IOException
	 */
	public void createDoc(String filePath) throws IOException {
        XWPFDocument doc = new XWPFDocument();
        
        Random rand = new Random();
        int docSize = rand.nextInt(20);
        
        for(int i = 0; i < docSize; i++) {
	        
	        XWPFParagraph p1 = doc.createParagraph();
	        XWPFRun r1 = p1.createRun();
	        int headerSize = rand.nextInt(8);
	        
	        StringBuilder sb = new StringBuilder();
	        for(int y = 0; y < headerSize; y++) {
	        	sb.append(wordList[rand.nextInt(wlSize)]+" ");
	        }
	        r1.setText(sb.toString());
	        
	        XWPFParagraph p2 = doc.createParagraph();
	        p2.setWordWrap(true);
	        p2.setAlignment(ParagraphAlignment.LEFT);
	              
	        XWPFRun r2 = p2.createRun();

	        int textSize = rand.nextInt(500);
	        
	        sb = new StringBuilder();
	        for(int y = 0; y < textSize; y++) {
	        	sb.append(wordList[rand.nextInt(wlSize)]+" ");
	        }
	        r2.setText(sb.toString());
        }

        FileOutputStream out = new FileOutputStream(new File(filePath));
        doc.write(out);
        out.close();

    }
}
