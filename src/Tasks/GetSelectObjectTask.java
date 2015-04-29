/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.Enums.*;
import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQChannelListResult;
import MQApi.QueryModel.MQQueueListResult;
import UI.Helpers.TableHelper;
import com.ibm.mq.MQQueueManager;
import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jzhou
 * @param <T>
 */
public class GetSelectObjectTask<T extends Enum> extends TaskBase{
    
    MQQueueManager queueManager;
    T[] selectObjectType;
    JTable table;
    
    public GetSelectObjectTask(MQQueueManager queueManager, JTable table, T[] selectObjectType ){
        this.queueManager = queueManager;
        this.selectObjectType = selectObjectType;
        this.table = table;
    }
    

    @Override
    public void run() {
        updateTable();
    }
    
    private void updateTable(){
        if(selectObjectType instanceof QueueType[]){
            QueueType[] queueTypes = (QueueType[])selectObjectType;
            boolean getTransmissionQOnly = queueTypes.length == 1 && queueTypes[0] == QueueType.Transmit;
            if(getTransmissionQOnly){
                queueTypes = new QueueType[]{QueueType.Local};
            }
            MQQueueListResult queryResult = MQPCF.GetQueueList(queueManager,"*", queueTypes);
            if(stopTask == false && queryResult.QuerySuccess){
                ArrayList<MQQueueListResult.QueueDetailModel> models; 
                if(getTransmissionQOnly == false){
                    models = queryResult.GetFilterDataModels("*",false, true);
                }
                else{
                    models = queryResult.GetFilterTransmissionQDataModels();
                }
                TableHelper.UpdateContentTable(table, models);
                FireActionSuccessEvent();
            }
            else{
                JOptionPane.showMessageDialog(table, queryResult.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }
        }
        else if(selectObjectType instanceof ChannelType[]){
            ChannelType[] channelTypes =(ChannelType[])selectObjectType;
            MQChannelListResult queryResult = MQPCF.GetChannelList(queueManager,"*", channelTypes);
            if(stopTask == false && queryResult.QuerySuccess){
                ArrayList<MQChannelListResult.ChannelDetailModel> models  = queryResult.DataModels; 
                TableHelper.UpdateContentTable(table, models);
                FireActionSuccessEvent();                
            }
            else{
                JOptionPane.showMessageDialog(table, queryResult.ErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                FireActionFailEvent();
            }
        }
        else{
            FireActionFailEvent();
        }
    }
    
}
