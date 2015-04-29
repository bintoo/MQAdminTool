/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQQueueListResult;
import UI.Helpers.TableHelper;
import UI.Helpers.TreeHelper;
import UI.Models.TreeNodeObject;
import com.ibm.mq.MQException;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author jzhou
 */
public class UpdateQueueListTask extends UpdateContentTableTaskBase{
    
    private boolean showTemp;
    public UpdateQueueListTask(Component window,JTable table, DefaultMutableTreeNode node, String searchString, boolean showTemp, boolean showSystem, boolean loadNewData) {
        super(window, null, table, node,searchString, showSystem, loadNewData);
        this.showTemp = showTemp;
    }
    
    
    private void updateContent(){
        DefaultMutableTreeNode queueManagerNode = (DefaultMutableTreeNode)node.getParent();
        TreeNodeObject queueManagerNoteObject = (TreeNodeObject)queueManagerNode.getUserObject();
        MQQueueListResult result = MQPCF.GetQueueList(queueManagerNoteObject.QueueManager, "*",  null, loadNewData);
        if(stopTask == false){
            if(result.QuerySuccess){
                TableHelper.UpdateContentTable(this.ContentTable,result.GetFilterDataModels(searchString, showTemp, showSystem)); 
                FireActionSuccessEvent();
            }
            else{
                TreeView.setSelectionPath(new TreePath(queueManagerNode.getPath())); 
                try {
                    TreeHelper.DisconnectQueueManager(TreeView);
                } catch (MQException ex) {
                }
                showErrorMessage(result.ErrorMessage);
                FireActionFailEvent();
            }   
        }
    }
    
    @Override
    public void run() {
        updateContent();
    }
    
}
