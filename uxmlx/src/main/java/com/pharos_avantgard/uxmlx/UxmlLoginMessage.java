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
 *  NOTE:	The uxml login that will be received
 *          and send
 *
 *  @see	UxmlMessage
 *  ---------------------------------------------------------
 */

package com.pharos_avantgard.uxmlx;

public class UxmlLoginMessage extends UxmlMessage {

    // Pair index
    final static int ULOGIN_USER     = 0;
    final static int ULOGIN_PASSWORD = 1;
    final static int ULOGIN_RMT_SYS  = 2;
    final static int ULOGIN_NODE_ID  = 3; 
    final static int ULOGIN_COOKIE   = 4; 
    final static int ULOGIN_MAX_PRAM = 5; 


    public UxmlLoginMessage() 
    {
        super();
        // Create the pair values
        m_pairs = new String [ULOGIN_MAX_PRAM] [UXML_MAX_PAIR];

        //Set the name values for the pairs    
        m_pairs[ULOGIN_USER]    [UXML_NAME] = "USER";
        m_pairs[ULOGIN_PASSWORD][UXML_NAME] = "PASSWORD";
        m_pairs[ULOGIN_RMT_SYS] [UXML_NAME] = "RMT_SYS";
        m_pairs[ULOGIN_NODE_ID] [UXML_NAME] = "NODE_ID";
        m_pairs[ULOGIN_COOKIE]  [UXML_NAME] = "COOKIE";

        clearValues();

        // Set abstract class helper values
        m_root = "login";
        m_name = "UxmlLoginMessage";

    }

    public int getParamCount ( ) {
        return  ULOGIN_MAX_PRAM;
    }

    public int getVariableCount ( ) {
        return  0;
    }
    
    public void setUser ( String user ) {
        m_pairs[ULOGIN_USER][UXML_VALUE] = user;
    }

    public String getUser (  ) {
        return m_pairs[ULOGIN_USER][UXML_VALUE];
    }

    public void setPassword ( String password ) {
        m_pairs[ULOGIN_PASSWORD][UXML_VALUE] = password;
    }

    public String getPassword (  ) {
        return m_pairs[ULOGIN_PASSWORD][UXML_VALUE];
    }

    public void setRmt_Sys ( String rmt_sys ) {
        m_pairs[ULOGIN_RMT_SYS][UXML_VALUE] = rmt_sys;
    }

    public String getRmt_Sys (  ) {
        return m_pairs[ULOGIN_RMT_SYS][UXML_VALUE];
    }

    public void setNode_Id ( String node_id ) {
        m_pairs[ULOGIN_NODE_ID][UXML_VALUE] = node_id;
    }

    public String getNode_Id (  ) {
        return m_pairs[ULOGIN_NODE_ID][UXML_VALUE];
    }

    public void setCookie ( String cookie ) {
        m_pairs[ULOGIN_COOKIE][UXML_VALUE] = cookie;
    }

    public String getCookie (  ) {
        return m_pairs[ULOGIN_COOKIE][UXML_VALUE];
    }

}
