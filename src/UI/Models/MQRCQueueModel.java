/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Models;

/**
 *
 * @author jzhou
 */
public class MQRCQueueModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Queue Name *", createNewModel = true)
    public String QueueName;
    @MQRCModelAnnotation(ExcelKeyword = "Queue Type *")
    public String QueueType;
    @MQRCModelAnnotation(ExcelKeyword = "Cluster Name (if applicable) *")
    public String Cluster;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String ShortDescription;
    @MQRCModelAnnotation(ExcelKeyword = "PUT")
    public String Put;
    @MQRCModelAnnotation(ExcelKeyword = "GET")
    public String Get;
    @MQRCModelAnnotation(ExcelKeyword = "DEFBIND")
    public String Defbind;
    @MQRCModelAnnotation(ExcelKeyword = "DEFPSIST")
    public String DEFPSIST;
    @MQRCModelAnnotation(ExcelKeyword = "DEFPRTY")
    public String DEFPRTY;
    @MQRCModelAnnotation(ExcelKeyword = "DEFSOPT")
    public String DEFSOPT;
    @MQRCModelAnnotation(ExcelKeyword = "HARDENBO")
    public String HARDENBO;
    @MQRCModelAnnotation(ExcelKeyword = "MAXDEPTH")
    public String MAXDEPTH;
    @MQRCModelAnnotation(ExcelKeyword = "MAXMSGL")
    public String MAXMSGL;
    @MQRCModelAnnotation(ExcelKeyword = "MSGDLVSQ")
    public String MSGDLVSQ;
    @MQRCModelAnnotation(ExcelKeyword = "NPMCLASS")
    public String NPMCLASS;
    @MQRCModelAnnotation(ExcelKeyword = "SHARE")
    public String SHARE;
    @MQRCModelAnnotation(ExcelKeyword = "INDXTYPE")
    public String INDXTYPE;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGTYPE")
    public String TRIGTYPE;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGGER")
    public String TRIGGER;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGMPRI")
    public String TRIGMPRI;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGDPTH")
    public String TRIGDPTH;
    @MQRCModelAnnotation(ExcelKeyword = "INITQ")
    public String INITQ;
    @MQRCModelAnnotation(ExcelKeyword = "PROCESS")
    public String PROCESS;
    @MQRCModelAnnotation(ExcelKeyword = "TRIGDATA")
    public String TRIGDATA;

    
    
}
