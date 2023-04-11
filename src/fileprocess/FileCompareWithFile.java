package fileprocess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import fileprocess.xml.RecordFileCompareXmlHandlerV1;
import fileprocess.xml.RecordXmlHandler;

import java.io.*;

public class FileCompareWithFile {
	public static Logger logger = Logger.getLogger("FileCompareWithFile");

	public static void main(String[] args) {
		/*
		 * // Get current size of heap in bytes long heapSize =
		 * Runtime.getRuntime().totalMemory();
		 * 
		 * // Get maximum size of heap in bytes. The heap cannot grow beyond this
		 * size.// Any attempt will result in an OutOfMemoryException. long heapMaxSize
		 * = Runtime.getRuntime().maxMemory();
		 * 
		 * // Get amount of free memory within the heap in bytes. This size will
		 * increase // after garbage collection and decrease as new objects are created.
		 * long heapFreeSize = Runtime.getRuntime().freeMemory();
		 */
		//readTextFile("./input/sample7mil.txt");
		String fileNamePart="./input/sample10mil";
		List<String> lines = readTextFileWithScanner(fileNamePart+".txt");
		readXmlFileWithSax(fileNamePart+".xml", lines);
		System.gc();
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );

	}
	
	public static void readXmlFileWithSax(String filePath, List<String> lines) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			RecordFileCompareXmlHandlerV1 xmlHandler = new RecordFileCompareXmlHandlerV1(lines);
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
			saxParser.parse(filePath, xmlHandler);
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
			//logger.info("Total Lines : " + xmlHandler.getLines().size());
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static List<String> readTextFileWithScanner(String filePath) {
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
		List<String> lines = new ArrayList<String>();
		try (Scanner scanner = new Scanner(new FileReader(filePath)).useDelimiter("\\||\\*")  ) {
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
			
			for(int lineNo= 0;scanner.hasNext();lineNo++) {
				
				  lines.add(scanner.next());
				  
//				  if (lineNo<3) logger.info(lines.get(lineNo));
//				  if (lineNo==4) {
//					  logger.info(lines.get(lineNo));
//					  String s[] =  lines.get(lineNo).split(",");
//					  for (String string : s) { 
//						  logger.info("----" + string);
//					  }
//				  }
				 			
			}
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
			logger.info("lines.length : " + lines.size());
			//for (String str : lines) { logger.info(str); }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (lines);

	}
	
	public static long sizeInMb(long sizeInBytes) {
		return sizeInBytes / (1024 * 1024);
	}

}
