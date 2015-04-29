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
    public ArrayList<ChannelStatusModel> ChannelStatus = new ArrayList<ChannelStatusModel>();
    public class ChannelStatusModel{        
        @MQObjectListtAnnotation(DisplayName = "Channel Name", MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String ChannelName;
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
    }
}
