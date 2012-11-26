/*
 *  ----------------------------------------------------------
 *      Copyright (c) 2006 by Pharos Consulting (Pty) Ltd
 *  ----------------------------------------------------------
 *
 *  @version	2.0.00 2007-12-07
 *  @author		Trevor Woollacott, Pharos Avantgard (Pty) Ltd.
 *
 *  NOTE:	- UXML-X
 *                Added functionality to parse_message to handle the 
 *                <attributes A="A" B="B" .../> child node. The attributes
 *                get stored in an ArrayList of type 
 *                String[] = {attribName, attribValue}
 *
 *  @version	1.0.03 2006-11-09
 *  @author		Trevor Woollacott, Pharos Consulting (Pty) Ltd.
 *
 *  NOTE:	- Added code in generateMessage to include variables
 *              if it is a UxmlScript. Variables node (<vars>)
 *              gets added before the cookie
 *              - Changed serialization
 *
 *  @version	1.0.02 2003-02-20
 *  @author		Jan Jacobs, Pharos Consulting (Pty) Ltd.
 *
 *  NOTE:	This forms the basis of the uxml messages that
 *			shall send and received  over the socket.
 *          The functional messages shall inherit from this.
 *
 *  @see	UxmlLoginMessage
 *  @see	UxmlLoginRespMessage
 *  @see	UxmlPduMessage
 *  ---------------------------------------------------------
 */

package com.pharos_avantgard.uxmlx;

import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.xml.serialize.*; 

//import com.sun.org.apache.xml.internal.serialize.OutputFormat;// test for different serializer
//import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
//import com.sun.org.apache.xml.internal.serialize.*;

import java.io.*;
import java.util.*;

public abstract class UxmlMessage {
    // Pair:
    public final static int UXML_NAME     = 0;
    public final static int UXML_VALUE    = 1;
    public final static int UXML_MAX_PAIR = 2;

	// XML document prolog
	// public final static String  m_prolog = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	
    // Normalized data
    String [] [] m_pairs;
    ArrayList attribList = new ArrayList();

    // Helper strings to make life easier
    String m_root;      //XML document name, eg login, ussd
	String m_child_cookie;    // XML documents cookie
        String m_child_variables; // XML documents variables
        String m_child_attrib;    // XML documents attribute
	String m_name;            // XML PDU name - used for debugging
	String m_serialout;       // DOM serialized output string
	
        String m_root_type;       // either "ussd" or "u_script_post""
	boolean m_debug;

    public UxmlMessage () {
        attribList.clear();        
        m_pairs = null;
        m_root  = null;
        m_name  = null;
		m_child_cookie    = null;
                m_child_variables = null;
		m_debug           = false;
		m_serialout       = null;
                m_root_type       = "";     
	}

    abstract public int getParamCount ( );
    abstract public int getVariableCount ( );

    public void clearValues () 
    {
        for (int i = 0; i < getParamCount(); i++ ) {
            m_pairs[i][UXML_VALUE] = "";
        }
        attribList.clear();
    }

    public String [] [] getPairs ( ) {
        return  m_pairs;
    }
    
    public ArrayList getAttribs ( ) {
        return attribList;
    }
    
    public String getRootType ( ) {
        return m_root_type;
    }    

    public void setPairs ( String [] [] pairs ) {
        if (m_debug) { System.out.println("setPairs in UXMLMESSAGE"); }
        for (int i = 0; i < getParamCount(); i++ ) {
                m_pairs[i][UXML_VALUE] = pairs[i][UXML_VALUE];
        }
    }

    public void setDebug ( boolean debug )	{
            m_debug = debug;
    }

