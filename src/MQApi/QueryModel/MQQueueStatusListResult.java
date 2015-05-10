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
public class MQQueueStatusListResult extends MQQueryResultBase  {
    
    public ArrayList<QueueStatusDetailModel> DataModels = new ArrayList<QueueStatusDetailModel>(); 
   
    
    public class QueueStatusDetailModel extends DetailModelCore{
        @MQObjectListtAnnotation(DisplayName = "Queue Name", MQConstant = MQConstants.MQCA_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Current depth", MQConstant = MQConstants.MQIA_CURRENT_Q_DEPTH, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial)
        public Integer CurrentQDepth;  
        @MQObjectListtAnnotation(DisplayName = "Last get date", MQConstant = MQConstants.MQCACF_LAST_GET_DATE, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String LastGetDate;  
        @MQObjectListtAnnotation(DisplayName = "Last get time", MQConstant = MQConstants.MQCACF_LAST_GET_TIME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String LastGetTime;  
        @MQObjectListtAnnotation(DisplayName = "Last put date", MQConstant = MQConstants.MQCACF_LAST_PUT_DATE, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String LastPutDate;  
        @MQObjectListtAnnotation(DisplayName = "Last put date", MQConstant = MQConstants.MQCACF_LAST_PUT_TIME, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial)
        public String LastPutTime;  
        @MQObjectListtAnnotation(DisplayName = "Oldest message (seconds)", MQConstant = MQConstants.MQIACF_OLDEST_MSG_AGE, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial, ShowOnTable = false)
        public Integer OldestMsgAge;   
        @MQObjectListtAnnotation(DisplayName = "Oldest message (seconds)", MQConstant = MQConstants.MQIACF_OLDEST_MSG_AGE, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial, GetValue = false)
        public String OldestMsgAgeValue; 
        @MQObjectListtAnnotation(DisplayName = "Uncommitted message", MQConstant = MQConstants.MQIACF_UNCOMMITTED_MSGS, VarType = VariableType.Number, QueryType = QueryType.QueueStatusDetial, ShowOnTable = false)
        public Integer UncommittedMsgs; 
        @MQObjectListtAnnotation(DisplayName = "Uncommitted message", MQConstant = MQConstants.MQIACF_UNCOMMITTED_MSGS, VarType = VariableType.Text, QueryType = QueryType.QueueStatusDetial, ShowOnTable = true, GetValue = false)
        public String UncommittedMsgsValue; 
        
        @Override
        public void setDisplayValues(){
            UncommittedMsgsValue = getUncommittedMsgsValue(UncommittedMsgs);
            OldestMsgAgeValue = getOldestMsgAgeValue(OldestMsgAge);
        }
    }
    
    private String getOldestMsgAgeValue(Integer value){
        if(value != null){
            if(value == -1){
                return "Not available";
            }
            else {
                return Integer.toString(value);
            }
        }
        return null;
    }
    
    private String getUncommittedMsgsValue(Integer value){
        if(value != null){
            switch(value){
                case MQConstants.MQQSUM_YES:
                    return "Yes";
                case MQConstants.MQQSUM_NO:
                    return "No";
            }
            return Integer.toString(value);
        }
        return null;
    }
}
