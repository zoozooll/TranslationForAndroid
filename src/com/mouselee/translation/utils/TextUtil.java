/**
 * 
 */
package com.mouselee.translation.utils;

import java.util.Locale;

import com.mouselee.translation.bo.Config;

/**
 * @author aaronli
 *
 */
public class TextUtil {
	
	public static final String EMPTY_STRING = "";
	
	public static Locale sCustomLocale;
	
	public static Locale sDefaultLocale;
	
	static {
		sCustomLocale = instanceLocale(Config.CUSTOM_LOCALE);
		sDefaultLocale = instanceLocale(Config.DEFUALT_LOCALE);
	}

	public static boolean isEmpty(String str) {
		return (str == null || EMPTY_STRING.equals(str));
	}
	
	public static String getDefaultTitle() {
		String name = "";
		switch (Config.TITLE_SHOWS_CATEGORY) {
		case BY_ITSELF: {
			name = sDefaultLocale.getDisplayName(sDefaultLocale);
		}
			break;
		case BY_CUSTOM: {
			name = sDefaultLocale.getDisplayName(sCustomLocale);
		}
			break;
		case BY_SYSTEM_SETTING: {
			name = sDefaultLocale.getDisplayName(Locale.getDefault());
		}
			break;
		case BY_DEFAULT:
		default: {
			name = sDefaultLocale.getDisplayName(sDefaultLocale);
		}
			break;
		}
		return name;
	}
	
	public static String getLanguageTitle(String locale) {
		String name = "";
		Locale l = instanceLocale(locale);
		switch (Config.TITLE_SHOWS_CATEGORY) {
		case BY_ITSELF: {
			name = l.getDisplayName(l);
		}
			break;
		case BY_CUSTOM: {
			name = l.getDisplayName(sCustomLocale);
		}
			break;
		case BY_SYSTEM_SETTING: {
			name = l.getDisplayName(Locale.getDefault());
		}
			break;
		case BY_DEFAULT:
		default: {
			name = l.getDisplayName(sDefaultLocale);
		}
			break;
		}
		return name;
	}
	
	public static Locale instanceLocale(String str) {
    	if (isEmpty(str)) {
    		return null;
    	}
    	String[] arr = str.split("_", 3);
    	if (arr.length <= 1) {
    		arr = str.split("-", 3);
    	}
    	String language = "";
    	String country = ""	;
    	String variant = "";
    	
    	if (arr.length >= 1){
    		language = arr[0];
    	}
    	if (arr.length >= 2){
    		country = arr[1];
    		if (country.startsWith("r")) {
    			country = country.substring(1, country.length());
    		}
    	}
    	if (arr.length >= 3){
    		variant = arr[2];
    	}
    	return new Locale(language, country, variant);
    }
}