    public String generateMessage() {
        
        if (m_debug) { System.out.println("Generating Message..."); }
        
        Document doc;
        
        try {
            
            // Create the document manaufacture
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
            
            // Create Root Element
            Element root = doc.createElement(m_root);
            
            // Create element
            for (int i = 0; i < getParamCount(); i++) {
                // Make sure we only add the POST_REF Value if we are a script (ie. have variables)
                if (((m_child_variables == null) && (!m_pairs[i][UXML_NAME].equals("POST_REF"))) ||
                        (m_child_variables != null))
                    root.setAttribute( m_pairs[i][UXML_NAME], m_pairs[i][UXML_VALUE] );
            }
            
            // ----------------------------------------------------
            // Check if we have variables. ie. is it a UxmlScript?
            // ----------------------------------------------------
            if (m_child_variables == null) {
                // ----------------------------------
                // No Variables, so just add Cookie
                // ----------------------------------
                if (m_child_cookie != null) {
                    Element item = doc.createElement(m_child_cookie);
                    item.setAttribute( m_pairs[getParamCount()][UXML_NAME], m_pairs[getParamCount()][UXML_VALUE] );
                    root.appendChild( item );
                }
            }
            
            if (m_child_variables != null) {
                // ----------------------------------
                // Add variable list then the cookie
                // ----------------------------------
                Element variditem = null;
                //Node n = null;
                Element varitem = doc.createElement(m_child_variables);
                
                for (int i = getParamCount(); i < getParamCount()+getVariableCount(); i++) {
                    variditem = doc.createElementNS(null, "var");
                    variditem.setAttributeNS(null, "ID", m_pairs[i][UXML_NAME]);
                    variditem.setAttributeNS(null, "VALUE", m_pairs[i][UXML_VALUE]);
                    varitem.appendChild(variditem);
                }
                root.appendChild( varitem );
                
                // -------------------------------
                // Now add cookie
                // -------------------------------
                if ((m_child_cookie != null) && (m_child_variables == null)) {  // only add cookie if cookie has a value and it's not a SCRIPT
                    Element item_cookie = doc.createElement(m_child_cookie);
                    item_cookie.setAttribute( m_pairs[getParamCount()+getVariableCount()][UXML_NAME], m_pairs[getParamCount()+getVariableCount()][UXML_VALUE] );
                    root.appendChild( item_cookie );
                }
            }
            
            // Add Root to Document
            doc.appendChild( root );
            
            // Serialize the output
            // m_serialout = doc.getDocumentElement().toString();    // Jan's old serializer'
            
            // ----------------------------------------------------------
            // 2006-09-26: New Serializer
            // ----------------------------------------------------------
            OutputFormat format = new OutputFormat(doc);
            format.setEncoding( "ISO-8859-1" );
            
            StringWriter strWriter = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(strWriter, format);

            serializer.asDOMSerializer();
            serializer.serialize(doc.getDocumentElement());
            m_serialout = strWriter.toString();
            // ----------------------------------------------------------
            
            // Add the XML prolog to the serialized XML doc
            // m_serialout = m_serialout;
            
            if (m_debug) {
                System.out.println("Serialized");
                String str = "Message:" + m_name + ":\n" + m_serialout;
                System.out.println(str);
            }
            
            return m_serialout;
            
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }
        
        return null;
    }

    public boolean parseMessage( String message ) throws Exception{
        Document doc;
        
        if (m_debug) {
            System.out.println(	"\nDebug -> PDU received: " + message );
        }
        
        try {
            
            if(message == null || message.trim().length() <= 0)
                throw new Exception("Error, trying to parse an empty/null message.");
            
            // Creat the document manaufacture
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new InputSource(new CharArrayReader( message.toCharArray() )));
            
            // Get Root Element
            Element root = doc.getDocumentElement();
            m_root_type = root.getTagName();
            System.out.println(":m_root_type:"+m_root_type);
            if (m_debug) {
                System.out.println(	"Root POST (root we recieve): " + m_root_type );
            }
            
            // Get attributes
            NamedNodeMap attrbs = root.getAttributes();
            
            // Populate the normilized data
            for (int i = 0; i < getParamCount(); i ++ ) {
                if (attrbs.getNamedItem (m_pairs[i][UXML_NAME]) != null) {
                    Node value = attrbs.getNamedItem(m_pairs[i][UXML_NAME]);
                    m_pairs[i][UXML_VALUE] = value.getNodeValue(); }
                else {
                    // If there is an attribute missing we are probably not a script
                    // because normal ussd wont have POST_REF
                    m_pairs[i][UXML_VALUE] = "";    // probably POST_REF when root = "ussd"
                }
                //System.out.println(i+": m_pairs[i][UXML_NAME]:"+m_pairs[i][UXML_NAME]+"; val:"+m_pairs[i][UXML_VALUE]);
                
            }
            attribList.clear();
            NodeList Nd= root.getChildNodes();
            int childLen = Nd.getLength();
            for (int i=0; i < childLen; i++){
                Node Attrib = Nd.item(i);//getFirstChild();
                //System.out.println("childnodes?"+root.hasChildNodes()+"; Attrib:"+Attrib.getNodeName());
                if (Attrib.getNodeName().equals(m_child_attrib)){
                    NamedNodeMap tmpAttribs = Attrib.getAttributes();
                    for (int j=0; j < tmpAttribs.getLength(); j++){
                        Node Item = tmpAttribs.item(j);
                        //System.out.println("Attrib:"+Item.getNodeName()+"; "+Item.getNodeValue());
                        String[] Att = {Item.getNodeName(), Item.getNodeValue()};
                        attribList.add(Att);
                    }
                }
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return true;
    }
    
}