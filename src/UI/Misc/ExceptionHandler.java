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

/**
 *
 * @author jzhou
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogWriter.WriteToLog(e);
    }
    private String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
    
    public static void registerExceptionHandler() {
      Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
      System.setProperty("sun.awt.exception.handler", ExceptionHandler.class.getName());
    }
}
