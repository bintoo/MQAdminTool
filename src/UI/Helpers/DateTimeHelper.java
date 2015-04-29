/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jzhou
 */
public class DateTimeHelper {
    
    private static final String format = "yyyy-MM-dd HH:mm:ss";
    
    public static String GetCurrentTimeStamp(){
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
    
    public static String GetCustomTimeStamp(Date date){
        return new SimpleDateFormat(format).format(date.getTime());
    }
    
    public static String GetCurrentAddSecondTimeStamp(int second){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,  second);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());        
    }
}
