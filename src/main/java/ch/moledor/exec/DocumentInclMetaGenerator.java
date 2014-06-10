package ch.moledor.exec;

import java.io.File;
import java.net.ConnectException;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**
 * zuerst unter C:\Program Files (x86)\OpenOffice 4\program
 * in der Kommandozeile folgenden Befehl absetzen:
 *      soffice.exe -accept=socket,host=localhost,port=8100;urp;StarOffice.ServiceManager
 * @author MARIUS
 *
 */
public class DocumentInclMetaGenerator {
	
	private OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
	
    public void generatePDF(String docfileName, String pdfFilename) {
    	File inputFile = new File(docfileName);
    	File outputFile = new File(pdfFilename);

    	// convert
    	DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
    	converter.convert(inputFile, outputFile);

	}
    
    /**
     * connect to an OpenOffice.org instance running on port 8100
     * @throws ConnectException
     */
    public void connect() throws ConnectException{
    	connection.connect();
    }
    
    /**
     * close the connection
     */
    public void disconnect() {
    	connection.disconnect();
    }
}
