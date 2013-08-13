/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.File;
import java.util.List;

import com.mouselee.translation.vo.Language;
import com.mouselee.translation.vo.Project;
import com.mouselee.translation.vo.Translations;

/**
 * @author aaronli
 *
 */
public class XmlWriter {
	private String xmlsPath;

	public void setXlsPath(String xmlsPath) {
		this.xmlsPath = xmlsPath;
	}
	
	public void writeToXls(Translations translations) {
		File root = new File(xmlsPath);
		if(checkFolder(root)) {
			createProjectFiles(root, translations);
		}
	}

	private void createProjectFiles(File root, Translations translations) {
		List<Project> list = translations.getProjects();
		if (list == null) {
			return;
		}
		for (Project p : list) {
			String projectName = p.getProjectName();
			if (projectName != null && !("").equals(projectName)) {
				File projectFile = new File(root, projectName);
				if (checkFolder(projectFile)) {
					createLanguageFolders(projectFile, p);
				}
				
			}
		}
	}
	
	
	private void createLanguageFolders(File projectFile, Project p) {
		List<Language> list = p.getLanguageList();
		if (list == null) {
			return;
		}
		for (Language l : list) {
			String languageName = l.getLanguageName();
			if (languageName != null && !("").equals(languageName)) {
				File languageDirector = mkLanguageDirectories(projectFile, languageName);
				createXmlFiles(languageDirector, l);
			}
		}
	}

	private void createXmlFiles(File languageDirector, Language language) {
		// TODO Auto-generated method stub
		
	}

	private File mkLanguageDirectories(File projectFile, String languageName) {
		File folder = null;
		if ("default".equals(languageName)) {
			folder = new File(projectFile, "value");
		} else {
			folder = new File(projectFile, "value" + languageName);
		}
		if (checkFolder(folder)) {
			return folder;
		}
		return null;
	}

	private boolean checkFolder(File checked) {
		if (!checked.exists()) {
			checked.mkdir();
		}
		if (!checked.isDirectory()) {
			System.out.println("This xmlsPath is not a folder");
			return false;
		}
		return true;
	}
}
