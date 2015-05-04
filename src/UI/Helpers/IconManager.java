/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Helpers;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author jzhou
 */
public class IconManager {
    private String ManagerConnected = "Resources/Images/Connected.png";
    private String ManagerDisconnected = "Resources/Images/Disconnected.png";
    private String PutMessage = "Resources/Images/Add File.png";
    private String BrowseMessage = "Resources/Images/View File.png";
    private String DeleteMessage = "Resources/Images/Delete File.png";
    private String Properties = "Resources/Images/Show Property.png";
    private String BackupMessage = "Resources/Images/Download.png";
    private String RestoreMessage = "Resources/Images/Upload.png";
    private String Delete = "Resources/Images/Delete.png";
    private String DeviceManager = "Resources/Images/Device Manager.png";
    private String Queue = "Resources/Images/Queue.png";
    private String Channel = "Resources/Images/Channel.png";
    private String Root = "Resources/Images/Root.png";
    private String queueLocal = "Resources/Images/queueLocalSmall.gif";
    private String queueRemote = "Resources/Images/queueRemoteSmall.gif";
    private String queueAlias = "Resources/Images/queueAliasSmall.gif";
    private String queueTransmit = "Resources/Images/queueTransmissionSmall.gif";
    private String Message = "Resources/Images/Message.png";
    private String CSV = "Resources/Images/CSV.png";
    private String TemporaryObject = "Resources/Images/temporary.png";
    private String Refresh = "Resources/Images/refresh.png";
    private String Search = "Resources/Images/Search.png";
    private String AddNew = "Resources/Images/AddNew.png";
    private String Left = "Resources/Images/Left.png";
    private String Right = "Resources/Images/Right.png";
    private String Start = "Resources/Images/Play.png";
    private String Stop = "Resources/Images/Stop.png";
    private String Resolve = "Resources/Images/Questions.png";
    private String Status = "Resources/Images/Status.png";
 
    public ImageIcon StatusIcon(){
        return getImageIcon(Status);
    }
 
    public ImageIcon ResolveIcon(){
        return getImageIcon(Resolve);
    }
    
    public ImageIcon StartIcon(){
        return getImageIcon(Start);
    }
     
    public ImageIcon StopIcon(){
        return getImageIcon(Stop);
    }
     
    public ImageIcon LeftIcon(){
        return getImageIcon(Left);
    }
    public ImageIcon RightIcon(){
        return getImageIcon(Right);
    }
    
    public ImageIcon AddNewIcon(){
        return getImageIcon(AddNew);
    }
    
    public ImageIcon SearchIcon(){
        return getImageIcon(Search);
    }
    
    public ImageIcon RefreshIcon(){
        return getImageIcon(Refresh);
    }
    
    public ImageIcon TemporaryObjectIcon(){
        return getImageIcon(TemporaryObject);
    }
    
    public ImageIcon CSVIcon(){
        return getImageIcon(CSV);
    }
    public ImageIcon MessageIcon(){
        return getImageIcon(Message);
    }  
    public ImageIcon QueueLocalIcon(){
        return getImageIcon(queueLocal);
    }  

    public ImageIcon QueueRemoteIcon(){
        return getImageIcon(queueRemote);
    }  
    
    public ImageIcon QueueAliasIcon(){
        return getImageIcon(queueAlias);
    }  
    
    public ImageIcon QueueTransmitIcon(){
        return getImageIcon(queueTransmit);
    }  
    
    public ImageIcon QueueMgrConnectedIcon(){
        return getImageIcon(ManagerConnected);
    }   
    public ImageIcon QueueMgrDisconnectedIcon(){
        return getImageIcon(ManagerDisconnected);
    }
    
    public ImageIcon PutMessageIcon(){
        return getImageIcon(PutMessage);
    }
    
    public ImageIcon BrowseMessageIcon(){
        return getImageIcon(BrowseMessage);
    }
    
    public ImageIcon DeleteMessageIcon(){
        return getImageIcon(DeleteMessage);
    }
    
    public ImageIcon PropertiesIcon(){
        return getImageIcon(Properties);
    }
    
    public ImageIcon BackupMessageIcon(){
        return getImageIcon(BackupMessage);
    }
    
    public ImageIcon RestoreMessageIcon(){
        return getImageIcon(RestoreMessage);
    }
    
    public ImageIcon Delete(){
        return getImageIcon(Delete);
    }
    
    public ImageIcon DeviceManager(){
        return getImageIcon(DeviceManager);
    }
    
    public ImageIcon Queue(){
        return getImageIcon(Queue);
    }
    
    public ImageIcon Channel(){
        return getImageIcon(Channel);
    }
    
    public ImageIcon Root(){
        return getImageIcon(Root);
    }
            
    private ImageIcon getImageIcon(String path){
        URL iconPath = getClass().getClassLoader().getResource(path);;
        return new ImageIcon(new ImageIcon(iconPath).getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));
    }    
}
