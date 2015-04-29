/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Misc;

import UI.Helpers.IconManager;
import UI.Models.TreeNodeObject;
import UI.Models.TreeNodeObject.TreeNodeObjectType;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author jzhou
 */
public class CustomTreeRender extends DefaultTreeCellRenderer {
    IconManager iconManager = new IconManager();
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row,boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row,hasFocus);
        if(isRoot(value)){
            setIcon(iconManager.Root());
        }
        else if(isQueueManagerFolderNode(value)){
            setIcon(iconManager.DeviceManager());
        }
        else if(isQueueManagerNodeAndConnected(value)){
            setIcon(iconManager.QueueMgrConnectedIcon()); 
        }
        else if(isQueueManagerNodeNotConnected(value)){
            setIcon(iconManager.QueueMgrDisconnectedIcon());
        }
        else if(isQueueFolder(value)){
            setIcon(iconManager.Queue());
        }        
        else if(isChannelFolder(value)){
            setIcon(iconManager.Channel());
        }
        return this;
    }
    
    private boolean isRoot(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);
        return nodeInfo.Type == TreeNodeObjectType.Root;
    }  
  
    private boolean isQueueFolder(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);
        return nodeInfo.Type == TreeNodeObjectType.Queues;
    }    

    private boolean isChannelFolder(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);
        return nodeInfo.Type == TreeNodeObjectType.Channel;
    }  
    
    private boolean isQueueManagerNodeAndConnected(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);
        return nodeInfo.Type == TreeNodeObjectType.QueueManager && nodeInfo.IsConnected();
    }
    
    private boolean isQueueManagerNodeNotConnected(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);;
        return nodeInfo.Type == TreeNodeObjectType.QueueManager && !nodeInfo.IsConnected();
    }
    
    private boolean isQueueManagerFolderNode(Object value) {
        TreeNodeObject nodeInfo = getTreeNodeObject(value);
        return nodeInfo.Type == TreeNodeObjectType.QueueManagerFolder;
    }
    
    private TreeNodeObject getTreeNodeObject(Object value){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        TreeNodeObject nodeInfo = (TreeNodeObject)(node.getUserObject());   
        return nodeInfo;
    }

}
