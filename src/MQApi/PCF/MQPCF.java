/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.PCF;
import MQApi.QueryModel.MQChannelListResult;
import MQApi.QueryModel.MQChannelStatusListResult;
import MQApi.QueryModel.MQQueueListResult;
import MQApi.QueryModel.MQQueuePropertyModel;
import MQApi.QueryModel.MQCommandResult;
import MQApi.QueryModel.MQNameListResult;
import MQApi.QueryModel.MQChannelStatusModel;
import MQApi.*;
import MQApi.Connection.MQConnection;
import MQApi.Enums.*;
import MQApi.Logs.LogWriter;
import MQApi.Models.ToolChannelStatusModel;
import MQApi.Models.ChannelStatusValueModel;
import MQApi.Models.Query.ConnectionDetailModel;
import MQApi.QueryModel.DetailModelCore;
import MQApi.QueryModel.MQChannelAuthListResult;
import MQApi.QueryModel.MQChannelAuthListResult.ChannelAuthDetailModel;
import MQApi.Result.Annotations.MQObjectListtAnnotation;
import MQApi.Result.Annotations.MQObjectPropertyAnnotation;
import MQApi.QueryModel.MQChannelListResult.ChannelDetailModel;
import MQApi.QueryModel.MQChannelPropertyModel;
import MQApi.QueryModel.MQChannelStatusListResult.ChannelStatusModel;
import MQApi.QueryModel.MQObjectPropertyModel;
import MQApi.QueryModel.MQQueueListResult.QueueDetailModel;
import MQApi.QueryModel.MQQueueStatusHandleListResult;
import MQApi.QueryModel.MQQueueStatusHandleListResult.QueueStatusHandleDetailModel;
import MQApi.QueryModel.MQQueueStatusListResult;
import MQApi.QueryModel.MQQueueStatusListResult.QueueStatusDetailModel;
import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQException;

import com.ibm.mq.MQQueueManager;
import java.io.IOException;

import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.pcf.PCFException;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author jzhou
 */
public class MQPCF {
    private static MQQueueListResult MQQueueListResultCache = null;
    private static MQChannelListResult MQChannelListResultCache = null;
    private static MQChannelAuthListResult MQChannelAuthListResultCache = null;

