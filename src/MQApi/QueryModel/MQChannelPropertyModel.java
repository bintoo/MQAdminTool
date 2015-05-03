/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.VariableType;
import MQApi.Result.Annotations.MQObjectPropertyAnnotation;
import com.ibm.mq.constants.MQConstants;

/**
 *
 * @author jzhou
 */
public class MQChannelPropertyModel extends MQObjectPropertyModel{
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text)
    public String Name;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CHANNEL_TYPE, VarType = VariableType.Number)
    public Integer Type;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_BATCH_HB, VarType = VariableType.Number)
    public Integer BatchHeartbeat;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_BATCH_INTERVAL, VarType = VariableType.Number)
    public Integer BatchInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_BATCH_DATA_LIMIT, VarType = VariableType.Number)
    public Integer BatchDataLimit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_BATCH_SIZE, VarType = VariableType.Number)
    public Integer BatchSize;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_DESC, VarType = VariableType.Text)
    public String ChannelDesc;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_MONITORING_CHANNEL, VarType = VariableType.Number)
    public Integer ChannelMonitoring;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_STATISTICS_CHANNEL, VarType = VariableType.Number)
    public Integer ChannelStatistics;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CLIENT_CHANNEL_WEIGHT, VarType = VariableType.Number)
    public Integer ClientChannelWeight;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CLUSTER_NAME, VarType = VariableType.Text)
    public String ClusterName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CLUSTER_NAMELIST, VarType = VariableType.Text)
    public String ClusterNamelist;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CLWL_CHANNEL_PRIORITY, VarType = VariableType.Number)
    public Integer CLWLChannelPriority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CLWL_CHANNEL_RANK, VarType = VariableType.Number)
    public Integer CLWLChannelRank;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CLWL_CHANNEL_WEIGHT, VarType = VariableType.Number)
    public Integer CLWLChannelWeight;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_CONNECTION_AFFINITY, VarType = VariableType.Number)
    public Integer ConnectionAffinity;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_CONNECTION_NAME, VarType = VariableType.Text)
    public String ConnectionName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_DATA_CONVERSION, VarType = VariableType.Number)
    public Integer DataConversion;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_DEF_RECONNECT, VarType = VariableType.Number)
    public Integer DefReconnect;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_DISC_INTERVAL, VarType = VariableType.Number)
    public Integer DiscInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_HDR_COMPRESSION, VarType = VariableType.Number)
    public Integer HeaderCompression;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_HB_INTERVAL, VarType = VariableType.Number)
    public Integer HeartbeatInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_LOCAL_ADDRESS, VarType = VariableType.Text)
    public String LocalAddress;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_LONG_RETRY, VarType = VariableType.Number)
    public Integer LongRetryCount;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_LONG_TIMER, VarType = VariableType.Number)
    public Integer LongRetryInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MAX_INSTANCES, VarType = VariableType.Number)
    public Integer MaxInstances;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MAX_INSTS_PER_CLIENT, VarType = VariableType.Number)
    public Integer MaxInstancesPerClient;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MAX_MSG_LENGTH, VarType = VariableType.Number)
    public Integer MaxMsgLength;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MCA_NAME, VarType = VariableType.Text)
    public String MCAName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MCA_TYPE, VarType = VariableType.Number)
    public Integer MCAType;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MCA_USER_ID, VarType = VariableType.Text)
    public String MCAUserIdentifier;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MSG_COMPRESSION, VarType = VariableType.Number)
//    public Integer MessageCompression;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MSG_EXIT_NAME, VarType = VariableType.TextArray)
    public String[] MsgExit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MR_COUNT, VarType = VariableType.Number)
    public Integer MsgRetryCount;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MR_EXIT_NAME, VarType = VariableType.Text)
    public String MsgRetryExit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_MR_INTERVAL, VarType = VariableType.Number)
    public Integer MsgRetryInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MR_EXIT_USER_DATA, VarType = VariableType.Text)
    public String MsgRetryUserData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_MSG_EXIT_USER_DATA, VarType = VariableType.TextArray)
    public String[] MsgUserData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_NETWORK_PRIORITY, VarType = VariableType.Number)
    public Integer NetworkPriority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_NPM_SPEED, VarType = VariableType.Number)
    public Integer NonPersistentMsgSpeed;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_PASSWORD, VarType = VariableType.Text)
    public String Password;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_PROPERTY_CONTROL, VarType = VariableType.Number)
    public Integer PropertyControl;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_PUT_AUTHORITY, VarType = VariableType.Number)
    public Integer PutAuthority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_Q_MGR_NAME, VarType = VariableType.Text)
    public String QMgrName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_RCV_EXIT_NAME, VarType = VariableType.TextArray)
    public String[] ReceiveExit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_RCV_EXIT_USER_DATA, VarType = VariableType.TextArray)
    public String[] ReceiveUserData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_SEC_EXIT_NAME, VarType = VariableType.Text)
    public String SecurityExit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_SEC_EXIT_USER_DATA, VarType = VariableType.Text)
    public String SecurityUserData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_SEND_EXIT_NAME, VarType = VariableType.TextArray)
    public String[] SendExit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_SEND_EXIT_USER_DATA, VarType = VariableType.TextArray)
    public String[] SendUserData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_SEQUENCE_NUMBER_WRAP, VarType = VariableType.Number)
    public Integer SeqNumberWrap;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_SHARING_CONVERSATIONS, VarType = VariableType.Number)
    public Integer SharingConversations;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_SHORT_RETRY, VarType = VariableType.Number)
    public Integer ShortRetryCount;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_SHORT_TIMER, VarType = VariableType.Number)
    public Integer ShortRetryInterval;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_SSL_CIPHER_SPEC, VarType = VariableType.Text)
//    public String SSLCipherSpec;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_SSL_CLIENT_AUTH, VarType = VariableType.Number)
//    public Integer SSLClientAuth;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_XMIT_PROTOCOL_TYPE, VarType = VariableType.Number)
    public Integer TransportType;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_USE_DEAD_LETTER_Q, VarType = VariableType.Number)
    public Integer UseDLQ;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_USER_ID, VarType = VariableType.Text)
    public String UserIdentifier;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACH_XMIT_Q_NAME, VarType = VariableType.Text)
    public String XmitQName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_ALTERATION_DATE, VarType = VariableType.Text)
    public String AlterationDate;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_ALTERATION_TIME, VarType = VariableType.Text)
    public String AlterationTime;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIACH_KEEP_ALIVE_INTERVAL, VarType = VariableType.Number)
    public Integer KeepAliveInterval;
}
