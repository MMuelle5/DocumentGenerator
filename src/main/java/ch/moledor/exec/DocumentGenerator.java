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

public class DocumentGenerator {
//	private static final String HEADING1 = "Heading1";

	private String[] wordList;
	private int wlSize;
	
	public DocumentGenerator(List<String> wordList) {
		super();
		this.wordList = new String[wordList.size()];
		this.wordList = wordList.toArray(this.wordList);
		this.wlSize = wordList.size();
	}



	public void createDoc(String filePath) throws IOException {
        XWPFDocument doc = new XWPFDocument();
        
        Random rand = new Random();
        int docSize = rand.nextInt(20);
        
        for(int i = 0; i < docSize; i++) {
	        
	        XWPFParagraph p1 = doc.createParagraph();
//			p1.setStyle(HEADING1);
	        XWPFRun r1 = p1.createRun();
	        int headerSize = rand.nextInt(8);
	        
	        StringBuilder sb = new StringBuilder();
	        for(int y = 0; y < headerSize; y++) {
	        	sb.append(wordList[rand.nextInt(wlSize)]+" ");
	        }
	        r1.setText(sb.toString());
	        
	        XWPFParagraph p2 = doc.createParagraph();
	        p2.setWordWrap(true);
//	        p2.setPageBreak(true);
	                
	        //p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
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
//
//	public void vorlage() {
//        XWPFDocument doc = new XWPFDocument();
//		// von hier an vorlagen
//
//        XWPFParagraph p1 = doc.createParagraph();
//        p1.setAlignment(ParagraphAlignment.CENTER);
//        p1.setBorderBottom(Borders.DOUBLE);
//        p1.setBorderTop(Borders.DOUBLE);
//
//        p1.setBorderRight(Borders.DOUBLE);
//        p1.setBorderLeft(Borders.DOUBLE);
//        p1.setBorderBetween(Borders.SINGLE);
//
//        p1.setVerticalAlignment(TextAlignment.TOP);
//
//        XWPFRun r1 = p1.createRun();
//        r1.setBold(true);
//        r1.setText("The quick brown fox");
//        r1.setBold(true);
//        r1.setFontFamily("Courier");
//        r1.setUnderline(UnderlinePatterns.DOT_DOT_DASH);
//        r1.setTextPosition(100);
//
//        XWPFParagraph p2 = doc.createParagraph();
//        p2.setAlignment(ParagraphAlignment.RIGHT);
//
//        //BORDERS
//        p2.setBorderBottom(Borders.DOUBLE);
//        p2.setBorderTop(Borders.DOUBLE);
//        p2.setBorderRight(Borders.DOUBLE);
//        p2.setBorderLeft(Borders.DOUBLE);
//        p2.setBorderBetween(Borders.SINGLE);
//
//        XWPFRun r2 = p2.createRun();
//        r2.setText("jumped over the lazy dog");
//        r2.setStrike(true);
//        r2.setFontSize(20);
//
//        XWPFRun r3 = p2.createRun();
//        r3.setText("and went away");
//        r3.setStrike(true);
//        r3.setFontSize(20);
//        r3.setSubscript(VerticalAlign.SUPERSCRIPT);
//
//
//        XWPFParagraph p3 = doc.createParagraph();
//        p3.setWordWrap(true);
//        p3.setPageBreak(true);
//                
//        //p3.setAlignment(ParagraphAlignment.DISTRIBUTE);
//        p3.setAlignment(ParagraphAlignment.BOTH);
//        p3.setSpacingLineRule(LineSpacingRule.EXACT);
//
//        p3.setIndentationFirstLine(600);
//        
//
//        XWPFRun r4 = p3.createRun();
//        r4.setTextPosition(20);
//        r4.setText("To be, or not to be: that is the question: "
//                + "Whether 'tis nobler in the mind to suffer "
//                + "The slings and arrows of outrageous fortune, "
//                + "Or to take arms against a sea of troubles, "
//                + "And by opposing end them? To die: to sleep; ");
//        r4.addBreak(BreakType.PAGE);
//        r4.setText("No more; and by a sleep to say we end "
//                + "The heart-ache and the thousand natural shocks "
//                + "That flesh is heir to, 'tis a consummation "
//                + "Devoutly to be wish'd. To die, to sleep; "
//                + "To sleep: perchance to dream: ay, there's the rub; "
//                + ".......");
//        r4.setItalic(true);
////This would imply that this break shall be treated as a simple line break, and break the line after that word:
//
//        XWPFRun r5 = p3.createRun();
//        r5.setTextPosition(-10);
//        r5.setText("For in that sleep of death what dreams may come");
//        r5.addCarriageReturn();
//        r5.setText("When we have shuffled off this mortal coil,"
//                + "Must give us pause: there's the respect"
//                + "That makes calamity of so long life;");
//        r5.addBreak();
//        r5.setText("For who would bear the whips and scorns of time,"
//                + "The oppressor's wrong, the proud man's contumely,");
//        
//        r5.addBreak(BreakClear.ALL);
//        r5.setText("The pangs of despised love, the law's delay,"
//                + "The insolence of office and the spurns" + ".......");
//
////        FileOutputStream out = new FileOutputStream(new File(filePath));
////        doc.write(out);
////        out.close();
//
//    }
}