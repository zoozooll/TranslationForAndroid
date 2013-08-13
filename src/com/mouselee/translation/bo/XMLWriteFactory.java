/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mouselee.translation.vo.XMLFile;

/**
 * @author zoozo_000
 *
 */
public class XMLWriteFactory {
	private static XMLWriteFactory instance;
	private XMLWriter writer;
	
	public static synchronized XMLWriteFactory instance() {
		if (instance == null) {
			instance = new XMLWriteFactory();
		}
		return instance;
	}
	
	private XMLWriteFactory() {
		super();
		writer = new XMLWriter();
	}
	
	public void write(String xmlPath, XMLFile note) throws DocumentException {
		write(new File(xmlPath), note);
		
	}
	
	public void write(File xmlPath, XMLFile note) throws DocumentException {
		Document doc = reader.read(xmlPath);
		treeWalk(doc, note);
	}
}
