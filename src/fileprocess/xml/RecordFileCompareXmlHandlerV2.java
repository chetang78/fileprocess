package fileprocess.xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RecordFileCompareXmlHandlerV2 extends DefaultHandler {
	/*
	 * <body> <record> <from>H143409080000590</from> <field2>0000-00-00</field2>
	 * <field3>0000-00-00</field3> <amount>-10000.01</amount> </record>
	 * 
	 * 
	 */
	public static Logger logger = Logger.getLogger("RecordFileCompareXmlHandlerV2");

	public RecordFileCompareXmlHandlerV2(String txtFileName) {
		super();
		this.txtFileName = txtFileName;
	}

	private static long sizeInMb(long sizeInBytes) {
		return sizeInBytes / (1024 * 1024);
	}

	private static final String BODY = "body";
	private static final String RECORD = "record";
	private static final String AMOUNT = "amount";
	private static final String FROM = "from";

	private String txtFileName;
	private Record record;
	private int recordNo = 0;
	private StringBuilder elementValue;
	private Scanner scanner;

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (elementValue == null) {
			elementValue = new StringBuilder();
		} else {
			elementValue.append(ch, start, length);
		}
	}

	@Override
	public void startDocument() throws SAXException {
		try {
			scanner = new Scanner(new FileReader(txtFileName)).useDelimiter("\\||\\*");
			for (int i=1;i<4;i++) {
				if (scanner.hasNext()) {
					logger.info(" SKIPPING : " + scanner.next());
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SAXException("Unable to open Text File to be compared : " + e.getMessage());
		}
	}

	@Override
	public void endDocument() throws SAXException {
		logger.info(" Records Compared : " + recordNo);
		while (scanner.hasNext()) {
			logger.info(" ERROR XML DOES NOT HAVE FOLLOWING RECORD : " + recordNo + scanner.next());
			recordNo++;
		}
	}
	
	@Override
	public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {
		switch (qName) {
//		case BODY:
//			txtFileLines = new ArrayList<Record>();
//			break;
		case RECORD:
			record = new Record();
			break;
		default:
			elementValue = new StringBuilder();
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case RECORD:
			recordNo++;
			// if (recordNo <10)
			if (scanner.hasNext())
				recordCompare(record, scanner.next(), recordNo);
			else {
				logger.info("MISMATCH in FILES" + recordNo);
			}
			record = new Record();
			break;
		case FROM:
			record.setLineNo(elementValue.toString());
			break;
		case AMOUNT:
			record.setAmount(elementValue.toString());
			// System.out.println("Amount : " + record.getAmount());
			break;
		/*
		 * case BODY: System.out.println("Lines : " + lines.size()); break;
		 */
		}
	}

	private void recordCompare(Record xmlRecord, String txtLine, int recordNo) {
		// TODO Auto-generated method stub
		String[] txtValues = txtLine.split(",");
		if (!xmlRecord.getAmount().equals(txtValues[3])) {
			// System.out.println(txtLine);
			System.out.println("Different : " + xmlRecord.getAmount() + " - " + txtValues[3] + " - " + recordNo);
		} 
//		else {
//			System.out.println("Same : " + xmlRecord.getAmount() + " - " + txtValues[3] + " - " + recordNo);
//			
//		}

	}

}