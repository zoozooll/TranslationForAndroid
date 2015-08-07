/**
 * 
 */
package com.mouselee.merge.bo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.ArrayUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mouselee.translation.bo.Log;
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
	
	private Map<String , Map<String, String>> mieXlsList ;
	private XSSFWorkbook workBook;
	
	private static final String EMPTY_STRING = "AABACDC,,";
	
	private static final String[] IGNORE_LOCALES = 
		{};
	
	/**
	 * 
	 * @param origanalXls
	 * @param rebackXls
	 * @param outputXls
	 */
	public void mergeRebackXls(String originalXls,String rebackXls, String outputXls) {
		mieXlsList = parseMIEXls(new File(rebackXls));
		System.out.println(mieXlsList);
		File originalFile = new File(originalXls);
		if (!originalFile.exists())
		{
			return;
		}
		File output = new File(outputXls);
		if (!output.getParentFile().exists()) {
			output.getParentFile().mkdirs();
		}
		compareOriginalData(originalFile, mieXlsList, output);
	}
	
	
	
	private Map<String , Map<String, String>> parseMIEXls(File xlsFile) {
		Map<String , Map<String, String>> result = null;
		try{
			XSSFWorkbook workBook = new XSSFWorkbook(new FileInputStream(xlsFile));
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
		
		//parse sheet"s content from the second row.
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				continue;
			}
			if (miexls == null) {
				miexls = new HashMap<String , Map<String, String>>();
			}
			parsePerMIERow (row, firstRow, miexls);
		}
		return miexls;
	}
	
	private void parsePerMIERow (Row row, Row firstRow, Map<String , Map<String, String>> item) {
		if (item == null) 
			item = new HashMap<String , Map<String, String>> ();
		Map<String, String> unit;
		String itemId = null;
		unit = new HashMap<String, String>();
		for (int i = 0; i <= firstRow.getLastCellNum(); i ++) {
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
				System.out.println(row.getRowNum() +"," + i);
				Cell firstCell = firstRow.getCell(i);
				if (firstCell == null) {
					continue;
				}
				String languageStringValue = firstCell.getStringCellValue();
				String key =  sMIELanguageKeys(languageStringValue);
				if (key != null) unit.put(key, cell.getStringCellValue());
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
			for (int i = 1, size = sheet.getLastRowNum(); i <= size; i++) {
				Row row = sheet.getRow(i);
				if (row == null) {
					continue;
				}
				compareOriginalCell(firstRow, row, i);
			}
			checkEmpty(sheet);
			setOutput(workBook, output);
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
		
		Map<String, String> map = mieXlsList.get(idName);
		if (map == null) {
			return;
		}
		for (int i = 4, size = firstRow.getLastCellNum(); i <= size; i++) {
			Cell languageCell = firstRow.getCell(i);
			if (languageCell == null) {
				continue;
			}
			String languageKey = languageCell.getStringCellValue();
			if (contains(IGNORE_LOCALES, languageKey) > -1) {
				continue;
			}
			Cell curCell = curRow.getCell(i); 
			if (curCell == null) {
				curCell = curRow.createCell(i);
			}
			String value = curCell.getStringCellValue();
			String translatedValue = map.get(languageKey);
			if (translatedValue != null && !translatedValue.trim().equals("") && !translatedValue.equals(value)) {
				// find out difference;
				//createDiffCellStyle(curCell);
				curCell.setCellValue(translatedValue);
				System.out.println(idName+" )"+languageKey+" value from " +value + " ====> "+translatedValue);
			}
		}
	}
	
	private void setOutput(HSSFWorkbook workBook, File output) {
		try {
			FileOutputStream fileOut = new FileOutputStream(output);
			workBook.write(fileOut);
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createDiffCellStyle( Cell cell) {
		HSSFWorkbook workbook  = (HSSFWorkbook) cell.getSheet().getWorkbook();
		CellStyle style = workbook.createCellStyle();

	    style.setFillBackgroundColor(HSSFColor.RED.index);
	    cell.setCellStyle(style);
	    
	}
	
	
	private String sMIELanguageKeys(String mieKey) {
		return sLangsMap.get(mieKey);
	}
	
	private void checkEmpty(Sheet sheet) {
		for (int i = 0, rowCount = sheet.getLastRowNum(); i <= rowCount; i ++) {
			Row row = sheet.getRow(i);
			for (int j = 0, columnCount = row.getLastCellNum(); j <= columnCount; j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
					cell = row.createCell(j);
//					createDiffCellStyle(cell);
//					cell.setCellValue(EMPTY_STRING);
				} else {
					String value = cell.getStringCellValue();
					if (value == null || value.trim().equals("")) {
//						cell.setCellValue(EMPTY_STRING);
					}
				}
			}
		}
	}
	
	private static <T> int contains (T[] array, T item) {
		int i = 0;
		for (T t : array) {
			if (t.equals(item)) {
				break;
			}
			i ++;
		}
		if (i >= array.length) {
			i = -1;
		}
		return i;
	}
	
	private static Map<String, String> sLangsMap = new HashMap<String, String>();
	
	static {
		sLangsMap.put("Chinese_CN", "zh-rCN"); //简体中文
		sLangsMap.put("Chinese_HK", "zh-rHK"); //繁体中文
		sLangsMap.put("Chinese_TW", "zh-rTW"); //繁体中文
		sLangsMap.put("English"   , "default");      //英语
		sLangsMap.put("French"    , "fr");       //法语
		sLangsMap.put("Dutch"     , "nl");       //荷兰
		sLangsMap.put("German"    , "de");      //德国
		sLangsMap.put("Greek"     , "el");       //希腊
		sLangsMap.put("Hungarian" , "hu");   //匈牙利
		sLangsMap.put("Italian"   , "it");     //意大利
		sLangsMap.put("Portuguese", "pt");  //葡萄牙
		sLangsMap.put("Spanish"   , "es");     //西班牙
		sLangsMap.put("Turkish"   , "tr");     //土耳其
		sLangsMap.put("Polish"    , "pl");      //波兰
		sLangsMap.put("Czech"     , "cs");       //捷克
		sLangsMap.put("Malay"     , "ms");       //马来语
		sLangsMap.put("Indonesian", "in");  //印尼
		sLangsMap.put("Slovak"    , "sk");      //斯洛伐克
		sLangsMap.put("Romanian"  , "ro");    //罗马尼亚
		sLangsMap.put("Slovenian" , "sl");   //斯洛文尼亚
		sLangsMap.put("Thai"      , "th");        //泰国
		sLangsMap.put("Serbian"   , "sr");     //塞尔维亚
		sLangsMap.put("Galician"  , "gl");    //加利西亚
		sLangsMap.put("Vietnamese", "vi");  //越南
		sLangsMap.put("Brazilian" , "pt-rBR");//巴西
		sLangsMap.put("Japanese"  , "ja");    //日语
		sLangsMap.put("Latinesp"  , "es-rLA");  //拉丁西班牙语
		sLangsMap.put("Farsi"     , "fa");        //波斯
		sLangsMap.put("Croatian"  , "hr");	//克罗地亚
		sLangsMap.put("Russian"   , "ru");	    //俄语
		sLangsMap.put("Arabic"    , "ar");            //阿拉拍语
		sLangsMap.put("Catalan"   , "ca");           //加泰罗尼亚
		sLangsMap.put("Danish"    , "da-rDK");            //丹麦
		sLangsMap.put("Finnish"   , "fi");           //芬兰
		sLangsMap.put("French_CA" , "fr-rCA");     //法语-加拿大
		sLangsMap.put("Norwegian" , "nb-rNO");         //挪威
		sLangsMap.put("Swedish"   , "sv");           //瑞典
		sLangsMap.put("Euskera"   , "eu");           //巴斯克
		sLangsMap.put("Albanian"  , "sq");          //阿尔巴尼亚文
		sLangsMap.put("Bengali"   , "bn-rBD");       // 孟加拉
		sLangsMap.put("Bulgarian" , "bg");         //保加利亚语
		sLangsMap.put("Cambodian" , "km-rKH");     //柬埔寨
		sLangsMap.put("Estonian"  , "et");          //爱沙尼亚语
		sLangsMap.put("Hebrew"    , "iw");            //希伯来语
		sLangsMap.put("Korean"    , "ko");            //朝鲜语
		sLangsMap.put("Laotian"   , "lo-rLA");       //老挝语
		sLangsMap.put("Latvian"   , "lv");           //拉脱维亚语
		sLangsMap.put("Lithuanian", "lt");        //立陶宛
		sLangsMap.put("Macedonian", "mk");        //马其顿
		sLangsMap.put("Myanmar"   , "my-rMM");       //缅甸
		sLangsMap.put("Ukrainian" , "uk");         //乌克兰语
		sLangsMap.put("Hindi"     , "hi");
	}
	//Thai	Spanish	Latinesp	French	French_CA	German	Russian	Slovak	Italian	Arabic	Portuguese	Turkish	Vietnamese	Indonesian	Malay	Hindi	Danish	Czech	Polish	Hungarian	Finnish	Dutch	Swedish	Croatian	Romanian	Slovenian	Greek	Hebrew	Bulgarian	Ukrainian	Brazilian	Catalan	Farsi	Serbian	Korean	Japanese	Norwegian	Macedonian	Galician	Euskera	Albanian	Bengali	Cambodian	Myanmar	Latvian	Lithuanian	Estonian	Laotian	Urdu

}
