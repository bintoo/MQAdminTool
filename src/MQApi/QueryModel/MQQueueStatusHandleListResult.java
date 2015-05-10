/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.QueryType;
import MQApi.Enums.QueueType;
import MQApi.Enums.VariableType;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import com.ibm.mq.constants.MQConstants;
import java.util.ArrayList;

/**
 *
 * @author jzhou
 */
public class MQQueueStatusHandleListResult extends MQQueryResultBase  {
    
    public ArrayList<QueueStatusHandleDetailModel> DataModels = new ArrayList<QueueStatusHandleDetailModel>(); 
   
    
    public class QueueStatusHandleDetailModel extends DetailModelCore{
        @MQObjectListtAnnotation(DisplayName = "Queue Name", MQConstant = MQConstants.MQCA_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Open application tag", MQConstant = MQConstants.MQCACF_APPL_TAG, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String ApplTag;
        @MQObjectListtAnnotation(DisplayName = "ApplType", MQConstant = MQConstants.MQIA_APPL_TYPE, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial, ShowOnTable = false)
        public Integer ApplType;
        @MQObjectListtAnnotation(DisplayName = "ApplType", VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial, ShowOnTable = true, GetValue = false)
        public String ApplTypeDisplayValue;
        @MQObjectListtAnnotation(DisplayName = "Asynchronous state", MQConstant = MQConstants.MQIACF_ASYNC_STATE, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial, ShowOnTable = false)
        public Integer AsynchronousState;
        @MQObjectListtAnnotation(DisplayName = "Asynchronous state", VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial, ShowOnTable = true, GetValue = false)
        public String AsynchronousStateValue;
        @MQObjectListtAnnotation(DisplayName = "Channel name", MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String ChannelName;
        @MQObjectListtAnnotation(DisplayName = "Connection name", MQConstant = MQConstants.MQCACH_CONNECTION_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String Conname;
        @MQObjectListtAnnotation(DisplayName = "Handle state", MQConstant = MQConstants.MQIACF_HANDLE_STATE, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial, ShowOnTable = false)
        public Integer HandleState;
        @MQObjectListtAnnotation(DisplayName = "Handle state", VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial, ShowOnTable = true, GetValue = false)
        public String HandleStateValue;
        @MQObjectListtAnnotation(DisplayName = "Process id", MQConstant = MQConstants.MQIACF_PROCESS_ID, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String ProcessId;
        @MQObjectListtAnnotation(DisplayName = "User id", MQConstant = MQConstants.MQCACF_USER_IDENTIFIER, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String UserIdentifier;
        
        @Override
        public void setDisplayValues(){
            ApplTypeDisplayValue = ApplType != null ? getApplTypeDisplayValue(ApplType) : null;
            AsynchronousStateValue = AsynchronousState != null ? getAsynchronousStateDisplayValue(AsynchronousState) : null;
            HandleStateValue = HandleState != null ? getHandleStateDisplayValue(HandleState) : null;
        }
    }
    
    private String getApplTypeDisplayValue(Integer value){
        switch(value){
            case MQConstants.MQAT_QMGR:
                return "Queue manager process";
            case MQConstants.MQAT_USER:
                return "User application";
            case MQConstants.MQAT_CHANNEL_INITIATOR:
                return "Channel initiator";
            case MQConstants.MQAT_BATCH:
                return "Batch";
            case MQConstants.MQAT_RRS_BATCH:
                return "RRS batch";
            case MQConstants.MQAT_CICS:
                return "CICS";
            case MQConstants.MQAT_IMS:
                return "IMS";
            case MQConstants.MQAT_SYSTEM_EXTENSION:
                return "System exception";
        }
        return null;
    }
    
    private String getAsynchronousStateDisplayValue(Integer value){
        switch(value){
            case MQConstants.MQAS_ACTIVE:
                return "Active";
            case MQConstants.MQAS_INACTIVE:
                return "Inactive";
            case MQConstants.MQAS_SUSPENDED:
                return "Suspended";
            case MQConstants.MQAS_SUSPENDED_TEMPORARY:
                return "Suspended temporary";
            case MQConstants.MQAS_NONE:
                return "None";
        }
        return null;
    }
    
    private String getHandleStateDisplayValue(Integer value){
        switch(value){
            case MQConstants.MQHSTATE_ACTIVE:
                return "Active";
            case MQConstants.MQHSTATE_INACTIVE:
                return "Inactive";
        }
        return null;
    }
}