    //Queue   
    public static MQNameListResult GetQueueNameList(ConnectionDetailModel connectionDetail, QueueType type, String querySring){
     
        MQNameListResult result = new MQNameListResult();       
        try {
            MQQueueManager queueManager = MQConnection.GetMQQueueManager(connectionDetail);
            int queueType = ConstantConverter.ConvertQueueTypeToConstant(type);
            PCFMessageAgent agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q_NAMES);
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, querySring);
            pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, queueType);
            PCFMessage[] pcfResponse = null;
            pcfResponse = agent.send(pcfCmd);
            String[] names = (String[]) pcfResponse[0].getParameterValue(MQConstants.MQCACF_Q_NAMES);
            result.NameList = new ArrayList<String>();            
            result.NameList.addAll(Arrays.asList(names));
            //Object types =  pcfResponse[0].getParameterValue(MQConstants.MQIACF_Q_TYPES);
            result.QuerySuccess = true;
        }
        catch (Exception ex) {
                result.QuerySuccess = false;
                result.ErrorMessage = ex.getMessage();
        }
        return result;
    }
    
    public static String ResolveAliasBaseQueueName(MQQueueManager queueManager, String queueName){
        try {
            MQQueuePropertyModel queueProperties = MQPCF.GetQueueProperties(queueManager, queueName);
            if(ConstantConverter.ConvertConstantToQueueType(queueProperties.Type)  == QueueType.Alias){
                return queueProperties.BaseObjectName;
            }  
            return queueName;
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "ResolveAliasBaseQueueName", ex);
            return null;
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "ResolveAliasBaseQueueName", ex);
            return null;
        }
    }
          
    public static MQQueueListResult GetQueueList(MQQueueManager queueManager, String queueNameFilter, QueueType[] type, boolean loadNewData){
        if(loadNewData == true || (MQQueueListResultCache == null || MQQueueListResultCache.QuerySuccess == false)){
            return MQPCF.GetQueueList(queueManager,queueNameFilter, type);
        }
        else{
            return MQQueueListResultCache;
        }
    }
    
    public static MQQueueListResult GetQueueList(MQQueueManager queueManager, String queueNameFilter, QueueType[] type ){
        MQQueueListResult result = new MQQueueListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q);        
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueNameFilter);
            pcfCmd.addParameter(MQConstants.MQIA_Q_TYPE, MQConstants.MQQT_ALL );
            PCFMessage[] pcfResponse = agent.send(pcfCmd);           
            WriteToDetailModel(pcfResponse, result, QueueDetailModel.class, type);
            result.QuerySuccess = true;
        } catch (MQDataException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());
            LogWriter.WriteToLog("MQPCF", "GetQueueList", ex);
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetQueueList", ex);
        } 
        MQQueueListResultCache = result;
        disconnectAgent(agent);        
        return result;
    }
    
    public static MQQueuePropertyModel GetQueueProperties(MQQueueManager queueManager, String name) throws MQDataException, IOException{
        MQQueuePropertyModel model = null;
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q);        
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, name);
            agent.setCheckResponses(false);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            if(pcfResponse.length > 0 && pcfResponse[0].getCompCode() == 0){
                model = new MQQueuePropertyModel();
                readToPropertyModel(pcfResponse[0], model);
            }
        
        } catch (MQDataException ex) {
            disconnectAgent(agent);
            LogWriter.WriteToLog("MQPCF", "GetQueueProperties", ex);
            throw ex;
        } catch (IOException ex) {
            disconnectAgent(agent);
            LogWriter.WriteToLog("MQPCF", "GetQueueProperties", ex);
            throw ex;
        }
        disconnectAgent(agent);
        return model;
    }
        
    public static MQCommandResult UpdateQueueProperties(MQQueueManager queueManager, MQQueuePropertyModel model){
        return CreateOrModifyQueueProperties(queueManager, model, false);
    }
    
    public static MQCommandResult CreateQueue(MQQueueManager queueManager, MQQueuePropertyModel model){
        return CreateOrModifyQueueProperties(queueManager, model, true);
    }
           
    public static boolean CheckQueueExist(MQQueueManager queueManager, String name){
        try{
            MQQueuePropertyModel model = GetQueueProperties(queueManager, name);
            return model != null;
        }catch(Exception ex){
            return false;
        }
    }
          
    public static MQCommandResult ClearQueue(MQQueueManager queueManager, String queueName, boolean isAlias) {
        PCFMessageAgent agent = null;
        MQCommandResult result = new MQCommandResult();        
        try {

            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_CLEAR_Q);
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            assignCommandReturnResult(pcfResponse[0], result);
            
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "ClearQueue", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "ClearQueue",ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }   
    
    public static MQCommandResult DeleteQueue(MQQueueManager queueManager, String queueName, QueueType type){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_DELETE_Q);
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, queueName);
            if(type == QueueType.Local){
                pcfCmd.addParameter(MQConstants.MQIACF_PURGE, MQConstants.MQPO_YES);
            }
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            assignCommandReturnResult(pcfResponse[0], result);            
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "DeleteQueue", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "DeleteQueue", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
        
    }
    
    public static MQQueueStatusHandleListResult GetQueueStatusHandleList(MQQueueManager queueManager, String nameFilter){
        MQQueueStatusHandleListResult result = new MQQueueStatusHandleListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q_STATUS);        
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, nameFilter);
            pcfCmd.addParameter(MQConstants.MQIACF_Q_STATUS_TYPE, MQConstants.MQIACF_Q_HANDLE);
            agent.setCheckResponses(false);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);    
            if(pcfResponse.length > 0 && pcfResponse[0].getCompCode() != 2){
                WriteToDetailModel(pcfResponse, result, QueueStatusHandleDetailModel.class, null);
                result.QuerySuccess = true;
            }
            else{
                result.QuerySuccess = false;
                result.ErrorMessage = getMQReturnMessage(pcfResponse[0].getCompCode(), pcfResponse[0].getReason());
            }
        } catch (MQDataException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());
            LogWriter.WriteToLog("MQPCF", "GetQueueStatusHandleList", ex);
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetQueueStatusHandleList", ex);
        }
        disconnectAgent(agent);
        return result;
    }
    
    public static MQQueueStatusListResult GetQueueStatusList(MQQueueManager queueManager, String nameFilter){
        MQQueueStatusListResult result = new MQQueueStatusListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_Q_STATUS);        
            pcfCmd.addParameter(MQConstants.MQCA_Q_NAME, nameFilter);
            pcfCmd.addParameter(MQConstants.MQIACF_Q_STATUS_TYPE, MQConstants.MQIACF_Q_STATUS);
            agent.setCheckResponses(false);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);    
            if(pcfResponse.length > 0 && pcfResponse[0].getCompCode() != 2){
                WriteToDetailModel(pcfResponse, result, QueueStatusDetailModel.class, null);
                result.QuerySuccess = true;
            }
            else{
                result.QuerySuccess = false;
                result.ErrorMessage = getMQReturnMessage(pcfResponse[0].getCompCode(), pcfResponse[0].getReason());
            }
        } catch (MQDataException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());
            LogWriter.WriteToLog("MQPCF", "GetQueueStatusList", ex);
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetQueueStatusList", ex);
        }
        disconnectAgent(agent);
        return result;
    }
    
    
    //Channel
    public static MQChannelListResult GetChannelList(MQQueueManager queueManager, String channelNameFilter, ChannelType[] type, boolean loadNewData){
        if(loadNewData == true || (MQChannelListResultCache == null || MQChannelListResultCache.QuerySuccess == false)){
            return GetChannelList(queueManager, channelNameFilter, type);
        }
        else{
            return MQChannelListResultCache;
        }
    }
    
    public static MQChannelListResult GetChannelList(MQQueueManager queueManager, String channelNameFilter, ChannelType[] type){
        MQChannelListResult result = new MQChannelListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmdQueryDetail = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL);        
            pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelNameFilter);
            pcfCmdQueryDetail.addParameter(MQConstants.MQIACH_CHANNEL_TYPE, MQConstants.MQCHT_ALL );
            PCFMessage[] pcfResponseDetail = agent.send(pcfCmdQueryDetail); 
            WriteToDetailModel(pcfResponseDetail, result, ChannelDetailModel.class, type);       
            MQChannelStatusListResult channelStatus = GetChannelStatusList(queueManager, "*", null, false);
            Hashtable<String, ChannelStatusValueModel> statusTable = new Hashtable<String, ChannelStatusValueModel>();
            WriteToHashtable(channelStatus.DataModels, statusTable);
            GetCombinedChannelDetailResult(result, statusTable);
            result.QuerySuccess = true;
        
        } catch (MQDataException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());            
            LogWriter.WriteToLog("MQPCF", "GetChannelList", ex);
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetChannelList", ex);
        }
        MQChannelListResultCache = result;
        disconnectAgent(agent);
        
        return result;
    }
 
    public static MQChannelStatusListResult GetChannelStatusList(MQQueueManager queueManager, String nameFilter, ChannelType[] type, boolean getSaved){
        MQChannelStatusListResult result = new MQChannelStatusListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS);        
            pcfCmd.addParameter(MQConstants.MQCACH_CHANNEL_NAME, nameFilter);
            if(getSaved){
                pcfCmd.addParameter(MQConstants.MQIACH_CHANNEL_INSTANCE_TYPE, MQConstants.MQOT_SAVED_CHANNEL);
            }
            agent.setCheckResponses(false);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);    
            if(pcfResponse.length > 0 && pcfResponse[0].getCompCode() != 2){
                WriteToDetailModel(pcfResponse, result, ChannelStatusModel.class, type);;
                result.QuerySuccess = true;
            }
            else{
                result.QuerySuccess = false;
                result.ErrorMessage = getMQReturnMessage(pcfResponse[0].getCompCode(), pcfResponse[0].getReason());
            }
        } catch (MQDataException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());
            LogWriter.WriteToLog("MQPCF", "GetchannelStatusList", ex);
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetchannelStatusList", ex);
        }
        disconnectAgent(agent);
        return result;
    }
        
    public static MQChannelStatusModel GenerateChannelStatus(MQQueueManager queueManager, String querySring){
         MQChannelStatusModel result = new MQChannelStatusModel();
         PCFMessageAgent agent = null;
        try {           
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL_STATUS);
            pcfCmd.addParameter(MQConstants.MQCACH_CHANNEL_NAME, querySring);
            PCFMessage[] pcfResponse = null;
            pcfResponse = agent.send(pcfCmd);
            result.QuerySuccess = true;
            result.ChannelStatusList = ConveryToChModel(pcfResponse);    
        } catch (Exception ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GenerateChannelStatus", ex);           
        }  
        disconnectAgent(agent); 
        return result;
    }   
    
    public static boolean CheckChannelExist(MQQueueManager queueManager, String name){
            try{
                MQChannelPropertyModel model = GetChannelProperties(queueManager, name);
                return model != null;
            }catch(Exception ex){
                return false;
            }
    }
    
    public static MQChannelPropertyModel GetChannelProperties(MQQueueManager queueManager, String name) throws MQDataException, IOException{
        MQChannelPropertyModel model = null;
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHANNEL);        
            pcfCmd.addParameter(MQConstants.MQCACH_CHANNEL_NAME, name);
            agent.setCheckResponses(false);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            if(pcfResponse.length > 0 && pcfResponse[0].getCompCode() == 0){
                model = new MQChannelPropertyModel();
                readToPropertyModel(pcfResponse[0], model);
            }
        
        } catch (MQDataException ex) {
            disconnectAgent(agent);
            LogWriter.WriteToLog("MQPCF", "GetChannelProperties", ex);
            throw ex;
        } catch (IOException ex) {
            disconnectAgent(agent);
            LogWriter.WriteToLog("MQPCF", "GetChannelProperties", ex);
            throw ex;
        }
        disconnectAgent(agent);
        return model;
    }
    
    public static MQCommandResult UpdateChannelProperties(MQQueueManager queueManager, MQChannelPropertyModel model){
        return CreateOrModifyChannelProperties(queueManager, model, false);
    }
    
    public static MQCommandResult CreateChannel(MQQueueManager queueManager, MQChannelPropertyModel model){
        return CreateOrModifyChannelProperties(queueManager, model, true);
    }
    
    public static MQCommandResult StartChannel(MQQueueManager queueManager, String name){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmdQueryDetail = new PCFMessage(MQConstants.MQCMD_START_CHANNEL);
            pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CHANNEL_NAME, name);
            PCFMessage[] pcfResponse = agent.send(pcfCmdQueryDetail);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "StartChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "StartChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }

    public static MQCommandResult StopChannel(MQQueueManager queueManager, String name, ChannelStatusType toStatus, ChannelStopType stopType, String queueManagerName, String connectionName){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        if(toStatus != ChannelStatusType.Inactive && toStatus != ChannelStatusType.Stopped){
            result.QuerySuccess = false;
            result.ErrorMessage = "Status error: must be Inactive or Stopped";
            return result;
        }
        try {
            agent = new PCFMessageAgent(queueManager);
            agent.setCheckResponses(false);
            PCFMessage pcfCmdQueryDetail = new PCFMessage(MQConstants.MQCMD_STOP_CHANNEL);
            pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CHANNEL_NAME, name);
            pcfCmdQueryDetail.addParameter(MQConstants.MQIACH_CHANNEL_STATUS, toStatus == ChannelStatusType.Inactive ? MQConstants.MQCHS_INACTIVE : MQConstants.MQCHS_STOPPED);
            switch(stopType){
                case Force:
                    pcfCmdQueryDetail.addParameter(MQConstants.MQIACF_MODE, MQConstants.MQMODE_FORCE);
                    break;
                case Quiesec:
                    pcfCmdQueryDetail.addParameter(MQConstants.MQIACF_MODE, MQConstants.MQMODE_QUIESCE);
                    break;
                case Terminate:
                    pcfCmdQueryDetail.addParameter(MQConstants.MQIACF_MODE, MQConstants.MQMODE_TERMINATE);
                    break;
            }
            if(toStatus == ChannelStatusType.Inactive){
                if(queueManagerName != null && !queueManagerName.isEmpty()){
                    pcfCmdQueryDetail.addParameter(MQConstants.MQCA_Q_MGR_NAME, queueManagerName);
                }
                if(connectionName != null && !connectionName.isEmpty()){
                    pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CONNECTION_NAME, connectionName);
                }
            }
            PCFMessage[] pcfResponse = agent.send(pcfCmdQueryDetail);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "StopChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "StopChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);         
        return result;
    }
    
    public static MQCommandResult ResetChannel(MQQueueManager queueManager, String name, Integer seq){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmdQueryDetail = new PCFMessage(MQConstants.MQCMD_RESET_CHANNEL);
            pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CHANNEL_NAME, name);
            pcfCmdQueryDetail.addParameter(MQConstants.MQIACH_MSG_SEQUENCE_NUMBER, seq);
            PCFMessage[] pcfResponse = agent.send(pcfCmdQueryDetail);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "ResetChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "ResetChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }
    
    public static MQCommandResult ResolveChannel(MQQueueManager queueManager, String name, ChannelResolveType type){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmdQueryDetail = new PCFMessage(MQConstants.MQCMD_RESOLVE_CHANNEL);
            pcfCmdQueryDetail.addParameter(MQConstants.MQCACH_CHANNEL_NAME, name);
            pcfCmdQueryDetail.addParameter(MQConstants.MQIACH_IN_DOUBT, type == ChannelResolveType.Backout ? MQConstants.MQIDO_BACKOUT : MQConstants.MQIDO_COMMIT);
            PCFMessage[] pcfResponse = agent.send(pcfCmdQueryDetail);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "ResolveChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "ResolveChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }
    
    public static MQCommandResult DeleteChannel (MQQueueManager queueManager, String channelName){
        MQCommandResult result = new MQCommandResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_DELETE_CHANNEL);
            pcfCmd.addParameter(MQConstants.MQCACH_CHANNEL_NAME, channelName);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            assignCommandReturnResult(pcfResponse[0], result);
            
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "DeleteChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "DeleteChannel", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
        
    }
    
    //Channel Auth
    
    public static MQChannelAuthListResult GetChannelAuthList(MQQueueManager queueManager, String NameFilter, QueueType[] type, boolean loadNewData){
        if(loadNewData == true || (MQQueueListResultCache == null || MQQueueListResultCache.QuerySuccess == false)){
            return MQPCF.GetChannelAuthList(queueManager,NameFilter, type);
        }
        else{
            return MQChannelAuthListResultCache;
        }
    }
    
    public static MQChannelAuthListResult GetChannelAuthList(MQQueueManager queueManager, String queueNameFilter, QueueType[] type ){
        MQChannelAuthListResult result = new MQChannelAuthListResult();
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_INQUIRE_CHLAUTH_RECS);        
            pcfCmd.addParameter(MQConstants.MQCACH_CHANNEL_NAME, queueNameFilter);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);        
            WriteToDetailModel(pcfResponse, result, ChannelAuthDetailModel.class, null); 
            result.QuerySuccess = true;
        } catch (MQDataException ex) {
            if(ex.getReason() == MQConstants.MQRCCF_CFH_COMMAND_ERROR ||ex.getReason() == MQConstants.MQRCCF_MQOPEN_FAILED ){
                result.QuerySuccess = true;
                disconnectAgent(agent);
                return result;
            }
            else{
                result.QuerySuccess = false;
                result.ErrorMessage = MQUtility.getMQReturnMessage(ex.getCompCode(), ex.getReason());
                LogWriter.WriteToLog("MQPCF", "GetChannelAuthList", ex);
            }
        } catch (IOException ex) {
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
            LogWriter.WriteToLog("MQPCF", "GetChannelAuthList", ex);
        } 
        MQChannelAuthListResultCache = result;
        disconnectAgent(agent);        
        return result;
    }
    
    //private
    private static <T, Y> void WriteToDetailModel(PCFMessage[] pcfResponse , T model, Class<Y> modelClass,  Object[] type){
        for(PCFMessage response : pcfResponse){     
            if(response.getCompCode() == 0){
                try {
                    Y detailModel = modelClass.getDeclaredConstructor(model.getClass()).newInstance(model);
                    setModelValue(detailModel, response);
                    if(type == null || Arrays.asList(type).contains(detailModel.getClass().getField("Type").get(detailModel))){                       
                        if(detailModel.getClass().getField("Name").get(detailModel) != null){
                            Object modelArray = model.getClass().getField("DataModels").get(model);
                            ((DetailModelCore)detailModel).setDisplayValues();
                            ((ArrayList<Y>)modelArray).add(detailModel);
                        }
                    }
                } catch (InstantiationException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());
                } catch (IllegalAccessException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());;
                } catch (NoSuchFieldException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());
                } catch (SecurityException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());;
                } catch (InvocationTargetException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());;
                } catch (NoSuchMethodException ex) {
                    LogWriter.WriteToLog(ex.fillInStackTrace());
                }
            }
        }
    }
    
    private static void setModelValue(Object model, PCFMessage response){
        for(Field field : model.getClass().getFields()){
            if(field.getAnnotation(MQObjectListtAnnotation.class).GetValue() == true){
                int mqConstant = field.getAnnotation(MQObjectListtAnnotation.class).MQConstant();
                VariableType varType = field.getAnnotation(MQObjectListtAnnotation.class).VarType();
                SetFieldValue(field, response, model, mqConstant,varType);
            }
        }        
    }
    
    private static void SetFieldValue(Field field, PCFMessage response, Object model, int mqConstant, VariableType varType){
        try{
           switch(varType){
               case QueueType :{
                   QueueType value = ConstantConverter.ConvertConstantToQueueType((Integer) response.getParameterValue(mqConstant));
                   field.set(model, value);
                   break;
               }
               case ChannelType :{
                   ChannelType value = ConstantConverter.ConvertConstantToChannelType((Integer) response.getParameterValue(mqConstant));
                   field.set(model, value);
                   break;
               }
               case ChannelStatusType :{
                   ChannelStatusType value = ConstantConverter.ConvertConstantToChannelStatusType((Integer) response.getParameterValue(mqConstant));
                   field.set(model, value);
                   break;
               }
               case ChannelAuthType :{
                   ChannelAuthType value = ConstantConverter.ConvertConstantToChannelAuthType((Integer) response.getParameterValue(mqConstant));
                   field.set(model, value);
                   break;
               }
               case QueueDefinitionType :{
                   Object value = response.getParameterValue(mqConstant);
                   if(value != null){
                       int intValue = Integer.parseInt(value.toString());
                       String stringValue = null;
                       switch(intValue){
                           case MQConstants.MQQDT_PREDEFINED:
                               stringValue = "Predefined permanent queue";
                               break;
                           case MQConstants.MQQDT_PERMANENT_DYNAMIC:
                               stringValue = "Dynamically defined permanent queue";
                               break;
                           case MQConstants.MQQDT_SHARED_DYNAMIC:
                               stringValue = "Dynamically defined shared queue";
                               break;
                           case MQConstants.MQQDT_TEMPORARY_DYNAMIC:
                               stringValue = "Dynamically defined temporary queue";
                               break;
                       }
                       field.set(model, stringValue);
                   }
                   else{
                       field.set(model, null);
                   }
                   break;
               }
               case Number :{
                   Object value = response.getParameterValue(mqConstant);
                   if(value != null)
                        field.set(model, value);
                   else
                       field.set(model, null);
                   break;
               }
               case Text :{
                   Object value = response.getParameterValue(mqConstant);
                   if(value != null){
                       field.set(model, value.toString().trim());
                   }
                   else
                       field.set(model, null);
                   break;
               }
               case TextArray :{
                   Object value = response.getParameterValue(mqConstant);
                   if(value != null){
                       field.set(model, value);
                   }
                   else
                       field.set(model, null);
                   break;
               }
               case TrueFalse :{
                   String[] displayValues = field.getAnnotation(MQObjectListtAnnotation.class).TrueFalseDisplayValue();
                   Object value = response.getParameterValue(mqConstant);
                   if(value != null){
                        int objectValue = Integer.parseInt(value.toString());
                        field.set(model, objectValue == 1 ? displayValues[1] : displayValues[0]);
                   }
                   else{
                       field.set(model, null);
                   }
                   break;
               }
           }
       }catch (Exception ex){

       }
   }       
    
    private static ArrayList<ToolChannelStatusModel> ConveryToChModel(PCFMessage[] pcfResponse){
        ArrayList<ToolChannelStatusModel> models = new ArrayList<ToolChannelStatusModel>();
        for(PCFMessage message : pcfResponse){
            try {
                ToolChannelStatusModel model = new ToolChannelStatusModel();
                model.Value = new ChannelStatusValueModel();
                //model.Application = (String) message.getStringParameterValue(MQConstants.MQCACH_REMOTE_APPL_TAG);
                model.ChannelName = (String) message.getStringParameterValue(MQConstants.MQCACH_CHANNEL_NAME).trim();
                model.IpAddress = (String) message.getStringParameterValue(MQConstants.MQCACH_CONNECTION_NAME);
                model.Value.MSGS =  ((Integer) (message.getParameterValue(MQConstants.MQIACH_MSGS)));
                int a = (Integer) message.getParameterValue(MQConstants.MQIACH_CHANNEL_STATUS);
                model.Value.Status = ConstantConverter.ConvertConstantToChannelStatusType((Integer) message.getParameterValue(MQConstants.MQIACH_CHANNEL_STATUS));
                models.add(model);
            } catch (PCFException ex) {
                Logger.getLogger(MQPCF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return models;
    }
    
    private static void WriteToHashtable(ArrayList<ChannelStatusModel> chResult, Hashtable<String, ChannelStatusValueModel> table){
        for(ChannelStatusModel m : chResult){
            if(m.Name != null){
                String key = m.Name.trim();
                ChannelStatusValueModel value = new ChannelStatusValueModel();
                value.Status = m.Status;
                value.MSGS = m.MSGS;
                if(table.containsKey(key)){
                    value.MSGS = (table.get(key)).MSGS + value.MSGS;
                    table.put(key, value);
                }
                else{
                   table.put(key, value); 
                }
            }
        }
    }
    
    private static void GetCombinedChannelDetailResult(MQChannelListResult result, Hashtable<String, ChannelStatusValueModel> statusTable){
        for(ChannelDetailModel channelDetail : result.DataModels){
            if(channelDetail.Name != null){
                if(statusTable.containsKey(channelDetail.Name)){
                    channelDetail.Status = statusTable.get(channelDetail.Name).Status;
                }
                else{
                    channelDetail.Status = ChannelStatusType.Inactive;
                }
            }
        }
    }
    
    private static void disconnectAgent(PCFMessageAgent agent){
        if(agent != null){
            try {
                agent.disconnect();
                agent = null;
            } catch (MQDataException ex) {
                LogWriter.WriteToLog("MQPCF", "disconnectAgent", ex); 
            }
        }
    }
    
    private static void readToPropertyModel(PCFMessage message, Object model ){
        for(Field field : model.getClass().getFields()){
            int mqConstant = field.getAnnotation(MQObjectPropertyAnnotation.class).MQConstant();
            VariableType varType = field.getAnnotation(MQObjectPropertyAnnotation.class).VarType();
            SetFieldValue(field, message, model, mqConstant,varType);
        }        
    }    

    private static MQCommandResult CreateOrModifyQueueProperties(MQQueueManager queueManager, MQQueuePropertyModel model, boolean isCreate){
        PCFMessageAgent agent = null;
        MQCommandResult result = new MQCommandResult();
        try {           
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(isCreate ? MQConstants.MQCMD_CREATE_Q : MQConstants.MQCMD_CHANGE_Q);
            addParameters(model, pcfCmd);
            if(!isCreate){
                pcfCmd.addParameter(MQConstants.MQIACF_FORCE, MQConstants.MQFC_YES); //always force change
            }
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "CreateOrModifyQueueProperties", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "CreateOrModifyQueueProperties", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }
    
    private static MQCommandResult CreateOrModifyChannelProperties(MQQueueManager queueManager, MQChannelPropertyModel model, boolean isCreate){
        PCFMessageAgent agent = null;
        MQCommandResult result = new MQCommandResult();
        try {           
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(isCreate ? MQConstants.MQCMD_CREATE_CHANNEL : MQConstants.MQCMD_CHANGE_CHANNEL);
            addParameters(model, pcfCmd);
            PCFMessage[] pcfResponse = agent.send(pcfCmd);
            assignCommandReturnResult(pcfResponse[0], result);
        } catch (MQDataException ex) {
            LogWriter.WriteToLog("MQPCF", "CreateOrModifyChannelProperties", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
        } catch (IOException ex) {
            LogWriter.WriteToLog("MQPCF", "CreateOrModifyChannelProperties", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = ex.getMessage();
        }
        disconnectAgent(agent);
        return result;
    }
    
    private static void addParameters(Object model, PCFMessage pcfCmd ){
        for(Field field : model.getClass().getFields()){
            try {
                int parameter = field.getAnnotation(MQObjectPropertyAnnotation.class).MQConstant();
                VariableType variableType = field.getAnnotation(MQObjectPropertyAnnotation.class).VarType();
                boolean sendToPCF = field.getAnnotation(MQObjectPropertyAnnotation.class).SendToPCF();
                Object value = field.get(model);
                if(value != null && sendToPCF){
                    switch(variableType){
                        case Text :
                            pcfCmd.addParameter(parameter, value.toString());
                            break;
                        case TextArray :
                            pcfCmd.addParameter(parameter, (String[])value);
                            break;
                        case Number:    
                            Integer paraValue = Integer.parseInt(value.toString());
                            pcfCmd.addParameter(parameter, paraValue);
                            break;
                    }
                }
            } catch (IllegalArgumentException ex) {
                LogWriter.WriteToLog("MQPCF", "addParameters", ex);
            } catch (IllegalAccessException ex) {
                LogWriter.WriteToLog("MQPCF", "addParameters", ex);
            }
        }        
    }
    
    private static void assignCommandReturnResult(PCFMessage pcfResponse,MQCommandResult result){
        if(pcfResponse.getCompCode() == 0){
            result.QuerySuccess = true;
            result.ReturnMessage = getMQReturnMessage(pcfResponse.getCompCode(), pcfResponse.getReason());
        }
        else{
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(pcfResponse.getCompCode(), pcfResponse.getReason());                
        }    
    }
    
    private static String getMQReturnMessage(int compCode, int reason){
        String msg = MQConstants.lookupCompCode(compCode) + " : (" + MQConstants.lookupReasonCode(reason) +" )";
        msg = msg.replace("_", " ");
        msg =  msg.toLowerCase();
        msg = msg.replace("mqcc", "Command");
        msg = msg.replace("mqrccf", "");
        msg = msg.replace("mqrc", "");
        return msg;
    }
    

}
