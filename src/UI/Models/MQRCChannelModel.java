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
public class MQRCChannelModel {
    @MQRCModelAnnotation(ExcelKeyword = "Platform to define on", loop = false)
    public String Platform;
    @MQRCModelAnnotation(ExcelKeyword = "MQ Queue Manager *", loop = false)
    public String QueueManager;
    @MQRCModelAnnotation(ExcelKeyword = "Channel Name *", createNewModel = true)
    public String Name;
    @MQRCModelAnnotation(ExcelKeyword = "Channel Type *")
    public String Type;
    @MQRCModelAnnotation(ExcelKeyword = "SHARE (defaults 10)")
    public String Share;
    @MQRCModelAnnotation(ExcelKeyword = "Short Description *")
    public String ShortDescription;


    
    
}
