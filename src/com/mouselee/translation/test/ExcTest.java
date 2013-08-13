package com.mouselee.translation.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
* EXCEL�ĵ�����������
* �ù����ܽ�EXCEL�ĵ��еı����Ϊ��JAVA���๹�ɵ���ݼ���
* ���EXCEL���ɶ�������.ÿ����һ��LIST��ʾ.
* EXCEL�е�����һ��LIST��ʾ,���е���������0��ʼһһ�����������LIST��;
* ����й��������,��һ��LIST��Ŷ����.
* 
*******************************************
* com.trumptech.common.fileParser.excel
* 2007-6-15
* 16:20:38
* author linfan
*******************************************
*/
public class ExcTest {

	private org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(ExcTest.class);
	
	
	private HSSFWorkbook workbook ;
	
	public ExcTest(File excelFile) throws FileNotFoundException, IOException{
	
		workbook = new HSSFWorkbook(new FileInputStream(excelFile));
	}


	/**
	* ��ñ��е����
	* @param sheetNumber �������(EXCEL �Ƕ���ĵ�,������Ҫ����������)
	* @return ��LIST���ɵ��кͱ�
	* @throws FileNotFoundException
	* @throws IOException
	*/
	public List<List> getDatasInSheet(int sheetNumber) throws FileNotFoundException, IOException{
			List<List> result = new ArrayList<List>();
		
		 //���ָ���ı�
		 HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
		
		 //������������
		 int rowCount = sheet.getLastRowNum();
		 logger.info("found excel rows count: " + rowCount);
		 if (rowCount < 1) {
		  return result;
		 }
		
		 //���ж�ȡ���
		 for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
		 
		  //����ж���
		  HSSFRow row = sheet.getRow(rowIndex);
		 
		  if (row != null) {
		  
		   List<Object> rowData = new ArrayList<Object>();
		  
		   //��ñ����е�Ԫ��ĸ���
		   int columnCount = row.getLastCellNum();
		  
		   //��ñ����и���Ԫ���е����
		   for (short columnIndex = 0; columnIndex < columnCount; columnIndex++) {
		    HSSFCell cell = row.getCell(columnIndex);
		   
		    //���ָ����Ԫ�������
		    Object cellStr = this.getCellString(cell);
		   
		    rowData.add(cellStr);
		   
		   }
		  
		   result.add(rowData);
		  }
		 }
		 return result;
	}
	
	/**
	* ��õ�Ԫ���е�����
	* @param cell
	* @return
	*/
	protected Object getCellString(HSSFCell cell){
		 Object result = null;
		 if (cell != null) {
		
		  int cellType = cell.getCellType();
		 
		  switch(cellType){
		 
		  case HSSFCell.CELL_TYPE_STRING :
		   result = cell.getRichStringCellValue().getString();
		   break;
		  case HSSFCell.CELL_TYPE_NUMERIC:
		   result=cell.getNumericCellValue();
		   break;
		  case HSSFCell.CELL_TYPE_FORMULA:
		   result = cell.getNumericCellValue();
		   break;
		  case HSSFCell.CELL_TYPE_ERROR:
		   result=null;
		   break;
		  case HSSFCell.CELL_TYPE_BOOLEAN:
		   result=cell.getBooleanCellValue();
		   break;
		  case HSSFCell.CELL_TYPE_BLANK:
		   result=null;
		   break;
		  }
		 }
		 return result;
	}
	
	public static void main(String[] args) throws Exception {
		 File file = new File("D:\\officework\\20130802\\translationtest.xls");
		 ExcTest parser = new ExcTest(file);
		 List<List> datas = parser.getDatasInSheet(0);
		
		 for(int i=0;i<datas.size();i++){//��ʾ���
		  List row = datas.get(i);
		  for(short n=0;n<row.size() ;n++){
		   Object value = row.get(n);
		   String data = String.valueOf(value);
		   System.out.print(data +"\t");
		  }
		  System.out.println();
		 }
	}
}
