/* 
 *  ----------------------------------------------------------
 *      Copyright (c) 2003 by Pharos Consulting (Pty) Ltd
 *  ----------------------------------------------------------
 *
 *  @version	2.0.00 2007-12-05
 *  @author		Trevor Woollacott, Pharos Consulting (Pty) Ltd.
 *	
 *  NOTE:	Updated version to 2.0.00 for UXML-X
 *
 *  @version	1.0.01 2003-11-29
 *  @author		Jan Jacobs, Pharos Consulting (Pty) Ltd.
 *	
 *  NOTE:	The uxml login rsponse that will be received
 *          and send
 *
 *  @see	UxmlMessage
 *  ---------------------------------------------------------
 */

package com.pharos_avantgard.uxmlx;


public class UxmlLoginRespMessage extends UxmlMessage {

    // Pair index
    final static int ULOGINRESP_COOKIE      = 0; 
    final static int ULOGINRESP_STATUS      = 1; 
    final static int ULOGINRESP_MAX_PRAM    = 2; 

    public UxmlLoginRespMessage () 
    {
        super();
        m_pairs = new String [ULOGINRESP_MAX_PRAM] [UXML_MAX_PAIR];

        //Set the name values for the pairs    
        m_pairs[ULOGINRESP_COOKIE]  [UXML_NAME] = "VALUE";
        m_pairs[ULOGINRESP_STATUS]  [UXML_NAME] = "STATUS";

        clearValues();

        // Set abstract class helper values
        m_root = "cookie";
        m_name = "UxmlLoginRespMessage";

    }

    public int getParamCount ( ) {
        return  ULOGINRESP_MAX_PRAM;
    }

    public int getVariableCount ( ) {
        return  0;
    }
    
    public void setCookie ( String cookie ) {
        m_pairs[ULOGINRESP_COOKIE][UXML_VALUE] = cookie;
    }

    public String getCookie (  ) {
        return m_pairs[ULOGINRESP_COOKIE][UXML_VALUE];
    }
    
   public String getStatus (  ) {
        return m_pairs[ULOGINRESP_STATUS][UXML_VALUE];
    }    
}
