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
import com.sun.javafx.animation.TickCalculation;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.util.converter.LocalDateStringConverter;

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
        @MQObjectListtAnnotation(DisplayName = "Channel connection",MQConstant = MQConstants.MQCACH_CONNECTION_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public String ConnectionName;
        @MQObjectListtAnnotation(DisplayName = "Current Sharing Conversation",MQConstant = MQConstants.MQIACH_CURRENT_SHARING_CONVS, VarType = VariableType.Number, QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""})
        public Integer CurrentSharingConversations;
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
        @MQObjectListtAnnotation(DisplayName = "Ave MSGS", MQConstant = MQConstants.MQIACH_MSGS, VarType = VariableType.Number,QueryType = QueryType.ChannelStatus, TrueFalseDisplayValue = {""}, ShowOnTable = false, GetValue = false)
        public Integer AveMSGS;
//        @MQObjectListtAnnotation(DisplayName = "MCA status",MQConstant = MQConstants.MQIACH_MCA_STATUS, VarType = VariableType.TrueFalse, QueryType = QueryType.DataModels, TrueFalseDisplayValue = {"Stopped", "Running"})
//        public String MCAStatus;

        @Override
        public void setDisplayValues() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateStringConverter converter = new LocalDateStringConverter(formatter, null);
            LocalDate startDate = converter.fromString(StartDate);
            LocalDate endDate = converter.fromString(LastMsgDate);
            LocalDate today = LocalDate.now();
            if(startDate != null && endDate != null){

                boolean isEndDateToday = Period.between(today, endDate).getDays() == 0;
                if(isEndDateToday && MSGS != null){
                    int dayDiff = getDayDiff(StartDate, LastMsgDate) + 1;
                    AveMSGS = MSGS / dayDiff;
                }
            }
                      
        }
        
        private int getDayDiff(String startDate, String endDate){
            double DAY_MILLIS = 1000.0 * 24.0 * 60.0 * 60.0;
            String[] startStrings = startDate.split("-");
            String[] endStrings = endDate.split("-");
            
            Calendar c1=Calendar.getInstance();
            c1.set(Integer.parseInt(startStrings[0]),Integer.parseInt(startStrings[1]), Integer.parseInt(startStrings[2]) );
            Calendar c2=Calendar.getInstance();
            c2.set(Integer.parseInt(endStrings[0]),Integer.parseInt(endStrings[1]), Integer.parseInt(endStrings[2]));

            Date d1=c1.getTime();
            Date d2=c2.getTime();

            long diff=d2.getTime()-d1.getTime();
            double noofdays= Math.abs(diff/DAY_MILLIS);
            return (int )noofdays;

        }
    }
}
