package internaluse;

import org.apache.log4j.Logger;

/**
 * Simulate log on CATT & Console.
 */
public class log {
	private static Logger logger = Logger.getLogger(log.class.getName());

	private log() {
	}

	/**
	 * Print warning message.
	 * 
	 * @param msg
	 *            Message
	 */
	public static void warn(String msg) {
		System.out.println(msg);
		logger.warn(msg);
	}

	/**
	 * Print info message.
	 * 
	 * @param msg
	 *            Message
	 */
	public static void info(String msg) {
		System.out.println(msg);
		logger.info(msg);
	}

	/**
	 * Print error message.
	 * 
	 * @param msg
	 *            Message
	 */
	public static void error(String msg) {
		System.err.println(msg);
		logger.error(msg);
	}

	/**
	 * Print debug message.
	 * 
	 * @param msg
	 *            Message
	 */
	public static void debug(String msg) {
		System.out.println(msg);
		logger.debug(msg);
	}
}