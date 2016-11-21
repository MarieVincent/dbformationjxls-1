package application;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.xml.sax.SAXException;

import entities.Entry;
import entities.StupidEntry;

public class App {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		App app = new App();

		List<Entry> entries = app.xlsReadFromFile();

		for (int i = 0; i < entries.size(); i++) {
			System.out.println(entries.get(i));
		}
		
		System.out.println(entries.size());

	}

	public List<Entry> xlsReadFromFile() throws ParseException {

		InputStream xmlConfig = null;

		try {
			xmlConfig = new BufferedInputStream(new FileInputStream(
					"C://code//workspace//dbformationjxls//src//main//java//application//config.xml"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XLSReader xlsReader = null;

		try {
			xlsReader = ReaderBuilder.buildFromXML(xmlConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<StupidEntry> stupidentries = new ArrayList<StupidEntry>();

		Map<String, List<StupidEntry>> beans = new HashMap<String, List<StupidEntry>>();

		beans.put("stupidentries", stupidentries);

		InputStream xlsStream = null;

		try {
			xlsStream = new BufferedInputStream(
					new FileInputStream("C://code//workspace//dbformationjxls//sopra-modified.xlsx"));
			xlsReader.read(xlsStream, beans);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		stupidentries.remove(stupidentries.size() - 1);

		List<Entry> entries = new ArrayList<Entry>();

		for (int i = 0; i < stupidentries.size(); i++) {
			Entry entry = new Entry(stupidentries.get(i));
			entries.add(entry);
		}

		return entries;

	}

}