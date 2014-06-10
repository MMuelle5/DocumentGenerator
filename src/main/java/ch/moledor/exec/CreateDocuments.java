package ch.moledor.exec;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeException;

/**
 * Ausfuehrende Klasse
 * Generiert im definierten Ordner Dokumente
 *  - Aktuelles Dokument gemaess definierter Wahrscheinlichkeit auswaehlen
 *  - Dokumentennamen zufaellig waehlen
 *  - Aufruf von DocumentGenerator
 *  - Zweimaliger Aufruf von DocumentInclMetaGenerator (fuer *.doc und *.pdf)
 * @author MARIUS
 *
 */
public class CreateDocuments {

	public final static String PATH_DOC = "D:\\testFolderDataRetrieval\\docVorlage";
	public final static String PATH_PDF = "D:\\testFolderDataRetrieval\\pdfFolders";
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		System.out.println(PATH_DOC);
		System.out.println(PATH_PDF);
		List<String> wordList = getWordList();
		
		DocumentGenerator doc = new DocumentGenerator(wordList);
		DocumentInclMetaGenerator finalDoc = new DocumentInclMetaGenerator();
		finalDoc.connect();
		File file = new File(PATH_DOC);
		Random rand = new Random();
		int range = wordList.size();
		
		for(int i = 0; i < 1000; i ++) {
			double nextStep = Math.random();
			String documentName = wordList.get(rand.nextInt(range));
			
			
			//Dokument im Ueberverzeichnis anlegen, sofern != Hauptfolder
			if(nextStep<0.05 && !file.getAbsolutePath().equals(PATH_DOC)) {
				file  = file.getParentFile();
			}
			//neues Verzeichnis anlegen
			else if(nextStep >= 0.9) {
				
				//max Pathlength darf fuer die Erstellung von PDFs max 260 Zeichen lang sein
				if(file.getAbsolutePath().length() > 210) {
					file = file.getParentFile().getParentFile().getParentFile();
				}

				List<File> subFolders = new ArrayList<File>();
				if(nextStep < 0.8 && file.listFiles() != null) {
					for(File sub : file.listFiles()) {
						if(sub.isDirectory()) {
							subFolders.add(sub);
						}
					}
					
					if(subFolders.size() != 0) {
						file = subFolders.get(rand.nextInt(subFolders.size()));
					}
				}
				if(subFolders.size() == 0) {
					file = new File(file.getAbsolutePath()+"\\"+wordList.get(rand.nextInt(range)));
					file.mkdir();
					File pdfFolder = new File(file.getAbsolutePath().replaceAll("docVorlage", "pdfFolders"));
					pdfFolder.mkdir();
					File docFolder = new File(file.getAbsolutePath().replaceAll("docVorlage", "docFolders"));
					docFolder.mkdir();
				}
			}
			
			String fileNameWithoutEnding = file.getAbsolutePath()+"\\"+documentName;
			String docName = fileNameWithoutEnding+".doc";
			doc.createDoc(docName);

			String finalDocName = fileNameWithoutEnding.replaceAll("docVorlage", "docFolders")+".doc";
			String pdfName = fileNameWithoutEnding.replaceAll("docVorlage", "pdfFolders")+".pdf";
			System.out.println(i);
			try {
				finalDoc.generatePDF(docName, finalDocName);
				finalDoc.generatePDF(docName, pdfName);
			} catch(OpenOfficeException e) {
				System.out.println(pdfName);
			}
			
		}
		
		finalDoc.disconnect();
	}
	
	/**
	 * Woerter vom File einlesen
	 * @return
	 */
	private static List<String> getWordList() {
		List<String> wordList = new ArrayList<String>();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(new File("Wortsammlung.txt")));
			String line = "";
			
			while((line = br.readLine()) != null) {
				if(!line.startsWith("...") && !line.startsWith("#")) {
					wordList.add(line);
				}
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println(wordList.size());
		return wordList;
	}

}
