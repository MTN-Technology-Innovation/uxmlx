/*
 * UxmlPacket.java
 *
 *   ----------------------------------------------------------
 *      Copyright (c) 2007 by Pharos-AvantGard (Pty) Ltd
 *  ----------------------------------------------------------
 *
 *  @version	2.0.01 2007-12-24
 *  @author		Pharos-AvantGard (Pty) Ltd.
 *  Notes:      - Added support for NOTIFY and PROMPT PDU
 *
 *  @version	2.0.00 2007-12-11
 *  @author		Pharos-AvantGard (Pty) Ltd.
 *  Notes:      - Added support for attributes to support UXML-X
 *
 *  @version	1.0.00 2006-12-11
 *  @author		Pharos-AvantGard (Pty) Ltd.
 *
 *
 */

package com.pharos_avantgard.uxmlx;

import java.io.*;
import java.util.ArrayList;

public class UxmlPacket {
    public final UxmlPdu pdu = new UxmlPdu ();    
    
    /** Creates a new instance of UxmlPacket */
    public UxmlPacket() {
        pdu.clearValues();
    }

    /**
     * clear pdu
     */
    public void clearValues() {
        pdu.clearValues();
    }      
    
        /**
     * getMessage
     * Receives message
     * @param Connection connection
     * @return Boolean - true if meesage recieved
     */
    public boolean getMessage(UxmlConnection Connection){
        boolean result;
        try
        {
                result = Connection.receive ( pdu );
	}catch(IOException e)
	{
		System.out.println("IOException :" + e.getMessage());
                result = false;
	}            
        return result;
    }
         
         /**
     * checkConnection
     * Sends CTRL to check connection or to keep it alive
     * @return boolean - true = successful send
     * @param Connection connection
     */    
    public boolean checkConnection(UxmlConnection Connection) {
        pdu.setPdu("CTRL");
        pdu.setStatus("0");
        pdu.setMsisdn("*");
        pdu.setEncoding("ASCII");
        pdu.setTariff("*");
        pdu.setTid("0");
        pdu.setReqid("0");
        pdu.setString("");
        return Connection.send ( pdu );
    }
    
         /**
     * ussrrSend
     * @return boolean - true = successful send
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     */
    public boolean ussrrSend(UxmlConnection Connection, String Text, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("USSRR");
        pdu.setString(Text);
        pdu.setReqid(ReqID);
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);
        return Connection.send ( pdu );
    }
    
             /**
     * ussnr_send
     * @return boolean - true = successful send
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     */
    public boolean ussnrSend(UxmlConnection Connection, String Text, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("USSNR");
        pdu.setReqid(ReqID);        
        pdu.setString(Text);
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);        
        return Connection.send ( pdu );
    }
    
        /**
     * pssrc_send
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     * @return boolean - true = successful send
     */
    public boolean pssrcSend(UxmlConnection Connection, String Text, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("PSSRC");
        pdu.setString(Text);
        pdu.setReqid(ReqID);
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);        
        return Connection.send ( pdu );
    }    

    /**
     * 
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param MSISDN MSISDN
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     * @param Tariff tariff
     * @return boolean - true = successful send
     */
   public boolean pssrcSend(UxmlConnection Connection, String Text, String MSISDN, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("PSSRC");
        pdu.setString(Text);
        pdu.setMsisdn(MSISDN);   
        pdu.setReqid(ReqID);   
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);        
        return Connection.send ( pdu );
    }  
   
        /**
     * promptSend
     * @return boolean - true = successful send
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     */
    public boolean promptSend(UxmlConnection Connection, String Text, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("PROMPT");
        pdu.setString(Text);
        pdu.setTid("0");        
        pdu.setReqid(ReqID);
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);
        return Connection.send ( pdu );
    }   
    
   
     /**
     * notifySend
     * @return boolean - true = successful send
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param ReqID string value for Session Number
     * @param ENCODING string value ASCII|...
     */
    public boolean notifySend(UxmlConnection Connection, String Text, String ReqID, String ENCODING, String Tariff) {
        pdu.setPdu("NOTIFY");
        pdu.setTid("0");
        pdu.setString(Text);
        pdu.setReqid(ReqID);
        pdu.setEncoding(ENCODING);
        pdu.setTariff(Tariff);
        return Connection.send ( pdu );
    }     

     /**
     * abortSend
     * 
     * @param Tariff tariff
     * @param Connection connection
     * @param Text string value for the string (digit sequnce|message)
     * @param Status string reason for the error
     * @param ReqID string value for Session Number
     * @return boolean - true = successful send
     */
    public boolean abortSend(UxmlConnection Connection, String Text, String Status, String ReqID, String Tariff) {
        pdu.setPdu("ABORT");
        pdu.setStatus(Status);        
        pdu.setString(Text);
        pdu.setReqid(ReqID); 
        pdu.setTariff(Tariff);       
        return Connection.send ( pdu );
    }
	/**
     * getPdu
     * Consult UXML spec for legal values
     * 
     * @return String - String value for PDU
     */    
    public String getPdu() {
        return pdu.getPdu();
    }   
    
	/**
     * getMsisdn
     * Consult UXML spec for legal values
     * 
     * @return String - String value for MSISDN
     */      
    public String getMsisdn() {
        return pdu.getMsisdn();
    }   
    
    	/**
     * getString
     * Consult UXML spec for legal values
     * 
     * @return String - String value for UXML String attribute
     */  
    public String getString() {
        return pdu.getString();
    }       

        /**
     * getReqid
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Required ID (reqid)
     */  
    public String getReqid() {
        return pdu.getReqid();
    }       
    
        /**
     * getEncoding
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Encoding
     */  
    public String getEncoding() {
        return pdu.getEncoding();
    }      
    
        /**
     * getTariff
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Tariff
     */     
    public String getTariff() {
        return pdu.getTariff();
    }   
    
        /**
     * getStatus
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Status
     */      
    public String getStatus() {
        return pdu.getStatus();
    }    
    
        /**
     * getPostRef
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Post Ref
     */     
    public String getPostRef() {
        return pdu.getPostRef();
    }
    
        /**
     * getRootType
     * Consult UXML spec for legal values
     * 
     * @return String - String value for Root Type
     */     
    public String getRootType() {
        return pdu.getRootType();
    }
    
        /**
     * getAttribute
     * Consult UXML spec for legal values - Only for PSSRR and PSSDR
     * @return String - String value for Root Type
     * @param attribName specify the name of the attribute to receive it's value
     */     
    public String getAttribute(String attribName) {
        if ((pdu.getPdu().equals("PSSRR")) || (pdu.getPdu().equals("PSSDR"))) {
            ArrayList attribs = pdu.getAttribs();
            for (int i=0; i < attribs.size(); i++){
                String[] attrib = (String[])attribs.get(i);
                if (attrib[0].equals(attribName)) return attrib[1];
            }
        }
        return null;
    }
    
    /**
     * 
     * 
     * @param connection connection
     */
    public void send(UxmlConnection connection) {
        connection.send ( pdu );
    }
        
}
