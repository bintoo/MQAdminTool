/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import UI.Models.MQRC.*;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author jzhou
 */
public class ExcelHelper {
    
    public static void ReadExcelFile(String filepath){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filepath));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet queueModelSheet = wb.getSheetAt(1);
            HSSFSheet otherObjectsModelSheet = wb.getSheetAt(2);
            HSSFSheet pubSubModelSheet = wb.getSheetAt(3);
           // ArrayList<MQRCQueueModel> queueModels = getModels(queueModelSheet,MQRCQueueModel.class, null, null);
//            ArrayList<MQRCChannelModel> channelModels = getModels(otherObjectsModelSheet,MQRCChannelModel.class ,"Channels required", null);
//            ArrayList<MQRCQueueAliasModel> aliasModels = getModels(otherObjectsModelSheet,MQRCQueueAliasModel.class, "Queue Alias required", "Channels required");
            //ArrayList<MQRCProcessModel> processModels = getModels(otherObjectsModelSheet,MQRCProcessModel.class, "Processes required", "Queue Alias required");
            //ArrayList<MQRCTopicModel> topModels = getModels(pubSubModelSheet,MQRCTopicModel.class, "Topics Required", "Subscriptions Required");
            ArrayList<MQRCSubscriptionModel> subModels = getModels(pubSubModelSheet,MQRCSubscriptionModel.class,"Subscriptions Required", null);
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
    }
    
    private static <T> ArrayList<T> getModels(HSSFSheet sheet, Class<T> modelClass, String startTitle, String endTitle){
        ArrayList<T> models = new ArrayList<T>();
        ArrayList<PlatformModel> platforms = new ArrayList<PlatformModel>();
        int modelIndex = 0;       
        HSSFRow row;
        HSSFCell cell;        
        int rows;
        rows = sheet.getPhysicalNumberOfRows();
        int cols = 0;
        int tmp = 0;
        Boolean inArea = null;
        for(int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if(row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if(tmp > cols) cols = tmp;
            }
        }

        for(int r = 0; r < rows; r++) {
            row = sheet.getRow(r);
            if(row != null) {
                HSSFCell firstCell = row.getCell(0);
                String firstValue = firstCell != null ? firstCell.getStringCellValue() : null;
                if(inArea == null && (startTitle == null ||  startTitle.equals(firstValue.trim()))){
                    inArea = true;
                }
                if(inArea != null && endTitle != null && endTitle.equals(firstValue.trim())){
                    inArea = false;
                }
                if(firstValue != null && "Platform to define on".equals(firstValue.trim())){
                    for(int c = 1; c < cols; c++) {
                        cell = row.getCell(c);
                        if(cell != null && cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {
                            PlatformModel platformModel = new PlatformModel();
                            platforms.add(platformModel);
                            String value = cell.getStringCellValue();
                            platformModel.Platform = value;
                        }
                    }  
                }
                if(firstValue != null && "MQ Queue Manager *".equals(firstValue.trim())){
                    int index = 0;
                    for(int c = 1; c < cols; c++) {
                        cell = row.getCell(c);
                        if(cell != null && cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {
                            PlatformModel platformModel = platforms.get(index);
                            String value = cell.getStringCellValue();
                            platformModel.QueueManager = value;
                            index++;
                        }
                    }  
                }
                if(inArea != null && inArea){
                    for(Field field : modelClass.getFields()){
                        MQRCModelAnnotation annotation = field.getAnnotation(MQRCModelAnnotation.class);
                        if(annotation != null && annotation.loop()){
                            String key = annotation.ExcelKeyword();                           
                            if(firstValue != null && key.equals(firstValue.trim())){
                                if(annotation.createNewModel()){
                                    modelIndex = models.size();
                                    addProperty(key, row, cols, models, platforms, modelClass);                                
                                }
                                else{
                                    addProperty(key, row, cols, models, modelIndex);
                                }                                
                            }
                        }
                    }
                }
            }
        }
        return models;
    }
    
    private static <T> void addProperty(String keyword, HSSFRow row, int cols, ArrayList<T> models, ArrayList<PlatformModel> platforms, Class<T> modelClass){
        int platFormModelIndex = 0;
        for(int c = 1; c < cols; c++) {
            HSSFCell cell = row.getCell(c);
            if(cell != null && !checkStrikeThrough(cell) && cell.getStringCellValue() != null && !cell.getStringCellValue().isEmpty()) {
                try {
                    T model = modelClass.newInstance();
                    models.add(model);
                    String value = cell.getStringCellValue();
                    setModelValue(keyword, model, value);
                    PlatformModel platformModel = platforms.get(platFormModelIndex);
                    addPlatFormInfor(model, platformModel);
                    platFormModelIndex++;
                } catch (InstantiationException ex) {
                    Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
    }
    
    private static <T> void addProperty(String keyword, HSSFRow row, int cols, ArrayList<T> models,  int modelIndex){
        if(models.size() > 0){
            for(int c = 1; c < cols; c++) {
                HSSFCell cell = row.getCell(c);
                Object cellValue = null;
                if(cell != null && !checkStrikeThrough(cell)){
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                        cellValue = cell.getStringCellValue();
                    }
                    else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                        cellValue = (int)cell.getNumericCellValue();
                    }
                }
                if(cellValue != null) {
                    Object queueModel = models.get(modelIndex);
                    Object value = cellValue;
                    setModelValue(keyword, queueModel, value);
                    modelIndex++;
                }
            }   
        }
    }
    
    private static void setModelValue(String keyword, Object model, Object value){
        for(Field field : model.getClass().getFields()){
            String excelKey = ((MQRCModelAnnotation)field.getAnnotation(MQRCModelAnnotation.class)).ExcelKeyword();
            if(excelKey == null ? keyword == null : excelKey.equals(keyword)){
                try {
                    field.set(model, value.toString());
                    break;
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private static void addPlatFormInfor(Object model, PlatformModel platformModel ){
        try {
            model.getClass().getField("Platform").set(model, platformModel.Platform);    
            model.getClass().getField("QueueManager").set(model,platformModel.QueueManager);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ExcelHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static boolean checkStrikeThrough(HSSFCell cell){
        CellStyle style = cell.getCellStyle(); 
        HSSFFont cellFont = cell.getSheet().getWorkbook().getFontAt(style.getFontIndex()); 
        boolean isStrikeout = cellFont.getStrikeout();
        return isStrikeout;
    }
    
    private static class PlatformModel{
        public String Platform;
        public String QueueManager;
    }
}
