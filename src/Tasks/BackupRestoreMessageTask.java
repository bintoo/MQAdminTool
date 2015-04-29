/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.Models.MQMessageIdModel;
import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQQueueManager;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author jzhou
 */
public class BackupRestoreMessageTask extends TaskBase{
    MQQueueManager queueManager;
    String queueName;
    JProgressBar progressBar;
    Frame parent;    
    boolean isBackup;
    String filePath;
    boolean isCompress;
    boolean isAlias;
    ArrayList<MQMessageIdModel> ids;
    
    public BackupRestoreMessageTask(MQQueueManager queueManager, String queueName,String filePath,  Frame parent,JProgressBar progressBar, ArrayList<MQMessageIdModel> ids, boolean isBackup, boolean isCompress, boolean isAlias){
        this.filePath = filePath;
        this.queueManager = queueManager;
        this.progressBar = progressBar;
        this.parent = parent;
        this.queueName = queueName;
        this.isBackup = isBackup;
        this.isCompress = isCompress;
        this.isAlias = isAlias;
        this.ids = ids;
    }

    @Override
    public void run() {
        runTask();
    }
    
    private void runTask(){
        if(isBackup == true){
            try {
                MQUtility.BackupMessageToFile(queueManager, queueName, filePath, progressBar,ids,isCompress,isAlias);
                JOptionPane.showMessageDialog(parent, "Successfully backup to file " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
                FireActionSuccessEvent();
            } catch (Exception ex) {
                Logger.getLogger(BackupRestoreMessageTask.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }
        }
        else{
            try {
                MQUtility.RestoreMessageFromFile(queueManager, queueName, filePath, progressBar,isAlias,true);
                JOptionPane.showMessageDialog(parent, "Successfully restore messages to queue " + queueName, "Success", JOptionPane.INFORMATION_MESSAGE);
                FireActionSuccessEvent();
            } catch (Exception ex) {
                Logger.getLogger(BackupRestoreMessageTask.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }            
        }
    }
    
}
