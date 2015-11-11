/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Utilities;

import MQApi.Connection.MQConnection;
import MQApi.Logs.LogWriter;
import MQApi.Models.MQMessageIdModel;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQMessageListResult;
import MQApi.QueryModel.MQMessageListResult.MessageDetailModel;
import UI.Dialogs.MessageEditDialog;
import UI.Helpers.DateTimeHelper;
import UI.Helpers.TreeHelper;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQDLH;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.MQHeaderList;
import com.ibm.mq.headers.MQRFH2;
import com.ibm.mq.headers.internal.Header;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.logging.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JProgressBar;

/**
 *
 * @author jzhou
 */
public class MQUtility {
    
    private static int messageListDataLength = 50;

    public static boolean PutMessage(MQQueueManager queueManager, String queueName, MQMessage message){    
        MQQueue queue = null;
        try {
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_OUTPUT);
            queue.put(message);
            closeQueue(queue);
        } catch (Exception ex) {
            LogWriter.WriteToLog("MQUtility", "PutMessage", ex);
            closeQueue(queue);
            return false;
        }
        return true;
    }  
    
    public static int GetQueueDepth(MQQueueManager queueManager, String queueName){
        MQQueue queue = null;
        int result = 0;
        try {
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE);
            result = queue.getCurrentDepth();
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "GetQueueDepth", ex);
        }
        closeQueue(queue);
        return result;
    }
    
    public static MQMessage GetMessage(MQQueueManager queueManager, String queueName, MQMessageIdModel messageIdModel, boolean matchPosition) throws MQException{
        MQQueue queue = null;
        MQMessage message = new MQMessage();
        message.messageId = messageIdModel.MessageId;
        message.correlationId = messageIdModel.CorrelationdId;
        MQGetMessageOptions options = new MQGetMessageOptions();
        options.options = CMQC.MQGMO_BROWSE_NEXT;
        options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;
        try {                 
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_OUTPUT);
            if(matchPosition){
                setQueueCursor(queue, messageIdModel.position);
            }
            queue.get(message, options);

        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "GetMessage", ex);
            closeQueue(queue);
            throw ex;           
        }
        closeQueue(queue);
        return message;                
    }
    
    public static MQMessage GetMessageWithInterval(MQQueueManager queueManager, String queueName, int interval) throws MQException{
        MQQueue queue = null;
        MQMessage message = new MQMessage();
        MQGetMessageOptions options = new MQGetMessageOptions();
        options.options = CMQC.MQGMO_WAIT | CMQC.MQGMO_NO_SYNCPOINT;
        options.waitInterval  = interval;
        try {                 
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_OUTPUT | CMQC.MQOO_INPUT_SHARED);
            queue.get(message, options);

        } catch (MQException ex) {
            //LogWriter.WriteToLog("MQUtility", "GetMessage", ex);
            closeQueue(queue);
            throw ex;           
        }
        closeQueue(queue);
        return message;                
    }
    
    public static void UpdateMessage(MQQueueManager queueManager, String queueName, MQMessage message) throws MQException{
        MQQueue queue = null;
        MQPutMessageOptions options = new MQPutMessageOptions();
        options.options = CMQC.MQPMO_SET_ALL_CONTEXT;
        MQMessageIdModel oldId = new MQMessageIdModel();
        oldId.CorrelationdId = message.correlationId;
        oldId.MessageId = message.messageId;
        try{
            ComsumeSelectedMessages(queueManager, queueName, oldId);
        }
        catch(MQException ex){
            LogWriter.WriteToLog("MQUtility", "UpdateMessage", ex);
            closeQueue(queue);
            throw ex;
        }
        try {            
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_OUTPUT | CMQC.MQOO_SET_ALL_CONTEXT);
            queue.put(message, options);
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "UpdateMessage", ex);
            closeQueue(queue);
            throw ex;           
        }
        closeQueue(queue);              
    }

    public static void UpdateMessageToSamePosition(MQQueueManager queueManager, String queueName, MQMessage message) throws MQException, Exception{
        MQMessageIdModel id = new MQMessageIdModel();
        id.CorrelationdId = message.correlationId;
        id.MessageId = message.messageId;
        String fileName = "UpdateTemp.msg";
        BackupAndRemoveMessageFromSelectedPosition(queueManager, queueName, fileName, id);
        try{
            UpdateMessage(queueManager, queueName, message);
        }
        catch(MQException ex){
            LogWriter.WriteToLog("MQUtility", "UpdateMessageToSamePosition", ex);
            RestoreMessageFromFile(queueManager, queueName, fileName, null, false, false);
            removeFile(fileName);  
            throw ex;
        }
        RestoreMessageFromFile(queueManager, queueName, fileName, null, false, false);
        removeFile(fileName);            
    }
    
    public static MQMessageListResult GetMessageList(MQQueueManager queueManager, String queueName, int numOfMessageRequired,boolean isAlias){
        return GetMessageList(queueManager, queueName, numOfMessageRequired, isAlias, null, 0);
    }
          
    public static MQMessageListResult GetMessageList(MQQueueManager queueManager, String queueName, int numOfMessageRequired,boolean isAlias, MQMessageIdModel fromId, int fromPosition){
        MQMessageListResult result = new MQMessageListResult();
        MQQueue queue = null;
        try {
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE);
            int queueDepth = queue.getCurrentDepth();
            int index = numOfMessageRequired > 0 &&  numOfMessageRequired < (queueDepth - fromPosition + 1) ? numOfMessageRequired : (queueDepth - fromPosition + 1);
            int position = fromPosition;
            MQMessage message = new MQMessage();
            MQGetMessageOptions options = new MQGetMessageOptions();
            options.options = CMQC.MQGMO_BROWSE_NEXT;
            options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;
            if(fromId != null){
                message.messageId = fromId.MessageId;
                message.correlationId = fromId.CorrelationdId;
                try{
                    queue.get(message, options);
                }
                catch(MQException ex){
                    if(!setQueueCursor(queue, fromPosition)){
                        closeQueue(queue);
                        return result;
                    }                                        
                }
            }
            while(index > 0){ 
                resetMessage(message);
                try{
                    queue.get(message, options);
                }
                catch(MQException ex){
                    if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                        break;
                    }
                    else{
                        LogWriter.WriteToLog("MQUtility", "GetMessageList", ex);
                        throw ex;
                    }
                }
                index--;
                MessageDetailModel model = turnToMessageModel(result, message, position);
                result.DataModels.add(model);
                position ++;
            }            
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "GetMessageList", ex);
            result.QuerySuccess = false;
            result.ErrorMessage = getMQReturnMessage(ex.getCompCode(), ex.getReason());
            closeQueue(queue);
            return result;
        }
        closeQueue(queue);
        result.QuerySuccess = true;
        return result;
    }

    public static void ComsumeMessagesThread(MQQueueManager queueManager, String queueName, boolean forceOpenGet, boolean isAlias, Observer observer){
        MQQueue queue = null;
        try {
            int openQueueOptions;
            
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            openQueueOptions = forceOpenGet == true? CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_SET : CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED;
            queue = queueManager.accessQueue(queueName, openQueueOptions);
            boolean isGetOpen = queue.getInhibitGet() == MQConstants.MQQA_GET_ALLOWED;
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_ALLOWED);
            }
            MQGetMessageOptions options = new MQGetMessageOptions();
            MQMessage message = new MQMessage();
            options.matchOptions = MQConstants.MQMO_NONE;
            options.options = CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
            while(true){               
                try{
                    queue.get(message, options, 1);
                }
                catch(MQException ex){
                    if(ex.getReason() == MQConstants.MQRC_TRUNCATED_MSG_ACCEPTED){  
                        observer.Action();
                        continue;
                    }  
                    else if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                        break;
                    }
                    else{
                        LogWriter.WriteToLog("MQUtility", "ComsumeAllMessages",ex);
                        throw ex;
                    }
                }               
            }
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_INHIBITED);
            }
            closeQueue(queue);
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "ComsumeAllMessages",ex);
            closeQueue(queue);
        }        
    }
    public static void ComsumeAllMessagesMultiThread(MQQueueManager queueManager, String queueName, JProgressBar progressBar, boolean forceOpenGet, boolean isAlias) throws MQException{
        MQQueue queue = null;
        try{
            int openQueueOptions;
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            openQueueOptions = forceOpenGet == true? CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_SET : CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED;
            queue = queueManager.accessQueue(queueName, openQueueOptions);
            final int queueDepth = queue.getCurrentDepth();
            Processed processed = new Processed();
            processed.MessageDeleted = 0;
            ThreadFinish thread1Finish = new ThreadFinish();
            thread1Finish.IsFinish = false;
            ThreadFinish thread2Finish = new ThreadFinish();
            thread2Finish.IsFinish = false;
            ThreadFinish thread3Finish = new ThreadFinish();
            thread3Finish.IsFinish = false;
            ThreadFinish thread4Finish = new ThreadFinish();
            thread4Finish.IsFinish = false;
            
            MQQueueManager queueManager1 = MQConnection.GetMQQueueManager(TreeHelper.GetCurrentConnectionDetail(null));
            MQQueueManager queueManager2 = MQConnection.GetMQQueueManager(TreeHelper.GetCurrentConnectionDetail(null));
            MQQueueManager queueManager3 = MQConnection.GetMQQueueManager(TreeHelper.GetCurrentConnectionDetail(null));
            MQQueueManager queueManager4 = MQConnection.GetMQQueueManager(TreeHelper.GetCurrentConnectionDetail(null));
            
            ObserverTask observerTask1 = new ObserverTask(progressBar,queueDepth, processed);
            ConsumeTask task1 = new ConsumeTask(queueManager1, queueName, forceOpenGet, isAlias, observerTask1, thread1Finish);
            Thread thread1 = new Thread(task1);
            
            ObserverTask observerTask2 = new ObserverTask(progressBar,queueDepth, processed);
            ConsumeTask task2 = new ConsumeTask(queueManager2, queueName, forceOpenGet, isAlias, observerTask2, thread2Finish);
            Thread thread2 = new Thread(task2);
  
            ObserverTask observerTask3 = new ObserverTask(progressBar,queueDepth, processed);
            ConsumeTask task3 = new ConsumeTask(queueManager3, queueName, forceOpenGet, isAlias, observerTask3, thread3Finish);
            Thread thread3 = new Thread(task3);
            
            ObserverTask observerTask4 = new ObserverTask(progressBar,queueDepth, processed);
            ConsumeTask task4 = new ConsumeTask(queueManager4, queueName, forceOpenGet, isAlias, observerTask4, thread4Finish);
            Thread thread4 = new Thread(task4);
            
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    
                }
                if(thread1Finish.IsFinish && thread2Finish.IsFinish && thread3Finish.IsFinish && thread4Finish.IsFinish){
                    progressBar.setValue(100);
                    break;
                }
            }
        }
        catch (MQException ex){
            ComsumeAllMessages(queueManager,queueName, progressBar, forceOpenGet, isAlias );
        }
    }
    public static void ComsumeAllMessages(MQQueueManager queueManager, String queueName, JProgressBar progressBar, boolean forceOpenGet, boolean isAlias) throws MQException{
        MQQueue queue = null;
        try {
            int openQueueOptions;
            
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            openQueueOptions = forceOpenGet == true? CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_SET : CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED;
            queue = queueManager.accessQueue(queueName, openQueueOptions);
            boolean isGetOpen = queue.getInhibitGet() == MQConstants.MQQA_GET_ALLOWED;
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_ALLOWED);
            }
            int queueDepth = queue.getCurrentDepth();
            int index = queueDepth;
            MQGetMessageOptions options = new MQGetMessageOptions();
            MQMessage message = new MQMessage();
            options.matchOptions = MQConstants.MQMO_NONE;
            options.options = CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
            while(index > 0){               
                try{
                    index--;
                    if(progressBar != null){
                        int value = ((queueDepth - index)*100)/queueDepth;
                        progressBar.setValue(value);
                    }
                    queue.get(message, options, 1);
                }
                catch(MQException ex){
                    if(ex.getReason() == MQConstants.MQRC_TRUNCATED_MSG_ACCEPTED){                        
                        continue;
                    }  
                    else if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                        break;
                    }
                    else{
                        LogWriter.WriteToLog("MQUtility", "ComsumeAllMessages",ex);
                        throw ex;
                    }
                }               
            }
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_INHIBITED);
            }
            closeQueue(queue);
            if(progressBar != null){
                progressBar.setValue(100);
            }
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "ComsumeAllMessages",ex);
            closeQueue(queue);
            throw ex;
        }

    }
   
    public static void ComsumeAllMessagesWithFilter(MQQueueManager queueManager, String queueName, JProgressBar progressBar, boolean forceOpenGet, boolean isAlias, String stringfilter) throws MQException{
        MQQueue queue = null;
        try {
            int openQueueOptions;
            
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            openQueueOptions = forceOpenGet == true? CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_SET : CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED;
            queue = queueManager.accessQueue(queueName, openQueueOptions);
            int queueDepth = queue.getCurrentDepth();
            int index = queueDepth;
            MQGetMessageOptions options = new MQGetMessageOptions();
            MQMessage message = new MQMessage();
            options.matchOptions = MQConstants.MQMO_NONE;
            options.options = CMQC.MQGMO_BROWSE_NEXT;
            ArrayList<MQMessageIdModel>ids = new ArrayList<MQMessageIdModel>();
            if(progressBar != null){
                progressBar.setValue(0);
            }
            while(index > 0){               
                try{
                    index--;
                    options.options = CMQC.MQGMO_BROWSE_NEXT;
                    queue.get(message, options);
                    String content = message.readStringOfByteLength(message.getDataLength());
                    if(content.contains(stringfilter)){
                        resetMessage(message);
                        options.options = CMQC.MQGMO_MSG_UNDER_CURSOR;
                        queue.get(message, options);
                    }
                    resetMessage(message);
                }
                 catch (IOException ex) {
                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
                }               
            }
            closeQueue(queue);
            ComsumeSelectedMessages(queueManager, queueName, ids, progressBar, forceOpenGet, isAlias);
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "ComsumeAllMessages",ex);
            closeQueue(queue);
            throw ex;
        }
        if(progressBar != null){
            progressBar.setValue(100);
        }
    }    
    public static void ComsumeSelectedMessages(MQQueueManager queueManager, String queueName, MQMessageIdModel id) throws MQException{
        ArrayList<MQMessageIdModel> ids = new ArrayList<MQMessageIdModel>();
        ids.add(id);
        queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
        ComsumeSelectedMessages(queueManager, queueName, ids, null, false, false);
    }
    
    public static void ComsumeSelectedMessages(MQQueueManager queueManager, String queueName, ArrayList<MQMessageIdModel>ids, JProgressBar progressBar, boolean forceOpenGet, boolean isAlias) throws MQException {
        MQQueue queue = null;
        try {
            int openQueueOptions;
            if(isAlias){
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            }
            openQueueOptions = forceOpenGet == true? CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_SET : CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED;
            queue = queueManager.accessQueue(queueName, openQueueOptions);
            boolean isGetOpen = queue.getInhibitGet() == MQConstants.MQQA_GET_ALLOWED;
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_ALLOWED);
            }
            int i = 1;
            MQMessage message = new MQMessage();
            MQGetMessageOptions options = new MQGetMessageOptions();
            for(MQMessageIdModel id : ids){
                options.options = CMQC.MQGMO_ACCEPT_TRUNCATED_MSG;
                options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;                
                message.messageId = id.MessageId;
                message.correlationId = id.CorrelationdId;
                
                if(progressBar != null){
                    int value = i*100/ids.size();
                    progressBar.setValue(value);
                }
                i++;
                try{
                    queue.get(message, options, 1);
                }
                catch(MQException ex){
                    if(ex.getReason() == MQConstants.MQRC_TRUNCATED_MSG_ACCEPTED || ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                        continue;
                    }   
                    else{
                        throw ex;
                    }
                }
            }
            if(forceOpenGet == true && isGetOpen == false){
                queue.setInhibitGet(MQConstants.MQQA_GET_INHIBITED);
            }
            closeQueue(queue);
            if(progressBar != null){
                progressBar.setValue(100);
            }
        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "ComsumeSelectedMessages",ex);
            closeQueue(queue);
            throw ex;
        }

    }
    
    public static String getMQReturnMessage(int compCode, int reason){
            String msg = MQConstants.lookupCompCode(compCode) + " : (" + MQConstants.lookupReasonCode(reason) +" )";
            msg = msg.replace("_", " ");
            msg =  msg.toLowerCase();
            msg = msg.replace("mqcc", "Command");
            msg = msg.replace("mqrccf", "");
            msg = msg.replace("mqrc", "");
            return msg;
    }

    public static void BackupMessageToFile(MQQueueManager queueManager, String queueName, String filePath, JProgressBar progressBar, ArrayList<MQMessageIdModel>ids, boolean isCompress, boolean isAlias, boolean removeDLH, String filterString) throws Exception{
            FileOutputStream fileOutPutStream = null;
            BufferedOutputStream bufferedOutputStream = null;
            ObjectOutputStream  objectOutputStream = null;
            GZIPOutputStream gzipOutStream = null;            
            MQQueue queue = null;
            if(isAlias)
                queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);
            try{
                queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE);
                int queueDepth = queue.getCurrentDepth();            
                if(queueDepth > 0){
                    fileOutPutStream = new FileOutputStream(filePath);
                    bufferedOutputStream = new BufferedOutputStream(fileOutPutStream);
                    if(isCompress == true){
                        gzipOutStream = new GZIPOutputStream(bufferedOutputStream);
                        objectOutputStream = new ObjectOutputStream(gzipOutStream);
                    }
                    else{
                        objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
                    }
                    objectOutputStream.writeObject("MQAdminToolQueueDataFile");
                    objectOutputStream.writeInt(0x10001);
                    objectOutputStream.writeObject(queueName);
                    objectOutputStream.writeInt(ids == null? queueDepth : ids.size());
                    int index = queueDepth;
                    if(ids == null){
                        while(index > 0){
                            MQMessage message = new MQMessage();
                            MQGetMessageOptions options = new MQGetMessageOptions();
                            options.options = CMQC.MQGMO_BROWSE_NEXT;
                            try{
                                queue.get(message, options);
                            }
                            catch(MQException ex){
                                if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                                    if(progressBar != null){
                                        progressBar.setValue(100);
                                    }
                                    break;
                                }
                                else{
                                    throw ex;
                                }
                            }
                            if(removeDLH){
                                message = RemoveMQDLH(message);
                            }
                            if(filterString != null && !filterString.isEmpty()){
                                String content = message.readStringOfByteLength(message.getDataLength());
                                if(content.contains(filterString)){
                                    writeMessageToStream(message, objectOutputStream);
                                }                                
                            }
                            else{                           
                                writeMessageToStream(message, objectOutputStream);
                            }
                            index--;
                            if(progressBar != null){
                                int value = ((queueDepth - index)*100)/queueDepth;
                                progressBar.setValue(value);
                            }
                        }
                    }
                    else{
                        int i = 1;
                        for(MQMessageIdModel id : ids){
                            MQMessage message = new MQMessage();
                            message.messageId = id.MessageId;
                            message.correlationId = id.CorrelationdId;
                            MQGetMessageOptions options = new MQGetMessageOptions();
                            options.options = CMQC.MQGMO_BROWSE_NEXT;
                            options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;
                            queue.get(message, options);   
                            if(removeDLH){
                                message = RemoveMQDLH(message);
                            }
                            writeMessageToStream(message, objectOutputStream);
                            if(progressBar != null){
                                int value = (i*100)/ids.size();
                                progressBar.setValue(value);
                            }  
                            i++;
                        }
                    }
                    closeQueue(queue);
                    objectOutputStream.flush();
                    if(gzipOutStream != null){
                        gzipOutStream.flush();
                    }
                    bufferedOutputStream.flush();
                    disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream,gzipOutStream, objectOutputStream);
                }
                else {
                    closeQueue(queue);
                    throw new Exception("Queue depth is 0");
                }
            }catch(IOException ex){
                LogWriter.WriteToLog("MQUtility", "BackupMessageToFile",ex);
                disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream, gzipOutStream, objectOutputStream);
                removeFile(filePath);
                closeQueue(queue);
                throw new Exception("File access error");
            }
            catch(MQException ex){
                LogWriter.WriteToLog("MQUtility", "BackupMessageToFile",ex);
                disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream, gzipOutStream, objectOutputStream);
                removeFile(filePath);
                closeQueue(queue);
                throw new Exception(getMQReturnMessage(ex.getCompCode(), ex.getReason()));                
            }
    
        
    }
    
    public static void SaveMessageContentToFile(MQQueueManager queueManager, String queueName, String filePath, JProgressBar progressBar, ArrayList<MQMessageIdModel>ids) throws Exception{
            BufferedWriter out = new BufferedWriter(new FileWriter(new File(filePath)));
            MQQueue queue = null;
            try{
                queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE);
                int queueDepth = queue.getCurrentDepth();            
                if(queueDepth > 0){
                    int index = queueDepth;
                    if(ids == null){
                        int pos = 1;
                        while(index > 0){
                            MQMessage message = new MQMessage();
                            MQGetMessageOptions options = new MQGetMessageOptions();
                            options.options = CMQC.MQGMO_BROWSE_NEXT;
                            try{
                                queue.get(message, options);
                            }
                            catch(MQException ex){
                                if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                                    if(progressBar != null){
                                        progressBar.setValue(100);
                                    }
                                    break;
                                }
                                else{
                                    throw ex;
                                }
                            }
                            out.write("Message : " + pos);
                            out.write("\r\n"); 
                            out.write("\r\n");
                            out.write(GetMessageStringContent(message, null));
                            out.write("\r\n"); 
                            out.write("\r\n"); 
                            index--;
                            pos++;
                            if(progressBar != null){
                                int value = ((queueDepth - index)*100)/queueDepth;
                                progressBar.setValue(value);
                            }
                        }
                    }
                    else{
                        int i = 1;
                        for(MQMessageIdModel id : ids){
                            MQMessage message = new MQMessage();
                            message.messageId = id.MessageId;
                            message.correlationId = id.CorrelationdId;
                            MQGetMessageOptions options = new MQGetMessageOptions();
                            options.options = CMQC.MQGMO_BROWSE_NEXT;
                            options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;
                            queue.get(message, options);   
                            out.write("Message position : " + i);
                            out.write("\r\n"); 
                            out.write("\r\n");
                            out.write(message.readStringOfByteLength(message.getMessageLength()));
                            out.write("\r\n"); 
                            out.write("\r\n");
                            if(progressBar != null){
                                int value = (i*100)/ids.size();
                                progressBar.setValue(value);
                            }  
                            i++;
                        }
                    }
                    closeQueue(queue);
                    out.flush();
                    out.close();
                }
                else {
                    closeQueue(queue);
                    out.close();
                    throw new Exception("Queue depth is 0");
                }
            }catch(IOException ex){
                LogWriter.WriteToLog("MQUtility", "SaveMessageContentToFile",ex);
                removeFile(filePath);
                closeQueue(queue);
                out.close();
                throw new Exception("File access error");
            }
            catch(MQException ex){
                LogWriter.WriteToLog("MQUtility", "SaveMessageContentToFile",ex);
                removeFile(filePath);
                closeQueue(queue);
                out.close();
                throw new Exception(getMQReturnMessage(ex.getCompCode(), ex.getReason()));                
            }
    
        
    }
 
    public static void RestoreMessageFromFile(MQQueueManager queueManager, String queueName, String filePath, JProgressBar progressBar, boolean isAlias, boolean checkQueueDepth) throws Exception{
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;  
        GZIPInputStream gzipInStream = null;
        BufferedInputStream bufferedInputStream = null;
        MQQueue queue = null;
        if(isAlias)
            queueName = MQPCF.ResolveAliasBaseQueueName(queueManager, queueName);        
        try {
            try{
                fileInputStream = new FileInputStream(filePath);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                gzipInStream = new GZIPInputStream(bufferedInputStream);
                objectInputStream = new ObjectInputStream(gzipInStream);
            }catch(Exception ex){
                disposeInputStreamObject(fileInputStream,bufferedInputStream,gzipInStream, objectInputStream);
                fileInputStream = new FileInputStream(filePath);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                objectInputStream = new ObjectInputStream(bufferedInputStream);
            }
            
            try {
                String fileIdentifier = (String)objectInputStream.readObject();
                int intIdentifier = objectInputStream.readInt();
                if(fileIdentifier == null || !fileIdentifier.equals("MQAdminToolQueueDataFile") || intIdentifier != 0x10001){
                    throw new Exception("Not a valid messagae data file");
                }
            } catch (Exception ex) {
                throw new Exception("Not a valid messagae data file");
            }
            String originalQueueName = (String)objectInputStream.readObject();
            int totalNumOfMessages = objectInputStream.readInt();                  
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_OUTPUT | CMQC.MQOO_SET_ALL_CONTEXT | CMQC.MQOO_INPUT_SHARED);
            if(checkQueueDepth){
                try{
                    int curDepth = queue.getCurrentDepth();
                    int maxDepth = queue.getMaximumDepth();                
                    int availableMsgNum = maxDepth - curDepth;
                    if(totalNumOfMessages > availableMsgNum ){
                        closeQueue(queue);
                        throw new Exception("Not enough avaliable queue depth");
                    }
                }
                catch(MQException ex){

                }
            }
            MQPutMessageOptions option = new MQPutMessageOptions();
            option.options = CMQC.MQPMO_SET_ALL_CONTEXT;
            for(int i = 0; i < totalNumOfMessages; i++){
                try{
                    MQMessage message = readMessageFromStream(objectInputStream);
                    if(message.format.contains("MQXMIT")){
                        Field versionField = message.getClass().getSuperclass().getDeclaredField("version");
                        versionField.setAccessible(true);
                        versionField.set(message, 1);
                    }
                    queue.put(message, option);
                    if(progressBar != null){
                        int value = ((i + 1) * 100)/totalNumOfMessages;
                        progressBar.setValue(value);
                    }
                }
                catch(IOException | ClassNotFoundException | MQException ex){    
                    if(progressBar != null){
                        progressBar.setValue(100);
                    }
                    if(ex instanceof EOFException){
                        break;
                    }
                    else{
                        throw ex;
                    }
                }

            }
            closeQueue(queue);
            disposeInputStreamObject(fileInputStream,bufferedInputStream,gzipInStream, objectInputStream);

        }
        catch (IOException | ClassNotFoundException ex){
            LogWriter.WriteToLog("MQUtility", "RestoreMessageFromFile",ex);
            disposeInputStreamObject(fileInputStream,bufferedInputStream,gzipInStream, objectInputStream);
            closeQueue(queue);
            throw new Exception("File access or format error");
        }
        catch(MQException ex){
            LogWriter.WriteToLog("MQUtility", "RestoreMessageFromFile",ex);
            disposeInputStreamObject(fileInputStream,bufferedInputStream,gzipInStream, objectInputStream);
            closeQueue(queue);
            throw new Exception(getMQReturnMessage(ex.getCompCode(), ex.getReason())); 
        }
    }
  
    public static String BytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static MQDLH getDLH(MQMessage message){
         try {
            MQHeaderList list = new MQHeaderList(message, false);
            if(list.size() > 0 && list.indexOf("MQDLH") >= 0){
                MQDLH dlh = (MQDLH) list.get(list.indexOf("MQDLH"));
                message.seek(0);
                return dlh;
            }
        } catch(Exception ex){

        }
        return null;       
    }
    
    public static MQMessage RemoveMQDLH(MQMessage message){
        try {
            MQHeaderList list = new MQHeaderList(message, true);
            if(list.size() > 0 && list.indexOf("MQDLH") >= 0){
                MQDLH dlh = (MQDLH) list.get(list.indexOf("MQDLH"));
                list.remove(dlh);
                MQMessage newMessage = new MQMessage();
                copyMessageMQMD(newMessage, message);
                list.write(newMessage, true);
                newMessage.format = !list.getFormat().trim().contains( "MQDEAD") ? list.getFormat() : null;            
                return newMessage;
            }
        } catch (MQDataException ex) {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
        } catch(Exception ex){
            
        }
        return message;
    }
    
    //Msc
    
    public static int GetMessageContentLength(MQMessage message){     
        int msglen = 0;
        try {
            message.seek(0);
            msglen = message.getMessageLength();
            MQHeaderList list = new MQHeaderList(message);
            if(list.size() > 0){
                boolean seek = true;
                for (Object list1 : list) {
                    Header header = (Header) list1;
                    msglen = msglen - header.size();
                    seek = false;
                }
//                if (list.indexOf("MQRFH2") >= 0) {
//                    MQRFH2 header = (MQRFH2) list.get(list.indexOf("MQRFH2"));
//                    msglen = msglen - header.size();
//                    seek = false;
//                }
//                if (list.indexOf("MQDLH") >= 0) {
//                    MQDLH header = (MQDLH) list.get(list.indexOf("MQDLH"));
//                    msglen = msglen - header.size();
//                    seek = false;
//                }
                if(seek){
                    message.seek(0);
                }
            }
            
        } catch (MQDataException | IOException ex) {
            Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception ex){
            Logger.getLogger(MessageEditDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msglen;
    }
    
    public static String GetMessageStringContent(MQMessage message, Integer length){
        String content = "";
        int messageLength = GetMessageContentLength(message);
        int requiredLen = length != null ? length > messageLength? messageLength : length : messageLength;
        try{
            //message.seek(0);
            try{
                content = message.readStringOfByteLength(requiredLen);  
            }catch(Exception ex){
                //tmp solution for wrong charset.       
                byte[] buff = new byte[requiredLen];
                message.readFully(buff);
                String strContent = new String(buff);
                content = strContent;
            }     
        }catch(IOException ex){
            
        }
        
        return content;
    }
    
    // test method
    public static Charset GetMessageCharset(MQMessage message){
        int a = CMQC.MQCCSI_Q_MGR;
        switch (message.characterSet){
            case 850 :
                return StandardCharsets.US_ASCII;
            case 819 :
                return StandardCharsets.ISO_8859_1;
            case 37 :
                return StandardCharsets.UTF_16BE;
            case 1200 :
                return StandardCharsets.UTF_16;
            case 1208 :
                return StandardCharsets.UTF_8;
        }
        return StandardCharsets.US_ASCII;
    }
    
//private
    private static MessageDetailModel turnToMessageModel(MQMessageListResult result, MQMessage message, int position){
        MessageDetailModel model = result.new MessageDetailModel();
        model.MessageId = message.messageId;
        model.CorrelationId = message.correlationId;
        model.ApplicationIdentifyData = message.applicationIdData.trim();
        model.ApplicationOriginalData = message.applicationOriginData.trim();
        try {
            model.DataLength = Integer.toString(message.getDataLength());
        } catch (IOException ex) {
            model.DataLength = "0";
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        model.Encoding = Integer.toString(message.encoding).trim();
        model.Expiry = message.expiry == MQConstants.MQEI_UNLIMITED? "Unlimited" : DateTimeHelper.GetCurrentAddSecondTimeStamp(message.expiry / 10);
        model.Format = message.format.trim();
        model.MessageType = getMQMEessageType(message.messageType);
        model.Persistence = message.persistence == 0 ? "Not persistence" : "Persistence";
        model.Priority = Integer.toString(message.priority).trim();
        model.PutApplicationName = message.putApplicationName.trim();
        model.PutDateTime =  message.putDateTime != null ? DateTimeHelper.GetCustomTimeStamp(message.putDateTime.getTime()) : null;
        model.Position = Integer.toString(position).trim();
        model.ReplyToQueue = message.replyToQueueName.trim();
        model.ReplyToQueueManager = message.replyToQueueManagerName.trim();
        model.TotalLength = Integer.toString(message.getTotalMessageLength()).trim();
        model.UserIdentifier = message.userId.trim();
        model.SequenceNumber = message.messageSequenceNumber;
        model.AccountToken = BytesToHex(message.accountingToken);
        model.MessageIdString = BytesToHex(message.messageId);
        model.Offset = message.offset;
        model.MessageData = GetMessageStringContent(message, messageListDataLength);
        model.MessageFullData = GetMessageStringContent(message, null);
        model.BackoutCount = message.backoutCount;
        return model;
    }
    
    private static String getMQMEessageType(int type){
        switch(type){
            case CMQC.MQMT_DATAGRAM :
                return "Datagram";
            case CMQC.MQMT_REQUEST:
                return "Request";
            case CMQC.MQMT_REPLY:
                return "Reply";
            case CMQC.MQMT_REPORT:
                return "Report";
            default:
                return Integer.toString(type);
        }
    }

    private static MQMessage readMessageFromStream(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        MQMessage message = new MQMessage();
        for(Field field : message.getClass().getFields()){
            if(Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && field.getName() != "unmappableAction"){
                try {
                    field.set(message, stream.readObject());                    
                } catch (IOException | ClassNotFoundException | IllegalArgumentException | IllegalAccessException ex) {
                    continue;
                }
            }
        }
        int i = stream.readInt();
        byte buff[] = new byte[i];
        stream.readFully(buff);
        message.write(buff);
        return message;
    }
    
    private static void writeMessageToStream(MQMessage message, ObjectOutputStream stream){
        
        try
        {
            for(Field field : message.getClass().getFields()){
                if(Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers()) && field.getName() != "unmappableAction"){
                    try {
                        Object value = field.get(message);
                        stream.writeObject(value);
                    } catch (IllegalArgumentException | IllegalAccessException | IOException ex) {
                        continue;
                    }
                }
            }
            message.seek(0);
            stream.writeInt(message.getMessageLength());
            byte buff[] = new byte[message.getMessageLength()];
            message.readFully(buff);
            stream.write(buff);
        }
        catch(Exception ex)
        {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    private static void copyMessageMQMD(MQMessage newMessage, MQMessage oldMessage){
        for(Field field : oldMessage.getClass().getFields()){
            if(Modifier.isPublic(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())){
                try {
                    String name = field.getName();
                    Object value = field.get(oldMessage);
                    newMessage.getClass().getField(name).set(newMessage, value);
                } catch (Exception ex) {
                    continue;
                }
            }
        }
    }

    private static void disposeOutputStreamObject(FileOutputStream fileOutPutStream,BufferedOutputStream bufferedOutputStream, GZIPOutputStream gzipOutStream , ObjectOutputStream  objectOutputStream ){
        if(objectOutputStream != null){
            try {
                objectOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(gzipOutStream != null){
            try {
                gzipOutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(bufferedOutputStream != null){
            try {
                bufferedOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(fileOutPutStream != null){
            try {
                fileOutPutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private static void disposeInputStreamObject(FileInputStream fileInPutStream, BufferedInputStream bufferedInputStream, GZIPInputStream gzipInStream, ObjectInputStream  objectInputStream ){
        if(objectInputStream != null){
            try {
                objectInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(gzipInStream != null){
            try {
                gzipInStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(bufferedInputStream != null){
            try {
                bufferedInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        if(fileInPutStream != null){
            try {
                fileInPutStream.close();
            } catch (IOException ex) {
                Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private static void BackupAndRemoveMessageFromSelectedPosition(MQQueueManager queueManager, String queueName, String filePath,MQMessageIdModel id) throws Exception{
        FileOutputStream fileOutPutStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream  objectOutputStream = null;
        GZIPOutputStream gzipOutStream = null;            
        MQQueue queue = null;
        try{
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_INPUT_SHARED | CMQC.MQOO_OUTPUT);
            int queueDepth = queue.getCurrentDepth();            
            if(queueDepth > 0){
                fileOutPutStream = new FileOutputStream(filePath);
                bufferedOutputStream = new BufferedOutputStream(fileOutPutStream);
                gzipOutStream = new GZIPOutputStream(bufferedOutputStream);
                objectOutputStream = new ObjectOutputStream(gzipOutStream);

                objectOutputStream.writeObject("MQAdminToolQueueDataFile");
                objectOutputStream.writeInt(0x10001);
                objectOutputStream.writeObject(queueName);
                objectOutputStream.writeInt(queueDepth);
                MQMessage message = new MQMessage();
                message.messageId = id.MessageId;
                message.correlationId = id.CorrelationdId;
                MQGetMessageOptions options = new MQGetMessageOptions();
                options.options = CMQC.MQGMO_BROWSE_NEXT;
                options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;                
                queue.get(message, options);
                ArrayList<MQMessageIdModel> idsToRemove = new ArrayList<MQMessageIdModel>();
                while(true){
                    try{
                        resetMessage(message);
                        options.options = CMQC.MQGMO_BROWSE_NEXT ;
                        options.matchOptions = CMQC.MQMO_NONE;
                        queue.get(message, options);
                        writeMessageToStream(message, objectOutputStream);
                        MQMessageIdModel deleteMsgId = new MQMessageIdModel();
                        deleteMsgId.MessageId = message.messageId;
                        deleteMsgId.CorrelationdId = message.correlationId;
                        idsToRemove.add(deleteMsgId);
                    }
                    catch(MQException ex){
                        if(ex.getReason() == MQConstants.MQRC_NO_MSG_AVAILABLE){
                            break;
                        }
                        else{
                            throw ex;
                        }
                    }
                }
                
                closeQueue(queue);
                objectOutputStream.flush();
                if(gzipOutStream != null){
                    gzipOutStream.flush();
                }
                bufferedOutputStream.flush();
                disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream,gzipOutStream, objectOutputStream);
                ComsumeSelectedMessages(queueManager, queueName, idsToRemove, null, false, false);
            }
            else {
                closeQueue(queue);
                throw new Exception("Queue depth is 0");
            }
        }catch(IOException ex){
            LogWriter.WriteToLog("MQUtility", "BackupAndRemoveMessageFromSelectedPosition",ex);
            disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream, gzipOutStream, objectOutputStream);
            removeFile(filePath);
            closeQueue(queue);
            throw new Exception("File access error");
        }
        catch(MQException ex){
            LogWriter.WriteToLog("MQUtility", "BackupAndRemoveMessageFromSelectedPosition",ex);
            disposeOutputStreamObject(fileOutPutStream, bufferedOutputStream, gzipOutStream, objectOutputStream);
            removeFile(filePath);
            closeQueue(queue);
            throw new Exception(getMQReturnMessage(ex.getCompCode(), ex.getReason()));                
        }
    
        
    }
    
    private static void removeFile(String filePath){
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }
    
    private static void closeQueue(MQQueue queue){
        if(queue != null){
            try {
                queue.close();
            } catch (MQException ex) {
                LogWriter.WriteToLog("MQUtility", "removeFile",ex);
            }
        }
    }
       
    private static boolean setQueueCursor(MQQueue queue, int position){
        MQMessage message = new MQMessage();
        MQGetMessageOptions options = new MQGetMessageOptions();
        options.options = CMQC.MQGMO_BROWSE_NEXT;
        options.matchOptions = CMQC.MQMO_NONE;
        try {
            int queueDepth = queue.getCurrentDepth();
            position = position > queueDepth ? queueDepth : position;
            for(int i = 0; i < position - 1; i++){
                queue.get(message, options);  
                resetMessage(message);
            }
        }
        catch (MQException ex) {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } 
        return true;
    }
    
    private static void resetMessage(MQMessage message){
        try {
            message.clearMessage();
            message.messageId = MQConstants.MQMI_NONE;
            message.correlationId = MQConstants.MQMI_NONE;
        } catch (IOException ex) {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
            message = new MQMessage();
        }

    }
    
}
