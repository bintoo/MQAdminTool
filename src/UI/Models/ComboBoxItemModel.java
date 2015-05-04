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
    public Object Value;
    
    public ComboBoxItemModel(String name, Object value){
        this.DisplayName = name;
        this.Value = value;
    }
    
    @Override
    public String toString(){
        return DisplayName;
    }
    
}
