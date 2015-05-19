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
public class MQRCTopicModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Topic Name *", createNewModel = true)
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String Description;    
    @MQRCModelAnnotation(ExcelKeyword = "DURSUB")
    public String DURSUB = "YES";  
    @MQRCModelAnnotation(ExcelKeyword = "PUB")
    public String PUB; 
    @MQRCModelAnnotation(ExcelKeyword = "SUB")
    public String SUB; 
    @MQRCModelAnnotation(ExcelKeyword = "DEFPSIST")
    public String DEFPSIST;  
    @MQRCModelAnnotation(ExcelKeyword = "DEFPRESP")
    public String DEFPRESP; 
    @MQRCModelAnnotation(ExcelKeyword = "DEFPRTY")
    public String DEFPRTY; 
    @MQRCModelAnnotation(ExcelKeyword = "PMSGDLV")
    public String PMSGDLV;  
    @MQRCModelAnnotation(ExcelKeyword = "NPMSGDLV")
    public String NPMSGDLV = "ALLAVAIL"; 
    @MQRCModelAnnotation(ExcelKeyword = "WILDCARD")
    public String WILDCARD = "PASSTHROUGH"; 
    @MQRCModelAnnotation(ExcelKeyword = "MDURMDL")
    public String MDURMDL;  
    @MQRCModelAnnotation(ExcelKeyword = "MNDURMDL")
    public String MNDURMDL; 
}
