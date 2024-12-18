package com.mod_snmp.snmp.tools;
/* Copyright 2000-2013 Harrie Hazewinkel. All rights reserved.*/

import com.mod_snmp.snmp.manager.Arguments;
import com.mod_snmp.snmp.manager.ManagedInstance;
import com.mod_snmp.snmp.manager.SnmpManager;
import com.mod_snmp.snmp.protocol.SnmpMessageException;
import com.mod_snmp.snmp.protocol.SnmpPdu;
import com.mod_snmp.snmp.values.SnmpValueException;
import com.mod_snmp.snmp.values.Varbind;
import com.mod_snmp.snmp.values.VarbindList;
import net.lisanza.cliargs.ArgsException;

/**
 * Retrieving all managed instances of an remote SNMP agent.
 * This class is an example on how to retrieve a list of management
 * instances in the form of a so-called SNMP-walk.
 * <P>
 * The SNMP-walk implemented in the class is done by looping with an
 * SNMP-GETNEXT until the 'EndOfMIBView' exception is retrieved as value.
 * During the loop the retrieved object identifier is used for the next
 * SNMP request.
 * </P>
 */

public class SnmpWalk {
    private SnmpManager mgr;

    private SnmpWalk(SnmpManager a) {
         mgr = a;
    }

    private void walk(Varbind vb)
             throws SnmpMessageException, SnmpValueException {
        VarbindList vbl = new VarbindList();
        vbl.addVarbind(vb);
        boolean loop = true;
        do {
            SnmpPdu pdu = mgr.invokeSnmpGetNext(vbl);
            VarbindList recv_vbl = pdu.getVarbindList();
            vb = recv_vbl.firstVarbind();
            if (pdu.isErrorStatusSet() || vb.isException()) {
                    loop = false;
            } else {
                vbl = new VarbindList();
                vbl.addVarbind(new Varbind(vb.name));
            }
                System.out.println(vb.toString());
        } while (loop);
   }

    private void walk(ManagedInstance mi)
             throws SnmpMessageException, SnmpValueException {
        boolean loop = true;
        Varbind vb = mi.createVarbind();
        VarbindList vbl = new VarbindList();
        vbl.addVarbind(vb);
        do {
            SnmpPdu pdu = mgr.invokeSnmpGetNext(vbl);
            VarbindList recv_vbl = pdu.getVarbindList();
            vb = recv_vbl.firstVarbind();
            if (pdu.isErrorStatusSet() || vb.isException()) {
                loop = false;
            } else {
                vbl = new VarbindList();
                vbl.addVarbind(new Varbind(vb.name));
            }
            System.out.println(vb.toString());
        } while (loop);
    }

    public static void main(String args[]) {
        Arguments a;
        try {
            a =  new Arguments("SnmpWalk");
            a.parse(args);
            SnmpWalk appl = new SnmpWalk(a.generateManager());
            appl.walk(new ManagedInstance(".1.3"));
        } catch (ArgsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
