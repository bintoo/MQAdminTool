/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.ConstantConverter;
import MQApi.Enums.ChannelType;
import MQApi.Enums.QueueType;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQChannelPropertyModel;
import MQApi.QueryModel.MQCommandResult;
import MQApi.Utilities.MQUtility;
import UI.Misc.*;
import UI.Models.*;
import com.ibm.mq.MQException;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author jzhou
 */
public class ChannelProperitiesDialog extends ObjectPropertiesDialogBase {
    
    private ChannelType channelType;
    private boolean isCreate;
    private Hashtable<String, JPanel> categoryPanels = new Hashtable<String, JPanel>();
    private String channelName;
    private String objectNameRegex =  "^[0-9A-Za-z./_%]+$";
    private String defaultCopyFromSender = "SYSTEM.DEF.SENDER";
    private String defaultCopyFromServer = "SYSTEM.DEF.SERVER";
    private String defaultCopyFromRequestor = "SYSTEM.DEF.REQUESTER";
    private String defaultCopyFromServerConnection = "SYSTEM.DEF.SVRCONN";
    private String defaultCopyFromClusterSender = "SYSTEM.DEF.CLUSSDR";
    private String defaultCopyFromClusterReceiver = "SYSTEM.DEF.CLUSRCVR";
    private String defaultCopyFromReceiver = "SYSTEM.DEF.RECEIVER";
    boolean isAtInitialPanel;
    MQChannelPropertyModel propertyModel;

