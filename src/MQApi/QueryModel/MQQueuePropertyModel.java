/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.QueueType;
import MQApi.Enums.VariableType;
import MQApi.Result.Annotations.MQObjectPropertyAnnotation;
import com.ibm.mq.constants.MQConstants;

/**
 *
 * @author jzhou
 */
public class MQQueuePropertyModel extends MQObjectPropertyModel{
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_Q_NAME, VarType = VariableType.Text)
    public String Name;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_TYPE, VarType = VariableType.Number)
    public Integer Type;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_Q_DESC, VarType = VariableType.Text)
    public String Description;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_INHIBIT_GET, VarType = VariableType.Number)
    public Integer InhibitGet;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_INHIBIT_PUT, VarType = VariableType.Number)
    public Integer InhibitPut;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_PRIORITY, VarType = VariableType.Number)
    public Integer DefPriority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_PERSISTENCE, VarType = VariableType.Number)
    public Integer DefPersistence;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_SCOPE, VarType = VariableType.Number)
    public Integer Scope;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_USAGE, VarType = VariableType.Number)
    public Integer Usage;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_MAX_Q_DEPTH, VarType = VariableType.Number)
    public Integer MaxQDepth;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_MAX_MSG_LENGTH, VarType = VariableType.Number)
    public Integer MaxMsgLength;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_SHAREABILITY, VarType = VariableType.Number)
    public Integer Shareability;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_INPUT_OPEN_OPTION, VarType = VariableType.Number)
    public Integer DefInputOpenOption;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_MSG_DELIVERY_SEQUENCE, VarType = VariableType.Number)
    public Integer MsgDeliverySequence;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_RETENTION_INTERVAL, VarType = VariableType.Number)
    public Integer RetentionInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEFINITION_TYPE, VarType = VariableType.Number, SendToPCF = false)
    public Integer DefinitionType;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DIST_LISTS, VarType = VariableType.Number)
    public Integer DistLists;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_READ_AHEAD, VarType = VariableType.Number)
    public Integer DefReadAhead;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_PUT_RESPONSE_TYPE, VarType = VariableType.Number)
    public Integer DefaultPutResponse;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_PROPERTY_CONTROL, VarType = VariableType.Number)
    public Integer PropertyControl;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CUSTOM, VarType = VariableType.Text, SendToPCF = false)
    public String Custom;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CLUS_CHL_NAME, VarType = VariableType.Text)
    public String ClusterChannelName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CLUSTER_NAME, VarType = VariableType.Text)
    public String ClusterName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CLUSTER_NAMELIST, VarType = VariableType.Text)
    public String ClusterNamelist;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_DEF_BIND, VarType = VariableType.Number)
    public Integer DefBind;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_CLWL_Q_RANK, VarType = VariableType.Number)
    public Integer CLWLQueueRank;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_CLWL_Q_PRIORITY, VarType = VariableType.Number)
    public Integer CLWLQueuePriority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_CLWL_USEQ, VarType = VariableType.Number)
    public Integer CLWLUseQ;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_TRIGGER_CONTROL, VarType = VariableType.Number)
    public Integer TriggerControl;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_TRIGGER_TYPE, VarType = VariableType.Number)
    public Integer TriggerType;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_TRIGGER_DEPTH, VarType = VariableType.Number)
    public Integer TriggerDepth;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_TRIGGER_MSG_PRIORITY, VarType = VariableType.Number)
    public Integer TriggerMsgPriority;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_TRIGGER_DATA, VarType = VariableType.Text)
    public String TriggerData;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_INITIATION_Q_NAME, VarType = VariableType.Text)
    public String InitiationQName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_PROCESS_NAME, VarType = VariableType.Text)
    public String ProcessName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_DEPTH_MAX_EVENT, VarType = VariableType.Number)
    public Integer QDepthMaxEvent;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_DEPTH_HIGH_EVENT, VarType = VariableType.Number)
    public Integer QDepthHighEvent;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_DEPTH_HIGH_LIMIT, VarType = VariableType.Number)
    public Integer QDepthHighLimit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_DEPTH_LOW_EVENT, VarType = VariableType.Number)
    public Integer QDepthLowEvent;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_DEPTH_LOW_LIMIT, VarType = VariableType.Number)
    public Integer QDepthLowLimit;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_SERVICE_INTERVAL_EVENT, VarType = VariableType.Number)
    public Integer QServiceIntervalEvent;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_Q_SERVICE_INTERVAL, VarType = VariableType.Number)
    public Integer QServiceInterval;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_BACKOUT_REQ_Q_NAME, VarType = VariableType.Text)
    public String BackoutRequeueName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_BACKOUT_THRESHOLD, VarType = VariableType.Number)
    public Integer BackoutThreshold;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_HARDEN_GET_BACKOUT, VarType = VariableType.Number)
    public Integer HardenGetBackout;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_NPM_CLASS, VarType = VariableType.Number)
    public Integer NonPersistentMessageClass;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_MONITORING_Q, VarType = VariableType.Number)
    public Integer QueueMonitoring;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_STATISTICS_Q, VarType = VariableType.Number)
    public Integer QueueStatistics;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_ACCOUNTING_Q, VarType = VariableType.Number)
    public Integer QueueAccounting;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_BASE_OBJECT_NAME, VarType = VariableType.Text)
    public String BaseObjectName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CREATION_DATE, VarType = VariableType.Text, SendToPCF = false)
    public String CreationDate;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CREATION_TIME, VarType = VariableType.Text, SendToPCF = false)
    public String CreationTime;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_CURRENT_Q_DEPTH, VarType = VariableType.Number, SendToPCF = false)
    public Integer CurrentQDepth;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_ALTERATION_DATE, VarType = VariableType.Text, SendToPCF = false)
    public String AlterationDate;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_ALTERATION_TIME, VarType = VariableType.Text, SendToPCF = false)
    public String AlterationTime;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_OPEN_INPUT_COUNT, VarType = VariableType.Number, SendToPCF = false)
    public Integer OpenInputCount;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_OPEN_OUTPUT_COUNT, VarType = VariableType.Number, SendToPCF = false)
    public Integer OpenOutputCount;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_REMOTE_Q_NAME, VarType = VariableType.Text)
    public String RemoteQName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_REMOTE_Q_MGR_NAME, VarType = VariableType.Text)
    public String RemoteQMgrName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_XMIT_Q_NAME, VarType = VariableType.Text)
    public String XmitQName;
    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_BASE_TYPE, VarType = VariableType.Number)
    public Integer TargetType;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCACF_COMMAND_SCOPE, VarType = VariableType.Text)
//    public String CommandScope;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_CF_STRUC_NAME, VarType = VariableType.Text)
//    public String CFStructure;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_INDEX_TYPE, VarType = VariableType.Number)
//    public Integer IndexType;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQIA_QSG_DISP, VarType = VariableType.Number)
//    public Integer QSGDisposition;
//    @MQObjectPropertyAnnotation(MQConstant = MQConstants.MQCA_STORAGE_CLASS, VarType = VariableType.Text)
//    public String StorageClass;
}
