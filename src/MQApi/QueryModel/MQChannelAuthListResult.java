/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

import MQApi.Enums.ChannelAuthType;
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
public class MQChannelAuthListResult extends MQQueryResultBase  {
    
    public ArrayList<ChannelAuthDetailModel> DataModels = new ArrayList<ChannelAuthDetailModel>(); 
    
    public ArrayList<ChannelAuthDetailModel> GetFilterDataModels(String searchString, boolean showSystem){
        
        if(DataModels != null && DataModels.size() > 0){
            searchString = searchString.trim();
            if(searchString == null || searchString.isEmpty()){
                searchString = "*";
            }
            final boolean isMatchExact = !searchString.endsWith("*");
            final String nameSearchString = searchString.trim().equals("*") ? null : searchString.trim().split("[*]")[0];
            ArrayList<ChannelAuthDetailModel> resultDataModels = (ArrayList<ChannelAuthDetailModel> )DataModels.clone();
            ArrayList<ChannelAuthDetailModel> toBeRemoved = new ArrayList<ChannelAuthDetailModel>();

            if(!showSystem){
                for(ChannelAuthDetailModel t : resultDataModels){
                    if( t.Name != null && t.Name.startsWith("SYSTEM")){
                        toBeRemoved.add(t);
                    }
                }
            }

            if(nameSearchString != null && !nameSearchString.isEmpty()){
                for(ChannelAuthDetailModel t : resultDataModels){
                    if( !isMatchExact){
                        if(t.Name != null && !t.Name.startsWith(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                    else{
                        if(t.Name != null && !t.Name.equals(nameSearchString)){
                            toBeRemoved.add(t);
                        }
                    }
                }
            }
            for(ChannelAuthDetailModel r : toBeRemoved){
                resultDataModels.remove(r);
            }
            return resultDataModels;
        }
        return null;
    }
        
    
    public class ChannelAuthDetailModel{
        @MQObjectListtAnnotation(DisplayName = "Channel profile", MQConstant = MQConstants.MQCACH_CHANNEL_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {""})
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Type", MQConstant = MQConstants.MQIACF_CHLAUTH_TYPE, VarType = VariableType.ChannelAuthType,  QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {""})
        public ChannelAuthType Type;
        @MQObjectListtAnnotation(DisplayName = "Address", MQConstant = MQConstants.MQCACH_CONNECTION_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String Address;
        @MQObjectListtAnnotation(DisplayName = "Description", MQConstant = MQConstants.MQCA_CHLAUTH_DESC, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String Description;
        @MQObjectListtAnnotation(DisplayName = "Address list", MQConstant = MQConstants.MQCACH_CONNECTION_NAME_LIST, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String AddressList;
        @MQObjectListtAnnotation(DisplayName = "User list", MQConstant = MQConstants.MQCACH_MCA_USER_ID_LIST, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String UserList;
        @MQObjectListtAnnotation(DisplayName = "Client user id", MQConstant = MQConstants.MQCACH_CLIENT_USER_ID, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String ClntUser;
        @MQObjectListtAnnotation(DisplayName = "MCA user", MQConstant = MQConstants.MQCACH_MCA_USER_ID, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String MCAUser;
        @MQObjectListtAnnotation(DisplayName = "Remote queue manager", MQConstant = MQConstants.MQCA_REMOTE_Q_MGR_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String QMName;
        @MQObjectListtAnnotation(DisplayName = "SSL Peer", MQConstant = MQConstants.MQCACH_SSL_PEER_NAME, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String SSLPeer;
        @MQObjectListtAnnotation(DisplayName = "Warn", MQConstant = MQConstants.MQIACH_WARNING, VarType = VariableType.TrueFalse, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {"No", "Yes"})
        public String Warn;
        @MQObjectListtAnnotation(DisplayName = "User source", MQConstant = MQConstants.MQIACH_USER_SOURCE, VarType = VariableType.Number, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {}, ShowOnTable = false)
        public Integer UserSrc;
        @MQObjectListtAnnotation(DisplayName = "User source", MQConstant = MQConstants.MQIACH_USER_SOURCE, VarType = VariableType.Number, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {}, ShowOnTable = true, GetValue = false)
        public String UserSrcValue;
        @MQObjectListtAnnotation(DisplayName = "Alteration date", MQConstant = MQConstants.MQCA_ALTERATION_DATE, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String AlterationDate;
        @MQObjectListtAnnotation(DisplayName = "Alteration time", MQConstant = MQConstants.MQCA_ALTERATION_TIME, VarType = VariableType.Text, QueryType = QueryType.ChannelAuthDetail, TrueFalseDisplayValue = {})
        public String AlterationTime;
        
        public void setDisplayValues(){
            UserSrcValue = UserSrc != null ? getUserSrcValue(UserSrc) : null;
        }
    }
    
    private String getUserSrcValue(Integer value){
        if(value != null){
            switch(value){
                case MQConstants.MQUSRC_MAP :
                    return "Map";
                case MQConstants.MQUSRC_NOACCESS :
                    return "No access";
                case MQConstants.MQUSRC_CHANNEL :
                    return "Channel";
            }
        }
        return null;
    }
}
