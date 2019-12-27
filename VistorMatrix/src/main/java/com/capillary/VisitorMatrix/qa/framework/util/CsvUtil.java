package com.capillary.VisitorMatrix.qa.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.apache.commons.lang.StringUtils;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.opencsv.CSVWriter;

public class CsvUtil {

	public static CsvUtil csv;

	public CsvUtil() {
	}

	public static synchronized CsvUtil getInstance() {
		if (csv == null) {
			return new CsvUtil();
		}
		return csv;
	}

	public synchronized ArrayList<LinkedHashMap<String, String>> getCsvData(File file) throws IOException {
		String headerString;
		String[] headers;
		String rowdata;
		ArrayList<LinkedHashMap<String, String>> csvListMap = new ArrayList<LinkedHashMap<String, String>>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try {
			headerString = reader.readLine();
			headers = headerString.split(",");
			while ((rowdata = reader.readLine()) != null) {
				LinkedHashMap<String, String> mappedData = new LinkedHashMap<String, String>();
				String[] data = rowdata.split(",(?=([^\\\"]|\\\"[^\\\"]*\\\")*$)");
				for (int i = 0; i < data.length; i++) {

					mappedData.put(headers[i], data[i]);
				}
				csvListMap.add(mappedData);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			reader.close();
		}
		return csvListMap;
	}

	@SuppressWarnings("resource")
	public synchronized void updateCSVData(File file, ArrayList<LinkedHashMap<String, String>> devicedata) throws IOException {
		Writer writer1 = null;
		try {
			LinkedHashSet<String> keys = new LinkedHashSet<>(devicedata.get(0).keySet());
			keys.addAll(devicedata.get(0).keySet());
			String[] headerRecord = StringUtils.join(devicedata.get(0).keySet(), ",").split(",");
			writer1 = Files.newBufferedWriter(Paths.get(file.getAbsolutePath()));
			CSVWriter csvWriter = new CSVWriter(writer1, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER,
					CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
			csvWriter.writeNext(headerRecord);
			System.out.println(headerRecord);
			for (LinkedHashMap<String, String> map : devicedata) {
				csvWriter.writeNext(getLineFromMap(map, keys).split(","));
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			writer1.close();
		}
	}
	
	public String getLineFromMap(LinkedHashMap<String, String> someMap, LinkedHashSet<String> keys) {
		ArrayList<String> values = new ArrayList<>();
		for (String key : keys) {
			values.add(someMap.get(key) == null ? "" : someMap.get(key));
		}
		return StringUtils.join(values, ",");
	}
}
