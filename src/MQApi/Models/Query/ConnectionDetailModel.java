/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Models.Query;

/**
 *
 * @author jzhou
 */
public class ConnectionDetailModel {
    public String Host; 
    public String QueueManager; 
    public String Channel; 
    public String Port;
    
    public String User;
    public String Password;
 
    public String toString() {
        return "<html>\nhost: " + Host + 
                "<br/>\nqueue manager: " + QueueManager + 
                "<br/>\nchannel: " + Channel + 
                "<br/>\nport: " + Port + 
                "<br/>\nuser: " + User + 
                "<br/>\npassword: " + Password +
                "</html>";
    }
}
