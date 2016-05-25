/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

/**
 *
 * @author Jianbin Zhou
 */
public class DateTimeModel implements Comparable {
    public String DateString;
    public String TimeString;
    
    public int Year;
    public int Month;
    public int Day;
    public int Hour;
    public int Minute;
    public int Second;
    
    
    public DateTimeModel(String dataString, String timeString){
        this.DateString = dataString;
        this.TimeString = timeString;
        
        ConvertToField();
    }
    
    @Override
    public String toString(){
        return DateString + " " + TimeString;
    }

    @Override
    public int compareTo(Object o) {
        if(DateString != null && TimeString != null && o != null){
            DateTimeModel other = (DateTimeModel)o;
            if(other.DateString != null && other.TimeString != null){
                if(this.Year != other.Year){
                    return compare(this.Year, other.Year);
                }
                else if(this.Month != other.Month){
                    return compare(this.Month, other.Month);
                }
                else if(this.Day != other.Day){
                    return compare(this.Day, other.Day);
                }
                else if(this.Hour != other.Hour){
                    return compare(this.Hour, other.Hour);
                }
                else if(this.Minute != other.Minute){
                    return compare(this.Minute, other.Minute);
                }
                else if(this.Second != other.Second){
                    return compare(this.Second, other.Second);
                }
                else
                    return 0;
            }
            else{
                return -1;
            }
            
        }
        else{
            return -1;
        }
    }
    
    private void ConvertToField(){
        if(DateString != null && TimeString != null){
            String[] thisDate = DateString.trim().split("-");
            String[] thisTime = TimeString.trim().replace(".", "-").split("-");
            Year = Integer.parseInt(thisDate[0]);
            Month = Integer.parseInt(thisDate[1]);
            Day = Integer.parseInt(thisDate[2]);
            Hour = Integer.parseInt(thisTime[0]);
            Minute = Integer.parseInt(thisTime[1]);
            Second = Integer.parseInt(thisTime[2]);
        }
    }
    
    private int compare(int a, int b){
        if(a > b)
            return 1;
        else
            return -1;
    }
    
    
}
