/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Utilities;

import com.ibm.mq.MQQueueManager;

/**
 *
 * @author jzhou
 */
public class ConsumeTask implements Runnable  {
    private MQQueueManager queueManager;
    private String queueName;
    private boolean forceOpenGet;
    private boolean isAlias;
    private Observer observer;
    private Integer processed;
    private ThreadFinish threadFinish;

    @Override
    public void run() {
        MQUtility.ComsumeMessagesThread(queueManager, queueName, forceOpenGet, isAlias, observer);
        this.threadFinish.IsFinish = true;
    }
    
    public ConsumeTask(MQQueueManager queueManager, String queueName, boolean forceOpenGet, boolean isAlias, Observer observer, ThreadFinish threadFinish){
        this.queueManager = queueManager;
        this.queueName = queueName;
        this.forceOpenGet = forceOpenGet;
        this.isAlias = isAlias;
        this.observer = observer;
        this.threadFinish = threadFinish;
    }
    
}
