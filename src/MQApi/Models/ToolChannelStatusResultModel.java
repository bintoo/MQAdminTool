/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Models;

/**
 *
 * @author Jianbin Zhou
 */
public class ToolChannelStatusResultModel implements Comparable<ToolChannelStatusResultModel> {
    public String Key;
    public int Before;
    public int After;
    public int Change;


    @Override
    public int compareTo(ToolChannelStatusResultModel o) {
        return Integer.compare(this.Change, o.Change);
    }
}
