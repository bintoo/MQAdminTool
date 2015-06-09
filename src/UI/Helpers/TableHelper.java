/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import MQApi.Enums.TableContentType;
import MQApi.Models.MQMessageIdModel;
import MQApi.QueryModel.MQChannelAuthListResult.ChannelAuthDetailModel;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import MQApi.QueryModel.MQChannelListResult.ChannelDetailModel;
import MQApi.QueryModel.MQMessageListResult.MessageDetailModel;
import MQApi.QueryModel.MQQueueListResult.QueueDetailModel;
import UI.Models.TableListObject;
import java.awt.Component;
import java.awt.Point;
import java.lang.reflect.Field;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author jzhou
 */
public class TableHelper {
    
    public static void InitTable(JTable contentTable){
        ToggleContentTable(contentTable, false);
        contentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        contentTable.setAutoCreateRowSorter(true);
    }

    public static<T> void UpdateContentTable(JTable contentTable, ArrayList<T> dataModel){
        contentTable.getParent().getParent().setVisible(false);
        DefaultTableModel model = (DefaultTableModel)contentTable.getModel();
        if(dataModel != null && dataModel.size() > 0){
            ArrayList<String> headerNames = new ArrayList<String>();
            ArrayList<String> fieldNames = new ArrayList<String>();
            for(Field field : dataModel.get(0).getClass().getFields()){
                if(field.getAnnotation(MQObjectListtAnnotation.class) != null && field.getAnnotation(MQObjectListtAnnotation.class).ShowOnTable()){
                    headerNames.add(field.getAnnotation(MQObjectListtAnnotation.class).DisplayName());
                    fieldNames.add(field.getName());
                }
            }
            String headers[] = headerNames.toArray(new String[headerNames.size()]);
            Object[][] tablleDataObjects = new Object[dataModel.size()][fieldNames.size()];
            for(int i = 0; i < dataModel.size(); i++){
                T data = dataModel.get(i);
                TableContentType tableType;
                if(data instanceof QueueDetailModel){
                    tableType = TableContentType.QueueList;
                }
                else if(data instanceof ChannelDetailModel){
                    tableType = TableContentType.ChannelList;
                }
                else if(data instanceof MessageDetailModel){
                    tableType = TableContentType.MessageList;
                }
                else if(data instanceof ChannelAuthDetailModel){
                    tableType = TableContentType.ChannelAuthList;
                }
                else{
                    tableType = null;
                }
                for(int j = 0; j < fieldNames.size(); j++){
                    String fieldName = fieldNames.get(j);
                    try{                        
                        Object o = data.getClass().getField(fieldName).get(data);
                        if(j == 0){
                            TableListObject obj = new TableListObject();
                            obj.ObjectName = o != null ? o.toString() : null;
                            obj.TableType = tableType;
                            if(tableType == TableContentType.MessageList){
                                obj.MessageIdInfo = new MQMessageIdModel();
                                obj.MessageIdInfo.MessageId = (byte[])(data.getClass().getField("MessageId").get(data));
                                obj.MessageIdInfo.CorrelationdId = (byte[])(data.getClass().getField("CorrelationId").get(data));
                                obj.MessageIdInfo.position = Integer.parseInt(data.getClass().getField("Position").get(data).toString());
                            }
                            setType(obj, data);
                            tablleDataObjects[i][j] = obj;
                        }
                        else{
                            tablleDataObjects[i][j] = o != null ? o : null;
                        }
                    }catch(Exception ex){
                        tablleDataObjects[i][j] = null;
                    }
                }

            }    
            model.setDataVector(tablleDataObjects, headers);
        }
        else{
            ToggleContentTable(contentTable, false);
        }
        resizeColumnWidth(contentTable);
        contentTable.getParent().getParent().setVisible(true);
    }

    public static void setType(TableListObject obj, Object data){
        try{
            obj.Type =  data.getClass().getField("Type").get(data);
        }catch(Exception ex){
            obj.Type = null;
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
        }
        
    }
    
    public static void ToggleContentTable(JTable contentTable, boolean show){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                int rowCount = getRowCount();
                Object value = null;
                for(int i = 0 ; i < rowCount; i++){
                    value = getValueAt(i, columnIndex);
                    if(value != null){
                        return value.getClass();
                    }
                }
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
              return false;
            }                
        };
        contentTable.setModel(model);
        //contentTable.setVisible(show);
   }
    
    public static TableListObject GetCurrentTableSelectRowObject(JTable contentTable){
        int selectedRow = contentTable.getSelectedRow();
        return GetTableSelectRowObject(contentTable, selectedRow);
    }
    
    public static TableListObject GetLastTableRowObject(JTable contentTable){
        int selectedRow = contentTable.getRowCount() - 1;
        return GetTableSelectRowObject(contentTable, selectedRow);
    }
    
    public static TableListObject GetTableSelectRowObject(JTable contentTable, int selectedRow){
        DefaultTableModel model = (DefaultTableModel)contentTable.getModel();
        if(selectedRow > -1){
            TableListObject objectModel = (TableListObject)model.getValueAt(contentTable.convertRowIndexToModel(selectedRow), 0);
            return objectModel;
        }
        return null;        
    }
    
    
    public static ArrayList<TableListObject> GetCurrentTableSelectRowObjects(JTable contentTable){
        DefaultTableModel model = (DefaultTableModel)contentTable.getModel();
        ArrayList<TableListObject> result = new ArrayList<TableListObject>();
        int[] selectedRows = contentTable.getSelectedRows();
        if(selectedRows != null && selectedRows.length > 0){
            for(int selectedRow : selectedRows){
                if(selectedRow > -1){
                    TableListObject objectModel = (TableListObject)model.getValueAt(contentTable.convertRowIndexToModel(selectedRow), 0);
                    result.add(objectModel);
                }            
            }
            return result;
        }
        return null;
    }
    
    public static void SetSelectedRow(JTable contentTable, Point point){
        int row = contentTable.rowAtPoint(point);

        if (! contentTable.isRowSelected(row)){
            contentTable.changeSelection(row, 0, false, false);
        }       
    }
       
    private static String toDisplayName(String name){
        return splitFieldName(toFirstLetterUppercase(name));
    }
    
    private static String toFirstLetterUppercase(String str){
        char[] stringArray = str.trim().toCharArray();
        stringArray[0] = Character.toUpperCase(stringArray[0]);
        return new String(stringArray);        
    }
    
    private static String splitFieldName(String name){
        String[] names = name.trim().split("(?=\\p{Upper})");
        String result = "";
        for(String s : names){
            result += s + " ";
        }
        return result.trim();
    }
    
    private static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 150; // Min width
            int maxWidth = 300;
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width, width);
                width = width > maxWidth ? maxWidth : width;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }
    
}
