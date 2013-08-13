/**
 * 
 */
package com.mouselee.translation.test;

import com.mouselee.translation.bo.XlsReader;
import com.mouselee.translation.bo.XlsWriter;
import com.mouselee.translation.bo.XmlReader;
import com.mouselee.translation.vo.Translations;

/**
 * @author aaronli
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//XlsReader test = new XlsReader("D:\\officework\\20130802\\translationtest.xls");
		//Translations t = test.parseFromXls();
		//System.out.println("translation "+ t);
		XmlReader xmlTest = new XmlReader("D:\\android_code\\android_4_0\\packages\\apps");
		Translations translations =  xmlTest.parseStringXmls();
		System.out.println(translations);
		XlsWriter xlsWriter = new XlsWriter();
		xlsWriter.setXlsPath("D:\\temp\\translationtest"+System.currentTimeMillis()+".xls");
		xlsWriter.writeToXls(translations);
		
	}

}
