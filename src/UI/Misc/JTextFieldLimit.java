/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI.Misc;
import java.util.regex.Pattern;
import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 *
 * @author jzhou
 */
public class JTextFieldLimit extends PlainDocument{
    private int limit;
    Pattern pattern = null;

    public JTextFieldLimit(int limit, String regex) {
        super();
        this.limit = limit;
        if(regex != null){
            this.pattern = Pattern.compile(regex);
        }
    }

    @Override
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
          if (str == null) return;
          
          if(pattern != null && !pattern.matcher(str).matches())
              return;

          if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
          }
    }    
}
