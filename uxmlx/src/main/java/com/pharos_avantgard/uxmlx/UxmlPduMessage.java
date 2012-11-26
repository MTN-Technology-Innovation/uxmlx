/* 
 *  ----------------------------------------------------------
 *      Copyright (c) 2003 by Pharos Consulting (Pty) Ltd
 *  ----------------------------------------------------------
 *
 *  @version	2.0.00 2007-12-05
 *  @author		Trevor Woollacott, Pharos Consulting (Pty) Ltd.
 *	
 *  NOTE:	Updated for UXML-X support with attributes
 *
 *  @version	1.0.01 2003-11-29
 *  @author		Jan Jacobs, Pharos Consulting (Pty) Ltd.
 *	
 *  NOTE:	The uxml pdu that will be received and send
 *
 *  @see	UxmlMessage
 *  ---------------------------------------------------------
 */

package com.pharos_avantgard.uxmlx;

import java.util.ArrayList;


public class UxmlPduMessage extends UxmlMessage {

    // Pair index
    final static int UPDU_PDU       = 0;
    final static int UPDU_MSISDN    = 1;
    final static int UPDU_STRING    = 2;
    final static int UPDU_TID       = 3; 
    final static int UPDU_REQID     = 4; 
    final static int UPDU_ENCODING  = 5; 
    final static int UPDU_TARIFF    = 6; 
    final static int UPDU_STATUS    = 7;
    final static int UPDU_POST_REF  = 8;    // For recieving Scripts (for PDU Packets this will be empty)
    
    final static int UPDU_MAX_PRAM      = 9;        //8
    final static int UPDU_CHILD_Cookie	= 9;        //8
	final static int UPDU_MAX		= 10;        //9


    public UxmlPduMessage () {
        super();
        m_pairs = new String [UPDU_MAX] [UXML_MAX_PAIR];
        attribList = new ArrayList();

        //Set the name values for the pairs    
        m_pairs[UPDU_PDU]       [UXML_NAME] = "PDU";
        m_pairs[UPDU_MSISDN]    [UXML_NAME] = "MSISDN";
        m_pairs[UPDU_STRING]    [UXML_NAME] = "STRING";
        m_pairs[UPDU_TID]       [UXML_NAME] = "TID";
        m_pairs[UPDU_REQID]     [UXML_NAME] = "REQID";
        m_pairs[UPDU_ENCODING]  [UXML_NAME] = "ENCODING";
        m_pairs[UPDU_TARIFF]    [UXML_NAME] = "TARIFF";
        m_pairs[UPDU_STATUS]    [UXML_NAME] = "STATUS";
        m_pairs[UPDU_POST_REF]    [UXML_NAME] = "POST_REF";    // For recieving Scripts (for PDU Packets this will be empty)
        
		m_pairs[UPDU_CHILD_Cookie]   [UXML_NAME] = "VALUE";
    
        clearValues();

        // Set abstract class helper values
        m_root         = "ussd";                // <ussd>
        m_child_cookie = "cookie";              // <cookie>
        m_child_attrib = "attributes";          // <attributes>
        m_name         = "UxmlPduMessage";      // For debugging
    }

    public int getParamCount ( ) {
        return  UPDU_MAX_PRAM;
    }

    
    public int getVariableCount ( ) {
        return  0;
    }
    
    public void setPdu ( String pdu ) {
        m_pairs[UPDU_PDU][UXML_VALUE] = pdu;
    }

    public String getPdu (  ) {
        return m_pairs[UPDU_PDU][UXML_VALUE];
    }

    public void setMsisdn ( String msisdn ) {
        m_pairs[UPDU_MSISDN][UXML_VALUE] = msisdn;
    }

    public String getMsisdn (  ) {
        return m_pairs[UPDU_MSISDN][UXML_VALUE];
    }

    public void setString ( String string ) {
        m_pairs[UPDU_STRING][UXML_VALUE] = string;
    }

    public String getString (  ) {
        return m_pairs[UPDU_STRING][UXML_VALUE];
    }

    public void setTid ( String tid ) {
        m_pairs[UPDU_TID][UXML_VALUE] = tid;
    }

    public String getTid (  ) {
        return m_pairs[UPDU_TID][UXML_VALUE];
    }

    public void setReqid ( String reqid ) {
        m_pairs[UPDU_REQID][UXML_VALUE] = reqid;
    }

    public String getReqid (  ) {
        return m_pairs[UPDU_REQID][UXML_VALUE];
    }

    public void setEncoding ( String encoding ) {
        m_pairs[UPDU_ENCODING][UXML_VALUE] = encoding;
    }

    public String getEncoding (  ) {
        return m_pairs[UPDU_ENCODING][UXML_VALUE];
    }

    public void setTariff ( String tariff ) {
        m_pairs[UPDU_TARIFF][UXML_VALUE] = tariff;
    }

    public String getTariff (  ) {
        return m_pairs[UPDU_TARIFF][UXML_VALUE];
    }

    public void setStatus ( String status ) {
        m_pairs[UPDU_STATUS][UXML_VALUE] = status;
    }

    public String getStatus (  ) {
        return m_pairs[UPDU_STATUS][UXML_VALUE];
    }

    public void setCookie ( String cookie ) {
        m_pairs[UPDU_CHILD_Cookie][UXML_VALUE] = cookie;
    }

    public String getCookie (  ) {
        return m_pairs[UPDU_CHILD_Cookie][UXML_VALUE];
    }

    public String getPostRef (  ) {
        return m_pairs[UPDU_POST_REF][UXML_VALUE];
    }        
}
