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

import fileprocess.xml.RecordFileCompareXmlHandlerV2;
import fileprocess.xml.RecordXmlHandler;

import java.io.*;

public class FileCompareWithFileNoList {
	public static Logger logger = Logger.getLogger("FileCompareWithFileNoList");

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
		//readTextFile("./input/sample8mil.txt");
		String fileNamePart="./input/sample10mil";
		readTxtAndXmlFileWithCompare(fileNamePart+".xml", fileNamePart+".txt");
		System.gc();
		logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );

	}
	
	public static void readTxtAndXmlFileWithCompare(String xmlFilePath, String txtFilePath) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			RecordFileCompareXmlHandlerV2 xmlHandler = new RecordFileCompareXmlHandlerV2(txtFilePath);
			logger.info("HeapSize/HeapMaxSize:HeapFreeSize : " + sizeInMb(Runtime.getRuntime().totalMemory()) + "/" + sizeInMb(Runtime.getRuntime().maxMemory()) + ":" + sizeInMb(Runtime.getRuntime().freeMemory())  + "==" + (sizeInMb(Runtime.getRuntime().totalMemory()) - sizeInMb(Runtime.getRuntime().freeMemory())) );
			saxParser.parse(xmlFilePath, xmlHandler);
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
	
	public static long sizeInMb(long sizeInBytes) {
		return sizeInBytes / (1024 * 1024);
	}

}
