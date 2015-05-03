/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.LogType;
import MQApi.Enums.QueueType;
import MQApi.Logs.LogWriter;
import MQApi.Models.MQMessageIdModel;
import MQApi.Utilities.MQUtility;
import Tasks.DestoryMessageTask;
import UI.Helpers.IconManager;
import UI.Models.TableListObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author jzhou
 */
public class DestoryMessagesDialog extends DialogBase {
    
    boolean forceOpenGet;
    boolean isDeleteAllMessages;
    ArrayList<MQMessageIdModel> ids;
    Component component = this;
    String stringFilter;
    public DestoryMessagesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject, boolean forceOpenGet, String stringFilter) {       
        super(parent, modal,queueManager,selectedObject);
        initComponents();
        initCustomProperties();
        this.forceOpenGet = forceOpenGet;
        this.isDeleteAllMessages = true;
        this.stringFilter = stringFilter;
    }
    
    public DestoryMessagesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject, ArrayList<MQMessageIdModel> ids, boolean forceOpenGet) {       
        super(parent, modal,queueManager,selectedObject);
        initComponents();
        initCustomProperties();
        this.forceOpenGet = forceOpenGet;
        this.isDeleteAllMessages = false;
        this.ids = ids;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ClearMessageProgressBar = new javax.swing.JProgressBar();
        StateLabel = new javax.swing.JLabel();
        ClearButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 150));
        setResizable(false);

        StateLabel.setPreferredSize(new java.awt.Dimension(300, 25));

        ClearButton.setText("Clear");
        ClearButton.setPreferredSize(new java.awt.Dimension(100, 25));
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.setPreferredSize(new java.awt.Dimension(100, 25));
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClearMessageProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(StateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 181, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(StateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ClearMessageProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        Close();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        toggleButtons(false);
        boolean isAlias = (QueueType)selectedObject.Type == QueueType.Alias;
        DestoryMessageTask task;
        if(isDeleteAllMessages){
            task  = new DestoryMessageTask(queueManager, selectedObject.ObjectName, ClearMessageProgressBar, component, forceOpenGet,isAlias, stringFilter);
        }
        else{
            task  = new DestoryMessageTask(queueManager, selectedObject.ObjectName, ClearMessageProgressBar, component, ids, forceOpenGet,isAlias, stringFilter);
        }
        task.AddTaskActionSuccessListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(component, "The queue has been cleared of messages", "Success", JOptionPane.INFORMATION_MESSAGE);
                toggleButtons(true);
                FireActionEvent();
                Close();
            }
        });
        task.AddTaskActionFailListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                toggleButtons(true);
            }
        });
        Thread thread = new Thread(task);
        thread.start();    
        this.StateLabel.setText("Clearing message...");
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void initCustomProperties(){        
        this.setTitle("Clear message");
        this.setIconImage(iconManager.DeleteMessageIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        this.ClearMessageProgressBar.setValue(0);
        this.ClearMessageProgressBar.setStringPainted(true);
        this.StateLabel.setText("Are you sure you want to clear this messages?");
    }
    
    private void toggleButtons(boolean value){
        this.CancelButton.setEnabled(value);
        this.ClearButton.setEnabled(value);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton ClearButton;
    private javax.swing.JProgressBar ClearMessageProgressBar;
    private javax.swing.JLabel StateLabel;
    // End of variables declaration//GEN-END:variables
}
