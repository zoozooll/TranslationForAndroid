/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class Language {

	 private String languageName;
	 private List<XMLFile> xmlFiles;
	 
	/**
	 * @return the language
	 */
	public String getLanguageName() {
		return languageName;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	/**
	 * @return the notes
	 */
	public List<XMLFile> getXmlFiles() {
		return xmlFiles;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setXmlFiles(List<XMLFile> files) {
		this.xmlFiles = files;
	}
	 
	 
}
