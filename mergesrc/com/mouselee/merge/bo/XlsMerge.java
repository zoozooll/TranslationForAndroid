/**
 * 
 */
package com.mouselee.merge.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import bean.MIEXls;
import bean.TranslateUnit;
import bean.TranslationItem;

import com.mouselee.translation.vo.Language;
import com.mouselee.translation.vo.Project;

/**
 * @author zuokang.li
 *
 */
public class XlsMerge {

	/* The first column of translation characters */
	private int  firstContentColumnIndex = -1;
	
	private List<String> descriptionTitles;
	
	private Map<Integer, String> contentTitles;
 	
	/**
	 * 
	 * @param origanalXls
	 * @param rebackXls
	 * @param outputXls
	 */
	public void mergeRebackXls(File originalXls,File rebackXls, File outputXls) {
		
	}
	
	private List<MIEXls> parseXls(File xlsFile) {
		List<MIEXls> result;
		try{
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(xlsFile));
			Project project = null;
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				//parse per sheet.
				Sheet sheet = workBook.getSheetAt(numSheet);
				if (sheet == null) {
					continue;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private MIEXls parsePerXlsSheet(Sheet sheet) {
		// parse the first row. 
		Row firstRow = sheet.getRow(0);
		if (firstRow == null) {
			return null;
		}
		
		for (int i = 2; i <= firstRow.getLastCellNum(); i ++) {
			Cell cell = firstRow.getCell(i);
			if (cell == null) {
				continue;
			}
			if ("English".equalsIgnoreCase(cell.getStringCellValue())) {
				firstContentColumnIndex = i;
				break;
			} else {
				if (descriptionTitles == null) {
					descriptionTitles = new ArrayList<String>();
				} else {
					descriptionTitles.clear();
				}
				
				descriptionTitles.add(cell.getStringCellValue());
			}
		}
		
		//parse sheet's content from the second row.
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			
		}
		return list;
	}
	
	private TranslationItem parsePerMIERow (Row row) {
		TranslateUnit unit;
		for (int i = 2; i <= row.getLastCellNum(); i ++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				continue;
			}
			if ( i >= firstContentColumnIndex) {
				unit = new TranslateUnit();
				unit.setCharacters(characters);
			}
		}
	}
}
