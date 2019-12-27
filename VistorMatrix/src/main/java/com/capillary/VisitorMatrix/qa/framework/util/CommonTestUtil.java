package com.capillary.VisitorMatrix.qa.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import com.capillary.VisitorMatrix.qa.framework.core.ExecutionConfig;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.googlecode.htmlcompressor.compressor.YuiCssCompressor;

public class CommonTestUtil {

	public static synchronized Properties loadPropertiesFile(String fileName) throws IOException {
		Properties prop = new Properties();
		File file = new File("");
		String filePath = file.getAbsolutePath() + File.separator + ExecutionConfig.TEST_DATA_PATH + File.separator
				+ fileName;
		file = new File(filePath);
		FileInputStream input = null;

		try {
			input = new FileInputStream(file);
			prop.load(input);
		} finally {
			input.close();
		}
		return prop;
	}	


	/**
	 * Executes a SELECT sql query and returns the result
	 * 
	 * @param sqlQuery
	 *            The SELECT sql query to execute
	 * @return a list of all rows in the result set where each list element
	 *         represents a row, empty list if result set is empty
	 *
	 */
	// public static synchronized List<Map<String, Object>>
	// executeSelectQuery(String sqlQuery){
	// Reporter.log("<b>Execute SQL query</b><br>Query => " + sqlQuery + "<br>");
	// Connection conn = null;
	// Statement stmt = null;
	// ResultSet rs = null;
	// String driver = "com.mysql.cj.jdbc.Driver";
	// String url = ExecutionConfig.DB_URL;
	// String usr = ExecutionConfig.DB_USERNAME;
	// String pwd = ExecutionConfig.DB_PASSWORD;
	// try{
	// System.out.println(url + "<>" + usr + "<>" + pwd);
	// DbUtils.loadDriver(driver);
	// conn = DriverManager.getConnection(url, usr, pwd);
	// stmt = conn.createStatement();
	// rs = stmt.executeQuery(sqlQuery);
	// MapListHandler mapListHandler = new MapListHandler();
	// List<Map<String, Object>> result = mapListHandler.handle(rs);
	// return result;
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// } finally {
	// DbUtils.closeQuietly(conn, stmt, rs);
	// }
	// }

	/**
	 * Executes a DML sql query (e.g. insert, update, delete etc.)
	 * 
	 * @param sqlQuery
	 *            The DML query to execute
	 *
	 */
	// public static synchronized void executeUpdateQuery(String sqlQuery){
	// Reporter.log("<b>Execute SQL query</b><br>Query => " + sqlQuery + "<br>");
	// Connection conn = null;
	// Statement stmt = null;
	// String driver = "com.mysql.cj.jdbc.Driver";
	// String url = ExecutionConfig.DB_URL;
	// String usr = ExecutionConfig.DB_USERNAME;
	// String pwd = ExecutionConfig.DB_PASSWORD;
	// try{
	// DbUtils.loadDriver(driver);
	// conn = DriverManager.getConnection(url, usr, pwd);
	// stmt = conn.createStatement();
	// stmt.executeUpdate(sqlQuery);
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// } finally {
	// DbUtils.closeQuietly(conn);
	// DbUtils.closeQuietly(stmt);
	// }
	// }

	/**
	 * Reads each line in the file and replaces placeholders in the format {0}, {1},
	 * etc. with the provided parameters.
	 *
	 * @param filePath
	 *            file path in which placeholders are to be replaced
	 * @param args
	 *            arguments with which placeholders should be replaced.
	 *
	 */
	public static void replaceKeysInFile(String filePath, String... args) {
		String destFileAbsolutePath = FileUtil.getAbsoluteFilePath(filePath);
		try {
			File file = new File(FileUtil.getAbsoluteFilePath(filePath));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String text = "", newText = "";
			while ((text = reader.readLine()) != null) {
				newText += MessageFormat.format(text, args[0], args[1]) + System.getProperty("line.separator");
			}
			reader.close();

			FileWriter writer = new FileWriter(destFileAbsolutePath);
			writer.write(newText);
			writer.close();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Compresses html & any inline css to remove tabs, new line, carriage return,
	 * extra white spaces etc.
	 *
	 * @param htmlContent
	 *            html to be compressed
	 * @return Compressed html
	 *
	 */
	public static String getCompressedHTML(String htmlContent) {
		Reporter.log("<b>Compress html<b><br>");
		Reporter.log("HTML => " + htmlContent + "<br>");
		htmlContent = htmlContent.replaceAll("\t", "");
		htmlContent = htmlContent.replaceAll("\r|\n", "");
		HtmlCompressor compressor = new HtmlCompressor();
		compressor.setRemoveIntertagSpaces(true);
		compressor.setRemoveSurroundingSpaces(HtmlCompressor.ALL_TAGS);
		YuiCssCompressor cssCompressor = new YuiCssCompressor();
		compressor.setCssCompressor(cssCompressor);
		return compressor.compress(htmlContent);
	}

	public static void pause(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<String, String> getMapProperties(String fileName) throws IOException {
		Properties prop = new Properties();
		File file = new File("");
		String filePath = file.getAbsolutePath() + File.separator + ExecutionConfig.TEST_DATA_PATH + File.separator
				+ fileName;
		file = new File(filePath);
		FileInputStream input = null;
		HashMap<String, String> propertiesMap = new HashMap<String, String>();
		try {
			input = new FileInputStream(file);
			prop.load(input);
			for (@SuppressWarnings("rawtypes")
			Map.Entry entry : prop.entrySet()) {
				propertiesMap.put((String) entry.getKey(), (String) entry.getValue());
			}
		} catch (IOException e) {
			Reporter.log(e.getMessage());
		} finally {
			input.close();
		}
		return propertiesMap;
	}
}