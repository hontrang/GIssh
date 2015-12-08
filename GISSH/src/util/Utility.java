package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Utility class.
 */
public class Utility {
    
    public static String getTCID(String path) {
    	if (path.contains("."))
    		path = path.substring(path.lastIndexOf(".")+  1);

    	return path;
    }
    
    /**
	 * Read file.
	 * 
	 * @param path
	 *            Path of file
	 * @return String contains content of the file
	 * 
	 * @author Tri Nguyen
	 */
	public static String readFile(String path) {
		StringBuffer content = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
			String line = null;
			while ((line = br.readLine()) != null) {
				content.append(line);
				content.append("\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		 finally {
			   try {
				   if(br != null)
					   br.close();
			   }
			   catch (IOException e) {
			   }
		   }

		return content.toString();
	}
	
	/**
	 * Write content into a file.
	 * @param content File content
	 * @param destinationFilePath Destination file path
	 */
	public static void writeFile(String content, String destinationFilePath) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(destinationFilePath));
			bw.write(content);
		}
		catch (Exception e) {
			return;
		}
		finally {
			try {
				if(bw != null)
					bw.close();
			}
			catch (IOException e) {
				// Do nothing
			}
		}
	}
	
	/**
	 * Create new file if it has already existed
	 * @param filePath File path
	 */
	public static void createNewFile(String filePath) {
		File file = new File(filePath);
		try {
			if (file.exists()) {
				writeFile("", filePath);
			}
			else {
				file.createNewFile();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static String substring(String str) {
		return (str != null) ? str.substring(str.length() - 4) : "No value for variable"+  str+  "!";
	}
	
	public static String getDateTime() {
		return getDateTime("_dd-MMM-yy_hh-mm-ss_SSS_a");
	}
	
	public static String getDateTime(String dateFormatStr) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatStr);
		Date date = new Date();
		String currentDay = dateFormat.format(date);
		return currentDay;
	}
	
	
}