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

import fileprocess.xml.RecordXmlHandler;

import java.io.*;

public class FileCompare {
	public static Logger logger = Logger.getLogger("FileCompare");

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
		//readTextFileWithScanner("./input/sample10mil.txt");
		readXmlFileWithSax("./input/sample10mil.xml");
		System.gc();
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));

	}
	
	public static void readXmlFileWithSax(String filePath) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			RecordXmlHandler xmlHandler = new RecordXmlHandler();
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
			saxParser.parse(filePath, xmlHandler);
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
			logger.info("Total Lines : " + xmlHandler.getLines().size());
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void readTextFileWithScanner(String filePath) {
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
		try (Scanner scanner = new Scanner(new FileReader(filePath)).useDelimiter("\\||\\*")  ) {
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
			List<String> lines = new ArrayList<String>();
			
			for(int lineNo= 0;scanner.hasNext();lineNo++) {
				scanner.next();
				/*
				 * lines.add(scanner.next());
				 * 
				 * if (lineNo<3) logger.info(lines.get(lineNo));
				 */			
			}
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
			logger.info("lines.length : " + lines.size());
			//for (String str : lines) { logger.info(str); }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void readTextFile(String filePath) {
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/"
				+ sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			while (reader.ready()) {
				logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory()));
				String line = reader.readLine();
				String[] lines = line.split("\\||\\*");
				logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/"
						+ sizeInMb(Runtime.getRuntime().maxMemory()) + ":"
						+ sizeInMb(Runtime.getRuntime().freeMemory()));
				logger.info("lines.length : " + lines.length);
				// for (String str : lines) { logger.info(str); }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static long sizeInMb(long sizeInBytes) {
		return sizeInBytes / (1024 * 1024);
	}

}
