package fileprocess;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;

public class InputFileCreator {

	public static String header = "590FB|000000026|T|";
	public static String content = "C,H143409080000590,0000-00-00,-10000.01*";
	
	public static String headerXml = "<?xml version=\"1.0\"?><body>";
	public static String contentXml = "<record> <from>H143409080000590</from> <field2>0000-00-00</field2> <field3>0000-00-00</field3> <amount>-10000.01</amount> </record>";
	public static String footerXml = "</body>";
	
	
	public static Logger logger = Logger.getLogger( "File" );

	public static void main(String[] args) throws IOException {
		/*
		 * logger.info("FileCreation Started");
		 * whenWriteStringUsingBufferedWritter_thenCorrect("sample7mil.txt", 7000000);
		 * logger.info("FileCreation Ended"); logger.info("FileCreation Started");
		 * whenWriteStringUsingBufferedWritter_thenCorrect("sample8mil.txt", 8000000);
		 * logger.info("FileCreation Ended"); logger.info("FileCreation Started");
		 * whenWriteStringUsingBufferedWritter_thenCorrect("sample9mil.txt", 9000000);
		 * logger.info("FileCreation Ended"); logger.info("FileCreation Started");
		 * whenWriteStringUsingBufferedWritter_thenCorrect("sample10mil.txt", 10000000);
		 * logger.info("FileCreation Ended");
		 */		
		 logger.info("FileCreation Started");
		 whenWriteXmlUsingBufferedWritter_thenCorrect("sample1.xml", 100);
			
			  whenWriteXmlUsingBufferedWritter_thenCorrect("sample6mil.xml", 6000000);
			  whenWriteXmlUsingBufferedWritter_thenCorrect("sample7mil.xml", 7000000);
			  whenWriteXmlUsingBufferedWritter_thenCorrect("sample8mil.xml", 8000000);
			  whenWriteXmlUsingBufferedWritter_thenCorrect("sample9mil.xml", 9000000);
			  whenWriteXmlUsingBufferedWritter_thenCorrect("sample10mil.xml", 10000000);
			 		 
		 logger.info("FileCreation Ended"); 
	}

	public static void whenWriteXmlUsingBufferedWritter_thenCorrect(String fileName, long noOfRows)
			throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(headerXml);
			for (int i = 0; i < noOfRows; i++) {
				writer.write(contentXml);
			}
			writer.write(footerXml);
		}

	}

	public static void whenWriteStringUsingBufferedWritter_thenCorrect(String fileName, long noOfRows)
			throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(header);
			for (int i = 0; i < noOfRows; i++) {
				writer.write(content);
			}
		}

	}

	public static void givenWritingToFile_whenUsingFileChannel_thenCorrect(String fileName, long noOfRows) throws IOException {
		RandomAccessFile stream = new RandomAccessFile(fileName, "rw");
		FileChannel channel = stream.getChannel();
		String value = "Hello";
		byte[] strBytes = value.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(strBytes.length);
		buffer.put(strBytes);
		buffer.flip();
		channel.write(buffer);
		stream.close();
		channel.close();

		// verify
		/*
		 * RandomAccessFile reader = new RandomAccessFile(fileName, "r");
		 * assertEquals(value, reader.readLine()); reader.close();
		 */
	}
}
