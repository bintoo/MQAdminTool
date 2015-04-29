/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Misc;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author jzhou
 */
public class JTextFieldNumLimit extends PlainDocument{
    private int max;

    public JTextFieldNumLimit(int max) {
        super();
        this.max = max;
    }   
    
    @Override
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
            if ( str == null ) {
                return;
            }

            char[] chars = str.toCharArray();
            boolean ok = true;

            for ( int i = 0; i < chars.length; i++ ) {
                try {
                    Integer.parseInt( String.valueOf( chars[i] ) );
                    int value = Integer.parseInt(str);
                    if(value > max){
                        ok = false;
                    }
                } catch ( NumberFormatException exc ) {
                    ok = false;
                    break;
                }
            }

            if ( ok ){
                
                super.insertString( offset, new String( chars ), attr );
            }
    }
}