    public ChannelProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, ChannelType channelType) {
        super(parent, modal, queueManager, null);
        this.isCreate = true;
        this.channelType = channelType;
        initComponents();
        initCustomProperties();
        setDetailListItems(channelType);
        this.CopyFromTextField.setText(getDefaultCopyFromQueueName(channelType));
    }
    
    public ChannelProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String name) {
        super(parent, modal, queueManager, null);
        this.isCreate = false;        
        this.channelName = name;
        initComponents();
        initCustomProperties();
        loadModelAndSetupUI(channelName, this.isCreate);
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
        ChannelNameTextBox = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ChannelTypeTextBox = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TransmissionProtocolLabel = new javax.swing.JLabel();
        DescriptionTextBox = new javax.swing.JTextField();
        TransmissionProtocolComboBox = new javax.swing.JComboBox();
        ConnectionNameLabel = new javax.swing.JLabel();
        TransmissionQueueLabel = new javax.swing.JLabel();
        ConnectionNameTextField = new javax.swing.JTextField();
        TransmissionQueueTextField = new javax.swing.JTextField();
        SelectTransmissionQueueButton = new javax.swing.JButton();
        LocalCommunicationAddressLabel = new javax.swing.JLabel();
        LocalCommunicationAddressTextField = new javax.swing.JTextField();
        ExtendedPanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        HeartbeatIntervalLabel = new javax.swing.JLabel();
        HeartbearIntercalSpinner = new javax.swing.JSpinner();
        MaxMessageLengthLabel = new javax.swing.JLabel();
        NonPersistentMessageSpeedLabel = new javax.swing.JLabel();
        SequenceNumberWrapLabel = new javax.swing.JLabel();
        DataConversionLabel = new javax.swing.JLabel();
        PropertyControlLabel = new javax.swing.JLabel();
        MaxMessageLengthSpinner = new javax.swing.JSpinner();
        NonPersistentMessageSpeedComboBox = new javax.swing.JComboBox();
        SequenceNumberWraplSpinner = new javax.swing.JSpinner();
        DataConversionComboBox = new javax.swing.JComboBox();
        PriorityControlComboBox = new javax.swing.JComboBox();
        KeepAliveIntervalLabel = new javax.swing.JLabel();
        KeepAliveIntervalSpinner = new javax.swing.JSpinner();
        BatchSizeLabel = new javax.swing.JLabel();
        BatchSizeSpinner = new javax.swing.JSpinner();
        BatchIntervalLabel = new javax.swing.JLabel();
        BatchIntervaSpinner = new javax.swing.JSpinner();
        DisconnectIntervalLabel = new javax.swing.JLabel();
        DisconnectIntervalSpinner = new javax.swing.JSpinner();
        BatchheartBeatIntervalLabel = new javax.swing.JLabel();
        BatchheartBeatIntervallSpinner = new javax.swing.JSpinner();
        BatchDataLimitLabel = new javax.swing.JLabel();
        BatchDataLimitSpinner = new javax.swing.JSpinner();
        UseDeadLetterQueueLabel = new javax.swing.JLabel();
        UseDeadLetterQueueComboBox = new javax.swing.JComboBox();
        PutAuthorityLabel = new javax.swing.JLabel();
        PutAuthorityComboBox = new javax.swing.JComboBox();
        SharingConversationLabel = new javax.swing.JLabel();
        SharingConversationSpinner = new javax.swing.JSpinner();
        ClusterPanel = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        ShareInClustersPanel = new javax.swing.JPanel();
        NotShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterRadioButton = new javax.swing.JRadioButton();
        ShareInListClusterRadioButton = new javax.swing.JRadioButton();
        ShareInClusterTextField = new javax.swing.JTextField();
        ShareInListClusterTextField = new javax.swing.JTextField();
        CLWLQueueRankLabel = new javax.swing.JLabel();
        CLWLChannelPriorityLabel = new javax.swing.JLabel();
        CLWLRankSpinner = new javax.swing.JSpinner();
        CLWLPrioritySpinner = new javax.swing.JSpinner();
        NetworkPriorityLabel = new javax.swing.JLabel();
        NetworkPrioritySpinner = new javax.swing.JSpinner();
        CLWLQueueWeightLabel = new javax.swing.JLabel();
        CLWLChanneWeightSpinner = new javax.swing.JSpinner();
        MCAPanel = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        MCATypeLabel = new javax.swing.JLabel();
        MCAUserIdLabel = new javax.swing.JLabel();
        MCATypeComboBox = new javax.swing.JComboBox();
        MCAUserIdTextField = new javax.swing.JTextField();
        ExitPanel = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        ReceiveExitLabel = new javax.swing.JLabel();
        ReceiveExitTextField = new javax.swing.JTextField();
        ReceiveExitUserDataLabel = new javax.swing.JLabel();
        ReceiveExitUserDataTextField = new javax.swing.JTextField();
        SendExitLabel = new javax.swing.JLabel();
        SendExitField = new javax.swing.JTextField();
        SendExitUserDataLabel = new javax.swing.JLabel();
        SendExitUserDataTextField = new javax.swing.JTextField();
        MessageExitLabel = new javax.swing.JLabel();
        MessageExitUserDataLabel = new javax.swing.JLabel();
        SecurityExitLabel = new javax.swing.JLabel();
        SecurityExitUserDataLabel = new javax.swing.JLabel();
        SecurityExitUserDataTextField = new javax.swing.JTextField();
        SecurityExitTextField = new javax.swing.JTextField();
        MessageExitUserDataTextField = new javax.swing.JTextField();
        MessageExitTextField = new javax.swing.JTextField();
        RetryPanel = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        ShortRetryCountLabel = new javax.swing.JLabel();
        ShortRetryCountSpinner = new javax.swing.JSpinner();
        ShortRetryIntervalLabel = new javax.swing.JLabel();
        ShortRetryIntervalSpinner = new javax.swing.JSpinner();
        LongRetryCountLabel = new javax.swing.JLabel();
        LongRetryCountSpinner = new javax.swing.JSpinner();
        LongRetryIntervalLabel = new javax.swing.JLabel();
        LongRetryIntervalSpinner = new javax.swing.JSpinner();
        StatisticsPanel = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        ChannelMonitoringLabel = new javax.swing.JLabel();
        ChannelStatisticsLabel = new javax.swing.JLabel();
        ChannelMonitoringComboBox = new javax.swing.JComboBox();
        ChannelStatisticComboBox = new javax.swing.JComboBox();
        AlterationDateLabel = new javax.swing.JLabel();
        AlterationTimeLabel = new javax.swing.JLabel();
        AlterationDateTextField = new javax.swing.JTextField();
        AlterationTimeTextField = new javax.swing.JTextField();
        MessageRetryPanel = new javax.swing.JPanel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        MessageRetryCountLabel = new javax.swing.JLabel();
        MessageRetryCountSpinner = new javax.swing.JSpinner();
        MessageRetryIntervalLabel = new javax.swing.JLabel();
        MessageRetryIntervaSpinner = new javax.swing.JSpinner();
        MessageRetryExitNameLabel = new javax.swing.JLabel();
        MessageRetryExitNameTextField = new javax.swing.JTextField();
        MessageRetryExitUserDataLabel = new javax.swing.JLabel();
        MessageRetryExitUserDataTextField = new javax.swing.JTextField();
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

        jLabel2.setText("Channel name :");
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
            String[] strings = { "General", "Extended", "MCA", "Exits", "Cluster", "Statistics" };
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

        jLabel11.setText("Channel name:");
        jLabel11.setPreferredSize(new java.awt.Dimension(200, 25));

        ChannelNameTextBox.setEditable(false);
        ChannelNameTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        jLabel12.setText("Channel type:");
        jLabel12.setPreferredSize(new java.awt.Dimension(200, 25));

        ChannelTypeTextBox.setEditable(false);
        ChannelTypeTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        jLabel13.setText("Description:");
        jLabel13.setPreferredSize(new java.awt.Dimension(200, 25));

        TransmissionProtocolLabel.setText("Transmission protocol:");
        TransmissionProtocolLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DescriptionTextBox.setPreferredSize(new java.awt.Dimension(280, 25));

        TransmissionProtocolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Allowed", "Inhibited" }));
        TransmissionProtocolComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ConnectionNameLabel.setText("Connection name:");
        ConnectionNameLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        TransmissionQueueLabel.setText("Transmission queue:");
        TransmissionQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ConnectionNameTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        TransmissionQueueTextField.setEditable(false);
        TransmissionQueueTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SelectTransmissionQueueButton.setText("Select");
        SelectTransmissionQueueButton.setPreferredSize(new java.awt.Dimension(70, 25));
        SelectTransmissionQueueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectTransmissionQueueButtonActionPerformed(evt);
            }
        });

        LocalCommunicationAddressLabel.setText("Local communication address:");
        LocalCommunicationAddressLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        LocalCommunicationAddressTextField.setPreferredSize(new java.awt.Dimension(280, 25));

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
                                .addComponent(ConnectionNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ConnectionNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(TransmissionProtocolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TransmissionProtocolComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DescriptionTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ChannelNameTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ChannelTypeTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(24, 24, 24))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(TransmissionQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TransmissionQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SelectTransmissionQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(LocalCommunicationAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LocalCommunicationAddressTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChannelNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChannelTypeTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TransmissionProtocolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TransmissionProtocolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ConnectionNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ConnectionNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TransmissionQueueTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SelectTransmissionQueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TransmissionQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LocalCommunicationAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LocalCommunicationAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
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

        HeartbeatIntervalLabel.setText("Heartbeat interval:");
        HeartbeatIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        HeartbearIntercalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        HeartbearIntercalSpinner.setModel(new SpinnerNumberModel(0, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.HeartbearIntercalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.HeartbearIntercalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        MaxMessageLengthLabel.setText("Maximum message length (bytes):");
        MaxMessageLengthLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        NonPersistentMessageSpeedLabel.setText("Non-persistent message speed:");
        NonPersistentMessageSpeedLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SequenceNumberWrapLabel.setText("Sequence number wrap:");
        SequenceNumberWrapLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DataConversionLabel.setText("Data conversion:");
        DataConversionLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        PropertyControlLabel.setText("Property control:");
        PropertyControlLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MaxMessageLengthSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MaxMessageLengthSpinner.setModel(new SpinnerNumberModel(0, 0, 104857600, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MaxMessageLengthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        NonPersistentMessageSpeedComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Priority", "FIFO" }));
        NonPersistentMessageSpeedComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        SequenceNumberWraplSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        SequenceNumberWraplSpinner.setModel(new SpinnerNumberModel(100, 100, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.SequenceNumberWraplSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.SequenceNumberWraplSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        DataConversionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asynchronous", "Synchronous" }));
        DataConversionComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PriorityControlComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Compatibility", "Force MQRFH2", "None", "Version 6 compatible" }));
        PriorityControlComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        KeepAliveIntervalLabel.setText("Keep alive interval (seconds):");
        KeepAliveIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        KeepAliveIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        KeepAliveIntervalSpinner.setModel(new SpinnerNumberModel(0, 0, 99999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.KeepAliveIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.KeepAliveIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        BatchSizeLabel.setText("Batch size:");
        BatchSizeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BatchSizeSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        BatchSizeSpinner.setModel(new SpinnerNumberModel(1, 1, 9999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BatchSizeSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BatchSizeSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        BatchIntervalLabel.setText("Batch interval (milliseconds):");
        BatchIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BatchIntervaSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        BatchIntervaSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BatchIntervaSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BatchIntervaSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        DisconnectIntervalLabel.setText("Disconnect interval (seconds):");
        DisconnectIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        DisconnectIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        DisconnectIntervalSpinner.setModel(new SpinnerNumberModel(0, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.DisconnectIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.DisconnectIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        BatchheartBeatIntervalLabel.setText("Batch heartbeat interval:");
        BatchheartBeatIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BatchheartBeatIntervallSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        BatchheartBeatIntervallSpinner.setModel(new SpinnerNumberModel(0, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BatchheartBeatIntervallSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BatchheartBeatIntervallSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        BatchDataLimitLabel.setText("Batch data limit:");
        BatchDataLimitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        BatchDataLimitSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        BatchDataLimitSpinner.setModel(new SpinnerNumberModel(5000, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BatchDataLimitSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BatchDataLimitSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        UseDeadLetterQueueLabel.setText("Use dead-letter queue:");
        UseDeadLetterQueueLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        UseDeadLetterQueueComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Asynchronous", "Synchronous" }));
        UseDeadLetterQueueComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        PutAuthorityLabel.setText("Put authority:");
        PutAuthorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        PutAuthorityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "All", "Compatibility", "Force MQRFH2", "None", "Version 6 compatible" }));
        PutAuthorityComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        SharingConversationLabel.setText("Sharing conversation:");
        SharingConversationLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SharingConversationSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        SharingConversationSpinner.setModel(new SpinnerNumberModel(10, 0, 999999999, 10));
        ((NumberFormatter)((JSpinner.NumberEditor)this.SharingConversationSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.SharingConversationSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BatchheartBeatIntervalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HeartbeatIntervalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BatchDataLimitLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaxMessageLengthLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NonPersistentMessageSpeedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UseDeadLetterQueueLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SequenceNumberWrapLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutAuthorityLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DataConversionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PropertyControlLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SharingConversationLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(KeepAliveIntervalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BatchSizeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BatchIntervalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisconnectIntervalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BatchheartBeatIntervallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(HeartbearIntercalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(BatchDataLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(UseDeadLetterQueueComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(PutAuthorityComboBox, 0, 250, Short.MAX_VALUE)
                    .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(SharingConversationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(NonPersistentMessageSpeedComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(SequenceNumberWraplSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(DataConversionComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(PriorityControlComboBox, 0, 0, Short.MAX_VALUE)
                    .addComponent(KeepAliveIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(BatchSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(BatchIntervaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(DisconnectIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(HeartbeatIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxMessageLengthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SequenceNumberWrapLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KeepAliveIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisconnectIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PropertyControlLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchDataLimitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataConversionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NonPersistentMessageSpeedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UseDeadLetterQueueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PutAuthorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchheartBeatIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SharingConversationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(HeartbearIntercalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MaxMessageLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SequenceNumberWraplSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(KeepAliveIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DisconnectIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PriorityControlComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchDataLimitSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataConversionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NonPersistentMessageSpeedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchIntervaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UseDeadLetterQueueComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PutAuthorityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BatchheartBeatIntervallSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SharingConversationSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        CLWLChannelPriorityLabel.setText("CLWL queue priority:");
        CLWLChannelPriorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CLWLRankSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        CLWLRankSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.CLWLRankSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.CLWLRankSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        CLWLPrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        CLWLPrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.CLWLPrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.CLWLPrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        NetworkPriorityLabel.setText("Network priority:");
        NetworkPriorityLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        NetworkPrioritySpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        NetworkPrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.NetworkPrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.NetworkPrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        CLWLQueueWeightLabel.setText("CLWL queue weight:");
        CLWLQueueWeightLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        CLWLChanneWeightSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        CLWLChanneWeightSpinner.setModel(new SpinnerNumberModel(1, 1, 99, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.CLWLChanneWeightSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.CLWLChanneWeightSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(CLWLQueueWeightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLChanneWeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(NetworkPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NetworkPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addComponent(ShareInClustersPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(CLWLQueueRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(CLWLChannelPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(26, 26, 26))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ShareInClustersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NetworkPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NetworkPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLWLQueueRankLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLRankSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLWLChannelPriorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLPrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CLWLQueueWeightLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CLWLChanneWeightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        jLabel7.setText("MCA");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane5.setBorder(null);

        jPanel4.setPreferredSize(new java.awt.Dimension(490, 340));

        MCATypeLabel.setText("MCA type:");
        MCATypeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MCAUserIdLabel.setText("MCA user id:");
        MCAUserIdLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MCATypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Off", "On" }));
        MCATypeComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        MCAUserIdTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(MCAUserIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MCAUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(MCATypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MCATypeComboBox, 0, 1, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MCATypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MCATypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MCAUserIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MCAUserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(277, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel4);

        javax.swing.GroupLayout MCAPanelLayout = new javax.swing.GroupLayout(MCAPanel);
        MCAPanel.setLayout(MCAPanelLayout);
        MCAPanelLayout.setHorizontalGroup(
            MCAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MCAPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MCAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4)
                    .addGroup(MCAPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane5)
        );
        MCAPanelLayout.setVerticalGroup(
            MCAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MCAPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(MCAPanel, "card2");

        ExitPanel.setPreferredSize(new java.awt.Dimension(525, 431));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Exit");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane6.setBorder(null);

        jPanel5.setPreferredSize(new java.awt.Dimension(490, 340));

        ReceiveExitLabel.setText("Receive exit:");
        ReceiveExitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ReceiveExitTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        ReceiveExitUserDataLabel.setText("Receive exit user data:");
        ReceiveExitUserDataLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ReceiveExitUserDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SendExitLabel.setText("Send exit:");
        SendExitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SendExitField.setPreferredSize(new java.awt.Dimension(280, 25));

        SendExitUserDataLabel.setText("Send exit user data:");
        SendExitUserDataLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SendExitUserDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageExitLabel.setText("Message exit:");
        MessageExitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageExitUserDataLabel.setText("Message exit user data:");
        MessageExitUserDataLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SecurityExitLabel.setText("Security exit:");
        SecurityExitLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SecurityExitUserDataLabel.setText("Security exit user data:");
        SecurityExitUserDataLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        SecurityExitUserDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        SecurityExitTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageExitUserDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageExitTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SendExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SendExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SendExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SendExitField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(ReceiveExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReceiveExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(ReceiveExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ReceiveExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SecurityExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SecurityExitUserDataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(SecurityExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SecurityExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(MessageExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(MessageExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReceiveExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReceiveExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReceiveExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReceiveExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SendExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SendExitField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SendExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SendExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecurityExitLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SecurityExitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SecurityExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SecurityExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(126, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel5);

        javax.swing.GroupLayout ExitPanelLayout = new javax.swing.GroupLayout(ExitPanel);
        ExitPanel.setLayout(ExitPanelLayout);
        ExitPanelLayout.setHorizontalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator5)
                    .addGroup(ExitPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 355, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane6)
        );
        ExitPanelLayout.setVerticalGroup(
            ExitPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExitPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(ExitPanel, "card2");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Storage");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane7.setBorder(null);

        jPanel6.setPreferredSize(new java.awt.Dimension(490, 340));

        ShortRetryCountLabel.setText("Short retry count:");
        ShortRetryCountLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ShortRetryCountSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        ShortRetryCountSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.ShortRetryCountSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.ShortRetryCountSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        ShortRetryIntervalLabel.setText("Short retry interval (seconds):");
        ShortRetryIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ShortRetryIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        ShortRetryIntervalSpinner.setModel(new SpinnerNumberModel(0, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.ShortRetryIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.ShortRetryIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        LongRetryCountLabel.setText("Long retry count:");
        LongRetryCountLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        LongRetryCountSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        LongRetryCountSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.LongRetryCountSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.LongRetryCountSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        LongRetryIntervalLabel.setText("Long retry interval (seconds):");
        LongRetryIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        LongRetryIntervalSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        LongRetryIntervalSpinner.setModel(new SpinnerNumberModel(0, 0, 999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.LongRetryIntervalSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.LongRetryIntervalSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(LongRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LongRetryIntervalSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(LongRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LongRetryCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ShortRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ShortRetryIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(ShortRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ShortRetryCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShortRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShortRetryCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShortRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ShortRetryIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LongRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LongRetryCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LongRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LongRetryIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jScrollPane7.setViewportView(jPanel6);

        javax.swing.GroupLayout RetryPanelLayout = new javax.swing.GroupLayout(RetryPanel);
        RetryPanel.setLayout(RetryPanelLayout);
        RetryPanelLayout.setHorizontalGroup(
            RetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RetryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator6)
                    .addGroup(RetryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane7)
        );
        RetryPanelLayout.setVerticalGroup(
            RetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RetryPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(RetryPanel, "card2");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Statistics");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane8.setBorder(null);

        jPanel7.setPreferredSize(new java.awt.Dimension(490, 340));

        ChannelMonitoringLabel.setText("Channel monitoring:");
        ChannelMonitoringLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ChannelStatisticsLabel.setText("Channel statistics:");
        ChannelStatisticsLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        ChannelMonitoringComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "High", "Low", "Medium", "Off", "Queue manager" }));
        ChannelMonitoringComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        ChannelStatisticComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "On", "Off", "Queue manager" }));
        ChannelStatisticComboBox.setPreferredSize(new java.awt.Dimension(280, 25));

        AlterationDateLabel.setText("Alteration date:");
        AlterationDateLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        AlterationTimeLabel.setText("Alteration time:");
        AlterationTimeLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        AlterationDateTextField.setEditable(false);
        AlterationDateTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        AlterationTimeTextField.setEditable(false);
        AlterationTimeTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(AlterationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterationTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(ChannelStatisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChannelStatisticComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(ChannelMonitoringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ChannelMonitoringComboBox, 0, 1, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(AlterationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AlterationDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)))
                .addGap(39, 39, 39))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlterationDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlterationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AlterationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlterationTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChannelMonitoringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChannelMonitoringComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChannelStatisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChannelStatisticComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(247, Short.MAX_VALUE))
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

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Message retry");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 25));

        jScrollPane9.setBorder(null);

        jPanel8.setPreferredSize(new java.awt.Dimension(490, 340));

        MessageRetryCountLabel.setText("Message retry count:");
        MessageRetryCountLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageRetryCountSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MessageRetryCountSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MessageRetryCountSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MessageRetryCountSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        MessageRetryIntervalLabel.setText("Message retry interval:");
        MessageRetryIntervalLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageRetryIntervaSpinner.setPreferredSize(new java.awt.Dimension(280, 25));
        MessageRetryIntervaSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MessageRetryIntervaSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MessageRetryIntervaSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        MessageRetryExitNameLabel.setText("Message retry exit name:");
        MessageRetryExitNameLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageRetryExitNameTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        MessageRetryExitUserDataLabel.setText("Message retry exit user data:");
        MessageRetryExitUserDataLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MessageRetryExitUserDataTextField.setPreferredSize(new java.awt.Dimension(280, 25));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(MessageRetryExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageRetryExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(MessageRetryExitNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageRetryExitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(MessageRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageRetryIntervaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(MessageRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(MessageRetryCountSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(30, 30, 30))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageRetryCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageRetryCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageRetryIntervalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageRetryIntervaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageRetryExitNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageRetryExitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MessageRetryExitUserDataLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageRetryExitUserDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jScrollPane9.setViewportView(jPanel8);

        javax.swing.GroupLayout MessageRetryPanelLayout = new javax.swing.GroupLayout(MessageRetryPanel);
        MessageRetryPanel.setLayout(MessageRetryPanelLayout);
        MessageRetryPanelLayout.setHorizontalGroup(
            MessageRetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessageRetryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MessageRetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addGroup(MessageRetryPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 350, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane9)
        );
        MessageRetryPanelLayout.setVerticalGroup(
            MessageRetryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessageRetryPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );

        SetupDetailPanel.add(MessageRetryPanel, "card2");

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
                   MQChannelPropertyModel model = MQPCF.GetChannelProperties(queueManager, this.CopyFromTextField.getText());
                   propertyModel = model;
                   MQChannelPropertyModel createModel = propertyModel;
                   createModel.Name = this.ObjectNameTextField.getText();
                   createModel.ChannelDesc = "";
                   createOrChangeChannel(queueManager, createModel, true);
               } catch (MQDataException ex) {
                   JOptionPane.showMessageDialog(this, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                   Logger.getLogger(ChannelProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                   Close();
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   Logger.getLogger(ChannelProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                   Close();
               }
           }
           else{
               setModelFromUI(propertyModel);
               createOrChangeChannel(queueManager, propertyModel, true);              
           }
       }
       else{
            setModelFromUI(propertyModel);
            createOrChangeChannel(queueManager, propertyModel, false);
       }
    }//GEN-LAST:event_FinishButtonActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        showInitialPanel();
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        this.channelName = this.ObjectNameTextField.getText();
        showDetailPanel();
        loadModelAndSetupUI(this.CopyFromTextField.getText(),true);
    }//GEN-LAST:event_NextButtonActionPerformed

    private void SelectObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectObjectButtonActionPerformed
        showSelectObjectBrowser(new ChannelType[]{channelType}, CopyFromTextField);     
    }//GEN-LAST:event_SelectObjectButtonActionPerformed

    private void DetailListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_DetailListValueChanged
        setSelectedPanel();      
    }//GEN-LAST:event_DetailListValueChanged

    private void ShareInClusterValueChange(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ShareInClusterValueChange
        clusterRadiobuttonSelectChange();
    }//GEN-LAST:event_ShareInClusterValueChange

    private void SelectTransmissionQueueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectTransmissionQueueButtonActionPerformed
        showSelectObjectBrowser(new QueueType[]{QueueType.Transmit},TransmissionQueueTextField);
    }//GEN-LAST:event_SelectTransmissionQueueButtonActionPerformed
    
    //private methods    
    private void initCustomProperties(){
        
        if(isCreate){
            this.setTitle("Add " + channelType.toString() + " channel"  );
            this.FinishButton.setText("Create");
        }
        else{
            this.setTitle("Edit channel properties"  );
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
        this.ObjectNameTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_CHANNEL_NAME_LENGTH, objectNameRegex));
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
        this.DescriptionTextBox.setDocument(new JTextFieldLimit(MQConstants.MQ_CHANNEL_DESC_LENGTH, null));
        this.MCAUserIdTextField.setDocument(new JTextFieldLimit(MQConstants.MQ_MCA_NAME_LENGTH, null));
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
        if(name != null && !name.isEmpty() && name.length() > 0 && name.length() <= MQConstants.MQ_CHANNEL_NAME_LENGTH && copyObjectName != null && !copyObjectName.isEmpty()){
            //this.FinishButton.setEnabled(true);
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
        categoryPanels.put("MCA",this.MCAPanel);
        categoryPanels.put("Exits",this.ExitPanel);
        categoryPanels.put("Retry",this.RetryPanel);
        categoryPanels.put("Message retry",this.MessageRetryPanel);
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
                    MQChannelPropertyModel model = MQPCF.GetChannelProperties(queueManager, name);
                    propertyModel = model;
                    if(isCreateNewObject){
                        model.Name = channelName;
                        model.ChannelDesc = "";
                    }
                    if(model != null){
                        channelType = ConstantConverter.ConvertConstantToChannelType(model.Type);
                        setUIFromModel(model);
                        setDetailListItems(channelType);
                        objectLoadFinish();
                    }
                    else{
                        JOptionPane.showMessageDialog(parent, "Fail to load object properties", "Error", JOptionPane.ERROR_MESSAGE);                    
                        Close();
                    }
                } catch (MQDataException ex) {
                    JOptionPane.showMessageDialog(parent, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(ChannelProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(parent, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                    Logger.getLogger(ChannelProperitiesDialog.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void setUIFromModel(MQChannelPropertyModel model){
        this.ChannelNameTextBox.setText(model.Name);
        this.ChannelTypeTextBox.setText(ConstantConverter.ConvertConstantToChannelType(model.Type).toString());
        this.DescriptionTextBox.setText(model.ChannelDesc);
        if(model.TransportType != null){
            setComboBoxValue(TransmissionProtocolComboBox, model.TransportType);
        }
        else{
            this.TransmissionProtocolLabel.setVisible(false);
            this.TransmissionProtocolComboBox.setVisible(false);
        }
        if(model.ConnectionName != null){
            this.ConnectionNameTextField.setText(model.ConnectionName);
        }
        else{
            this.ConnectionNameLabel.setVisible(false);
            this.ConnectionNameTextField.setVisible(false);
        }
        if(model.XmitQName != null){
            this.TransmissionQueueTextField.setText(model.XmitQName);
        }
        else{
            this.TransmissionQueueLabel.setVisible(false);
            this.TransmissionQueueTextField.setVisible(false);
            this.SelectTransmissionQueueButton.setVisible(false);
        }
        if(model.LocalAddress != null){
            this.LocalCommunicationAddressTextField.setText(model.LocalAddress);
        }
        else{
            this.LocalCommunicationAddressLabel.setVisible(false);
            this.LocalCommunicationAddressTextField.setVisible(false);
        }
        if(model.HeartbeatInterval != null){
            this.HeartbearIntercalSpinner.setValue(model.HeartbeatInterval);
        }
        else{
            this.HeartbeatIntervalLabel.setVisible(false);
            this.HeartbearIntercalSpinner.setVisible(false);
        }
        if(model.MaxMsgLength != null){
            this.MaxMessageLengthSpinner.setValue(model.MaxMsgLength);
        }
        else{
            this.MaxMessageLengthLabel.setVisible(false);
            this.MaxMessageLengthSpinner.setVisible(false);
        }
        if(model.SeqNumberWrap != null){
            this.SequenceNumberWraplSpinner.setValue(model.SeqNumberWrap);
        }
        else{
            this.SequenceNumberWrapLabel.setVisible(false);
            this.SequenceNumberWraplSpinner.setVisible(false);
        }
        if(model.KeepAliveInterval != null){
            this.KeepAliveIntervalSpinner.setValue(model.KeepAliveInterval);
        }
        else{
            this.KeepAliveIntervalLabel.setVisible(false);
            this.KeepAliveIntervalSpinner.setVisible(false);
        }
        if(model.NonPersistentMsgSpeed != null){
            setComboBoxValue(NonPersistentMessageSpeedComboBox, model.NonPersistentMsgSpeed);
        }
        else{
            this.NonPersistentMessageSpeedLabel.setVisible(false);
            this.NonPersistentMessageSpeedComboBox.setVisible(false);
        }
        if(model.BatchSize != null){
            this.BatchSizeSpinner.setValue(model.BatchSize);
        }
        else{
            this.BatchSizeLabel.setVisible(false);
            this.BatchSizeSpinner.setVisible(false);
        }
        if(model.BatchInterval != null){
            this.BatchIntervaSpinner.setValue(model.BatchInterval);
        }
        else{
            this.BatchIntervalLabel.setVisible(false);
            this.BatchIntervaSpinner.setVisible(false);
        }
        if(model.DiscInterval != null){
            this.DisconnectIntervalSpinner.setValue(model.DiscInterval);
        }
        else{
            this.DisconnectIntervalLabel.setVisible(false);
            this.DisconnectIntervalSpinner.setVisible(false);
        }
        if(model.DataConversion != null){
            setComboBoxValue(this.DataConversionComboBox, model.DataConversion);
        }
        else{
            this.DataConversionLabel.setVisible(false);
            this.DataConversionComboBox.setVisible(false);
        }
        if(model.BatchHeartbeat != null){
            this.BatchheartBeatIntervallSpinner.setValue(model.BatchHeartbeat);
        }
        else{
            this.BatchheartBeatIntervalLabel.setVisible(false);
            this.BatchheartBeatIntervallSpinner.setVisible(false);
        }
        if(model.PropertyControl != null){
            setComboBoxValue(this.PriorityControlComboBox, model.PropertyControl);
        }
        else{
            this.PropertyControlLabel.setVisible(false);
            this.PriorityControlComboBox.setVisible(false);
        }
        if(model.BatchDataLimit != null){
            this.BatchDataLimitSpinner.setValue(model.BatchDataLimit);
        }
        else{
            this.BatchDataLimitLabel.setVisible(false);
            this.BatchDataLimitSpinner.setVisible(false);
        }
        if(model.UseDLQ != null){
            setComboBoxValue(this.UseDeadLetterQueueComboBox, model.UseDLQ);
        }
        else{
            this.UseDeadLetterQueueLabel.setVisible(false);
            this.UseDeadLetterQueueComboBox.setVisible(false);
        }
        if(model.PutAuthority != null){
            setComboBoxValue(this.PutAuthorityComboBox, model.PutAuthority);
        }
        else{
            this.PutAuthorityLabel.setVisible(false);
            this.PutAuthorityComboBox.setVisible(false);
        }
        if(model.SharingConversations != null){
            this.SharingConversationSpinner.setValue(model.SharingConversations);
        }
        else{
            this.SharingConversationLabel.setVisible(false);
            this.SharingConversationSpinner.setVisible(false);
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
        if(model.NetworkPriority != null){
            this.NetworkPrioritySpinner.setValue(model.NetworkPriority);
        }
        else{
            this.NetworkPriorityLabel.setVisible(false);
            this.NetworkPrioritySpinner.setVisible(false);
        }
        if(model.CLWLChannelRank != null){
            this.CLWLRankSpinner.setValue(model.CLWLChannelRank);
        }
        else{
            this.CLWLQueueRankLabel.setVisible(false);
            this.CLWLRankSpinner.setVisible(false);
        }
        if(model.CLWLChannelWeight != null){
            this.CLWLChanneWeightSpinner.setValue(model.CLWLChannelWeight);
        }
        else{
            this.CLWLQueueWeightLabel.setVisible(false);
            this.CLWLChanneWeightSpinner.setVisible(false);
        }
        if(model.CLWLChannelPriority != null){
            this.CLWLPrioritySpinner.setValue(model.CLWLChannelPriority);
        }
        else{
            this.CLWLChannelPriorityLabel.setVisible(false);
            this.CLWLPrioritySpinner.setVisible(false);
        }
        if(model.MCAType != null){
            setComboBoxValue(this.MCATypeComboBox, model.MCAType);
        }
        else{
            this.MCATypeLabel.setVisible(false);
            this.MCATypeComboBox.setVisible(false);
        }
        if(model.MCAUserIdentifier != null){
            this.MCAUserIdTextField.setText(model.MCAUserIdentifier);
        }
        else{
            this.MCAUserIdLabel.setVisible(false);
            this.MCAUserIdTextField.setVisible(false);
        }
        if(model.ReceiveExit != null){
            setTextFieldArrayValue(this.ReceiveExitTextField, model.ReceiveExit);
        }
        else{
            this.ReceiveExitLabel.setVisible(false);
            this.ReceiveExitTextField.setVisible(false);
        }
        if(model.ReceiveUserData != null){
            setTextFieldArrayValue(this.ReceiveExitUserDataTextField, model.ReceiveUserData);
        }
        else{
            this.ReceiveExitUserDataLabel.setVisible(false);
            this.ReceiveExitUserDataTextField.setVisible(false);
        }
        if(model.SendExit != null){
            setTextFieldArrayValue(this.SendExitField, model.SendExit);
        }
        else{
            this.SendExitLabel.setVisible(false);
            this.SendExitField.setVisible(false);
        }
        if(model.SendUserData != null){
            setTextFieldArrayValue(this.SendExitUserDataTextField, model.SendUserData);
        }
        else{
            this.SendExitUserDataLabel.setVisible(false);
            this.SendExitUserDataTextField.setVisible(false);
        }
        if(model.MsgExit != null){
            setTextFieldArrayValue(this.MessageExitTextField, model.MsgExit);
        }
        else{
            this.MessageExitLabel.setVisible(false);
            this.MessageExitTextField.setVisible(false);
        }
        if(model.MsgUserData != null){
            setTextFieldArrayValue(this.MessageExitUserDataTextField, model.MsgUserData);
        }
        else{
            this.MessageExitUserDataLabel.setVisible(false);
            this.MessageExitUserDataTextField.setVisible(false);
        }
        if(model.SecurityExit != null){
            this.SecurityExitTextField.setText(model.SecurityExit);
        }
        else{
            this.SecurityExitLabel.setVisible(false);
            this.SecurityExitTextField.setVisible(false);
        }
        if(model.SecurityUserData != null){
            this.SecurityExitUserDataTextField.setText(model.SecurityUserData);
        }
        else{
            this.SecurityExitUserDataLabel.setVisible(false);
            this.SecurityExitUserDataTextField.setVisible(false);
        }
        if(model.ShortRetryCount != null){
            this.ShortRetryCountSpinner.setValue(model.ShortRetryCount);
        }
        else{
            this.ShortRetryCountLabel.setVisible(false);
            this.ShortRetryCountSpinner.setVisible(false);
        }
        if(model.ShortRetryInterval != null){
            this.ShortRetryIntervalSpinner.setValue(model.ShortRetryInterval);
        }
        else{
            this.ShortRetryIntervalLabel.setVisible(false);
            this.ShortRetryIntervalSpinner.setVisible(false);
        }
        if(model.LongRetryCount != null){
            this.LongRetryCountSpinner.setValue(model.LongRetryCount);
        }
        else{
            this.LongRetryCountLabel.setVisible(false);
            this.LongRetryCountSpinner.setVisible(false);
        }
        if(model.LongRetryInterval != null){
            this.LongRetryIntervalSpinner.setValue(model.LongRetryInterval);
        }
        else{
            this.LongRetryIntervalLabel.setVisible(false);
            this.LongRetryIntervalSpinner.setVisible(false);
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
        if(model.ChannelMonitoring != null){
            setComboBoxValue(this.ChannelMonitoringComboBox, model.ChannelMonitoring);
        }
        else{
            this.ChannelMonitoringLabel.setVisible(false);
            this.ChannelMonitoringComboBox.setVisible(false);
        }
        if(model.ChannelStatistics != null){
            setComboBoxValue(this.ChannelStatisticComboBox, model.ChannelStatistics);
        }
        else{
            this.ChannelStatisticsLabel.setVisible(false);
            this.ChannelStatisticComboBox.setVisible(false);
        }
        if(model.MsgRetryCount != null){
            this.MessageRetryCountSpinner.setValue(model.MsgRetryCount);
        }
        else{
            this.MessageRetryCountLabel.setVisible(false);
            this.MessageRetryCountSpinner.setVisible(false);
        }
        if(model.MsgRetryInterval != null){
            this.MessageRetryIntervaSpinner.setValue(model.MsgRetryInterval);
        }
        else{
            this.MessageRetryIntervalLabel.setVisible(false);
            this.MessageRetryIntervaSpinner.setVisible(false);
        }
        if(model.MsgRetryExit != null){
            this.MessageRetryExitNameTextField.setText(model.MsgRetryExit);
        }
        else{
            this.MessageRetryExitNameLabel.setVisible(false);
            this.MessageRetryExitNameTextField.setVisible(false);
        }
        if(model.MsgRetryUserData != null){
            this.MessageRetryExitUserDataTextField.setText(model.MsgRetryUserData);
        }
        else{
            this.MessageRetryExitUserDataLabel.setVisible(false);
            this.MessageRetryExitUserDataTextField.setVisible(false);
        }
    }
    
    private void setModelFromUI(MQChannelPropertyModel model){
 
        model.Name = this.channelName;
        model.Type = ConstantConverter.ConvertChannelTypeToConstant(this.channelType);
        model.ChannelDesc = getTextFieldValue(this.DescriptionTextBox);
        model.TransportType = getComboBoxValue(this.TransmissionProtocolComboBox);
        model.ConnectionName = getTextFieldValue(this.ConnectionNameTextField);
        model.XmitQName = getTextFieldValue(this.TransmissionQueueTextField);
        model.LocalAddress = getTextFieldValue(this.LocalCommunicationAddressTextField);
        model.HeartbeatInterval = getSpinnerBoxValue(this.HeartbearIntercalSpinner);
        model.MaxMsgLength = getSpinnerBoxValue(this.MaxMessageLengthSpinner);
        model.SeqNumberWrap = getSpinnerBoxValue(this.SequenceNumberWraplSpinner);
        model.KeepAliveInterval = getSpinnerBoxValue(this.KeepAliveIntervalSpinner);
        model.NonPersistentMsgSpeed = getComboBoxValue(this.NonPersistentMessageSpeedComboBox);
        model.BatchSize = getSpinnerBoxValue(this.BatchSizeSpinner);
        model.BatchInterval = getSpinnerBoxValue(this.BatchIntervaSpinner);
        model.DiscInterval = getSpinnerBoxValue(this.DisconnectIntervalSpinner);
        model.DataConversion = getComboBoxValue(this.DataConversionComboBox);
        model.BatchHeartbeat = getSpinnerBoxValue(this.BatchheartBeatIntervallSpinner);
        model.PropertyControl = getComboBoxValue(this.PriorityControlComboBox);
        model.BatchDataLimit = getSpinnerBoxValue(this.BatchDataLimitSpinner);
        model.UseDLQ = getComboBoxValue(this.UseDeadLetterQueueComboBox);
        model.PutAuthority = getComboBoxValue(this.PutAuthorityComboBox);
        model.SharingConversations = getSpinnerBoxValue(this.SharingConversationSpinner);
        model.ClusterName = getTextFieldValue(this.ShareInClusterTextField);
        model.ClusterNamelist = getTextFieldValue(this.ShareInListClusterTextField);
        model.CLWLChannelRank = getSpinnerBoxValue(this.CLWLRankSpinner);
        model.CLWLChannelPriority = getSpinnerBoxValue(this.CLWLPrioritySpinner);
        model.NetworkPriority = getSpinnerBoxValue(this.NetworkPrioritySpinner);
        model.CLWLChannelWeight = getSpinnerBoxValue(this.CLWLChanneWeightSpinner);
        model.MCAType = getComboBoxValue(this.MCATypeComboBox);
        model.MCAUserIdentifier = getTextFieldValue(this.MCAUserIdTextField);
        model.ReceiveExit = getTextFieldArrayValue(this.ReceiveExitTextField);
        model.ReceiveUserData = getTextFieldArrayValue(this.ReceiveExitUserDataTextField);
        model.SendExit = getTextFieldArrayValue(this.SendExitField);
        model.SendUserData = getTextFieldArrayValue(this.SendExitUserDataTextField);
        model.MsgExit = getTextFieldArrayValue(this.MessageExitTextField);
        model.MsgUserData = getTextFieldArrayValue(this.MessageExitUserDataTextField);
        model.SecurityExit = getTextFieldValue(this.SecurityExitTextField);
        model.SecurityUserData = getTextFieldValue(this.SecurityExitUserDataTextField);
        model.ShortRetryCount = getSpinnerBoxValue(this.ShortRetryCountSpinner);
        model.ShortRetryInterval = getSpinnerBoxValue(this.ShortRetryIntervalSpinner);
        model.LongRetryCount = getSpinnerBoxValue(this.LongRetryCountSpinner);
        model.LongRetryInterval = getSpinnerBoxValue(this.LongRetryIntervalSpinner);
        model.ChannelMonitoring = getComboBoxValue(this.ChannelMonitoringComboBox);
        model.ChannelStatistics = getComboBoxValue(this.ChannelStatisticComboBox);
        model.MsgRetryCount = getSpinnerBoxValue(this.MessageRetryCountSpinner);
        model.MsgRetryInterval = getSpinnerBoxValue(this.MessageRetryIntervaSpinner);
        model.MsgRetryExit = getTextFieldValue(this.MessageRetryExitNameTextField);
        model.MsgRetryUserData = getTextFieldValue(this.MessageRetryExitUserDataTextField);
        

    }
    
    private void initComboBoxValue(){
        this.TransmissionProtocolComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("LU 6.2", MQConstants.MQXPT_LU62), new ComboBoxItemModel("TCP", MQConstants.MQXPT_TCP), 
            new ComboBoxItemModel("NetBIOS", MQConstants.MQXPT_NETBIOS),new ComboBoxItemModel("SPX", MQConstants.MQXPT_SPX)
        }));
        this.NonPersistentMessageSpeedComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Normal", MQConstants.MQNPMS_NORMAL), new ComboBoxItemModel("Fast", MQConstants.MQNPMS_FAST), 
        }));
        this.DataConversionComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("No conversion", MQConstants.MQCDC_NO_SENDER_CONVERSION), new ComboBoxItemModel("Conversion", MQConstants.MQCDC_SENDER_CONVERSION), 
        }));
        this.PriorityControlComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Compatibility", MQConstants.MQPROP_COMPATIBILITY), new ComboBoxItemModel("None", MQConstants.MQPROP_NONE), 
            new ComboBoxItemModel("All", MQConstants.MQPROP_ALL)
        }));
        this.UseDeadLetterQueueComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("No", MQConstants.MQUSEDLQ_NO), new ComboBoxItemModel("Yes", MQConstants.MQUSEDLQ_YES), 
        }));
        this.PutAuthorityComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Default", MQConstants.MQPA_DEFAULT), new ComboBoxItemModel("Context", MQConstants.MQPA_CONTEXT), 
            new ComboBoxItemModel("Alternate or MCA", MQConstants.MQPA_ALTERNATE_OR_MCA), new ComboBoxItemModel("MCA", MQConstants.MQPA_ONLY_MCA),
        }));
        this.MCATypeComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Process", MQConstants.MQMCAT_PROCESS), new ComboBoxItemModel("Thread", MQConstants.MQMCAT_THREAD), 
        }));
        this.ChannelMonitoringComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Off", MQConstants.MQMON_OFF), new ComboBoxItemModel("Queue manager", MQConstants.MQMON_Q_MGR), 
            new ComboBoxItemModel("Low", MQConstants.MQMON_LOW), new ComboBoxItemModel("Medium", MQConstants.MQMON_MEDIUM),
            new ComboBoxItemModel("Hight", MQConstants.MQMON_HIGH),
        }));
        this.ChannelStatisticComboBox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Off", MQConstants.MQMON_OFF), new ComboBoxItemModel("Queue manager", MQConstants.MQMON_Q_MGR), 
            new ComboBoxItemModel("Low", MQConstants.MQMON_LOW), new ComboBoxItemModel("Medium", MQConstants.MQMON_MEDIUM),
            new ComboBoxItemModel("Hight", MQConstants.MQMON_HIGH),
        }));
    }
        
    private void setDetailListItems(ChannelType channelType){
        switch(channelType){
            case Sender :
            case Server:
            case Cluster_Sender:
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "MCA", "Exits", "Retry", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;
            case Receiver :
            case Requester :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "MCA", "Exits", "Message retry", "Statistics"};
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;   
            case Cluster_Receiver :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "MCA", "Exits", "Retry", "Message retry", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;
            case Server_Connection :
                DetailList.setModel(new javax.swing.AbstractListModel() {
                    String[] strings = { "General", "Extended", "MCA", "Exits", "Statistics" };
                    public int getSize() { return strings.length; }
                    public Object getElementAt(int i) { return strings[i]; }
                });  
                break;
                       
        }
        DetailList.setSelectedIndex(0);


    }
    
    private String getDefaultCopyFromQueueName(ChannelType type){
        switch (type){
            case Cluster_Receiver:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromClusterReceiver)? this.defaultCopyFromClusterReceiver : null;
            case Cluster_Sender:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromClusterSender)? this.defaultCopyFromClusterSender : null;
            case Receiver:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromReceiver)? this.defaultCopyFromReceiver : null;
            case Requester:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromRequestor)? this.defaultCopyFromRequestor : null;
            case Sender:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromSender)? this.defaultCopyFromSender : null;
            case Server:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromServer)? this.defaultCopyFromServer : null;
            case Server_Connection:
                return MQPCF.CheckChannelExist(queueManager, defaultCopyFromServerConnection)? this.defaultCopyFromServerConnection : null;
        }
        return null;
    }
    
    private void createOrChangeChannel(MQQueueManager queueManager, MQChannelPropertyModel model, boolean isCreate){
        MQCommandResult result = isCreate == true ? MQPCF.CreateChannel(queueManager, model) : MQPCF.UpdateChannelProperties(queueManager, model) ;
        if(result.QuerySuccess){
            JOptionPane.showMessageDialog(this, isCreate == true ? "New channel has been created" :  "Channel properties have been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
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
    private javax.swing.JLabel BatchDataLimitLabel;
    private javax.swing.JSpinner BatchDataLimitSpinner;
    private javax.swing.JSpinner BatchIntervaSpinner;
    private javax.swing.JLabel BatchIntervalLabel;
    private javax.swing.JLabel BatchSizeLabel;
    private javax.swing.JSpinner BatchSizeSpinner;
    private javax.swing.JLabel BatchheartBeatIntervalLabel;
    private javax.swing.JSpinner BatchheartBeatIntervallSpinner;
    private javax.swing.JSpinner CLWLChanneWeightSpinner;
    private javax.swing.JLabel CLWLChannelPriorityLabel;
    private javax.swing.JSpinner CLWLPrioritySpinner;
    private javax.swing.JLabel CLWLQueueRankLabel;
    private javax.swing.JLabel CLWLQueueWeightLabel;
    private javax.swing.JSpinner CLWLRankSpinner;
    private javax.swing.JButton CancelButton;
    private javax.swing.JComboBox ChannelMonitoringComboBox;
    private javax.swing.JLabel ChannelMonitoringLabel;
    private javax.swing.JTextField ChannelNameTextBox;
    private javax.swing.JComboBox ChannelStatisticComboBox;
    private javax.swing.JLabel ChannelStatisticsLabel;
    private javax.swing.JTextField ChannelTypeTextBox;
    private javax.swing.JPanel ClusterPanel;
    private javax.swing.JLabel ConnectionNameLabel;
    private javax.swing.JTextField ConnectionNameTextField;
    private javax.swing.JPanel ContentPanel;
    private javax.swing.JTextField CopyFromTextField;
    private javax.swing.JComboBox DataConversionComboBox;
    private javax.swing.JLabel DataConversionLabel;
    private javax.swing.JTextField DescriptionTextBox;
    private javax.swing.JList DetailList;
    private javax.swing.JLabel DisconnectIntervalLabel;
    private javax.swing.JSpinner DisconnectIntervalSpinner;
    private javax.swing.JPanel ExitPanel;
    private javax.swing.JPanel ExtendedPanel;
    private javax.swing.JButton FinishButton;
    private javax.swing.JPanel GeneralPanel;
    private javax.swing.JSpinner HeartbearIntercalSpinner;
    private javax.swing.JLabel HeartbeatIntervalLabel;
    private javax.swing.JPanel InitialPanel;
    private javax.swing.JLabel KeepAliveIntervalLabel;
    private javax.swing.JSpinner KeepAliveIntervalSpinner;
    private javax.swing.JLabel LocalCommunicationAddressLabel;
    private javax.swing.JTextField LocalCommunicationAddressTextField;
    private javax.swing.JLabel LongRetryCountLabel;
    private javax.swing.JSpinner LongRetryCountSpinner;
    private javax.swing.JLabel LongRetryIntervalLabel;
    private javax.swing.JSpinner LongRetryIntervalSpinner;
    private javax.swing.JPanel MCAPanel;
    private javax.swing.JComboBox MCATypeComboBox;
    private javax.swing.JLabel MCATypeLabel;
    private javax.swing.JLabel MCAUserIdLabel;
    private javax.swing.JTextField MCAUserIdTextField;
    private javax.swing.JLabel MaxMessageLengthLabel;
    private javax.swing.JSpinner MaxMessageLengthSpinner;
    private javax.swing.JLabel MessageExitLabel;
    private javax.swing.JTextField MessageExitTextField;
    private javax.swing.JLabel MessageExitUserDataLabel;
    private javax.swing.JTextField MessageExitUserDataTextField;
    private javax.swing.JLabel MessageRetryCountLabel;
    private javax.swing.JSpinner MessageRetryCountSpinner;
    private javax.swing.JLabel MessageRetryExitNameLabel;
    private javax.swing.JTextField MessageRetryExitNameTextField;
    private javax.swing.JLabel MessageRetryExitUserDataLabel;
    private javax.swing.JTextField MessageRetryExitUserDataTextField;
    private javax.swing.JSpinner MessageRetryIntervaSpinner;
    private javax.swing.JLabel MessageRetryIntervalLabel;
    private javax.swing.JPanel MessageRetryPanel;
    private javax.swing.JLabel NetworkPriorityLabel;
    private javax.swing.JSpinner NetworkPrioritySpinner;
    private javax.swing.JButton NextButton;
    private javax.swing.JComboBox NonPersistentMessageSpeedComboBox;
    private javax.swing.JLabel NonPersistentMessageSpeedLabel;
    private javax.swing.JRadioButton NotShareInClusterRadioButton;
    private javax.swing.JTextField ObjectNameTextField;
    private javax.swing.JComboBox PriorityControlComboBox;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JLabel PropertyControlLabel;
    private javax.swing.JComboBox PutAuthorityComboBox;
    private javax.swing.JLabel PutAuthorityLabel;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JLabel ReceiveExitLabel;
    private javax.swing.JTextField ReceiveExitTextField;
    private javax.swing.JLabel ReceiveExitUserDataLabel;
    private javax.swing.JTextField ReceiveExitUserDataTextField;
    private javax.swing.JPanel RetryPanel;
    private javax.swing.JLabel SecurityExitLabel;
    private javax.swing.JTextField SecurityExitTextField;
    private javax.swing.JLabel SecurityExitUserDataLabel;
    private javax.swing.JTextField SecurityExitUserDataTextField;
    private javax.swing.JButton SelectObjectButton;
    private javax.swing.JButton SelectTransmissionQueueButton;
    private javax.swing.JTextField SendExitField;
    private javax.swing.JLabel SendExitLabel;
    private javax.swing.JLabel SendExitUserDataLabel;
    private javax.swing.JTextField SendExitUserDataTextField;
    private javax.swing.JLabel SequenceNumberWrapLabel;
    private javax.swing.JSpinner SequenceNumberWraplSpinner;
    private javax.swing.JPanel SetupDetailPanel;
    private javax.swing.JPanel SetupPanel;
    private javax.swing.JRadioButton ShareInClusterRadioButton;
    private javax.swing.JTextField ShareInClusterTextField;
    private javax.swing.JPanel ShareInClustersPanel;
    private javax.swing.JRadioButton ShareInListClusterRadioButton;
    private javax.swing.JTextField ShareInListClusterTextField;
    private javax.swing.JLabel SharingConversationLabel;
    private javax.swing.JSpinner SharingConversationSpinner;
    private javax.swing.ButtonGroup SharingInClustersNuttonGroup;
    private javax.swing.JLabel ShortRetryCountLabel;
    private javax.swing.JSpinner ShortRetryCountSpinner;
    private javax.swing.JLabel ShortRetryIntervalLabel;
    private javax.swing.JSpinner ShortRetryIntervalSpinner;
    private javax.swing.JPanel StatisticsPanel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JComboBox TransmissionProtocolComboBox;
    private javax.swing.JLabel TransmissionProtocolLabel;
    private javax.swing.JLabel TransmissionQueueLabel;
    private javax.swing.JTextField TransmissionQueueTextField;
    private javax.swing.JComboBox UseDeadLetterQueueComboBox;
    private javax.swing.JLabel UseDeadLetterQueueLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    // End of variables declaration//GEN-END:variables
}
