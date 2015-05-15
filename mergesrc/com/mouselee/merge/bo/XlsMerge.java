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
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
			parsePerMIERow (row, miexls);
		}
		return miexls;
	}
	
	private void parsePerMIERow (Row row, Map<String , Map<String, String>> item) {
		if (item == null) 
			item = new HashMap<String , Map<String, String>> ();
		Map<String, String> unit;
		String itemId = null;
		unit = new HashMap<String, String>();
		for (int i = 0; i <= row.getLastCellNum(); i ++) {
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
				String languageStringValue = row.getSheet().getRow(0).getCell(i).getStringCellValue();
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
			Cell curCell = curRow.getCell(i); 
			if (curCell == null) {
				curCell = curRow.createCell(i);
			}
			String value = curCell.getStringCellValue();
			Cell languageCell = firstRow.getCell(i);
			if (languageCell == null) {
				continue;
			}
			String languageKey = languageCell.getStringCellValue();
			String translatedValue = map.get(languageKey);
			if (translatedValue != null && !translatedValue.equals(value)) {
				// find out difference;
				//createDiffCellStyle(curCell);
				curCell.setCellValue(translatedValue);
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
					cell.setCellValue(EMPTY_STRING);
				} else {
					String value = cell.getStringCellValue();
					if (value == null || value.trim().equals("")) {
						cell.setCellValue(EMPTY_STRING);
					}
				}
			}
		}
	}
	
	private static Map<String, String> sLangsMap = new HashMap<String, String>();
	
	static {
		sLangsMap.put("CHINE_NEW","zh-rCN"); //简体中文
		sLangsMap.put("CHINE_OLD","zh-rHK"); //繁体中文
		sLangsMap.put("ENGLISH","default");      //英语
		sLangsMap.put("FRENCH","fr");       //法语
		sLangsMap.put("DUTCH","nl");       //荷兰
		sLangsMap.put("GERMAN","de");      //德国
		sLangsMap.put("GREEK","el");       //希腊
		sLangsMap.put("HUNGARIAN","hu");   //匈牙利
		sLangsMap.put("ITALIAN","it");     //意大利
		sLangsMap.put("PORTUGUESE","pt");  //葡萄牙
		sLangsMap.put("SPANISH","es");     //西班牙
		sLangsMap.put("TURKISH","tr");     //土耳其
		sLangsMap.put("POLISH","pl");      //波兰
		sLangsMap.put("CZECH","cs");       //捷克
		sLangsMap.put("MALAY","ms");       //马来语
		sLangsMap.put("INDONESIAN","in");  //印尼
		sLangsMap.put("SLOVAK","sk");      //斯洛伐克
		sLangsMap.put("ROMANIAN","ro");    //罗马尼亚
		sLangsMap.put("SLOVENIAN","sl");   //斯洛文尼亚
		sLangsMap.put("THAI","th");        //泰国
		sLangsMap.put("SERBIAN","sr");     //塞尔维亚
		sLangsMap.put("GALICIAN","gl");    //加利西亚
		sLangsMap.put("VIETNAMESE","vi");  //越南
		sLangsMap.put("BRAZILIAN","pt-rBR");//巴西
		sLangsMap.put("JAPANESE","ja");    //日语
		sLangsMap.put("LATINESP","es-rLA");  //拉丁西班牙语
		sLangsMap.put("FARSI","fa");        //波斯
		sLangsMap.put("CROATIAN","hr");	//克罗地亚
		sLangsMap.put("RUSSIAN","ru");	    //俄语
		// IDOL3 与 MIE 差异
		sLangsMap.put("ARABIC","ar");            //阿拉拍语
		sLangsMap.put("CATALAN","ca");           //加泰罗尼亚
		sLangsMap.put("DANISH","da");            //丹麦
		sLangsMap.put("FINNISH","fi");           //芬兰
		sLangsMap.put("FRENCH_CA","fr-rCA");     //法语-加拿大
		sLangsMap.put("NORWEGIAN","no");         //挪威
		sLangsMap.put("SWEDISH","sv");           //瑞典
		sLangsMap.put("EUSKERA","eu");           //巴斯克
		// IDOL3 新增语言
		sLangsMap.put("ALBANIAN","sq");          //阿尔巴尼亚文
		sLangsMap.put("BENGALI","bn-rBD");       // 孟加拉
		sLangsMap.put("BULGARIAN","bg");         //保加利亚语
		sLangsMap.put("CAMBODIAN","km-rKH");     //柬埔寨
		sLangsMap.put("ESTONIAN","et");          //爱沙尼亚语
		sLangsMap.put("HEBREW","he");            //希伯来语
		sLangsMap.put("KOREAN","ko");            //朝鲜语
		sLangsMap.put("LAOTIAN","lo-rLA");       //老挝语
		sLangsMap.put("LATVIAN","lv");           //拉脱维亚语
		sLangsMap.put("LITHUANIAN","lt");        //立陶宛
		sLangsMap.put("MACEDONIAN","mk");        //马其顿
		sLangsMap.put("MYANMAR","my-rMM");       //缅甸
		sLangsMap.put("UKRAINIAN","uk");         //乌克兰语
		sLangsMap.put("Hindi","hi");
	}
}
