/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.Connection.MQConnection;
import MQApi.PCF.MQPCF;
import MQApi.Models.ToolChannelStatusModel;
import MQApi.Models.Query.ConnectionDetailModel;
import MQApi.Models.ToolChannelStatusResultModel;
import MQApi.QueryModel.MQChannelStatusModel;
import MQApi.TextWriter;
import MQApi.Utilities.MQUtility;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import UI.ChannelStatusTool;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.commonservices.internal.utils.QueueManager;
import java.awt.Component;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author jzhou
 */
public class CheckChannelTask extends TaskBase{
    
    private ConnectionDetailModel connection;
    private int waitFor;
    private JTextArea waitForTextField;
    private boolean repeat;
    private int displayTop;
    Component parent;
    
    public CheckChannelTask(ConnectionDetailModel connection, int waitFor, JTextArea waitForTextField, Component parent, boolean repeat, int displayTop){
        this.connection = connection;
        this.waitFor = waitFor * 60;
        this.waitForTextField = waitForTextField;
        this.parent = parent;
        this.repeat = repeat;
        this.displayTop = displayTop;
    }
    public void CheckChannelMessageChange(){
        MQQueueManager queueManager = null;
        try {
            String statusText = "Getting the first sample data....";
            UpdateDebugWindows(waitForTextField,statusText);
            Hashtable<String, Integer> resultTable1 = new Hashtable<String, Integer>();
            Hashtable<String, Integer> resultTable2 = new Hashtable<String, Integer>();
            queueManager = MQConnection.GetMQQueueManager(connection);
            MQChannelStatusModel channelStatus1 = MQPCF.GenerateChannelStatus(queueManager, "*");
            if(channelStatus1.QuerySuccess){
                String temp = statusText + "\r\n" + "Success....Wait for ";
                statusText = temp + waitFor + " seconds";
                UpdateDebugWindows(waitForTextField,statusText);
                for(int i = waitFor; i > 0; i--){
                    try {
                        Thread.sleep(1 * 1000);
                        statusText = temp + i + " seconds";
                        UpdateDebugWindows(waitForTextField,statusText);
                    } catch (InterruptedException ex) {
                        
                    }
                }                    
                statusText += "\r\n" + "Getting the second data...";
                UpdateDebugWindows(waitForTextField,statusText);
                //queueManager = MQConnection.GetMQQueueManager(connection);
                MQChannelStatusModel channelStatus2 = MQPCF.GenerateChannelStatus(queueManager, "*");
                if(channelStatus2.QuerySuccess){
                    statusText += "\r\n" + "Success...Generating result...";
                    UpdateDebugWindows(waitForTextField,statusText);
                    if(channelStatus1.QuerySuccess && channelStatus1.QuerySuccess ){
                        WriteToHashtable(channelStatus1.ChannelStatusList, resultTable1);
                        WriteToHashtable(channelStatus2.ChannelStatusList, resultTable2);
                        ArrayList<ToolChannelStatusResultModel> result = CombinTable(resultTable1, resultTable2);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        String fileName = "CH_" + this.connection.QueueManager + "_" + timeStamp +".csv";
                        statusText += "\r\n" + "Saving to file " + fileName;
                        try {
                            //TextWriter.WriteHashTableToFile(resultTable1, resultTable2, fileName);
                            TextWriter.WriteToolChannelStatusResultModel(result, fileName, this.displayTop, true);
                            statusText += "\r\n" + "Done. ";
                        } catch (IOException ex) {
                            statusText += "\r\n" + "Fail to save file.";
                            statusText += "\r\n" + ex.getMessage();
                        }
                        
                        UpdateDebugWindows(waitForTextField,statusText);
                    }
                }
                else{
                    statusText += "\r\n" + "Error :" + channelStatus2.ErrorMessage;
                    statusText += "\r\n" + "Please check the parameters and try again later.";
                    UpdateDebugWindows(waitForTextField,statusText);
                }
            }
            else{
                statusText += "\r\n" + "Error :" + channelStatus1.ErrorMessage;
                statusText += "\r\n" + "Please check the parameters and try again later.";
                UpdateDebugWindows(waitForTextField,statusText);
            }

        } catch (MQException ex) {            
            Logger.getLogger(CheckChannelTask.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error",JOptionPane.ERROR_MESSAGE );
            UpdateDebugWindows(waitForTextField,"");
            if(queueManager != null && queueManager.isConnected()){
                try {
                    queueManager.disconnect();
                } catch (MQException ex1) {
                    Logger.getLogger(CheckChannelTask.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            disconnectQueueManager(queueManager);
            FireActionFailEvent();
        }
        disconnectQueueManager(queueManager);
        FireActionSuccessEvent();
    }   
    
    public void CheckChannelMessageChangeRepeat(){
        MQQueueManager queueManager = null;
        try {
            String statusText = "Getting sample data....";
            UpdateDebugWindows(waitForTextField,statusText);
            Hashtable<String, Integer> resultTable1 = new Hashtable<String, Integer>();
            Hashtable<String, Integer> resultTable2 = new Hashtable<String, Integer>();
            queueManager = MQConnection.GetMQQueueManager(connection);
            MQChannelStatusModel channelStatus1 = MQPCF.GenerateChannelStatus(queueManager, "*");
            boolean header = true;
            if(channelStatus1.QuerySuccess){
                WriteToHashtable(channelStatus1.ChannelStatusList, resultTable1);
                String temp = statusText + "\r\n" + "Success....Wait for ";
                statusText = temp + waitFor + " seconds";
                UpdateDebugWindows(waitForTextField,statusText);   
                
                while(true && !this.stopTask){
                    for(int i = waitFor; i > 0; i--){
                        try {
                            Thread.sleep(1 * 1000);
                            statusText = temp + i + " seconds";
                            UpdateDebugWindows(waitForTextField,statusText);
                        } catch (InterruptedException ex) {

                        }
                    }    
                    statusText += "\r\n" + "Getting  new data...";
                    UpdateDebugWindows(waitForTextField,statusText);
                    //queueManager = MQConnection.GetMQQueueManager(connection);
                    MQChannelStatusModel channelStatus2 = MQPCF.GenerateChannelStatus(queueManager, "*");
                    if(channelStatus2.QuerySuccess){
                        statusText += "\r\n" + "Success...Generating result...";
                        UpdateDebugWindows(waitForTextField,statusText);
                        if(channelStatus1.QuerySuccess && channelStatus1.QuerySuccess ){
                            
                            WriteToHashtable(channelStatus2.ChannelStatusList, resultTable2);
                            ArrayList<ToolChannelStatusResultModel> result = CombinTable(resultTable1, resultTable2);
                            String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
                            String fileName = "CH_" + this.connection.QueueManager + "_" + timeStamp +".csv";
                            statusText += "\r\n" + "Saving to file " + fileName;
                            try {
                                //TextWriter.WriteHashTableToFile(resultTable1, resultTable2, fileName);
                                TextWriter.WriteToolChannelStatusResultModel(result, fileName, this.displayTop, header);
                                header = false;
                                statusText += "\r\n" + "Done. ";
                                resultTable1 = resultTable2;
                                resultTable2 = new Hashtable<>();
                            } catch (IOException ex) {
                                statusText += "\r\n" + "Fail to save file.";
                                statusText += "\r\n" + ex.getMessage();
                                this.stopTask = true;
                            }

                            UpdateDebugWindows(waitForTextField,statusText);
                        }
                    }
                    else{
                        statusText += "\r\n" + "Error :" + channelStatus2.ErrorMessage;
                        statusText += "\r\n" + "Please check the parameters and try again later.";
                        UpdateDebugWindows(waitForTextField,statusText);
                    }
                }
            }
            else{
                statusText += "\r\n" + "Error :" + channelStatus1.ErrorMessage;
                statusText += "\r\n" + "Please check the parameters and try again later.";
                UpdateDebugWindows(waitForTextField,statusText);
            }
            if(queueManager != null && queueManager.isConnected()){
                queueManager.disconnect();
            }
        } catch (MQException ex) {            
            Logger.getLogger(CheckChannelTask.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error",JOptionPane.ERROR_MESSAGE );
            UpdateDebugWindows(waitForTextField,"");
            disconnectQueueManager(queueManager);
            FireActionFailEvent();
        }
        disconnectQueueManager(queueManager);
        FireActionSuccessEvent();
    }  
    private void UpdateDebugWindows(JTextArea waitForTextField,String text){
        waitForTextField.setText(text);
    }
    private Hashtable<String, Integer> WriteToHashtable(ArrayList<ToolChannelStatusModel> chResult, Hashtable<String, Integer> table){
        for(ToolChannelStatusModel m : chResult){
            String key = m.ChannelName.trim() + "," + m.IpAddress.trim();
            int value = m.Value.MSGS;
            if(table.containsKey(key)){
                int newValue = table.get(key) + value;
                table.put(key, newValue);
            }
            else{
               table.put(key, value); 
            }
        }
        return table;
    }
    
    private ArrayList<ToolChannelStatusResultModel> CombinTable(Hashtable<String, Integer> tableOld, Hashtable<String, Integer> tableNew){
        ArrayList<ToolChannelStatusResultModel> result = new ArrayList<ToolChannelStatusResultModel>();
        Iterator<Map.Entry<String, Integer>> it = tableNew.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next(); 
            if(tableOld.containsKey(entry.getKey())){
                ToolChannelStatusResultModel model = new ToolChannelStatusResultModel();
                String key = entry.getKey();
                model.Key = key;
                model.Before = tableOld.get(key);
                model.After = entry.getValue();
                model.Change = model.After - model.Before;
                result.add(model);
            }
        }
        Collections.sort(result);
        Collections.reverse(result);
        return result;
    }
    
    private void disconnectQueueManager(MQQueueManager queueManager){
        if(queueManager != null && queueManager.isConnected()){
            try {
                queueManager.disconnect();
            } catch (MQException ex) {

            }
        }       
    }
    @Override
    public void run() {
        if(!this.repeat){
            CheckChannelMessageChange();
        }
        else{
            CheckChannelMessageChangeRepeat();
        }
    }
}
