/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.ConstantConverter;
import MQApi.Enums.LogType;
import MQApi.Enums.MQObjectType;
import MQApi.Enums.QueueType;
import MQApi.Logs.LogWriter;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQCommandResult;
import MQApi.QueryModel.MQQueuePropertyModel;
import MQApi.Utilities.MQUtility;
import UI.Misc.*;
import UI.Models.*;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.internal.MQCommonServices;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
public class QueueProperitiesDialog extends ObjectPropertiesDialogBase {
    
    private QueueType queueType;
    private boolean isCreate;
    private Hashtable<String, JPanel> categoryPanels = new Hashtable<String, JPanel>();
    private String queueName;
    String objectNameRegex =  "^[0-9A-Za-z./_%]+$";
    String defaultCopyFromLocalQueue = "SYSTEM.DEFAULT.LOCAL.QUEUE";
    String defaultCopyFromRemoteQueue = "SYSTEM.DEFAULT.REMOTE.QUEUE";
    String defaultCopyFromAliasQueue = "SYSTEM.DEFAULT.ALIAS.QUEUE";
    String defaultCopyFromModelQueue = "SYSTEM.DEFAULT.MODEL.QUEUE";
    boolean isAtInitialPanel;
    MQQueuePropertyModel propertyModel;

    public QueueProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, QueueType queueType) {
        super(parent, modal, queueManager, null);
        this.isCreate = true;
        this.queueType = queueType;
        initComponents();
        initCustomProperties();
        setDetailListItems(queueType);
        this.CopyFromTextField.setText(getDefaultCopyFromQueueName(queueType));
    }
    
    public QueueProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String name) {
        super(parent, modal, queueManager, null);
        this.isCreate = false;        
        this.queueName = name;
        initComponents();
        initCustomProperties();
        loadModelAndSetupUI(queueName, this.isCreate);
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
        PutMessageLabel = new javax.swing.JLabel();
        GetMessageLabel = new javax.swing.JLabel();
        DefaultPriorityLabel = new javax.swing.JLabel();
        DefaultPersistenceLabel = new javax.swing.JLabel();
        ScopeLabel = new javax.swing.JLabel();
        UsageLabel = new javax.swing.JLabel();
        DescriptionTextBox = new javax.swing.JTextField();
        PutMessageComboBox = new javax.swing.JComboBox();
        GetMessageComboBox = new javax.swing.JComboBox();
        DefaultPrioritySpinner = new javax.swing.JSpinner();
        PersistenceComboBox = new javax.swing.JComboBox();
        ScopeComboBox = new javax.swing.JComboBox();
        UsageComboBox = new javax.swing.JComboBox();
        RemoteQueueLabel = new javax.swing.JLabel();
        RemoteQueueManagerLabel = new javax.swing.JLabel();
        TransmissionQueueLabel = new javax.swing.JLabel();
        RemoteQueueTextField = new javax.swing.JTextField();
        RemoteQueueManagerTextField = new javax.swing.JTextField();
        TransmissionQueueTextField = new javax.swing.JTextField();
        SelectTransmissionQueueButton = new javax.swing.JButton();
        BaseObjectLabel = new javax.swing.JLabel();
        BaseObjectTypeLabel = new javax.swing.JLabel();
        BaseObjectTextField = new javax.swing.JTextField();
        BaseObjectTypeComboBox = new javax.swing.JComboBox();
        ExtendedPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        MaxQueueDepthLabel = new javax.swing.JLabel();
        MaxQueueDepthSpinner = new javax.swing.JSpinner();
        MaxMessageLengthLabel = new javax.swing.JLabel();
        ShareabilityLabel = new javax.swing.JLabel();
        DefaultInputOpenOptionLabel = new javax.swing.JLabel();
        MessageDeliverySequenceLabel = new javax.swing.JLabel();
        RetentionIntervalLabel = new javax.swing.JLabel();
        DefinitionTypeLabel = new javax.swing.JLabel();
        DistributionListLabel = new javax.swing.JLabel();
        DefaultReadAheadLabel = new javax.swing.JLabel();
        DefaultPutResponseTypeLabel = new javax.swing.JLabel();
        PropertyControlLabel = new javax.swing.JLabel();
        ClusterChannelNameLabel = new javax.swing.JLabel();
        MaxMessageLengthSpinner = new javax.swing.JSpinner();
        ShareabilityComboBox = new javax.swing.JComboBox();
        DefaultInputOpenOptionComboBox = new javax.swing.JComboBox();
        MessageDeliverySequenceComboBox = new javax.swing.JComboBox();
        RetentionIntervalSpinner = new javax.swing.JSpinner();
        DefinitionTypeComboBox = new javax.swing.JComboBox();
        DistributionListComboBox = new javax.swing.JComboBox();
        DefaultReadAheadComboBox = new javax.swing.JComboBox();
        DefaultPutResponseComboBox = new javax.swing.JComboBox();
        PriorityControlComboBox = new javax.swing.JComboBox();
        ClusterChannelNameTextField = new javax.swing.JTextField();
        CustomLabel = new javax.swing.JLabel();
        CustomTextBox = new javax.swing.JTextField();
        ClusterPanel = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        DefaultBindTypeLabel = new javax.swing.JLabel();
        ShareInClustersPanel = new javax.swing.JPanel();
        NotShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInListClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterTextField = new javax.swing.JTextField();
        ShareInListClusterTextField = new javax.swing.JTextField();
        CLWLQueueRankLabel = new javax.swing.JLabel();
        CLWLQueuePriorityLabel = new javax.swing.JLabel();
        ClusterWorkloadUseQueueLabel = new javax.swing.JLabel();
        DefaultBindTypeComboBox = new javax.swing.JComboBox();
        ClusterWorkloadComboBox = new javax.swing.JComboBox();
        CLWLRankSpinner = new javax.swing.JSpinner();
        CLWLPrioritySpinner = new javax.swing.JSpinner();
        TriggeringPanel = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        TriggerControlLabel = new javax.swing.JLabel();
        TriggerTypeLabel = new javax.swing.JLabel();
        TriggerDepthLabel = new javax.swing.JLabel();
        TriggerMessagePriorityLabel = new javax.swing.JLabel();
        TriggerDataLable = new javax.swing.JLabel();
        InitiationQueueLabel = new javax.swing.JLabel();
        ProcessNameLabel = new javax.swing.JLabel();
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
        QueueDepthMaxEventLabel = new javax.swing.JLabel();
        QueueDepthHighEventsLabel = new javax.swing.JLabel();
        QueueDepthHighLimitLabel = new javax.swing.JLabel();
        QueueDepthLowEventsLabel = new javax.swing.JLabel();
        QueueDepthLowLimitLabel = new javax.swing.JLabel();
        QueueServiceIntervalEventLabel = new javax.swing.JLabel();
        QueueServiceIntervalLabel = new javax.swing.JLabel();
        QueueDepthMaxEventComboBox = new javax.swing.JComboBox();
        QueueDepthHighLimitSpinner = new javax.swing.JSpinner();
        QueueDepthHighEventComboBox = new javax.swing.JComboBox();
        QueueDepthLowlEventComboBox = new javax.swing.JComboBox();
        QueueServiceIntervalEventComboBox = new javax.swing.JComboBox();
        QueueDepthLowLimitSpinner = new javax.swing.JSpinner();
        QueueServiceIntervalSpinner = new javax.swing.JSpinner();
        StoragePanel = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        BackoutRequeueQueueLabel = new javax.swing.JLabel();
        BackoutThresholdLabel = new javax.swing.JLabel();
        HardenGetBackoutLabel = new javax.swing.JLabel();
        NPMClassLabel = new javax.swing.JLabel();
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
        QueueMonitoringLabel = new javax.swing.JLabel();
        QueueStatisticsLabel = new javax.swing.JLabel();
        QueueAccountingLabel = new javax.swing.JLabel();
        QueueMonitoringComboBox = new javax.swing.JComboBox();
        QueueStatisticComboBox = new javax.swing.JComboBox();
        QueueAccountingComboBox = new javax.swing.JComboBox();
        CreationDateLabel = new javax.swing.JLabel();
        CreationTimeLabel = new javax.swing.JLabel();
        AlterationDateLabel = new javax.swing.JLabel();
        AlterationTimeLabel = new javax.swing.JLabel();
        OpenInputCountLabel = new javax.swing.JLabel();
        OpenOutputCountLabel = new javax.swing.JLabel();
        CurrentQueueDepthLabel = new javax.swing.JLabel();
        CreationDateTextField = new javax.swing.JTextField();
        CreationTimeTextField = new javax.swing.JTextField();
        AlterationDateTextField = new javax.swing.JTextField();
        AlterationTimeTextField = new javax.swing.JTextField();
        OpenInputCountTextField = new javax.swing.JTextField();
        OpenOutputCountTextField = new javax.swing.JTextField();
        CurrentQueueDepthTextField = new javax.swing.JTextField();
        ProgressBar = new javax.swing.JProgressBar();

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

        jLabel3.setText("Copy attributes from : ");
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

        PutMessageLabel.setText("Put messages:");
        PutMessageLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        GetMessageLabel.setText("Get messages:");
        GetMessageLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultPriorityLabel.setText("Default priority:");
        DefaultPriorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultPersistenceLabel.setText("Default persistence:");
        DefaultPersistenceLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ScopeLabel.setText("Scope:");
        ScopeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        UsageLabel.setText("Usage:");
        UsageLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DescriptionTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PutMessageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Allowed", "Inhibited" }));
        PutMessageComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        GetMessageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Allowed", "Inhibited" }));
        GetMessageComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultPrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        DefaultPrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.DefaultPrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.DefaultPrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        PersistenceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Not persistent", "Persistent" }));
        PersistenceComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ScopeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cell", "Queue manager" }));
        ScopeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        UsageComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Normal", "Transimission" }));
        UsageComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        RemoteQueueLabel.setText("Remote queue:");
        RemoteQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        RemoteQueueManagerLabel.setText("Remote queue manager:");
        RemoteQueueManagerLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TransmissionQueueLabel.setText("Transmission queue:");
        TransmissionQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        RemoteQueueTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        RemoteQueueManagerTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        TransmissionQueueTextField.setEditable(false);
        TransmissionQueueTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SelectTransmissionQueueButton.setText("Select");
        SelectTransmissionQueueButton.setPreferredSize(new java.awt.Dimension(70, 25));
        SelectTransmissionQueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectTransmissionQueueButtonActionPerformed(evt);
            }
        });

        BaseObjectLabel.setText("Base object:");
        BaseObjectLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BaseObjectTypeLabel.setText("Base object type:");
        BaseObjectTypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BaseObjectTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        BaseObjectTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Queue", "Topic" }));
        BaseObjectTypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(RemoteQueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RemoteQueueManagerTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(RemoteQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(RemoteQueueTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(UsageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(UsageComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ScopeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ScopeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(DefaultPersistenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PersistenceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(DefaultPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DefaultPrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(GetMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(GetMessageComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(PutMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TransmissionQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TransmissionQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelectTransmissionQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BaseObjectTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BaseObjectTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BaseObjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BaseObjectTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))))
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
                    .addComponent(PutMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutMessageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GetMessageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GetMessageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultPersistenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PersistenceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ScopeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScopeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UsageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UsageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoteQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoteQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RemoteQueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RemoteQueueManagerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TransmissionQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SelectTransmissionQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TransmissionQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BaseObjectLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BaseObjectTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BaseObjectTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BaseObjectTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        SetupDetailPanel.add(GeneralPanel, "card2");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Extended");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane3.setBorder(null);
        jScrollPane3.setPreferredSize(new java.awt.Dimension(490, 340));

        MaxQueueDepthLabel.setText("Max queue depth:");
        MaxQueueDepthLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MaxQueueDepthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MaxQueueDepthSpinner.setModel(new SpinnerNumberModel(5000, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MaxQueueDepthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MaxQueueDepthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        MaxMessageLengthLabel.setText("Maximum message length (bytes):");
        MaxMessageLengthLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ShareabilityLabel.setText("Shareability:");
        ShareabilityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultInputOpenOptionLabel.setText("Default input open option:");
        DefaultInputOpenOptionLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageDeliverySequenceLabel.setText("Message delivery sequence:");
        MessageDeliverySequenceLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        RetentionIntervalLabel.setText("Retention interval (hours):");
        RetentionIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefinitionTypeLabel.setText("Definition type:");
        DefinitionTypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DistributionListLabel.setText("Distribution lists:");
        DistributionListLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultReadAheadLabel.setText("Default read ahead:");
        DefaultReadAheadLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DefaultPutResponseTypeLabel.setText("Default put response type:");
        DefaultPutResponseTypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        PropertyControlLabel.setText("Property control:");
        PropertyControlLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ClusterChannelNameLabel.setText("Cluster channel name:");
        ClusterChannelNameLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MaxMessageLengthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MaxMessageLengthSpinner.setModel(new SpinnerNumberModel(4194304, 0, 104857600, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        ShareabilityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Shareable", "Not shareable" }));
        ShareabilityComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultInputOpenOptionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Input shared", "Input exclusive" }));
        DefaultInputOpenOptionComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageDeliverySequenceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Priority", "FIFO" }));
        MessageDeliverySequenceComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        RetentionIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        RetentionIntervalSpinner.setModel(new SpinnerNumberModel(999999999, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.RetentionIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.RetentionIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        DefinitionTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Permanent dynamic", "Predefined", "Temporary dynamic", "Shared dynamic" }));
        DefinitionTypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DistributionListComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Supported", "Not supported" }));
        DistributionListComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultReadAheadComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No", "Yes", "Disabled" }));
        DefaultReadAheadComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        DefaultPutResponseComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asynchronous", "Synchronous" }));
        DefaultPutResponseComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PriorityControlComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Compatibility", "Force MQRFH2", "None", "Version 6 compatible" }));
        PriorityControlComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ClusterChannelNameTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        CustomLabel.setText("Custom:");
        CustomLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CustomTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CustomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CustomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(PropertyControlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriorityControlComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DefaultPutResponseTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultPutResponseComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DefaultReadAheadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultReadAheadComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DistributionListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DistributionListComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DefinitionTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefinitionTypeComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(RetentionIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RetentionIntervalSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(MessageDeliverySequenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageDeliverySequenceComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(DefaultInputOpenOptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DefaultInputOpenOptionComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ShareabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ShareabilityComboBox, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(MaxMessageLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(MaxQueueDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MaxQueueDepthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ClusterChannelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ClusterChannelNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxQueueDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaxQueueDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaxMessageLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareabilityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShareabilityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultInputOpenOptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultInputOpenOptionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageDeliverySequenceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageDeliverySequenceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RetentionIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RetentionIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefinitionTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefinitionTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DistributionListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DistributionListComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultReadAheadLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultReadAheadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultPutResponseTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultPutResponseComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PropertyControlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PriorityControlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CustomLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CustomTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClusterChannelNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClusterChannelNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
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

        DefaultBindTypeLabel.setText("Default bind type:");
        DefaultBindTypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ShareInClustersPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sharing in clusters"));

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

        javax.swing.GroupLayout ShareInClustersPanelLayout = new javax.swing.GroupLayout(ShareInClustersPanel);
        ShareInClustersPanel.setLayout(ShareInClustersPanelLayout);
        ShareInClustersPanelLayout.setHorizontalGroup(
            ShareInClustersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShareInClustersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ShareInClustersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ShareInClustersPanelLayout.createSequentialGroup()
                        .addComponent(ShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(ShareInClusterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(ShareInClustersPanelLayout.createSequentialGroup()
                        .addComponent(NotShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(ShareInClustersPanelLayout.createSequentialGroup()
                        .addComponent(ShareInListClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(ShareInListClusterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ShareInClustersPanelLayout.setVerticalGroup(
            ShareInClustersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ShareInClustersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NotShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ShareInClustersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareInClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShareInClusterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ShareInClustersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShareInListClusterRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShareInListClusterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        CLWLQueueRankLabel.setText("CLWL queue rank:");
        CLWLQueueRankLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CLWLQueuePriorityLabel.setText("CLWL queue priority:");
        CLWLQueuePriorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ClusterWorkloadUseQueueLabel.setText("Cluster workload use queue:");
        ClusterWorkloadUseQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

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
                    .addComponent(ShareInClustersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(DefaultBindTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DefaultBindTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(CLWLQueueRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(CLWLQueuePriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(ClusterWorkloadUseQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClusterWorkloadComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShareInClustersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DefaultBindTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DefaultBindTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLWLQueueRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLWLQueuePriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ClusterWorkloadUseQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClusterWorkloadComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
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

        TriggerControlLabel.setText("Trigger control:");
        TriggerControlLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TriggerTypeLabel.setText("Trigger type:");
        TriggerTypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TriggerDepthLabel.setText("Trigger depth:");
        TriggerDepthLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TriggerMessagePriorityLabel.setText("Trigger message priority:");
        TriggerMessagePriorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TriggerDataLable.setText("Trigger data:");
        TriggerDataLable.setPreferredSize(new java.awt.Dimension(200, 25));

        InitiationQueueLabel.setText("Initiation queue");
        InitiationQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ProcessNameLabel.setText("Process name:");
        ProcessNameLabel.setPreferredSize(new java.awt.Dimension(200, 25));

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
                                .addComponent(ProcessNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ProcessNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TriggerDataLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TriggerMessagePriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerMsgPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TriggerDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerDepthDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TriggerTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerTypeComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(TriggerControlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TriggerControlComboBox, 0, 1, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(InitiationQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(TriggerControlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerControlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TriggerTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TriggerDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerDepthDepthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TriggerMessagePriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerMsgPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TriggerDataLable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TriggerDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(InitiationQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(InitiationQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectInitQButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ProcessNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        QueueDepthMaxEventLabel.setText("Queue depth max events:");
        QueueDepthMaxEventLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthHighEventsLabel.setText("Queue depth high events:");
        QueueDepthHighEventsLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthHighLimitLabel.setText("Queue depth high limit:");
        QueueDepthHighLimitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthLowEventsLabel.setText("Queue depth low events:");
        QueueDepthLowEventsLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthLowLimitLabel.setText("Queue depth low limit:");
        QueueDepthLowLimitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueServiceIntervalEventLabel.setText("Queue service interval events:");
        QueueServiceIntervalEventLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueServiceIntervalLabel.setText("Queue service interval (milliseconds):");
        QueueServiceIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueDepthMaxEventComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthMaxEventComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueDepthHighLimitSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        QueueDepthHighLimitSpinner.setModel(new SpinnerNumberModel(80, 0, 100, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.QueueDepthHighLimitSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.QueueDepthHighLimitSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        QueueDepthHighEventComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthHighEventComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueDepthLowlEventComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disabled", "Enabled" }));
        QueueDepthLowlEventComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueServiceIntervalEventComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "None", "OK" }));
        QueueServiceIntervalEventComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

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
                        .addComponent(QueueServiceIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueServiceIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueServiceIntervalEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueServiceIntervalEventComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueDepthLowLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthLowLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueDepthLowEventsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthLowlEventComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueDepthHighLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthHighLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueDepthHighEventsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthHighEventComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(QueueDepthMaxEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueDepthMaxEventComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueDepthMaxEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthMaxEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueDepthHighEventsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthHighEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueDepthHighLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthHighLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueDepthLowEventsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthLowlEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueDepthLowLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueDepthLowLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueServiceIntervalEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueServiceIntervalEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueServiceIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueServiceIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(122, Short.MAX_VALUE))
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

        BackoutRequeueQueueLabel.setText("Backout requeue queue:");
        BackoutRequeueQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BackoutThresholdLabel.setText("Backout threshold:");
        BackoutThresholdLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        HardenGetBackoutLabel.setText("Harden get backout:");
        HardenGetBackoutLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        NPMClassLabel.setText("NPM class:");
        NPMClassLabel.setPreferredSize(new java.awt.Dimension(200, 25));

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
                                .addComponent(NPMClassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NPMClassComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(HardenGetBackoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(HardenGetBackoutComboBox, 0, 1, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(BackoutThresholdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BackoutThresholdSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(30, 30, 30))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(BackoutRequeueQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(BackoutRequeueQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackoutThresholdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackoutThresholdSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HardenGetBackoutLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HardenGetBackoutComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NPMClassLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NPMClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
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

        QueueMonitoringLabel.setText("Queue monitoring:");
        QueueMonitoringLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueStatisticsLabel.setText("Queue statistics:");
        QueueStatisticsLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueAccountingLabel.setText("Queue accounting:");
        QueueAccountingLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        QueueMonitoringComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low", "Medium", "Off", "Queue manager" }));
        QueueMonitoringComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueStatisticComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On", "Off", "Queue manager" }));
        QueueStatisticComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        QueueAccountingComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On", "Off", "Queue manager" }));
        QueueAccountingComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        CreationDateLabel.setText("Creation date:");
        CreationDateLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CreationTimeLabel.setText("Creation time:");
        CreationTimeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        AlterationDateLabel.setText("Alteration date:");
        AlterationDateLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        AlterationTimeLabel.setText("Alteration time:");
        AlterationTimeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        OpenInputCountLabel.setText("Open input count:");
        OpenInputCountLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        OpenOutputCountLabel.setText("Open output count:");
        OpenOutputCountLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CurrentQueueDepthLabel.setText("Current queue depth:");
        CurrentQueueDepthLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CreationDateTextField.setEditable(false);
        CreationDateTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        CreationTimeTextField.setEditable(false);
        CreationTimeTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        AlterationDateTextField.setEditable(false);
        AlterationDateTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        AlterationTimeTextField.setEditable(false);
        AlterationTimeTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        OpenInputCountTextField.setEditable(false);
        OpenInputCountTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        OpenOutputCountTextField.setEditable(false);
        OpenOutputCountTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        CurrentQueueDepthTextField.setEditable(false);
        CurrentQueueDepthTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(CurrentQueueDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CurrentQueueDepthTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(OpenOutputCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OpenOutputCountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(OpenInputCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(OpenInputCountTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(AlterationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterationTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(AlterationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterationDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(CreationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CreationTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(QueueAccountingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueAccountingComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(QueueStatisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueStatisticComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(QueueMonitoringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QueueMonitoringComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(CreationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CreationDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                .addGap(39, 39, 39))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreationDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreationTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlterationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlterationDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlterationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlterationTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenInputCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenInputCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenOutputCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenOutputCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CurrentQueueDepthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CurrentQueueDepthTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueMonitoringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueMonitoringComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueStatisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueStatisticComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(QueueAccountingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueAccountingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
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

        ProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

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
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(FinishButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    //event handler    
    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        Close();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void FinishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FinishButtonActionPerformed
       if(isCreate == true){
           if(isAtInitialPanel){
               try {
                   MQQueuePropertyModel model = MQPCF.GetQueueProperties(queueManager, this.CopyFromTextField.getText());
                   this.propertyModel = model;
                   MQQueuePropertyModel createModel = this.propertyModel;
                   createModel.Name = this.ObjectNameTextField.getText();
                   createModel.Description = "";
                   createOrChangeQueue(queueManager, createModel, true);
               } catch (MQDataException ex) {
                   JOptionPane.showMessageDialog(this, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                   Logger.getLogger(QueueProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                   Close();
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   Logger.getLogger(QueueProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                   Close();
               }
           }
           else{
               setModelFromUI(propertyModel);
               createOrChangeQueue(queueManager, propertyModel, true);              
           }
       }
       else{
            setModelFromUI(propertyModel);
            createOrChangeQueue(queueManager, propertyModel, false);
       }
    }//GEN-LAST:event_FinishButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        showInitialPanel();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        this.queueName = this.ObjectNameTextField.getText();
        showDetailPanel();
        loadModelAndSetupUI(this.CopyFromTextField.getText(),true);
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

    private void SelectTransmissionQueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectTransmissionQueueButtonActionPerformed
        showSelectObjectBrowser(new QueueType[]{QueueType.Transmit},TransmissionQueueTextField);
    }//GEN-LAST:event_SelectTransmissionQueueButtonActionPerformed
    
    //private methods    
    private void initCustomProperties(){
        
        if(isCreate){
            this.setTitle("Add " + queueType.toString() + " queue"  );
            this.FinishButton.setText("Create");
        }
        else{
            this.setTitle("Edit queue properties"  );
            this.BackButton.setVisible(false);
            this.NextButton.setVisible(false);
            this.FinishButton.setText("Update");
        }
        this.setIconImage(iconManager.AddNewIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        };
        this.QueueManagerLabel.setText(queueManagerName);
        this.BackButton.setEnabled(false);
        this.FinishButton.setEnabled(false);
        this.SetupPanel.setVisible(false);
        this.FinishButton.setEnabled(false);
        this.NextButton.setEnabled(false); 
        this.ObjectNameTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_Q_NAME_LENGTH, objectNameRegex));
        this.ObjectNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }
        });
        this.CopyFromTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkInitialPanelInput();
            }
        });
        this.DescriptionTextBox.setDocument(new JTextFieldLimit(MQConstants.MQ_Q_DESC_LENGTH, null));
        this.TriggerDataTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_TRIGGER_DATA_LENGTH, null));
        this.ProcessNameTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_PROCESS_NAME_LENGTH, null));
        this.RemoteQueueTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_Q_NAME_LENGTH, objectNameRegex));
        this.RemoteQueueManagerTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_Q_MGR_NAME_LENGTH, objectNameRegex));
        this.ProgressBar.setIndeterminate(true);
        this.ProgressBar.setVisible(false);
        initComboBoxValue();

        addCategoryPanelToList();
        this.DetailList.setSelectedIndex(0);
        setSelectedPanel(); 
        NotShareInClusterRadioButton.setSelected(true); //temp
        clusterRadiobuttonSelectChange();
        if(isCreate){
            showInitialPanel();
            checkInitialPanelInput();
        }
        else{
            showDetailPanel();
        }
        
//        this.jSpinner1.setModel(new SpinnerNumberModel(0, 0, 100, ));
//       ((NumberFormatter)((JSpinner.NumberEditor)this.jSpinner1.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        
    }  
           
    private void showDetailPanel(){
        this.InitialPanel.setVisible(false);
        this.SetupPanel.setVisible(true);
        this.NextButton.setEnabled(false);
        this.BackButton.setEnabled(true);
        this.isAtInitialPanel = false;
    }
    
    private void showInitialPanel(){
        this.InitialPanel.setVisible(true);
        this.SetupPanel.setVisible(false);
        this.NextButton.setEnabled(true);
        this.BackButton.setEnabled(false);
        this.isAtInitialPanel = true;
    }
        
    private void checkInitialPanelInput(){
        String name = this.ObjectNameTextField.getText().trim();
        String copyObjectName = this.CopyFromTextField.getText();
        if(name != null && !name.isEmpty() && name.length() > 0 && name.length() <= MQConstants.MQ_Q_NAME_LENGTH && copyObjectName != null && !copyObjectName.isEmpty()){
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
        if(DetailList.getSelectedValue() != null){
            String selectValue = DetailList.getSelectedValue().toString();
            showSelectedCategoryPanel(selectValue);  
        }
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
    
    private void loadModelAndSetupUI(String objectName, boolean isCreate){  
        final String name = objectName;
        final JDialog parent = this;
        final boolean isCreateNewObject = isCreate;
        objectLoading();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    objectLoading();
                    MQQueuePropertyModel model = MQPCF.GetQueueProperties(queueManager, name);
                    propertyModel = model;
                    if(isCreateNewObject){
                        model.Name = queueName;
                        model.Description = "";
                    }
                    if(model != null){
                        queueType = ConstantConverter.ConvertConstantToQueueType(model.Type);
                        setUIFromModel(model);
                        setDetailListItems(queueType);
                        objectLoadFinish();
                    }
                    else{
                        JOptionPane.showMessageDialog(parent, "Fail to load object properties", "Error", JOptionPane.ERROR_MESSAGE);                    
                        Close();
                    }
                } catch (MQDataException ex) {
                    JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(QueueProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                    Logger.getLogger(QueueProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        thread.start();
    }
    
    private void objectLoadFinish(){
        this.ProgressBar.setVisible(false);
        this.SetupDetailPanel.setVisible(true);
        this.DetailList.setEnabled(true);
        this.FinishButton.setEnabled(true);
    }
    
    private void objectLoading(){
        this.ProgressBar.setVisible(true);
        this.SetupDetailPanel.setVisible(false);
        this.DetailList.setEnabled(false);
        this.FinishButton.setEnabled(false);
    }
    
    private void setUIFromModel(MQQueuePropertyModel model){
        this.QueueNameTextBox.setText(model.Name);
        this.QueueTypeTextBox.setText(ConstantConverter.ConvertConstantToQueueType(model.Type).toString());
        this.DescriptionTextBox.setText(model.Description);
        if(model.InhibitPut != null){
            setComboBoxValue(this.PutMessageComboBox, model.InhibitPut);
        }
        else{
            this.PutMessageLabel.setVisible(false);
            this.PutMessageComboBox.setVisible(false);            
        }
        if(model.InhibitGet != null){
            setComboBoxValue(this.GetMessageComboBox, model.InhibitGet);
        }
        else{
            this.GetMessageLabel.setVisible(false);
            this.GetMessageComboBox.setVisible(false);            
        }
        if(model.DefPriority != null){
            this.DefaultPrioritySpinner.setValue(model.DefPriority);
        }
        else{
            this.DefaultPriorityLabel.setVisible(false);
            this.DefaultPrioritySpinner.setVisible(false);
        }
        if(model.DefPersistence != null){
            setComboBoxValue(PersistenceComboBox, model.DefPersistence);
        }
        else{
            this.DefaultPersistenceLabel.setVisible(false);
            this.PersistenceComboBox.setVisible(false);
        }
        if(model.Scope != null){
            setComboBoxValue(ScopeComboBox, model.Scope);
        }
        else{
            this.ScopeLabel.setVisible(false);
            this.ScopeComboBox.setVisible(false);
        }
        if(model.Usage != null){
            setComboBoxValue(UsageComboBox, model.Usage);
        }
        else{
            this.UsageLabel.setVisible(false);
            this.UsageComboBox.setVisible(false);
        }
        
        if(model.MaxQDepth != null){
            this.MaxQueueDepthSpinner.setValue(model.MaxQDepth);
        }
        else{
            this.MaxQueueDepthLabel.setVisible(false);
            this.MaxQueueDepthSpinner.setVisible(false);
        }
        if(model.MaxMsgLength != null){
            this.MaxMessageLengthSpinner.setValue(model.MaxMsgLength);
        }
        else{
            this.MaxMessageLengthLabel.setVisible(false);
            this.MaxMessageLengthSpinner.setVisible(false);
        }
        if(model.Shareability != null){
            setComboBoxValue(ShareabilityComboBox, model.Shareability);
        }
        else{
            this.ShareabilityLabel.setVisible(false);
            this.ShareabilityComboBox.setVisible(false);
        }
        if(model.DefInputOpenOption != null){
            setComboBoxValue(DefaultInputOpenOptionComboBox, model.DefInputOpenOption);
        }
        else{
            this.DefaultInputOpenOptionLabel.setVisible(false);
            this.DefaultInputOpenOptionComboBox.setVisible(false);
        }
        if(model.MsgDeliverySequence != null){
            setComboBoxValue(MessageDeliverySequenceComboBox, model.MsgDeliverySequence);
        }
        else{
            this.MessageDeliverySequenceComboBox.setVisible(false);
            this.MessageDeliverySequenceLabel.setVisible(false);
        }
        if(model.RetentionInterval != null){
            this.RetentionIntervalSpinner.setValue(model.RetentionInterval);
        }
        else{
            this.RetentionIntervalLabel.setVisible(false);
            this.RetentionIntervalSpinner.setVisible(false);
        }
        if(model.DefinitionType != null){
            setComboBoxValue(DefinitionTypeComboBox, model.DefinitionType);
        }
        else{
            this.DefinitionTypeLabel.setVisible(false);
            this.DefinitionTypeComboBox.setVisible(false);
        }
        if(model.DistLists != null){
            setComboBoxValue(DistributionListComboBox, model.DistLists);
        }
        else{
            this.DistributionListComboBox.setVisible(false);
            this.DistributionListLabel.setVisible(false);
        }
        if(model.DefReadAhead != null){
            setComboBoxValue(DefaultReadAheadComboBox, model.DefReadAhead);
        }
        else{
            this.DefaultReadAheadComboBox.setVisible(false);
            this.DefaultReadAheadLabel.setVisible(false);
        }
        if(model.DefaultPutResponse != null){
            setComboBoxValue(DefaultPutResponseComboBox, model.DefaultPutResponse);
        }
        else{
            this.DefaultPutResponseComboBox.setVisible(false);
            this.DefaultPutResponseTypeLabel.setVisible(false);
        }
        if(model.PropertyControl != null){
            setComboBoxValue(PriorityControlComboBox, model.PropertyControl);
        }
        else{
            this.PriorityControlComboBox.setVisible(false);
            this.PropertyControlLabel.setVisible(false);
        }
        if(model.Custom != null){
            this.CustomTextBox.setText(model.Custom);
        }
        else{
            this.CustomLabel.setVisible(false);
            this.CustomTextBox.setVisible(false);
        }
        if(model.ClusterChannelName != null){
            this.ClusterChannelNameTextField.setText(model.ClusterChannelName);
        }
        else{
            this.ClusterChannelNameLabel.setVisible(false);
            this.ClusterChannelNameTextField.setVisible(false);
        }
        if(model.ClusterName == null && model.ClusterNamelist == null){
            this.ShareInClustersPanel.setVisible(false);
            this.ShareInClusterTextField.setVisible(false);
            this.ShareInListClusterTextField.setVisible(false);
        }
        else if(model.ClusterName.isEmpty() && model.ClusterNamelist.isEmpty()){
            this.NotShareInClusterRadioButton.setSelected(true);
        }
        else if(!model.ClusterName.isEmpty()){
            this.ShareInClusterRadioButton.setSelected(true);
            this.ShareInClusterTextField.setText(model.ClusterName);
        }
        else if(!model.ClusterNamelist.isEmpty()){
            this.ShareInListClusterRadioButton.setSelected(true);
            this.ShareInListClusterTextField.setText(model.ClusterNamelist);
        }
        
        if(model.DefBind != null){
            setComboBoxValue(DefaultBindTypeComboBox, model.DefBind);
        }
        else{
            this.DefaultBindTypeComboBox.setVisible(false);
            this.DefaultBindTypeLabel.setVisible(false);
        }
        
        if(model.CLWLQueueRank != null){
            this.CLWLRankSpinner.setValue(model.CLWLQueueRank);
        }
        else{
            this.CLWLQueueRankLabel.setVisible(false);
            this.CLWLRankSpinner.setVisible(false);
        }
  
        if(model.CLWLQueuePriority != null){
            this.CLWLPrioritySpinner.setValue(model.CLWLQueuePriority);
        }
        else{
            this.CLWLQueuePriorityLabel.setVisible(false);
            this.CLWLPrioritySpinner.setVisible(false);
        }
        
        if(model.CLWLUseQ != null){
            setComboBoxValue(ClusterWorkloadComboBox, model.CLWLUseQ);
        }
        else{
            this.ClusterWorkloadComboBox.setVisible(false);
            this.ClusterWorkloadUseQueueLabel.setVisible(false);
        }
        
        if(model.TriggerControl != null){
            setComboBoxValue(TriggerControlComboBox, model.TriggerControl);
        }
        else{
            this.TriggerControlComboBox.setVisible(false);
            this.TriggerControlLabel.setVisible(false);
        }
        
        if(model.TriggerType != null){
            setComboBoxValue(TriggerTypeComboBox, model.TriggerType);
        }
        else{
            this.TriggerTypeComboBox.setVisible(false);
            this.TriggerTypeLabel.setVisible(false);
        }
        
        if(model.TriggerDepth != null){
            this.TriggerDepthDepthSpinner.setValue(model.TriggerDepth);
        }
        else{
            this.TriggerDepthLabel.setVisible(false);
            this.TriggerDepthDepthSpinner.setVisible(false);
        }
        
        if(model.TriggerMsgPriority != null){
            this.TriggerMsgPrioritySpinner.setValue(model.TriggerMsgPriority);
        }
        else{
            this.TriggerMessagePriorityLabel.setVisible(false);
            this.TriggerMsgPrioritySpinner.setVisible(false);
        }  
        
        if(model.TriggerData != null){
            this.TriggerDataTextField.setText(model.TriggerData);
        }
        else{
            this.TriggerDataLable.setVisible(false);
            this.TriggerDataTextField.setVisible(false);
        }    
        
        if(model.InitiationQName != null){
            this.InitiationQueueTextField.setText(model.InitiationQName);
        }
        else{
            this.InitiationQueueLabel.setVisible(false);
            this.InitiationQueueTextField.setVisible(false);
            this.SelectInitQButton.setVisible(false);
        } 
        if(model.ProcessName != null){
            this.ProcessNameTextField.setText(model.ProcessName);
        }
        else{
            this.ProcessNameLabel.setVisible(false);
            this.ProcessNameTextField.setVisible(false);
        } 
        if(model.QDepthMaxEvent != null){
            setComboBoxValue(QueueDepthMaxEventComboBox, model.QDepthMaxEvent);
        }
        else{
            this.QueueDepthMaxEventLabel.setVisible(false);
            this.QueueDepthMaxEventComboBox.setVisible(false);
        } 
        if(model.QDepthHighEvent != null){
            setComboBoxValue(QueueDepthHighEventComboBox, model.QDepthHighEvent);
        }
        else{
            this.QueueDepthHighEventComboBox.setVisible(false);
            this.QueueDepthHighEventsLabel.setVisible(false);
        } 
        if(model.QDepthHighLimit != null){
            this.QueueDepthHighLimitSpinner.setValue(model.QDepthHighLimit);
        }
        else{
            this.QueueDepthHighLimitSpinner.setVisible(false);
            this.QueueDepthHighLimitLabel.setVisible(false);
        } 
        if(model.QDepthLowEvent != null){
            setComboBoxValue(QueueDepthLowlEventComboBox, model.QDepthLowEvent);
        }
        else{
            this.QueueDepthLowlEventComboBox.setVisible(false);
            this.QueueDepthLowEventsLabel.setVisible(false);
        } 
        if(model.QDepthLowLimit != null){
            this.QueueDepthLowLimitSpinner.setValue(model.QDepthLowLimit);
        }
        else{
            this.QueueDepthLowLimitSpinner.setVisible(false);
            this.QueueDepthLowLimitLabel.setVisible(false);
        } 
        if(model.QServiceIntervalEvent != null){
            setComboBoxValue(QueueServiceIntervalEventComboBox, model.QServiceIntervalEvent);
        }
        else{
            this.QueueServiceIntervalEventComboBox.setVisible(false);
            this.QueueServiceIntervalEventLabel.setVisible(false);
        } 
        if(model.QServiceInterval != null){
            this.QueueServiceIntervalSpinner.setValue(model.QServiceInterval);
        }
        else{
            this.QueueServiceIntervalSpinner.setVisible(false);
            this.QueueServiceIntervalLabel.setVisible(false);
        } 
        if(model.BackoutRequeueName != null){
            this.BackoutRequeueQueueTextField.setText(model.BackoutRequeueName);
        }
        else{
            this.BackoutRequeueQueueTextField.setVisible(false);
            this.BackoutRequeueQueueLabel.setVisible(false);
            this.SelectBackoutReQueueButton.setVisible(false);
        } 
        if(model.BackoutThreshold != null){
            this.BackoutThresholdSpinner.setValue(model.BackoutThreshold);
        }
        else{
            this.BackoutThresholdSpinner.setVisible(false);
            this.BackoutThresholdLabel.setVisible(false);
        } 
        if(model.HardenGetBackout != null){
            setComboBoxValue(HardenGetBackoutComboBox, model.HardenGetBackout);
        }
        else{
            this.HardenGetBackoutComboBox.setVisible(false);
            this.HardenGetBackoutLabel.setVisible(false);
        }
        if(model.NonPersistentMessageClass != null){
            setComboBoxValue(NPMClassComboBox, model.NonPersistentMessageClass);
        }
        else{
            this.NPMClassComboBox.setVisible(false);
            this.NPMClassLabel.setVisible(false);
        }
        if(model.QueueMonitoring != null){
            setComboBoxValue(QueueMonitoringComboBox, model.QueueMonitoring);
        }
        else{
            this.QueueMonitoringComboBox.setVisible(false);
            this.QueueMonitoringLabel.setVisible(false);
        }
        if(model.QueueStatistics != null){
            setComboBoxValue(QueueStatisticComboBox, model.QueueStatistics);
        }
        else{
            this.QueueStatisticComboBox.setVisible(false);
            this.QueueStatisticsLabel.setVisible(false);
        }
        if(model.QueueAccounting != null){
            setComboBoxValue(QueueAccountingComboBox, model.QueueAccounting);
        }
        else{
            this.QueueAccountingComboBox.setVisible(false);
            this.QueueAccountingLabel.setVisible(false);
        }
        if(model.CreationDate != null && !isCreate){
            this.CreationDateTextField.setText(model.CreationDate);
        }
        else{
            this.CreationDateLabel.setVisible(false);
            this.CreationDateTextField.setVisible(false);
        } 
        if(model.CreationTime != null && !isCreate){
            this.CreationTimeTextField.setText(model.CreationTime);
        }
        else{
            this.CreationTimeLabel.setVisible(false);
            this.CreationTimeTextField.setVisible(false);
        } 
        if(model.AlterationDate != null && !isCreate){
            this.AlterationDateTextField.setText(model.AlterationDate);
        }
        else{
            this.AlterationDateLabel.setVisible(false);
            this.AlterationDateTextField.setVisible(false);
        } 
        if(model.AlterationTime != null && !isCreate){
            this.AlterationTimeTextField.setText(model.AlterationTime);
        }
        else{
            this.AlterationTimeLabel.setVisible(false);
            this.AlterationTimeTextField.setVisible(false);
        } 
        if(model.OpenInputCount != null && !isCreate){
            this.OpenInputCountTextField.setText(model.OpenInputCount.toString());
        }
        else{
            this.OpenInputCountLabel.setVisible(false);
            this.OpenInputCountTextField.setVisible(false);
        } 
        if(model.OpenOutputCount != null && !isCreate){
            this.OpenOutputCountTextField.setText(model.OpenOutputCount.toString());
        }
        else{
            this.OpenOutputCountLabel.setVisible(false);
            this.OpenOutputCountTextField.setVisible(false);
        } 
        if(model.CurrentQDepth != null && !isCreate){
            this.CurrentQueueDepthTextField.setText(model.CurrentQDepth.toString());
        }
        else{
            this.CurrentQueueDepthLabel.setVisible(false);
            this.CurrentQueueDepthTextField.setVisible(false);
        } 
        if(model.RemoteQName != null){
            this.RemoteQueueTextField.setText(model.RemoteQName);
        }
        else{
            this.RemoteQueueLabel.setVisible(false);
            this.RemoteQueueTextField.setVisible(false);
        } 
        if(model.RemoteQMgrName != null){
            this.RemoteQueueManagerTextField.setText(model.RemoteQMgrName);
        }
        else{
            this.RemoteQueueManagerLabel.setVisible(false);
            this.RemoteQueueManagerTextField.setVisible(false);
        } 
        if(model.XmitQName != null){
            this.TransmissionQueueTextField.setText(model.XmitQName);
        }
        else{
            this.TransmissionQueueLabel.setVisible(false);
            this.TransmissionQueueTextField.setVisible(false);
            this.SelectTransmissionQueueButton.setVisible(false);
        } 
        if(model.BaseObjectName != null){
            this.BaseObjectTextField.setText(model.BaseObjectName);
        }
        else{
            this.BaseObjectLabel.setVisible(false);
            this.BaseObjectTextField.setVisible(false);
        } 
        if(model.TargetType != null){
            setComboBoxValue(BaseObjectTypeComboBox, model.TargetType);
        }
        else{
            this.BaseObjectTypeLabel.setVisible(false);
            this.BaseObjectTypeComboBox.setVisible(false);
        } 
    }
    
    private void setModelFromUI(MQQueuePropertyModel model){
        
        model.Name = this.queueName;
        model.Type = ConstantConverter.ConvertQueueTypeToConstant(this.queueType);
        model.Description = getTextFieldValue(this.DescriptionTextBox);
        model.InhibitPut = getComboBoxValue(this.PutMessageComboBox);
        model.InhibitGet = getComboBoxValue(this.GetMessageComboBox);
        model.DefPriority = getSpinnerBoxValue(this.DefaultPrioritySpinner);
        model.DefPersistence = getComboBoxValue(this.PersistenceComboBox);
        model.Scope = getComboBoxValue(this.ScopeComboBox);
        model.Usage = getComboBoxValue(this.UsageComboBox);
        model.RemoteQName = getTextFieldValue(this.RemoteQueueTextField);
        model.RemoteQMgrName = getTextFieldValue(this.RemoteQueueManagerTextField);
        model.XmitQName = getTextFieldValue(this.TransmissionQueueTextField);
        model.BaseObjectName = getTextFieldValue(this.BaseObjectTextField);
        model.TargetType = getComboBoxValue(this.BaseObjectTypeComboBox);
        
        model.MaxQDepth = getSpinnerBoxValue(this.MaxQueueDepthSpinner);
        model.MaxMsgLength = getSpinnerBoxValue(this.MaxMessageLengthSpinner);
        model.Shareability = getComboBoxValue(this.ShareabilityComboBox);
        model.DefInputOpenOption = getComboBoxValue(this.DefaultInputOpenOptionComboBox);
        model.MsgDeliverySequence = getComboBoxValue(this.MessageDeliverySequenceComboBox);
        model.RetentionInterval = getSpinnerBoxValue(this.RetentionIntervalSpinner);
        model.DefinitionType = getComboBoxValue(this.DefinitionTypeComboBox);
        model.DistLists = getComboBoxValue(this.DistributionListComboBox);
        model.DefReadAhead = getComboBoxValue(this.DefaultReadAheadComboBox);
        model.DefaultPutResponse = getComboBoxValue(this.DefaultPutResponseComboBox);
        model.PropertyControl = getComboBoxValue(this.PriorityControlComboBox);
        model.Custom = getTextFieldValue(this.CustomTextBox);
        model.ClusterChannelName = getTextFieldValue(this.ClusterChannelNameTextField);
        
        model.ClusterName = getTextFieldValue(this.ShareInClusterTextField);
        model.ClusterNamelist = getTextFieldValue(this.ShareInListClusterTextField);
        model.DefBind = getComboBoxValue(this.DefaultBindTypeComboBox);
        model.CLWLQueueRank = getSpinnerBoxValue(this.CLWLRankSpinner);
        model.CLWLQueuePriority = getSpinnerBoxValue(this.CLWLPrioritySpinner);
        model.CLWLUseQ = getComboBoxValue(this.ClusterWorkloadComboBox);
        
        model.TriggerControl = getComboBoxValue(this.TriggerControlComboBox);
        model.TriggerType = getComboBoxValue(this.TriggerTypeComboBox);
        model.TriggerDepth = getSpinnerBoxValue(this.TriggerDepthDepthSpinner);
        model.TriggerMsgPriority = getSpinnerBoxValue(this.TriggerMsgPrioritySpinner);
        model.TriggerData = getTextFieldValue(this.TriggerDataTextField);
        model.InitiationQName = getTextFieldValue(this.InitiationQueueTextField);
        model.ProcessName = getTextFieldValue(this.ProcessNameTextField);
        
        model.QDepthMaxEvent = getComboBoxValue(this.QueueDepthMaxEventComboBox);
        model.QDepthHighEvent = getComboBoxValue(this.QueueDepthHighEventComboBox);
        model.QDepthHighLimit = getSpinnerBoxValue(this.QueueDepthHighLimitSpinner);
        model.QDepthLowEvent = getComboBoxValue(this.QueueDepthLowlEventComboBox);
        model.QDepthLowLimit = getSpinnerBoxValue(this.QueueDepthLowLimitSpinner);
        model.QServiceInterval = getSpinnerBoxValue(this.QueueServiceIntervalSpinner);
        model.QServiceIntervalEvent = getComboBoxValue(this.QueueServiceIntervalEventComboBox);
        
        model.BackoutRequeueName = getTextFieldValue(this.BackoutRequeueQueueTextField);
        model.BackoutThreshold = getSpinnerBoxValue(this.BackoutThresholdSpinner);
        model.HardenGetBackout = getComboBoxValue(this.HardenGetBackoutComboBox);
        model.NonPersistentMessageClass = getComboBoxValue(this.NPMClassComboBox);
        
        model.QueueMonitoring = getComboBoxValue(this.QueueMonitoringComboBox);
        model.QueueAccounting = getComboBoxValue(this.QueueAccountingComboBox);
        model.QueueStatistics = getComboBoxValue(this.QueueStatisticComboBox);
    }
    
    private void initComboBoxValue(){
        this.PutMessageComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Allowed", MQConstants.MQQA_PUT_ALLOWED), new ComboBoxItemModel("Inhibited", MQConstants.MQQA_PUT_INHIBITED)
        }));
        this.GetMessageComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Allowed", MQConstants.MQQA_GET_ALLOWED), new ComboBoxItemModel("Inhibited", MQConstants.MQQA_GET_INHIBITED)
        }));
        this.PersistenceComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Persistent", MQConstants.MQPER_PERSISTENT), new ComboBoxItemModel("Not persistent", MQConstants.MQPER_NOT_PERSISTENT)
        }));
        this.ScopeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQSCO_Q_MGR), new ComboBoxItemModel("Cell", MQConstants.MQSCO_CELL)
        }));
        this.ScopeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQSCO_Q_MGR), new ComboBoxItemModel("Cell", MQConstants.MQSCO_CELL)
        }));
        this.UsageComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Normal", MQConstants.MQUS_NORMAL), new ComboBoxItemModel("Transmission", MQConstants.MQUS_TRANSMISSION)
        }));
        this.BaseObjectTypeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue", MQConstants.MQOT_Q), new ComboBoxItemModel("Topic", MQConstants.MQOT_TOPIC)
        }));
        this.ShareabilityComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Shareable", MQConstants.MQQA_SHAREABLE), new ComboBoxItemModel("Not shareable", MQConstants.MQQA_NOT_SHAREABLE)
        }));
        this.DefaultInputOpenOptionComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Exclusive", MQConstants.MQOO_INPUT_EXCLUSIVE), new ComboBoxItemModel("Shared", MQConstants.MQOO_INPUT_SHARED)
        }));
        this.MessageDeliverySequenceComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Priority", MQConstants.MQMDS_PRIORITY), new ComboBoxItemModel("FIFO", MQConstants.MQMDS_FIFO)
        }));
        this.DefinitionTypeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Predefined", MQConstants.MQQDT_PREDEFINED), new ComboBoxItemModel("Permanent dynamic", MQConstants.MQQDT_PERMANENT_DYNAMIC), 
            new ComboBoxItemModel("Shared dynamic", MQConstants.MQQDT_SHARED_DYNAMIC),new ComboBoxItemModel("Temporary dynamic", MQConstants.MQQDT_TEMPORARY_DYNAMIC)
        }));
        this.DistributionListComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Supported", MQConstants.MQDL_SUPPORTED), new ComboBoxItemModel("Not supported", MQConstants.MQDL_NOT_SUPPORTED)
        }));
        this.DefaultReadAheadComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("No", MQConstants.MQREADA_NO), new ComboBoxItemModel("Yes", MQConstants.MQREADA_YES), 
            new ComboBoxItemModel("Disabled", MQConstants.MQREADA_DISABLED)
        }));
        this.DefaultPutResponseComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Sync", MQConstants.MQPRT_SYNC_RESPONSE), new ComboBoxItemModel("Async", MQConstants.MQPRT_ASYNC_RESPONSE), 
        }));
        this.PriorityControlComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Compatibility", MQConstants.MQPROP_COMPATIBILITY), new ComboBoxItemModel("None", MQConstants.MQPROP_NONE), 
            new ComboBoxItemModel("All", MQConstants.MQPROP_ALL),new ComboBoxItemModel("Force MQRFH2", MQConstants.MQPROP_FORCE_MQRFH2),
        }));
        this.DefaultBindTypeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Open", MQConstants.MQBND_BIND_ON_OPEN), new ComboBoxItemModel("Not fixed", MQConstants.MQBND_BIND_NOT_FIXED), 
            new ComboBoxItemModel("On group", MQConstants.MQBND_BIND_ON_GROUP)
        }));
        this.ClusterWorkloadComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQCLWL_USEQ_AS_Q_MGR), new ComboBoxItemModel("Any", MQConstants.MQCLWL_USEQ_ANY), 
            new ComboBoxItemModel("Local", MQConstants.MQCLWL_USEQ_LOCAL)
        }));
        this.TriggerControlComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Off", MQConstants.MQTC_OFF), new ComboBoxItemModel("On", MQConstants.MQTC_ON), 
        }));
        this.TriggerTypeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("None", MQConstants.MQTT_NONE), new ComboBoxItemModel("First", MQConstants.MQTT_FIRST), 
            new ComboBoxItemModel("Every", MQConstants.MQTT_EVERY),new ComboBoxItemModel("Depth", MQConstants.MQTT_DEPTH),
        }));
        this.QueueDepthMaxEventComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Disabled", MQConstants.MQEVR_DISABLED), new ComboBoxItemModel("Enabled", MQConstants.MQEVR_ENABLED), 
        }));
        this.QueueDepthHighEventComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Disabled", MQConstants.MQEVR_DISABLED), new ComboBoxItemModel("Enabled", MQConstants.MQEVR_ENABLED), 
        }));
        this.QueueDepthLowlEventComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Disabled", MQConstants.MQEVR_DISABLED), new ComboBoxItemModel("Enabled", MQConstants.MQEVR_ENABLED), 
        }));
        this.QueueServiceIntervalEventComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("High", MQConstants.MQQSIE_HIGH), new ComboBoxItemModel("Ok", MQConstants.MQQSIE_OK), 
            new ComboBoxItemModel("None", MQConstants.MQQSIE_NONE)
        }));
        this.HardenGetBackoutComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Hardened", MQConstants.MQQA_BACKOUT_HARDENED), new ComboBoxItemModel("Not hardened", MQConstants.MQQA_BACKOUT_NOT_HARDENED), 
        }));
        this.NPMClassComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Normal", MQConstants.MQNPM_CLASS_NORMAL), new ComboBoxItemModel("High", MQConstants.MQNPM_CLASS_HIGH), 
        }));
        this.QueueMonitoringComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQMON_Q_MGR), new ComboBoxItemModel("Off", MQConstants.MQMON_OFF), 
            new ComboBoxItemModel("Low", MQConstants.MQMON_LOW),new ComboBoxItemModel("Medium", MQConstants.MQMON_MEDIUM),
            new ComboBoxItemModel("High", MQConstants.MQMON_HIGH),
        }));
        this.QueueStatisticComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQMON_Q_MGR), new ComboBoxItemModel("Off", MQConstants.MQMON_OFF), 
            new ComboBoxItemModel("On", MQConstants.MQMON_ON)
        }));
        this.QueueAccountingComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQMON_Q_MGR), new ComboBoxItemModel("Off", MQConstants.MQMON_OFF), 
            new ComboBoxItemModel("On", MQConstants.MQMON_ON)
        }));
    }
        
    private void setDetailListItems(QueueType queueType){
        switch(queueType){
            case Local :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "Cluster", "Triggering", "Events", "Storage", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;
            case Remote :
            case Alias :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "Cluster", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;   
            case Model :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "Triggering", "Events", "Storage", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;
        }
        DetailList.setSelectedIndex(0);


    }
    
    private String getDefaultCopyFromQueueName(QueueType type){
        switch (type){
            case Local:
                return MQPCF.CheckQueueExist(queueManager, defaultCopyFromLocalQueue)? this.defaultCopyFromLocalQueue : null;
            case Model:
                return MQPCF.CheckQueueExist(queueManager, defaultCopyFromModelQueue)? this.defaultCopyFromModelQueue : null;
            case Alias:
                return MQPCF.CheckQueueExist(queueManager, defaultCopyFromAliasQueue)? this.defaultCopyFromAliasQueue : null;
            case Remote:
                return MQPCF.CheckQueueExist(queueManager, defaultCopyFromRemoteQueue)? this.defaultCopyFromRemoteQueue : null;
        }
        return null;
    }
    
    private void createOrChangeQueue(MQQueueManager queueManager, MQQueuePropertyModel model, boolean isCreate){
        MQCommandResult result = isCreate == true ? MQPCF.CreateQueue(queueManager, model) : MQPCF.UpdateQueueProperties(queueManager, model) ;
        if(result.QuerySuccess){
            JOptionPane.showMessageDialog(this, isCreate == true ? "New queue has been created" :  "Queue properties have been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
            FireActionEvent();
            Close();                    
        }
        else{
            JOptionPane.showMessageDialog(this, result.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }
    
   
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AlterationDateLabel;
    private javax.swing.JTextField AlterationDateTextField;
    private javax.swing.JLabel AlterationTimeLabel;
    private javax.swing.JTextField AlterationTimeTextField;
    private javax.swing.JButton BackButton;
    private javax.swing.JLabel BackoutRequeueQueueLabel;
    private javax.swing.JTextField BackoutRequeueQueueTextField;
    private javax.swing.JLabel BackoutThresholdLabel;
    private javax.swing.JSpinner BackoutThresholdSpinner;
    private javax.swing.JLabel BaseObjectLabel;
    private javax.swing.JTextField BaseObjectTextField;
    private javax.swing.JComboBox BaseObjectTypeComboBox;
    private javax.swing.JLabel BaseObjectTypeLabel;
    private javax.swing.JSpinner CLWLPrioritySpinner;
    private javax.swing.JLabel CLWLQueuePriorityLabel;
    private javax.swing.JLabel CLWLQueueRankLabel;
    private javax.swing.JSpinner CLWLRankSpinner;
    private javax.swing.JButton CancelButton;
    private javax.swing.JLabel ClusterChannelNameLabel;
    private javax.swing.JTextField ClusterChannelNameTextField;
    private javax.swing.JPanel ClusterPanel;
    private javax.swing.JComboBox ClusterWorkloadComboBox;
    private javax.swing.JLabel ClusterWorkloadUseQueueLabel;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JTextField CopyFromTextField;
    private javax.swing.JLabel CreationDateLabel;
    private javax.swing.JTextField CreationDateTextField;
    private javax.swing.JLabel CreationTimeLabel;
    private javax.swing.JTextField CreationTimeTextField;
    private javax.swing.JLabel CurrentQueueDepthLabel;
    private javax.swing.JTextField CurrentQueueDepthTextField;
    private javax.swing.JLabel CustomLabel;
    private javax.swing.JTextField CustomTextBox;
    private javax.swing.JComboBox DefaultBindTypeComboBox;
    private javax.swing.JLabel DefaultBindTypeLabel;
    private javax.swing.JComboBox DefaultInputOpenOptionComboBox;
    private javax.swing.JLabel DefaultInputOpenOptionLabel;
    private javax.swing.JLabel DefaultPersistenceLabel;
    private javax.swing.JLabel DefaultPriorityLabel;
    private javax.swing.JSpinner DefaultPrioritySpinner;
    private javax.swing.JComboBox DefaultPutResponseComboBox;
    private javax.swing.JLabel DefaultPutResponseTypeLabel;
    private javax.swing.JComboBox DefaultReadAheadComboBox;
    private javax.swing.JLabel DefaultReadAheadLabel;
    private javax.swing.JComboBox DefinitionTypeComboBox;
    private javax.swing.JLabel DefinitionTypeLabel;
    private javax.swing.JTextField DescriptionTextBox;
    private javax.swing.JList DetailList;
    private javax.swing.JComboBox DistributionListComboBox;
    private javax.swing.JLabel DistributionListLabel;
    private javax.swing.JPanel EventsPanel;
    private javax.swing.JPanel ExtendedPanel;
    private javax.swing.JButton FinishButton;
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JComboBox GetMessageComboBox;
    private javax.swing.JLabel GetMessageLabel;
    private javax.swing.JComboBox HardenGetBackoutComboBox;
    private javax.swing.JLabel HardenGetBackoutLabel;
    private javax.swing.JPanel InitialPanel;
    private javax.swing.JLabel InitiationQueueLabel;
    private javax.swing.JTextField InitiationQueueTextField;
    private javax.swing.JLabel MaxMessageLengthLabel;
    private javax.swing.JSpinner MaxMessageLengthSpinner;
    private javax.swing.JLabel MaxQueueDepthLabel;
    private javax.swing.JSpinner MaxQueueDepthSpinner;
    private javax.swing.JComboBox MessageDeliverySequenceComboBox;
    private javax.swing.JLabel MessageDeliverySequenceLabel;
    private javax.swing.JComboBox NPMClassComboBox;
    private javax.swing.JLabel NPMClassLabel;
    private javax.swing.JButton NextButton;
    private javax.swing.JRadioButton NotShareInClusterRadioButton;
    private javax.swing.JTextField ObjectNameTextField;
    private javax.swing.JLabel OpenInputCountLabel;
    private javax.swing.JTextField OpenInputCountTextField;
    private javax.swing.JLabel OpenOutputCountLabel;
    private javax.swing.JTextField OpenOutputCountTextField;
    private javax.swing.JComboBox PersistenceComboBox;
    private javax.swing.JComboBox PriorityControlComboBox;
    private javax.swing.JLabel ProcessNameLabel;
    private javax.swing.JTextField ProcessNameTextField;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel PropertyControlLabel;
    private javax.swing.JComboBox PutMessageComboBox;
    private javax.swing.JLabel PutMessageLabel;
    private javax.swing.JComboBox QueueAccountingComboBox;
    private javax.swing.JLabel QueueAccountingLabel;
    private javax.swing.JComboBox QueueDepthHighEventComboBox;
    private javax.swing.JLabel QueueDepthHighEventsLabel;
    private javax.swing.JLabel QueueDepthHighLimitLabel;
    private javax.swing.JSpinner QueueDepthHighLimitSpinner;
    private javax.swing.JLabel QueueDepthLowEventsLabel;
    private javax.swing.JLabel QueueDepthLowLimitLabel;
    private javax.swing.JSpinner QueueDepthLowLimitSpinner;
    private javax.swing.JComboBox QueueDepthLowlEventComboBox;
    private javax.swing.JComboBox QueueDepthMaxEventComboBox;
    private javax.swing.JLabel QueueDepthMaxEventLabel;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JComboBox QueueMonitoringComboBox;
    private javax.swing.JLabel QueueMonitoringLabel;
    private javax.swing.JTextField QueueNameTextBox;
    private javax.swing.JComboBox QueueServiceIntervalEventComboBox;
    private javax.swing.JLabel QueueServiceIntervalEventLabel;
    private javax.swing.JLabel QueueServiceIntervalLabel;
    private javax.swing.JSpinner QueueServiceIntervalSpinner;
    private javax.swing.JComboBox QueueStatisticComboBox;
    private javax.swing.JLabel QueueStatisticsLabel;
    private javax.swing.JTextField QueueTypeTextBox;
    private javax.swing.JLabel RemoteQueueLabel;
    private javax.swing.JLabel RemoteQueueManagerLabel;
    private javax.swing.JTextField RemoteQueueManagerTextField;
    private javax.swing.JTextField RemoteQueueTextField;
    private javax.swing.JLabel RetentionIntervalLabel;
    private javax.swing.JSpinner RetentionIntervalSpinner;
    private javax.swing.JComboBox ScopeComboBox;
    private javax.swing.JLabel ScopeLabel;
    private javax.swing.JButton SelectBackoutReQueueButton;
    private javax.swing.JButton SelectInitQButton;
    private javax.swing.JButton SelectObjectButton;
    private javax.swing.JButton SelectTransmissionQueueButton;
    private javax.swing.JPanel SetupDetailPanel;
    private javax.swing.JPanel SetupPanel;
    private javax.swing.JRadioButton ShareInClusterRadioButton;
    private javax.swing.JTextField ShareInClusterTextField;
    private javax.swing.JPanel ShareInClustersPanel;
    private javax.swing.JRadioButton ShareInListClusterRadioButton;
    private javax.swing.JTextField ShareInListClusterTextField;
    private javax.swing.JComboBox ShareabilityComboBox;
    private javax.swing.JLabel ShareabilityLabel;
    private javax.swing.ButtonGroup SharingInClustersNuttonGroup;
    private javax.swing.JPanel StatisticsPanel;
    private javax.swing.JPanel StoragePanel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JLabel TransmissionQueueLabel;
    private javax.swing.JTextField TransmissionQueueTextField;
    private javax.swing.JComboBox TriggerControlComboBox;
    private javax.swing.JLabel TriggerControlLabel;
    private javax.swing.JLabel TriggerDataLable;
    private javax.swing.JTextField TriggerDataTextField;
    private javax.swing.JSpinner TriggerDepthDepthSpinner;
    private javax.swing.JLabel TriggerDepthLabel;
    private javax.swing.JLabel TriggerMessagePriorityLabel;
    private javax.swing.JSpinner TriggerMsgPrioritySpinner;
    private javax.swing.JComboBox TriggerTypeComboBox;
    private javax.swing.JLabel TriggerTypeLabel;
    private javax.swing.JPanel TriggeringPanel;
    private javax.swing.JComboBox UsageComboBox;
    private javax.swing.JLabel UsageLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
