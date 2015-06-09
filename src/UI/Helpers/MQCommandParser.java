/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import MQApi.Logs.LogWriter;
import MQApi.TextWriter;
import UI.Models.MQOuput.MQOutputTopicModel;
import UI.Models.MQOuput.MQOutputProcessModel;
import UI.Models.MQOuput.MQOutputQueueModel;
import UI.Models.MQOuput.MQOutputListenerModel;
import UI.Models.MQOuput.MQOutputSubModel;
import UI.Models.MQOuput.MQOutputChannelModel;
import UI.Models.MQOuput.MQOuputServiceModel;
import UI.Models.MQOuput.MQOuputModel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JProgressBar;

/**
 *
 * @author jzhou
 */
public class MQCommandParser {
    
    public static void ProcessFiles(File[] inputPaths, String outputPath, JProgressBar progressBar){
        for(int i = 0; i < inputPaths.length; i++){
            readFile(inputPaths[i].getPath(), outputPath);
            if(progressBar != null){
                progressBar.setValue(((i + 1) * 100) / inputPaths.length);
            }
        }
    }
    
    private static void readFile(String path, String output){
        FileReader input = null;
        MQOuputModel model = new MQOuputModel();
        try {
            input = new FileReader(path);
            BufferedReader bufRead = new BufferedReader(input);
            String line = null;
            String queueManager = null;
            while ( (line = bufRead.readLine()) != null)
            {
                while(line.endsWith("-")){
                    line = line.replace("-","") +  bufRead.readLine();
                }
                line = line.trim();
                if(line.replace("*","").trim().startsWith("QMNAME") && queueManager == null){
                    queueManager = getPropertyValue(line);
                }
                if(line.contains("DEFINE QLOCAL") || line.contains("DEFINE QREMOTE") || line.contains("DEFINE QALIAS") || line.contains("DEFINE QMODEL")){
                    MQOutputQueueModel queueModel = new MQOutputQueueModel();
                    queueModel.QMGR = queueManager;
                    if(line.contains("DEFINE QLOCAL"))
                        queueModel.TYPE = "LOCAL";
                    else if(line.contains("DEFINE QREMOTE"))
                        queueModel.TYPE = "REMOTE";
                    else if(line.contains("DEFINE QALIAS"))
                        queueModel.TYPE = "ALIAS";
                    else if(line.contains("DEFINE QMODEL"))
                        queueModel.TYPE = "MODEL";
                    queueModel.NAME = getPropertyValue(line);
                    assignValue(queueModel, bufRead);
                    model.queueModelList.add(queueModel);
                }
                
                if(line.contains("DEFINE CHANNEL")){
                    MQOutputChannelModel channelModel = new MQOutputChannelModel();
                    channelModel.QMGR = queueManager;
                    assignChannelNameAndType(channelModel, line);
                    assignValue(channelModel, bufRead);
                    model.channelModelList.add(channelModel);
                }
                if(line.contains("DEFINE PROCESS")){
                    MQOutputProcessModel processModel = new MQOutputProcessModel();
                    processModel.QMGR = queueManager;
                    processModel.NAME = getPropertyValue(line);
                    assignValue(processModel, bufRead);
                    model.processModelList.add(processModel);
                }
                if(line.contains("DEFINE LISTENER")){
                    MQOutputListenerModel listenerModel = new MQOutputListenerModel();
                    listenerModel.QMGR = queueManager;
                    listenerModel.NAME = getPropertyValue(line);
                    assignValue(listenerModel, bufRead);
                    model.ListenerModelList.add(listenerModel);
                }
                if(line.contains("DEFINE SERVICE")){
                    MQOuputServiceModel processModel = new MQOuputServiceModel();
                    processModel.QMGR = queueManager;
                    processModel.NAME = getPropertyValue(line);
                    assignValue(processModel, bufRead);
                    model.ServiceModelList.add(processModel);
                }
                if(line.contains("DEFINE TOPIC")){
                    MQOutputTopicModel topicModel = new MQOutputTopicModel();
                    topicModel.QMGR = queueManager;
                    topicModel.NAME = getPropertyValue(line);
                    assignValue(topicModel, bufRead);
                    model.TopicModelList.add(topicModel);
                }
                if(line.contains("DEFINE SUB")){
                    MQOutputSubModel subModel = new MQOutputSubModel();
                    subModel.QMGR = queueManager;
                    subModel.NAME = getPropertyValue(line);
                    assignValue(subModel, bufRead);
                    model.SubModelList.add(subModel);
                }
            }
            writeFile(output,model);
        } catch (FileNotFoundException ex) {
            LogWriter.WriteToLog(ex.fillInStackTrace());
            Logger.getLogger(MQCommandParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LogWriter.WriteToLog(ex.fillInStackTrace());
            Logger.getLogger(MQCommandParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                LogWriter.WriteToLog(ex.fillInStackTrace());
                Logger.getLogger(MQCommandParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    private static void writeFile(String path, MQOuputModel model){
        try {
            writeModelToCSV((ArrayList<Object>)(Object)model.queueModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.channelModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.TopicModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.SubModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.processModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.ServiceModelList, path);
            writeModelToCSV((ArrayList<Object>)(Object)model.ListenerModelList, path);
        } catch (IOException ex) {
            LogWriter.WriteToLog(ex.fillInStackTrace());
            Logger.getLogger(MQCommandParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void assignChannelNameAndType(MQOutputChannelModel model, String line){
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()){
            String name = matcher.group(1);
            model.NAME = name.replace("'", "").trim();
        }
        if(matcher.find()){
            String type = matcher.group(1);
            model.TYPE = type.trim();
        }        
    }
    
    private static String getPropertyValue(String line){
        String result = null;
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(line);  
        if(matcher.find()){
            result = matcher.group(1);
            result = result.replace("'", "").trim();  
        }
        return result;        
    }
    
    private static String getPropertyName(String line){
        String result = null;
        
        if(line.startsWith("HARDENBO") || line.startsWith("NOHARDENBO")){
            return "HARDENBO";
        }
        if((line.startsWith("SHARE") && !line.contains("SHARECNV")) || line.startsWith("NOSHARE")){
            return "SHARE";
        }
        if(line.startsWith("TRIGGER") || line.startsWith("NOTRIGGER")){
            return "TRIGGER";
        }
        if(line != null && !line.isEmpty()){
            int index = line.indexOf("(");
            if(index >=0){
                result = line.substring(0, index).replace("*","").trim();
            }
        }
        return result;
    }
    
    
    private static void assignValue(Object model, BufferedReader bufRead){
        while(true){
            try {            
                String line = bufRead.readLine();
                while(line.endsWith("-")){
                    line = line.replace("-","") +  bufRead.readLine();
                }
                if(line == null || line.isEmpty()){
                    break;
                }
                line = line.trim();
                String name = getPropertyName(line);
                String value = getPropertyValue(line);
                if(name != null){
                    model.getClass().getField(name).set(model, value != null? value : line.replace("+", "").trim());
                }

            } catch (IOException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(MQCommandParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void writeModelToCSV(ArrayList<Object> models, String filePath) throws IOException{
        if(models != null && models.size() > 0){
            filePath = checkFilePath(filePath, "csv");
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(filePath),true));
            Object m = models.get(0);
            Field[] fields = m.getClass().getFields();
            for(int i = 0; i < fields.length; i++){
                Field field = fields[i];
                if(i != fields.length - 1){
                    out.write(field.getName() + ",");
                }
                else{
                    out.write(field.getName());
                    out.write("\r\n");
                }
            }
            
            for(Object model : models){
                Field[] dataFields = model.getClass().getFields();
                for(int i = 0; i < dataFields.length; i++){
                    Field dataField = dataFields[i];
                    if(i != fields.length - 1){
                        try {
                            out.write(dataField.get(model) != null ? dataField.get(model).toString().trim() + "," : " " +",");
                        } catch (Exception ex) {
                            out.write(" " + ",");
                            Logger.getLogger(TextWriter.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                    else{
                        try {
                            out.write(dataField.get(model) != null ? dataField.get(model).toString().trim() + "," : " ");
                        } catch (Exception ex) {
                            out.write(" " );
                            Logger.getLogger(TextWriter.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        out.write("\r\n");
                    }                
                }
            }
//            out.write("\r\n");
//            out.write("\r\n");
            out.flush();
            out.close();
        }
    }
    
    private static String checkFilePath(String filePath, String extension){
        int lastIndexOf = filePath.lastIndexOf(".");
        if (lastIndexOf == -1 || !extension.equals(filePath.substring(lastIndexOf + 1).toLowerCase())) {
            return filePath + "." + extension;
        }
        return filePath;      
    }
}
