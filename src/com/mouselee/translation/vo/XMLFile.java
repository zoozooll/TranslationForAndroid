/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class XMLFile {
	private String filename;
	private String root;
	private List<XMLItems> strings;
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the root
	 */
	public String getRoot() {
		return root;
	}
	/**
	 * @param root the root to set
	 */
	public void setRoot(String root) {
		this.root = root;
	}
	/**
	 * @return the strings
	 */
	public List<XMLItems> getStrings() {
		return strings;
	}
	/**
	 * @param strings the strings to set
	 */
	public void setStrings(List<XMLItems> strings) {
		this.strings = strings;
	}
	
}
