/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Utilities;

import javax.swing.JProgressBar;

/**
 *
 * @author jzhou
 */
public class ObserverTask implements Observer{
    private Processed processed;
    private JProgressBar progressBar;
    private int total;
    @Override
    public void Action() {
        processed.MessageDeleted++;    
        int value = ( processed.MessageDeleted * 100)/total;
        progressBar.setValue(value <= 100? value : 100);        
    }
    
    public ObserverTask(JProgressBar progressBar, int total, Processed processed){
        this.processed = processed;
        this.progressBar = progressBar;
        this.total = total;
    }
    
}
