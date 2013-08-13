/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class LocalItem {

	 private String language;
	 private List<XMLNote> notes;
	 
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the notes
	 */
	public List<XMLNote> getNotes() {
		return notes;
	}
	/**
	 * @param notes the notes to set
	 */
	public void setNotes(List<XMLNote> notes) {
		this.notes = notes;
	}
	 
	 
}
