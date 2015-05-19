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
public class MQRCChannelModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Channel Name *", createNewModel = true, ApplyForType = {})
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Channel Type *", ApplyForType = {})
    public String Type;
    @MQRCModelAnnotation(ExcelKeyword = "SHARE (defaults 10)",ApplyForType = {})
    public String Share;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *", ApplyForType = {})
    public String ShortDescription;
    @MQRCModelAnnotation(ExcelKeyword = "DISCINT", ApplyForType = {"SDR"})
    public String DISCINT;
    @MQRCModelAnnotation(ExcelKeyword = "HBINT", ApplyForType = {"SDR","RCVR","SVRCONN"})
    public String HBINT = "300";
    @MQRCModelAnnotation(ExcelKeyword = "MAXMSGL", ApplyForType = {"SDR","RCVR","SVRCONN"})
    public String MAXMSGL = "4194304";
    @MQRCModelAnnotation(ExcelKeyword = "MCATYPE", ApplyForType = {"SDR"})
    public String MCATYPE = "PROCESS";
    @MQRCModelAnnotation(ExcelKeyword = "MCAUSER", ApplyForType = {"SDR","SVRCONN"})
    public String MCAUSER;
    @MQRCModelAnnotation(ExcelKeyword = "LONGRTY", ApplyForType = {"SDR"})
    public String LONGRTY = "999999999";
    @MQRCModelAnnotation(ExcelKeyword = "LONGTMR", ApplyForType = {"SDR"})
    public String LONGTMR = "1200";
    @MQRCModelAnnotation(ExcelKeyword = "SHORTTMR", ApplyForType = {"SDR"})
    public String SHORTTMR = "60";
    @MQRCModelAnnotation(ExcelKeyword = "TRPTYPE", ApplyForType = {"SDR","RCVR","SVRCONN"})
    public String TRPTYPE  = "TCP";
    @MQRCModelAnnotation(ExcelKeyword = "KAINT", ApplyForType = {"SDR","RCVR","SVRCONN"})
    public String KAINT = "AUTO";
    @MQRCModelAnnotation(ExcelKeyword = "SSLCAUTH", ApplyForType = {"RCVR","SVRCONN"})
    public String SSLCAUTH = "REQUIRED";
    

    
    
}
