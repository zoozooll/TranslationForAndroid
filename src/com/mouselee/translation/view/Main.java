package com.mouselee.translation.view;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdesktop.application.SingleFrameApplication;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
/**
 * 
 */
public class Main extends SingleFrameApplication {
    private JPanel topPanel;
    private JTextField tfdSelectXmlPath;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JTextField tfdSavedXls;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JFileChooser fIleChooser;
    private JButton btnSavedXmlPath;
    private JButton btnStartXls2Xml;
    private JTextField tfdSavedXmlPath;
    private JButton btnSelectXlsPath;
    private JTextField tfdChooseXlsPath;
    private JLabel Xls2Xml;
    private JButton btnStartXml3Xls;
    private JButton btnSavedXlsPath;
    private JButton btnSelectXmlPath;
    private JLabel jLabel1;
    private JLabel titleBar;

    @Override
    protected void startup() {
        topPanel = new JPanel();
        topPanel.setPreferredSize(new java.awt.Dimension(600, 756));
        topPanel.setLayout(null);
        {
        	titleBar = new JLabel();
        	topPanel.add(titleBar);
        	titleBar.setName("titleBar");
        	titleBar.setBounds(122, 5, 263, 28);
        }
        {
        	jLabel1 = new JLabel();
        	topPanel.add(jLabel1);
        	jLabel1.setName("jLabel1");
        	jLabel1.setBounds(29, 41, 72, 15);
        }
        {
        	tfdSelectXmlPath = new JTextField();
        	topPanel.add(tfdSelectXmlPath);
        	tfdSelectXmlPath.setBounds(29, 86, 392, 21);
        	tfdSelectXmlPath.setName("tfdSelectXmlPath");
        }
        {
        	btnSelectXmlPath = new JButton();
        	topPanel.add(btnSelectXmlPath);
        	btnSelectXmlPath.setBounds(431, 85, 69, 23);
        	btnSelectXmlPath.setName("btnSelectXmlPath");
        	btnSelectXmlPath.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnSelectXmlPathMouseClicked(evt);
        		}
        	});
        }
        {
        	jLabel2 = new JLabel();
        	topPanel.add(jLabel2);
        	jLabel2.setBounds(29, 65, 144, 15);
        	jLabel2.setName("jLabel2");
        }
        {
        	jLabel3 = new JLabel();
        	topPanel.add(jLabel3);
        	jLabel3.setBounds(29, 125, 204, 15);
        	jLabel3.setName("jLabel3");
        }
        {
        	tfdSavedXls = new JTextField();
        	topPanel.add(tfdSavedXls);
        	tfdSavedXls.setBounds(29, 146, 392, 21);
        }
        {
        	btnSavedXlsPath = new JButton();
        	topPanel.add(btnSavedXlsPath);
        	btnSavedXlsPath.setBounds(431, 145, 69, 23);
        	btnSavedXlsPath.setName("btnSavedXlsPath");
        	btnSavedXlsPath.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnSavedXlsPathMouseClicked(evt);
        		}
        	});
        }
        {
        	btnStartXml3Xls = new JButton();
        	topPanel.add(btnStartXml3Xls);
        	btnStartXml3Xls.setBounds(29, 188, 81, 23);
        	btnStartXml3Xls.setName("btnStartXml3Xls");
        	btnStartXml3Xls.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnStartXml3XlsMouseClicked(evt);
        		}
        	});
        }
        {
        	Xls2Xml = new JLabel();
        	topPanel.add(Xls2Xml);
        	Xls2Xml.setBounds(29, 263, 64, 15);
        	Xls2Xml.setName("Xls2Xml");
        }
        {
        	tfdChooseXlsPath = new JTextField();
        	topPanel.add(tfdChooseXlsPath);
        	tfdChooseXlsPath.setBounds(29, 313, 392, 21);
        }
        {
        	btnSelectXlsPath = new JButton();
        	topPanel.add(btnSelectXlsPath);
        	btnSelectXlsPath.setBounds(431, 312, 69, 23);
        	btnSelectXlsPath.setName("btnSelectXlsPath");
        	btnSelectXlsPath.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnSelectXlsPathMouseClicked(evt);
        		}
        	});
        }
        {
        	tfdSavedXmlPath = new JTextField();
        	topPanel.add(tfdSavedXmlPath);
        	tfdSavedXmlPath.setBounds(29, 388, 392, 21);
        }
        {
        	btnSavedXmlPath = new JButton();
        	topPanel.add(btnSavedXmlPath);
        	btnSavedXmlPath.setBounds(431, 387, 69, 23);
        	btnSavedXmlPath.setName("btnSavedXmlPath");
        	btnSavedXmlPath.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnSavedXmlPathMouseClicked(evt);
        		}
        	});
        }
        {
        	btnStartXls2Xml = new JButton();
        	topPanel.add(btnStartXls2Xml);
        	btnStartXls2Xml.setBounds(29, 434, 63, 23);
        	btnStartXls2Xml.setName("btnStartXls2Xml");
        	btnStartXls2Xml.addMouseListener(new MouseAdapter() {
        		public void mouseClicked(MouseEvent evt) {
        			btnStartXls2XmlMouseClicked(evt);
        		}
        	});
        }
        {
        	jLabel4 = new JLabel();
        	topPanel.add(jLabel4);
        	jLabel4.setBounds(29, 288, 132, 15);
        	jLabel4.setName("jLabel4");
        }
        {
        	jLabel5 = new JLabel();
        	topPanel.add(jLabel5);
        	jLabel5.setBounds(29, 363, 216, 15);
        	jLabel5.setName("jLabel5");
        }
        {
        	fIleChooser = new JFileChooser();
        	//topPanel.add(fIleChooser);
        	fIleChooser.setBounds(29, 11, 557, 397);
        }
        show(topPanel);
    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }
    
    private void btnSelectXmlPathMouseClicked(MouseEvent evt) {
    	System.out.println("btnSelectXmlPath.mouseClicked, event="+evt);
    	//TODO add your code for btnSelectXmlPath.mouseClicked
    }
    
    private void btnSavedXlsPathMouseClicked(MouseEvent evt) {
    	System.out.println("btnSavedXlsPath.mouseClicked, event="+evt);
    	//TODO add your code for btnSavedXlsPath.mouseClicked
    }
    
    private void btnSelectXlsPathMouseClicked(MouseEvent evt) {
    	System.out.println("btnSelectXlsPath.mouseClicked, event="+evt);
    	//TODO add your code for btnSelectXlsPath.mouseClicked
    }
    
    private void btnSavedXmlPathMouseClicked(MouseEvent evt) {
    	System.out.println("btnSavedXmlPath.mouseClicked, event="+evt);
    	//TODO add your code for btnSavedXmlPath.mouseClicked
    }
    
    private void btnStartXml3XlsMouseClicked(MouseEvent evt) {
    	System.out.println("btnStartXml3Xls.mouseClicked, event="+evt);
    	//TODO add your code for btnStartXml3Xls.mouseClicked
    }
    
    private void btnStartXls2XmlMouseClicked(MouseEvent evt) {
    	System.out.println("btnStartXls2Xml.mouseClicked, event="+evt);
    	//TODO add your code for btnStartXls2Xml.mouseClicked
    }

}
