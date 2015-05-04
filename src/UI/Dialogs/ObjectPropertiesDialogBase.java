/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Dialogs;

import MQApi.Enums.QueueType;
import UI.Models.ComboBoxItemModel;
import UI.Models.TableListObject;
import com.ibm.mq.MQQueueManager;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author jzhou
 */
public abstract class ObjectPropertiesDialogBase extends DialogBase{

    public ObjectPropertiesDialogBase(Frame parent, boolean modal, MQQueueManager queueManager, TableListObject selectedObject) {
        super(parent, modal, queueManager, selectedObject);
    }
    
    protected void updateTextFieldFromSelectObject(JTextField field, String value){
        field.setText(value);
    }
    
    protected <T extends Enum> void showSelectObjectBrowser(T[] types, JTextField field){
        SelectObjectDialog dialog = DialogFactory.CreateSelectObjectDialog(ParentJFrame, true, queueManager, types);
        final JTextField targetField = field;
        dialog.AddDialogActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateTextFieldFromSelectObject(targetField, e.getActionCommand());
            }
        });
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);        
    }
    
    protected void setComboBoxValue(JComboBox box, Integer value){
        DefaultComboBoxModel model = (DefaultComboBoxModel)box.getModel();
        for(int i = 0 ; i < model.getSize(); i++){
            ComboBoxItemModel itemModel = (ComboBoxItemModel)model.getElementAt(i);
            if(itemModel.Value == value){
                box.setSelectedItem(itemModel);
                return;
            }
        }
    }
    
    protected Integer getComboBoxValue(JComboBox box){
        return box.isVisible() ? (Integer)((ComboBoxItemModel)box.getSelectedItem()).Value : null;
    }
    
    protected Integer getSpinnerBoxValue(JSpinner spinner){
        return spinner.isVisible() ? Integer.parseInt(spinner.getValue().toString()) : null;
    }
    
    protected String getTextFieldValue(JTextField field){
        return field.isVisible() ? field.getText(): null;
    }
    
    protected String[] getTextFieldArrayValue(JTextField field){
        if(field.isVisible()){
            String tmp = field.getText().trim();
            String[] valueArray = tmp.split(",");
            return valueArray;
            
        }
        return null;
    }
    
    protected void setTextFieldArrayValue(JTextField field, String[] values){
        String value = "";
        for(int i = 0; i < values.length; i++){
            if(i == values.length - 1 ){
                value += values[i].trim();
            }
            else{
                value += values[i].trim() + ",";
            }
        }
        
        field.setText(value);
    }
    
}
