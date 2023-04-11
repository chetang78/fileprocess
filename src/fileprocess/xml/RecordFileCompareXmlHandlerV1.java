package fileprocess.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.xylem.Logger;

public class RecordFileCompareXmlHandlerV1 extends DefaultHandler {
	/*
	 * <body> <record> <from>H143409080000590</from> <field2>0000-00-00</field2>
	 * <field3>0000-00-00</field3> <amount>-10000.01</amount> </record>
	 * 
	 * 
	 */

	public RecordFileCompareXmlHandlerV1(List<String> txtFileLines) {
		super();
		this.txtFileLines = txtFileLines;
	}

	private static final String BODY = "body";
	private static final String RECORD = "record";
	private static final String AMOUNT = "amount";
	private static final String FROM = "from";

	private List<String> txtFileLines;
	private Record record;
	private int recordNo = 0;
	private StringBuilder elementValue;
	

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (elementValue == null) {
			elementValue = new StringBuilder();
		} else {
			elementValue.append(ch, start, length);
		}
	}

	/*
	 * @Override public void startDocument() throws SAXException { lines = new
	 * ArrayList<String>(); }
	 */

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
			//if (recordNo <10)
				recordCompare(record, txtFileLines.get((int) (recordNo+3-1)) , recordNo);
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
			//System.out.println(txtLine);
			System.out.println("Different : " + xmlRecord.getAmount() + " - "+txtValues[3]  + " - "+ recordNo);
		}
		
	}


}