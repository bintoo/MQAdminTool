/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import UI.Helpers.IconManager;
import UI.Models.TableListObject;
import com.ibm.mq.MQQueueManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author jzhou
 */
public abstract class DialogBase extends javax.swing.JDialog {
    protected MQQueueManager queueManager;
    protected TableListObject selectedObject;
    protected java.awt.Frame ParentJFrame;
    protected IconManager iconManager = new IconManager();
    ArrayList<ActionListener> Listeners = new ArrayList<ActionListener>();
    
    public DialogBase(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject){
        super(parent, modal);
        this.queueManager = queueManager;
        this.selectedObject = selectedObject;
        this.ParentJFrame = parent;
        this.setModal(modal);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
    }
    
    public void AddDialogActionListener(ActionListener listener){
        Listeners.add(listener);
    }
        
    protected void FireActionEvent(){
        int id = 0;
        for(ActionListener listener : Listeners){
            ActionEvent evt = new ActionEvent(this, id, null);
            listener.actionPerformed(evt);
            id++;
        }
    }
    
    protected void FireActionEvent(String value){
        int id = 0;
        for(ActionListener listener : Listeners){
            ActionEvent evt = new ActionEvent(this, id, value);
            listener.actionPerformed(evt);
            id++;
        }
    } 
    
    protected void Close(){
        this.setVisible(false);
        this.dispose();       
    }
    
    protected String checkFilePath(String filePath, String extension){
        int lastIndexOf = filePath.lastIndexOf(".");
        if (lastIndexOf == -1 || !extension.equals(filePath.substring(lastIndexOf + 1).toLowerCase())) {
            return filePath + "." + extension;
        }
        return filePath;      
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
        if(ParentJFrame != null){
            ParentJFrame.repaint();
        }
    }   
    
}
