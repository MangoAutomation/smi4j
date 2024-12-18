package com.mod_snmp.snmp.manager;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/

import com.mod_snmp.snmp.protocol.SnmpPdu;

/**
 * Exception handler for the SnmpManager.
 */
public class SnmpManagerException extends Exception {
    /**
     * Exception without an cause identified.
     */
    public SnmpManagerException() {
        super();
    }
    /**
     * Exception with an identified cause.
     */
    public SnmpManagerException(String e) {
        super(e);
    }
    /**
     * Exception caught when the response from the SNMP target
     * contained an application level error.
     * For the SNMP-PDU this means the error-status and error-index were set.
     */
    public SnmpManagerException(SnmpPdu pdu) {
        super(pdu.getVarbindList().varbindAt(pdu.getErrorIndex() - 1).name +
                                     " : " + pdu.getErrorStatus());
    }
}
