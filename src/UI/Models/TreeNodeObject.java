package UI.Models;

import MQApi.Models.Query.ConnectionDetailModel;
import com.ibm.mq.MQQueueManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jzhou
 */
public class TreeNodeObject {
    public String Name;
    public TreeNodeObjectType Type;
    public MQQueueManager QueueManager;
    public ConnectionDetailModel ConnectionDetail;
    
    public TreeNodeObject(String name, TreeNodeObjectType type){
        this.Name = name;
        this.Type = type;
    }
    
    public boolean IsConnected(){
        return QueueManager != null && QueueManager.isConnected();
    }
    @Override
    public String toString() {
        if(Type == TreeNodeObjectType.QueueManager && ConnectionDetail != null){
            return this.Name +" ("+ ConnectionDetail.Host + ":" +ConnectionDetail.Port +")";
        }
        else{
            return this.Name;
        }
    }
    public enum TreeNodeObjectType{
        Root,
        QueueManagerFolder,
        QueueManager,
        Queues,
        Channel,
        ChannelAuth,
        QueueManagerClusterFolder,
    }
}
