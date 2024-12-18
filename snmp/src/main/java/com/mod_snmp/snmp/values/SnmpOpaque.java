package com.mod_snmp.snmp.values;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/

/**
 * The object that does handle a Snmp Octet String.
 */
public class SnmpOpaque implements SnmpValue {
    private final static int DEFAULT_OPAQUE_LENGTH = 25;
    
    private byte bytes[];
    private int length;

    /**
     * Default constructor that creates an opaque octet string of 'DEFAULT_OPAQUE_LENGTH'.
     */
    public SnmpOpaque() {
        this(DEFAULT_OPAQUE_LENGTH);
    }
    /**
     * Constructor that creates an opaque octet string of size length.
     */
    public SnmpOpaque(int size) {
        bytes = new byte[size];
        length = 0;
    }
    /**
     * Constructor of an opaque octet string initialized with the value of string.
     */
    public SnmpOpaque(String str) {
        this.bytes = str.getBytes();
    }
    /**
     * Constructor of an opaque octet string initialized with the value of byte array.
     */
    public SnmpOpaque(byte[] str) {
        this.bytes = str;
    }
    /**
     * Retrieve the prefix of the opaque octet string.
     */
    public String SnmpOpaque(int prefix_nr) {
        String str = new String("");
        if (prefix_nr > length) {
            prefix_nr = length;
        }
        if (prefix_nr > 0) {
            str += bytes[0];
            for (int i = 1; i < prefix_nr; i++) {
                str += "." + bytes[i];
            }
        }
        return str;
    }
    /**
     * Concatenate an opaque octet string.
     */
    public SnmpOpaque concat(SnmpOpaque suffix) {
        int i;
        int j;
        if (length + suffix.length < bytes.length) {
            byte newbytes[] = new byte[length+suffix.length];
            for (i = 0; i < length; i++) {
                newbytes[i] = bytes[i];
            }
            for (i = length, j = 0; j < suffix.length; i++, j++) {
                newbytes[j] = suffix.bytes[i];
            }
            bytes = newbytes;
        }
        for (i = length, j = 0; j < suffix.length; i++, j++) {
            bytes[j] = suffix.bytes[i];
        }
        length += suffix.length;
        return this;
    }
    /**
     * Retrieve the value as a String.               
     */
    public String toString() {
        for (int i = 0; i < bytes.length; i++) {
            int val = ((int)bytes[i] & 0xff);
            if ((val < 0x20) || (0x7f <= val)) {
                return toHexString();
            }
        }
        return new String(bytes);
    }
    /**
     * Retrieve the value as an hexadecimal string.
     */
    public String toHexString() {
        String str = "HEX(" + bytes.length + "):";
        for (int i = 0; i < bytes.length; i++) {
            if (((int)bytes[i] & 0xff) < 0x10) {
                str += " 0";
            } else {
                str += " ";
            }
            str += Integer.toHexString((int)0xff & bytes[i]);
        }
        return str;
    }
    /**
     * Retrieve the value as a byte array.
     */
    public byte[] getBytes() {
        return bytes;
    }
    /**
     * A dumper for debugging purposes.
     */
    public void dump() {
        System.out.println("        value: Opaque   : " + toHexString()); 
    }
}
