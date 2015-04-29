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
public class MQQueueListResult extends MQQueryResultBase  {
    
    public ArrayList<QueueDetailModel> DataModels = new ArrayList<QueueDetailModel>(); 
    
    public ArrayList<QueueDetailModel> GetFilterDataModels(String searchString, boolean showTemp, boolean showSystem){
        
        if(DataModels != null && DataModels.size() > 0){
            searchString = searchString.trim();
            if(searchString == null || searchString.isEmpty()){
                searchString = "*";
            }
            final boolean isMatchExact = !searchString.endsWith("*");
            final String nameSearchString = searchString.trim().equals("*") ? null : searchString.trim().split("[*]")[0];
            ArrayList<QueueDetailModel> resultDataModels = (ArrayList<QueueDetailModel> )DataModels.clone();
            ArrayList<QueueDetailModel> toBeRemoved = new ArrayList<QueueDetailModel>();
            if(!showTemp){
                for(QueueDetailModel t : resultDataModels){
                    if(t.DefinitionType != null && t.DefinitionType.equals("Dynamically defined temporary queue") && t.Type != QueueType.Model){
                        toBeRemoved.add(t);
                    }
                }
            }
            if(!showSystem){
                for(QueueDetailModel t : resultDataModels){
                    if( t.QueueName != null && t.QueueName.startsWith("SYSTEM")){
                        toBeRemoved.add(t);
                    }
                }
            }

            if(nameSearchString != null && !nameSearchString.isEmpty()){
                for(QueueDetailModel t : resultDataModels){
                    if( !isMatchExact){
                        if(t.QueueName != null && !t.QueueName.startsWith(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                    else{
                        if(t.QueueName != null && !t.QueueName.equals(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                }
            }
            for(QueueDetailModel r : toBeRemoved){
                resultDataModels.remove(r);
            }
            return resultDataModels;
        }
        return null;
    }
    
    public ArrayList<QueueDetailModel> GetFilterTransmissionQDataModels(){
        if(DataModels != null && DataModels.size() > 0){
            ArrayList<QueueDetailModel> resultDataModels = (ArrayList<QueueDetailModel> )DataModels.clone();
            ArrayList<QueueDetailModel> toBeRemoved = new ArrayList<QueueDetailModel>();
                for(QueueDetailModel t : resultDataModels){
                    if(t.Usage == null || "Normal".equals(t.Usage)){
                        toBeRemoved.add(t);
                    }
                }
            for(QueueDetailModel r : toBeRemoved){
                resultDataModels.remove(r);
            }
            return resultDataModels;
        }
        return null;
    }
    
    public class QueueDetailModel{
        @MQObjectListtAnnotation(DisplayName = "Queue Name", MQConstant = MQConstants.MQCA_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String QueueName;
        @MQObjectListtAnnotation(DisplayName = "Queue Type", MQConstant = MQConstants.MQIA_Q_TYPE, VarType = VariableType.QueueType,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public QueueType Type;
        @MQObjectListtAnnotation(DisplayName = "Current Depth", MQConstant = MQConstants.MQIA_CURRENT_Q_DEPTH, VarType = VariableType.Number,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public Integer CurrentQueueDepth;
        @MQObjectListtAnnotation(DisplayName = "Max Depth", MQConstant = MQConstants.MQIA_MAX_Q_DEPTH,VarType = VariableType.Number,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public Integer MaxQueueDepth;
        @MQObjectListtAnnotation(DisplayName = "Open input count", MQConstant = MQConstants.MQIA_OPEN_INPUT_COUNT, VarType = VariableType.Number, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public Integer OpenInputCount;
        @MQObjectListtAnnotation(DisplayName = "Open output count", MQConstant = MQConstants.MQIA_OPEN_OUTPUT_COUNT, VarType = VariableType.Number, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public Integer OpenOutputCount;
        @MQObjectListtAnnotation(DisplayName = "Put message", MQConstant = MQConstants.MQIA_INHIBIT_PUT, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Allowed","Inhibited"})
        public String PutMessage;
        @MQObjectListtAnnotation(DisplayName = "Get message", MQConstant = MQConstants.MQIA_INHIBIT_GET, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Allowed","Inhibited"})
        public String GetMessage;
        @MQObjectListtAnnotation(DisplayName = "Remote queue name", MQConstant = MQConstants.MQCA_REMOTE_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String RemoteQueueName;
        @MQObjectListtAnnotation(DisplayName = "Remote queue manager", MQConstant = MQConstants.MQCA_REMOTE_Q_MGR_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String RemoteQueueManager;
        @MQObjectListtAnnotation(DisplayName = "Base object", MQConstant = MQConstants.MQCA_BASE_OBJECT_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String BaseObject;
        @MQObjectListtAnnotation(DisplayName = "Base type", MQConstant = MQConstants.MQIA_BASE_TYPE, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Topic", "Queue"})
        public String BaseObjectType;
        @MQObjectListtAnnotation(DisplayName = "Description", MQConstant = MQConstants.MQCA_Q_DESC, VarType = VariableType.Text,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String Description;
        @MQObjectListtAnnotation(DisplayName = "Default persistent", MQConstant = MQConstants.MQIA_DEF_PERSISTENCE, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Not persistent","Persistent"})
        public String DefaultPersistent;
        @MQObjectListtAnnotation(DisplayName = "Usage", MQConstant = MQConstants.MQIA_USAGE, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Normal","Transmission"})
        public String Usage;
        @MQObjectListtAnnotation(DisplayName = "Transmission queue", MQConstant = MQConstants.MQCA_XMIT_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String TransmissionQueue;
        @MQObjectListtAnnotation(DisplayName = "Shareability", MQConstant = MQConstants.MQIA_SHAREABILITY, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Not shareable","Shareable"})
        public String Shareability;
        @MQObjectListtAnnotation(DisplayName = "Trigger control", MQConstant = MQConstants.MQIA_TRIGGER_CONTROL, VarType = VariableType.TrueFalse, QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {"Off","On"})
        public String TriggerControl;
        @MQObjectListtAnnotation(DisplayName = "Backout threshold", MQConstant = MQConstants.MQIA_BACKOUT_THRESHOLD, VarType = VariableType.Number,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public Integer BackoutThreshold;
        @MQObjectListtAnnotation(DisplayName = "Definition type", MQConstant = MQConstants.MQIA_DEFINITION_TYPE, VarType = VariableType.QueueDefinitionType,  QueryType = QueryType.QueueDetial, TrueFalseDisplayValue = {""})
        public String DefinitionType;
    }
}
