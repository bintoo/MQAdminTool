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
import java.awt.Component;
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
    private JButton connectButton;
    private JButton closeButton;
    Component parent;
    
    public CheckChannelTask(ConnectionDetailModel connection, int waitFor, JTextArea waitForTextField, JButton connectButton, JButton closeButton, Component parent){
        this.connection = connection;
        this.waitFor = waitFor;
        this.waitForTextField = waitForTextField;
        this.connectButton = connectButton;
        this.closeButton = closeButton;
        this.parent = parent;
    }
    public void CheckChannelMessageChange(){
        try {
            connectButton.setEnabled(false);
            closeButton.setEnabled(false);
            String statusText = "Getting the first sample data....";
            UpdateDebugWindows(waitForTextField,statusText);
            Hashtable<String, Integer> resultTable1 = new Hashtable<String, Integer>();
            Hashtable<String, Integer> resultTable2 = new Hashtable<String, Integer>();
            MQQueueManager queueManager = MQConnection.GetMQQueueManager(connection);
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
                queueManager = MQConnection.GetMQQueueManager(connection);
                MQChannelStatusModel channelStatus2 = MQPCF.GenerateChannelStatus(queueManager, "*");
                if(channelStatus2.QuerySuccess){
                    statusText += "\r\n" + "Success...Generating result...";
                    UpdateDebugWindows(waitForTextField,statusText);
                    if(channelStatus1.QuerySuccess && channelStatus1.QuerySuccess ){
                        WriteToHashtable(channelStatus1.ChannelStatusList, resultTable1);
                        WriteToHashtable(channelStatus2.ChannelStatusList, resultTable2);
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                        String fileName = "CH_" + this.connection.QueueManager + "_" + timeStamp +".csv";
                        statusText += "\r\n" + "Saving to file " + fileName;
                        try {
                            TextWriter.WriteHashTableToFile(resultTable1, resultTable2, fileName);
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
            if(queueManager != null && queueManager.isConnected()){
                queueManager.disconnect();
            }
        } catch (MQException ex) {            
            Logger.getLogger(CheckChannelTask.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error",JOptionPane.ERROR_MESSAGE );
            UpdateDebugWindows(waitForTextField,"");
            FireActionFailEvent();
        }
        connectButton.setEnabled(true);
        closeButton.setEnabled(true);
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

    @Override
    public void run() {
        CheckChannelMessageChange();
    }
}
