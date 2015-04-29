/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi;

import MQApi.Enums.ChannelStatusType;
import MQApi.Enums.ChannelType;
import MQApi.Enums.QueueType;
import com.ibm.mq.constants.MQConstants;

/**
 *
 * @author jzhou
 */
public class ConstantConverter {
    public static int ConvertQueueTypeToConstant(QueueType type){
        switch(type){
            case All:
                return MQConstants.MQQT_ALL;
            case Local:
                return MQConstants.MQQT_LOCAL;
            case Remote:
                return MQConstants.MQQT_REMOTE;
            case Alias:
                return MQConstants.MQQT_ALIAS;
            case Model:
                return MQConstants.MQQT_MODEL;
            default:
                return MQConstants.MQQT_ALL;
        }
    }
    
    public static QueueType ConvertConstantToQueueType (int type){
        switch (type){
            case MQConstants.MQQT_ALIAS : 
                return QueueType.Alias;
            case MQConstants.MQQT_CLUSTER :
                return QueueType.Cluster;
            case MQConstants.MQQT_LOCAL :
                return QueueType.Local;
            case MQConstants.MQQT_REMOTE :
                return QueueType.Remote;
            case MQConstants.MQQT_MODEL :
                return QueueType.Model;
        }
        return null;
    }
    
    public static int ConvertChannelTypeToConstant(ChannelType type){
        switch(type){
            case Sender :
                return MQConstants.MQCHT_SENDER;
            case Server :
                return MQConstants.MQCHT_SERVER;
            case Receiver :
                return MQConstants.MQCHT_RECEIVER;
            case Requester :
                return MQConstants.MQCHT_REQUESTER;
            case Server_Connection :
                return MQConstants.MQCHT_SVRCONN;
            case Client_connection :
                return MQConstants.MQCHT_CLNTCONN;
            case Cluster_Receiver :
                return MQConstants.MQCHT_CLUSRCVR;
            case Cluster_Sender :
                return MQConstants.MQCHT_CLUSSDR;
            case Telemetry :
                return MQConstants.MQCHT_MQTT;
            case All :
                return MQConstants.MQCHT_ALL;
            default:
                return MQConstants.MQCHT_ALL;                   
        }
    }

    public static ChannelType ConvertConstantToChannelType (int constant){
        switch (constant){
            case MQConstants.MQCHT_SENDER : 
                return ChannelType.Sender;
            case MQConstants.MQCHT_SERVER: 
                return ChannelType.Server;
            case MQConstants.MQCHT_RECEIVER : 
                return ChannelType.Receiver;
            case MQConstants.MQCHT_REQUESTER : 
                return ChannelType.Requester;
            case MQConstants.MQCHT_SVRCONN : 
                return ChannelType.Server_Connection;
            case MQConstants.MQCHT_CLNTCONN : 
                return ChannelType.Client_connection;
            case MQConstants.MQCHT_CLUSRCVR : 
                return ChannelType.Cluster_Receiver;
            case MQConstants.MQCHT_CLUSSDR : 
                return ChannelType.Cluster_Sender;
            case MQConstants.MQCHT_MQTT : 
                return ChannelType.Telemetry;
            case MQConstants.MQCHT_ALL : 
                return ChannelType.All;
        }
        return null;
    }
    
    public static ChannelStatusType ConvertConstantToChannelStatusType(int constant){
          switch (constant){
            case MQConstants.MQCHS_BINDING : 
                return ChannelStatusType.Binding;
            case MQConstants.MQCHS_STARTING: 
                return ChannelStatusType.Starting;
            case MQConstants.MQCHS_RUNNING : 
                return ChannelStatusType.Running;
            case MQConstants.MQCHS_PAUSED : 
                return ChannelStatusType.Paused;
            case MQConstants.MQCHS_STOPPING : 
                return ChannelStatusType.Stopping;
            case MQConstants.MQCHS_RETRYING : 
                return ChannelStatusType.Retrying;
            case MQConstants.MQCHS_STOPPED : 
                return ChannelStatusType.Stopped;
            case MQConstants.MQCHS_REQUESTING : 
                return ChannelStatusType.Requesting;
            case MQConstants.MQCHS_SWITCHING : 
                return ChannelStatusType.Switching;
            case MQConstants.MQCHS_INITIALIZING : 
                return ChannelStatusType.Initializing;
        }
        return null;      
    }
}
