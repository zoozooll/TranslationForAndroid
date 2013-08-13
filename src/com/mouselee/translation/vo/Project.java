/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class Project {

	private String projectName;
	private List<Language> languageList;

	/**
	 * @return the localItem
	 */
	public List<Language> getLanguageList() {
		return languageList;
	}

	/**
	 * @param languageList the localItem to set
	 */
	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
}
