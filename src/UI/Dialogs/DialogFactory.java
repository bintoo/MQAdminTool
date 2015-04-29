/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.QueueType;
import MQApi.Models.MQMessageIdModel;
import UI.Helpers.TableHelper;
import UI.Helpers.TreeHelper;
import UI.Models.TableListObject;
import com.ibm.mq.MQQueueManager;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTree;

/**
 *
 * @author jzhou
 */
public class DialogFactory {
    
    public static <T extends DialogBase>T CreateOperationalDialog(Class<T> dialogClass, java.awt.Frame parent, boolean modal,JTree treeView, JTable contentTable ){
        MQQueueManager queueManager = TreeHelper.GetCurrentSelectedQueueManager(treeView);
        TableListObject selectedObject = TableHelper.GetCurrentTableSelectRowObject(contentTable);
        return CreateOperationalDialog(dialogClass, parent, modal, queueManager, selectedObject);
    }
    
    public static <T extends DialogBase>T CreateOperationalDialog(Class<T> dialogClass, java.awt.Frame parent,boolean modal, MQQueueManager queueManager, TableListObject selectedObject){

        if(dialogClass == BrowseMessageDialog.class){
            return (T) new BrowseMessageDialog(parent, modal, queueManager, selectedObject);
        }
        else if(dialogClass == ClearMessagesDialog.class){
            return (T) new ClearMessagesDialog(parent, modal, queueManager, selectedObject);
        }
        else{
            return null;
        }       
    }
    
    public static BackupRestoreMessageDialog CreateBackupRestoreMessageDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject, ArrayList<MQMessageIdModel> ids){
        return new BackupRestoreMessageDialog(parent, modal, queueManager, selectedObject,ids);
    }
    
    public static QueueProperitiesDialog CreateQueueSetupProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, QueueType queueType){        
        return new QueueProperitiesDialog(parent, modal, queueManager, queueType);
    }
    
    public static QueueProperitiesDialog CreateQueueSetupProperitiesDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String name){        
        return new QueueProperitiesDialog(parent, modal, queueManager, name);
    }
    
    public static <T extends Enum> SelectObjectDialog CreateSelectObjectDialog(java.awt.Frame parent, boolean modal,  MQQueueManager queueManager, T[] objectType){;
        return new SelectObjectDialog(parent, modal, queueManager, objectType);
    }
    
    public static MessageEditDialog CreateMessageEditDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String queueName, MQMessageIdModel messageId){        
        return new MessageEditDialog(parent, modal, queueManager, queueName, messageId);
    }
    public static MessageEditDialog CreateMessageNewDialog(java.awt.Frame parent, boolean modal, MQQueueManager queueManager, String queueName){        
        return new MessageEditDialog(parent, modal, queueManager, queueName);
    }
    
}
