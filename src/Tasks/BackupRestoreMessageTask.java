/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.Models.MQMessageIdModel;
import MQApi.Utilities.MQUtility;
import UI.Dialogs.BackupRestoreMessageDialog;
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
    String filePath;
    boolean isCompress;
    boolean isAlias;
    ArrayList<MQMessageIdModel> ids;
    int option;
    boolean removeDLH;
    
    public BackupRestoreMessageTask(MQQueueManager queueManager, String queueName,String filePath,  Frame parent,JProgressBar progressBar, ArrayList<MQMessageIdModel> ids, int option, boolean isCompress, boolean isAlias, boolean removeDLH){
        this.filePath = filePath;
        this.queueManager = queueManager;
        this.progressBar = progressBar;
        this.parent = parent;
        this.queueName = queueName;
        this.option = option;
        this.isCompress = isCompress;
        this.isAlias = isAlias;
        this.ids = ids;
        this.removeDLH = removeDLH;
    }

    @Override
    public void run() {
        runTask();
    }
    
    private void runTask(){
        if(option == BackupRestoreMessageDialog.Usage_Backup){
            try {
                MQUtility.BackupMessageToFile(queueManager, queueName, filePath, progressBar,ids,isCompress,isAlias, removeDLH);
                JOptionPane.showMessageDialog(parent, "Successfully backup to file " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
                FireActionSuccessEvent();
            } catch (Exception ex) {
                Logger.getLogger(BackupRestoreMessageTask.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }
        }
        else if(option == BackupRestoreMessageDialog.Usage_Restore){
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
        else if(option == BackupRestoreMessageDialog.Usage_SaveMsgContent){
            try {
                MQUtility.SaveMessageContentToFile(queueManager, queueName, filePath, progressBar, ids);
                JOptionPane.showMessageDialog(parent, "Successfully backup to file " + filePath, "Success", JOptionPane.INFORMATION_MESSAGE);
                FireActionSuccessEvent();
            } catch (Exception ex) {
                Logger.getLogger(BackupRestoreMessageTask.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }            
        }
    }
    
}
