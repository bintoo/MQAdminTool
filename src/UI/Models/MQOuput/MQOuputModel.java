/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models.MQOuput;

import java.util.ArrayList;

/**
 *
 * @author jzhou
 */
public class MQOuputModel {
    public ArrayList<MQOutputQueueModel> queueModelList = new ArrayList<MQOutputQueueModel>();
    public ArrayList<MQOutputChannelModel> channelModelList = new ArrayList<MQOutputChannelModel>();
    public ArrayList<MQOutputProcessModel> processModelList = new ArrayList<MQOutputProcessModel>();
    public ArrayList<MQOutputListenerModel> ListenerModelList = new ArrayList<MQOutputListenerModel>();
    public ArrayList<MQOuputServiceModel> ServiceModelList = new ArrayList<MQOuputServiceModel>();
    public ArrayList<MQOutputTopicModel> TopicModelList = new ArrayList<MQOutputTopicModel>();
    public ArrayList<MQOutputSubModel> SubModelList = new ArrayList<MQOutputSubModel>();   
    
}
