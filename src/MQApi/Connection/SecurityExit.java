/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MQApi.Connection;

import com.ibm.mq.MQChannelDefinition;
import com.ibm.mq.MQChannelExit;
import com.ibm.mq.MQSecurityExit;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author jzhou
 */
public class SecurityExit implements MQSecurityExit{
    
    private String userId;
    private String password;
    
    public SecurityExit(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    @Override
    public byte[] securityExit(MQChannelExit mqce, MQChannelDefinition mqcd, byte[] bytes) {
        mqce.exitResponse = 0;
        String s;
        switch(mqce.exitReason)
        {
        case 11: // '\013'
            s = "MQXR_INIT";
            break;

        case 12: // '\f'
            s = "MQXR_TERM";
            break;

        case 14: // '\016'
            s = "MQXR_XMIT";
            break;

        case 15: // '\017'
            s = "MQXR_SEC_MSG";
            break;

        case 16: // '\020'
            s = "MQXR_INIT_SEC";
            break;

        case 13: // '\r'
        default:
            s = "Unknown: " + mqce.exitReason;
            break;
        }
        switch(mqce.exitReason)
        {
        case 15: // '\017'
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            try
            {
                bytearrayoutputstream.write("SEC1".getBytes());
                bytearrayoutputstream.write(0);
                bytearrayoutputstream.write(0);
                bytearrayoutputstream.write(0);
                bytearrayoutputstream.write(0);
                StringBuffer stringbuffer = new StringBuffer(userId);
                stringbuffer.setLength(12);
                bytearrayoutputstream.write(stringbuffer.toString().getBytes());
                stringbuffer = new StringBuffer(new String(password));
                stringbuffer.setLength(20);
                bytearrayoutputstream.write(stringbuffer.toString().getBytes());
                bytearrayoutputstream.flush();
                byte abyte1[] = bytearrayoutputstream.toByteArray();
                bytearrayoutputstream.close();
                mqce.exitResponse = -4;
                return abyte1;
            } catch(Exception e) {
                e.printStackTrace();
            }
            mqce.exitResponse = -1;
            break;
        }
        return null;
    }
    
}
