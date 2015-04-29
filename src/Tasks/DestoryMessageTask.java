/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.Models.MQMessageIdModel;
import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.awt.Component;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author jzhou
 */
public class DestoryMessageTask extends TaskBase {
    MQQueueManager queueManager;
    String queueName;
    JProgressBar progressBar;
    Component parent;    
    boolean openGet;
    boolean isAlias;
    boolean isRemoveAllMessages;
    ArrayList<MQMessageIdModel> ids;
    
    public DestoryMessageTask(MQQueueManager queueManager, String queueName, JProgressBar progressBar, Component parent, boolean openGet, boolean isAlias){
        this.queueManager = queueManager;
        this.queueName = queueName;
        this.progressBar = progressBar;
        this.parent = parent;
        this.openGet = openGet;
        this.isAlias = isAlias;
        this.isRemoveAllMessages = true;
        
    }
    
    public DestoryMessageTask(MQQueueManager queueManager, String queueName, JProgressBar progressBar, Component parent, ArrayList<MQMessageIdModel> ids, boolean openGet, boolean isAlias){
        this.queueManager = queueManager;
        this.queueName = queueName;
        this.progressBar = progressBar;
        this.parent = parent;
        this.openGet = openGet;
        this.isAlias = isAlias;
        this.isRemoveAllMessages = false;
        this.ids = ids;
    }

    @Override
    public void run() {
        destoryMessage();
    }
    
    private void destoryMessage(){
        try {
            if(isRemoveAllMessages){
                MQUtility.ComsumeAllMessages(queueManager, queueName, progressBar, openGet,isAlias);
            }
            else{
                MQUtility.ComsumeSelectedMessages(queueManager, queueName, ids, progressBar, openGet, isAlias);
            }
            FireActionSuccessEvent();
        } catch (MQException ex) {
            Logger.getLogger(DestoryMessageTask.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
            FireActionFailEvent();
        }
    }
    
}
