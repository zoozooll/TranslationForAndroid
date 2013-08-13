/**
 * 
 */
package com.mouselee.translation.bo;

import org.apache.commons.logging.impl.Log4JLogger;

/**
 * @author aaronli
 *
 */
public class Log {
	private static Log4JLogger instance;
	
	static {
		instance = new Log4JLogger("aaron");
	}
	
	public static void d(Object message) {
		instance.debug(message);
	}
	
	public static void d(Object message, Throwable t) {
		instance.debug(message, t);
	}
	
	public static void e(Object message) {
		instance.error(message);
	}
	
	public static void e(Object message, Throwable t) {
		instance.error(message, t);
	}
	
	public static void f(Object message) {
		instance.fatal(message);
	}
	
	public static void f(Object message, Throwable t) {
		instance.fatal(message, t);
	}
	
	public static void t(Object message) {
		System.out.println(message);
	}
	
	public static void t(Object message, Throwable t) {
		System.out.println(message);
		System.err.println(t);
	}
	
	public static void w(Object message) {
		instance.warn(message);
	}
	
	public static void w(Object message, Throwable t) {
		instance.warn(message, t);
	}
	
	
}
