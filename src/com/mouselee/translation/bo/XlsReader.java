/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mouselee.translation.vo.Language;
import com.mouselee.translation.vo.Project;
import com.mouselee.translation.vo.Translations;
import com.mouselee.translation.vo.XMLItemString;
import com.mouselee.translation.vo.XMLItems;
import com.mouselee.translation.vo.XMLFile;

/**
 * @author aaronli
 *
 */
public class XlsReader {

	protected String path;
	protected Translations mTranslations;
	
	
	public XlsReader(String path) {
		super();
		this.path = path;
	}

	/**
	 * @return the path
	 */
	public String getParsingFile() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setParsingFile(String path) {
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see com.oregonscientific.meep.translation.bo.IXlsCtrl#parseFromXmls()
	 */
	public Translations parseFromXls() {
		File file = new File(path);
		if (path != null && file.exists()) {
			mTranslations = new Translations();
			mTranslations.setmMeepVersion("Meep3.0");
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(currentTime);
			mTranslations.setDate(dateString);
			mTranslations.setProjects(getAllSheet(path));
		}
		return mTranslations;
	}
	
	protected List<Project> getAllSheet(String path){
		List<Project>  list = null;
		try{
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(path));
			list = new ArrayList<Project>();	// 
			Project project = null;
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				//鑾峰緱鎸囧畾鐨勮〃
				Sheet sheet = workBook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				project = new Project();
				project.setProjectName(sheet.getSheetName());
				// 寰幆琛孯ow
				project.setLanguageList(parsePerSheet(sheet));		//寰挵鍐呭寘鍚簡瑙ｆ瀽姣忓�sheet鐨勬柟娉�
				list.add(project);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	protected List<Language> parsePerSheet(Sheet sheet) {
		Row firstRow = sheet.getRow(0);
		if (firstRow == null) {
			return null;
		}
		List<Language> list = new ArrayList<Language>();
		Language localItem;
		for (int i = 4; i <= firstRow.getLastCellNum(); i ++) {
			localItem = new Language();
			Cell cell = firstRow.getCell(i);
			if (cell == null) {
				continue;
			}
			localItem.setLanguageName(cell.getStringCellValue());
			localItem.setXmlFiles(matchPerNote(sheet, i));
			list.add(localItem);
		}
		return list;
	}
	
	protected List<XMLFile> matchPerNote(Sheet sheet, int colNum) {
		List<XMLFile> notes = new ArrayList<XMLFile>();
		XMLFile note = null;
		String filename = null;
		String root = null;
		String type = null;
		String tagname = null;
		String value = null;
		// start to parse
		String lastFileName = "";
		String lastArrayTag = "";
		Row row;
		List<XMLItems> strings = null;
		XMLItems xmlItem = null;
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			row =  sheet.getRow(rowNum);
			filename = row.getCell(0).getStringCellValue();
			System.out.println("filename " + filename);
			root = row.getCell(1).getStringCellValue();
			type = row.getCell(2).getStringCellValue();
			tagname = row.getCell(3).getStringCellValue();
			Cell valueCell = row.getCell(colNum);
			if (valueCell != null) {
				value = valueCell.getStringCellValue();
			}
			
			if (note == null || !lastFileName.equals(filename)) {
				note = new XMLFile();
				lastFileName = filename;
				note.setFilename(filename);
				note.setRoot(root);
				notes.add(note);
				strings = new ArrayList<XMLItems>();
				note.setStrings(strings);
			}
			
			if (("string").equals(type) || ("timezone").equals(type)) {
				xmlItem = new XMLItems<String>();
				xmlItem.setType(type);
				xmlItem.setTagName(tagname);
				xmlItem.setValue(value);
				strings.add(xmlItem);
			} else if (("string-array").equals(type) || ("plurals").equals(type)) {
				List<String> items = new ArrayList<String>();
				if (xmlItem == null || !lastArrayTag.equals(tagname)) {
					xmlItem = new XMLItems<List<String>>();
					xmlItem.setType(type);
					items.add(value);
				}
				xmlItem.setTagName(tagname);
				xmlItem.setValue(items);
				strings.add(xmlItem);
			}
		}
		return notes;
	}
}
