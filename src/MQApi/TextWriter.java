/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi;

import MQApi.Models.ToolChannelStatusModel;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import UI.Helpers.DateTimeHelper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.RowFilter.Entry;
/**
 *
 * @author jzhou
 */
public class TextWriter {
    public static void WriteError(String message){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("errors.txt")));
            out.write(message);
            out.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception ");       
        }  
    }
    
    public static void WriteChannel(ArrayList<ToolChannelStatusModel> models){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(new File("ch.txt")));
            for(ToolChannelStatusModel model : models){
                out.write(model.ChannelName.trim() + " ");
                out.write(model.IpAddress.trim() + " ");
                out.write(model.Application.trim() + " ");
                out.write(model.Value.MSGS + " ");
                out.write("\r\n");                
            }
            
            out.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception ");       
        } 
    }
    
     public static void WriteHashTableToFile(Hashtable<String, Integer> tableOld, Hashtable<String, Integer> tableNew, String fileName) throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(fileName)));
        out.write("Channel" + ",");
        out.write("IP"+ ",");
        out.write("Messages Before"+ ",");
        out.write("Messages After"+ ",");
        out.write("Change" + ",");
        out.write("\r\n");            
        Iterator<Map.Entry<String, Integer>> it = tableNew.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            if(tableOld.containsKey(entry.getKey())){
                int oldValue = tableOld.get(entry.getKey());
                int newValue = entry.getValue();
                int diff = newValue - oldValue;
                out.write(entry.getKey().trim() + ",");
                out.write(oldValue+ ",");
                out.write(newValue+ ",");
                out.write(diff + " ");
                out.write("\r\n"); 
            }
       }                      
        out.close();
    }
    
    public static void WriteModelToCSV(ArrayList<Object> models, String filePath, boolean append) throws IOException{
        if(models != null && models.size() > 0){
            filePath = checkFilePath(filePath, "csv");
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(filePath),append));
            Object m = models.get(0);
            Field[] fields = m.getClass().getFields();
            if(append){
                out.write(DateTimeHelper.GetCurrentTimeStamp());
                out.write("\r\n");
            }
            for(int i = 0; i < fields.length; i++){
                Field field = fields[i];
                MQObjectListtAnnotation annotation = field.getAnnotation(MQObjectListtAnnotation.class);
                if(i != fields.length - 1){                  
                    if(annotation.ShowOnCSV()){
                        out.write(annotation.DisplayName() + ",");
                    }
                }
                else{
                    if(annotation.ShowOnCSV()){
                        out.write(annotation.DisplayName());
                    }
                    out.write("\r\n");
                }
            }
            
            for(Object model : models){
                Field[] dataFields = model.getClass().getFields();
                for(int i = 0; i < dataFields.length; i++){
                    Field dataField = dataFields[i];
                    MQObjectListtAnnotation annotation = dataField.getAnnotation(MQObjectListtAnnotation.class);
                    if(annotation.ShowOnCSV()){
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
            }
            out.write("\r\n");
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
