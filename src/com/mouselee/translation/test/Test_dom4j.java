package com.mouselee.translation.test;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;


public class Test_dom4j {
	public static void main(String[] args) {  
        SAXReader reader = new SAXReader();  
        File file = new File("D:\\test\\test1.xml");  
        try {  
            Document doc = reader.read(file);  
           // doc.accept(new MyVistor()); 
            Test_dom4j test = new Test_dom4j();
            test.treeWalk(doc);
        } catch (DocumentException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
    public static class MyVistor extends VisitorSupport {  
        public void visit(Attribute node) {  
            //System.out.println("Attibute:---" + node.getName() + "="+ node.getValue());  
        }  
   
        public void visit(Element node) {  
            if (node.isTextOnly()) {  
                System.out.println("Element:---" + node.getName() + "="  
                        + node.getText());  
            }else{  
                System.out.println("--------" + node.getName() + "-------");  
            }  
        }  
  
        @Override  
        public void visit(ProcessingInstruction node) {  
            System.out.println("PI:"+node.getTarget()+" "+node.getText());  
        }  
    }
    
    public void treeWalk(Document document) {
    	Element root = document.getRootElement();
    	System.out.println(root.getName());
        treeWalk(root);
    }

    public void treeWalk(Element element) {
        for ( int i = 0, size = element.nodeCount(); i < size; i++ ) {
            Node node = element.node(i);
            if ( node instanceof Element ) {
                Element current = (Element) node;
				System.out.println(String.format("%s \'s %s-->%s text --> %s",
						current.getName(), current.attribute(0).getName(),
						current.attribute(0).getValue(), current.getText()));
                
            }
            else {
                
            }
        }
    }
	
}
