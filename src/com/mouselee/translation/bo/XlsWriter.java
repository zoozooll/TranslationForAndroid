/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mouselee.translation.vo.Language;
import com.mouselee.translation.vo.Project;
import com.mouselee.translation.vo.Translations;
import com.mouselee.translation.vo.XMLItems;
import com.mouselee.translation.vo.XMLFile;

/**
 * @author aaronli
 *
 */
public class XlsWriter {
	
	private String xlsPath;
	private Translations mTranslations;
	private Workbook workbook;
	private int mRowIndex;
	private int mColIndex;

	public void setXlsPath(String xlsPath) {
		this.xlsPath = xlsPath;
	}
	
	public void writeToXls(Translations translations) {
		mTranslations = translations;
		workbook = new HSSFWorkbook();
		loopSheets(mTranslations.getProjects());
		// Write the output to a file
        FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(xlsPath);
			workbook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loopSheets(List<Project> projects) {
		for (Project p : projects ) {
			Sheet sheet = workbook.createSheet(p.getProjectName());
			mRowIndex = 0;
			List<Language> localItems = p.getLanguageList();
			if (localItems != null && localItems.size() > 0) {
				loopStringFile(sheet, localItems);
			}
		}
	}
	
	
	private void loopStringFile(Sheet sheet, List<Language> localItems) {
		// we have to loop the LocalItem 's language 'default' first.And then loop others languages except 'default'
		for (Language item : localItems) {
			if ("default".equals(item.getLanguageName())) {
				Row firstRow = sheet.createRow(mRowIndex);
				mRowIndex ++;
				
				Cell currentCell = firstRow.createCell(0);
				currentCell.setCellValue("filename");
				currentCell = firstRow.createCell(1);
				currentCell.setCellValue("root");
				currentCell = firstRow.createCell(2);
				currentCell.setCellValue("type");
				currentCell = firstRow.createCell(3);
				currentCell.setCellValue("tagname");
				currentCell = firstRow.createCell(4);
				currentCell.setCellValue("default");
				writeDefaultLanguage(sheet, item);
				break;
			}
		}
		mColIndex = 4;
		for (Language other: localItems) {
			if ("default".equals(other.getLanguageName())) {
				continue;
			}
			writeOthersLanguage(sheet, other);
		}
	}
	
	private void writeDefaultLanguage(Sheet sheet, Language localItem) {
		List<XMLFile> notes = localItem.getXmlFiles();
		if (notes != null) {
			for (XMLFile xmlNote : notes) {
				if (xmlNote.getStrings() != null) {
					writeCellInDefaultLanguage(sheet, xmlNote);
				}
			}
		}
	}
	
	
	private void writeOthersLanguage(Sheet sheet, Language localItem) {
		String language = localItem.getLanguageName();
		Row firstRow = sheet.getRow(0);
		mColIndex++;
		Cell languageCell = firstRow.createCell(mColIndex);
		languageCell.setCellValue(language);
		List<XMLFile> notes = localItem.getXmlFiles();
		if (notes != null) {
			for (XMLFile xmlNote : notes) {
				if (xmlNote.getStrings() != null) {
					writeCellInOtherLanguage(sheet, xmlNote);
				}
			}
		}
	}
	
	private void writeCellInDefaultLanguage(Sheet sheet, XMLFile xmlNote) {
		String fileName = xmlNote.getFilename();
		String root = xmlNote.getRoot();
		String tagName = null;
		String type = null;
		String value = null;
		Row row;
		
		for (XMLItems item : xmlNote.getStrings()) {
			type = item.getType();
			if (("string").equals(type)) {
				row = sheet.createRow(mRowIndex);
				mRowIndex ++;
				
				row.createCell(0).setCellValue(fileName);
				row.createCell(1).setCellValue(root);
				row.createCell(2).setCellValue(type);
				row.createCell(3).setCellValue(item.getTagName());
				row.createCell(4).setCellValue((String)item.getValue());
			} else if (("string-array").equals(type)) {
				List<String> strs = (List<String>) item.getValue();
				for (String s : strs) {
					row = sheet.createRow(mRowIndex);
					mRowIndex ++;
					
					row.createCell(0).setCellValue(fileName);
					row.createCell(1).setCellValue(root);
					row.createCell(2).setCellValue(type);
					row.createCell(3).setCellValue(item.getTagName());
					row.createCell(4).setCellValue(s);
				}
				
			} else if (("timezone").equals(type)) {
				row = sheet.createRow(mRowIndex);
				mRowIndex ++;
				
				row.createCell(0).setCellValue(fileName);
				row.createCell(1).setCellValue(root);
				row.createCell(2).setCellValue(type);
				row.createCell(3).setCellValue(item.getTagName());
				row.createCell(4).setCellValue((String)item.getValue());
			}
		}
	}
	
	private void writeCellInOtherLanguage(Sheet sheet, XMLFile xmlNote) {
		String fileName = xmlNote.getFilename();
		String root = xmlNote.getRoot();
		String type = null;
		Row row;
		
		for (XMLItems item : xmlNote.getStrings()) {
			type = item.getType();
			if (("string").equals(type)) {
				/*row = sheet.createRow(mRowIndex);
				
				row.createCell(0).setCellValue(fileName);
				row.createCell(1).setCellValue(root);
				row.createCell(2).setCellValue(type);
				row.createCell(3).setCellValue(item.getTagName());
				row.createCell(4).setCellValue((String)item.getValue());*/
				setCellStringInOtherLanguage(sheet, item);
			} else if (("string-array").equals(type)) {
				/*List<String> strs = (List<String>) item.getValue();
				for (String s : strs) {
					row = sheet.createRow(mRowIndex);
					mRowIndex ++;
					
					row.createCell(0).setCellValue(fileName);
					row.createCell(1).setCellValue(root);
					row.createCell(2).setCellValue(type);
					row.createCell(3).setCellValue(item.getTagName());
					row.createCell(4).setCellValue(s);
				}*/
				
			} else if (("timezone").equals(type)) {
				setCellStringInOtherLanguage(sheet, item);
			}
		}
	}
	
	private void setCellStringInOtherLanguage(Sheet sheet, XMLItems item) {
		String tagName = item.getTagName();
		int matchRow = 0;
		for (int i = 1, sum = sheet.getLastRowNum(); i <= sum; i ++) {
			String flagTagName = sheet.getRow(i).getCell(3).getStringCellValue();
			if (tagName.equals(flagTagName)) {
				matchRow = i;
				break;
			}
		}
		if (matchRow <= 0) {
			return;
		} else {
			Cell matchCell = sheet.getRow(matchRow).createCell(mColIndex);
			matchCell.setCellValue((String) item.getValue());
		}
	}
}
