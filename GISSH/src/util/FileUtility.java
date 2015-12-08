package util;

import internaluse.log;

import java.io.*;

public class FileUtility {

	public static boolean writeFileLog(String fileName, String string) {
//		System.out.println(string);
		log.info(string);
		PrintWriter out = null;
		try {
		    String currentDay = Utility.getDateTime("dd-MMM-yy hh:mm:ss a");
			out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			out.println(currentDay+  "\t"+  string);
			
		} catch (IOException e) {
			String errMsg = ("\t\t" + e.getMessage()); 
			log.error(errMsg);
			out.print(errMsg);
		}
		finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}

		return true;
	}
	
	public static void writeLogFile(String string) {
		String fileName = "./result/result.txt";
		writeFileLog(fileName, string);
	}	
}
