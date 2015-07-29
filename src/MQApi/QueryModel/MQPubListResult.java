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
public class MQPubListResult extends MQQueryResultBase  {
    
    public ArrayList<PubDetailModel> DataModels = new ArrayList<PubDetailModel>(); 
    
    public ArrayList<PubDetailModel> GetFilterDataModels(String searchString, boolean showSystem){
        
        if(DataModels != null && DataModels.size() > 0){
            searchString = searchString.trim();
            if(searchString == null || searchString.isEmpty()){
                searchString = "*";
            }
            final boolean isMatchExact = !searchString.endsWith("*");
            final String nameSearchString = searchString.trim().equals("*") ? null : searchString.trim().split("[*]")[0];
            ArrayList<PubDetailModel> resultDataModels = (ArrayList<PubDetailModel> )DataModels.clone();
            ArrayList<PubDetailModel> toBeRemoved = new ArrayList<PubDetailModel>();

            if(!showSystem){
                for(PubDetailModel t : resultDataModels){
                    if( t.Name != null && t.Name.startsWith("SYSTEM")){
                        toBeRemoved.add(t);
                    }
                }
            }

            if(nameSearchString != null && !nameSearchString.isEmpty()){
                for(PubDetailModel t : resultDataModels){
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
            for(PubDetailModel r : toBeRemoved){
                resultDataModels.remove(r);
            }
            return resultDataModels;
        }
        return null;
    }
        
    
    public class PubDetailModel extends DetailModelCore{
        @MQObjectListtAnnotation(DisplayName = "Topic name", MQConstant = MQConstants.MQCA_TOPIC_NAME, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public String Name;
        @MQObjectListtAnnotation(DisplayName = "Topic type", MQConstant = MQConstants.MQIA_TOPIC_TYPE, VarType = VariableType.Number, QueryType = QueryType.PubDetail, ShowOnTable = false, ShowOnCSV = false)
        public Integer TopicTypeValue;       
        @MQObjectListtAnnotation(DisplayName = "Topic type", MQConstant = MQConstants.MQIA_TOPIC_TYPE, VarType = VariableType.Text, QueryType = QueryType.PubDetail, GetValue = false)
        public String TopicType;    
        @MQObjectListtAnnotation(DisplayName = "Topic string", MQConstant = MQConstants.MQCA_TOPIC_STRING, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public String TopicString;
        @MQObjectListtAnnotation(DisplayName = "Subscription scope", MQConstant = MQConstants.MQIA_SUB_SCOPE, VarType = VariableType.Number, QueryType = QueryType.PubDetail, ShowOnTable = false, ShowOnCSV = false)
        public Integer SubscriptionScopeValue; 
        @MQObjectListtAnnotation(DisplayName = "Subscription scope", MQConstant = MQConstants.MQIA_TOPIC_TYPE, VarType = VariableType.Text, QueryType = QueryType.PubDetail, GetValue = false)
        public String SubscriptionScope; 
        @MQObjectListtAnnotation(DisplayName = "Topic description", MQConstant = MQConstants.MQCA_TOPIC_DESC, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public String TopicDesc;
        @MQObjectListtAnnotation(DisplayName = "Default persistence", MQConstant = MQConstants.MQIA_TOPIC_DEF_PERSISTENCE, VarType = VariableType.Number, QueryType = QueryType.PubDetail, ShowOnTable = false, ShowOnCSV = false)
        public Integer DefPersistenceValue; 
        @MQObjectListtAnnotation(DisplayName = "Default persistence", MQConstant = MQConstants.MQIA_TOPIC_DEF_PERSISTENCE, VarType = VariableType.Text, QueryType = QueryType.PubDetail, GetValue = false)
        public String DefPersistence; 
        @MQObjectListtAnnotation(DisplayName = "Default prioroty", MQConstant = MQConstants.MQIA_DEF_PRIORITY, VarType = VariableType.Number, QueryType = QueryType.PubDetail)
        public Integer DefPriority; 
        @MQObjectListtAnnotation(DisplayName = "Durable model Q name", MQConstant = MQConstants.MQCA_MODEL_DURABLE_Q, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public Integer DurableModelQName;
        @MQObjectListtAnnotation(DisplayName = "Durable Subscriotions", MQConstant = MQConstants.MQIA_TOPIC_DEF_PERSISTENCE, VarType = VariableType.Number, QueryType = QueryType.PubDetail, ShowOnTable = false, ShowOnCSV = false)
        public Integer DurableSubscriptionsValue; 
        @MQObjectListtAnnotation(DisplayName = "Durable Subscriotions", MQConstant = MQConstants.MQIA_TOPIC_DEF_PERSISTENCE, VarType = VariableType.Text, QueryType = QueryType.PubDetail, GetValue = false)
        public String DurableSubscriptions;        
        @MQObjectListtAnnotation(DisplayName = "Alteration date", MQConstant = MQConstants.MQCA_ALTERATION_DATE, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public String AlterationDate;   
        @MQObjectListtAnnotation(DisplayName = "Alteration time", MQConstant = MQConstants.MQCA_ALTERATION_TIME, VarType = VariableType.Text, QueryType = QueryType.PubDetail)
        public String AlterationTime;
        
        @Override
        public void setDisplayValues(){
            if(TopicTypeValue == MQConstants.MQTOPT_LOCAL){
                TopicType = "Local";
            }
            else{
                TopicType = "Cluster";
            }
            
            if(SubscriptionScopeValue == MQConstants.MQSCOPE_ALL){
                SubscriptionScope = "All";
            }
            else if(SubscriptionScopeValue == MQConstants.MQSCOPE_AS_PARENT){
                SubscriptionScope = "As parent";
            }
            else {
                SubscriptionScope = "Queue manager";
            }
            
            if(DefPersistenceValue == MQConstants.MQPER_PERSISTENCE_AS_PARENT){
                DefPersistence = "As parent";
            }
            else if(DefPersistenceValue == MQConstants.MQPER_PERSISTENT){
                DefPersistence = "Persistent";
            }
            else{
                DefPersistence = "Not persistent";
            }
            
            if(DurableSubscriptionsValue == MQConstants.MQSUB_DURABLE_AS_PARENT){
                DurableSubscriptions = "As parent";
            }
            else if(DurableSubscriptionsValue == MQConstants.MQSUB_DURABLE_YES){
                DurableSubscriptions = "Durable";
            }
            else{
                DurableSubscriptions = "Not durable";
            }
                
        }
    }
    
}
