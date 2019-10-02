/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import MQApi.Models.Query.ConnectionDetailModel;
import UI.MainWindow;
import UI.Models.MQRC.MQRCParentModel;
import UI.Models.SettingsModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

/**
 *
 * @author jzhou
 */
public class XMLHelper {
    private static String elementName = "ConnectionDetail";
    private static String rootName = "ConnectionSetting";
    private static String settingPath = "Settings.xml";
    public static void WriteConnectionModelToXml(ArrayList<ConnectionDetailModel> models, String filePath){
        
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder db = dbf.newDocumentBuilder();       
            Document document = db.newDocument();
            Element rootElement = document.createElement(rootName);
            for(ConnectionDetailModel model : models){
                Element connectionEle = document.createElement(elementName);
                for(Field field : model.getClass().getFields()){
                    String name = field.getName();
                    String value = field.get(model) != null ? (field.get(model)).toString() : null;
                    if(value != null){
                        Element ele = document.createElement(name);
                        ele.appendChild(document.createTextNode(value));
                        connectionEle.appendChild(ele);
                    }
                }
                rootElement.appendChild(connectionEle);
            }
            document.appendChild(rootElement);
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filePath)));
        } catch (Exception ex) {
            Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<ConnectionDetailModel> ReadConnectionModelFromXml(String filePath){
        ArrayList<ConnectionDetailModel> models = new ArrayList<ConnectionDetailModel>();
         try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(filePath);
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, file.getAbsolutePath().toString(), new NullPointerException());

            if(file.exists() && !file.isDirectory()) {
               FileInputStream fileStream = new FileInputStream(file);
               Document document = db.parse(fileStream);
               NodeList nodes = document.getElementsByTagName(elementName);
               for(int i = 0; i < nodes.getLength(); i++){
                   Node node = nodes.item(i);
                   ConnectionDetailModel model = new ConnectionDetailModel();
                   for(Field field : model.getClass().getFields()){
                       String fieldName = field.getName();
                       String fieldValue = getNodeValue(node, fieldName);
                       field.set(model, fieldValue);
                   }
                   models.add(model);
               }               
            }

         }catch(Exception ex){
             Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
         }
        return models;
    }

    public static SettingsModel ReadSettingsModelFromXml(){
        SettingsModel model = new SettingsModel();
        model.SkinName = "Acryl";
         try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            File file = new File(settingPath);
            if(file.exists() && !file.isDirectory()) {
               FileInputStream fileStream = new FileInputStream(file);
               Document document = db.parse(fileStream);
               NodeList nodes = document.getElementsByTagName("Skin");
               model.SkinName = nodes.item(0).getTextContent();              
            }

         }catch(Exception ex){
             Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
         }
        return model;
    }
    public static void WriteSettingModelToXml(SettingsModel model){
        
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder db = dbf.newDocumentBuilder();       
            Document document = db.newDocument();
            Element rootElement = document.createElement("Settings");
            Element skinEle = document.createElement("Skin");
            skinEle.appendChild(document.createTextNode(model.SkinName));
            rootElement.appendChild(skinEle);
            document.appendChild(rootElement);
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(settingPath)));
        } catch (Exception ex) {
            Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void WriteMQRCModelToXml(){
        MQRCParentModel model = new MQRCParentModel();
        String filePath = "MQRCDefValue.xml";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            
            DocumentBuilder db = dbf.newDocumentBuilder();       
            Document document = db.newDocument();
            Element rootElement = document.createElement("MQRCModelDefValues");
            for(Field field : model.getClass().getFields()){
                String name = field.getName();
                Element modelEle = document.createElement(name);
                Object childModel = field.get(model);
                for(Field modelField : childModel.getClass().getFields()){
                    String propertyName = modelField.getName();
                    String value = modelField.get(childModel) != null ? (modelField.get(childModel)).toString() : " ";
                    Element ele = document.createElement(propertyName);
                    ele.appendChild(document.createTextNode(value));
                    modelEle.appendChild(ele);
                }
                rootElement.appendChild(modelEle);
            }
            document.appendChild(rootElement);
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.transform(new DOMSource(document), new StreamResult(new FileOutputStream(filePath)));
        } catch (Exception ex) {
            Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String XMLStringFormat(String xml) {

        try {
            final InputSource src = new InputSource(new StringReader(xml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));

            //System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
            final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
            final LSSerializer writer = impl.createLSSerializer();

            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE); // Set this to true if the output needs to be beautified.
            writer.getDomConfig().setParameter("xml-declaration", keepDeclaration); // Set this to true if the declaration is needed to be outputted.
            return writer.writeToString(document);
        } catch (Exception e) {
            return xml;
            //throw new RuntimeException(e);
        }
    }
    
    public static boolean IsXML(String xml) {
         try {
            final InputSource src = new InputSource(new StringReader(xml));
            final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private static String getNodeValue(Node node, String fieldName){
        Node targetNode = findNode(node, fieldName);
        
        return targetNode != null? targetNode.getTextContent() : null;
    }
     
    private static Node findNode (Node node, String fieldName){
         NodeList Nodes = node.getChildNodes();
        for(int i = 0; i < Nodes.getLength(); i++){
            Node fieldNode = Nodes.item(i);
            String name = fieldNode.getNodeName();
            if(name != null && name == fieldName){
                return fieldNode;
            }
        }        
        return null;       
    }
}
