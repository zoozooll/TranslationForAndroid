package com.mouselee.translation.test;
import java.util.ArrayList; 
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	public ArrayList<ArrayList<String>> readExcel(String fileName,String path) {
		ArrayList<ArrayList<String>> Row =new ArrayList<ArrayList<String>>();
		
		try {
			Workbook workBook = null;
            try {
            	workBook = new XSSFWorkbook(path+"\\"+fileName);
            } catch (Exception ex) {
            	workBook = new HSSFWorkbook(new FileInputStream(path+"\\"+fileName));
            } 
			
			for (int numSheet = 0; numSheet < workBook.getNumberOfSheets(); numSheet++) {
				//���ָ���ı�
				Sheet sheet = workBook.getSheetAt(numSheet);
				System.out.println("name:"+sheet.getSheetName());
				if (sheet == null) {
					continue;
				}
				// ѭ����Row
				for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					
					// ѭ����Cell
					ArrayList<String> arrCell =new ArrayList<String>();
					//System.out.println("arrCell:"+arrCell.size());
					for (int cellNum = 0; cellNum <= row.getLastCellNum(); cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell == null) {
							continue;
						}
						arrCell.add(getValue(cell));
					}
					Row.add(arrCell);
				}
			}
		} catch (IOException e) {
			System.out.println("e:"+e);
		}
	
		return Row;
	}

	private String getValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}

	public static void main(String[] args) {
		ReadExcel s= new ReadExcel();
		//ArrayList<ArrayList<String>> row=s.readExcel("TEST.xlsx","D:\\Program Files\\Java");
		ArrayList<ArrayList<String>> row=s.readExcel("translationtest.xls","D:\\officework\\20130802");
		System.out.println("row size:"+row.size());
		for (ArrayList<String> cell : row) {
			//System.out.println("cell size:"+cell.size());
			for (String str : cell) {
					System.out.println(str);
				}
			}
	}
}
