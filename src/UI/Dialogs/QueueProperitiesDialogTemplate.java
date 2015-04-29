/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.LogType;
import MQApi.Enums.QueueType;
import MQApi.Logs.LogWriter;
import MQApi.PCF.MQPCF;
import UI.Misc.*;
import UI.Models.TableListObject;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jzhou
 */
public class QueueProperitiesDialogTemplate extends DialogBase {
    
    private QueueType queueType;
    private boolean isCreate;
    private int maxNameLength;
    private Hashtable<String, JPanel> categoryPanels = new Hashtable<String, JPanel>();

    public QueueProperitiesDialogTemplate(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, QueueType queueType, boolean isCreate) {
        super(parent, modal, queueManager, null);
        this.queueType = queueType;
        this.isCreate = isCreate;
        initComponents();
        initCustomProperties();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SharingInClustersNuttonGroup = new javax.swing.ButtonGroup();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        QueueManagerLabel = new javax.swing.JLabel();
        CancelButton = new javax.swing.JButton();
        FinishButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();
        ContentPanel = new javax.swing.JPanel();
        InitialPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ObjectNameTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        CopyFromTextField = new javax.swing.JTextField();
        SelectObjectButton = new javax.swing.JButton();
        SetupPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DetailList = new javax.swing.JList();
        SetupDetailPanel = new javax.swing.JPanel();
        GeneralPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        QueueNameTextBox = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        QueueTypeTextBox = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        DescriptionTextBox = new javax.swing.JTextField();
        PutMessageComboBox = new javax.swing.JComboBox();
        GetMessageComboBox2 = new javax.swing.JComboBox();
        PrioritySpinner = new javax.swing.JSpinner();
        PersistenceComboBox = new javax.swing.JComboBox();
        ScopeComboBox = new javax.swing.JComboBox();
        UsageComboBox = new javax.swing.JComboBox();
        ExtendedPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        MaxQueueDepthSpinner = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        MaxMessageLengthSpinner = new javax.swing.JSpinner();
        ShareabilityComboBox = new javax.swing.JComboBox();
        DefaultInputOpenOptionComboBox = new javax.swing.JComboBox();
        MessageDeliverySequenceComboBox = new javax.swing.JComboBox();
        RetentionIntervalSpinner = new javax.swing.JSpinner();
        DefinitionTypeComboBox = new javax.swing.JComboBox();
        DistributionListComboBox = new javax.swing.JComboBox();
        MessageDeliverySequenceComboBox3 = new javax.swing.JComboBox();
        DefaultPutResponseComboBox = new javax.swing.JComboBox();
        PriorityControlComboBox = new javax.swing.JComboBox();
        CustomTextBox = new javax.swing.JTextField();
        ClusterChannelNameTextField = new javax.swing.JTextField();
        ClusterPanel = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        NotShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInListClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterTextField = new javax.swing.JTextField();
        ShareInListClusterTextField = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        DefaultBindTypeComboBox = new javax.swing.JComboBox();
        ClusterWorkloadComboBox = new javax.swing.JComboBox();
        CLWLRankSpinner = new javax.swing.JSpinner();
        CLWLPrioritySpinner = new javax.swing.JSpinner();
        TriggeringPanel = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        TriggerMsgPrioritySpinner = new javax.swing.JSpinner();
        TriggerControlComboBox = new javax.swing.JComboBox();
        ProcessNameTextField = new javax.swing.JTextField();
        TriggerTypeComboBox = new javax.swing.JComboBox();
        TriggerDepthDepthSpinner = new javax.swing.JSpinner();
        TriggerDataTextField = new javax.swing.JTextField();
        InitiationQueueTextField = new javax.swing.JTextField();
        SelectInitQButton = new javax.swing.JButton();
        EventsPanel = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        QueueDepthComboBox = new javax.swing.JComboBox();
        QueueDepthMaxLimitSpinner = new javax.swing.JSpinner();
        QueueDepthHighComboBox = new javax.swing.JComboBox();
        QueueDepthLowlComboBox = new javax.swing.JComboBox();
        QueueServiceIntervalComboBox = new javax.swing.JComboBox();
        QueueDepthLowLimitSpinner = new javax.swing.JSpinner();
        QueueServiceIntervalSpinner = new javax.swing.JSpinner();
        StoragePanel = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        HardenGetBackoutComboBox = new javax.swing.JComboBox();
        BackoutRequeueQueueTextField = new javax.swing.JTextField();
        SelectBackoutReQueueButton = new javax.swing.JButton();
        BackoutThresholdSpinner = new javax.swing.JSpinner();
        NPMClassComboBox = new javax.swing.JComboBox();
        StatisticsPanel = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        QueueMonitoringComboBox = new javax.swing.JComboBox();
        QueueStatisticComboBox = new javax.swing.JComboBox();
        QueueAccountingComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(700, 530));

        TitlePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Queue manager :");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerLabel.setPreferredSize(new java.awt.Dimension(250, 25));

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        CancelButton.setText("Cancel");
        CancelButton.setPreferredSize(new java.awt.Dimension(100, 25));
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        FinishButton.setText("Finish");
        FinishButton.setPreferredSize(new java.awt.Dimension(100, 25));
        FinishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FinishButtonActionPerformed(evt);
            }
        });

        NextButton.setText("Next");
        NextButton.setPreferredSize(new java.awt.Dimension(100, 25));
        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        BackButton.setText("Back");
        BackButton.setPreferredSize(new java.awt.Dimension(100, 25));
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        ContentPanel.setLayout(new java.awt.CardLayout());

        jLabel2.setText("Queue name :");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));

        ObjectNameTextField.setPreferredSize(new java.awt.Dimension(400, 25));

        jLabel3.setText("Copy from : ");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 25));

        CopyFromTextField.setEditable(false);
        CopyFromTextField.setPreferredSize(new java.awt.Dimension(400, 25));

        SelectObjectButton.setText("Select");
        SelectObjectButton.setPreferredSize(new java.awt.Dimension(100, 25));
        SelectObjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectObjectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InitialPanelLayout = new javax.swing.GroupLayout(InitialPanel);
        InitialPanel.setLayout(InitialPanelLayout);
        InitialPanelLayout.setHorizontalGroup(
            InitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InitialPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InitialPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ObjectNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(110, 110, 110))
                    .addGroup(InitialPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CopyFromTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SelectObjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        InitialPanelLayout.setVerticalGroup(
            InitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InitialPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(InitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ObjectNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(InitialPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CopyFromTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectObjectButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(317, Short.MAX_VALUE))
        );

        ContentPanel.add(InitialPanel, "card2");

        DetailList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "General", "Extended", "Cluster", "Triggering", "Events", "Storage", "Statistics" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        DetailList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        DetailList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                DetailListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(DetailList);

        SetupDetailPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        SetupDetailPanel.setLayout(new java.awt.CardLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("General");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane2.setBorder(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel11.setText("Queue name:");
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueNameTextBox.setEditable(false);
        QueueNameTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        jLabel12.setText("Queue type:");
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueTypeTextBox.setEditable(false);
        QueueTypeTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        jLabel13.setText("Description:");
        jLabel13.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel14.setText("Put messages:");
        jLabel14.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel15.setText("Get messages:");
        jLabel15.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel16.setText("Default priority:");
        jLabel16.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel17.setText("Default persistence:");
        jLabel17.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel18.setText("Scope:");
        jLabel18.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel19.setText("Usage:");
        jLabel19.setPreferredSize(new java.awt.Dimension(200, 25));

        DescriptionTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PutMessageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Allowed", "Inhibited" }));
        PutMessageComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        GetMessageComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Allowed", "Inhibited" }));
        GetMessageComboBox2.setPreferredSize(new java.awt.Dimension(280, 25));

        PrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        PrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.PrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.PrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        PersistenceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Not persistence", "Persistence" }));
        PersistenceComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ScopeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cell", "Queue manager" }));
        ScopeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        UsageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Transimission" }));
        UsageComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UsageComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ScopeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PersistenceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GetMessageComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PutMessageComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DescriptionTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QueueNameTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QueueTypeTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueTypeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutMessageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GetMessageComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PersistenceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScopeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        javax.swing.GroupLayout GeneralPanelLayout = new javax.swing.GroupLayout(GeneralPanel);
        GeneralPanel.setLayout(GeneralPanelLayout);
        GeneralPanelLayout.setHorizontalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(GeneralPanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        GeneralPanelLayout.setVerticalGroup(
            GeneralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2)
                .addGap(16, 16, 16))
        );

        SetupDetailPanel.add(GeneralPanel, "card2");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Extended");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane3.setBorder(null);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(490, 340));

        jPanel1.setPreferredSize(new java.awt.Dimension(490, 420));

        jLabel29.setText("Max queue depth:");
        jLabel29.setPreferredSize(new java.awt.Dimension(200, 25));

        MaxQueueDepthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MaxQueueDepthSpinner.setModel(new SpinnerNumberModel(5000, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MaxQueueDepthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MaxQueueDepthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel30.setText("Maximum message length (bytes):");
        jLabel30.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel31.setText("Shareability:");
        jLabel31.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel32.setText("Default input open option:");
        jLabel32.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel33.setText("Message delivery sequence:");
        jLabel33.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel34.setText("Retention interval (hours):");
        jLabel34.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel35.setText("Definition type:");
        jLabel35.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel36.setText("Distribution lists:");
        jLabel36.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel37.setText("Default read ahead:");
        jLabel37.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel38.setText("Default put response type:");
        jLabel38.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel39.setText("Property control:");
        jLabel39.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel40.setText("Custom:");
        jLabel40.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel41.setText("Cluster channel name:");
        jLabel41.setPreferredSize(new java.awt.Dimension(200, 25));

        MaxMessageLengthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MaxMessageLengthSpinner.setModel(new SpinnerNumberModel(4194304, 0, 104857600, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        ShareabilityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Shareable", "Not Shareable" }));
        ShareabilityComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultInputOpenOptionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Input share", "Input exclusive" }));
        DefaultInputOpenOptionComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageDeliverySequenceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Priority", "FIFO" }));
        MessageDeliverySequenceComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        RetentionIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        RetentionIntervalSpinner.setModel(new SpinnerNumberModel(999999999, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.RetentionIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.RetentionIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        DefinitionTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Permanent dynamic", "Predefined", "Temporary dynamic" }));
        DefinitionTypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DistributionListComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supported", "Not supported" }));
        DistributionListComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageDeliverySequenceComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Priority", "FIFO" }));
        MessageDeliverySequenceComboBox3.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultPutResponseComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asynchronous", "Synchronous" }));
        DefaultPutResponseComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PriorityControlComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Compatibility", "Force MQRFH2", "None", "Version 6 compatible" }));
        PriorityControlComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        CustomTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ClusterChannelNameTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorityControlComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultPutResponseComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageDeliverySequenceComboBox3, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DistributionListComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefinitionTypeComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RetentionIntervalSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageDeliverySequenceComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultInputOpenOptionComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShareabilityComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaxQueueDepthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ClusterChannelNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CustomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaxQueueDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ShareabilityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DefaultInputOpenOptionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MessageDeliverySequenceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RetentionIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DefinitionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DistributionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MessageDeliverySequenceComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DefaultPutResponseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PriorityControlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CustomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClusterChannelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout ExtendedPanelLayout = new javax.swing.GroupLayout(ExtendedPanel);
        ExtendedPanel.setLayout(ExtendedPanelLayout);
        ExtendedPanelLayout.setHorizontalGroup(
            ExtendedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExtendedPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ExtendedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(ExtendedPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ExtendedPanelLayout.setVerticalGroup(
            ExtendedPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExtendedPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        SetupDetailPanel.add(ExtendedPanel, "card2");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Cluster");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane4.setBorder(null);

        jPanel3.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel42.setText("Default bind type:");
        jLabel42.setPreferredSize(new java.awt.Dimension(200, 25));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sharing in clusters"));

        SharingInClustersNuttonGroup.add(NotShareInClusterRadioButton);
        NotShareInClusterRadioButton.setText("Not share in a cluster");
        NotShareInClusterRadioButton.setPreferredSize(new java.awt.Dimension(150, 25));
        NotShareInClusterRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShareInClusterValueChange(evt);
            }
        });

        SharingInClustersNuttonGroup.add(ShareInClusterRadioButton);
        ShareInClusterRadioButton.setText("Share in cluster");
        ShareInClusterRadioButton.setPreferredSize(new java.awt.Dimension(150, 25));
        ShareInClusterRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShareInClusterValueChange(evt);
            }
        });

        SharingInClustersNuttonGroup.add(ShareInListClusterRadioButton);
        ShareInListClusterRadioButton.setText("Share in a list of cluster");
        ShareInListClusterRadioButton.setPreferredSize(new java.awt.Dimension(150, 25));
        ShareInListClusterRadioButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ShareInClusterValueChange(evt);
            }
        });

        ShareInClusterTextField.setPreferredSize(new java.awt.Dimension(200, 25));

        ShareInListClusterTextField.setPreferredSize(new java.awt.Dimension(200, 25));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(ShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(ShareInClusterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(NotShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(ShareInListClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(ShareInListClusterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NotShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShareInClusterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareInListClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShareInListClusterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel43.setText("CLWL queue rank:");
        jLabel43.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel44.setText("CLWL queue priority:");
        jLabel44.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel45.setText("Cluster workload use queue:");
        jLabel45.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultBindTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Not fixed", "On group", "On open" }));
        DefaultBindTypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ClusterWorkloadComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Any", "Local", "Queue manager" }));
        ClusterWorkloadComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        CLWLRankSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        CLWLRankSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.CLWLRankSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.CLWLRankSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        CLWLPrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        CLWLPrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.CLWLPrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.CLWLPrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DefaultBindTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClusterWorkloadComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultBindTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClusterWorkloadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel3);

        javax.swing.GroupLayout ClusterPanelLayout = new javax.swing.GroupLayout(ClusterPanel);
        ClusterPanel.setLayout(ClusterPanelLayout);
        ClusterPanelLayout.setHorizontalGroup(
            ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ClusterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(ClusterPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane4)
        );
        ClusterPanelLayout.setVerticalGroup(
            ClusterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClusterPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        SetupDetailPanel.add(ClusterPanel, "card2");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Triggering");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane5.setBorder(null);

        jPanel4.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel46.setText("Trigger control:");
        jLabel46.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel47.setText("Trigger type:");
        jLabel47.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel48.setText("Trigger depth:");
        jLabel48.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel49.setText("Trigger message priority:");
        jLabel49.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel50.setText("Trigger data:");
        jLabel50.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel51.setText("Initiation queue");
        jLabel51.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel52.setText("Process name:");
        jLabel52.setPreferredSize(new java.awt.Dimension(200, 25));

        TriggerMsgPrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        TriggerMsgPrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.TriggerMsgPrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.TriggerMsgPrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        TriggerControlComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Off", "On" }));
        TriggerControlComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ProcessNameTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        TriggerTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Depth", "Every", "First", "None" }));
        TriggerTypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        TriggerDepthDepthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        TriggerDepthDepthSpinner.setModel(new SpinnerNumberModel(1, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.TriggerDepthDepthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.TriggerDepthDepthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        TriggerDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        InitiationQueueTextField.setEditable(false);
        InitiationQueueTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SelectInitQButton.setText("Select");
        SelectInitQButton.setPreferredSize(new java.awt.Dimension(70, 25));
        SelectInitQButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectInitQButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ProcessNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerMsgPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerDepthDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerTypeComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerControlComboBox, 0, 1, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(InitiationQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelectInitQButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerControlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerDepthDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerMsgPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InitiationQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectInitQButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ProcessNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel4);

        javax.swing.GroupLayout TriggeringPanelLayout = new javax.swing.GroupLayout(TriggeringPanel);
        TriggeringPanel.setLayout(TriggeringPanelLayout);
        TriggeringPanelLayout.setHorizontalGroup(
            TriggeringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TriggeringPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TriggeringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(TriggeringPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane5)
        );
        TriggeringPanelLayout.setVerticalGroup(
            TriggeringPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TriggeringPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(TriggeringPanel, "card2");

        EventsPanel.setPreferredSize(new java.awt.Dimension(525, 431));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Events");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane6.setBorder(null);

        jPanel5.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel53.setText("Queue depth max events:");
        jLabel53.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel54.setText("Queue depth high events:");
        jLabel54.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel55.setText("Queue depth max limit:");
        jLabel55.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel56.setText("Queue depth low events:");
        jLabel56.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel57.setText("Queue depth low limit:");
        jLabel57.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel58.setText("Queue service interval events:");
        jLabel58.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel59.setText("Queue service interval (milliseconds):");
        jLabel59.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueDepthMaxLimitSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        QueueDepthMaxLimitSpinner.setModel(new SpinnerNumberModel(80, 0, 100, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.QueueDepthMaxLimitSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.QueueDepthMaxLimitSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        QueueDepthHighComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthHighComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueDepthLowlComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthLowlComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueServiceIntervalComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "None", "OK" }));
        QueueServiceIntervalComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueDepthLowLimitSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        QueueDepthLowLimitSpinner.setModel(new SpinnerNumberModel(20, 0, 100, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.QueueDepthLowLimitSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.QueueDepthLowLimitSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        QueueServiceIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        QueueServiceIntervalSpinner.setModel(new SpinnerNumberModel(999999999, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.QueueServiceIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.QueueServiceIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueServiceIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueServiceIntervalComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthLowLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthLowlComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthMaxLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthHighComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthComboBox, 0, 285, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthHighComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthMaxLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthLowlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthLowLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueServiceIntervalComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueServiceIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel5);

        javax.swing.GroupLayout EventsPanelLayout = new javax.swing.GroupLayout(EventsPanel);
        EventsPanel.setLayout(EventsPanelLayout);
        EventsPanelLayout.setHorizontalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(EventsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 355, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        EventsPanelLayout.setVerticalGroup(
            EventsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EventsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(EventsPanel, "card2");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Storage");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane7.setBorder(null);

        jPanel6.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel60.setText("Backout requeue queue:");
        jLabel60.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel61.setText("Backout threshold:");
        jLabel61.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel62.setText("Harden get backout:");
        jLabel62.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel63.setText("NPM class:");
        jLabel63.setPreferredSize(new java.awt.Dimension(200, 25));

        HardenGetBackoutComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Hardened", "Not hardened" }));
        HardenGetBackoutComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        BackoutRequeueQueueTextField.setEditable(false);
        BackoutRequeueQueueTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SelectBackoutReQueueButton.setText("Select");
        SelectBackoutReQueueButton.setPreferredSize(new java.awt.Dimension(70, 25));
        SelectBackoutReQueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectBackoutReQueueButtonActionPerformed(evt);
            }
        });

        BackoutThresholdSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        BackoutThresholdSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BackoutThresholdSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BackoutThresholdSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        NPMClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Normal" }));
        NPMClassComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NPMClassComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HardenGetBackoutComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BackoutThresholdSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BackoutRequeueQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelectBackoutReQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BackoutRequeueQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SelectBackoutReQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackoutThresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HardenGetBackoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NPMClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(248, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel6);

        javax.swing.GroupLayout StoragePanelLayout = new javax.swing.GroupLayout(StoragePanel);
        StoragePanel.setLayout(StoragePanelLayout);
        StoragePanelLayout.setHorizontalGroup(
            StoragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoragePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StoragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(StoragePanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane7)
        );
        StoragePanelLayout.setVerticalGroup(
            StoragePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StoragePanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(StoragePanel, "card2");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Statistics");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane8.setBorder(null);

        jPanel7.setPreferredSize(new java.awt.Dimension(490, 340));

        jLabel64.setText("Queue monitoring:");
        jLabel64.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel65.setText("Queue statistics:");
        jLabel65.setPreferredSize(new java.awt.Dimension(200, 25));

        jLabel66.setText("Queue accounting:");
        jLabel66.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueMonitoringComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low", "Medium", "Off", "Queue manager" }));
        QueueMonitoringComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueStatisticComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On", "Off", "Queue manager" }));
        QueueStatisticComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueAccountingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On", "Off", "Queue manager" }));
        QueueAccountingComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueAccountingComboBox, 0, 220, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueStatisticComboBox, 0, 220, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueMonitoringComboBox, 0, 220, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueMonitoringComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueStatisticComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueAccountingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(246, Short.MAX_VALUE))
        );

        jScrollPane8.setViewportView(jPanel7);

        javax.swing.GroupLayout StatisticsPanelLayout = new javax.swing.GroupLayout(StatisticsPanel);
        StatisticsPanel.setLayout(StatisticsPanelLayout);
        StatisticsPanelLayout.setHorizontalGroup(
            StatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7)
                    .addGroup(StatisticsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        StatisticsPanelLayout.setVerticalGroup(
            StatisticsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticsPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(StatisticsPanel, "card2");

        javax.swing.GroupLayout SetupPanelLayout = new javax.swing.GroupLayout(SetupPanel);
        SetupPanel.setLayout(SetupPanelLayout);
        SetupPanelLayout.setHorizontalGroup(
            SetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetupPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SetupDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
        );
        SetupPanelLayout.setVerticalGroup(
            SetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SetupPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(SetupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(SetupDetailPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
        );

        ContentPanel.add(SetupPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FinishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FinishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initCustomProperties(){
        this.setTitle("Add " + queueType.toString() + " queue"  );
        this.setIconImage(iconManager.AddNewIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        };
        this.QueueManagerLabel.setText(queueManagerName);
        this.maxNameLength = getMaxNameLength();
        this.BackButton.setEnabled(false);
        this.FinishButton.setEnabled(false);
        this.SetupPanel.setVisible(false);
        this.FinishButton.setEnabled(false);
        this.NextButton.setEnabled(false); 
        this.ObjectNameTextField.setDocument(new JTextFieldLimit(maxNameLength,"^[0-9A-Za-z./_%]+$"));
        this.ObjectNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkNameLength();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkNameLength();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkNameLength();
            }
        });
        addCategoryPanelToList();
        this.DetailList.setSelectedIndex(0);
        setSelectedPanel(); 
        NotShareInClusterRadioButton.setSelected(true); //temp
        clusterRadiobuttonSelectChange();
        if(isCreate){
            showInitialPanel();
        }
        else{
            showDetailPanel();
        }
//        this.jSpinner1.setModel(new SpinnerNumberModel(0, 0, 100, ));
//       ((NumberFormatter)((JSpinner.NumberEditor)this.jSpinner1.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        
    }
        
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        Close();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishButtonActionPerformed
        Close();
    }//GEN-LAST:event_FinishButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        showInitialPanel();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        showDetailPanel();
    }//GEN-LAST:event_NextButtonActionPerformed

    private void SelectObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectObjectButtonActionPerformed
        showSelectObjectBrowser(new QueueType[]{queueType}, CopyFromTextField);     
    }//GEN-LAST:event_SelectObjectButtonActionPerformed

    private void DetailListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_DetailListValueChanged
        setSelectedPanel();      
    }//GEN-LAST:event_DetailListValueChanged

    private void ShareInClusterValueChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShareInClusterValueChange
        clusterRadiobuttonSelectChange();
    }//GEN-LAST:event_ShareInClusterValueChange

    private void SelectInitQButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectInitQButtonActionPerformed
        showSelectObjectBrowser(new QueueType[]{QueueType.Local, QueueType.Remote,QueueType.Alias},InitiationQueueTextField);   
    }//GEN-LAST:event_SelectInitQButtonActionPerformed

    private void SelectBackoutReQueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectBackoutReQueueButtonActionPerformed
        showSelectObjectBrowser(new QueueType[]{QueueType.Local, QueueType.Remote,QueueType.Alias},BackoutRequeueQueueTextField);
    }//GEN-LAST:event_SelectBackoutReQueueButtonActionPerformed

    private void updateTextFieldFromSelectObject(JTextField field, String value){
        field.setText(value);
    }
    
    private void showSelectObjectBrowser(QueueType[] queueTypes, JTextField field){
        SelectObjectDialog dialog = DialogFactory.CreateSelectObjectDialog(ParentJFrame, true, queueManager, queueTypes);
        final JTextField targetField = field;
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextFieldFromSelectObject(targetField, e.getActionCommand());
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }
    
    private void showDetailPanel(){
        this.InitialPanel.setVisible(false);
        this.SetupPanel.setVisible(true);
        this.NextButton.setEnabled(false);
        this.BackButton.setEnabled(true);
    }
    
    private void showInitialPanel(){
        this.InitialPanel.setVisible(true);
        this.SetupPanel.setVisible(false);
        this.NextButton.setEnabled(true);
        this.BackButton.setEnabled(false);
    }
    
    private int getMaxNameLength(){
        try{
            int value = MQConstants.class.getField("MQ_Q_NAME_LENGTH").getInt(null);
            return value;
        }
        catch(Exception ex){
            return 48;
        }

    }
    
    private void checkNameLength(){
        String name = this.ObjectNameTextField.getText().trim();
        if(name != null && !name.isEmpty() && name.length() > 0 && name.length() <= maxNameLength){
            this.FinishButton.setEnabled(true);
            this.NextButton.setEnabled(true);
        }
        else{
            this.FinishButton.setEnabled(false);
            this.NextButton.setEnabled(false);           
        }
        
    }
    
    private void addCategoryPanelToList(){
        categoryPanels.put("General",this.GeneralPanel);
        categoryPanels.put("Extended",this.ExtendedPanel);
        categoryPanels.put("Cluster", this.ClusterPanel);
        categoryPanels.put("Triggering",this.TriggeringPanel);
        categoryPanels.put("Events",this.EventsPanel);
        categoryPanels.put("Storage",this.StoragePanel);
        categoryPanels.put("Statistics",this.StatisticsPanel);
    }
    
    private void showSelectedCategoryPanel(String selectedName){
        for(JPanel panel : categoryPanels.values()){
            panel.setVisible(false);
        }
        JPanel selectedPanel = categoryPanels.get(selectedName);
        selectedPanel.setVisible(true);
    }
    
    private void setSelectedPanel(){
        String selectValue = DetailList.getSelectedValue().toString();
        showSelectedCategoryPanel(selectValue);       
    }
    
    private void clusterRadiobuttonSelectChange(){
        if(ShareInListClusterRadioButton.isSelected()){
            ShareInClusterTextField.setEnabled(false);
            ShareInListClusterTextField.setEnabled(true);
        }
        else if(ShareInClusterRadioButton.isSelected()){
            ShareInClusterTextField.setEnabled(true);
            ShareInListClusterTextField.setEnabled(false);
        }
        else{
            ShareInClusterTextField.setEnabled(false);
            ShareInListClusterTextField.setEnabled(false);            
        }
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JTextField BackoutRequeueQueueTextField;
    private javax.swing.JSpinner BackoutThresholdSpinner;
    private javax.swing.JSpinner CLWLPrioritySpinner;
    private javax.swing.JSpinner CLWLRankSpinner;
    private javax.swing.JButton CancelButton;
    private javax.swing.JTextField ClusterChannelNameTextField;
    private javax.swing.JPanel ClusterPanel;
    private javax.swing.JComboBox ClusterWorkloadComboBox;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JTextField CopyFromTextField;
    private javax.swing.JTextField CustomTextBox;
    private javax.swing.JComboBox DefaultBindTypeComboBox;
    private javax.swing.JComboBox DefaultInputOpenOptionComboBox;
    private javax.swing.JComboBox DefaultPutResponseComboBox;
    private javax.swing.JComboBox DefinitionTypeComboBox;
    private javax.swing.JTextField DescriptionTextBox;
    private javax.swing.JList DetailList;
    private javax.swing.JComboBox DistributionListComboBox;
    private javax.swing.JPanel EventsPanel;
    private javax.swing.JPanel ExtendedPanel;
    private javax.swing.JButton FinishButton;
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JComboBox GetMessageComboBox2;
    private javax.swing.JComboBox HardenGetBackoutComboBox;
    private javax.swing.JPanel InitialPanel;
    private javax.swing.JTextField InitiationQueueTextField;
    private javax.swing.JSpinner MaxMessageLengthSpinner;
    private javax.swing.JSpinner MaxQueueDepthSpinner;
    private javax.swing.JComboBox MessageDeliverySequenceComboBox;
    private javax.swing.JComboBox MessageDeliverySequenceComboBox3;
    private javax.swing.JComboBox NPMClassComboBox;
    private javax.swing.JButton NextButton;
    private javax.swing.JRadioButton NotShareInClusterRadioButton;
    private javax.swing.JTextField ObjectNameTextField;
    private javax.swing.JComboBox PersistenceComboBox;
    private javax.swing.JComboBox PriorityControlComboBox;
    private javax.swing.JSpinner PrioritySpinner;
    private javax.swing.JTextField ProcessNameTextField;
    private javax.swing.JComboBox PutMessageComboBox;
    private javax.swing.JComboBox QueueAccountingComboBox;
    private javax.swing.JComboBox QueueDepthComboBox;
    private javax.swing.JComboBox QueueDepthHighComboBox;
    private javax.swing.JSpinner QueueDepthLowLimitSpinner;
    private javax.swing.JComboBox QueueDepthLowlComboBox;
    private javax.swing.JSpinner QueueDepthMaxLimitSpinner;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JComboBox QueueMonitoringComboBox;
    private javax.swing.JTextField QueueNameTextBox;
    private javax.swing.JComboBox QueueServiceIntervalComboBox;
    private javax.swing.JSpinner QueueServiceIntervalSpinner;
    private javax.swing.JComboBox QueueStatisticComboBox;
    private javax.swing.JTextField QueueTypeTextBox;
    private javax.swing.JSpinner RetentionIntervalSpinner;
    private javax.swing.JComboBox ScopeComboBox;
    private javax.swing.JButton SelectBackoutReQueueButton;
    private javax.swing.JButton SelectInitQButton;
    private javax.swing.JButton SelectObjectButton;
    private javax.swing.JPanel SetupDetailPanel;
    private javax.swing.JPanel SetupPanel;
    private javax.swing.JRadioButton ShareInClusterRadioButton;
    private javax.swing.JTextField ShareInClusterTextField;
    private javax.swing.JRadioButton ShareInListClusterRadioButton;
    private javax.swing.JTextField ShareInListClusterTextField;
    private javax.swing.JComboBox ShareabilityComboBox;
    private javax.swing.ButtonGroup SharingInClustersNuttonGroup;
    private javax.swing.JPanel StatisticsPanel;
    private javax.swing.JPanel StoragePanel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JComboBox TriggerControlComboBox;
    private javax.swing.JTextField TriggerDataTextField;
    private javax.swing.JSpinner TriggerDepthDepthSpinner;
    private javax.swing.JSpinner TriggerMsgPrioritySpinner;
    private javax.swing.JComboBox TriggerTypeComboBox;
    private javax.swing.JPanel TriggeringPanel;
    private javax.swing.JComboBox UsageComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
