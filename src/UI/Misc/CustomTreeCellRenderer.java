package UI.Misc;

import java.awt.Component;
 
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * @author nick
 *
 */
public class CustomTreeCellRenderer extends DefaultTreeCellRenderer  {	
  @Override
  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
    final Component rc = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);		
    String tooltip = "Custom tooltip";
    this.setToolTipText(tooltip);
    return rc;
  }
}
