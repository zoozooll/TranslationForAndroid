/**
 * 
 */
package com.mouselee.merge.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import bean.MIEXls;
import bean.TranslateUnit;
import bean.TranslationItem;

import com.mouselee.translation.bo.XlsReader;
import com.mouselee.translation.vo.Language;
import com.mouselee.translation.vo.Project;
import com.mouselee.translation.vo.Translations;

/**
 * @author zuokang.li
 *
 */
public class XlsMerge {

	/* The first column of translation characters */
	private int  firstContentColumnIndex = -1;
	
	/**
	 * 
	 * @param origanalXls
	 * @param rebackXls
	 * @param outputXls
	 */
	public void mergeRebackXls(String originalXls,String rebackXls, String outputXls) {
		Map<String , Map<String, String>> mieXlsList = parseMIEXls(new File(rebackXls));
		
	}
	
	
	
	private Map<String , Map<String, String>> parseMIEXls(File xlsFile) {
		Map<String , Map<String, String>> result = null;
		try{
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(xlsFile));
				//parse per sheet.
				Sheet sheet = workBook.getSheetAt(0);
				if (sheet == null) {
					return null;
				}
				result = parsePerMIESheet(sheet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private Map<String , Map<String, String>> parsePerMIESheet(Sheet sheet) {
		// parse the first row. 
		Row firstRow = sheet.getRow(0);
		if (firstRow == null) {
			return null;
		}
		
		Map<String , Map<String, String>> miexls = null;
		for (int i = 2; i <= firstRow.getLastCellNum(); i ++) {
			Cell cell = firstRow.getCell(i);
			if (cell == null) {
				continue;
			}
			if ("English".equalsIgnoreCase(cell.getStringCellValue())) {
				firstContentColumnIndex = i;
				break;
			} 
		}
		
		//parse sheet's content from the second row.
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			if (miexls == null) {
				miexls = new HashMap<String , Map<String, String>>();
			}
			parsePerMIERow (row, miexls);
		}
		return miexls;
	}
	
	private void parsePerMIERow (Row row, Map<String , Map<String, String>> item) {
		if (item == null) 
			item = new HashMap<String , Map<String, String>> ();
		List<TranslateUnit> units = new ArrayList<TranslateUnit>();
		Map<String, String> unit;
		String itemId = null;
		unit = new HashMap<String, String>();
		for (int i = 2; i <= row.getLastCellNum(); i ++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				continue;
			}
			if (i == 0) {
//				item.setRefName(cell.getStringCellValue());
			} else if (i == 1) {
				itemId = cell.getStringCellValue();
			} else if (i < firstContentColumnIndex) {
//				descriptions.put(row.getSheet().getRow(0).getCell(i).getStringCellValue(), cell.getStringCellValue());
			} else {
				unit.put(row.getSheet().getRow(0).getCell(i).getStringCellValue(), cell.getStringCellValue());
			} 
			
		}
		if (itemId != null) {
			item.put(itemId, unit);
		}
		return ;
	}
	
	
	private void compareOriginalData(File originalFile, Map<String , Map<String, String>> translatedDate, File output) {
		try{
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(originalFile));
			//parse per sheet.
			Sheet sheet = workBook.getSheetAt(0);
			if (sheet == null) {
				return ;
			}
			Row firstRow = sheet.getRow(0);
			if (firstRow == null) {
				return;
			}
			for (int i = 1, size = sheet.getLastRowNum(); i < size; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				compareOriginalCell(firstRow, row, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void compareOriginalCell(Row firstRow, Row curRow, int cellPos) {
		Cell idCell = curRow.getCell(3);
		if (idCell == null) {
			return;
		}
		String idName = idCell.getStringCellValue();
		for (int i = 4, size = curRow.getLastCellNum(); i < size; i++) {
			Value = 
		}
	}
}
