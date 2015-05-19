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
public class MQRCQueueAliasModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Queue Name *", createNewModel = true)
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Target Queue Name")
    public String TargetName;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String ShortDescription;
    @MQRCModelAnnotation(ExcelKeyword = "PUT")
    public String Put;
    @MQRCModelAnnotation(ExcelKeyword = "GET")
    public String Get;
    @MQRCModelAnnotation(ExcelKeyword = "DEFPSIST")
    public String DEFPSIST;
    @MQRCModelAnnotation(ExcelKeyword = "DEFPRTY")
    public String DEFPRTY;    
    
}
