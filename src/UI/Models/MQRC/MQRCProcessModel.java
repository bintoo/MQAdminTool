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
public class MQRCProcessModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Process Name *", createNewModel = true)
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String Description;    
    @MQRCModelAnnotation(ExcelKeyword = "Application ID *")
    public String AppId;  
    @MQRCModelAnnotation(ExcelKeyword = "Application Type *")
    public String AppType;  
}
