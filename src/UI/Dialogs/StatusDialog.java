/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.LogType;
import MQApi.Enums.MQObjectType;
import MQApi.Enums.QueueType;
import MQApi.Enums.StatusType;
import MQApi.Logs.LogWriter;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQChannelStatusListResult;
import MQApi.QueryModel.MQQueueListResult;
import MQApi.QueryModel.MQQueueStatusHandleListResult;
import MQApi.QueryModel.MQQueueStatusListResult;
import Tasks.GetSelectObjectTask;
import UI.Helpers.TableHelper;
import UI.Models.TableListObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jzhou
 */
public class StatusDialog<T extends Enum> extends DialogBase {
    private T[] selectObjectType;
    String name;
    Component component = this;
    StatusType statusType;
    
    public StatusDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String name, StatusType statusType) {
        super(parent, modal, queueManager, null);
        initComponents();
        this.name = name;
        this.statusType = statusType;
        initCustomProperties();
        initTable();
    }
    
    private void initCustomProperties(){
        this.setTitle("Status");
        this.setIconImage(iconManager.StatusIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        };
        this.QueueManagerLabel.setText(queueManagerName);
        this.ProgressBar.setIndeterminate(true);
        this.ProgressBar.setVisible(false);
        TableHelper.ToggleContentTable(Table, false);
        
    }
    
    private void initTable(){
        this.ProgressBar.setVisible(true);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                if(statusType == StatusType.ChannelStatus){
                    MQChannelStatusListResult result = MQPCF.GetChannelStatusList(queueManager, name, null, false);
                    if(result.QuerySuccess){
                        TableHelper.UpdateContentTable(Table, result.DataModels);
                        loadTableSuccess();
                    }
                    else{
                        loadTableFail();
                        JOptionPane.showMessageDialog(component, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        Close();
                    }
                }
                if(statusType == StatusType.ChannelSavedStatus){
                    MQChannelStatusListResult result = MQPCF.GetChannelStatusList(queueManager, name, null, true);
                    if(result.QuerySuccess){
                        TableHelper.UpdateContentTable(Table, result.DataModels);
                        loadTableSuccess();
                    }
                    else{
                        loadTableFail();
                        JOptionPane.showMessageDialog(component, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        Close();
                    }
                }
                else if(statusType == StatusType.QueueHandleStatus){
                    MQQueueStatusHandleListResult result = MQPCF.GetQueueStatusHandleList(queueManager, name);
                    if(result.QuerySuccess){
                        TableHelper.UpdateContentTable(Table, result.DataModels);
                        loadTableSuccess();
                    }
                    else{
                        loadTableFail();
                        JOptionPane.showMessageDialog(component, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        Close();
                    }
                }
                else if(statusType == StatusType.QueueStatus){
                    MQQueueStatusListResult result = MQPCF.GetQueueStatusList(queueManager, name);
                    if(result.QuerySuccess){
                        TableHelper.UpdateContentTable(Table, result.DataModels);
                        loadTableSuccess();
                    }
                    else{
                        loadTableFail();
                        JOptionPane.showMessageDialog(component, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                        Close();
                    }
                }
            }
        });
        thread.start();

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        QueueManagerLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        CancelButton = new javax.swing.JButton();
        ProgressBar = new javax.swing.JProgressBar();
        RefreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(580, 40));

        jLabel1.setText("Queue manager : ");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerLabel.setPreferredSize(new java.awt.Dimension(150, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Table.setAutoCreateRowSorter(true);
        Table.setModel(new javax.swing.table.DefaultTableModel(){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }

        });
        Table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(Table);

        CancelButton.setText("Close");
        CancelButton.setPreferredSize(new java.awt.Dimension(100, 25));
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        ProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        RefreshButton.setText("Refresh");
        RefreshButton.setPreferredSize(new java.awt.Dimension(100, 25));
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadTableSuccess(){
        this.ProgressBar.setVisible(false);
    }
   
    private void loadTableFail(){
         this.ProgressBar.setVisible(false);
    }
    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        Close();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        initTable();
    }//GEN-LAST:event_RefreshButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JTable Table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
