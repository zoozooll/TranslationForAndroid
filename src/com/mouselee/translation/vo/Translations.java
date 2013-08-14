/**
 * 
 */
package com.mouselee.translation.vo;

import java.util.List;

/**
 * @author aaronli
 *
 */
public class Translations {

	private List<Project> projects;   // every MeepMovie or MeepMusic... is one  MeepProject
	private String date; //  日期 編輯的時間,system.currentTimeMillions
	private String mMeepVersion; //Meep 3.0 
	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the mMeepVersion
	 */
	public String getmMeepVersion() {
		return mMeepVersion;
	}
	/**
	 * @param mMeepVersion the mMeepVersion to set
	 */
	public void setmMeepVersion(String mMeepVersion) {
		this.mMeepVersion = mMeepVersion;
	}
	
	
}
