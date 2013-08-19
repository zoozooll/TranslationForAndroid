/**
 * 
 */
package com.mouselee.translation.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.mouselee.translation.vo.XMLFile;
import com.mouselee.translation.vo.XMLItems;

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
		
	}
	
	public void write(String xmlPath, XMLFile note) throws DocumentException, IOException {
		write(new File(xmlPath), note);
		
	}
	
	public void write(File xmlPath, XMLFile note) throws DocumentException, IOException {
		System.out.println("xmlPath "+ xmlPath);
		OutputFormat format=OutputFormat.createPrettyPrint();
		writer = new XMLWriter(new FileWriter(xmlPath), format);
		Document document = createDocument(note);
		writer.write(document);
		writer.close();
	}
	
	private Document createDocument(XMLFile note) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement(note.getRoot());
		List<XMLItems> items = note.getStrings();
		if (items != null) {
			for (XMLItems item : items) {
				loopForAddElements(item, root);
			}
		}
		return document;
	}
	
	private void loopForAddElements(XMLItems item, Element root) {
		String tagName = item.getTagName();
		String type = item.getType();
		Object value =  item.getValue();
		if (value instanceof String) {
			root.addElement(type).addAttribute("name", tagName).addText((String) value);
		}
		if (value instanceof List) {
			List<String> strs = (List<String>) value;
			Element arrayElement =  root.addElement(type);
			addElementForStringArray(strs, arrayElement);
		}
	}

	private void addElementForStringArray(List<String> strs, Element arrayElement) {
		for (String value : strs) {
			if (value != null) {
				arrayElement.addElement("item").addText(value);
			}
		}
	}
}
