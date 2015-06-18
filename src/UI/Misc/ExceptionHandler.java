/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Misc;

import MQApi.Logs.LogWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import javax.swing.JOptionPane;

/**
 *
 * @author jzhou
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    private long mainThreadId;
    public ExceptionHandler(long mainThreadId){
        this.mainThreadId = mainThreadId;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        
        LogWriter.WriteToLog(e);
        if(t.getId() == mainThreadId){
            JOptionPane.showMessageDialog(null, "The program has encountered a fatal error and needs to be closed.\n\r\n\rPlease check the log and report to Jianbin for furture improvement.", "MQ Admin Tool Fatal error",JOptionPane.ERROR_MESSAGE );
            System.exit(1);
        }
    }
    private String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
    
    public static void registerExceptionHandler(long mainThreadId) {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(mainThreadId));
      System.setProperty("sun.awt.exception.handler", ExceptionHandler.class.getName());
    }
}
