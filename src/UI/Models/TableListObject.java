/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models;

import MQApi.Enums.ChannelType;
import MQApi.Enums.QueueType;
import MQApi.Enums.TableContentType;
import MQApi.Models.MQMessageIdModel;

/**
 *
 * @author jzhou
 */
public class TableListObject {
    public String ObjectName;
    public TableContentType TableType;
    public Object Type;
    public MQMessageIdModel MessageIdInfo;
    
    @Override
    public String toString() {
        return this.ObjectName;
    }
}
