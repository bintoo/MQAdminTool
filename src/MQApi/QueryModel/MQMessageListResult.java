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
public class MQMessageListResult extends MQQueryResultBase{
    
    public ArrayList<MessageDetailModel> DataModels = new ArrayList<MessageDetailModel>(); 
    
    public class MessageDetailModel{
        public byte[] MessageId;
        public byte[] CorrelationId;
        @MQObjectListtAnnotation(DisplayName = "Position", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Position;
        @MQObjectListtAnnotation(DisplayName = "Message data", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String MessageData;
        @MQObjectListtAnnotation(DisplayName = "Put date/time", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String PutDateTime;
        @MQObjectListtAnnotation(DisplayName = "Backout count", MQConstant = 0, VarType = VariableType.Number, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public int BackoutCount;
        @MQObjectListtAnnotation(DisplayName = "Sequence number", MQConstant = 0, VarType = VariableType.Number, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public Integer SequenceNumber;
        @MQObjectListtAnnotation(DisplayName = "User identifier", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String UserIdentifier;
        @MQObjectListtAnnotation(DisplayName = "Put application name", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String PutApplicationName;
        @MQObjectListtAnnotation(DisplayName = "Format", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Format;
        @MQObjectListtAnnotation(DisplayName = "Total length", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String TotalLength;
        @MQObjectListtAnnotation(DisplayName = "Data Length", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String DataLength;
        @MQObjectListtAnnotation(DisplayName = "Application identify data", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String ApplicationIdentifyData;
        @MQObjectListtAnnotation(DisplayName = "Application original data", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String ApplicationOriginalData;
        @MQObjectListtAnnotation(DisplayName = "Encoding", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Encoding;
        @MQObjectListtAnnotation(DisplayName = "Expiry", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Expiry;
        @MQObjectListtAnnotation(DisplayName = "Message type", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String MessageType;
        @MQObjectListtAnnotation(DisplayName = "Persistence", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Persistence;
        @MQObjectListtAnnotation(DisplayName = "Priority", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String Priority;
        @MQObjectListtAnnotation(DisplayName = "Reply to queue", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String ReplyToQueue;
        @MQObjectListtAnnotation(DisplayName = "Reply to queue manager", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String ReplyToQueueManager;
        @MQObjectListtAnnotation(DisplayName = "Account token", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String AccountToken;
        @MQObjectListtAnnotation(DisplayName = "Message id", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public String MessageIdString;
        @MQObjectListtAnnotation(DisplayName = "Offset", MQConstant = 0, VarType = VariableType.Text, QueryType = QueryType.MessageDetail, TrueFalseDisplayValue = {""})
        public Integer Offset;
        
    }
}
