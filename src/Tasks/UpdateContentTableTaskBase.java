/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tasks;
import java.awt.Component;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author jzhou
 */
public abstract class UpdateContentTableTaskBase extends TaskBase{
    protected JTree TreeView;
    protected JTable ContentTable;
    protected Component window;
    protected boolean showSystem;
    protected boolean loadNewData;
    protected String searchString;
    protected DefaultMutableTreeNode node;
    public UpdateContentTableTaskBase(Component window, JTree tree, JTable table, DefaultMutableTreeNode node,String searchString, boolean showSystem, boolean loadNewData){
        this.node = node;
        this.TreeView = tree;
        this.ContentTable = table;
        this.window = window;
        this.searchString = searchString.trim();
        this.showSystem = showSystem;
        this.loadNewData = loadNewData;
    }
    
    protected  void showErrorMessage(String message){
        JOptionPane.showMessageDialog(window, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public abstract void run();
    
}
