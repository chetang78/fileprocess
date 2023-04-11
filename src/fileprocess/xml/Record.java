package fileprocess.xml;

// <record> <from>H143409080000590</from> <field2>0000-00-00</field2> <field3>0000-00-00</field3> <amount>-10000.01</amount> </record>
public class Record {
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	String lineNo;
	String amount;
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Record [lineNo=" + lineNo + ", amount=" + amount + "]";
	}
	
	
}
