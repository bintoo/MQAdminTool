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
import MQApi.QueryModel.MQMessageListResult;
import MQApi.Utilities.MQUtility;
import UI.Helpers.DateTimeHelper;
import UI.Helpers.IconManager;
import UI.Helpers.TableHelper;
import UI.Misc.CustomTableCellRender;
import UI.Models.TableListObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueueManager;
import java.awt.ComponentOrientation;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

/**
 *
 * @author jzhou
 */
public class BrowseMessageDialog extends DialogBase {
    
    private int maxMessagePerPage = 1000;
    boolean isAlias;
    private MQMessageIdModel next = null;
    private MQMessageIdModel prev = null;
    private int fromPosition = 1;
    private int totalPage;
    private int currentPage = 1;
    private HashMap<Integer, MQMessageIdModel> pageInfoMap = new HashMap<Integer, MQMessageIdModel>();
        
    public BrowseMessageDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject) {
        super(parent, modal,  queueManager,selectedObject );
        this.isAlias = ((QueueType)selectedObject.Type) == QueueType.Alias;
        initComponents();
        initCustomProperties();
        updateTable();
    }

    private void initCustomProperties(){
        this.setTitle("Message browser");
        this.setIconImage(iconManager.BrowseMessageIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        this.QueueManagerNameLabel.setText(queueManagerName);
        this.QueueNameLabel.setText(selectedObject.ObjectName);
        this.Refreshbutton.setEnabled(false);
        MessageListTable.setAutoCreateRowSorter(true);
        TableHelper.ToggleContentTable(MessageListTable, true);   
    }
    
    private JPopupMenu getMessagelListTablePopupMenu(){
        JPopupMenu popup = new JPopupMenu();
        JMenuItem backupMenuItem = new JMenuItem("Backup messages to file",iconManager.BackupMessageIcon());
        JMenuItem saveContentMenuItem = new JMenuItem("Save message content to file",iconManager.BackupMessageIcon());
        JMenuItem deleteMenuItem = new JMenuItem("Delete messages",iconManager.Delete());
        deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMessages();
            }
        });
        backupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupMessages(false);
            }
        });
        saveContentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupMessages(true);
            }
        });
        popup.add(saveContentMenuItem);
        popup.add(backupMenuItem);
        popup.add(deleteMenuItem);
        
        return popup;       
    }
    
    private void deleteMessages(){
         ArrayList<TableListObject> selectedObjects = TableHelper.GetCurrentTableSelectRowObjects(MessageListTable);
         if(selectedObjects != null && selectedObjects.size() > 0){
            ArrayList<MQMessageIdModel> idModels = turnToMessageIdModel(selectedObjects);
            DestoryMessagesDialog dialog = new DestoryMessagesDialog(ParentJFrame, true ,queueManager, selectedObject, idModels,false);
            dialog.AddDialogActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTable();
                    FireActionEvent();
                }
            });
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);  
         }
    }
    
    private void backupMessages(boolean isSaveMessageContent){
        ArrayList<TableListObject> selectedObjects = TableHelper.GetCurrentTableSelectRowObjects(MessageListTable);
        if(selectedObjects != null && selectedObjects.size() > 0){
            ArrayList<MQMessageIdModel> idModels = turnToMessageIdModel(selectedObjects);
            BackupRestoreMessageDialog dialog = DialogFactory.CreateBackupRestoreMessageDialog(ParentJFrame, true,queueManager, selectedObject, idModels);
            dialog.SetUsage(isSaveMessageContent ? BackupRestoreMessageDialog.Usage_SaveMsgContent : BackupRestoreMessageDialog.Usage_Backup);
            dialog.AddDialogActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //showTableData(true);
                }
            });
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true); 
        }
    }
    
    private ArrayList<MQMessageIdModel> turnToMessageIdModel(ArrayList<TableListObject> selectedObjects){
        ArrayList<MQMessageIdModel> models = new ArrayList<MQMessageIdModel>();
        for(TableListObject object : selectedObjects){
            MQMessageIdModel idModel = object.MessageIdInfo;
            models.add(idModel);
        }
        return models;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        QueueManagerNameLabel = new javax.swing.JLabel();
        QueueNameLabel = new javax.swing.JLabel();
        Refreshbutton = new javax.swing.JButton();
        Closebutton = new javax.swing.JButton();
        UpdateTimeLabel = new javax.swing.JLabel();
        PagingToolBar = new javax.swing.JToolBar();
        NextButton = new javax.swing.JButton();
        PageInfoLabel = new javax.swing.JLabel();
        PreviousButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        TablePannel = new javax.swing.JScrollPane();
        MessageListTable = new javax.swing.JTable(){
            public Class getColumnClass(int column)
            {
                if(getValueAt(0, column) != null)
                return getValueAt(0, column).getClass();
                else
                return Object.class;
            }
        };
        ProgressBarPanel = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 500));
        setModal(true);
        setPreferredSize(new java.awt.Dimension(700, 500));

        jLabel1.setText("Queue manager name:");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel2.setText("Queue name:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerNameLabel.setPreferredSize(new java.awt.Dimension(400, 25));

        QueueNameLabel.setPreferredSize(new java.awt.Dimension(400, 25));

        Refreshbutton.setText("Refresh");
        Refreshbutton.setPreferredSize(new java.awt.Dimension(100, 25));
        Refreshbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshbuttonActionPerformed(evt);
            }
        });

        Closebutton.setText("Close");
        Closebutton.setPreferredSize(new java.awt.Dimension(100, 25));
        Closebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosebuttonActionPerformed(evt);
            }
        });

        UpdateTimeLabel.setPreferredSize(new java.awt.Dimension(250, 25));

        PagingToolBar.setFloatable(false);
        PagingToolBar.setRollover(true);
        PagingToolBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        NextButton.setIcon(iconManager.RightIcon());
        NextButton.setFocusable(false);
        NextButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NextButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });
        PagingToolBar.add(NextButton);
        PagingToolBar.add(PageInfoLabel);

        PreviousButton.setIcon(iconManager.LeftIcon());
        PreviousButton.setFocusable(false);
        PreviousButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        PreviousButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        PreviousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PreviousButtonActionPerformed(evt);
            }
        });
        PagingToolBar.add(PreviousButton);

        jPanel1.setLayout(new java.awt.CardLayout());

        MessageListTable.setModel(new javax.swing.table.DefaultTableModel(

        ));
        MessageListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MessageListTableMouseClicked(evt);
            }
        });
        TablePannel.setViewportView(MessageListTable);

        jPanel1.add(TablePannel, "card2");

        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setPreferredSize(new java.awt.Dimension(150, 25));

        javax.swing.GroupLayout ProgressBarPanelLayout = new javax.swing.GroupLayout(ProgressBarPanel);
        ProgressBarPanel.setLayout(ProgressBarPanelLayout);
        ProgressBarPanelLayout.setHorizontalGroup(
            ProgressBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProgressBarPanelLayout.createSequentialGroup()
                .addContainerGap(215, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        ProgressBarPanelLayout.setVerticalGroup(
            ProgressBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProgressBarPanelLayout.createSequentialGroup()
                .addContainerGap(155, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jPanel1.add(ProgressBarPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PagingToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(UpdateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Refreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Closebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QueueManagerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(QueueNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PagingToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Refreshbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Closebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UpdateTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClosebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosebuttonActionPerformed
        Close();
    }//GEN-LAST:event_ClosebuttonActionPerformed

    private void RefreshbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshbuttonActionPerformed
         updateTable();
    }//GEN-LAST:event_RefreshbuttonActionPerformed

    private void PreviousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PreviousButtonActionPerformed
        pageInfoMap.remove(currentPage);
        this.currentPage -=1;
        fromPosition = (this.currentPage - 1) * maxMessagePerPage + 1;
        updateTable();  
    }//GEN-LAST:event_PreviousButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        this.currentPage +=1;
        fromPosition = (this.currentPage - 1) * maxMessagePerPage  + 1;
        updateTable();                
    }//GEN-LAST:event_NextButtonActionPerformed

    private void MessageListTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MessageListTableMouseClicked
        if(evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2){
            showMessageEditDialog();
        }
        else if(evt.getButton() == MouseEvent.BUTTON3){
            TableHelper.SetSelectedRow(MessageListTable, evt.getPoint());
            ArrayList<TableListObject> selectedObjects = TableHelper.GetCurrentTableSelectRowObjects(MessageListTable);
            if(selectedObjects != null && selectedObjects.size() > 0){
                JPopupMenu popup = getMessagelListTablePopupMenu();
                popup.show(evt.getComponent(), evt.getPoint().x, evt.getPoint().y);
            }
        }
    }//GEN-LAST:event_MessageListTableMouseClicked
    
    private void updateTable(){
        tableLoadStart();        
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                int queueDepth = MQUtility.GetQueueDepth(queueManager, selectedObject.ObjectName);
                totalPage = getTotalPage(queueDepth, maxMessagePerPage);
                if(currentPage > totalPage){
                    currentPage = totalPage;
                    fromPosition = (currentPage - 1) * maxMessagePerPage  + 1;
                }
                MQMessageIdModel from = pageInfoMap.get(currentPage);
                MQMessageListResult result = MQUtility.GetMessageList(queueManager, selectedObject.ObjectName, maxMessagePerPage, isAlias, from,fromPosition);
                if(result.QuerySuccess){
                    TableHelper.UpdateContentTable(MessageListTable, result.DataModels);                       
                }
                else{
                    JOptionPane.showMessageDialog(ParentJFrame, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
                tableLoadFinish();
            }
        });
        thread.start();       
    }
    
    private int getTotalPage(int queueDepth, int maxMessagePerPage){
        int result = queueDepth % maxMessagePerPage > 0 ? 1 : 0;
        result += queueDepth / maxMessagePerPage;
        return result;
    }
    
    private void tableLoadStart(){
        this.PagingToolBar.setEnabled(false);
        this.NextButton.setEnabled(false);
        this.PreviousButton.setEnabled(false);
        this.Refreshbutton.setEnabled(false);
        this.TablePannel.setVisible(false);
        this.MessageListTable.setEnabled(false);
        this.ProgressBarPanel.setVisible(true);
    }
    
    private void tableLoadFinish(){
        String timeStamp = DateTimeHelper.GetCurrentTimeStamp();
        this.ProgressBarPanel.setVisible(false);
        this.TablePannel.setVisible(true);
        this.MessageListTable.setEnabled(true);
        this.UpdateTimeLabel.setText("Last updated: " + timeStamp + "");
        this.Refreshbutton.setEnabled(true);     
        this.PageInfoLabel.setText(this.currentPage + "/" + this.totalPage);
        if(totalPage > 1){            
            if(this.currentPage > 1){
                this.PreviousButton.setEnabled(true);
            }
            else{
                this.PreviousButton.setEnabled(false);
            }
            if(currentPage == totalPage){
                this.NextButton.setEnabled(false);
            }
            else{
                this.NextButton.setEnabled(true);
            }
            this.PagingToolBar.setEnabled(true);
            TableListObject obj  = TableHelper.GetLastTableRowObject(MessageListTable);
            next = obj.MessageIdInfo;
            pageInfoMap.put(currentPage + 1, next);
        }
        else{
            this.NextButton.setEnabled(false);
            this.PreviousButton.setEnabled(false);
            this.PagingToolBar.setEnabled(false);
        }
        
    }
    
    private void showMessageEditDialog(){
        
        TableListObject selectedRowObject = TableHelper.GetCurrentTableSelectRowObject(MessageListTable);
        MQMessageIdModel messageId = selectedRowObject.MessageIdInfo;
        MessageEditDialog dialog = DialogFactory.CreateMessageEditDialog(ParentJFrame, true, queueManager, selectedObject.ObjectName,messageId);
        dialog.AddDialogActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
                FireActionEvent();
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Closebutton;
    private javax.swing.JTable MessageListTable;
    private javax.swing.JButton NextButton;
    private javax.swing.JLabel PageInfoLabel;
    private javax.swing.JToolBar PagingToolBar;
    private javax.swing.JButton PreviousButton;
    private javax.swing.JPanel ProgressBarPanel;
    private javax.swing.JLabel QueueManagerNameLabel;
    private javax.swing.JLabel QueueNameLabel;
    private javax.swing.JButton Refreshbutton;
    private javax.swing.JScrollPane TablePannel;
    private javax.swing.JLabel UpdateTimeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
