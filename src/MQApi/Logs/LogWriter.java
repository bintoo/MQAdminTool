/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Logs;

import MQApi.Enums.LogType;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author jzhou
 */
public class LogWriter {
    private static Logger logger = Logger.getLogger("MQApi");
    private static boolean isLoggerReady = false;
    
    public static void SetFile(){
        String fileName = (new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())) + "_Logs.txt";
        try {
            File dir = new File("Logs");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File logFile = new File(dir,fileName);
            if(!logFile.exists()){
                try {
                    logFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            FileHandler fileTxt = new FileHandler(logFile.getAbsolutePath(),true);
            fileTxt.setFormatter(new SimpleFormatter());
            logger.addHandler(fileTxt);
            logger.setUseParentHandlers(false);
            isLoggerReady = true;
        } catch (IOException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
            isLoggerReady = false;
        } catch (SecurityException ex) {
            Logger.getLogger(LogWriter.class.getName()).log(Level.SEVERE, null, ex);
            isLoggerReady = false;
        }
        
    }
    public static void WriteToLog(String className, String methodName, Exception ex){
        if(!isLoggerReady){
            SetFile();
            if(isLoggerReady){
                logger.logp(Level.SEVERE, className, methodName, ex.getMessage());
            }
        }
        else{
            logger.logp(Level.SEVERE, className, methodName, ex.getMessage());
        }
      
    }
}
