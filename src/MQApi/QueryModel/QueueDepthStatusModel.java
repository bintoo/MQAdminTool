/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.QueryModel;

/**
 *
 * @author jzhou
 */
public class QueueDepthStatusModel implements Comparable {
    public Integer CurrentDepth;
    public Integer MaxDepth;
    
    @Override
    public String toString(){
        return "" + CurrentDepth;
    }

    @Override
    public int compareTo(Object o) {
        if(o != null){
            QueueDepthStatusModel other = (QueueDepthStatusModel)o;
            if(this.CurrentDepth > other.CurrentDepth)
                return 1;
            else if(this.CurrentDepth == other.CurrentDepth)
                return 0;
            else
                return -1;
        }
        else{
            return -1;
        }
    }
}
