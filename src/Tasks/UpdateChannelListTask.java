/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;

import MQApi.PCF.MQPCF;
import MQApi.QueryModel.MQChannelListResult;
import UI.Helpers.TableHelper;
import UI.Helpers.TreeHelper;
import UI.Models.TreeNodeObject;
import com.ibm.mq.MQException;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 *
 * @author jzhou
 */
public class UpdateChannelListTask extends UpdateContentTableTaskBase{

    public UpdateChannelListTask(Component window, JTree tree, JTable table, DefaultMutableTreeNode node, String searchString, boolean showSystem, boolean loadNewData) {
        super(window, null, table, node,searchString, showSystem, loadNewData);
    }
    
    private void updateContent(){
        DefaultMutableTreeNode queueManagerNode = (DefaultMutableTreeNode)node.getParent();
        TreeNodeObject queueManagerNoteObject = (TreeNodeObject)queueManagerNode.getUserObject();
        MQChannelListResult result = MQPCF.GetChannelList(queueManagerNoteObject.QueueManager, "*",  null, loadNewData);
        if(stopTask == false){
            if(result.QuerySuccess){
                TableHelper.UpdateContentTable(this.ContentTable, result.GetFilterDataModels(searchString, showSystem)); 
                FireActionSuccessEvent();
            }
            else{
                showErrorMessage(result.ErrorMessage);
                TreeView.setSelectionPath(new TreePath(queueManagerNode.getPath())); 
                try {
                    TreeHelper.DisconnectQueueManager(TreeView);
                } catch (MQException ex) {
                }
                
                FireActionFailEvent();
            }         
        }
    }
   
    @Override
    public void run() {
        updateContent();
    }
    
}
