/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.ChannelStatusType;
import MQApi.Enums.ChannelType;
import MQApi.Enums.QueryType;
import MQApi.Enums.VariableType;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import com.ibm.mq.constants.MQConstants;
import java.util.ArrayList;

/**
 *
 * @author jzhou
 */
public class MQChannelStatusListResult extends MQQueryResultBase{
    public ArrayList<ChannelStatusModel> DataModels = new ArrayList<ChannelStatusModel>();
    public class ChannelStatusModel extends DetailModelCore{        
        @MQObjectListtAnnotation(DisplayName = "Channel Name", MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Type", MQConstant = MQConstants.MQIACH_CHANNEL_TYPE, VarType = VariableType.ChannelType,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public ChannelType Type;
        @MQObjectListtAnnotation(DisplayName = "Running Status", MQConstant = MQConstants.MQIACH_CHANNEL_STATUS, VarType = VariableType.ChannelStatusType,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public ChannelStatusType Status;
        @MQObjectListtAnnotation(DisplayName = "MSGS", MQConstant = MQConstants.MQIACH_MSGS, VarType = VariableType.Number,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public Integer MSGS;
        @MQObjectListtAnnotation(DisplayName = "Start Date",MQConstant = MQConstants.MQCACH_CHANNEL_START_DATE, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String StartDate;
        @MQObjectListtAnnotation(DisplayName = "Start Time", MQConstant = MQConstants.MQCACH_CHANNEL_START_TIME, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String StartTime;
        @MQObjectListtAnnotation(DisplayName = "Last Msg Date",MQConstant = MQConstants.MQCACH_LAST_MSG_DATE, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String LastMsgDate;
        @MQObjectListtAnnotation(DisplayName = "Last Msg Time", MQConstant = MQConstants.MQCACH_LAST_MSG_TIME, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String LastMsgTime;
        @MQObjectListtAnnotation(DisplayName = "Sequence number", MQConstant = MQConstants.MQIACH_CURRENT_SEQ_NUMBER, VarType = VariableType.Number,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public Integer SequenceNumber;
        @MQObjectListtAnnotation(DisplayName = "Message in doubt", MQConstant = MQConstants.MQIACH_CURRENT_MSGS, VarType = VariableType.Number,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public Integer CurrentMsgs;
        @MQObjectListtAnnotation(DisplayName = "In-doubt status",MQConstant = MQConstants.MQIACH_INDOUBT_STATUS, VarType = VariableType.TrueFalse, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {"Not in-doubt", "In-doubt"})
        public String InDoubtStatus;
        @MQObjectListtAnnotation(DisplayName = "Local address", MQConstant = MQConstants.MQCACH_LOCAL_ADDRESS, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String LocalAddress;
        @MQObjectListtAnnotation(DisplayName = "MCA user id", MQConstant = MQConstants.MQCACH_MCA_USER_ID, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String MCAUserIdentifier;
//        @MQObjectListtAnnotation(DisplayName = "MCA status",MQConstant = MQConstants.MQIACH_MCA_STATUS, VarType = VariableType.TrueFalse, QueryType = QueryType.DataModels, TrueFalseDisplayValue = {"Stopped", "Running"})
//        public String MCAStatus;

        @Override
        public void setDisplayValues() {
        }
    }
}
