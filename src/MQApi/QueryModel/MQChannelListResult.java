/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.ChannelStatusType;
import MQApi.Enums.QueryType;
import MQApi.Enums.ChannelType;
import MQApi.Enums.VariableType;
import static MQApi.Enums.VariableType.ChannelType;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import com.ibm.mq.constants.MQConstants;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jzhou
 */
public class MQChannelListResult extends MQQueryResultBase{
    public ArrayList<ChannelDetailModel> DataModels = new ArrayList<ChannelDetailModel>();
 
    public ArrayList<ChannelDetailModel> GetFilterDataModels(String searchString, boolean showSystem){
        if(DataModels != null && DataModels.size() > 0){
            searchString = searchString.trim();
            if(searchString == null || searchString.isEmpty()){
                searchString = "*";
            }            
            final boolean isMatchExact = !searchString.endsWith("*");
            final String nameSearchString = searchString.trim().equals("*") ? null : searchString.trim().split("[*]")[0];
            ArrayList<ChannelDetailModel> resultDataModels = (ArrayList<ChannelDetailModel> )DataModels.clone();
            ArrayList<ChannelDetailModel> toBeRemoved = new ArrayList<ChannelDetailModel>();
            if(!showSystem){
                for(ChannelDetailModel t : resultDataModels){
                    if( t.Name != null && t.Name.startsWith("SYSTEM")){
                        toBeRemoved.add(t);
                    }
                }
            }

            if(nameSearchString != null && !nameSearchString.isEmpty()){
                for(ChannelDetailModel t : resultDataModels){
                    if( !isMatchExact){
                        if(!t.Name.startsWith(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                    else{
                        if(!t.Name.equals(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                }
            }
            for(ChannelDetailModel r : toBeRemoved){
                resultDataModels.remove(r);
            }
            return resultDataModels;
        }
        return null;
    }
    public class ChannelDetailModel extends DetailModelCore{        
        @MQObjectListtAnnotation(DisplayName = "Channel Name", MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Type", MQConstant = MQConstants.MQIACH_CHANNEL_TYPE, VarType = VariableType.ChannelType,QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public ChannelType Type;
        @MQObjectListtAnnotation(DisplayName = "Running Status", MQConstant = MQConstants.MQIACH_CHANNEL_STATUS, VarType = VariableType.ChannelStatusType,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public ChannelStatusType Status;
//        @MQObjectListtAnnotation(DisplayName = "Overall MSGS", MQConstant = MQConstants.MQIACH_MSGS, VarType = VariableType.Number,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
//        public int MSGS;
        @MQObjectListtAnnotation(DisplayName = "Alteration Date",MQConstant = MQConstants.MQCA_ALTERATION_DATE, VarType = VariableType.Text, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String AlterationDate;
        @MQObjectListtAnnotation(DisplayName = "Connection Name", MQConstant = MQConstants.MQCACH_CONNECTION_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String ConnectionName;
        @MQObjectListtAnnotation(DisplayName = "Transmission queue", MQConstant = MQConstants.MQCACH_XMIT_Q_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String TrabsnussionQueue;
        @MQObjectListtAnnotation(DisplayName = "MCA Name", MQConstant = MQConstants.MQCACH_MCA_NAME, VarType = VariableType.Text,QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String MCAName;
        @MQObjectListtAnnotation(DisplayName = "Batch Size", MQConstant = MQConstants.MQIACH_BATCH_SIZE, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer BatchSize;
        @MQObjectListtAnnotation(DisplayName = "Disconnect interval", MQConstant = MQConstants.MQIACH_DISC_INTERVAL, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer DisconnectInterval;
        @MQObjectListtAnnotation(DisplayName = "Heartbeat interval", MQConstant = MQConstants.MQIACH_HB_INTERVAL, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer HeartbeatInterval;
        @MQObjectListtAnnotation(DisplayName = "Max instances", MQConstant = MQConstants.MQIACH_MAX_INSTANCES, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer MaxInstances;
        @MQObjectListtAnnotation(DisplayName = "Max instances per client", MQConstant = MQConstants.MQIACH_MAX_INSTS_PER_CLIENT, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer MaxInstancesPerClient;
        @MQObjectListtAnnotation(DisplayName = "Description", MQConstant = MQConstants.MQCACH_DESC, VarType = VariableType.Text,QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String Description;
        @MQObjectListtAnnotation(DisplayName = "SSL Cihper Spec", MQConstant = MQConstants.MQCACH_SSL_CIPHER_SUITE, VarType = VariableType.Text,QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public String SSLChiperSpec;
        @MQObjectListtAnnotation(DisplayName = "Share conversations", MQConstant = MQConstants.MQIACH_SHARING_CONVERSATIONS, VarType = VariableType.Number, QueryType = QueryType.ChannelDetail, TrueFalseDisplayValue = {""})
        public Integer ShareConversations;
//        @MQObjectListtAnnotation(DisplayName = "Start Date",MQConstant = MQConstants.MQCACH_CHANNEL_START_DATE, VarType = VariableType.Text)
//        public String ChannelStartDate;
//        @MQObjectListtAnnotation(DisplayName = "Start Time", MQConstant = MQConstants.MQCACH_CHANNEL_START_TIME, VarType = VariableType.Text)
//        public String ChannelStartTime;

        @Override
        public void setDisplayValues() {
        }
    }
}
