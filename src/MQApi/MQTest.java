/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi;

import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;
import com.ibm.mq.MQQueueManagerFactory;
import com.ibm.mq.constants.CMQC;
import com.ibm.mq.constants.MQConstants;
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import com.ibm.msg.client.wmq.v6.base.internal.MQSESSION;
import java.io.IOException;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import com.ibm.msg.client.commonservices.trace.Trace;

/**
 *
 * @author jzhou
 */
public class MQTest {
    
    private static MQQueueManagerFactory factory;
    
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
}
