/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes.Name;

import org.apache.commons.logging.impl.Log4JLogger;
import org.dom4j.DocumentException;

import com.mouselee.translation.vo.LocalItem;
import com.mouselee.translation.vo.MeepProject;
import com.mouselee.translation.vo.Translations;
import com.mouselee.translation.vo.XMLItems;
import com.mouselee.translation.vo.XMLNote;

/**
 * @author aaronli
 *
 */
public class XmlReader {

	private String parsePath;
	
	private Translations mTranslations;
	public static final String[] LANGUAGES = {
		"zh","en","ar", "de","el","es","fr","it","ja","ko",
		"nl","pt","ru"
		
	} ;

	public XmlReader(String parsePath) {
		super();
		this.parsePath = parsePath;
	}

	/**
	 * @param parsePath the parsePath to set
	 */
	public void setParsePath(String parsePath) {
		this.parsePath = parsePath;
	}
	
	public Translations parseStringXmls() {
		File rootPath = new File(parsePath);
		if (parsePath != null && rootPath.isDirectory()) {
			mTranslations = new Translations();
			mTranslations.setmMeepVersion("Meep3.0");
			Log.t("mTranslations.getmMeepVersion() " + mTranslations.getmMeepVersion());
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(currentTime);
			mTranslations.setDate(dateString);
			Log.t("mTranslations.getDate() " + mTranslations.getDate());
			mTranslations.setProjects(scanMeepProjects(rootPath));
		}
		return mTranslations;
	}
	
	private java.util.List<MeepProject> scanMeepProjects(File rootPath) {
		File[] files = rootPath.listFiles();
		List<MeepProject> projects = null;
		MeepProject item;
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory() && f.getName().contains("Meep")) {
					if (projects == null) {
						projects = new ArrayList<MeepProject>();
					}
					item = new MeepProject();
					item.setProjectName(f.getName());
					Log.t("MeepProject.setProjectName " + f.getName());
					item.setLocalItem(scanLocalItem(f));
					projects.add(item);
				}
			}
		}
		return projects; 
	}

	private List<LocalItem> scanLocalItem(File projectPath) {
		List<LocalItem> list = null;
		File[] files = new File(projectPath, "res").listFiles();
		LocalItem item;
		if (files != null){
			
			for(File f : files) {
				if (f.isDirectory() && f.getName().contains("value")) {
					if (list == null) {
						list = new ArrayList<LocalItem>();
					}
					String language = getLanguageString(f.getName());
					if (language == null) {
						continue;
					}
					item = new LocalItem();
					item.setLanguage(language);
					Log.t("LocalItem setLanguage " + language);
					item.setNotes(getNotes(f, language));
					list.add(item);
				}
			}
		}
		return list;
	}
	
	private List<XMLNote> getNotes(File valueFolder, String language) {
		List<XMLNote> notes = new ArrayList<XMLNote>();
		XMLNote note;
		for (File f : valueFolder.listFiles()) {
			if (f.getName().contains("string") && getFileExtension(f.getName()).equalsIgnoreCase("xml")) {
				note = new XMLNote();
				note.setFilename(getFileNameWithoutExten(f.getName()));
				Log.t("note "+note.getFilename());
				//note.setRoot(root);
				//note.setStrings(strings);
				parseXml(f, note);
				notes.add(note);
			}
		}
		String suffix;
		if (language.equals("default")) {
			suffix = "";
		} else {
			suffix = '-' + language;
		}
		File timezonesFile = new File(valueFolder.getParentFile(), "xml"+suffix);
		if (timezonesFile.isDirectory()){
			for (File f: timezonesFile.listFiles()) {
				if (f.getName().contains("timezone") && getFileExtension(f.getName()).equalsIgnoreCase("xml")) {
					note = new XMLNote();
					//note.setFilename(filename)
					//parseXml(f);
					parseXml(f, note);
					notes.add(note);
				}
			}
		}
		return notes;
	}
	
	private List<XMLItems> parseXml(File xmlFile, XMLNote note) {
		List<XMLItems> strings = null;
		try {
			XMLFactory.instance().parse(xmlFile, note);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strings;
	}
	
	public static String getLanguageString(String name) {
		int index = name.indexOf('-');
		if (index < 0) {
			return ("default");
		} else {
			name = name.substring(index + 1);
			if (isInLanguage(name)) {
				return name;
			}
		}
		return null;
	}
	
	public static String getFileExtension(String file) {
		int index = file.indexOf('.');
		if (index < 0 ) {
			return ("");
		} else {
			return file.substring(index + 1);
		}
	}
	
	/**
	 * Get file name without the extension from file's abstract path<br> 
	 * @param path file's abstract path.<br>If the file's path is folder and end with {@link File.separatorChar},it should show wrong 
	 * @return the file name without extension
	 */
	public static String getFileNameWithoutExten(String path) {
		int beginIndex = path.lastIndexOf(File.separatorChar) + 1;
		if (beginIndex < 0) {
			beginIndex = 0;
		}
		int endIndex = path.lastIndexOf('.');
		if (endIndex < beginIndex) {
			endIndex = path.length();			
		}
		return path.substring(beginIndex, endIndex);
	}
	
	public static boolean isInLanguage(String language) {
		return Arrays.binarySearch(LANGUAGES, language) >= 0;
	}

}
