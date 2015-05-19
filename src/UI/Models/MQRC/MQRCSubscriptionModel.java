/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models.MQRC;

/**
 *
 * @author jzhou
 */
public class MQRCSubscriptionModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Subscription Name *", createNewModel = true)
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String Description;    
    @MQRCModelAnnotation(ExcelKeyword = "Topic Name *")
    public String TopicName;  
    @MQRCModelAnnotation(ExcelKeyword = "Topic String *")
    public String TopicString; 
    @MQRCModelAnnotation(ExcelKeyword = "Destionation Queue Manager")
    public String DestQmgr; 
    @MQRCModelAnnotation(ExcelKeyword = "Destination Name")
    public String DestName;  
    @MQRCModelAnnotation(ExcelKeyword = "SUBUSER")
    public String SUBUSER;  
    @MQRCModelAnnotation(ExcelKeyword = "DESTCLASS")
    public String DESTCLASS = "PROVIDED"; 
    @MQRCModelAnnotation(ExcelKeyword = "EXPIRY")
    public String EXPIRY = "UNLIMITED"; 
    @MQRCModelAnnotation(ExcelKeyword = "PSPROP")
    public String PSPROP = "MSGPROP";  
    @MQRCModelAnnotation(ExcelKeyword = "PUBPRTY")
    public String PUBPRTY; 
    @MQRCModelAnnotation(ExcelKeyword = "REQONLY")
    public String REQONLY = "NO"; 
    @MQRCModelAnnotation(ExcelKeyword = "WSCHEMA")
    public String WSCHEMA = "TOPIC";  
}
