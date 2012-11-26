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
 *  NOTE:	The uxml pdu for the API interface. This is a 
 *			data container.
 *
 *  ---------------------------------------------------------
 */

package com.pharos_avantgard.uxmlx;

import java.util.ArrayList;


public class UxmlPdu {

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
    final static int UPDU_MAX_PRAM  = 9; 

	String [] [] m_pairs;
        String msg_root_type;
        ArrayList attribList;

	/** Constructor for the class
	 * @exception None
	 */
    public UxmlPdu () {
        m_pairs = new String [UPDU_MAX_PRAM] [2];
        attribList = new ArrayList();

        //Set the name values for the pairs    
        m_pairs[UPDU_PDU]       [0] = "PDU";
        m_pairs[UPDU_MSISDN]    [0] = "MSISDN";
        m_pairs[UPDU_STRING]    [0] = "STRING";
        m_pairs[UPDU_TID]       [0] = "TID";
        m_pairs[UPDU_REQID]     [0] = "REQID";
        m_pairs[UPDU_ENCODING]  [0] = "ENCODING";
        m_pairs[UPDU_TARIFF]    [0] = "TARIFF";
        m_pairs[UPDU_STATUS]    [0] = "STATUS";
        m_pairs[UPDU_POST_REF]  [0] = "POST_REF";   // For recieving Scripts (for PDU Packets this will be empty)
        
        msg_root_type = "";
        
        clearValues();
    }

	/** getParamCount
	 * Get the number of data (Name & Value) pairs
	 * @return int - number of data pairs
	 * @exception None
	 */
    public int getParamCount ( ) {
        return  UPDU_MAX_PRAM;
    }

	/** clearValues
	 * Initialias all the value entries of the pairs
	 * @exception None
	 */
    public void clearValues () {

        for (int i = 0; i < getParamCount(); i++ ) {
            m_pairs[i][0] = "";
        }
        attribList.clear();
    }

	/** getPairs
	 * This is for internal use. 
	 * Bulk get of the name value pair.
	 * @return String [] [] - data pairs
	 * @exception None
	 */
    public String [] [] getPairs ( ) {
        return  m_pairs;
    }

	/** setPairs
	 * This is for internal use. (When receiving a message; connection:receive)
	 * Bulk set of the name value pair.
	 * @param pairs String [] [] data pairs
	 * @exception None
	 */
    public void setPairs ( String [] [] pairs ) {
		for (int i = 0; i < getParamCount(); i++ ) {
			m_pairs[i][1] = pairs[i][1];
		}
    }

    	/** setAttribs
	 * Consult UXML spec for legal values
	 * @exception None
	 */   
    public void setAttribs ( ArrayList attribs ) {
        attribList = attribs;
    }  

    	/** getAttribs
	 * Consult UXML spec for legal values
	 * @return ArrayList of [{Attribute_name, Attribute_value}, ....]
	 * @exception None
	 */   
    public ArrayList getAttribs ( ) {
        return attribList;
    }      
    
	/** setPdu
	 * Consult UXML spec for legal values
	 * @param pdu String value for the PDU Type
	 * @exception None
	 */
    public void setPdu ( String pdu ) {
        m_pairs[UPDU_PDU][1] = pdu;
    }

	/** getPdu
	 * Consult UXML spec for legal values
	 * @return String - String value for the PDU Type
	 * @exception None
	 */
    public String getPdu (  ) {
        return m_pairs[UPDU_PDU][1];
    }

	/** setMsisdn
	 * Consult UXML spec for legal values
	 * @param msisdn String value for the MSISDN (digit sequnce)
	 * @exception None
	 */
    public void setMsisdn ( String msisdn ) {
        m_pairs[UPDU_MSISDN][1] = msisdn;
    }

	/** getMsisdn
	 * Consult UXML spec for legal values
	 * @return String - String value for the MSISDN (digit sequnce)
	 * @exception None
	 */
    public String getMsisdn (  ) {
        return m_pairs[UPDU_MSISDN][1];
    }

	/** setString
	 * Consult UXML spec for legal values
	 * @param string String value for the string (digit sequnce|message) 
	 * @exception None
	 */
    public void setString ( String string ) {
        m_pairs[UPDU_STRING][1] = string;
    }

	/** getString
	 * Consult UXML spec for legal values
	 * @return String - String value for the string (digit sequnce|message) 
	 * @exception None
	 */
    public String getString (  ) {
        return m_pairs[UPDU_STRING][1];
    }

	/** setTid
	 * Consult UXML spec for legal values
	 * @param tid String value for the transaction id
	 * @exception None
	 */
    public void setTid ( String tid ) {
        m_pairs[UPDU_TID][1] = tid;
    }

	/** getTid
	 * Consult UXML spec for legal values
	 * @return String - String value for the transaction id
	 * @exception None
	 */
    public String getTid (  ) {
        return m_pairs[UPDU_TID][1];
    }

	/** setReqid
	 * Consult UXML spec for legal values
	 * @param Reqid String value for the request id
	 * @exception None
	 */
    public void setReqid ( String Reqid ) {
        m_pairs[UPDU_REQID][1] = Reqid;
    }

	/** getReqid
	 * Consult UXML spec for legal values
	 * @return String - String value for the request id
	 * @exception None
	 */
    public String getReqid (  ) {
        return m_pairs[UPDU_REQID][1];
    }

	/** setEncoding
	 * Consult UXML spec for legal values
	 * @param encoding String value for type of encoding for the message string
	 * @exception None
	 */
    public void setEncoding ( String encoding ) {
        m_pairs[UPDU_ENCODING][1] = encoding;
    }

	/** getEncoding
	 * Consult UXML spec for legal values
	 * @return String - String value for type of encoding for the message string
	 * @exception None
	 */
    public String getEncoding (  ) {
        return m_pairs[UPDU_ENCODING][1];
    }

	/** setTariff
	 * Consult UXML spec for legal values
	 * @param tariff String value for tariff
	 * @exception None
	 */
    public void setTariff ( String tariff ) {
        m_pairs[UPDU_TARIFF][1] = tariff;
    }

	/** getTariff
	 * Consult UXML spec for legal values
	 * @return String - String value for tariff
	 * @exception None
	 */
    public String getTariff (  ) {
        return m_pairs[UPDU_TARIFF][1];
    }

	/** setStatus
	 * Consult UXML spec for legal values
	 * @param status String value for status
	 * @exception None
	 */
    public void setStatus ( String status ) {
        m_pairs[UPDU_STATUS][1] = status;
    }

	/** getStatus
	 * Consult UXML spec for legal values
	 * @return String - String value for status
	 * @exception None
	 */
    public String getStatus (  ) {
        return m_pairs[UPDU_STATUS][1];
    }
    
	/** getPostRef
	 * Consult UXML spec for legal values
	 * @return String - String value for PostRef (only in POST from scripts) otherwise empty
	 * @exception None
	 */    
    public String getPostRef (  ) {
        return m_pairs[UPDU_POST_REF][1];
    }    
    
   	/** setRootType
	 * Consult UXML spec for legal values
	 * @param RootType String value for the root type. USSD or U_SCRIPT_POST
	 * @exception None
	 */
    public void setRootType ( String RootType ) {
        msg_root_type = RootType;
    }    
    
    	/** getRootType
	 * Consult UXML spec for legal values
	 * @return String - String value for Root Type. USSD or U_SCRIPT_POST
	 * @exception None
	 */   
    public String getRootType ( ) {
        return msg_root_type;
    }      
    
}
