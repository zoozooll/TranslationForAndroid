/**
 * 
 */
package com.mouselee.translation.bo;

/**
 * @author zuokang.li
 *
 */
public class Config {

	/**
	 * This 
	 */
	public static final String DEFUALT_LOCALE= "en";
	
	public static final LocalTitleCategory TITLE_SHOWS_CATEGORY = LocalTitleCategory.BY_CUSTOM;
	
	public static final String CUSTOM_LOCALE="zh";
	
	public static enum LocalTitleCategory {
		BY_DEFAULT,
		BY_ITSELF,
		BY_SYSTEM_SETTING,
		BY_CUSTOM
	}
}


