package fileprocess.xml;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RecordXmlHandler extends DefaultHandler {
	/*
	 * <body> <record> <from>H143409080000590</from> <field2>0000-00-00</field2>
	 * <field3>0000-00-00</field3> <amount>-10000.01</amount> </record>
	 * 
	 * 
	 */

	private static final String BODY = "body";
	private static final String RECORD = "record";
	private static final String AMOUNT = "amount";
	private static final String FROM = "from";

	private List<Record> lines;
	private Record record;
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
		case BODY:
			lines = new ArrayList<Record>();
			break;
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
			lines.add(record);
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

	public List<Record> getLines() {
		return lines;
	}

}