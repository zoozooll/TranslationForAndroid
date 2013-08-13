/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class MeepProject {

	private String projectName;
	private List<LocalItem> localItem;

	/**
	 * @return the localItem
	 */
	public List<LocalItem> getLocalItem() {
		return localItem;
	}

	/**
	 * @param localItem the localItem to set
	 */
	public void setLocalItem(List<LocalItem> localItem) {
		this.localItem = localItem;
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
