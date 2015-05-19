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
public class MQRCQueueModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Queue Name *", createNewModel = true, ApplyForType = {})
    public String QueueName;
    @MQRCModelAnnotation(ExcelKeyword = "Queue Type *", ApplyForType = {})
    public String QueueType;
    @MQRCModelAnnotation(ExcelKeyword = "Cluster Name (if applicable) *", ApplyForType = {})
    public String Cluster;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *", ApplyForType = {})
    public String ShortDescription;
    @MQRCModelAnnotation(ExcelKeyword = "PUT", ApplyForType = {"Local Queue,Remote Queue"})
    public String Put = "ENABLED";
    @MQRCModelAnnotation(ExcelKeyword = "GET", ApplyForType = {"Local Queue"})
    public String Get = "ENABLED";
    @MQRCModelAnnotation(ExcelKeyword = "DEFBIND", ApplyForType = {"Local Queue,Remote Queue"})
    public String DEFBIND = "OPEN";
    @MQRCModelAnnotation(ExcelKeyword = "DEFPSIST", ApplyForType = {"Local Queue,Remote Queue"})
    public String DEFPSIST = "YES";
    @MQRCModelAnnotation(ExcelKeyword = "DEFPRTY", ApplyForType = {"Local Queue,Remote Queue"})
    public String DEFPRTY = "0";
    @MQRCModelAnnotation(ExcelKeyword = "DEFSOPT", ApplyForType = {"Local Queue"})
    public String DEFSOPT = "SHARED";
    @MQRCModelAnnotation(ExcelKeyword = "HARDENBO", ApplyForType = {"Local Queue"})
    public String HARDENBO = "HARDENBO";
    @MQRCModelAnnotation(ExcelKeyword = "MAXDEPTH", ApplyForType = {"Local Queue"})
    public String MAXDEPTH = "50000";
    @MQRCModelAnnotation(ExcelKeyword = "MAXMSGL", ApplyForType = {"Local Queue"})
    public String MAXMSGL = "4194304";
    @MQRCModelAnnotation(ExcelKeyword = "MSGDLVSQ", ApplyForType = {"Local Queue"})
    public String MSGDLVSQ = "PRIORITY";
    @MQRCModelAnnotation(ExcelKeyword = "NPMCLASS", ApplyForType = {"Local Queue"})
    public String NPMCLASS = "NORMAL";
    @MQRCModelAnnotation(ExcelKeyword = "SHARE", ApplyForType = {"Local Queue"})
    public String SHARE = "SHARE";
    @MQRCModelAnnotation(ExcelKeyword = "INDXTYPE", ApplyForType = {"Local Queue"})
    public String INDXTYPE = "NONE";
    @MQRCModelAnnotation(ExcelKeyword = "TRIGTYPE", ApplyForType = {"Local Queue"})
    public String TRIGTYPE = "FIRST";
    @MQRCModelAnnotation(ExcelKeyword = "TRIGGER", ApplyForType = {"Local Queue"})
    public String TRIGGER = "NO";
    @MQRCModelAnnotation(ExcelKeyword = "TRIGMPRI", ApplyForType = {"Local Queue"})
    public String TRIGMPRI = "0";
    @MQRCModelAnnotation(ExcelKeyword = "TRIGDPTH", ApplyForType = {"Local Queue"})
    public String TRIGDPTH = "1";
    @MQRCModelAnnotation(ExcelKeyword = "INITQ", ApplyForType = {"Local Queue"})
    public String INITQ;
    @MQRCModelAnnotation(ExcelKeyword = "PROCESS", ApplyForType = {"Local Queue"})
    public String PROCESS;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGDATA", ApplyForType = {"Local Queue"})
    public String TRIGDATA;

    
    
}
