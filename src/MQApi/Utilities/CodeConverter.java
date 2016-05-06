/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Utilities;

/**
 *
 * @author Jianbin Zhou
 */
import MQApi.Utilities.MQUtility;
import com.ibm.mq.MQMessage;
 import java.io.BufferedWriter;       
import java.io.ByteArrayInputStream;
 import java.io.FileInputStream; 
 import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

  public class CodeConverter { 
 
 
  public static String convertEBDICtoASCII(MQMessage message) {
      
      try {
          message.seek(0);
          byte[] msgByteArray = new byte[message.getMessageLength()];
          for(int i = 0; i < message.getMessageLength(); i++){
              msgByteArray[i] = message.readByte();
          }
          InputStream str = new ByteArrayInputStream(msgByteArray);
          return new String(msgByteArray, MQUtility.getDefaultCharSet());
       

      } catch (IOException ex) {
          Logger.getLogger(CodeConverter.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return "";
 }


 }
