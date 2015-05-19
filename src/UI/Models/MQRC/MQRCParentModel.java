/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models.MQRC;

import UI.Models.MQRC.*;

/**
 *
 * @author jzhou
 */
public class MQRCParentModel {
    public MQRCQueueModel Queue = new MQRCQueueModel();
    public MQRCQueueAliasModel QueueAlias = new MQRCQueueAliasModel();
    public MQRCChannelModel Channel = new MQRCChannelModel();
    public MQRCProcessModel Process = new MQRCProcessModel();
    public MQRCTopicModel Topic = new MQRCTopicModel();
    public MQRCSubscriptionModel Sub = new MQRCSubscriptionModel();
    
}
