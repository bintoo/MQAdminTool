/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models;

/**
 *
 * @author jzhou
 */
public class ComboBoxItemModel {
    public String DisplayName;
    public Integer MQConstantValue;
    
    public ComboBoxItemModel(String name, Integer value){
        this.DisplayName = name;
        this.MQConstantValue = value;
    }
    
    @Override
    public String toString(){
        return DisplayName;
    }
    
}
