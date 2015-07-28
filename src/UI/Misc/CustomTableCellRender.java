/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Misc;

import MQApi.Enums.QueueType;
import MQApi.Enums.TableContentType;
import UI.Helpers.IconManager;
import UI.Models.TableListObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.text.DateFormat;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jzhou
 */
public class CustomTableCellRender extends DefaultTableCellRenderer  {
    IconManager iconManager = new IconManager();

    public CustomTableCellRender() { super(); }
 
//  @Override
//   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//     
//     if(value instanceof JProgressBar){
//        JProgressBar progressBar = (JProgressBar)value;
//        setText(null);
//        Insets borderInsets = getBorder().getBorderInsets(this);
//        progressBar.setSize(table.getColumnModel().getColumn(column).getWidth() - borderInsets.left - borderInsets.right,
//                table.getRowHeight(row) - borderInsets.top - borderInsets.bottom);
//        progressBar.setForeground(getForeground());
//        progressBar.setBackground(getBackground());
//        progressBar.setBorder(getBorder());
//        return progressBar;
//     }
//     else{
//         return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//     }
//   }
 
//    @Override
//    public void setValue(Object value) {
//        TableListObject obj = (TableListObject)value;
//        if(obj.TableType == TableContentType.QueueList){
//            setIcon(iconManager.Queue());   
////            QueueType queueType = (QueueType)obj.Type;
////            if(queueType == QueueType.Local){
////                setIcon(iconManager.QueueLocalIcon());
////                  
////            }
////            else if(queueType == QueueType.Remote){
////                setIcon(iconManager.QueueRemoteIcon());
////            }
////            else if(queueType == QueueType.Alias){
////                setIcon(iconManager.QueueAliasIcon());
////            }
//        }
//        else if(obj.TableType == TableContentType.ChannelList){
//            setIcon(iconManager.Channel());            
//        }
//        else if(obj.TableType == TableContentType.MessageList){
//            setIcon(iconManager.MessageIcon());            
//        }
//        setBackground(Color.lightGray);
//        setText((value == null) ? "" : value.toString()); 
//    }
}
