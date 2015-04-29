///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package UI.Misc;
//
//
//import org.webharvest.gui.*;
//
//import java.awt.Color;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Reader;
//
//import javax.swing.JEditorPane;
//import javax.swing.text.BadLocationException;
//import javax.swing.text.DefaultEditorKit;
//import javax.swing.text.Document;
//import javax.swing.text.Element;
//import javax.swing.text.View;
//import javax.swing.text.ViewFactory;
//import org.bounce.text.xml.XMLStyleConstants;
//
//
//
//public class XMLEditorKit extends DefaultEditorKit implements XMLStyleConstants {
//    private static final long serialVersionUID = 3832623997442667569L;
//
//    private XMLContext context  = null;
//    private ViewFactory factory = null;
//    private JEditorPane editor = null;
//
//    private boolean lineWrapping = false;
//    private boolean wrapStyleWord = false;
//
//    private XmlTextPane xmlTextPane;
//
//    /**
//     * Called when the kit is being installed into the
//     * a JEditorPane.  
//     *
//     * @param c the JEditorPane
//     */
//    public void install(JEditorPane c) {
//        super.install( c);
//
//        this.editor = c;
//    }
//
//    /**
//     * Constructs the view factory and the Context.
//     * 
//     * @param lineWrapping enables line wrapping feature if true.
//     */
//    public XMLEditorKit( boolean lineWrapping, XmlTextPane xmlTextPane) {
//        super();
//
//        this.xmlTextPane = xmlTextPane;
//        
//        factory = new XMLViewFactory();
//        context = new XMLContext();
//        
//        this.lineWrapping = lineWrapping;
//    }
//    
//    /**
//     * Returns true when line-wrapping has been turned on.
//     * 
//     * @return state of line-wrapping feature.
//     */
//    public boolean isLineWrapping() {
//        return lineWrapping;
//    }
//
//    /**
//     * Eanbles/disables the line-wrapping feature.
//     * 
//     * @param enabled true when line-wrapping enabled.
//     */
//    public void setLineWrappingEnabled( boolean enabled) {
//        lineWrapping = enabled;
//    }
//
//    /**
//     * Returns true when the wrapping style is 'word wrapping'.
//     * 
//     * @return the style of wrapping.
//     */
//    public boolean isWrapStyleWord() {
//        return wrapStyleWord;
//    }
//
//    /**
//     * Enables/disables the word-wrapping style.
//     * 
//     * @param enabled true when word-wrapping style enabled.
//     */
//    public void setWrapStyleWord( boolean enabled) {
//        wrapStyleWord = enabled;
//    }
//
//    /**
//     * Get the MIME type of the data that this kit represents support for. This
//     * kit supports the type <code>text/xml</code>.
//     * 
//     * @return the type.
//     */
//    public String getContentType() {
//        return "text/xml";
//    }
//
//    /**
//     * Fetches the XML factory that can produce views for XML Documents.
//     * 
//     * @return the XML factory
//     */
//    public final ViewFactory getViewFactory() {
//        return factory;
//    }
//    
//    /**
//     * Set the style identified by the name.
//     * 
//     * @param name the style name
//     * @param foreground the foreground color
//     * @param fontStyle the font style Plain, Italic or Bold
//     */
//    public void setStyle( String name, Color foreground, int fontStyle) {
//        context.setStyle( name, foreground, fontStyle);
//    }
//    
//    /**
//     * @see DefaultEditorKit#createDefaultDocument()
//     */
//    public Document createDefaultDocument() {
//        return new XMLDocument( editor);
//    }
//    
//    /**
//     * @see DefaultEditorKit#read( java.io.Reader, javax.swing.text.Document, int)
//     */
//    public void read( Reader in, Document doc, int pos) throws IOException, BadLocationException {
//        doc.putProperty( XMLDocument.LOADING_ATTRIBUTE, Boolean.TRUE);
//        
//        super.read( in, doc, pos);
//
//        doc.putProperty( XMLDocument.LOADING_ATTRIBUTE, Boolean.FALSE);
//    }
//
//    /**
//     * @see DefaultEditorKit#read( java.io.InputStream, javax.swing.text.Document, int)
//     */
//    public void read( InputStream in, Document doc, int pos) throws IOException, BadLocationException {
//        doc.putProperty( XMLDocument.LOADING_ATTRIBUTE, Boolean.TRUE);
//        
//        super.read( in, doc, pos);
//
//        doc.putProperty( XMLDocument.LOADING_ATTRIBUTE, Boolean.FALSE);
//    }
//
//    /**
//     * A simple view factory implementation.
//     */
//    class XMLViewFactory implements ViewFactory {
//        /**
//         * Creates the XML View.
//         * 
//         * @param elem the root element.
//         * @return the XMLView
//         */
//        public View create( Element elem) {
//            if ( lineWrapping) {
//                try {
//                    return new WrappedXMLView( context, elem, wrapStyleWord);
//                } catch ( IOException e) {
//                    // Instead of an IOException, this will return null if the 
//                    // WrappedXMLView could not be instantiated.
//                    // Should never happen.
//                }
//            } else {
//                try {
//                    return new XMLView(context, elem, xmlTextPane);
//                } catch ( IOException e) {
//                    // Instead of an IOException, this will return null if the 
//                    // XMLView could not be instantiated. 
//                    // Should never happen.
//                }
//            }
//
//            return null;
//        }
//    }
//}
