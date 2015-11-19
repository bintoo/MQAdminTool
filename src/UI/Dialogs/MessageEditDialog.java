/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.LogType;
import MQApi.Logs.LogWriter;
import MQApi.Models.MQMessageIdModel;
import MQApi.Utilities.MQUtility;
import UI.Helpers.XMLHelper;
import UI.Models.ComboBoxItemModel;
import com.ibm.mq.MQException;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDLH;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.MQHeaderList;
import com.ibm.mq.headers.MQRFH2;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.EditorKit;
import javax.swing.text.NumberFormatter;
//import xmleditorkit.XMLEditorKit;
import org.bounce.text.xml.XMLEditorKit;
import org.bounce.text.xml.XMLStyleConstants;

/**
 *
 * @author jzhou
 */
public class MessageEditDialog extends ObjectPropertiesDialogBase {
    
    MQMessageIdModel messageId;
    String queueName;
    MQMessage message;
    String content = null;
    byte[] byteContent;
    XMLEditorKit xmlEditorkit = new XMLEditorKit();
    EditorKit textEditorKit;
    Component component = this;
    boolean isNew;

    public MessageEditDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String queueName, MQMessageIdModel messageId) {
        super(parent, modal,  queueManager,null );
        this.messageId = messageId;
        this.queueName = queueName;
        this.isNew = false;
        initComponents();
        initCustomProperties();
        showMessageDetail();
    }
    
    public MessageEditDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String queueName) {
        super(parent, modal,  queueManager,null );
        this.messageId = null;
        this.queueName = queueName;
        this.isNew = true;
        initComponents();
        initCustomProperties();
        showMessageDetail();
    }

    private void initCustomProperties(){
        this.setTitle("Message detail");
        this.setIconImage(iconManager.BrowseMessageIcon().getImage());   
        String queueManagerName = "";
        try {
            queueManagerName = queueManager.getName();
        } catch (MQException ex) {
            //LogWriter.WriteToLog(ex.getMessage(), LogType.Error);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        initComboBoxValue();
        xmlEditorkit.setAutoIndentation(true);
        xmlEditorkit.setTagCompletion(true);
        xmlEditorkit.setStyle(XMLStyleConstants.ELEMENT_NAME, Color.BLUE, Font.PLAIN);
        xmlEditorkit.setStyle(XMLStyleConstants.ATTRIBUTE_NAME, Color.MAGENTA, Font.BOLD);
        this.textEditorKit = ContentEditorPane.getEditorKit();
        this.ContentEditorPane.setEditorKitForContentType("Text", textEditorKit);
        this.ContentEditorPane.setEditorKitForContentType("XML", xmlEditorkit);
        this.QueueManagerLabel.setText(queueManagerName);
        this.QueueNameLabel.setText(queueName);      
        this.ProgressBar.setVisible(false);
        this.StatusLabel.setVisible(false);
        this.OriginalLengthSpinner.setEnabled(false);
        this.BackoutSpinner.setEnabled(false);
        if(isNew){
            this.UpdateButton.setText("Put");
            this.KeepPositionCheckBox.setVisible(false);
            this.MessagePanel.setEnabledAt(1, false);
        }
        else{
            this.UpdateButton.setText("Update");
            this.KeepPositionCheckBox.setVisible(true);
        }
        this.MessagePanel.setEnabledAt(2, false);
        
    }
    
    private void showMessageDetail(){
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    if(isNew == false){
                        MQMessage selectedMessage = MQUtility.GetMessage(queueManager, queueName, messageId, false);
                        message = selectedMessage;
                    }
                    else{
                        message = new MQMessage();
                    }
                    processMessage();
                } catch (MQException ex) {
                    Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(ParentJFrame, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                    FireActionEvent();
                    Close();
                }
                finishLoading(false);
            }
        });
        StatusLabel.setText("Loading...");
        startLoading();
        thread.start();
    }
    
    private void processMessage(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                loadMessage(message);
            }
        });
        thread.start();
        

    }
    
    private void loadMessage(MQMessage message){       
        loadMessageMQMD(message);
        MQDLH dlh = MQUtility.getDLH(message);
        if(dlh != null){
            this.MessagePanel.setEnabledAt(2, true);
            loadMessageDLH(dlh);
        }
        content = MQUtility.GetMessageStringContent(message, null); 
        if(XMLHelper.IsXML(content)){
            content = XMLHelper.XMLStringFormat(content);
            ContentTypeSelectComboBox.setSelectedIndex(1);
            this.ContentEditorPane.setContentType("XML");
        }
        this.ContentEditorPane.setText(content); 
    }
    

    
    private void loadMessageDLH(MQDLH dlh){
        this.DLHDestintionTextField.setText(dlh.getDestQName());
        this.DLHPutDateTextField.setText(dlh.getPutDate());
        this.DLHPutNameTextField.setText(dlh.getPutApplName());
        this.DLHPutTimeTextField.setText(dlh.getPutTime());
        this.DLHPutTypeTextField.setText(Integer.toString(dlh.getPutApplType()));
        this.DLHQmgrTextField.setText(dlh.getDestQMgrName());
        this.DLHReasonTextField.setText(Integer.toString(dlh.getReason()));
        this.DLHFormatTextField.setText(dlh.getFormat());
    }
    
    private void loadMessageMQMD(MQMessage message){
        this.MessageIdTextField.setText(MQUtility.BytesToHex(message.messageId));
        this.CorrectionIdTextField.setText(MQUtility.BytesToHex(message.correlationId));
        this.GroupIdTextField.setText(MQUtility.BytesToHex(message.groupId));
        this.AccountTokenTextField.setText(MQUtility.BytesToHex(message.accountingToken));
        this.ApplicationIdDataTextField.setText(message.applicationIdData);
        this.ApplicationOriginDataTextField.setText(message.applicationOriginData);
        this.BackoutSpinner.setValue(message.backoutCount);
        setComboBoxValue(this.CharacterSetCombobox,message.characterSet);
        this.ExpirySpinner.setValue(message.expiry);
        setComboBoxValue(this.FeedbackCombobox, message.feedback);
        this.FormatTextField.setText(message.format);
        setComboBoxValue(this.MessageFlagCombobox, message.messageFlags);
        this.MessageSequenceNumberSpinner.setValue(message.messageSequenceNumber);
        this.OffsetSpinner.setValue(message.offset);
        this.OriginalLengthSpinner.setValue(message.originalLength);
        setComboBoxValue(this.PersistenceCombobox, message.persistence);
        this.PrioritySpinner.setValue(message.priority);
        this.PutApplicationNameTextField.setText(message.putApplicationName);
        this.PutApplicationTypeSpinner.setValue(message.putApplicationType);
        this.ReplyToQueueManagerNameTextField.setText(message.replyToQueueManagerName);
        this.ReplyToQueueNameTextField.setText(message.replyToQueueName);
        this.UserIdTextField.setText(message.userId);
    }
 
    private void setMessageMQMD(MQMessage message){
        message.applicationIdData = this.ApplicationIdDataTextField.getText().trim();
        message.applicationOriginData = this.ApplicationOriginDataTextField.getText().trim();
        message.backoutCount = Integer.parseInt(this.BackoutSpinner.getValue().toString());
        message.characterSet = getComboBoxValue(this.CharacterSetCombobox);
        message.expiry = Integer.parseInt(this.ExpirySpinner.getValue().toString());
        message.feedback = getComboBoxValue(this.FeedbackCombobox);
        message.format = this.FormatTextField.getText().trim();
        message.messageFlags = getComboBoxValue(this.MessageFlagCombobox);
        message.messageSequenceNumber = Integer.parseInt(this.MessageSequenceNumberSpinner.getValue().toString());
        message.offset = Integer.parseInt(this.OffsetSpinner.getValue().toString());
        message.originalLength = Integer.parseInt(this.OriginalLengthSpinner.getValue().toString());
        message.persistence = getComboBoxValue(this.PersistenceCombobox);
        message.priority = Integer.parseInt(this.PrioritySpinner.getValue().toString());
        message.putApplicationName = this.PutApplicationNameTextField.getText().trim();
        message.putApplicationType = Integer.parseInt(this.PutApplicationTypeSpinner.getValue().toString());
        message.replyToQueueManagerName = this.ReplyToQueueManagerNameTextField.getText().trim();
        message.replyToQueueName = this.ReplyToQueueNameTextField.getText().trim();
        message.userId = this.UserIdTextField.getText().trim();
    }
    
    private void updateMessage(final MQMessage message){
        
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    updateMessageObject();
                    if(isNew == false){
                        if(KeepPositionCheckBox.isSelected()){
                            MQUtility.UpdateMessageToSamePosition(queueManager, queueName, message);
                        }
                        else{
                            MQUtility.UpdateMessage(queueManager, queueName, message);
                        }
                    }
                    else{
                        MQUtility.PutMessage(queueManager, queueName, message);
                    }
                    finishLoading(true);
                    JOptionPane.showMessageDialog(component, isNew ? "Message has been added":"Message has been updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (MQException ex) {
                    finishLoading(true);
                    Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(component, MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason()), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    finishLoading(true);
                    Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(component, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    finishLoading(true);
                    Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(component, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });    
        this.StatusLabel.setText("Updating...");
        startLoading();
        thread.start();
    }
    
    private void updateMessageObject(){
        try {
            if(isNew == false){
                message.clearMessage();
                setMessageMQMD(message);
            }
            message.writeString(ContentEditorPane.getText());
        } catch (IOException ex) {
            Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void startLoading(){
        this.UpdateButton.setEnabled(false);
        this.ProgressBar.setVisible(true);        
        this.StatusLabel.setVisible(true);
        this.KeepPositionCheckBox.setEnabled(false);
        this.MessagePanel.setEnabled(false);
        this.ContentEditorPane.setEnabled(false);
        this.ContentTypeSelectComboBox.setEnabled(false);
    }
    
    private void finishLoading(boolean fireEvent){
        this.UpdateButton.setEnabled(true);
        this.ProgressBar.setVisible(false);
        this.StatusLabel.setVisible(false);
        this.KeepPositionCheckBox.setEnabled(true);
        this.MessagePanel.setEnabled(true);
        this.ContentEditorPane.setEnabled(true);
        this.ContentTypeSelectComboBox.setEnabled(true);
        if(isNew){
            this.ContentEditorPane.setText("");
            message = new MQMessage();
        }
        if(fireEvent){
            FireActionEvent();
        }
    }
    
    private void initComboBoxValue(){
        this.CharacterSetCombobox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Queue manager", MQConstants.MQCCSI_Q_MGR),           
            new ComboBoxItemModel("Unicode", 1200), new ComboBoxItemModel("UTF-8", 1208 ),
            new ComboBoxItemModel("The ISO standard ASCII codeset", 819), 
            new ComboBoxItemModel("Commonly used ASCII codeset ", 850),  new ComboBoxItemModel("The American EBCDIC codeset", 37),
        }));
        this.FeedbackCombobox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("None", MQConstants.MQFB_NONE), new ComboBoxItemModel("Expiration", MQConstants.MQFB_EXPIRATION),
            new ComboBoxItemModel("COA", MQConstants.MQFB_COA), new ComboBoxItemModel("COD", MQConstants.MQFB_COD),
            new ComboBoxItemModel("Quit", MQConstants.MQFB_QUIT), new ComboBoxItemModel("System first", MQConstants.MQFB_SYSTEM_FIRST ),
            new ComboBoxItemModel("Application can not be started", MQConstants.MQFB_APPL_CANNOT_BE_STARTED), new ComboBoxItemModel("TM error", MQConstants.MQFB_TM_ERROR),
            new ComboBoxItemModel("Application type error", MQConstants.MQFB_APPL_TYPE_ERROR), new ComboBoxItemModel("Stopped by message exit", MQConstants.MQFB_STOPPED_BY_MSG_EXIT),
            new ComboBoxItemModel("Transmit queue error", MQConstants.MQFB_XMIT_Q_MSG_ERROR), new ComboBoxItemModel("System last", MQConstants.MQFB_SYSTEM_LAST),
            new ComboBoxItemModel("Activity", MQConstants.MQFB_ACTIVITY), new ComboBoxItemModel("Max activities", MQConstants.MQFB_MAX_ACTIVITIES),
            new ComboBoxItemModel("Not forwarded", MQConstants.MQFB_NOT_FORWARDED), new ComboBoxItemModel("Not delivered", MQConstants.MQFB_NOT_DELIVERED),
            new ComboBoxItemModel("Unsupported forwarding", MQConstants.MQFB_UNSUPPORTED_FORWARDING), new ComboBoxItemModel("Unsupported delivery", MQConstants.MQFB_UNSUPPORTED_DELIVERY),
        }));
        this.MessageFlagCombobox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("None", MQConstants.MQMF_NONE), new ComboBoxItemModel("Segmentation inhibited", MQConstants.MQMF_SEGMENTATION_INHIBITED),
            new ComboBoxItemModel("Segmentation allowed", MQConstants.MQMF_SEGMENTATION_ALLOWED), new ComboBoxItemModel("Segment", MQConstants.MQMF_SEGMENT),
            new ComboBoxItemModel("Last segment", MQConstants.MQMF_LAST_SEGMENT | MQConstants.MQMF_SEGMENT), new ComboBoxItemModel("Message in group", MQConstants.MQMF_MSG_IN_GROUP ),
            new ComboBoxItemModel("Last message in group", MQConstants.MQMF_LAST_MSG_IN_GROUP | MQConstants.MQMF_MSG_IN_GROUP )
        }));
        this.PersistenceCombobox.setModel(new DefaultComboBoxModel(new ComboBoxItemModel[]{
            new ComboBoxItemModel("Persistent", MQConstants.MQPER_PERSISTENT), new ComboBoxItemModel("Not persistent", MQConstants.MQPER_NOT_PERSISTENT),
            new ComboBoxItemModel("Queue manager", MQConstants.MQPER_PERSISTENCE_AS_Q_DEF)
        }));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ContentDisplayTypeGroup = new javax.swing.ButtonGroup();
        TitlePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        QueueManagerLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        QueueNameLabel = new javax.swing.JLabel();
        UpdateButton = new javax.swing.JButton();
        Closebutton = new javax.swing.JButton();
        MessagePanel = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        ContentEditorToolbar = new javax.swing.JToolBar();
        ContentTypeSelectComboBox = new javax.swing.JComboBox();
        ContentEditorScrollPane = new javax.swing.JScrollPane();
        ContentEditorPane = new javax.swing.JEditorPane();
        MQMDScrollPanel = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        MessageIdTextField = new javax.swing.JTextField();
        CorrectionIdTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        GroupIdTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        AccountTokenTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ApplicationIdDataTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        ApplicationOriginDataTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        FormatTextField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        BackoutSpinner = new javax.swing.JSpinner();
        CharacterSetCombobox = new javax.swing.JComboBox();
        ExpirySpinner = new javax.swing.JSpinner();
        FeedbackCombobox = new javax.swing.JComboBox();
        MessageFlagCombobox = new javax.swing.JComboBox();
        MessageSequenceNumberSpinner = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        OffsetSpinner = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        OriginalLengthSpinner = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        PersistenceCombobox = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        PrioritySpinner = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        PutApplicationNameTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        PutApplicationTypeSpinner = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        ReplyToQueueManagerNameTextField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        ReplyToQueueNameTextField = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        UserIdTextField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        DLHReasonTextField = new javax.swing.JTextField();
        DLHDestintionTextField = new javax.swing.JTextField();
        DLHQmgrTextField = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        DLHFormatTextField = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        DLHPutTimeTextField = new javax.swing.JTextField();
        DLHPutDateTextField = new javax.swing.JTextField();
        DLHPutTypeTextField = new javax.swing.JTextField();
        DLHPutNameTextField = new javax.swing.JTextField();
        ProgressBar = new javax.swing.JProgressBar();
        StatusLabel = new javax.swing.JLabel();
        KeepPositionCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(680, 520));
        setPreferredSize(new java.awt.Dimension(680, 520));

        TitlePanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Queue manager :");
        jLabel1.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueManagerLabel.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel2.setText("Queue name:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 25));

        QueueNameLabel.setPreferredSize(new java.awt.Dimension(380, 25));

        javax.swing.GroupLayout TitlePanelLayout = new javax.swing.GroupLayout(TitlePanel);
        TitlePanel.setLayout(TitlePanelLayout);
        TitlePanelLayout.setHorizontalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(TitlePanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(QueueNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TitlePanelLayout.setVerticalGroup(
            TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TitlePanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueManagerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TitlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QueueNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        UpdateButton.setText("Update");
        UpdateButton.setPreferredSize(new java.awt.Dimension(100, 25));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        Closebutton.setText("Close");
        Closebutton.setPreferredSize(new java.awt.Dimension(100, 25));
        Closebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClosebuttonActionPerformed(evt);
            }
        });

        ContentEditorToolbar.setFloatable(false);
        ContentEditorToolbar.setRollover(true);
        ContentEditorToolbar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        ContentTypeSelectComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Text", "XML" }));
        ContentTypeSelectComboBox.setMaximumSize(new java.awt.Dimension(70, 20));
        ContentTypeSelectComboBox.setMinimumSize(new java.awt.Dimension(70, 20));
        ContentTypeSelectComboBox.setPreferredSize(new java.awt.Dimension(70, 25));
        ContentTypeSelectComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ContentTypeSelectComboBoxItemStateChanged(evt);
            }
        });
        ContentEditorToolbar.add(ContentTypeSelectComboBox);

        ContentEditorScrollPane.setViewportView(ContentEditorPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ContentEditorToolbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ContentEditorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ContentEditorToolbar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ContentEditorScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
        );

        MessagePanel.addTab("Data", jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(610, 705));

        jLabel3.setText("Message id:");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel4.setText("Correlation id:");
        jLabel4.setPreferredSize(new java.awt.Dimension(150, 25));

        MessageIdTextField.setEditable(false);
        MessageIdTextField.setFocusable(false);
        MessageIdTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        CorrectionIdTextField.setEditable(false);
        CorrectionIdTextField.setFocusable(false);
        CorrectionIdTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel5.setText("Group id:");
        jLabel5.setPreferredSize(new java.awt.Dimension(150, 25));

        GroupIdTextField.setEditable(false);
        GroupIdTextField.setFocusable(false);
        GroupIdTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel6.setText("Account token:");
        jLabel6.setPreferredSize(new java.awt.Dimension(150, 25));

        AccountTokenTextField.setEditable(false);
        AccountTokenTextField.setFocusable(false);
        AccountTokenTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel7.setText("Application id data:");
        jLabel7.setPreferredSize(new java.awt.Dimension(150, 25));

        ApplicationIdDataTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel8.setText("Application originl data:");
        jLabel8.setPreferredSize(new java.awt.Dimension(150, 25));

        ApplicationOriginDataTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel9.setText("Backout count:");
        jLabel9.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel10.setText("Character set:");
        jLabel10.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel11.setText("Expiry:");
        jLabel11.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel12.setText("Feedback:");
        jLabel12.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel13.setText("Format");
        jLabel13.setPreferredSize(new java.awt.Dimension(150, 25));

        FormatTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel14.setText("Message flag:");
        jLabel14.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel15.setText("Msg sequence number:");
        jLabel15.setPreferredSize(new java.awt.Dimension(150, 25));

        BackoutSpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.BackoutSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.BackoutSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.BackoutSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        CharacterSetCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CharacterSetCombobox.setPreferredSize(new java.awt.Dimension(380, 25));

        ExpirySpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.ExpirySpinner.setModel(new SpinnerNumberModel(-1, -1, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.ExpirySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.ExpirySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        FeedbackCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FeedbackCombobox.setPreferredSize(new java.awt.Dimension(380, 25));

        MessageFlagCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        MessageFlagCombobox.setPreferredSize(new java.awt.Dimension(380, 25));

        MessageSequenceNumberSpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.MessageSequenceNumberSpinner.setModel(new SpinnerNumberModel(1, 1, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.MessageSequenceNumberSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.MessageSequenceNumberSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel16.setText("Offset:");
        jLabel16.setPreferredSize(new java.awt.Dimension(150, 25));

        OffsetSpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.OffsetSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.OffsetSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.OffsetSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel17.setText("Original length:");
        jLabel17.setPreferredSize(new java.awt.Dimension(150, 25));

        OriginalLengthSpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.OriginalLengthSpinner.setModel(new SpinnerNumberModel(0, 0, 999999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.OriginalLengthSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.OriginalLengthSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel18.setText("Persistence:");
        jLabel18.setPreferredSize(new java.awt.Dimension(150, 25));

        PersistenceCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PersistenceCombobox.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel19.setText("Priority:");
        jLabel19.setPreferredSize(new java.awt.Dimension(150, 25));

        PrioritySpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.PrioritySpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.PrioritySpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.PrioritySpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel21.setText("Put application name:");
        jLabel21.setPreferredSize(new java.awt.Dimension(150, 25));

        PutApplicationNameTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel20.setText("Put application type:");
        jLabel20.setPreferredSize(new java.awt.Dimension(150, 25));

        PutApplicationTypeSpinner.setPreferredSize(new java.awt.Dimension(380, 25));
        this.PutApplicationTypeSpinner.setModel(new SpinnerNumberModel(0, 0, 99999999, 1));
        ((NumberFormatter)((JSpinner.NumberEditor)this.PutApplicationTypeSpinner.getEditor()).getTextField().getFormatter()).setAllowsInvalid(false);
        ((JSpinner.NumberEditor)this.PutApplicationTypeSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);

        jLabel22.setText("Reply to queue manager:");
        jLabel22.setPreferredSize(new java.awt.Dimension(150, 25));

        ReplyToQueueManagerNameTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel23.setText("Reply to  manager:");
        jLabel23.setPreferredSize(new java.awt.Dimension(150, 25));

        ReplyToQueueNameTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel24.setText("User id:");
        jLabel24.setPreferredSize(new java.awt.Dimension(150, 25));

        UserIdTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CorrectionIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(GroupIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(AccountTokenTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ApplicationIdDataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ApplicationOriginDataTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BackoutSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CharacterSetCombobox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ExpirySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FeedbackCombobox, 0, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(FormatTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageFlagCombobox, 0, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MessageSequenceNumberSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OffsetSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OriginalLengthSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PersistenceCombobox, 0, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PrioritySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PutApplicationNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PutApplicationTypeSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ReplyToQueueManagerNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ReplyToQueueNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UserIdTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CorrectionIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(GroupIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccountTokenTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApplicationIdDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ApplicationOriginDataTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackoutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CharacterSetCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExpirySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FeedbackCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageFlagCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MessageSequenceNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OffsetSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OriginalLengthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PersistenceCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PrioritySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutApplicationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PutApplicationTypeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReplyToQueueManagerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ReplyToQueueNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UserIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        MQMDScrollPanel.setViewportView(jPanel2);

        MessagePanel.addTab("MQMD", MQMDScrollPanel);

        jLabel33.setText("Reason:");
        jLabel33.setPreferredSize(new java.awt.Dimension(150, 25));

        DLHReasonTextField.setEditable(false);
        DLHReasonTextField.setFocusable(false);
        DLHReasonTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        DLHDestintionTextField.setEditable(false);
        DLHDestintionTextField.setFocusable(false);
        DLHDestintionTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        DLHQmgrTextField.setEditable(false);
        DLHQmgrTextField.setFocusable(false);
        DLHQmgrTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel34.setText("Destination queue name:");
        jLabel34.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel35.setText("Destination qmgr name:");
        jLabel35.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel36.setText("Format:");
        jLabel36.setPreferredSize(new java.awt.Dimension(150, 25));

        DLHFormatTextField.setEditable(false);
        DLHFormatTextField.setFocusable(false);
        DLHFormatTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        jLabel37.setText("Put application name:");
        jLabel37.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel38.setText("Put application type:");
        jLabel38.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel39.setText("Put date:");
        jLabel39.setPreferredSize(new java.awt.Dimension(150, 25));

        jLabel40.setText("Put time:");
        jLabel40.setPreferredSize(new java.awt.Dimension(150, 25));

        DLHPutTimeTextField.setEditable(false);
        DLHPutTimeTextField.setFocusable(false);
        DLHPutTimeTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        DLHPutDateTextField.setEditable(false);
        DLHPutDateTextField.setFocusable(false);
        DLHPutDateTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        DLHPutTypeTextField.setEditable(false);
        DLHPutTypeTextField.setFocusable(false);
        DLHPutTypeTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        DLHPutNameTextField.setEditable(false);
        DLHPutNameTextField.setFocusable(false);
        DLHPutNameTextField.setPreferredSize(new java.awt.Dimension(380, 25));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHReasonTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHDestintionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHQmgrTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHFormatTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHPutNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHPutTypeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHPutDateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DLHPutTimeTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHReasonTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHDestintionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHQmgrTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHFormatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHPutNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHPutTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHPutDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DLHPutTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(454, Short.MAX_VALUE))
        );

        MessagePanel.addTab("DLH", jPanel3);

        ProgressBar.setIndeterminate(true);
        ProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        StatusLabel.setText("Updating.....");
        StatusLabel.setPreferredSize(new java.awt.Dimension(80, 25));

        KeepPositionCheckBox.setText("Keep position");
        KeepPositionCheckBox.setToolTipText("<html>   <p width=\"200\"> Keeping the message in same position after update. Depend on the queue depth, using this option may take a vary long time to complete update.   </p> </html>");
        KeepPositionCheckBox.setPreferredSize(new java.awt.Dimension(100, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MessagePanel)
                    .addComponent(TitlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(KeepPositionCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Closebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TitlePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MessagePanel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Closebutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(StatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(KeepPositionCheckBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        if(this.ContentEditorPane.getText().trim().isEmpty()){
             JOptionPane.showMessageDialog(component, "Data field is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            updateMessage(message);
        }
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void ClosebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClosebuttonActionPerformed
        Close();
    }//GEN-LAST:event_ClosebuttonActionPerformed

    private void ContentTypeSelectComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ContentTypeSelectComboBoxItemStateChanged
        if(evt.getStateChange() == ItemEvent.SELECTED){
            if(ContentTypeSelectComboBox.getSelectedItem().toString() == "XML"){
//                content = this.ContentEditorPane.getText();
                this.ContentEditorPane.setContentType("XML");
                this.ContentEditorPane.setText(content);   
            }
            else if(ContentTypeSelectComboBox.getSelectedItem().toString() == "Text"){
//                content = this.ContentEditorPane.getText();
                this.ContentEditorPane.setContentType("Text");
                this.ContentEditorPane.setText(content);            
            }
            else{
    //            this.ContentEditorPane.setContentType("Text");
    //            this.ContentEditorPane.setText(Arrays.toString(byteContent));
            }
        }
        
    }//GEN-LAST:event_ContentTypeSelectComboBoxItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AccountTokenTextField;
    private javax.swing.JTextField ApplicationIdDataTextField;
    private javax.swing.JTextField ApplicationOriginDataTextField;
    private javax.swing.JSpinner BackoutSpinner;
    private javax.swing.JComboBox CharacterSetCombobox;
    private javax.swing.JButton Closebutton;
    private javax.swing.ButtonGroup ContentDisplayTypeGroup;
    private javax.swing.JEditorPane ContentEditorPane;
    private javax.swing.JScrollPane ContentEditorScrollPane;
    private javax.swing.JToolBar ContentEditorToolbar;
    private javax.swing.JComboBox ContentTypeSelectComboBox;
    private javax.swing.JTextField CorrectionIdTextField;
    private javax.swing.JTextField DLHDestintionTextField;
    private javax.swing.JTextField DLHFormatTextField;
    private javax.swing.JTextField DLHPutDateTextField;
    private javax.swing.JTextField DLHPutNameTextField;
    private javax.swing.JTextField DLHPutTimeTextField;
    private javax.swing.JTextField DLHPutTypeTextField;
    private javax.swing.JTextField DLHQmgrTextField;
    private javax.swing.JTextField DLHReasonTextField;
    private javax.swing.JSpinner ExpirySpinner;
    private javax.swing.JComboBox FeedbackCombobox;
    private javax.swing.JTextField FormatTextField;
    private javax.swing.JTextField GroupIdTextField;
    private javax.swing.JCheckBox KeepPositionCheckBox;
    private javax.swing.JScrollPane MQMDScrollPanel;
    private javax.swing.JComboBox MessageFlagCombobox;
    private javax.swing.JTextField MessageIdTextField;
    private javax.swing.JTabbedPane MessagePanel;
    private javax.swing.JSpinner MessageSequenceNumberSpinner;
    private javax.swing.JSpinner OffsetSpinner;
    private javax.swing.JSpinner OriginalLengthSpinner;
    private javax.swing.JComboBox PersistenceCombobox;
    private javax.swing.JSpinner PrioritySpinner;
    private javax.swing.JProgressBar ProgressBar;
    private javax.swing.JTextField PutApplicationNameTextField;
    private javax.swing.JSpinner PutApplicationTypeSpinner;
    private javax.swing.JLabel QueueManagerLabel;
    private javax.swing.JLabel QueueNameLabel;
    private javax.swing.JTextField ReplyToQueueManagerNameTextField;
    private javax.swing.JTextField ReplyToQueueNameTextField;
    private javax.swing.JLabel StatusLabel;
    private javax.swing.JPanel TitlePanel;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JTextField UserIdTextField;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
