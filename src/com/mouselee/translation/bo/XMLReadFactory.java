/**
 * 
 */
package com.mouselee.translation.bo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.mouselee.translation.vo.XMLItems;
import com.mouselee.translation.vo.XMLFile;

/**
 * @author aaronli
 *
 */
public class XMLReadFactory {
	
	private static XMLReadFactory instance;
	
	private SAXReader reader;
	private String currentRoot;
	private String currentElementName;
	
	public static synchronized XMLReadFactory instance() {
		if (instance == null) {
			instance = new XMLReadFactory();
		}
		return instance;
	}
	
	private XMLReadFactory() {
		super();
		reader = new SAXReader();
	}

	public void parse(String xmlPath, XMLFile note) throws DocumentException {
		parse(new File(xmlPath), note);
		
	}
	
	public void parse(File xmlPath, XMLFile note) throws DocumentException {
		Document doc = reader.read(xmlPath);
		treeWalk(doc, note);
	}
	
	public void treeWalk(Document document, XMLFile note) {
    	Element root = document.getRootElement();
    	currentRoot = root.getName();
    	note.setRoot(currentRoot);
    	System.out.println("note root "+note.getRoot());
        note.setStrings(treeWalk(root)) ;
    }

    public List<XMLItems> treeWalk(Element element) {
    	List<XMLItems> items = new ArrayList<XMLItems>();
    	XMLItems str;
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                Element current = (Element) node;
				/*System.out.println(String.format("%s \'s %s-->%s text --> %s",
						current.getName(), current.attribute(0).getName(),
						current.attribute(0).getValue(), current.getText()));*/
				if ("resources".equals(currentRoot)) {
					if ("string".equals(current.getName())) {
						str = parseStringItem(current);
						items.add(str);
					}
					if ("string-array".equals(current.getName())) {
						str = parseStringArrayItem(current);
						items.add(str);
					}
				} else if ("timezones".equals(currentRoot)) {
					str =parseTimeZoneItem(current);
					items.add(str);
				}
                
            } else {
                
            }
        }
        return items;
    }
    
    private XMLItems<String> parseStringItem(Element current) {
    	XMLItems<String> str = new XMLItems<String>();
    	str.setTagName(current.attribute(0).getValue());
    	str.setType(current.getName());
    	str.setValue(current.getTextTrim());
    	return str;
    }
    
    private XMLItems<List<String>> parseStringArrayItem(Element current) {
    	XMLItems<List<String>> str = new XMLItems<List<String>>();
    	str.setTagName(current.attribute(0).getValue());
    	str.setType(current.getName());
    	List<String> strs = new ArrayList<String>();
    	for (Object e : current.elements()) {
    		if (e instanceof Element &&  ("item").endsWith(((Element)e).getName())) {
    			strs.add(((Element)e).getTextTrim());
    		}
    	};
    	str.setValue(strs);
    	return str;
    }
    
    private XMLItems<String> parseTimeZoneItem(Element current) {
    	XMLItems<String> str = new XMLItems<String>();
    	str.setTagName(current.attribute(0).getValue());
    	str.setType(current.getName());
    	str.setValue(current.getTextTrim());
    	return str;
    }
}
