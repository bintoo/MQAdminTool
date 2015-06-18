/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi;

import MQApi.Logs.LogWriter;
import MQApi.QueryModel.MQQueueListResult;
import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
//import com.ibm.mq.MQQueueManagerFactory;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import com.ibm.mq.headers.MQData;
import com.ibm.mq.headers.MQDataException;
import com.ibm.mq.headers.MQHeaderList;
import com.ibm.mq.headers.pcf.MQCFGR;
import com.ibm.mq.headers.pcf.MQCFH;
import com.ibm.mq.headers.pcf.PCFMessage;
import com.ibm.mq.headers.pcf.PCFMessageAgent;
import com.ibm.mq.headers.pcf.PCFParameter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jzhou
 */
public class MQTest {
    
    //private static MQQueueManagerFactory factory;
    
    public static void test(MQQueueManager queueManager, String queueName){
        try {
            MQQueue queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE);
            int queueDepth = queue.getCurrentDepth();
            int index = queueDepth;
            
            while(index > 0){
                MQMessage message = new MQMessage();
                MQGetMessageOptions options = new MQGetMessageOptions();
                options.options = CMQC.MQGMO_BROWSE_NEXT; 
                queue.get(message, options); 
//                message.clearMessage();
//                message.messageId = MQConstants.MQSID_NONE;
//                message.correlationId = MQConstants.MQACT_NONE;
//                String content = message.readStringOfByteLength(message.getTotalMessageLength());
//                try {
//                    //Method[] methods = queue.getClass().getDeclaredMethods();
//                    Method method = queue.getClass().getDeclaredMethod("getHandle");
//                    method.setAccessible(true);
//                    Object o = method.invoke(queue);
//                    String aa = "ADS";
//                    //int an = Integer.parseInt(o.toString());
//                } catch (NoSuchMethodException ex) {
//                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SecurityException ex) {
//                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalArgumentException ex) {
//                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InvocationTargetException ex) {
//                    Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
//                }
                index--;
//                if(queueDepth - index == 30000){
//                    break;
//                }
            }
            String a = "ASD";

        } catch (MQException ex) {
            Logger.getLogger(MQUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void test2(MQQueueManager queueManager, String queueName){
        for(int i = 0; i < 50000; i++){
            MQMessage message = new MQMessage();
            String content = String.valueOf(i);
            try {
                message.writeString(content);
            } catch (IOException ex) {
                Logger.getLogger(MQTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            MQUtility.PutMessage(queueManager, queueName, message);
        }
        String aa = "ADS";
        
    }
    
    public static void test3(){
        int aa = CMQC.MQGMO_BROWSE_NEXT | CMQC.MQGMO_MSG_UNDER_CURSOR | CMQC.MQGMO_ACCEPT_TRUNCATED_MSG ;
        String b = "ASD";
//        factory.getInstance().C
//        MQQueueManager queueManager = new MQQueueManager("ASd");
//        queueManager.get
//        MQSESSION se

    
    }
    public static void GetMessageTest(MQQueueManager queueManager, String queueName) throws MQException{
        PCFMessageAgent agent = null;
        try {
            agent = new PCFMessageAgent(queueManager);
            PCFMessage pcfCmd = new PCFMessage(MQConstants.MQCMD_ACCOUNTING_Q);  
            PCFMessage[] pcfResponse = agent.send(pcfCmd); 
            String aa = "ADAS";
        } catch (MQDataException ex) {

            LogWriter.WriteToLog("MQPCF", "GetQueueList", ex);
        } catch (IOException ex) {

            LogWriter.WriteToLog("MQPCF", "GetQueueList", ex);
        } 
               
    }
    
    public static MQMessage GetMessage(MQQueueManager queueManager, String queueName) throws MQException{
        MQQueue queue = null;
        MQMessage message = new MQMessage();
        MQGetMessageOptions options = new MQGetMessageOptions();
        options.options = CMQC.MQGMO_BROWSE_NEXT;
        options.matchOptions = MQConstants.MQMO_MATCH_MSG_ID  | MQConstants.MQMO_MATCH_CORREL_ID;
        try {                 
            queue = queueManager.accessQueue(queueName, CMQC.MQOO_INQUIRE | CMQC.MQOO_BROWSE | CMQC.MQOO_OUTPUT);
            queue.get(message, options);
            PCFMessage pcf = new PCFMessage(message);
            MQCFGR aaa = (MQCFGR)pcf.getParameter(MQConstants.MQGACF_Q_ACCOUNTING_DATA);
            Object name = aaa.getParameterValue(MQConstants.MQCA_Q_NAME);
            String aa = "ASDA";

        } catch (MQException ex) {
            LogWriter.WriteToLog("MQUtility", "GetMessage", ex);
            closeQueue(queue);
            throw ex;           
        } catch (MQDataException ex) {
            Logger.getLogger(MQTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MQTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeQueue(queue);
        return message;                
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
}
